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

public abstract class DataStruct3 implements Message {

    // Abstract accessors for discriminator values.
    public abstract Short getNLB();
    public abstract Short getDataType();

    // Properties.
    private final short symbolType1;

    public DataStruct3(short symbolType1) {
        this.symbolType1 = symbolType1;
    }

    public abstract long getIdentifier();

    public short getSymbolType1() {
        return symbolType1;
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
        DataStruct3 _value  = this;

        // Simple field (symbolType1)
        lengthInBits += 8;

        // A virtual field doesn't have any in- or output.

        // A virtual field doesn't have any in- or output.

        // Length of sub-type elements will be added by sub-type...

        return lengthInBits;
    }

    @Override
    public MessageIO<DataStruct3, DataStruct3> getMessageIO() {
        return new DataStruct3IO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataStruct3)) {
            return false;
        }
        DataStruct3 that = (DataStruct3) o;
        return
            (getSymbolType1() == that.getSymbolType1()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getSymbolType1()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("symbolType1", getSymbolType1())
            .append("NLB", getNLB())
            .append("dataType", getDataType())
            .toString();
    }

}