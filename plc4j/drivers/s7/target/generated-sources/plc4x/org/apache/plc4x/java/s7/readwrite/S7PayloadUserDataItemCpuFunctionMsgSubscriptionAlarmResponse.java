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

public class S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse extends S7PayloadUserDataItem implements Message {

    // Accessors for discriminator values.
    public Byte getCpuFunctionType() {
        return 0x08;
    }
    public Short getCpuSubfunction() {
        return 0x02;
    }
    public Integer getDataLength() {
        return 0x05;
    }

    // Properties.
    private final short result;
    private final short reserved01;
    private final AlarmType alarmType;
    private final short reserved02;
    private final short reserved03;

    public S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse(DataTransportErrorCode returnCode, DataTransportSize transportSize, short result, short reserved01, AlarmType alarmType, short reserved02, short reserved03) {
        super(returnCode, transportSize);
        this.result = result;
        this.reserved01 = reserved01;
        this.alarmType = alarmType;
        this.reserved02 = reserved02;
        this.reserved03 = reserved03;
    }

    public short getResult() {
        return result;
    }

    public short getReserved01() {
        return reserved01;
    }

    public AlarmType getAlarmType() {
        return alarmType;
    }

    public short getReserved02() {
        return reserved02;
    }

    public short getReserved03() {
        return reserved03;
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
        S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse _value  = this;

        // Simple field (result)
        lengthInBits += 8;

        // Simple field (reserved01)
        lengthInBits += 8;

        // Simple field (alarmType)
        lengthInBits += 8;

        // Simple field (reserved02)
        lengthInBits += 8;

        // Simple field (reserved03)
        lengthInBits += 8;

        return lengthInBits;
    }

    @Override
    public MessageIO<S7PayloadUserDataItem, S7PayloadUserDataItem> getMessageIO() {
        return new S7PayloadUserDataItemIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse)) {
            return false;
        }
        S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse that = (S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) o;
        return
            (getResult() == that.getResult()) &&
            (getReserved01() == that.getReserved01()) &&
            (getAlarmType() == that.getAlarmType()) &&
            (getReserved02() == that.getReserved02()) &&
            (getReserved03() == that.getReserved03()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getResult(),
            getReserved01(),
            getAlarmType(),
            getReserved02(),
            getReserved03()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("result", getResult())
            .append("reserved01", getReserved01())
            .append("alarmType", getAlarmType())
            .append("reserved02", getReserved02())
            .append("reserved03", getReserved03())
            .toString();
    }

}
