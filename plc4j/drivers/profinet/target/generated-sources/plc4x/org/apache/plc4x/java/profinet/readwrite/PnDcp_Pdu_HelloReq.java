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

public class PnDcp_Pdu_HelloReq extends PnDcp_Pdu implements Message {

    // Accessors for discriminator values.
    public PnDcp_FrameId getFrameId() {
        return PnDcp_FrameId.DCP_Hello_ReqPDU;
    }
    public PnDcp_ServiceId getServiceId() {
        return PnDcp_ServiceId.HELLO;
    }
    public Boolean getServiceTypeResponse() {
        return false;
    }

    public PnDcp_Pdu_HelloReq(PnDcp_ServiceType serviceType, long xid, int responseDelayFactorOrPadding) {
        super(serviceType, xid, responseDelayFactorOrPadding);
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
        PnDcp_Pdu_HelloReq _value  = this;

        return lengthInBits;
    }

    @Override
    public MessageIO<PnDcp_Pdu, PnDcp_Pdu> getMessageIO() {
        return new PnDcp_PduIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PnDcp_Pdu_HelloReq)) {
            return false;
        }
        PnDcp_Pdu_HelloReq that = (PnDcp_Pdu_HelloReq) o;
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