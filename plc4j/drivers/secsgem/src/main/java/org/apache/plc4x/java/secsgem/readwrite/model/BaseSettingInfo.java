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
package org.apache.plc4x.java.secsgem.readwrite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseSettingInfo implements Serializable {
    @JsonProperty("hostIP")
    private String hostIP;
    @JsonProperty("connectionIP")
    private String connectionIP;
    @JsonProperty("IP-address-assignment-on-passive")
    private String iPaddressassignmentonpassive;
    @JsonProperty("linkTestReqSendInterval")
    private String linkTestReqSendInterval;
    @JsonProperty("description")
    private String description;
    @JsonProperty("conversationTimeOut")
    private String conversationTimeOut;
    @JsonProperty("connectionPort")
    private String connectionPort;
    @JsonProperty("gatewayPort")
    private String gatewayPort;
    @JsonProperty("deviceId")
    private String deviceId;
    @JsonProperty("connectMode")
    private String connectMode;
    @JsonProperty("protocalType")
    private String protocalType;
    @JsonProperty("networkInterCharactorTimeOut")
    private String networkInterCharactorTimeOut;//t8
    @JsonProperty("notSelectedTimeOut")
    private String notSelectedTimeOut;//t7
    @JsonProperty("controlTransationTimeOut")
    private String controlTransationTimeOut;//t6
    @JsonProperty("name")
    private String name;
    @JsonProperty("connectSeparationTimeOut")
    private String connectSeparationTimeOut;//t5
    @JsonProperty("transportType")
    private String transportType;
    @JsonProperty("hostPort")
    private String hostPort;
    @JsonProperty("replyTimeOut")
    private String replyTimeOut;//t3
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("loggingLinkTestMessage")
    private String loggingLinkTestMessage;
    @JsonProperty("option")
    private String option;
    @JsonProperty("eqStatus")
    private String eqStatus;
    @JsonProperty("controlStatus")
    private String controlStatus;

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public String getConnectionIP() {
        return connectionIP;
    }

    public void setConnectionIP(String connectionIP) {
        this.connectionIP = connectionIP;
    }

    public String getiPaddressassignmentonpassive() {
        return iPaddressassignmentonpassive;
    }

    public void setiPaddressassignmentonpassive(String iPaddressassignmentonpassive) {
        this.iPaddressassignmentonpassive = iPaddressassignmentonpassive;
    }

    public String getLinkTestReqSendInterval() {
        return linkTestReqSendInterval;
    }

    public void setLinkTestReqSendInterval(String linkTestReqSendInterval) {
        this.linkTestReqSendInterval = linkTestReqSendInterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConversationTimeOut() {
        return conversationTimeOut;
    }

    public void setConversationTimeOut(String conversationTimeOut) {
        this.conversationTimeOut = conversationTimeOut;
    }

    public String getConnectionPort() {
        return connectionPort;
    }

    public void setConnectionPort(String connectionPort) {
        this.connectionPort = connectionPort;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getConnectMode() {
        return connectMode;
    }

    public void setConnectMode(String connectMode) {
        this.connectMode = connectMode;
    }

    public String getProtocalType() {
        return protocalType;
    }

    public void setProtocalType(String protocalType) {
        this.protocalType = protocalType;
    }




    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoggingLinkTestMessage() {
        return loggingLinkTestMessage;
    }

    public void setLoggingLinkTestMessage(String loggingLinkTestMessage) {
        this.loggingLinkTestMessage = loggingLinkTestMessage;
    }

    public String getNetworkInterCharactorTimeOut() {
        return networkInterCharactorTimeOut;
    }

    public void setNetworkInterCharactorTimeOut(String networkInterCharactorTimeOut) {
        this.networkInterCharactorTimeOut = networkInterCharactorTimeOut;
    }

    public String getNotSelectedTimeOut() {
        return notSelectedTimeOut;
    }

    public void setNotSelectedTimeOut(String notSelectedTimeOut) {
        this.notSelectedTimeOut = notSelectedTimeOut;
    }
    public String getControlTransationTimeOut() {
        return controlTransationTimeOut;
    }

    public void setControlTransationTimeOut(String controlTransationTimeOut) {
        this.controlTransationTimeOut = controlTransationTimeOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnectSeparationTimeOut() {
        return connectSeparationTimeOut;
    }

    public void setConnectSeparationTimeOut(String connectSeparationTimeOut) {
        this.connectSeparationTimeOut = connectSeparationTimeOut;
    }

    public String getReplyTimeOut() {
        return replyTimeOut;
    }

    public void setReplyTimeOut(String replyTimeOut) {
        this.replyTimeOut = replyTimeOut;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getEqStatus() {
        return eqStatus;
    }

    public void setEqStatus(String eqStatus) {
        this.eqStatus = eqStatus;
    }

    public String getControlStatus() {
        return controlStatus;
    }

    public void setControlStatus(String controlStatus) {
        this.controlStatus = controlStatus;
    }

    public String getGatewayPort() {
        return gatewayPort;
    }

    public void setGatewayPort(String gatewayPort) {
        this.gatewayPort = gatewayPort;
    }

    @Override
    public String toString() {
        return "BaseSettingInfo{" +
            "hostIP='" + hostIP + '\'' +
            ", connectionIP='" + connectionIP + '\'' +
            ", iPaddressassignmentonpassive='" + iPaddressassignmentonpassive + '\'' +
            ", linkTestReqSendInterval='" + linkTestReqSendInterval + '\'' +
            ", description='" + description + '\'' +
            ", conversationTimeOut='" + conversationTimeOut + '\'' +
            ", connectionPort='" + connectionPort + '\'' +
            ", gatewayPort='" + gatewayPort + '\'' +
            ", deviceId='" + deviceId + '\'' +
            ", connectMode='" + connectMode + '\'' +
            ", protocalType='" + protocalType + '\'' +
            ", networkInterCharactorTimeOut='" + networkInterCharactorTimeOut + '\'' +
            ", notSelectedTimeOut='" + notSelectedTimeOut + '\'' +
            ", controlTransationTimeOut='" + controlTransationTimeOut + '\'' +
            ", name='" + name + '\'' +
            ", connectSeparationTimeOut='" + connectSeparationTimeOut + '\'' +
            ", transportType='" + transportType + '\'' +
            ", hostPort='" + hostPort + '\'' +
            ", replyTimeOut='" + replyTimeOut + '\'' +
            ", id=" + id +
            ", loggingLinkTestMessage='" + loggingLinkTestMessage + '\'' +
            ", option='" + option + '\'' +
            ", eqStatus='" + eqStatus + '\'' +
            ", controlStatus='" + controlStatus + '\'' +
            '}';
    }
}