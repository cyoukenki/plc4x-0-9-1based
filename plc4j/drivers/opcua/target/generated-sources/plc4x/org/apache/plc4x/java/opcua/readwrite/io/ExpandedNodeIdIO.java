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

public class ExpandedNodeIdIO implements MessageIO<ExpandedNodeId, ExpandedNodeId> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpandedNodeIdIO.class);

    @Override
    public ExpandedNodeId parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return ExpandedNodeIdIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ExpandedNodeId value, Object... args) throws ParseException {
        ExpandedNodeIdIO.staticSerialize(writeBuffer, value);
    }

    public static ExpandedNodeId staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("ExpandedNodeId");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (namespaceURISpecified)
boolean namespaceURISpecified = readBuffer.readBit("namespaceURISpecified") ;

        // Simple Field (serverIndexSpecified)
boolean serverIndexSpecified = readBuffer.readBit("serverIndexSpecified") ;
        readBuffer.pullContext("nodeId");

        // Simple Field (nodeId)
NodeIdTypeDefinition nodeId = NodeIdTypeDefinitionIO.staticParse(readBuffer ) ;        readBuffer.closeContext("nodeId");

        // Virtual field (Just declare a local variable so we can access it in the parser)
        String identifier = String.valueOf(nodeId.getIdentifier());

        // Optional Field (namespaceURI) (Can be skipped, if a given expression evaluates to false)
        PascalString namespaceURI = null;
        if(namespaceURISpecified) {
            namespaceURI = PascalStringIO.staticParse(readBuffer);
        }

        // Optional Field (serverIndex) (Can be skipped, if a given expression evaluates to false)
        Long serverIndex = null;
        if(serverIndexSpecified) {
            serverIndex = readBuffer.readUnsignedLong("serverIndex", 32);
        }

        readBuffer.closeContext("ExpandedNodeId");
        // Create the instance
        return new ExpandedNodeId(namespaceURISpecified, serverIndexSpecified, nodeId, namespaceURI, serverIndex);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ExpandedNodeId _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ExpandedNodeId");

        // Simple Field (namespaceURISpecified)
        boolean namespaceURISpecified = (boolean) _value.getNamespaceURISpecified();
        writeBuffer.writeBit("namespaceURISpecified", (boolean) (namespaceURISpecified));

        // Simple Field (serverIndexSpecified)
        boolean serverIndexSpecified = (boolean) _value.getServerIndexSpecified();
        writeBuffer.writeBit("serverIndexSpecified", (boolean) (serverIndexSpecified));

        // Simple Field (nodeId)
        NodeIdTypeDefinition nodeId = (NodeIdTypeDefinition) _value.getNodeId();
        writeBuffer.pushContext("nodeId");
        NodeIdTypeDefinitionIO.staticSerialize(writeBuffer, nodeId);
        writeBuffer.popContext("nodeId");

        // Optional Field (namespaceURI) (Can be skipped, if the value is null)
        PascalString namespaceURI = null;
        if(_value.getNamespaceURI() != null) {
            namespaceURI = (PascalString) _value.getNamespaceURI();
            PascalStringIO.staticSerialize(writeBuffer, namespaceURI);
        }

        // Optional Field (serverIndex) (Can be skipped, if the value is null)
        Long serverIndex = null;
        if(_value.getServerIndex() != null) {
            serverIndex = (Long) _value.getServerIndex();
            writeBuffer.writeUnsignedLong("serverIndex", 32, ((Number) (serverIndex)).longValue());
        }
        writeBuffer.popContext("ExpandedNodeId");
    }

}