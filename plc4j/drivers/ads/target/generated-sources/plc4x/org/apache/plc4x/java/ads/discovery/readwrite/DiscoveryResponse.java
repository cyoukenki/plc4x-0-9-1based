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

public class DiscoveryResponse extends AdsDiscovery implements Message {

    // Accessors for discriminator values.

    // Properties.
    private final AmsNetId amsNetId;
    private final AmsMagicString name;

    public DiscoveryResponse(Operation operation, Direction direction, AmsNetId amsNetId, AmsMagicString name) {
        super(operation, direction);
        this.amsNetId = amsNetId;
        this.name = name;
    }

    public AmsNetId getAmsNetId() {
        return amsNetId;
    }

    public AmsMagicString getName() {
        return name;
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
        DiscoveryResponse _value  = this;

        // Simple field (amsNetId)
        lengthInBits += amsNetId.getLengthInBits();

        // Reserved Field (reserved)
        lengthInBits += 16;

        // Reserved Field (reserved)
        lengthInBits += 16;

        // Reserved Field (reserved)
        lengthInBits += 24;

        // Simple field (name)
        lengthInBits += name.getLengthInBits();

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
        if (!(o instanceof DiscoveryResponse)) {
            return false;
        }
        DiscoveryResponse that = (DiscoveryResponse) o;
        return
            (getAmsNetId() == that.getAmsNetId()) &&
            (getName() == that.getName()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getAmsNetId(),
            getName()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("amsNetId", getAmsNetId())
            .append("name", getName())
            .toString();
    }

}
