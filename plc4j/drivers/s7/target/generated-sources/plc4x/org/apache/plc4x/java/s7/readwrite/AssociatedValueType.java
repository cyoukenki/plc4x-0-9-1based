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

public class AssociatedValueType implements Message {


    // Properties.
    private final DataTransportErrorCode returnCode;
    private final DataTransportSize transportSize;
    private final int valueLength;
    private final short[] data;

    public AssociatedValueType(DataTransportErrorCode returnCode, DataTransportSize transportSize, int valueLength, short[] data) {
        this.returnCode = returnCode;
        this.transportSize = transportSize;
        this.valueLength = valueLength;
        this.data = data;
    }

    public DataTransportErrorCode getReturnCode() {
        return returnCode;
    }

    public DataTransportSize getTransportSize() {
        return transportSize;
    }

    public int getValueLength() {
        return valueLength;
    }

    public short[] getData() {
        return data;
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
        int lengthInBits = 0;
        AssociatedValueType _value  = this;

        // Simple field (returnCode)
        lengthInBits += 8;

        // Simple field (transportSize)
        lengthInBits += 8;

        // Manual Field (valueLength)
        lengthInBits += 2 * 8;

        // Array field
        if(data != null) {
            lengthInBits += 8 * data.length;
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<AssociatedValueType, AssociatedValueType> getMessageIO() {
        return new AssociatedValueTypeIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssociatedValueType)) {
            return false;
        }
        AssociatedValueType that = (AssociatedValueType) o;
        return
            (getReturnCode() == that.getReturnCode()) &&
            (getTransportSize() == that.getTransportSize()) &&
            (getValueLength() == that.getValueLength()) &&
            (getData() == that.getData()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getReturnCode(),
            getTransportSize(),
            getValueLength(),
            getData()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("returnCode", getReturnCode())
            .append("transportSize", getTransportSize())
            .append("valueLength", getValueLength())
            .append("data", getData())
            .toString();
    }

}