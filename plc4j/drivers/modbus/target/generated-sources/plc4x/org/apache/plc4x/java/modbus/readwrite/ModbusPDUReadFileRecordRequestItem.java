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
package org.apache.plc4x.java.modbus.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.modbus.readwrite.io.*;
import org.apache.plc4x.java.modbus.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class ModbusPDUReadFileRecordRequestItem implements Message {


    // Properties.
    private final short referenceType;
    private final int fileNumber;
    private final int recordNumber;
    private final int recordLength;

    public ModbusPDUReadFileRecordRequestItem(short referenceType, int fileNumber, int recordNumber, int recordLength) {
        this.referenceType = referenceType;
        this.fileNumber = fileNumber;
        this.recordNumber = recordNumber;
        this.recordLength = recordLength;
    }

    public short getReferenceType() {
        return referenceType;
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public int getRecordLength() {
        return recordLength;
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
        ModbusPDUReadFileRecordRequestItem _value  = this;

        // Simple field (referenceType)
        lengthInBits += 8;

        // Simple field (fileNumber)
        lengthInBits += 16;

        // Simple field (recordNumber)
        lengthInBits += 16;

        // Simple field (recordLength)
        lengthInBits += 16;

        return lengthInBits;
    }

    @Override
    public MessageIO<ModbusPDUReadFileRecordRequestItem, ModbusPDUReadFileRecordRequestItem> getMessageIO() {
        return new ModbusPDUReadFileRecordRequestItemIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModbusPDUReadFileRecordRequestItem)) {
            return false;
        }
        ModbusPDUReadFileRecordRequestItem that = (ModbusPDUReadFileRecordRequestItem) o;
        return
            (getReferenceType() == that.getReferenceType()) &&
            (getFileNumber() == that.getFileNumber()) &&
            (getRecordNumber() == that.getRecordNumber()) &&
            (getRecordLength() == that.getRecordLength()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getReferenceType(),
            getFileNumber(),
            getRecordNumber(),
            getRecordLength()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("referenceType", getReferenceType())
            .append("fileNumber", getFileNumber())
            .append("recordNumber", getRecordNumber())
            .append("recordLength", getRecordLength())
            .toString();
    }

}