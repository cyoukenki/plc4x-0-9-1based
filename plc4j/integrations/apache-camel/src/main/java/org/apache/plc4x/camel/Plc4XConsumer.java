/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.plc4x.camel;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.support.DefaultConsumer;
import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.PlcDriver;
import org.apache.plc4x.java.api.PlcServerConnector;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.exceptions.PlcIncompatibleDatatypeException;
import org.apache.plc4x.java.api.listener.MessageExchangeListener;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.scraper.config.JobConfigurationImpl;
import org.apache.plc4x.java.scraper.config.ScraperConfiguration;
import org.apache.plc4x.java.scraper.config.triggeredscraper.ScraperConfigurationTriggeredImpl;
import org.apache.plc4x.java.scraper.exception.ScraperException;
import org.apache.plc4x.java.scraper.triggeredscraper.TriggeredScraperImpl;
import org.apache.plc4x.java.scraper.triggeredscraper.triggerhandler.collector.TriggerCollector;
import org.apache.plc4x.java.scraper.triggeredscraper.triggerhandler.collector.TriggerCollectorImpl;
import org.apache.plc4x.java.utils.connectionpool2.CachedPlcConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.*;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Plc4XConsumer extends DefaultConsumer implements MessageExchangeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Plc4XConsumer.class);

    private final Map<String, Object> tags;
    private final String trigger;
    private final String driverMode;
    private int maxRetryCount;
    private final String protocal;
    private final Plc4XEndpoint plc4XEndpoint;
    private final Boolean eventModel;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> future;
    private TriggerCollector collector;
    private TriggeredScraperImpl scraper;
    private PlcServerConnector plcServerConnector;
    private PlcConnection plcConnection;
    private int retryNum = 0;


    public Plc4XConsumer(Plc4XEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.plc4XEndpoint = endpoint;
//        this.plcConnection = endpoint.getConnection();
        this.tags = endpoint.getTags();
        this.trigger = endpoint.getTrigger();
        this.eventModel = endpoint.getEventModel();
        this.driverMode = endpoint.getDriverMode();
        this.protocal = endpoint.getProtocal();
        this.maxRetryCount = endpoint.getRetryCount();
    }

    @Override
    public String toString() {
        return "Plc4XConsumer[" + plc4XEndpoint + "]";
    }

    @Override
    public Endpoint getEndpoint() {
        return plc4XEndpoint;
    }

    @Override
    protected void doStart() throws ScraperException {
        if (!StringUtils.isEmpty(driverMode) && driverMode.equals("passive") && !StringUtils.isEmpty(protocal)) {
            startSub();
        } else {
            if (trigger == null) {
                startUnTriggered();
            } else {
                startTriggered();
            }
        }
    }

    private synchronized void startSub() {
        while (maxRetryCount > 0 && (plcServerConnector == null || !plcServerConnector.isStart())) {
            try {
                plc4XEndpoint.getPlcDriverManager().getDriver(protocal).setListener(Plc4XConsumer.this);
                plcServerConnector = plc4XEndpoint.getPlcDriverManager().getServerConnector(plc4XEndpoint.getUri());
                plcServerConnector.start();
            } catch (PlcConnectionException e) {
                LOGGER.error("getPlcConnection failed  in Plc4xConsumer startSub {} ,{}", plc4XEndpoint.getUri(), e.getMessage());
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            maxRetryCount--;
        }
    }


    private void startUnTriggered() {
        PlcConnection plcConnection;
        try {
            plcConnection = plc4XEndpoint.getPlcDriverManager().getConnection(plc4XEndpoint.getUri());
        } catch (PlcConnectionException e) {
            LOGGER.error("getPlcConnection failed  in Plc4xConsumer startUnTriggered {}", e.getMessage());
            throw new RuntimeException(e);
        }
        PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
        for (Map.Entry<String, Object> tag : tags.entrySet()) {
            try {
                builder.addItem(tag.getKey(), (String) tag.getValue());
            } catch (PlcIncompatibleDatatypeException e) {
                LOGGER.error("For consumer, please use Map<String,String>, currently using {}",
                    tags.getClass().getSimpleName());
            }
        }
        PlcReadRequest request = builder.build();
        future = executorService.schedule(() -> request.execute().thenAccept(response -> {
            try {
                Exchange exchange = plc4XEndpoint.createExchange();
                // Map<String, Object> rsp = new HashMap<>();
                // for (String field : response.getFieldNames()) {
                //     rsp.put(field, response.getObject(field));
                // }

                JsonObject rsp = new JsonObject();

                for (String field : response.getFieldNames()) {
                    rsp.addProperty(field, String.valueOf(response.getObject(field)));
                }
                exchange.getIn().setBody(rsp);
                getProcessor().process(exchange);

                LOGGER.debug("Response message :" + rsp.toString());
            } catch (Exception e) {
                getExceptionHandler().handleException(e);
            } finally {
                try {
                    plcConnection.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }), 500, TimeUnit.MILLISECONDS);
    }

    static Map<String, Object> lastRes = new HashMap<>();

    private void startTriggered() throws ScraperException {
        ScraperConfiguration configuration = getScraperConfig(validateTags());
        collector = new TriggerCollectorImpl(plc4XEndpoint.getPlcDriverManager());
        scraper = new TriggeredScraperImpl(configuration, plc4XEndpoint.getPlcDriverManager(), (job, alias, response) -> {
            try {
                if (eventModel) {
                    if (response.equals(lastRes)) {
                        return;
                    }

                }
                Exchange exchange = plc4XEndpoint.createExchange();
                exchange.getIn().setBody(response);
                getProcessor().process(exchange);

            } catch (Exception e) {
                getExceptionHandler().handleException(e);
            }
            lastRes.clear();
            lastRes = response;
        }, collector);
        scraper.start();
        collector.start();
    }

    private Map<String, String> validateTags() {

        Map<String, String> map = new HashMap<>();
        for (Map.Entry<String, Object> tag : tags.entrySet()) {
            if (tag.getValue() instanceof String) {
                map.put(tag.getKey(), (String) tag.getValue());
            }
        }
        if (map.size() != tags.size()) {
            LOGGER.error("At least one entry does not match the format : Map.Entry<String,String> ");
            return null;
        } else
            return map;
    }

    private ScraperConfigurationTriggeredImpl getScraperConfig(Map<String, String> tagList) {
        // LOGGER.info("===================tags:" + tagList.toString());
        // String config = "(TRIGGER_VAR," + plc4XEndpoint.getPeriod() + ",(" +
        // plc4XEndpoint.getTrigger() + ")==(true))";
        String config = "(SCHEDULED," + plc4XEndpoint.getPeriod() + ")";
        List<JobConfigurationImpl> job = Collections.singletonList(new JobConfigurationImpl("PLC4X-Camel", config, 0,
            Collections.singletonList(Constants.PLC_NAME), tagList));
        Map<String, String> source = Collections.singletonMap(Constants.PLC_NAME, plc4XEndpoint.getUri());
        return new ScraperConfigurationTriggeredImpl(source, job);
    }

    @Override
    protected void doStop() {
        // First stop the polling process
        if (future != null) {
            future.cancel(true);
        }
        if (collector != null) {
            collector.stop();
        }
        if (scraper != null && scraper.isRunning()) {
            scraper.stop();
        }
        if (plcServerConnector != null && plcServerConnector.isStart()) {
            try {
                plcServerConnector.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        PlcConnection connection = null;
        try {
            connection = plc4XEndpoint.getPlcDriverManager().getConnection(plc4XEndpoint.getUri());
            if (connection != null && connection.isConnected()) {
                if (connection instanceof CachedPlcConnection) {
                    ((CachedPlcConnection) connection).releaseConenction();
                } else {
                    connection.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void received(Object message) {
        Exchange exchange = plc4XEndpoint.createExchange();
        exchange.getIn().setBody(message);
        try {
            getProcessor().process(exchange);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sending(Object message) {

    }
}