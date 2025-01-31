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

public class AmsSerialFrame implements Message {


    // Properties.
    private final int magicCookie;
    private final byte transmitterAddress;
    private final byte receiverAddress;
    private final byte fragmentNumber;
    private final byte length;
    private final AmsPacket userdata;
    private final int crc;

    public AmsSerialFrame(int magicCookie, byte transmitterAddress, byte receiverAddress, byte fragmentNumber, byte length, AmsPacket userdata, int crc) {
        this.magicCookie = magicCookie;
        this.transmitterAddress = transmitterAddress;
        this.receiverAddress = receiverAddress;
        this.fragmentNumber = fragmentNumber;
        this.length = length;
        this.userdata = userdata;
        this.crc = crc;
    }

    public int getMagicCookie() {
        return magicCookie;
    }

    public byte getTransmitterAddress() {
        return transmitterAddress;
    }

    public byte getReceiverAddress() {
        return receiverAddress;
    }

    public byte getFragmentNumber() {
        return fragmentNumber;
    }

    public byte getLength() {
        return length;
    }

    public AmsPacket getUserdata() {
        return userdata;
    }

    public int getCrc() {
        return crc;
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
        AmsSerialFrame _value  = this;

        // Simple field (magicCookie)
        lengthInBits += 16;

        // Simple field (transmitterAddress)
        lengthInBits += 8;

        // Simple field (receiverAddress)
        lengthInBits += 8;

        // Simple field (fragmentNumber)
        lengthInBits += 8;

        // Simple field (length)
        lengthInBits += 8;

        // Simple field (userdata)
        lengthInBits += userdata.getLengthInBits();

        // Simple field (crc)
        lengthInBits += 16;

        return lengthInBits;
    }

    @Override
    public MessageIO<AmsSerialFrame, AmsSerialFrame> getMessageIO() {
        return new AmsSerialFrameIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AmsSerialFrame)) {
            return false;
        }
        AmsSerialFrame that = (AmsSerialFrame) o;
        return
            (getMagicCookie() == that.getMagicCookie()) &&
            (getTransmitterAddress() == that.getTransmitterAddress()) &&
            (getReceiverAddress() == that.getReceiverAddress()) &&
            (getFragmentNumber() == that.getFragmentNumber()) &&
            (getLength() == that.getLength()) &&
            (getUserdata() == that.getUserdata()) &&
            (getCrc() == that.getCrc()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getMagicCookie(),
            getTransmitterAddress(),
            getReceiverAddress(),
            getFragmentNumber(),
            getLength(),
            getUserdata(),
            getCrc()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("magicCookie", getMagicCookie())
            .append("transmitterAddress", getTransmitterAddress())
            .append("receiverAddress", getReceiverAddress())
            .append("fragmentNumber", getFragmentNumber())
            .append("length", getLength())
            .append("userdata", getUserdata())
            .append("crc", getCrc())
            .toString();
    }

}
