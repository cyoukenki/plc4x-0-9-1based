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
package org.apache.plc4x.java.spi.messages;

import org.apache.plc4x.java.api.messages.PlcRequestMessage;
import org.apache.plc4x.java.api.types.PlcRequestProtocolType;
import org.apache.plc4x.java.api.types.PlcRequestType;

import java.util.Map;

public class DefaultPlcRequestMessage implements PlcRequestMessage {
    private PlcRequestType plcRequestType;//请求类型（read\write ）
    private PlcRequestProtocolType plcRequestProtocolType;//请求通道类型
    private String requestFunctionType;
    private String protocolCommond;
    private String remoteIpAddress;
    private Map<String, Object> queryfileds; //read参数
    private Map<String, Map<String, Object>> writeFileds;//write 参数
    private Map<String, Object> paramMap;

    private String requestUrl;//请求地址参数

    public DefaultPlcRequestMessage() {
    }

    public PlcRequestType getPlcRequestType() {
        return plcRequestType;
    }

    public void setPlcRequestType(PlcRequestType plcRequestType) {
        this.plcRequestType = plcRequestType;
    }

    public Map<String, Object> getQueryfileds() {
        return queryfileds;
    }

    public void setQueryfileds(Map<String, Object> queryfileds) {
        this.queryfileds = queryfileds;
    }

    public Map<String, Map<String, Object>> getWriteFileds() {
        return writeFileds;
    }

    public void setWriteFileds(Map<String, Map<String, Object>> writeFileds) {
        this.writeFileds = writeFileds;
    }

    public PlcRequestProtocolType getPlcRequestProtocolType() {
        return plcRequestProtocolType;
    }

    public void setPlcRequestProtocolType(PlcRequestProtocolType plcRequestProtocolType) {
        this.plcRequestProtocolType = plcRequestProtocolType;
    }

    public String getRequestFunctionType() {
        return requestFunctionType;
    }

    public void setRequestFunctionType(String requestFunctionType) {
        this.requestFunctionType = requestFunctionType;
    }

    public String getRemoteIpAddress() {
        return remoteIpAddress;
    }

    public void setRemoteIpAddress(String remoteIpAddress) {
        this.remoteIpAddress = remoteIpAddress;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getProtocolCommond() {
        return protocolCommond;
    }

    public void setProtocolCommond(String protocolCommond) {
        this.protocolCommond = protocolCommond;
    }

    @Override
    public String toString() {
        return "DefaultPlcRequestMessage{" +
            "plcRequestType=" + plcRequestType +
            ", plcRequestProtocolType=" + plcRequestProtocolType +
            ", requestFunctionType='" + requestFunctionType + '\'' +
            ", remoteIpAddress='" + remoteIpAddress + '\'' +
            ", queryfileds=" + queryfileds +
            ", writeFileds=" + writeFileds +
            ", paramMap=" + paramMap +
            ", requestUrl='" + requestUrl + '\'' +
            '}';
    }
}
