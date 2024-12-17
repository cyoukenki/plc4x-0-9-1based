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
import org.apache.plc4x.java.api.messages.PlcResponseMessage;
import org.apache.plc4x.java.api.types.PlcResponseCode;

import java.util.List;

public class DefaultPlcResponseMessage implements PlcResponseMessage {
    public DefaultPlcResponseMessage() {
    }
    private PlcResponseCode plcResponseCode;
    private int respCode;
    private List<ResItem> responseItems;
    private JsonObject data;

    public PlcResponseCode getPlcResponseCode() {
        return plcResponseCode;
    }

    public void setPlcResponseCode(PlcResponseCode plcResponseCode) {
        this.plcResponseCode = plcResponseCode;
    }

    public List<ResItem> getResponseItems() {
        return responseItems;
    }

    public void setResponseItems(List<ResItem> responseItems) {
        this.responseItems = responseItems;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
