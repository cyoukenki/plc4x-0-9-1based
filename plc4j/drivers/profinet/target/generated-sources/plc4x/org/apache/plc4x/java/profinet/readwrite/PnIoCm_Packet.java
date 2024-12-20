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

public abstract class PnIoCm_Packet implements Message {

    // Abstract accessors for discriminator values.
    public abstract DceRpc_PacketType getPacketType();

    // Properties.
    private final long argsLength;
    private final long arrayMaximumCount;
    private final long arrayOffset;
    private final long arrayActualCount;
    private final PnIoCm_Block[] blocks;

    public PnIoCm_Packet(long argsLength, long arrayMaximumCount, long arrayOffset, long arrayActualCount, PnIoCm_Block[] blocks) {
        this.argsLength = argsLength;
        this.arrayMaximumCount = arrayMaximumCount;
        this.arrayOffset = arrayOffset;
        this.arrayActualCount = arrayActualCount;
        this.blocks = blocks;
    }

    public long getArgsLength() {
        return argsLength;
    }

    public long getArrayMaximumCount() {
        return arrayMaximumCount;
    }

    public long getArrayOffset() {
        return arrayOffset;
    }

    public long getArrayActualCount() {
        return arrayActualCount;
    }

    public PnIoCm_Block[] getBlocks() {
        return blocks;
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
        PnIoCm_Packet _value  = this;

        // Length of sub-type elements will be added by sub-type...

        // Simple field (argsLength)
        lengthInBits += 32;

        // Simple field (arrayMaximumCount)
        lengthInBits += 32;

        // Simple field (arrayOffset)
        lengthInBits += 32;

        // Simple field (arrayActualCount)
        lengthInBits += 32;

        // Array field
        if(blocks != null) {
            for(Message element : blocks) {
                lengthInBits += element.getLengthInBits();
            }
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<PnIoCm_Packet, PnIoCm_Packet> getMessageIO() {
        return new PnIoCm_PacketIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PnIoCm_Packet)) {
            return false;
        }
        PnIoCm_Packet that = (PnIoCm_Packet) o;
        return
            (getArgsLength() == that.getArgsLength()) &&
            (getArrayMaximumCount() == that.getArrayMaximumCount()) &&
            (getArrayOffset() == that.getArrayOffset()) &&
            (getArrayActualCount() == that.getArrayActualCount()) &&
            (getBlocks() == that.getBlocks()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getArgsLength(),
            getArrayMaximumCount(),
            getArrayOffset(),
            getArrayActualCount(),
            getBlocks()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("argsLength", getArgsLength())
            .append("arrayMaximumCount", getArrayMaximumCount())
            .append("arrayOffset", getArrayOffset())
            .append("arrayActualCount", getArrayActualCount())
            .append("blocks", getBlocks())
            .toString();
    }

}
