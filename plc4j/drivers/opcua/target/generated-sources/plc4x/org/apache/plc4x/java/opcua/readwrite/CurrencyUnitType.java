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

public class CurrencyUnitType extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "23500";
    }

    // Properties.
    private final short numericCode;
    private final byte exponent;
    private final PascalString alphabeticCode;
    private final LocalizedText currency;

    public CurrencyUnitType(short numericCode, byte exponent, PascalString alphabeticCode, LocalizedText currency) {
        this.numericCode = numericCode;
        this.exponent = exponent;
        this.alphabeticCode = alphabeticCode;
        this.currency = currency;
    }

    public short getNumericCode() {
        return numericCode;
    }

    public byte getExponent() {
        return exponent;
    }

    public PascalString getAlphabeticCode() {
        return alphabeticCode;
    }

    public LocalizedText getCurrency() {
        return currency;
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
        CurrencyUnitType _value  = this;

        // Simple field (numericCode)
        lengthInBits += 16;

        // Simple field (exponent)
        lengthInBits += 8;

        // Simple field (alphabeticCode)
        lengthInBits += alphabeticCode.getLengthInBits();

        // Simple field (currency)
        lengthInBits += currency.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<ExtensionObjectDefinition, ExtensionObjectDefinition> getMessageIO() {
        return new ExtensionObjectDefinitionIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyUnitType)) {
            return false;
        }
        CurrencyUnitType that = (CurrencyUnitType) o;
        return
            (getNumericCode() == that.getNumericCode()) &&
            (getExponent() == that.getExponent()) &&
            (getAlphabeticCode() == that.getAlphabeticCode()) &&
            (getCurrency() == that.getCurrency()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getNumericCode(),
            getExponent(),
            getAlphabeticCode(),
            getCurrency()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("numericCode", getNumericCode())
            .append("exponent", getExponent())
            .append("alphabeticCode", getAlphabeticCode())
            .append("currency", getCurrency())
            .toString();
    }

}