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

public abstract class Variant implements Message {

    // Abstract accessors for discriminator values.
    public abstract Short getVariantType();

    // Properties.
    private final boolean arrayLengthSpecified;
    private final boolean arrayDimensionsSpecified;
    private final Integer noOfArrayDimensions;
    private final boolean[] arrayDimensions;

    public Variant(boolean arrayLengthSpecified, boolean arrayDimensionsSpecified, Integer noOfArrayDimensions, boolean[] arrayDimensions) {
        this.arrayLengthSpecified = arrayLengthSpecified;
        this.arrayDimensionsSpecified = arrayDimensionsSpecified;
        this.noOfArrayDimensions = noOfArrayDimensions;
        this.arrayDimensions = arrayDimensions;
    }

    public boolean getArrayLengthSpecified() {
        return arrayLengthSpecified;
    }

    public boolean getArrayDimensionsSpecified() {
        return arrayDimensionsSpecified;
    }

    public Integer getNoOfArrayDimensions() {
        return noOfArrayDimensions;
    }

    public boolean[] getArrayDimensions() {
        return arrayDimensions;
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
        Variant _value  = this;

        // Simple field (arrayLengthSpecified)
        lengthInBits += 1;

        // Simple field (arrayDimensionsSpecified)
        lengthInBits += 1;

        // Discriminator Field (VariantType)
        lengthInBits += 6;

        // Length of sub-type elements will be added by sub-type...

        // Optional Field (noOfArrayDimensions)
        if(noOfArrayDimensions != null) {
            lengthInBits += 32;
        }

        // Array field
        if(arrayDimensions != null) {
            lengthInBits += 1 * arrayDimensions.length;
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<Variant, Variant> getMessageIO() {
        return new VariantIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Variant)) {
            return false;
        }
        Variant that = (Variant) o;
        return
            (getArrayLengthSpecified() == that.getArrayLengthSpecified()) &&
            (getArrayDimensionsSpecified() == that.getArrayDimensionsSpecified()) &&
            (getNoOfArrayDimensions() == that.getNoOfArrayDimensions()) &&
            (getArrayDimensions() == that.getArrayDimensions()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getArrayLengthSpecified(),
            getArrayDimensionsSpecified(),
            getNoOfArrayDimensions(),
            getArrayDimensions()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("arrayLengthSpecified", getArrayLengthSpecified())
            .append("arrayDimensionsSpecified", getArrayDimensionsSpecified())
            .append("noOfArrayDimensions", getNoOfArrayDimensions())
            .append("arrayDimensions", getArrayDimensions())
            .toString();
    }

}