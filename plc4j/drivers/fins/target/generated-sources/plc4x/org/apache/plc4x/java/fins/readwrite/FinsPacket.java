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
package org.apache.plc4x.java.fins.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.fins.readwrite.io.*;
import org.apache.plc4x.java.fins.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public abstract class FinsPacket implements Message {

    // Abstract accessors for discriminator values.
    public abstract Long getCommand();

    // Constant values.
    public static final long FINSHEADER = 0x46494E53;

    // Properties.
    private final long errorCode;
    private final short ICF;
    private final short RSV;
    private final short GCT;
    private final short DNA;

    public FinsPacket(long errorCode, short ICF, short RSV, short GCT, short DNA) {
        this.errorCode = errorCode;
        this.ICF = ICF;
        this.RSV = RSV;
        this.GCT = GCT;
        this.DNA = DNA;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public short getICF() {
        return ICF;
    }

    public short getRSV() {
        return RSV;
    }

    public short getGCT() {
        return GCT;
    }

    public short getDNA() {
        return DNA;
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
        FinsPacket _value  = this;

        // Const Field (finsHeader)
        lengthInBits += 32;

        // Implicit Field (len)
        lengthInBits += 32;
        //long len = (long) ((_value.getLengthInBytes()) - (8));

        // Discriminator Field (command)
        lengthInBits += 32;

        // Simple field (errorCode)
        lengthInBits += 32;

        // Simple field (ICF)
        lengthInBits += 8;

        // Simple field (RSV)
        lengthInBits += 8;

        // Simple field (GCT)
        lengthInBits += 8;

        // Simple field (DNA)
        lengthInBits += 8;

        // Length of sub-type elements will be added by sub-type...

        return lengthInBits;
    }

    @Override
    public MessageIO<FinsPacket, FinsPacket> getMessageIO() {
        return new FinsPacketIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinsPacket)) {
            return false;
        }
        FinsPacket that = (FinsPacket) o;
        return
            (getErrorCode() == that.getErrorCode()) &&
            (getICF() == that.getICF()) &&
            (getRSV() == that.getRSV()) &&
            (getGCT() == that.getGCT()) &&
            (getDNA() == that.getDNA()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getErrorCode(),
            getICF(),
            getRSV(),
            getGCT(),
            getDNA()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("errorCode", getErrorCode())
            .append("ICF", getICF())
            .append("RSV", getRSV())
            .append("GCT", getGCT())
            .append("DNA", getDNA())
            .toString();
    }

}