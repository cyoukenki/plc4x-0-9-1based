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

public class BrowseDescription extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "516";
    }

    // Properties.
    private final NodeId nodeId;
    private final BrowseDirection browseDirection;
    private final NodeId referenceTypeId;
    private final boolean includeSubtypes;
    private final long nodeClassMask;
    private final long resultMask;

    public BrowseDescription(NodeId nodeId, BrowseDirection browseDirection, NodeId referenceTypeId, boolean includeSubtypes, long nodeClassMask, long resultMask) {
        this.nodeId = nodeId;
        this.browseDirection = browseDirection;
        this.referenceTypeId = referenceTypeId;
        this.includeSubtypes = includeSubtypes;
        this.nodeClassMask = nodeClassMask;
        this.resultMask = resultMask;
    }

    public NodeId getNodeId() {
        return nodeId;
    }

    public BrowseDirection getBrowseDirection() {
        return browseDirection;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public boolean getIncludeSubtypes() {
        return includeSubtypes;
    }

    public long getNodeClassMask() {
        return nodeClassMask;
    }

    public long getResultMask() {
        return resultMask;
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
        BrowseDescription _value  = this;

        // Simple field (nodeId)
        lengthInBits += nodeId.getLengthInBits();

        // Simple field (browseDirection)
        lengthInBits += 32;

        // Simple field (referenceTypeId)
        lengthInBits += referenceTypeId.getLengthInBits();

        // Reserved Field (reserved)
        lengthInBits += 7;

        // Simple field (includeSubtypes)
        lengthInBits += 1;

        // Simple field (nodeClassMask)
        lengthInBits += 32;

        // Simple field (resultMask)
        lengthInBits += 32;

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
        if (!(o instanceof BrowseDescription)) {
            return false;
        }
        BrowseDescription that = (BrowseDescription) o;
        return
            (getNodeId() == that.getNodeId()) &&
            (getBrowseDirection() == that.getBrowseDirection()) &&
            (getReferenceTypeId() == that.getReferenceTypeId()) &&
            (getIncludeSubtypes() == that.getIncludeSubtypes()) &&
            (getNodeClassMask() == that.getNodeClassMask()) &&
            (getResultMask() == that.getResultMask()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getNodeId(),
            getBrowseDirection(),
            getReferenceTypeId(),
            getIncludeSubtypes(),
            getNodeClassMask(),
            getResultMask()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("nodeId", getNodeId())
            .append("browseDirection", getBrowseDirection())
            .append("referenceTypeId", getReferenceTypeId())
            .append("includeSubtypes", getIncludeSubtypes())
            .append("nodeClassMask", getNodeClassMask())
            .append("resultMask", getResultMask())
            .toString();
    }

}
