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
package org.apache.plc4x.java.opcua.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.io.*;
import org.apache.plc4x.java.opcua.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class HistoryReadRequest extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "664";
    }

    // Properties.
    private final ExtensionObjectDefinition requestHeader;
    private final ExtensionObject historyReadDetails;
    private final TimestampsToReturn timestampsToReturn;
    private final boolean releaseContinuationPoints;
    private final int noOfNodesToRead;
    private final ExtensionObjectDefinition[] nodesToRead;

    public HistoryReadRequest(ExtensionObjectDefinition requestHeader, ExtensionObject historyReadDetails, TimestampsToReturn timestampsToReturn, boolean releaseContinuationPoints, int noOfNodesToRead, ExtensionObjectDefinition[] nodesToRead) {
        this.requestHeader = requestHeader;
        this.historyReadDetails = historyReadDetails;
        this.timestampsToReturn = timestampsToReturn;
        this.releaseContinuationPoints = releaseContinuationPoints;
        this.noOfNodesToRead = noOfNodesToRead;
        this.nodesToRead = nodesToRead;
    }

    public ExtensionObjectDefinition getRequestHeader() {
        return requestHeader;
    }

    public ExtensionObject getHistoryReadDetails() {
        return historyReadDetails;
    }

    public TimestampsToReturn getTimestampsToReturn() {
        return timestampsToReturn;
    }

    public boolean getReleaseContinuationPoints() {
        return releaseContinuationPoints;
    }

    public int getNoOfNodesToRead() {
        return noOfNodesToRead;
    }

    public ExtensionObjectDefinition[] getNodesToRead() {
        return nodesToRead;
    }

    @Override
    public int getLengthInBytes() {
        return getLengthInBits() / 8;
    }

    @Override
    public int getLengthInBits() {
        return getLengthInBitsConditional(false);
    }

    public int getLengthInBitsConditional(boolean lastItem) {
        int lengthInBits = super.getLengthInBitsConditional(lastItem);
        HistoryReadRequest _value  = this;

        // Simple field (requestHeader)
        lengthInBits += requestHeader.getLengthInBits();

        // Simple field (historyReadDetails)
        lengthInBits += historyReadDetails.getLengthInBits();

        // Simple field (timestampsToReturn)
        lengthInBits += 32;

        // Reserved Field (reserved)
        lengthInBits += 7;

        // Simple field (releaseContinuationPoints)
        lengthInBits += 1;

        // Simple field (noOfNodesToRead)
        lengthInBits += 32;

        // Array field
        if(nodesToRead != null) {
            int i=0;
            for(ExtensionObjectDefinition element : nodesToRead) {
                boolean last = ++i >= nodesToRead.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<ExtensionObjectDefinition, ExtensionObjectDefinition> getMessageIO() {
        return new ExtensionObjectDefinitionIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoryReadRequest)) {
            return false;
        }
        HistoryReadRequest that = (HistoryReadRequest) o;
        return
            (getRequestHeader() == that.getRequestHeader()) &&
            (getHistoryReadDetails() == that.getHistoryReadDetails()) &&
            (getTimestampsToReturn() == that.getTimestampsToReturn()) &&
            (getReleaseContinuationPoints() == that.getReleaseContinuationPoints()) &&
            (getNoOfNodesToRead() == that.getNoOfNodesToRead()) &&
            (getNodesToRead() == that.getNodesToRead()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getRequestHeader(),
            getHistoryReadDetails(),
            getTimestampsToReturn(),
            getReleaseContinuationPoints(),
            getNoOfNodesToRead(),
            getNodesToRead()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("requestHeader", getRequestHeader())
            .append("historyReadDetails", getHistoryReadDetails())
            .append("timestampsToReturn", getTimestampsToReturn())
            .append("releaseContinuationPoints", getReleaseContinuationPoints())
            .append("noOfNodesToRead", getNoOfNodesToRead())
            .append("nodesToRead", getNodesToRead())
            .toString();
    }

}