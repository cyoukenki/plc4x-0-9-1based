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

public class NetworkGroupDataType extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "11946";
    }

    // Properties.
    private final PascalString serverUri;
    private final int noOfNetworkPaths;
    private final ExtensionObjectDefinition[] networkPaths;

    public NetworkGroupDataType(PascalString serverUri, int noOfNetworkPaths, ExtensionObjectDefinition[] networkPaths) {
        this.serverUri = serverUri;
        this.noOfNetworkPaths = noOfNetworkPaths;
        this.networkPaths = networkPaths;
    }

    public PascalString getServerUri() {
        return serverUri;
    }

    public int getNoOfNetworkPaths() {
        return noOfNetworkPaths;
    }

    public ExtensionObjectDefinition[] getNetworkPaths() {
        return networkPaths;
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
        NetworkGroupDataType _value  = this;

        // Simple field (serverUri)
        lengthInBits += serverUri.getLengthInBits();

        // Simple field (noOfNetworkPaths)
        lengthInBits += 32;

        // Array field
        if(networkPaths != null) {
            int i=0;
            for(ExtensionObjectDefinition element : networkPaths) {
                boolean last = ++i >= networkPaths.length;
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
        if (!(o instanceof NetworkGroupDataType)) {
            return false;
        }
        NetworkGroupDataType that = (NetworkGroupDataType) o;
        return
            (getServerUri() == that.getServerUri()) &&
            (getNoOfNetworkPaths() == that.getNoOfNetworkPaths()) &&
            (getNetworkPaths() == that.getNetworkPaths()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getServerUri(),
            getNoOfNetworkPaths(),
            getNetworkPaths()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("serverUri", getServerUri())
            .append("noOfNetworkPaths", getNoOfNetworkPaths())
            .append("networkPaths", getNetworkPaths())
            .toString();
    }

}