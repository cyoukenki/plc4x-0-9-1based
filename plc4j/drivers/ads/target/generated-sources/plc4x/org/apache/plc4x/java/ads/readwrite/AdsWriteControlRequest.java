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
package org.apache.plc4x.java.ads.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.readwrite.io.*;
import org.apache.plc4x.java.ads.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class AdsWriteControlRequest extends AdsData implements Message {

    // Accessors for discriminator values.
    public CommandId getCommandId() {
        return CommandId.ADS_WRITE_CONTROL;
    }
    public Boolean getResponse() {
        return false;
    }

    // Properties.
    private final int adsState;
    private final int deviceState;
    private final byte[] data;

    public AdsWriteControlRequest(int adsState, int deviceState, byte[] data) {
        this.adsState = adsState;
        this.deviceState = deviceState;
        this.data = data;
    }

    public int getAdsState() {
        return adsState;
    }

    public int getDeviceState() {
        return deviceState;
    }

    public byte[] getData() {
        return data;
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
        AdsWriteControlRequest _value  = this;

        // Simple field (adsState)
        lengthInBits += 16;

        // Simple field (deviceState)
        lengthInBits += 16;

        // Implicit Field (length)
        lengthInBits += 32;
        //long length = (long) (COUNT(_value.getData()));

        // Array field
        if(data != null) {
            lengthInBits += 8 * data.length;
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<AdsData, AdsData> getMessageIO() {
        return new AdsDataIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdsWriteControlRequest)) {
            return false;
        }
        AdsWriteControlRequest that = (AdsWriteControlRequest) o;
        return
            (getAdsState() == that.getAdsState()) &&
            (getDeviceState() == that.getDeviceState()) &&
            (getData() == that.getData()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getAdsState(),
            getDeviceState(),
            getData()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("adsState", getAdsState())
            .append("deviceState", getDeviceState())
            .append("data", getData())
            .toString();
    }

}