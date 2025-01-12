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

import org.apache.camel.*;
import org.apache.camel.support.DefaultEndpoint;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriEndpoint;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.PlcServerConnector;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.utils.connectionpool2.CachedPlcConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;
import java.util.Map;
import java.util.Objects;


@UriEndpoint(scheme = "plc4x", title = "PLC4X", syntax = "plc4x:driver", label = "plc4x")
public class Plc4XEndpoint extends DefaultEndpoint {
    private final Logger logger = LoggerFactory.getLogger(Plc4XEndpoint.class);
    @UriPath
    @Metadata(required = true)
    private String driver;

    @UriParam
    private Map<String, Object> tags;
    @UriParam
    private Map<String, Map<String,Object>> structs;
    @UriParam
    private Boolean eventModel = false;


    @UriParam
    private String trigger;

    @UriParam
    private int period;
    @UriParam
    private Boolean isRead = false;
    @UriParam
    private String driverMode;
    private String protocal;
    private int retryCount;

    public Boolean getEventModel() {
        return eventModel;
    }

    public void setEventModel(Boolean eventModel) {
        this.eventModel = eventModel;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    private PlcDriverManager plcDriverManager;
    private String uri;

    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public void setDriverMode(String driverMode) {
        this.driverMode = driverMode;
    }

    public String getDriverMode() {
        return driverMode;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public String getProtocal() {
        return protocal;
    }

    public Plc4XEndpoint(String endpointUri, Component component) throws PlcConnectionException {
        super(endpointUri, component);
        //Here we establish the connection in the endpoint, as it is created once during the context
        // to avoid disconnecting and reconnecting for every request
        this.plcDriverManager = ((Plc4XComponent) component).getDriverManager(endpointUri);
        this.uri = endpointUri.replaceFirst("plc4x:/?/?", "");
       
    }

    @Override
    public void setProperties(Object bean, Map<String, Object> parameters) {

    }

    @Override
    public Producer createProducer() throws Exception {
        //Checking if connection is still up and reconnecting if not
        return new Plc4XProducer(this);
    }

    @Override
    public Consumer createConsumer(Processor processor) {
        //Checking if connection is still up and reconnecting if not
        return new Plc4XConsumer(this, processor);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public PlcDriverManager getPlcDriverManager() {
        return plcDriverManager;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    public void setTags(Map<String, Object> tags) {
        this.tags = tags;
    }
    public Map<String,Map<String, Object>> getStructs() {
        return structs;
    }

    public void setStructs(Map<String,Map<String, Object>> structs) {
        this.structs = structs;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getRetryCount() {
        return retryCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Plc4XEndpoint)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Plc4XEndpoint that = (Plc4XEndpoint) o;
        return Objects.equals(getDriver(), that.getDriver()) &&
            Objects.equals(getTags(), that.getTags()) &&
            Objects.equals(getPlcDriverManager(), that.getPlcDriverManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDriver(), getTags(), getPlcDriverManager());
    }

    @Override
    public void doStop() throws Exception {
        //Shutting down the connection when leaving the Context
//        if (!StringUtils.isEmpty(driverMode) && driverMode.equals("passive")) {
//            PlcServerConnector serverConnector = plcDriverManager.getServerConnector(this.uri);
//            if (serverConnector.isStart()) {
//                serverConnector.stop();
//            }
//        } else {
//            PlcConnection connection = plcDriverManager.getConnection(this.uri);
//            if (connection != null && connection.isConnected()) {
//                if (connection instanceof CachedPlcConnection) {
//                    ((CachedPlcConnection) connection).releaseConenction();
//                } else {
//                    connection.close();
//                }
//            }
//        }
    }
}
