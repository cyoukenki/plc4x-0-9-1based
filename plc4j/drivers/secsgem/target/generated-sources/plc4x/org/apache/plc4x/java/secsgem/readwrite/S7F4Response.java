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

public class S7F4Response extends SecsPacket implements Message {

    // Accessors for discriminator values.
    public Integer getStreamFunction() {
        return 0x0704;
    }

    // Properties.
    private final DataStruct ACKC7;

    public S7F4Response(int deviceID, short PType, short Stype, long systemBytes, DataStruct ACKC7) {
        super(deviceID, PType, Stype, systemBytes);
        this.ACKC7 = ACKC7;
    }

    public DataStruct getACKC7() {
        return ACKC7;
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
        S7F4Response _value  = this;

        // Simple field (ACKC7)
        lengthInBits += ACKC7.getLengthInBits();

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
        if (!(o instanceof S7F4Response)) {
            return false;
        }
        S7F4Response that = (S7F4Response) o;
        return
            (getACKC7() == that.getACKC7()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getACKC7()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("ACKC7", getACKC7())
            .toString();
    }

}
