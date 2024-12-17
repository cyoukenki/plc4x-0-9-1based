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

import org.apache.plc4x.java.secsgem.readwrite.constant.ConnectionStatus;
import org.apache.plc4x.java.secsgem.readwrite.constant.ServerConstants;
import org.apache.plc4x.java.spi.ConversationContext;

public class HostChannelInfo {
    String address;
    ConversationContext context;
    ConnectionStatus connectionStatus;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ConversationContext getContext() {
        return context;
    }

    public void setContext(ConversationContext context) {
        this.context = context;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public HostChannelInfo(String address, ConversationContext context, ConnectionStatus connectionStatus) {
        this.address = address;
        this.context = context;
        this.connectionStatus = connectionStatus;
    }
}
