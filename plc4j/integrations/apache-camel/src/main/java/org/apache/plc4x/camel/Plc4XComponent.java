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
import org.apache.camel.support.DefaultComponent;
import org.apache.camel.util.PropertiesHelper;

import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.utils.connectionpool2.CachedDriverManager;
import org.apache.plc4x.java.utils.connectionpool2.PlcConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Plc4XComponent extends DefaultComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(Plc4XComponent.class);

    public Map<String, PlcDriverManager> plcConnectionMap = new HashMap<>();
    public String driverMode = "active";
    public int retryCount = -1;

    public synchronized PlcDriverManager getDriverManager(String uri) {
        String connectionUrl = uri.replaceFirst("plc4x:/?/?", "").split("\\?")[0];
        PlcDriverManager plcDriverManager = plcConnectionMap.get(connectionUrl);
        if (plcDriverManager == null) {
            if (!StringUtils.isEmpty(driverMode) && driverMode.equals("passive")) {
                plcDriverManager = new PlcDriverManager();
            } else {
                plcDriverManager = new CachedDriverManager(uri.replaceFirst("plc4x:/?/?", ""),
                        new PlcConnectionFactory() {
                            @Override
                            public PlcConnection create() throws PlcConnectionException {
                                return new PlcDriverManager().getConnection(uri.replaceFirst("plc4x:/?/?", ""));
                            }
                        });
            }
            plcConnectionMap.put(connectionUrl, plcDriverManager);
        }
        return plcDriverManager;
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        String driverMode = getAndRemoveOrResolveReferenceParameter(parameters, "driverMode", String.class);
        String retryCountStr = getAndRemoveOrResolveReferenceParameter(parameters, "retryCount", String.class);
        if (!StringUtils.isEmpty(driverMode)) {
            this.driverMode = driverMode;
        }
        if (!StringUtils.isEmpty(retryCountStr)) {
            this.retryCount = Integer.parseInt(retryCountStr);
        }
        if (parameters.containsKey("structs")) {

            // Map<String, LinkedHashMap<String, Object>> structs = getAndRemoveOrResolveReferenceParameter(parameters, "structs",
            //         LinkedHashMap.class);
            // LinkedHashMap<String, LinkedHashMap<String, Object>> structs = getAndRemoveOrResolveReferenceParameter(parameters, "structs",LinkedHashMap.class);
            PlcStructureConfig structs = getAndRemoveOrResolveReferenceParameter(parameters, "structs",PlcStructureConfig.class);
            LOGGER.info("aaaa===a==" + structs.get().toString());
            // LOGGER.info("kkkkkk===="+(LinkedHashMap)parameters.get("structs"));
            for (String key : structs.get().keySet()) {
                LOGGER.info("bbbb=====" + key);
            }
            // parameters.remove("structs");
            // parameters.put("structs", structs);
            String strucConfig =new Gson().toJson(structs.get(),LinkedHashMap.class);
            try {
                strucConfig = URLEncoder.encode(strucConfig, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            LOGGER.info("============struct config:" + strucConfig);
            uri = uri.replaceAll("(?<=structs=)([^&]*)", strucConfig);
            LOGGER.info("uri:" + uri);
            // this.uri = this.uri.replaceAll("(?<=structs=)([^&]*)",
            // this.structs.get(structRef).toString());
            // for (String string : uriContext) {
            // if(string.startsWith("structs")){
            // logger.info("source struct config:"+string);
            // structRef = string.split("=")[1];
            // logger.info("source struct ref config:"+structRef);
            // if(this.structs.containsKey(structRef)){
            // this.uri = this.uri.replaceAll("(?<=structs=)([^&]*)",
            // this.structs.get(structRef).toString());
            // logger.info("The uri contains a structure:"+this.uri);
            // }else{
            // logger.error("The structure "+ structRef+" used in the URI is undefined !");
            // }
            // }
            // }

        }
        Plc4XEndpoint endpoint = new Plc4XEndpoint(uri, this);
        // Tags have a Name, a query and an optional value (for writing)
        // Reading --> Map<String,String>
        // Writing --> Map<String,Map.Entry<String,Object>>

        Map<String, Object> tags = getAndRemoveOrResolveReferenceParameter(parameters, "tags", Map.class);

        if (tags != null) {
            LOGGER.info(tags.values().toString());
            endpoint.setTags(tags);
        }
        if (!StringUtils.isEmpty(driverMode)) {
            endpoint.setProtocal(remaining.split(":")[0]);
            endpoint.setDriverMode(driverMode);
            endpoint.setRetryCount(retryCount);
        }
        String trigger = getAndRemoveOrResolveReferenceParameter(parameters, "trigger", String.class);
        if (trigger != null) {
            endpoint.setTrigger(trigger);
        }
        Integer period = getAndRemoveOrResolveReferenceParameter(parameters, "period", Integer.class);
        if (period != null) {
            endpoint.setPeriod(period);
        }
        Boolean eventModel = getAndRemoveOrResolveReferenceParameter(parameters, "eventModel", Boolean.class);
        if (eventModel != null) {
            endpoint.setEventModel(eventModel);
        }
        Boolean isRead = getAndRemoveOrResolveReferenceParameter(parameters, "isRead", Boolean.class);
        if (isRead != null) {
            endpoint.setRead(isRead);
        }

        setProperties(endpoint, parameters);
        return endpoint;
    }

    @Override
    protected void afterConfiguration(String uri, String remaining, Endpoint endpoint, Map<String, Object> parameters) {
        Plc4XEndpoint plc4XEndpoint = (Plc4XEndpoint) endpoint;
        plc4XEndpoint.setDriver(remaining.split(":")[0]);
    }

    @Override
    protected void validateParameters(String uri, Map<String, Object> parameters, String optionPrefix) {
        if (parameters != null && !parameters.isEmpty()) {
            Map<String, Object> param = parameters;
            if (optionPrefix != null) {
                param = PropertiesHelper.extractProperties(parameters, optionPrefix);
            }

            if (parameters.size() > 0) {
                LOGGER.info("{} parameters will be passed to the PLC Driver", param);
            }
        }
    }

}