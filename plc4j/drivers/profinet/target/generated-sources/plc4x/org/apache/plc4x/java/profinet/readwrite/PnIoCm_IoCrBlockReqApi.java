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

public class PnIoCm_IoCrBlockReqApi implements Message {


    // Constant values.
    public static final long API = 0x00000000;

    // Properties.
    private final PnIoCm_IoDataObject[] ioDataObjects;
    private final PnIoCm_IoCs[] ioCss;

    public PnIoCm_IoCrBlockReqApi(PnIoCm_IoDataObject[] ioDataObjects, PnIoCm_IoCs[] ioCss) {
        this.ioDataObjects = ioDataObjects;
        this.ioCss = ioCss;
    }

    public PnIoCm_IoDataObject[] getIoDataObjects() {
        return ioDataObjects;
    }

    public PnIoCm_IoCs[] getIoCss() {
        return ioCss;
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
        PnIoCm_IoCrBlockReqApi _value  = this;

        // Const Field (api)
        lengthInBits += 32;

        // Implicit Field (numIoDataObjects)
        lengthInBits += 16;
        //int numIoDataObjects = (int) (COUNT(_value.getIoDataObjects()));

        // Array field
        if(ioDataObjects != null) {
            int i=0;
            for(PnIoCm_IoDataObject element : ioDataObjects) {
                boolean last = ++i >= ioDataObjects.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        // Implicit Field (numIoCss)
        lengthInBits += 16;
        //int numIoCss = (int) (COUNT(_value.getIoCss()));

        // Array field
        if(ioCss != null) {
            int i=0;
            for(PnIoCm_IoCs element : ioCss) {
                boolean last = ++i >= ioCss.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<PnIoCm_IoCrBlockReqApi, PnIoCm_IoCrBlockReqApi> getMessageIO() {
        return new PnIoCm_IoCrBlockReqApiIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PnIoCm_IoCrBlockReqApi)) {
            return false;
        }
        PnIoCm_IoCrBlockReqApi that = (PnIoCm_IoCrBlockReqApi) o;
        return
            (getIoDataObjects() == that.getIoDataObjects()) &&
            (getIoCss() == that.getIoCss()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getIoDataObjects(),
            getIoCss()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("ioDataObjects", getIoDataObjects())
            .append("ioCss", getIoCss())
            .toString();
    }

}