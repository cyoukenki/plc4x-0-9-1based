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
package org.apache.plc4x.java.opcua.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.io.*;
import org.apache.plc4x.java.opcua.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class DeleteNodesRequest extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "500";
    }

    // Properties.
    private final ExtensionObjectDefinition requestHeader;
    private final int noOfNodesToDelete;
    private final ExtensionObjectDefinition[] nodesToDelete;

    public DeleteNodesRequest(ExtensionObjectDefinition requestHeader, int noOfNodesToDelete, ExtensionObjectDefinition[] nodesToDelete) {
        this.requestHeader = requestHeader;
        this.noOfNodesToDelete = noOfNodesToDelete;
        this.nodesToDelete = nodesToDelete;
    }

    public ExtensionObjectDefinition getRequestHeader() {
        return requestHeader;
    }

    public int getNoOfNodesToDelete() {
        return noOfNodesToDelete;
    }

    public ExtensionObjectDefinition[] getNodesToDelete() {
        return nodesToDelete;
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
        DeleteNodesRequest _value  = this;

        // Simple field (requestHeader)
        lengthInBits += requestHeader.getLengthInBits();

        // Simple field (noOfNodesToDelete)
        lengthInBits += 32;

        // Array field
        if(nodesToDelete != null) {
            int i=0;
            for(ExtensionObjectDefinition element : nodesToDelete) {
                boolean last = ++i >= nodesToDelete.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<ExtensionObjectDefinition, ExtensionObjectDefinition> getMessageIO() {
        return new ExtensionObjectDefinitionIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeleteNodesRequest)) {
            return false;
        }
        DeleteNodesRequest that = (DeleteNodesRequest) o;
        return
            (getRequestHeader() == that.getRequestHeader()) &&
            (getNoOfNodesToDelete() == that.getNoOfNodesToDelete()) &&
            (getNodesToDelete() == that.getNodesToDelete()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getRequestHeader(),
            getNoOfNodesToDelete(),
            getNodesToDelete()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("requestHeader", getRequestHeader())
            .append("noOfNodesToDelete", getNoOfNodesToDelete())
            .append("nodesToDelete", getNodesToDelete())
            .toString();
    }

}