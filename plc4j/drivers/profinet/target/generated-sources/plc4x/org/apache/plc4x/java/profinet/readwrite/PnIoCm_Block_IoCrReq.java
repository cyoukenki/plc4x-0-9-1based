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

public class PnIoCm_Block_IoCrReq extends PnIoCm_Block implements Message {

    // Accessors for discriminator values.
    public PnIoCm_BlockType getBlockType() {
        return PnIoCm_BlockType.IO_CR_BLOCK_REQ;
    }

    // Properties.
    private final PnIoCm_IoCrType ioCrType;
    private final int ioCrReference;
    private final int lt;
    private final boolean fullSubFrameStructure;
    private final boolean distributedSubFrameWatchDog;
    private final boolean fastForwardingMacAdr;
    private final boolean mediaRedundancy;
    private final PnIoCm_RtClass rtClass;
    private final int dataLength;
    private final int frameId;
    private final int sendClockFactor;
    private final int reductionRatio;
    private final int phase;
    private final int sequence;
    private final long frameSendOffset;
    private final int watchDogFactor;
    private final int dataHoldFactor;
    private final int ioCrTagHeader;
    private final MacAddress ioCrMulticastMacAdr;
    private final PnIoCm_IoCrBlockReqApi[] apis;

    public PnIoCm_Block_IoCrReq(short blockVersionHigh, short blockVersionLow, PnIoCm_IoCrType ioCrType, int ioCrReference, int lt, boolean fullSubFrameStructure, boolean distributedSubFrameWatchDog, boolean fastForwardingMacAdr, boolean mediaRedundancy, PnIoCm_RtClass rtClass, int dataLength, int frameId, int sendClockFactor, int reductionRatio, int phase, int sequence, long frameSendOffset, int watchDogFactor, int dataHoldFactor, int ioCrTagHeader, MacAddress ioCrMulticastMacAdr, PnIoCm_IoCrBlockReqApi[] apis) {
        super(blockVersionHigh, blockVersionLow);
        this.ioCrType = ioCrType;
        this.ioCrReference = ioCrReference;
        this.lt = lt;
        this.fullSubFrameStructure = fullSubFrameStructure;
        this.distributedSubFrameWatchDog = distributedSubFrameWatchDog;
        this.fastForwardingMacAdr = fastForwardingMacAdr;
        this.mediaRedundancy = mediaRedundancy;
        this.rtClass = rtClass;
        this.dataLength = dataLength;
        this.frameId = frameId;
        this.sendClockFactor = sendClockFactor;
        this.reductionRatio = reductionRatio;
        this.phase = phase;
        this.sequence = sequence;
        this.frameSendOffset = frameSendOffset;
        this.watchDogFactor = watchDogFactor;
        this.dataHoldFactor = dataHoldFactor;
        this.ioCrTagHeader = ioCrTagHeader;
        this.ioCrMulticastMacAdr = ioCrMulticastMacAdr;
        this.apis = apis;
    }

    public PnIoCm_IoCrType getIoCrType() {
        return ioCrType;
    }

    public int getIoCrReference() {
        return ioCrReference;
    }

    public int getLt() {
        return lt;
    }

    public boolean getFullSubFrameStructure() {
        return fullSubFrameStructure;
    }

    public boolean getDistributedSubFrameWatchDog() {
        return distributedSubFrameWatchDog;
    }

    public boolean getFastForwardingMacAdr() {
        return fastForwardingMacAdr;
    }

    public boolean getMediaRedundancy() {
        return mediaRedundancy;
    }

    public PnIoCm_RtClass getRtClass() {
        return rtClass;
    }

    public int getDataLength() {
        return dataLength;
    }

    public int getFrameId() {
        return frameId;
    }

    public int getSendClockFactor() {
        return sendClockFactor;
    }

    public int getReductionRatio() {
        return reductionRatio;
    }

    public int getPhase() {
        return phase;
    }

    public int getSequence() {
        return sequence;
    }

    public long getFrameSendOffset() {
        return frameSendOffset;
    }

    public int getWatchDogFactor() {
        return watchDogFactor;
    }

    public int getDataHoldFactor() {
        return dataHoldFactor;
    }

    public int getIoCrTagHeader() {
        return ioCrTagHeader;
    }

    public MacAddress getIoCrMulticastMacAdr() {
        return ioCrMulticastMacAdr;
    }

    public PnIoCm_IoCrBlockReqApi[] getApis() {
        return apis;
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
        PnIoCm_Block_IoCrReq _value  = this;

        // Simple field (ioCrType)
        lengthInBits += 16;

        // Simple field (ioCrReference)
        lengthInBits += 16;

        // Simple field (lt)
        lengthInBits += 16;

        // Simple field (fullSubFrameStructure)
        lengthInBits += 1;

        // Simple field (distributedSubFrameWatchDog)
        lengthInBits += 1;

        // Simple field (fastForwardingMacAdr)
        lengthInBits += 1;

        // Reserved Field (reserved)
        lengthInBits += 17;

        // Simple field (mediaRedundancy)
        lengthInBits += 1;

        // Reserved Field (reserved)
        lengthInBits += 7;

        // Simple field (rtClass)
        lengthInBits += 4;

        // Simple field (dataLength)
        lengthInBits += 16;

        // Simple field (frameId)
        lengthInBits += 16;

        // Simple field (sendClockFactor)
        lengthInBits += 16;

        // Simple field (reductionRatio)
        lengthInBits += 16;

        // Simple field (phase)
        lengthInBits += 16;

        // Simple field (sequence)
        lengthInBits += 16;

        // Simple field (frameSendOffset)
        lengthInBits += 32;

        // Simple field (watchDogFactor)
        lengthInBits += 16;

        // Simple field (dataHoldFactor)
        lengthInBits += 16;

        // Simple field (ioCrTagHeader)
        lengthInBits += 16;

        // Simple field (ioCrMulticastMacAdr)
        lengthInBits += ioCrMulticastMacAdr.getLengthInBits();

        // Implicit Field (numberOfApis)
        lengthInBits += 16;
        //int numberOfApis = (int) (COUNT(_value.getApis()));

        // Array field
        if(apis != null) {
            int i=0;
            for(PnIoCm_IoCrBlockReqApi element : apis) {
                boolean last = ++i >= apis.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

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
        if (!(o instanceof PnIoCm_Block_IoCrReq)) {
            return false;
        }
        PnIoCm_Block_IoCrReq that = (PnIoCm_Block_IoCrReq) o;
        return
            (getIoCrType() == that.getIoCrType()) &&
            (getIoCrReference() == that.getIoCrReference()) &&
            (getLt() == that.getLt()) &&
            (getFullSubFrameStructure() == that.getFullSubFrameStructure()) &&
            (getDistributedSubFrameWatchDog() == that.getDistributedSubFrameWatchDog()) &&
            (getFastForwardingMacAdr() == that.getFastForwardingMacAdr()) &&
            (getMediaRedundancy() == that.getMediaRedundancy()) &&
            (getRtClass() == that.getRtClass()) &&
            (getDataLength() == that.getDataLength()) &&
            (getFrameId() == that.getFrameId()) &&
            (getSendClockFactor() == that.getSendClockFactor()) &&
            (getReductionRatio() == that.getReductionRatio()) &&
            (getPhase() == that.getPhase()) &&
            (getSequence() == that.getSequence()) &&
            (getFrameSendOffset() == that.getFrameSendOffset()) &&
            (getWatchDogFactor() == that.getWatchDogFactor()) &&
            (getDataHoldFactor() == that.getDataHoldFactor()) &&
            (getIoCrTagHeader() == that.getIoCrTagHeader()) &&
            (getIoCrMulticastMacAdr() == that.getIoCrMulticastMacAdr()) &&
            (getApis() == that.getApis()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getIoCrType(),
            getIoCrReference(),
            getLt(),
            getFullSubFrameStructure(),
            getDistributedSubFrameWatchDog(),
            getFastForwardingMacAdr(),
            getMediaRedundancy(),
            getRtClass(),
            getDataLength(),
            getFrameId(),
            getSendClockFactor(),
            getReductionRatio(),
            getPhase(),
            getSequence(),
            getFrameSendOffset(),
            getWatchDogFactor(),
            getDataHoldFactor(),
            getIoCrTagHeader(),
            getIoCrMulticastMacAdr(),
            getApis()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("ioCrType", getIoCrType())
            .append("ioCrReference", getIoCrReference())
            .append("lt", getLt())
            .append("fullSubFrameStructure", getFullSubFrameStructure())
            .append("distributedSubFrameWatchDog", getDistributedSubFrameWatchDog())
            .append("fastForwardingMacAdr", getFastForwardingMacAdr())
            .append("mediaRedundancy", getMediaRedundancy())
            .append("rtClass", getRtClass())
            .append("dataLength", getDataLength())
            .append("frameId", getFrameId())
            .append("sendClockFactor", getSendClockFactor())
            .append("reductionRatio", getReductionRatio())
            .append("phase", getPhase())
            .append("sequence", getSequence())
            .append("frameSendOffset", getFrameSendOffset())
            .append("watchDogFactor", getWatchDogFactor())
            .append("dataHoldFactor", getDataHoldFactor())
            .append("ioCrTagHeader", getIoCrTagHeader())
            .append("ioCrMulticastMacAdr", getIoCrMulticastMacAdr())
            .append("apis", getApis())
            .toString();
    }

}