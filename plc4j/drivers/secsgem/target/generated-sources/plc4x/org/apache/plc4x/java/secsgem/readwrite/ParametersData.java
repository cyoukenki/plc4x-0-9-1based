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

public class ParametersData implements Message {


    // Constant values.
    public static final int L2 = 0x0102;

    // Properties.
    private final DataStruct cpname;
    private final DataStruct cpval;

    public ParametersData(DataStruct cpname, DataStruct cpval) {
        this.cpname = cpname;
        this.cpval = cpval;
    }

    public DataStruct getCpname() {
        return cpname;
    }

    public DataStruct getCpval() {
        return cpval;
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
        ParametersData _value  = this;

        // Const Field (L2)
        lengthInBits += 16;

        // Simple field (cpname)
        lengthInBits += cpname.getLengthInBits();

        // Simple field (cpval)
        lengthInBits += cpval.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<ParametersData, ParametersData> getMessageIO() {
        return new ParametersDataIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParametersData)) {
            return false;
        }
        ParametersData that = (ParametersData) o;
        return
            (getCpname() == that.getCpname()) &&
            (getCpval() == that.getCpval()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getCpname(),
            getCpval()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("cpname", getCpname())
            .append("cpval", getCpval())
            .toString();
    }

}