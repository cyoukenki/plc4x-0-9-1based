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
package org.apache.plc4x.java.ads.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.readwrite.io.*;
import org.apache.plc4x.java.ads.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class AdsMultiRequestItemReadWrite extends AdsMultiRequestItem implements Message {

    // Accessors for discriminator values.
    public Long getIndexGroup() {
        return 61570L;
    }

    // Properties.
    private final long itemIndexGroup;
    private final long itemIndexOffset;
    private final long itemReadLength;
    private final long itemWriteLength;

    public AdsMultiRequestItemReadWrite(long itemIndexGroup, long itemIndexOffset, long itemReadLength, long itemWriteLength) {
        this.itemIndexGroup = itemIndexGroup;
        this.itemIndexOffset = itemIndexOffset;
        this.itemReadLength = itemReadLength;
        this.itemWriteLength = itemWriteLength;
    }

    public long getItemIndexGroup() {
        return itemIndexGroup;
    }

    public long getItemIndexOffset() {
        return itemIndexOffset;
    }

    public long getItemReadLength() {
        return itemReadLength;
    }

    public long getItemWriteLength() {
        return itemWriteLength;
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
        AdsMultiRequestItemReadWrite _value  = this;

        // Simple field (itemIndexGroup)
        lengthInBits += 32;

        // Simple field (itemIndexOffset)
        lengthInBits += 32;

        // Simple field (itemReadLength)
        lengthInBits += 32;

        // Simple field (itemWriteLength)
        lengthInBits += 32;

        return lengthInBits;
    }

    @Override
    public MessageIO<AdsMultiRequestItem, AdsMultiRequestItem> getMessageIO() {
        return new AdsMultiRequestItemIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdsMultiRequestItemReadWrite)) {
            return false;
        }
        AdsMultiRequestItemReadWrite that = (AdsMultiRequestItemReadWrite) o;
        return
            (getItemIndexGroup() == that.getItemIndexGroup()) &&
            (getItemIndexOffset() == that.getItemIndexOffset()) &&
            (getItemReadLength() == that.getItemReadLength()) &&
            (getItemWriteLength() == that.getItemWriteLength()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getItemIndexGroup(),
            getItemIndexOffset(),
            getItemReadLength(),
            getItemWriteLength()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("itemIndexGroup", getItemIndexGroup())
            .append("itemIndexOffset", getItemIndexOffset())
            .append("itemReadLength", getItemReadLength())
            .append("itemWriteLength", getItemWriteLength())
            .toString();
    }

}
