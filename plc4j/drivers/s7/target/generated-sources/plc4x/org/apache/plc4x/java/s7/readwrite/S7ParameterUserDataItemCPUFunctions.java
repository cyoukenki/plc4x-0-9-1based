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

public class S7ParameterUserDataItemCPUFunctions extends S7ParameterUserDataItem implements Message {

    // Accessors for discriminator values.
    public Short getItemType() {
        return 0x12;
    }

    // Properties.
    private final short method;
    private final byte cpuFunctionType;
    private final byte cpuFunctionGroup;
    private final short cpuSubfunction;
    private final short sequenceNumber;
    private final Short dataUnitReferenceNumber;
    private final Short lastDataUnit;
    private final Integer errorCode;

    public S7ParameterUserDataItemCPUFunctions(short method, byte cpuFunctionType, byte cpuFunctionGroup, short cpuSubfunction, short sequenceNumber, Short dataUnitReferenceNumber, Short lastDataUnit, Integer errorCode) {
        this.method = method;
        this.cpuFunctionType = cpuFunctionType;
        this.cpuFunctionGroup = cpuFunctionGroup;
        this.cpuSubfunction = cpuSubfunction;
        this.sequenceNumber = sequenceNumber;
        this.dataUnitReferenceNumber = dataUnitReferenceNumber;
        this.lastDataUnit = lastDataUnit;
        this.errorCode = errorCode;
    }

    public short getMethod() {
        return method;
    }

    public byte getCpuFunctionType() {
        return cpuFunctionType;
    }

    public byte getCpuFunctionGroup() {
        return cpuFunctionGroup;
    }

    public short getCpuSubfunction() {
        return cpuSubfunction;
    }

    public short getSequenceNumber() {
        return sequenceNumber;
    }

    public Short getDataUnitReferenceNumber() {
        return dataUnitReferenceNumber;
    }

    public Short getLastDataUnit() {
        return lastDataUnit;
    }

    public Integer getErrorCode() {
        return errorCode;
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
        S7ParameterUserDataItemCPUFunctions _value  = this;

        // Implicit Field (itemLength)
        lengthInBits += 8;
        //short itemLength = (short) ((_value.getLengthInBytes()) - (2));

        // Simple field (method)
        lengthInBits += 8;

        // Simple field (cpuFunctionType)
        lengthInBits += 4;

        // Simple field (cpuFunctionGroup)
        lengthInBits += 4;

        // Simple field (cpuSubfunction)
        lengthInBits += 8;

        // Simple field (sequenceNumber)
        lengthInBits += 8;

        // Optional Field (dataUnitReferenceNumber)
        if(dataUnitReferenceNumber != null) {
            lengthInBits += 8;
        }

        // Optional Field (lastDataUnit)
        if(lastDataUnit != null) {
            lengthInBits += 8;
        }

        // Optional Field (errorCode)
        if(errorCode != null) {
            lengthInBits += 16;
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<S7ParameterUserDataItem, S7ParameterUserDataItem> getMessageIO() {
        return new S7ParameterUserDataItemIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof S7ParameterUserDataItemCPUFunctions)) {
            return false;
        }
        S7ParameterUserDataItemCPUFunctions that = (S7ParameterUserDataItemCPUFunctions) o;
        return
            (getMethod() == that.getMethod()) &&
            (getCpuFunctionType() == that.getCpuFunctionType()) &&
            (getCpuFunctionGroup() == that.getCpuFunctionGroup()) &&
            (getCpuSubfunction() == that.getCpuSubfunction()) &&
            (getSequenceNumber() == that.getSequenceNumber()) &&
            (getDataUnitReferenceNumber() == that.getDataUnitReferenceNumber()) &&
            (getLastDataUnit() == that.getLastDataUnit()) &&
            (getErrorCode() == that.getErrorCode()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getMethod(),
            getCpuFunctionType(),
            getCpuFunctionGroup(),
            getCpuSubfunction(),
            getSequenceNumber(),
            getDataUnitReferenceNumber(),
            getLastDataUnit(),
            getErrorCode()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("method", getMethod())
            .append("cpuFunctionType", getCpuFunctionType())
            .append("cpuFunctionGroup", getCpuFunctionGroup())
            .append("cpuSubfunction", getCpuSubfunction())
            .append("sequenceNumber", getSequenceNumber())
            .append("dataUnitReferenceNumber", getDataUnitReferenceNumber())
            .append("lastDataUnit", getLastDataUnit())
            .append("errorCode", getErrorCode())
            .toString();
    }

}