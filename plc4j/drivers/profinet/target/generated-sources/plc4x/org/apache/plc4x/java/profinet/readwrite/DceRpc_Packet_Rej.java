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

public class DceRpc_Packet_Rej extends DceRpc_Packet implements Message {

    // Accessors for discriminator values.
    public DceRpc_PacketType getPacketType() {
        return DceRpc_PacketType.REJECT;
    }

    // Constant values.
    public static final byte BROADCAST = 0;
    public static final byte IDEMPOTENT = 0;
    public static final byte MAYBE = 0;
    public static final byte NOFRAGMENTACKNOWLEDGEREQUESTED = 0;
    public static final byte FRAGMENT = 0;
    public static final byte LASTFRAGMENT = 0;

    public DceRpc_Packet_Rej(int instanceOrNodeNumber, int deviceId, int vendorId, long activity, long serverBootTime, long sequenceNumber, DceRpc_Operation operation, PnIoCm_Packet payload) {
        super(instanceOrNodeNumber, deviceId, vendorId, activity, serverBootTime, sequenceNumber, operation, payload);
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
        DceRpc_Packet_Rej _value  = this;

        // Reserved Field (reserved)
        lengthInBits += 1;

        // Const Field (broadcast)
        lengthInBits += 1;

        // Const Field (idempotent)
        lengthInBits += 1;

        // Const Field (maybe)
        lengthInBits += 1;

        // Const Field (noFragmentAcknowledgeRequested)
        lengthInBits += 1;

        // Const Field (fragment)
        lengthInBits += 1;

        // Const Field (lastFragment)
        lengthInBits += 1;

        // Reserved Field (reserved)
        lengthInBits += 1;

        return lengthInBits;
    }

    @Override
    public MessageIO<DceRpc_Packet, DceRpc_Packet> getMessageIO() {
        return new DceRpc_PacketIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DceRpc_Packet_Rej)) {
            return false;
        }
        DceRpc_Packet_Rej that = (DceRpc_Packet_Rej) o;
        return
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .toString();
    }

}