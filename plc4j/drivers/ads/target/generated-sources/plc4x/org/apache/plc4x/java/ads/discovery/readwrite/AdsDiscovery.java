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
package org.apache.plc4x.java.ads.discovery.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.discovery.readwrite.io.*;
import org.apache.plc4x.java.ads.discovery.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public abstract class AdsDiscovery implements Message {

    // Abstract accessors for discriminator values.

    // Constant values.
    public static final long HEADER = 0x03661471L;

    // Properties.
    private final Operation operation;
    private final Direction direction;

    public AdsDiscovery(Operation operation, Direction direction) {
        this.operation = operation;
        this.direction = direction;
    }

    public Operation getOperation() {
        return operation;
    }

    public Direction getDirection() {
        return direction;
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
        AdsDiscovery _value  = this;

        // Const Field (header)
        lengthInBits += 32;

        // Reserved Field (reserved)
        lengthInBits += 32;

        // Enum Field (operation)
        lengthInBits += 8;

        // Reserved Field (reserved)
        lengthInBits += 16;

        // Enum Field (direction)
        lengthInBits += 8;

        // Length of sub-type elements will be added by sub-type...

        return lengthInBits;
    }

    @Override
    public MessageIO<AdsDiscovery, AdsDiscovery> getMessageIO() {
        return new AdsDiscoveryIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdsDiscovery)) {
            return false;
        }
        AdsDiscovery that = (AdsDiscovery) o;
        return
            (getOperation() == that.getOperation()) &&
            (getDirection() == that.getDirection()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getOperation(),
            getDirection()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("operation", getOperation())
            .append("direction", getDirection())
            .toString();
    }

}