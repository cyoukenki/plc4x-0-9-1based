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

public class S1F13Request extends SecsPacket implements Message {

    // Accessors for discriminator values.
    public Integer getStreamFunction() {
        return 0x810d;
    }

    // Constant values.
    public static final short L = 0x01;

    // Properties.
    private final short len;
    private final DataStruct[] values;

    public S1F13Request(int deviceID, short PType, short Stype, long systemBytes, short len, DataStruct[] values) {
        super(deviceID, PType, Stype, systemBytes);
        this.len = len;
        this.values = values;
    }

    public short getLen() {
        return len;
    }

    public DataStruct[] getValues() {
        return values;
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
        S1F13Request _value  = this;

        // Const Field (L)
        lengthInBits += 8;

        // Simple field (len)
        lengthInBits += 8;

        // Array field
        if(values != null) {
            int i=0;
            for(DataStruct element : values) {
                boolean last = ++i >= values.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

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
        if (!(o instanceof S1F13Request)) {
            return false;
        }
        S1F13Request that = (S1F13Request) o;
        return
            (getLen() == that.getLen()) &&
            (getValues() == that.getValues()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getLen(),
            getValues()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("len", getLen())
            .append("values", getValues())
            .toString();
    }

}