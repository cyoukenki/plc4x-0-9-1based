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

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportInfo {
    @JsonProperty("id")
    private int id;
    @JsonProperty("eventId")
    private int eventId;
    @JsonProperty("eventIdFormat")
    private String eventIdFormat;
    @JsonProperty("type")
    private String type;
    @JsonProperty("label")
    private String label;
    private List<DataDefinitionInfo> dataDefinitionInfoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventIdFormat() {
        return eventIdFormat;
    }

    public void setEventIdFormat(String eventIdFormat) {
        this.eventIdFormat = eventIdFormat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<DataDefinitionInfo> getDataDefinitionInfoList() {
        return dataDefinitionInfoList;
    }

    public void setDataDefinitionInfoList(List<DataDefinitionInfo> dataDefinitionInfoList) {
        this.dataDefinitionInfoList = dataDefinitionInfoList;
    }
}
