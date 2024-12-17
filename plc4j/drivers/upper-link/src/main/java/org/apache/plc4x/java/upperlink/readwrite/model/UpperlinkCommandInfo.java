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
package org.apache.plc4x.java.upperlink.readwrite.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.WriteBuffer;
import org.apache.plc4x.java.spi.utils.Serializable;

public class UpperlinkCommandInfo implements Serializable {
    @JsonProperty(value = "operationStatusCode")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String operationStatusCode;
    @JsonProperty(value = "operationStatus")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String operationStatus;
    @JsonProperty(value = "statusInfo")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String statusInfo;
    @JsonProperty(value = "railWidth")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String railWidth;
    @JsonProperty(value = "errorContentStatusCode")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String errorContentStatusCode;
    @JsonProperty(value = "errorContentStatus")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String errorContentStatus;
    @JsonProperty(value = "isRD")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private boolean isRD;
    @JsonProperty(value = "rdBeginingWord")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String rdBeginingWord;
    @JsonProperty(value = "no_of_words")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String no_of_words;
    @JsonProperty(value = "wdBeginingWord")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String wdBeginingWord;
    @JsonProperty(value = "wdRemainWord")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String wdRemainWord;

    public UpperlinkCommandInfo(String statusInfo, boolean isRD, String rdBeginingWord, String no_of_words) {
        this.statusInfo = statusInfo;
        this.isRD = isRD;
        this.rdBeginingWord = rdBeginingWord;
        this.no_of_words = no_of_words;
    }

    public UpperlinkCommandInfo(String operationStatusCode, String operationStatus, String statusInfo, String railWidth, String errorContentStatusCode, String errorContentStatus, boolean isRD, String rdBeginingWord, String no_of_words, String wdBeginingWord) {
        this.operationStatusCode = operationStatusCode;
        this.operationStatus = operationStatus;
        this.statusInfo = statusInfo;
        this.railWidth = railWidth;
        this.errorContentStatusCode = errorContentStatusCode;
        this.errorContentStatus = errorContentStatus;
        this.isRD = isRD;
        this.rdBeginingWord = rdBeginingWord;
        this.no_of_words = no_of_words;
        this.wdBeginingWord = wdBeginingWord;
    }

    public UpperlinkCommandInfo() {

    }

    public String getOperationStatusCode() {
        return operationStatusCode;
    }

    public void setOperationStatusCode(String operationStatusCode) {
        this.operationStatusCode = operationStatusCode;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getRailWidth() {
        return railWidth;
    }

    public void setRailWidth(String railWidth) {
        this.railWidth = railWidth;
    }

    public String getErrorContentStatusCode() {
        return errorContentStatusCode;
    }

    public void setErrorContentStatusCode(String errorContentStatusCode) {
        this.errorContentStatusCode = errorContentStatusCode;
    }

    public String getErrorContentStatus() {
        return errorContentStatus;
    }

    public void setErrorContentStatus(String errorContentStatus) {
        this.errorContentStatus = errorContentStatus;
    }

    public boolean isRD() {
        return isRD;
    }

    public void setRD(boolean RD) {
        isRD = RD;
    }

    public String getRdBeginingWord() {
        return rdBeginingWord;
    }

    public void setRdBeginingWord(String rdBeginingWord) {
        this.rdBeginingWord = rdBeginingWord;
    }

    public String getNo_of_words() {
        return no_of_words;
    }

    public void setNo_of_words(String no_of_words) {
        this.no_of_words = no_of_words;
    }

    public String getWdBeginingWord() {
        return wdBeginingWord;
    }

    public void setWdBeginingWord(String wdBeginingWord) {
        this.wdBeginingWord = wdBeginingWord;
    }

    public String getWdRemainWord() {
        return wdRemainWord;
    }

    public void setWdRemainWord(String wdRemainWord) {
        this.wdRemainWord = wdRemainWord;
    }

    @Override
    public String toString() {
        return "UpperlinkCommandInfo{" +
            "operationStatusCode='" + operationStatusCode + '\'' +
            ", operationStatus='" + operationStatus + '\'' +
            ", statusInfo='" + statusInfo + '\'' +
            ", railWidth='" + railWidth + '\'' +
            ", errorContentStatusCode='" + errorContentStatusCode + '\'' +
            ", errorContentStatus='" + errorContentStatus + '\'' +
            ", isRD=" + isRD +
            ", rdBeginingWord='" + rdBeginingWord + '\'' +
            ", no_of_words='" + no_of_words + '\'' +
            ", wdBeginingWord='" + wdBeginingWord + '\'' +
            ", wdRemainWord='" + wdRemainWord + '\'' +
            '}';
    }

    @Override
    public void serialize(WriteBuffer writeBuffer) throws ParseException {

    }
}
