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

public class SzlDataTreeItem implements Message {


    // Properties.
    private final int itemIndex;
    private final byte[] mlfb;
    private final int moduleTypeId;
    private final int ausbg;
    private final int ausbe;

    public SzlDataTreeItem(int itemIndex, byte[] mlfb, int moduleTypeId, int ausbg, int ausbe) {
        this.itemIndex = itemIndex;
        this.mlfb = mlfb;
        this.moduleTypeId = moduleTypeId;
        this.ausbg = ausbg;
        this.ausbe = ausbe;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public byte[] getMlfb() {
        return mlfb;
    }

    public int getModuleTypeId() {
        return moduleTypeId;
    }

    public int getAusbg() {
        return ausbg;
    }

    public int getAusbe() {
        return ausbe;
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
        SzlDataTreeItem _value  = this;

        // Simple field (itemIndex)
        lengthInBits += 16;

        // Array field
        if(mlfb != null) {
            lengthInBits += 8 * mlfb.length;
        }

        // Simple field (moduleTypeId)
        lengthInBits += 16;

        // Simple field (ausbg)
        lengthInBits += 16;

        // Simple field (ausbe)
        lengthInBits += 16;

        return lengthInBits;
    }

    @Override
    public MessageIO<SzlDataTreeItem, SzlDataTreeItem> getMessageIO() {
        return new SzlDataTreeItemIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SzlDataTreeItem)) {
            return false;
        }
        SzlDataTreeItem that = (SzlDataTreeItem) o;
        return
            (getItemIndex() == that.getItemIndex()) &&
            (getMlfb() == that.getMlfb()) &&
            (getModuleTypeId() == that.getModuleTypeId()) &&
            (getAusbg() == that.getAusbg()) &&
            (getAusbe() == that.getAusbe()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getItemIndex(),
            getMlfb(),
            getModuleTypeId(),
            getAusbg(),
            getAusbe()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("itemIndex", getItemIndex())
            .append("mlfb", getMlfb())
            .append("moduleTypeId", getModuleTypeId())
            .append("ausbg", getAusbg())
            .append("ausbe", getAusbe())
            .toString();
    }

}