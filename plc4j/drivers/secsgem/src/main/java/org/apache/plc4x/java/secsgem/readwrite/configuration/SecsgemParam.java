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
package org.apache.plc4x.java.secsgem.readwrite.configuration;

public class SecsgemParam {
    private String connectMode;
    private String hostIP;
    private int port;
    private int deviceId;
    private int t3;
    private int t5;
    private int t6;
    private int t7;
    private int t8;
    private int conversationTime;
    private boolean linkTestEnabled;
    private int linkTestTime;//单位 s
    private boolean linkTestMsg;

    public String getConnectMode() {
        return connectMode;
    }

    public void setConnectMode(String connectMode) {
        this.connectMode = connectMode;
    }

    public String getHostIP() {
        return hostIP;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getT5() {
        return t5;
    }

    public void setT5(int t5) {
        this.t5 = t5;
    }

    public int getT6() {
        return t6;
    }

    public void setT6(int t6) {
        this.t6 = t6;
    }

    public int getT7() {
        return t7;
    }

    public void setT7(int t7) {
        this.t7 = t7;
    }

    public int getT8() {
        return t8;
    }

    public void setT8(int t8) {
        this.t8 = t8;
    }

    public int getConversationTime() {
        return conversationTime;
    }

    public void setConversationTime(int conversationTime) {
        this.conversationTime = conversationTime;
    }

    public boolean isLinkTestEnabled() {
        return linkTestEnabled;
    }

    public void setLinkTestEnabled(boolean linkTestEnabled) {
        this.linkTestEnabled = linkTestEnabled;
    }

    public int getLinkTestTime() {
        return linkTestTime;
    }

    public void setLinkTestTime(int linkTestTime) {
        this.linkTestTime = linkTestTime;
    }


    public boolean isLinkTestMsg() {
        return linkTestMsg;
    }

    public void setLinkTestMsg(boolean linkTestMsg) {
        this.linkTestMsg = linkTestMsg;
    }
}
