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
package org.apache.plc4x.java.opcua.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.*;
import org.apache.plc4x.java.opcua.readwrite.io.*;
import org.apache.plc4x.java.opcua.readwrite.types.*;
import org.apache.plc4x.java.api.exceptions.PlcRuntimeException;
import org.apache.plc4x.java.spi.generation.*;
import org.apache.plc4x.java.api.value.PlcValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.time.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

// Code generated by code-generation. DO NOT EDIT.

public class NetworkGroupDataTypeIO implements MessageIO<NetworkGroupDataType, NetworkGroupDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkGroupDataTypeIO.class);

    @Override
    public NetworkGroupDataType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (NetworkGroupDataType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, NetworkGroupDataType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static NetworkGroupDataTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("NetworkGroupDataType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("serverUri");

        // Simple Field (serverUri)
PascalString serverUri = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("serverUri");


        // Simple Field (noOfNetworkPaths)
int noOfNetworkPaths = readBuffer.readInt("noOfNetworkPaths", 32) ;        // Array field (networkPaths)
        readBuffer.pullContext("networkPaths", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfNetworkPaths > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfNetworkPaths) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] networkPaths;
        {
            int itemCount = Math.max(0, (int) noOfNetworkPaths);
            networkPaths = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
networkPaths[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(11945) ) ;            }
        }
            readBuffer.closeContext("networkPaths", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("NetworkGroupDataType");
        // Create the instance
        return new NetworkGroupDataTypeBuilder(serverUri, noOfNetworkPaths, networkPaths);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, NetworkGroupDataType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("NetworkGroupDataType");

        // Simple Field (serverUri)
        PascalString serverUri = (PascalString) _value.getServerUri();
        writeBuffer.pushContext("serverUri");
        PascalStringIO.staticSerialize(writeBuffer, serverUri);
        writeBuffer.popContext("serverUri");

        // Simple Field (noOfNetworkPaths)
        int noOfNetworkPaths = (int) _value.getNoOfNetworkPaths();
        writeBuffer.writeInt("noOfNetworkPaths", 32, ((Number) (noOfNetworkPaths)).intValue());

        // Array Field (networkPaths)
        if(_value.getNetworkPaths() != null) {
            writeBuffer.pushContext("networkPaths", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getNetworkPaths().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getNetworkPaths()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("networkPaths", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("NetworkGroupDataType");
    }

    public static class NetworkGroupDataTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final PascalString serverUri;
        private final int noOfNetworkPaths;
        private final ExtensionObjectDefinition[] networkPaths;

        public NetworkGroupDataTypeBuilder(PascalString serverUri, int noOfNetworkPaths, ExtensionObjectDefinition[] networkPaths) {
            this.serverUri = serverUri;
            this.noOfNetworkPaths = noOfNetworkPaths;
            this.networkPaths = networkPaths;
        }

        public NetworkGroupDataType build() {
            return new NetworkGroupDataType(serverUri, noOfNetworkPaths, networkPaths);
        }
    }

}