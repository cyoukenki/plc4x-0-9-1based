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
package org.apache.plc4x.java.s7.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.s7.readwrite.io.*;
import org.apache.plc4x.java.s7.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class COTPPacketConnectionResponse extends COTPPacket implements Message {

    // Accessors for discriminator values.
    public Short getTpduCode() {
        return 0xD0;
    }

    // Properties.
    private final int destinationReference;
    private final int sourceReference;
    private final COTPProtocolClass protocolClass;

    public COTPPacketConnectionResponse(COTPParameter[] parameters, S7Message payload, int destinationReference, int sourceReference, COTPProtocolClass protocolClass) {
        super(parameters, payload);
        this.destinationReference = destinationReference;
        this.sourceReference = sourceReference;
        this.protocolClass = protocolClass;
    }

    public int getDestinationReference() {
        return destinationReference;
    }

    public int getSourceReference() {
        return sourceReference;
    }

    public COTPProtocolClass getProtocolClass() {
        return protocolClass;
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
        COTPPacketConnectionResponse _value  = this;

        // Simple field (destinationReference)
        lengthInBits += 16;

        // Simple field (sourceReference)
        lengthInBits += 16;

        // Simple field (protocolClass)
        lengthInBits += 8;

        return lengthInBits;
    }

    @Override
    public MessageIO<COTPPacket, COTPPacket> getMessageIO() {
        return new COTPPacketIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof COTPPacketConnectionResponse)) {
            return false;
        }
        COTPPacketConnectionResponse that = (COTPPacketConnectionResponse) o;
        return
            (getDestinationReference() == that.getDestinationReference()) &&
            (getSourceReference() == that.getSourceReference()) &&
            (getProtocolClass() == that.getProtocolClass()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getDestinationReference(),
            getSourceReference(),
            getProtocolClass()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("destinationReference", getDestinationReference())
            .append("sourceReference", getSourceReference())
            .append("protocolClass", getProtocolClass())
            .toString();
    }

}
