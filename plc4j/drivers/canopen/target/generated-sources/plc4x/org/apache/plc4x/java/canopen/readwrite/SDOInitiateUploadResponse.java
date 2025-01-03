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
package org.apache.plc4x.java.canopen.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.canopen.readwrite.io.*;
import org.apache.plc4x.java.canopen.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class SDOInitiateUploadResponse extends SDOResponse implements Message {

    // Accessors for discriminator values.
    public SDOResponseCommand getCommand() {
        return SDOResponseCommand.INITIATE_UPLOAD;
    }

    // Properties.
    private final boolean expedited;
    private final boolean indicated;
    private final IndexAddress address;
    private final SDOInitiateUploadResponsePayload payload;

    public SDOInitiateUploadResponse(boolean expedited, boolean indicated, IndexAddress address, SDOInitiateUploadResponsePayload payload) {
        this.expedited = expedited;
        this.indicated = indicated;
        this.address = address;
        this.payload = payload;
    }

    public boolean getExpedited() {
        return expedited;
    }

    public boolean getIndicated() {
        return indicated;
    }

    public IndexAddress getAddress() {
        return address;
    }

    public SDOInitiateUploadResponsePayload getPayload() {
        return payload;
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
        SDOInitiateUploadResponse _value  = this;

        // Reserved Field (reserved)
        lengthInBits += 1;

        // Implicit Field (size)
        lengthInBits += 2;
        //byte size = (byte) (org.apache.plc4x.java.canopen.helper.CANOpenHelper.count(_value.getExpedited(), _value.getIndicated(), _value.getPayload()));

        // Simple field (expedited)
        lengthInBits += 1;

        // Simple field (indicated)
        lengthInBits += 1;

        // Simple field (address)
        lengthInBits += address.getLengthInBits();

        // Simple field (payload)
        lengthInBits += payload.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<SDOResponse, SDOResponse> getMessageIO() {
        return new SDOResponseIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SDOInitiateUploadResponse)) {
            return false;
        }
        SDOInitiateUploadResponse that = (SDOInitiateUploadResponse) o;
        return
            (getExpedited() == that.getExpedited()) &&
            (getIndicated() == that.getIndicated()) &&
            (getAddress() == that.getAddress()) &&
            (getPayload() == that.getPayload()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getExpedited(),
            getIndicated(),
            getAddress(),
            getPayload()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("expedited", getExpedited())
            .append("indicated", getIndicated())
            .append("address", getAddress())
            .append("payload", getPayload())
            .toString();
    }

}
