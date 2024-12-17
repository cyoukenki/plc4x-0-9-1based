package org.apache.plc4x.java.ckd.readwrite.configuration;/*
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

import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.configuration.annotations.ConfigurationParameter;
import org.apache.plc4x.java.spi.configuration.annotations.defaults.IntDefaultValue;
import org.apache.plc4x.java.spi.configuration.annotations.defaults.StringDefaultValue;
import org.apache.plc4x.java.transport.serial.SerialTransportConfiguration;

public class CkdConfiguration implements Configuration, SerialTransportConfiguration {

    @ConfigurationParameter("request-timeout")
    @IntDefaultValue(5_000)
    private int requestTimeout;

    @ConfigurationParameter("unit-identifier")
    @IntDefaultValue(1)
    private int unitIdentifier;

    @ConfigurationParameter("baudRate")
    @IntDefaultValue(115200)
    private int baudRate;
    @ConfigurationParameter("dataBits")
    @IntDefaultValue(8)
    private int dataBits;
    @ConfigurationParameter("stopBits")
    @IntDefaultValue(1)
    private int stopBits;
    @ConfigurationParameter("parityBits")
    @IntDefaultValue(2)
    private int parityBits;
    @ConfigurationParameter("webTopic")
    @StringDefaultValue("")
    private String webTopic;
    @ConfigurationParameter("railNo")
    @IntDefaultValue(1)
    private int railNo;


    @Override
    public int getNumDataBits() {
        return dataBits;
    }

    @Override
    public int getNumStopBits() {
        return stopBits;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getUnitIdentifier() {
        return unitIdentifier;
    }

    public void setUnitIdentifier(int unitIdentifier) {
        this.unitIdentifier = unitIdentifier;
    }

    @Override
    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public int getDataBits() {
        return dataBits;
    }

    public void setDataBits(int dataBits) {
        this.dataBits = dataBits;
    }

    public int getStopBits() {
        return stopBits;
    }

    public void setStopBits(int stopBits) {
        this.stopBits = stopBits;
    }

    @Override
    public int getParityBits() {
        return parityBits;
    }

    public void setParityBits(int parityBits) {
        this.parityBits = parityBits;
    }

    public int getRailNo() {
        return railNo;
    }

    public void setRailNo(int railNo) {
        this.railNo = railNo;
    }

    public String getWebTopic() {
        return webTopic;
    }

    public void setWebTopic(String webTopic) {
        this.webTopic = webTopic;
    }
}
