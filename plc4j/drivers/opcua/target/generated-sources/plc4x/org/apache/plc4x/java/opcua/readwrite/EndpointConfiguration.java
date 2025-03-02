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

public class EndpointConfiguration extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "333";
    }

    // Properties.
    private final int operationTimeout;
    private final boolean useBinaryEncoding;
    private final int maxStringLength;
    private final int maxByteStringLength;
    private final int maxArrayLength;
    private final int maxMessageSize;
    private final int maxBufferSize;
    private final int channelLifetime;
    private final int securityTokenLifetime;

    public EndpointConfiguration(int operationTimeout, boolean useBinaryEncoding, int maxStringLength, int maxByteStringLength, int maxArrayLength, int maxMessageSize, int maxBufferSize, int channelLifetime, int securityTokenLifetime) {
        this.operationTimeout = operationTimeout;
        this.useBinaryEncoding = useBinaryEncoding;
        this.maxStringLength = maxStringLength;
        this.maxByteStringLength = maxByteStringLength;
        this.maxArrayLength = maxArrayLength;
        this.maxMessageSize = maxMessageSize;
        this.maxBufferSize = maxBufferSize;
        this.channelLifetime = channelLifetime;
        this.securityTokenLifetime = securityTokenLifetime;
    }

    public int getOperationTimeout() {
        return operationTimeout;
    }

    public boolean getUseBinaryEncoding() {
        return useBinaryEncoding;
    }

    public int getMaxStringLength() {
        return maxStringLength;
    }

    public int getMaxByteStringLength() {
        return maxByteStringLength;
    }

    public int getMaxArrayLength() {
        return maxArrayLength;
    }

    public int getMaxMessageSize() {
        return maxMessageSize;
    }

    public int getMaxBufferSize() {
        return maxBufferSize;
    }

    public int getChannelLifetime() {
        return channelLifetime;
    }

    public int getSecurityTokenLifetime() {
        return securityTokenLifetime;
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
        EndpointConfiguration _value  = this;

        // Simple field (operationTimeout)
        lengthInBits += 32;

        // Reserved Field (reserved)
        lengthInBits += 7;

        // Simple field (useBinaryEncoding)
        lengthInBits += 1;

        // Simple field (maxStringLength)
        lengthInBits += 32;

        // Simple field (maxByteStringLength)
        lengthInBits += 32;

        // Simple field (maxArrayLength)
        lengthInBits += 32;

        // Simple field (maxMessageSize)
        lengthInBits += 32;

        // Simple field (maxBufferSize)
        lengthInBits += 32;

        // Simple field (channelLifetime)
        lengthInBits += 32;

        // Simple field (securityTokenLifetime)
        lengthInBits += 32;

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
        if (!(o instanceof EndpointConfiguration)) {
            return false;
        }
        EndpointConfiguration that = (EndpointConfiguration) o;
        return
            (getOperationTimeout() == that.getOperationTimeout()) &&
            (getUseBinaryEncoding() == that.getUseBinaryEncoding()) &&
            (getMaxStringLength() == that.getMaxStringLength()) &&
            (getMaxByteStringLength() == that.getMaxByteStringLength()) &&
            (getMaxArrayLength() == that.getMaxArrayLength()) &&
            (getMaxMessageSize() == that.getMaxMessageSize()) &&
            (getMaxBufferSize() == that.getMaxBufferSize()) &&
            (getChannelLifetime() == that.getChannelLifetime()) &&
            (getSecurityTokenLifetime() == that.getSecurityTokenLifetime()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getOperationTimeout(),
            getUseBinaryEncoding(),
            getMaxStringLength(),
            getMaxByteStringLength(),
            getMaxArrayLength(),
            getMaxMessageSize(),
            getMaxBufferSize(),
            getChannelLifetime(),
            getSecurityTokenLifetime()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("operationTimeout", getOperationTimeout())
            .append("useBinaryEncoding", getUseBinaryEncoding())
            .append("maxStringLength", getMaxStringLength())
            .append("maxByteStringLength", getMaxByteStringLength())
            .append("maxArrayLength", getMaxArrayLength())
            .append("maxMessageSize", getMaxMessageSize())
            .append("maxBufferSize", getMaxBufferSize())
            .append("channelLifetime", getChannelLifetime())
            .append("securityTokenLifetime", getSecurityTokenLifetime())
            .toString();
    }

}
