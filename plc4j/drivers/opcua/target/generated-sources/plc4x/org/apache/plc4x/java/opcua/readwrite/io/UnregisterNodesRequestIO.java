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

public class UnregisterNodesRequestIO implements MessageIO<UnregisterNodesRequest, UnregisterNodesRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnregisterNodesRequestIO.class);

    @Override
    public UnregisterNodesRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (UnregisterNodesRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, UnregisterNodesRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static UnregisterNodesRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("UnregisterNodesRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");


        // Simple Field (noOfNodesToUnregister)
int noOfNodesToUnregister = readBuffer.readInt("noOfNodesToUnregister", 32) ;        // Array field (nodesToUnregister)
        readBuffer.pullContext("nodesToUnregister", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfNodesToUnregister > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfNodesToUnregister) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        NodeId[] nodesToUnregister;
        {
            int itemCount = Math.max(0, (int) noOfNodesToUnregister);
            nodesToUnregister = new NodeId[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
nodesToUnregister[curItem] = NodeIdIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("nodesToUnregister", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("UnregisterNodesRequest");
        // Create the instance
        return new UnregisterNodesRequestBuilder(requestHeader, noOfNodesToUnregister, nodesToUnregister);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, UnregisterNodesRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("UnregisterNodesRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (noOfNodesToUnregister)
        int noOfNodesToUnregister = (int) _value.getNoOfNodesToUnregister();
        writeBuffer.writeInt("noOfNodesToUnregister", 32, ((Number) (noOfNodesToUnregister)).intValue());

        // Array Field (nodesToUnregister)
        if(_value.getNodesToUnregister() != null) {
            writeBuffer.pushContext("nodesToUnregister", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getNodesToUnregister().length;
            int curItem = 0;
            for(NodeId element : _value.getNodesToUnregister()) {
                boolean lastItem = curItem == (itemCount - 1);
                NodeIdIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("nodesToUnregister", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("UnregisterNodesRequest");
    }

    public static class UnregisterNodesRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final int noOfNodesToUnregister;
        private final NodeId[] nodesToUnregister;

        public UnregisterNodesRequestBuilder(ExtensionObjectDefinition requestHeader, int noOfNodesToUnregister, NodeId[] nodesToUnregister) {
            this.requestHeader = requestHeader;
            this.noOfNodesToUnregister = noOfNodesToUnregister;
            this.nodesToUnregister = nodesToUnregister;
        }

        public UnregisterNodesRequest build() {
            return new UnregisterNodesRequest(requestHeader, noOfNodesToUnregister, nodesToUnregister);
        }
    }

}