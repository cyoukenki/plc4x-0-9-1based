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
public class EventInfo {

    @JsonProperty("id")
    private Integer ceid;
    @JsonProperty("eventId")
    private Integer eventId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("enable")
    private String enable;
    @JsonProperty("label")
    private String eventName;
    @JsonProperty("eventStatus")
    private String eventStatus;
    @JsonProperty("eventIdFormat")
    private String eventIdFormat;

    private List<ReportInfo> reportInfoList;

    public Integer getCeid() {
        return ceid;
    }

    public void setCeid(Integer ceid) {
        this.ceid = ceid;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<ReportInfo> getReportInfoList() {
        return reportInfoList;
    }

    public void setReportInfoList(List<ReportInfo> reportInfoList) {
        this.reportInfoList = reportInfoList;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getEventIdFormat() {
        return eventIdFormat;
    }

    public void setEventIdFormat(String eventIdFormat) {
        this.eventIdFormat = eventIdFormat;
    }

    @Override
    public String toString() {
        return "EventInfo{" +
            "ceid=" + ceid +
            ", eventId=" + eventId +
            ", type='" + type + '\'' +
            ", enable='" + enable + '\'' +
            ", eventName='" + eventName + '\'' +
            ", eventStatus='" + eventStatus + '\'' +
            ", eventIdFormat='" + eventIdFormat + '\'' +
            ", reportInfoList=" + reportInfoList +
            '}';
    }
}
