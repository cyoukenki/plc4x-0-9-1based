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

public class OpcuaCloseRequest extends MessagePDU implements Message {

    // Accessors for discriminator values.
    public String getMessageType() {
        return "CLO";
    }
    public Boolean getResponse() {
        return false;
    }

    // Properties.
    private final String chunk;
    private final int secureChannelId;
    private final int secureTokenId;
    private final int sequenceNumber;
    private final int requestId;
    private final ExtensionObject message;

    public OpcuaCloseRequest(String chunk, int secureChannelId, int secureTokenId, int sequenceNumber, int requestId, ExtensionObject message) {
        this.chunk = chunk;
        this.secureChannelId = secureChannelId;
        this.secureTokenId = secureTokenId;
        this.sequenceNumber = sequenceNumber;
        this.requestId = requestId;
        this.message = message;
    }

    public String getChunk() {
        return chunk;
    }

    public int getSecureChannelId() {
        return secureChannelId;
    }

    public int getSecureTokenId() {
        return secureTokenId;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public int getRequestId() {
        return requestId;
    }

    public ExtensionObject getMessage() {
        return message;
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
        OpcuaCloseRequest _value  = this;

        // Simple field (chunk)
        lengthInBits += 8;

        // Implicit Field (messageSize)
        lengthInBits += 32;
        //int messageSize = (int) (_value.getLengthInBytes());

        // Simple field (secureChannelId)
        lengthInBits += 32;

        // Simple field (secureTokenId)
        lengthInBits += 32;

        // Simple field (sequenceNumber)
        lengthInBits += 32;

        // Simple field (requestId)
        lengthInBits += 32;

        // Simple field (message)
        lengthInBits += message.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<MessagePDU, MessagePDU> getMessageIO() {
        return new MessagePDUIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcuaCloseRequest)) {
            return false;
        }
        OpcuaCloseRequest that = (OpcuaCloseRequest) o;
        return
            (getChunk() == that.getChunk()) &&
            (getSecureChannelId() == that.getSecureChannelId()) &&
            (getSecureTokenId() == that.getSecureTokenId()) &&
            (getSequenceNumber() == that.getSequenceNumber()) &&
            (getRequestId() == that.getRequestId()) &&
            (getMessage() == that.getMessage()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getChunk(),
            getSecureChannelId(),
            getSecureTokenId(),
            getSequenceNumber(),
            getRequestId(),
            getMessage()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("chunk", getChunk())
            .append("secureChannelId", getSecureChannelId())
            .append("secureTokenId", getSecureTokenId())
            .append("sequenceNumber", getSequenceNumber())
            .append("requestId", getRequestId())
            .append("message", getMessage())
            .toString();
    }

}