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
package org.apache.plc4x.java.opcua.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.io.*;
import org.apache.plc4x.java.opcua.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class PascalString implements Message {


    // Properties.
    private final String stringValue;

    public PascalString(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getStringLength() {
        return (int) ((((stringValue.length()) == (-(1))) ? 0 : stringValue.length()));
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
        PascalString _value  = this;

        // Implicit Field (sLength)
        lengthInBits += 32;
        //int sLength = (int) ((((_value.getStringValue().length()) == (0)) ? -(1) : _value.getStringValue().length()));

        // Simple field (stringValue)
        lengthInBits += ((((((_value.getStringValue().length()) == (0)) ? -(1) : _value.getStringValue().length())) == (-(1))) ? 0 : ((((_value.getStringValue().length()) == (0)) ? -(1) : _value.getStringValue().length())) * (8));

        // A virtual field doesn't have any in- or output.

        return lengthInBits;
    }

    @Override
    public MessageIO<PascalString, PascalString> getMessageIO() {
        return new PascalStringIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PascalString)) {
            return false;
        }
        PascalString that = (PascalString) o;
        return
            (getStringValue() == that.getStringValue()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getStringValue()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("stringValue", getStringValue())
            .append("stringLength", getStringLength())
            .toString();
    }

}