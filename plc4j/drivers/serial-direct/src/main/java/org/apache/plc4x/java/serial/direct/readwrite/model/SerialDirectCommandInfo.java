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
package org.apache.plc4x.java.serial.direct.readwrite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.WriteBuffer;
import org.apache.plc4x.java.spi.utils.Serializable;

public class SerialDirectCommandInfo implements Serializable {
    @JsonProperty(value = "pcbId")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String pcbId;
    @JsonProperty(value = "comPort")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String comPort;
    @JsonProperty(value = "statusInfo")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String statusInfo;
    @JsonProperty(value = "railNo")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private int railNo;//轨道信息 轨道1：1，轨道2：2

    public SerialDirectCommandInfo() {
    }

    public SerialDirectCommandInfo(String pcbId, String comPort, String statusInfo) {
        this.pcbId = pcbId;
        this.comPort = comPort;
        this.statusInfo = statusInfo;
    }

    public SerialDirectCommandInfo(String pcbId, String comPort, String statusInfo, int railNo) {
        this.pcbId = pcbId;
        this.comPort = comPort;
        this.statusInfo = statusInfo;
        this.railNo = railNo;
    }

    public String getPcbId() {
        return pcbId;
    }

    public void setPcbId(String pcbId) {
        this.pcbId = pcbId;
    }

    public String getComPort() {
        return comPort;
    }

    public void setComPort(String comPort) {
        this.comPort = comPort;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public int getRailNo() {
        return railNo;
    }

    public void setRailNo(int railNo) {
        this.railNo = railNo;
    }


    @Override
    public String toString() {
        return "SerialDirectCommandInfo{" +
            "pcbId='" + pcbId + '\'' +
            ", comPort='" + comPort + '\'' +
            ", statusInfo='" + statusInfo + '\'' +
            ", railNo=" + railNo +
            '}';
    }

    @Override
    public void serialize(WriteBuffer writeBuffer) throws ParseException {

    }
}
