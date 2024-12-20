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
package org.apache.plc4x.java.eip.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.eip.readwrite.io.*;
import org.apache.plc4x.java.eip.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class CipExchange implements Message {


    // Constant values.
    public static final int ITEMCOUNT = 0x02;
    public static final long NULLPTR = 0x0;
    public static final int UNCONNECTEDDATA = 0x00B2;

    // Properties.
    private final CipService service;

    public CipExchange(CipService service) {
        this.service = service;
    }

    public CipService getService() {
        return service;
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
        CipExchange _value  = this;

        // Const Field (itemCount)
        lengthInBits += 16;

        // Const Field (nullPtr)
        lengthInBits += 32;

        // Const Field (UnconnectedData)
        lengthInBits += 16;

        // Implicit Field (size)
        lengthInBits += 16;
        //int size = (int) (((_value.getLengthInBytes()) - (8)) - (2));

        // Simple field (service)
        lengthInBits += service.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<CipExchange, CipExchange> getMessageIO() {
        return new CipExchangeIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CipExchange)) {
            return false;
        }
        CipExchange that = (CipExchange) o;
        return
            (getService() == that.getService()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getService()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("service", getService())
            .toString();
    }

}
