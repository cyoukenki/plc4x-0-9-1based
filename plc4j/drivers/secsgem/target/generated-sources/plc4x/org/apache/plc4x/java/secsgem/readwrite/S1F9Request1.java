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

public class S1F9Request1 extends SecsPacket implements Message {

    // Accessors for discriminator values.
    public Integer getStreamFunction() {
        return 0X8109;
    }

    // Properties.
    private final UnDefinedFunctionDataStruct unDefinedFunction;

    public S1F9Request1(int deviceID, short PType, short Stype, long systemBytes, UnDefinedFunctionDataStruct unDefinedFunction) {
        super(deviceID, PType, Stype, systemBytes);
        this.unDefinedFunction = unDefinedFunction;
    }

    public UnDefinedFunctionDataStruct getUnDefinedFunction() {
        return unDefinedFunction;
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
        S1F9Request1 _value  = this;

        // Simple field (unDefinedFunction)
        lengthInBits += unDefinedFunction.getLengthInBits();

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
        if (!(o instanceof S1F9Request1)) {
            return false;
        }
        S1F9Request1 that = (S1F9Request1) o;
        return
            (getUnDefinedFunction() == that.getUnDefinedFunction()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getUnDefinedFunction()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("unDefinedFunction", getUnDefinedFunction())
            .toString();
    }

}
