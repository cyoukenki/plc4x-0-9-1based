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
package org.apache.plc4x.java.profinet.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.profinet.readwrite.io.*;
import org.apache.plc4x.java.profinet.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class Ethernet_FramePayload_VirtualLan extends Ethernet_FramePayload implements Message {

    // Accessors for discriminator values.
    public Integer getPacketType() {
        return 0x8100;
    }

    // Properties.
    private final VirtualLanPriority priority;
    private final boolean ineligible;
    private final int id;
    private final Ethernet_FramePayload payload;

    public Ethernet_FramePayload_VirtualLan(VirtualLanPriority priority, boolean ineligible, int id, Ethernet_FramePayload payload) {
        this.priority = priority;
        this.ineligible = ineligible;
        this.id = id;
        this.payload = payload;
    }

    public VirtualLanPriority getPriority() {
        return priority;
    }

    public boolean getIneligible() {
        return ineligible;
    }

    public int getId() {
        return id;
    }

    public Ethernet_FramePayload getPayload() {
        return payload;
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
        Ethernet_FramePayload_VirtualLan _value  = this;

        // Simple field (priority)
        lengthInBits += 3;

        // Simple field (ineligible)
        lengthInBits += 1;

        // Simple field (id)
        lengthInBits += 12;

        // Simple field (payload)
        lengthInBits += payload.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<Ethernet_FramePayload, Ethernet_FramePayload> getMessageIO() {
        return new Ethernet_FramePayloadIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ethernet_FramePayload_VirtualLan)) {
            return false;
        }
        Ethernet_FramePayload_VirtualLan that = (Ethernet_FramePayload_VirtualLan) o;
        return
            (getPriority() == that.getPriority()) &&
            (getIneligible() == that.getIneligible()) &&
            (getId() == that.getId()) &&
            (getPayload() == that.getPayload()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getPriority(),
            getIneligible(),
            getId(),
            getPayload()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("priority", getPriority())
            .append("ineligible", getIneligible())
            .append("id", getId())
            .append("payload", getPayload())
            .toString();
    }

}
