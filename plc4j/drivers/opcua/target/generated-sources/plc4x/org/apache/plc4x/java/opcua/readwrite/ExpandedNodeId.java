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

public class ExpandedNodeId implements Message {


    // Properties.
    private final boolean namespaceURISpecified;
    private final boolean serverIndexSpecified;
    private final NodeIdTypeDefinition nodeId;
    private final PascalString namespaceURI;
    private final Long serverIndex;

    public ExpandedNodeId(boolean namespaceURISpecified, boolean serverIndexSpecified, NodeIdTypeDefinition nodeId, PascalString namespaceURI, Long serverIndex) {
        this.namespaceURISpecified = namespaceURISpecified;
        this.serverIndexSpecified = serverIndexSpecified;
        this.nodeId = nodeId;
        this.namespaceURI = namespaceURI;
        this.serverIndex = serverIndex;
    }

    public boolean getNamespaceURISpecified() {
        return namespaceURISpecified;
    }

    public boolean getServerIndexSpecified() {
        return serverIndexSpecified;
    }

    public NodeIdTypeDefinition getNodeId() {
        return nodeId;
    }

    public PascalString getNamespaceURI() {
        return namespaceURI;
    }

    public Long getServerIndex() {
        return serverIndex;
    }

    public String getIdentifier() {
        return String.valueOf(nodeId.getIdentifier());
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
        ExpandedNodeId _value  = this;

        // Simple field (namespaceURISpecified)
        lengthInBits += 1;

        // Simple field (serverIndexSpecified)
        lengthInBits += 1;

        // Simple field (nodeId)
        lengthInBits += nodeId.getLengthInBits();

        // A virtual field doesn't have any in- or output.

        // Optional Field (namespaceURI)
        if(namespaceURI != null) {
            lengthInBits += namespaceURI.getLengthInBits();
        }

        // Optional Field (serverIndex)
        if(serverIndex != null) {
            lengthInBits += 32;
        }

        return lengthInBits;
    }

    @Override
    public MessageIO<ExpandedNodeId, ExpandedNodeId> getMessageIO() {
        return new ExpandedNodeIdIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExpandedNodeId)) {
            return false;
        }
        ExpandedNodeId that = (ExpandedNodeId) o;
        return
            (getNamespaceURISpecified() == that.getNamespaceURISpecified()) &&
            (getServerIndexSpecified() == that.getServerIndexSpecified()) &&
            (getNodeId() == that.getNodeId()) &&
            (getNamespaceURI() == that.getNamespaceURI()) &&
            (getServerIndex() == that.getServerIndex()) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getNamespaceURISpecified(),
            getServerIndexSpecified(),
            getNodeId(),
            getNamespaceURI(),
            getServerIndex()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .append("namespaceURISpecified", getNamespaceURISpecified())
            .append("serverIndexSpecified", getServerIndexSpecified())
            .append("nodeId", getNodeId())
            .append("namespaceURI", getNamespaceURI())
            .append("serverIndex", getServerIndex())
            .append("identifier", getIdentifier())
            .toString();
    }

}
