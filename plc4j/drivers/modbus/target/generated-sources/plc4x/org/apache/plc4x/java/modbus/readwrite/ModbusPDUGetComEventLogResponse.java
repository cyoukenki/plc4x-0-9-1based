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
package org.apache.plc4x.java.modbus.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.modbus.readwrite.io.*;
import org.apache.plc4x.java.modbus.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class ModbusPDUGetComEventLogResponse extends ModbusPDU implements Message {

    // Accessors for discriminator values.
    public Boolean getErrorFlag() {
        return false;
    }
    public Short getFunctionFlag() {
        return 0x0C;
    }
    public Boolean getResponse() {
        return true;
    }

    // Properties.
    private final int status;
    private final int eventCount;
    private final int messageCount;
    private final byte[] events;

    public ModbusPDUGetComEventLogResponse(int status, int eventCount, int messageCount, byte[] events) {
        this.status = status;
        this.eventCount = eventCount;
        this.messageCount = messageCount;
        this.events = events;
    }

    public int getStatus() {
        return status;
    }

    public int getEventCount() {
        return eventCount;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public byte[] getEvents() {
        return events;
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
        ModbusPDUGetComEventLogResponse _value  = this;

        // Implicit Field (byteCount)
        lengthInBits += 8;
        //short byteCount = (short) ((COUNT(_value.getEvents())) + (6));

        // Simple field (status)
        lengthInBits += 16;

        // Simple field (eventCount)
        lengthInBits += 16;

        // Simple field (messageCount)
        lengthInBits += 16;

        // Array field
        if(events != null) {
            lengthInBits += 8 * events.length;
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<ModbusPDU, ModbusPDU> getMessageIO() {
        return new ModbusPDUIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModbusPDUGetComEventLogResponse)) {
            return false;
        }
        ModbusPDUGetComEventLogResponse that = (ModbusPDUGetComEventLogResponse) o;
        return
            (getStatus() == that.getStatus()) &&
            (getEventCount() == that.getEventCount()) &&
            (getMessageCount() == that.getMessageCount()) &&
            (getEvents() == that.getEvents()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getStatus(),
            getEventCount(),
            getMessageCount(),
            getEvents()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("status", getStatus())
            .append("eventCount", getEventCount())
            .append("messageCount", getMessageCount())
            .append("events", getEvents())
            .toString();
    }

}