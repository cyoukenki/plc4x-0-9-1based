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
package org.apache.plc4x.java.s7.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.s7.readwrite.io.*;
import org.apache.plc4x.java.s7.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class AlarmMessageAckResponseType implements Message {


    // Properties.
    private final short functionId;
    private final short numberOfObjects;
    private final short[] messageObjects;

    public AlarmMessageAckResponseType(short functionId, short numberOfObjects, short[] messageObjects) {
        this.functionId = functionId;
        this.numberOfObjects = numberOfObjects;
        this.messageObjects = messageObjects;
    }

    public short getFunctionId() {
        return functionId;
    }

    public short getNumberOfObjects() {
        return numberOfObjects;
    }

    public short[] getMessageObjects() {
        return messageObjects;
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
        AlarmMessageAckResponseType _value  = this;

        // Simple field (functionId)
        lengthInBits += 8;

        // Simple field (numberOfObjects)
        lengthInBits += 8;

        // Array field
        if(messageObjects != null) {
            lengthInBits += 8 * messageObjects.length;
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<AlarmMessageAckResponseType, AlarmMessageAckResponseType> getMessageIO() {
        return new AlarmMessageAckResponseTypeIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AlarmMessageAckResponseType)) {
            return false;
        }
        AlarmMessageAckResponseType that = (AlarmMessageAckResponseType) o;
        return
            (getFunctionId() == that.getFunctionId()) &&
            (getNumberOfObjects() == that.getNumberOfObjects()) &&
            (getMessageObjects() == that.getMessageObjects()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getFunctionId(),
            getNumberOfObjects(),
            getMessageObjects()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("functionId", getFunctionId())
            .append("numberOfObjects", getNumberOfObjects())
            .append("messageObjects", getMessageObjects())
            .toString();
    }

}