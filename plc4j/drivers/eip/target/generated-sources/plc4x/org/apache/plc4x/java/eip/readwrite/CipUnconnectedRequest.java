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
package org.apache.plc4x.java.eip.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.eip.readwrite.io.*;
import org.apache.plc4x.java.eip.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class CipUnconnectedRequest extends CipService implements Message {

    // Accessors for discriminator values.
    public Short getService() {
        return 0x52;
    }

    // Constant values.
    public static final int ROUTE = 0x0001;

    // Properties.
    private final CipService unconnectedService;
    private final byte backPlane;
    private final byte slot;

    public CipUnconnectedRequest(CipService unconnectedService, byte backPlane, byte slot) {
        this.unconnectedService = unconnectedService;
        this.backPlane = backPlane;
        this.slot = slot;
    }

    public CipService getUnconnectedService() {
        return unconnectedService;
    }

    public byte getBackPlane() {
        return backPlane;
    }

    public byte getSlot() {
        return slot;
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
        CipUnconnectedRequest _value  = this;

        // Reserved Field (reserved)
        lengthInBits += 8;

        // Reserved Field (reserved)
        lengthInBits += 8;

        // Reserved Field (reserved)
        lengthInBits += 8;

        // Reserved Field (reserved)
        lengthInBits += 8;

        // Reserved Field (reserved)
        lengthInBits += 8;

        // Reserved Field (reserved)
        lengthInBits += 16;

        // Implicit Field (messageSize)
        lengthInBits += 16;
        //int messageSize = (int) (((_value.getLengthInBytes()) - (10)) - (4));

        // Simple field (unconnectedService)
        lengthInBits += unconnectedService.getLengthInBits();

        // Const Field (route)
        lengthInBits += 16;

        // Simple field (backPlane)
        lengthInBits += 8;

        // Simple field (slot)
        lengthInBits += 8;

        return lengthInBits;
    }

    @Override
    public MessageIO<CipService, CipService> getMessageIO() {
        return new CipServiceIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CipUnconnectedRequest)) {
            return false;
        }
        CipUnconnectedRequest that = (CipUnconnectedRequest) o;
        return
            (getUnconnectedService() == that.getUnconnectedService()) &&
            (getBackPlane() == that.getBackPlane()) &&
            (getSlot() == that.getSlot()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getUnconnectedService(),
            getBackPlane(),
            getSlot()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("unconnectedService", getUnconnectedService())
            .append("backPlane", getBackPlane())
            .append("slot", getSlot())
            .toString();
    }

}
