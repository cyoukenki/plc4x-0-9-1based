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

public class PnIoCm_ModuleDiffBlockApi_Module implements Message {


    // Properties.
    private final int slotNumber;
    private final long moduleIdentNumber;
    private final PnIoCm_ModuleState moduleState;
    private final PnIoCm_ModuleDiffBlockApi_Submodule[] submodules;

    public PnIoCm_ModuleDiffBlockApi_Module(int slotNumber, long moduleIdentNumber, PnIoCm_ModuleState moduleState, PnIoCm_ModuleDiffBlockApi_Submodule[] submodules) {
        this.slotNumber = slotNumber;
        this.moduleIdentNumber = moduleIdentNumber;
        this.moduleState = moduleState;
        this.submodules = submodules;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public long getModuleIdentNumber() {
        return moduleIdentNumber;
    }

    public PnIoCm_ModuleState getModuleState() {
        return moduleState;
    }

    public PnIoCm_ModuleDiffBlockApi_Submodule[] getSubmodules() {
        return submodules;
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
        PnIoCm_ModuleDiffBlockApi_Module _value  = this;

        // Simple field (slotNumber)
        lengthInBits += 16;

        // Simple field (moduleIdentNumber)
        lengthInBits += 32;

        // Simple field (moduleState)
        lengthInBits += 16;

        // Implicit Field (numSubmodules)
        lengthInBits += 16;
        //int numSubmodules = (int) (COUNT(_value.getSubmodules()));

        // Array field
        if(submodules != null) {
            int i=0;
            for(PnIoCm_ModuleDiffBlockApi_Submodule element : submodules) {
                boolean last = ++i >= submodules.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<PnIoCm_ModuleDiffBlockApi_Module, PnIoCm_ModuleDiffBlockApi_Module> getMessageIO() {
        return new PnIoCm_ModuleDiffBlockApi_ModuleIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PnIoCm_ModuleDiffBlockApi_Module)) {
            return false;
        }
        PnIoCm_ModuleDiffBlockApi_Module that = (PnIoCm_ModuleDiffBlockApi_Module) o;
        return
            (getSlotNumber() == that.getSlotNumber()) &&
            (getModuleIdentNumber() == that.getModuleIdentNumber()) &&
            (getModuleState() == that.getModuleState()) &&
            (getSubmodules() == that.getSubmodules()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getSlotNumber(),
            getModuleIdentNumber(),
            getModuleState(),
            getSubmodules()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("slotNumber", getSlotNumber())
            .append("moduleIdentNumber", getModuleIdentNumber())
            .append("moduleState", getModuleState())
            .append("submodules", getSubmodules())
            .toString();
    }

}
