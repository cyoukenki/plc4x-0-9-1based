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
package org.apache.plc4x.java.secsgem.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.secsgem.readwrite.io.*;
import org.apache.plc4x.java.secsgem.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class S18F0Request2 extends SecsPacket implements Message {

    // Accessors for discriminator values.
    public Integer getStreamFunction() {
        return 0X1200;
    }

    // Properties.
    private final UnDefinedStreamDataStruct unDefinedStream;

    public S18F0Request2(int deviceID, short PType, short Stype, long systemBytes, UnDefinedStreamDataStruct unDefinedStream) {
        super(deviceID, PType, Stype, systemBytes);
        this.unDefinedStream = unDefinedStream;
    }

    public UnDefinedStreamDataStruct getUnDefinedStream() {
        return unDefinedStream;
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
        S18F0Request2 _value  = this;

        // Simple field (unDefinedStream)
        lengthInBits += unDefinedStream.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<SecsPacket, SecsPacket> getMessageIO() {
        return new SecsPacketIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof S18F0Request2)) {
            return false;
        }
        S18F0Request2 that = (S18F0Request2) o;
        return
            (getUnDefinedStream() == that.getUnDefinedStream()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getUnDefinedStream()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("unDefinedStream", getUnDefinedStream())
            .toString();
    }

}