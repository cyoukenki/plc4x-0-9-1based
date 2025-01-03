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

public abstract class PnIoCm_Submodule implements Message {

    // Abstract accessors for discriminator values.
    public abstract PnIoCm_SubmoduleType getSubmoduleType();

    // Properties.
    private final int slotNumber;
    private final long submoduleIdentNumber;
    private final boolean discardIoxs;
    private final boolean reduceOutputModuleDataLength;
    private final boolean reduceInputModuleDataLength;
    private final boolean sharedInput;

    public PnIoCm_Submodule(int slotNumber, long submoduleIdentNumber, boolean discardIoxs, boolean reduceOutputModuleDataLength, boolean reduceInputModuleDataLength, boolean sharedInput) {
        this.slotNumber = slotNumber;
        this.submoduleIdentNumber = submoduleIdentNumber;
        this.discardIoxs = discardIoxs;
        this.reduceOutputModuleDataLength = reduceOutputModuleDataLength;
        this.reduceInputModuleDataLength = reduceInputModuleDataLength;
        this.sharedInput = sharedInput;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public long getSubmoduleIdentNumber() {
        return submoduleIdentNumber;
    }

    public boolean getDiscardIoxs() {
        return discardIoxs;
    }

    public boolean getReduceOutputModuleDataLength() {
        return reduceOutputModuleDataLength;
    }

    public boolean getReduceInputModuleDataLength() {
        return reduceInputModuleDataLength;
    }

    public boolean getSharedInput() {
        return sharedInput;
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
        PnIoCm_Submodule _value  = this;

        // Simple field (slotNumber)
        lengthInBits += 16;

        // Simple field (submoduleIdentNumber)
        lengthInBits += 32;

        // Reserved Field (reserved)
        lengthInBits += 10;

        // Simple field (discardIoxs)
        lengthInBits += 1;

        // Simple field (reduceOutputModuleDataLength)
        lengthInBits += 1;

        // Simple field (reduceInputModuleDataLength)
        lengthInBits += 1;

        // Simple field (sharedInput)
        lengthInBits += 1;

        // Discriminator Field (submoduleType)
            lengthInBits += 2;

        // Length of sub-type elements will be added by sub-type...

        return lengthInBits;
    }

    @Override
    public MessageIO<PnIoCm_Submodule, PnIoCm_Submodule> getMessageIO() {
        return new PnIoCm_SubmoduleIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PnIoCm_Submodule)) {
            return false;
        }
        PnIoCm_Submodule that = (PnIoCm_Submodule) o;
        return
            (getSlotNumber() == that.getSlotNumber()) &&
            (getSubmoduleIdentNumber() == that.getSubmoduleIdentNumber()) &&
            (getDiscardIoxs() == that.getDiscardIoxs()) &&
            (getReduceOutputModuleDataLength() == that.getReduceOutputModuleDataLength()) &&
            (getReduceInputModuleDataLength() == that.getReduceInputModuleDataLength()) &&
            (getSharedInput() == that.getSharedInput()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getSlotNumber(),
            getSubmoduleIdentNumber(),
            getDiscardIoxs(),
            getReduceOutputModuleDataLength(),
            getReduceInputModuleDataLength(),
            getSharedInput()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("slotNumber", getSlotNumber())
            .append("submoduleIdentNumber", getSubmoduleIdentNumber())
            .append("discardIoxs", getDiscardIoxs())
            .append("reduceOutputModuleDataLength", getReduceOutputModuleDataLength())
            .append("reduceInputModuleDataLength", getReduceInputModuleDataLength())
            .append("sharedInput", getSharedInput())
            .toString();
    }

}
