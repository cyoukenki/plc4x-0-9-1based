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

public class DataStruct1 implements Message {


    // Properties.
    private final SecsDataTypeCode dataType;
    private final short elementNb;
    private final DataStruct[] data;

    public DataStruct1(SecsDataTypeCode dataType, short elementNb, DataStruct[] data) {
        this.dataType = dataType;
        this.elementNb = elementNb;
        this.data = data;
    }

    public SecsDataTypeCode getDataType() {
        return dataType;
    }

    public short getElementNb() {
        return elementNb;
    }

    public DataStruct[] getData() {
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
        DataStruct1 _value  = this;

        // Enum Field (dataType)
        lengthInBits += 8;

        // Simple field (elementNb)
        lengthInBits += 8;

        // Array field
        if(data != null) {
            int i=0;
            for(DataStruct element : data) {
                boolean last = ++i >= data.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<DataStruct1, DataStruct1> getMessageIO() {
        return new DataStruct1IO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataStruct1)) {
            return false;
        }
        DataStruct1 that = (DataStruct1) o;
        return
            (getDataType() == that.getDataType()) &&
            (getElementNb() == that.getElementNb()) &&
            (getData() == that.getData()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getDataType(),
            getElementNb(),
            getData()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("dataType", getDataType())
            .append("elementNb", getElementNb())
            .append("data", getData())
            .toString();
    }

}
