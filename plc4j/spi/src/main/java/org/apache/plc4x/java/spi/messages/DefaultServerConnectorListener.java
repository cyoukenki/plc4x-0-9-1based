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


import com.google.gson.JsonObject;
import org.apache.plc4x.java.api.exceptions.PlcNotImplementedException;
import org.apache.plc4x.java.api.listener.ServerConnectorListener;

import java.util.function.Consumer;

public interface DefaultServerConnectorListener extends ServerConnectorListener {
    void onError(Throwable throwable);
    default void onMessage(Object message) {
        throw new PlcNotImplementedException("Not implemented for this method");
    }
    default DefaultPlcResponseMessage onRequestMessage(DefaultPlcRequestMessage defaultPlcRequestMessage){
        throw new PlcNotImplementedException("Not implemented for this method");
    }
    default void sendConsumer(Consumer consumer){
        throw new PlcNotImplementedException("Not implemented for this method");
    }
    default DefaultResponseMessage onRequestMessage(DefaultRequestMessage defaultRequestMessage){
        throw new PlcNotImplementedException("Not implemented for this method");
    }
}