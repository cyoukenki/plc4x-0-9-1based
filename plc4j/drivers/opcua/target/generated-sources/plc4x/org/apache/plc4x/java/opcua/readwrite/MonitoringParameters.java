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

public class MonitoringParameters extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "742";
    }

    // Properties.
    private final long clientHandle;
    private final double samplingInterval;
    private final ExtensionObject filter;
    private final long queueSize;
    private final boolean discardOldest;

    public MonitoringParameters(long clientHandle, double samplingInterval, ExtensionObject filter, long queueSize, boolean discardOldest) {
        this.clientHandle = clientHandle;
        this.samplingInterval = samplingInterval;
        this.filter = filter;
        this.queueSize = queueSize;
        this.discardOldest = discardOldest;
    }

    public long getClientHandle() {
        return clientHandle;
    }

    public double getSamplingInterval() {
        return samplingInterval;
    }

    public ExtensionObject getFilter() {
        return filter;
    }

    public long getQueueSize() {
        return queueSize;
    }

    public boolean getDiscardOldest() {
        return discardOldest;
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
        MonitoringParameters _value  = this;

        // Simple field (clientHandle)
        lengthInBits += 32;

        // Simple field (samplingInterval)
        lengthInBits += 64;

        // Simple field (filter)
        lengthInBits += filter.getLengthInBits();

        // Simple field (queueSize)
        lengthInBits += 32;

        // Reserved Field (reserved)
        lengthInBits += 7;

        // Simple field (discardOldest)
        lengthInBits += 1;

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
        if (!(o instanceof MonitoringParameters)) {
            return false;
        }
        MonitoringParameters that = (MonitoringParameters) o;
        return
            (getClientHandle() == that.getClientHandle()) &&
            (getSamplingInterval() == that.getSamplingInterval()) &&
            (getFilter() == that.getFilter()) &&
            (getQueueSize() == that.getQueueSize()) &&
            (getDiscardOldest() == that.getDiscardOldest()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getClientHandle(),
            getSamplingInterval(),
            getFilter(),
            getQueueSize(),
            getDiscardOldest()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("clientHandle", getClientHandle())
            .append("samplingInterval", getSamplingInterval())
            .append("filter", getFilter())
            .append("queueSize", getQueueSize())
            .append("discardOldest", getDiscardOldest())
            .toString();
    }

}