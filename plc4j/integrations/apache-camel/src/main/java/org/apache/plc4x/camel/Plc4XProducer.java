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

import com.google.gson.Gson;
import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.support.DefaultAsyncProducer;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.exceptions.PlcException;
import org.apache.plc4x.java.api.exceptions.PlcIncompatibleDatatypeException;
import org.apache.plc4x.java.api.exceptions.PlcInvalidFieldException;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.messages.PlcWriteResponse;
import org.apache.plc4x.java.scraper.config.JobConfigurationImpl;
import org.apache.plc4x.java.scraper.config.ScraperConfiguration;
import org.apache.plc4x.java.scraper.config.triggeredscraper.ScraperConfigurationTriggeredImpl;
import org.apache.plc4x.java.scraper.exception.ScraperException;
import org.apache.plc4x.java.scraper.triggeredscraper.TriggeredScraperImpl;
import org.apache.plc4x.java.scraper.triggeredscraper.triggerhandler.collector.TriggerCollector;
import org.apache.plc4x.java.scraper.triggeredscraper.triggerhandler.collector.TriggerCollectorImpl;
import org.apache.plc4x.java.spi.messages.DefaultPlcWriteResponse;
import org.apache.plc4x.java.utils.connectionpool2.CachedPlcConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Plc4XProducer extends DefaultAsyncProducer {
    private final Logger logger = LoggerFactory.getLogger(Plc4XProducer.class);
    private AtomicInteger openRequests;
    private Plc4XEndpoint plc4XEndpoint;
    private final String trigger;
    private final Boolean isRead;
    private final Boolean eventModel;
    Map<String, Object> tags;
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> future;

    public Plc4XProducer(Plc4XEndpoint endpoint) throws PlcException {
        super(endpoint);
        this.tags = endpoint.getTags();
        this.plc4XEndpoint = endpoint;
        this.trigger = endpoint.getTrigger();
        this.isRead = endpoint.getRead();
        this.eventModel = endpoint.getEventModel();
        openRequests = new AtomicInteger();
    }

    @Override
    public void process(Exchange exchange) throws Exception {
            if(isRead) {
                readProcess(exchange);
            } else {
                writeProcess(exchange);
            }
    }

    private void writeProcess(Exchange exchange) {
        PlcConnection plcConnection;
        try {
            plcConnection = plc4XEndpoint.getPlcDriverManager().getConnection(plc4XEndpoint.getUri());
        } catch (PlcConnectionException e) {
            logger.error("getPlcConnection failed  in Plc4xProducer writeProcess {}",e.getMessage());
            throw new RuntimeException(e);
        }
        Message in = exchange.getIn();
        Object body = in.getBody();
        PlcWriteRequest.Builder builder = plcConnection.writeRequestBuilder();
        if (body instanceof Map) { // Check if we have a Map
            Map<String, Map<String, Object>> tags = (Map<String, Map<String, Object>>) body;
            for (Map.Entry<String, Map<String, Object>> entry : tags.entrySet()) {
                // Tags are stored like this --> Map<Tagname,Map<Query,Value>> for writing
                String name = entry.getKey();
                String query = entry.getValue().keySet().iterator().next();
                Object value = entry.getValue().get(query);
                if(value.getClass().isArray()) {
                    builder.addItem(name, query, (Object[])value);
                } else {
                    builder.addItem(name, query, value);
                }
            }
        } else {
            throw new PlcInvalidFieldException("The body must contain a Map<String,Map<String,Object>");
        }

        CompletableFuture<? extends PlcWriteResponse> completableFuture = builder.build().execute();
        int currentlyOpenRequests = openRequests.incrementAndGet();
        try {
            // log.debug("8888888888888888888:" + currentlyOpenRequests);
            logger.debug("Currently open requests including {}:{}", exchange, currentlyOpenRequests);
            DefaultPlcWriteResponse plcWriteResponse = (DefaultPlcWriteResponse) completableFuture.get();
            if (exchange.getPattern().isOutCapable()) {
                Message out = exchange.getOut();
                out.copyFrom(exchange.getIn());
                out.setBody(plcWriteResponse);
            }
//            else {
                Collection<String> fieldNames = plcWriteResponse.getFieldNames();
                Map<String,String> responseMap = new HashMap<>();
                for (String fileName:fieldNames
                     ) {
                    responseMap.put(fileName,plcWriteResponse.getResponseCode(fileName).name());
                }
                in.setBody(new Gson().toJson(responseMap));
//            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            int openRequestsAfterFinish = openRequests.decrementAndGet();
            logger.trace("Open Requests after {}:{}", exchange, openRequestsAfterFinish);
            try {
                plcConnection.close();
            } catch (Exception e) {
                logger.error("CachePlcConenction close error in Plc4XProducer writeProcess{}",e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    private void readProcess(Exchange exchange) throws ScraperException {
        if (trigger == null) {
            startUnTriggered(exchange);
        } else {
            startTriggered(exchange);
        }
    }

    @Override
    public boolean process(Exchange exchange, AsyncCallback callback) {
        try {
            process(exchange);
            Message out = exchange.getOut();
            out.copyFrom(exchange.getIn());
        } catch (Exception e) {
            exchange.setOut(null);
            exchange.setException(e);
        }
        callback.done(true);
        return true;
    }

    @Override
    protected void doStop() throws Exception {
        int openRequestsAtStop = openRequests.get();
        logger.debug("Stopping with {} open requests", openRequestsAtStop);
        if (openRequestsAtStop > 0) {
            logger.warn("There are still {} open requests", openRequestsAtStop);
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
    private void startUnTriggered(Exchange exchange) {
        PlcConnection plcConnection;
        try {
            plcConnection = plc4XEndpoint.getPlcDriverManager().getConnection(plc4XEndpoint.getUri());
        } catch (PlcConnectionException e) {
            logger.error("getPlcConnection failed  in Plc4xConsumer startUnTriggered {}",e.getMessage());
            throw new RuntimeException(e);
        }
        Message in = exchange.getIn();
        Object body = in.getBody();
        PlcReadRequest.Builder builder = null;
        try {
            builder = plc4XEndpoint.getPlcDriverManager().getConnection(plc4XEndpoint.getUri()).readRequestBuilder();
        } catch (PlcConnectionException e) {
            logger.error("get connection failed in plc4xProducer for unTriggered with {}",
                plc4XEndpoint.getUri());
            throw new RuntimeException(e);
        }
        if (body instanceof Map) { // Check if we have a Map
            Map<String, Object> tags = (Map<String, Object>) body;
            for (Map.Entry<String, Object> tag : tags.entrySet()) {
                try {
                    builder.addItem(tag.getKey(), (String) tag.getValue());
                } catch (PlcIncompatibleDatatypeException e) {
                    logger.error("For consumer, please use Map<String,String>, currently using {}",
                        tags.getClass().getSimpleName());
                }
            }
        } else {
            throw new PlcInvalidFieldException("The body must contain a Map<String,Object>");
        }
        PlcReadRequest request = builder.build();

        future = executorService.schedule(() -> request.execute().thenAccept(response -> {
            try {
                Map<String, Object> rsp = new HashMap<>();
                for (String field : response.getFieldNames()) {
                    rsp.put(field, String.valueOf(response.getObject(field)));;
                }
                if (exchange.getPattern().isOutCapable()) {
                    Message out = exchange.getOut();
                    out.copyFrom(exchange.getIn());
                    out.setBody(rsp);
                } else {
                    in.setBody(rsp);
                }
                logger.debug("Response message producer startUnTriggered:"+rsp.toString());
            } catch (Exception e) {
                logger.error("read Response message error:"+e.getMessage());
            } finally {
                try {
                    plcConnection.close();
                } catch (Exception e) {
                    logger.trace("CacheDriverManager CachePlcConenction close error in startUnTriggered {}",e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }), 500, TimeUnit.MILLISECONDS);
    }
    static Map<String, Object> lastRes = new HashMap<>();

    private void startTriggered(Exchange exchange) throws ScraperException {
        Message in = exchange.getIn();

        Object body = in.getBody();

        ScraperConfiguration configuration = getScraperConfig(validateTags(body));
        TriggerCollector collector = new TriggerCollectorImpl(plc4XEndpoint.getPlcDriverManager());

        TriggeredScraperImpl scraper = new TriggeredScraperImpl(configuration, plc4XEndpoint.getPlcDriverManager(),(job, alias, response) -> {

            try {
                if ( eventModel) {
                    if(response.equals(lastRes)){
                        return;
                    }

                }
                if (exchange.getPattern().isOutCapable()) {
                    Message out = exchange.getOut();
                    out.copyFrom(exchange.getIn());
                    out.setBody(response);
                } else {
                    in.setBody(response);
                }
                logger.debug("Response message producer startTriggered:"+response.toString());
            } catch (Exception e) {
                logger.error("read Response message error:"+e.getMessage());
            }
            lastRes.clear();
            lastRes = response;
        }, collector);
        scraper.start();
        collector.start();
    }
    private ScraperConfigurationTriggeredImpl getScraperConfig(Map<String, String> tagList) {
        String config = "(SCHEDULED," + plc4XEndpoint.getPeriod() + ")";
        List<JobConfigurationImpl> job = Collections.singletonList(new JobConfigurationImpl("PLC4X-Camel", config, 0,
            Collections.singletonList(Constants.PLC_NAME), tagList));
        Map<String, String> source = Collections.singletonMap(Constants.PLC_NAME, plc4XEndpoint.getUri());
        return new ScraperConfigurationTriggeredImpl(source, job);
    }
    private Map<String, String> validateTags(Object body) {
        if (body instanceof Map) { // Check if we have a Map
            Map<String, String> tags = (Map<String, String>) body;
            return tags;
        } else {
            throw new PlcInvalidFieldException("The body must contain a Map<String,Object>");
        }
    }
}
