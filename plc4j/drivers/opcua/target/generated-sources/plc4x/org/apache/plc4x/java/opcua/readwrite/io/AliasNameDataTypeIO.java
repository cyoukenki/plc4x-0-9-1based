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

public class AliasNameDataTypeIO implements MessageIO<AliasNameDataType, AliasNameDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliasNameDataTypeIO.class);

    @Override
    public AliasNameDataType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (AliasNameDataType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AliasNameDataType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static AliasNameDataTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AliasNameDataType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("aliasName");

        // Simple Field (aliasName)
QualifiedName aliasName = QualifiedNameIO.staticParse(readBuffer ) ;        readBuffer.closeContext("aliasName");


        // Simple Field (noOfReferencedNodes)
int noOfReferencedNodes = readBuffer.readInt("noOfReferencedNodes", 32) ;        // Array field (referencedNodes)
        readBuffer.pullContext("referencedNodes", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfReferencedNodes > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfReferencedNodes) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExpandedNodeId[] referencedNodes;
        {
            int itemCount = Math.max(0, (int) noOfReferencedNodes);
            referencedNodes = new ExpandedNodeId[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
referencedNodes[curItem] = ExpandedNodeIdIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("referencedNodes", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("AliasNameDataType");
        // Create the instance
        return new AliasNameDataTypeBuilder(aliasName, noOfReferencedNodes, referencedNodes);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AliasNameDataType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AliasNameDataType");

        // Simple Field (aliasName)
        QualifiedName aliasName = (QualifiedName) _value.getAliasName();
        writeBuffer.pushContext("aliasName");
        QualifiedNameIO.staticSerialize(writeBuffer, aliasName);
        writeBuffer.popContext("aliasName");

        // Simple Field (noOfReferencedNodes)
        int noOfReferencedNodes = (int) _value.getNoOfReferencedNodes();
        writeBuffer.writeInt("noOfReferencedNodes", 32, ((Number) (noOfReferencedNodes)).intValue());

        // Array Field (referencedNodes)
        if(_value.getReferencedNodes() != null) {
            writeBuffer.pushContext("referencedNodes", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getReferencedNodes().length;
            int curItem = 0;
            for(ExpandedNodeId element : _value.getReferencedNodes()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExpandedNodeIdIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("referencedNodes", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("AliasNameDataType");
    }

    public static class AliasNameDataTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final QualifiedName aliasName;
        private final int noOfReferencedNodes;
        private final ExpandedNodeId[] referencedNodes;

        public AliasNameDataTypeBuilder(QualifiedName aliasName, int noOfReferencedNodes, ExpandedNodeId[] referencedNodes) {
            this.aliasName = aliasName;
            this.noOfReferencedNodes = noOfReferencedNodes;
            this.referencedNodes = referencedNodes;
        }

        public AliasNameDataType build() {
            return new AliasNameDataType(aliasName, noOfReferencedNodes, referencedNodes);
        }
    }

}