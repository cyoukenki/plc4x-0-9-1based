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
package org.apache.plc4x.java.mc.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.mc.readwrite.io.*;
import org.apache.plc4x.java.mc.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class DataStruct14020000doubleword implements Message {


    // Properties.
    private final Devicecode devicecoderadom;
    private final BigInteger deviceNoradom;
    private final long doublewordvalue14020001;

    public DataStruct14020000doubleword(Devicecode devicecoderadom, BigInteger deviceNoradom, long doublewordvalue14020001) {
        this.devicecoderadom = devicecoderadom;
        this.deviceNoradom = deviceNoradom;
        this.doublewordvalue14020001 = doublewordvalue14020001;
    }

    public Devicecode getDevicecoderadom() {
        return devicecoderadom;
    }

    public BigInteger getDeviceNoradom() {
        return deviceNoradom;
    }

    public long getDoublewordvalue14020001() {
        return doublewordvalue14020001;
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
        DataStruct14020000doubleword _value  = this;

        // Enum Field (devicecoderadom)
        lengthInBits += 16;

        // Simple field (deviceNoradom)
        lengthInBits += 48;

        // Simple field (doublewordvalue14020001)
        lengthInBits += 32;

        return lengthInBits;
    }

    @Override
    public MessageIO<DataStruct14020000doubleword, DataStruct14020000doubleword> getMessageIO() {
        return new DataStruct14020000doublewordIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataStruct14020000doubleword)) {
            return false;
        }
        DataStruct14020000doubleword that = (DataStruct14020000doubleword) o;
        return
            (getDevicecoderadom() == that.getDevicecoderadom()) &&
            (getDeviceNoradom() == that.getDeviceNoradom()) &&
            (getDoublewordvalue14020001() == that.getDoublewordvalue14020001()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getDevicecoderadom(),
            getDeviceNoradom(),
            getDoublewordvalue14020001()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("devicecoderadom", getDevicecoderadom())
            .append("deviceNoradom", getDeviceNoradom())
            .append("doublewordvalue14020001", getDoublewordvalue14020001())
            .toString();
    }

}
