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

public class FindServersOnNetworkRequest extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "12192";
    }

    // Properties.
    private final ExtensionObjectDefinition requestHeader;
    private final long startingRecordId;
    private final long maxRecordsToReturn;
    private final int noOfServerCapabilityFilter;
    private final PascalString[] serverCapabilityFilter;

    public FindServersOnNetworkRequest(ExtensionObjectDefinition requestHeader, long startingRecordId, long maxRecordsToReturn, int noOfServerCapabilityFilter, PascalString[] serverCapabilityFilter) {
        this.requestHeader = requestHeader;
        this.startingRecordId = startingRecordId;
        this.maxRecordsToReturn = maxRecordsToReturn;
        this.noOfServerCapabilityFilter = noOfServerCapabilityFilter;
        this.serverCapabilityFilter = serverCapabilityFilter;
    }

    public ExtensionObjectDefinition getRequestHeader() {
        return requestHeader;
    }

    public long getStartingRecordId() {
        return startingRecordId;
    }

    public long getMaxRecordsToReturn() {
        return maxRecordsToReturn;
    }

    public int getNoOfServerCapabilityFilter() {
        return noOfServerCapabilityFilter;
    }

    public PascalString[] getServerCapabilityFilter() {
        return serverCapabilityFilter;
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
        FindServersOnNetworkRequest _value  = this;

        // Simple field (requestHeader)
        lengthInBits += requestHeader.getLengthInBits();

        // Simple field (startingRecordId)
        lengthInBits += 32;

        // Simple field (maxRecordsToReturn)
        lengthInBits += 32;

        // Simple field (noOfServerCapabilityFilter)
        lengthInBits += 32;

        // Array field
        if(serverCapabilityFilter != null) {
            int i=0;
            for(PascalString element : serverCapabilityFilter) {
                boolean last = ++i >= serverCapabilityFilter.length;
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
        if (!(o instanceof FindServersOnNetworkRequest)) {
            return false;
        }
        FindServersOnNetworkRequest that = (FindServersOnNetworkRequest) o;
        return
            (getRequestHeader() == that.getRequestHeader()) &&
            (getStartingRecordId() == that.getStartingRecordId()) &&
            (getMaxRecordsToReturn() == that.getMaxRecordsToReturn()) &&
            (getNoOfServerCapabilityFilter() == that.getNoOfServerCapabilityFilter()) &&
            (getServerCapabilityFilter() == that.getServerCapabilityFilter()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getRequestHeader(),
            getStartingRecordId(),
            getMaxRecordsToReturn(),
            getNoOfServerCapabilityFilter(),
            getServerCapabilityFilter()
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
            .append("startingRecordId", getStartingRecordId())
            .append("maxRecordsToReturn", getMaxRecordsToReturn())
            .append("noOfServerCapabilityFilter", getNoOfServerCapabilityFilter())
            .append("serverCapabilityFilter", getServerCapabilityFilter())
            .toString();
    }

}