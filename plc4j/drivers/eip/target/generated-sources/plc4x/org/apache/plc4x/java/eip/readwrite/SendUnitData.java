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

public class SendUnitData extends EipPacket implements Message {

    // Accessors for discriminator values.
    public Integer getCommand() {
        return 0x0070;
    }

    // Constant values.
    public static final long API_HANDLE = 0x00;
    public static final int TIMEOUT = 0x00;
    public static final int ITEM_COUNT = 0x0002;
    public static final int CONNECTION_ADDRESS = 0x00a1;
    public static final int CONNECTION_LENGTH = 0x0004;
    public static final int CONNECTION_DATA_ITEM = 0x00b1;

    // Properties.
    private final long o_t_connection_id;
    private final CipExchange3 exchange3;

    public SendUnitData(long sessionHandle, long status, short[] senderContext, long options, long o_t_connection_id, CipExchange3 exchange3) {
        super(sessionHandle, status, senderContext, options);
        this.o_t_connection_id = o_t_connection_id;
        this.exchange3 = exchange3;
    }

    public long getO_t_connection_id() {
        return o_t_connection_id;
    }

    public CipExchange3 getExchange3() {
        return exchange3;
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
        SendUnitData _value  = this;

        // Const Field (api_handle)
        lengthInBits += 32;

        // Const Field (timeout)
        lengthInBits += 16;

        // Const Field (item_count)
        lengthInBits += 16;

        // Const Field (connection_address)
        lengthInBits += 16;

        // Const Field (connection_length)
        lengthInBits += 16;

        // Simple field (o_t_connection_id)
        lengthInBits += 32;

        // Const Field (connection_data_item)
        lengthInBits += 16;

        // Simple field (exchange3)
        lengthInBits += exchange3.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<EipPacket, EipPacket> getMessageIO() {
        return new EipPacketIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SendUnitData)) {
            return false;
        }
        SendUnitData that = (SendUnitData) o;
        return
            (getO_t_connection_id() == that.getO_t_connection_id()) &&
            (getExchange3() == that.getExchange3()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getO_t_connection_id(),
            getExchange3()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("o_t_connection_id", getO_t_connection_id())
            .append("exchange3", getExchange3())
            .toString();
    }

}