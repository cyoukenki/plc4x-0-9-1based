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
package org.apache.plc4x.java.abeth.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.abeth.readwrite.io.*;
import org.apache.plc4x.java.abeth.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public abstract class CIPEncapsulationPacket implements Message {

    // Abstract accessors for discriminator values.
    public abstract Integer getCommandType();

    // Properties.
    private final long sessionHandle;
    private final long status;
    private final short[] senderContext;
    private final long options;

    public CIPEncapsulationPacket(long sessionHandle, long status, short[] senderContext, long options) {
        this.sessionHandle = sessionHandle;
        this.status = status;
        this.senderContext = senderContext;
        this.options = options;
    }

    public long getSessionHandle() {
        return sessionHandle;
    }

    public long getStatus() {
        return status;
    }

    public short[] getSenderContext() {
        return senderContext;
    }

    public long getOptions() {
        return options;
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
        CIPEncapsulationPacket _value  = this;

        // Discriminator Field (commandType)
        lengthInBits += 16;

        // Implicit Field (len)
        lengthInBits += 16;
        //int len = (int) ((_value.getLengthInBytes()) - (28));

        // Simple field (sessionHandle)
        lengthInBits += 32;

        // Simple field (status)
        lengthInBits += 32;

        // Array field
        if(senderContext != null) {
            lengthInBits += 8 * senderContext.length;
        }

        // Simple field (options)
        lengthInBits += 32;

        // Reserved Field (reserved)
        lengthInBits += 32;

        // Length of sub-type elements will be added by sub-type...

        return lengthInBits;
    }

    @Override
    public MessageIO<CIPEncapsulationPacket, CIPEncapsulationPacket> getMessageIO() {
        return new CIPEncapsulationPacketIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CIPEncapsulationPacket)) {
            return false;
        }
        CIPEncapsulationPacket that = (CIPEncapsulationPacket) o;
        return
            (getSessionHandle() == that.getSessionHandle()) &&
            (getStatus() == that.getStatus()) &&
            (getSenderContext() == that.getSenderContext()) &&
            (getOptions() == that.getOptions()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getSessionHandle(),
            getStatus(),
            getSenderContext(),
            getOptions()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("sessionHandle", getSessionHandle())
            .append("status", getStatus())
            .append("senderContext", getSenderContext())
            .append("options", getOptions())
            .toString();
    }

}
