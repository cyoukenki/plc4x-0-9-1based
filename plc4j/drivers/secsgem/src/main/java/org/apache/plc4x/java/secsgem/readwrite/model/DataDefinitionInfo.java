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
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataDefinitionInfo {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("dataId")
    private Integer dataId;
    @JsonProperty("type")
    private String type;
    @JsonProperty("dataIdFormat")
    private String dataIdFormat;
    @JsonProperty("description")
    private String description;
    @JsonProperty("format")
    private String format;
    @JsonProperty("dataSize")
    private String dataSize;
    @JsonProperty("fixedLength")
    private String fixedLength;
    @JsonProperty("ecMax")
    private String ecMax;
    @JsonProperty("ecMin")
    private String ecMin;
    @JsonProperty("ecDef")
    private String ecDef;
    @JsonProperty("units")
    private String units;
    @JsonProperty("linkVariable")
    private String linkVariable;
    @JsonProperty("label")
    private String label;
    @JsonProperty("traceTarget")
    private String traceTarget;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getFixedLength() {
        return fixedLength;
    }

    public void setFixedLength(String fixedLength) {
        this.fixedLength = fixedLength;
    }

    public String getEcMax() {
        return ecMax;
    }

    public void setEcMax(String ecMax) {
        this.ecMax = ecMax;
    }

    public String getEcMin() {
        return ecMin;
    }

    public void setEcMin(String ecMin) {
        this.ecMin = ecMin;
    }

    public String getEcDef() {
        return ecDef;
    }

    public void setEcDef(String ecDef) {
        this.ecDef = ecDef;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getLinkVariable() {
        return linkVariable;
    }

    public void setLinkVariable(String linkVariable) {
        this.linkVariable = linkVariable;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDataIdFormat() {
        return dataIdFormat;
    }

    public void setDataIdFormat(String dataIdFormat) {
        this.dataIdFormat = dataIdFormat;
    }

    public String getTraceTarget() {
        return traceTarget;
    }

    public void setTraceTarget(String traceTarget) {
        this.traceTarget = traceTarget;
    }

    @Override
    public String toString() {
        return "DataDefinitionInfo{" +
            "id=" + id +
            ", dataId=" + dataId +
            ", type='" + type + '\'' +
            ", dataIdFormat='" + dataIdFormat + '\'' +
            ", description='" + description + '\'' +
            ", format='" + format + '\'' +
            ", dataSize='" + dataSize + '\'' +
            ", fixedLength='" + fixedLength + '\'' +
            ", ecMax='" + ecMax + '\'' +
            ", ecMin='" + ecMin + '\'' +
            ", ecDef='" + ecDef + '\'' +
            ", units='" + units + '\'' +
            ", linkVariable='" + linkVariable + '\'' +
            ", label='" + label + '\'' +
            ", traceTarget='" + traceTarget + '\'' +
            '}';
    }
}
