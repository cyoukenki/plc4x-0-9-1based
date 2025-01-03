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

public class PnIoCm_Block_AlarmCrReq extends PnIoCm_Block implements Message {

    // Accessors for discriminator values.
    public PnIoCm_BlockType getBlockType() {
        return PnIoCm_BlockType.ALARM_CR_BLOCK_REQ;
    }

    // Properties.
    private final PnIoCm_AlarmCrType alarmType;
    private final int lt;
    private final boolean transport;
    private final boolean priority;
    private final int rtaTimeoutFactor;
    private final int rtaRetries;
    private final int localAlarmReference;
    private final int maxAlarmDataLength;
    private final int alarmCtrTagHeaderHigh;
    private final int alarmCtrTagHeaderLow;

    public PnIoCm_Block_AlarmCrReq(short blockVersionHigh, short blockVersionLow, PnIoCm_AlarmCrType alarmType, int lt, boolean transport, boolean priority, int rtaTimeoutFactor, int rtaRetries, int localAlarmReference, int maxAlarmDataLength, int alarmCtrTagHeaderHigh, int alarmCtrTagHeaderLow) {
        super(blockVersionHigh, blockVersionLow);
        this.alarmType = alarmType;
        this.lt = lt;
        this.transport = transport;
        this.priority = priority;
        this.rtaTimeoutFactor = rtaTimeoutFactor;
        this.rtaRetries = rtaRetries;
        this.localAlarmReference = localAlarmReference;
        this.maxAlarmDataLength = maxAlarmDataLength;
        this.alarmCtrTagHeaderHigh = alarmCtrTagHeaderHigh;
        this.alarmCtrTagHeaderLow = alarmCtrTagHeaderLow;
    }

    public PnIoCm_AlarmCrType getAlarmType() {
        return alarmType;
    }

    public int getLt() {
        return lt;
    }

    public boolean getTransport() {
        return transport;
    }

    public boolean getPriority() {
        return priority;
    }

    public int getRtaTimeoutFactor() {
        return rtaTimeoutFactor;
    }

    public int getRtaRetries() {
        return rtaRetries;
    }

    public int getLocalAlarmReference() {
        return localAlarmReference;
    }

    public int getMaxAlarmDataLength() {
        return maxAlarmDataLength;
    }

    public int getAlarmCtrTagHeaderHigh() {
        return alarmCtrTagHeaderHigh;
    }

    public int getAlarmCtrTagHeaderLow() {
        return alarmCtrTagHeaderLow;
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
        PnIoCm_Block_AlarmCrReq _value  = this;

        // Simple field (alarmType)
        lengthInBits += 16;

        // Simple field (lt)
        lengthInBits += 16;

        // Reserved Field (reserved)
        lengthInBits += 30;

        // Simple field (transport)
        lengthInBits += 1;

        // Simple field (priority)
        lengthInBits += 1;

        // Simple field (rtaTimeoutFactor)
        lengthInBits += 16;

        // Simple field (rtaRetries)
        lengthInBits += 16;

        // Simple field (localAlarmReference)
        lengthInBits += 16;

        // Simple field (maxAlarmDataLength)
        lengthInBits += 16;

        // Simple field (alarmCtrTagHeaderHigh)
        lengthInBits += 16;

        // Simple field (alarmCtrTagHeaderLow)
        lengthInBits += 16;

        return lengthInBits;
    }

    @Override
    public MessageIO<PnIoCm_Block, PnIoCm_Block> getMessageIO() {
        return new PnIoCm_BlockIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PnIoCm_Block_AlarmCrReq)) {
            return false;
        }
        PnIoCm_Block_AlarmCrReq that = (PnIoCm_Block_AlarmCrReq) o;
        return
            (getAlarmType() == that.getAlarmType()) &&
            (getLt() == that.getLt()) &&
            (getTransport() == that.getTransport()) &&
            (getPriority() == that.getPriority()) &&
            (getRtaTimeoutFactor() == that.getRtaTimeoutFactor()) &&
            (getRtaRetries() == that.getRtaRetries()) &&
            (getLocalAlarmReference() == that.getLocalAlarmReference()) &&
            (getMaxAlarmDataLength() == that.getMaxAlarmDataLength()) &&
            (getAlarmCtrTagHeaderHigh() == that.getAlarmCtrTagHeaderHigh()) &&
            (getAlarmCtrTagHeaderLow() == that.getAlarmCtrTagHeaderLow()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getAlarmType(),
            getLt(),
            getTransport(),
            getPriority(),
            getRtaTimeoutFactor(),
            getRtaRetries(),
            getLocalAlarmReference(),
            getMaxAlarmDataLength(),
            getAlarmCtrTagHeaderHigh(),
            getAlarmCtrTagHeaderLow()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("alarmType", getAlarmType())
            .append("lt", getLt())
            .append("transport", getTransport())
            .append("priority", getPriority())
            .append("rtaTimeoutFactor", getRtaTimeoutFactor())
            .append("rtaRetries", getRtaRetries())
            .append("localAlarmReference", getLocalAlarmReference())
            .append("maxAlarmDataLength", getMaxAlarmDataLength())
            .append("alarmCtrTagHeaderHigh", getAlarmCtrTagHeaderHigh())
            .append("alarmCtrTagHeaderLow", getAlarmCtrTagHeaderLow())
            .toString();
    }

}
