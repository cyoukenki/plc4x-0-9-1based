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

public class PubSubConfigurationDataTypeIO implements MessageIO<PubSubConfigurationDataType, PubSubConfigurationDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubSubConfigurationDataTypeIO.class);

    @Override
    public PubSubConfigurationDataType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (PubSubConfigurationDataType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, PubSubConfigurationDataType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static PubSubConfigurationDataTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("PubSubConfigurationDataType");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (noOfPublishedDataSets)
int noOfPublishedDataSets = readBuffer.readInt("noOfPublishedDataSets", 32) ;        // Array field (publishedDataSets)
        readBuffer.pullContext("publishedDataSets", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfPublishedDataSets > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfPublishedDataSets) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] publishedDataSets;
        {
            int itemCount = Math.max(0, (int) noOfPublishedDataSets);
            publishedDataSets = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
publishedDataSets[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(15580) ) ;            }
        }
            readBuffer.closeContext("publishedDataSets", WithReaderWriterArgs.WithRenderAsList(true));


        // Simple Field (noOfConnections)
int noOfConnections = readBuffer.readInt("noOfConnections", 32) ;        // Array field (connections)
        readBuffer.pullContext("connections", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfConnections > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfConnections) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] connections;
        {
            int itemCount = Math.max(0, (int) noOfConnections);
            connections = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
connections[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(15619) ) ;            }
        }
            readBuffer.closeContext("connections", WithReaderWriterArgs.WithRenderAsList(true));

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 7);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (enabled)
boolean enabled = readBuffer.readBit("enabled") ;
        readBuffer.closeContext("PubSubConfigurationDataType");
        // Create the instance
        return new PubSubConfigurationDataTypeBuilder(noOfPublishedDataSets, publishedDataSets, noOfConnections, connections, enabled);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, PubSubConfigurationDataType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("PubSubConfigurationDataType");

        // Simple Field (noOfPublishedDataSets)
        int noOfPublishedDataSets = (int) _value.getNoOfPublishedDataSets();
        writeBuffer.writeInt("noOfPublishedDataSets", 32, ((Number) (noOfPublishedDataSets)).intValue());

        // Array Field (publishedDataSets)
        if(_value.getPublishedDataSets() != null) {
            writeBuffer.pushContext("publishedDataSets", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getPublishedDataSets().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getPublishedDataSets()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("publishedDataSets", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (noOfConnections)
        int noOfConnections = (int) _value.getNoOfConnections();
        writeBuffer.writeInt("noOfConnections", 32, ((Number) (noOfConnections)).intValue());

        // Array Field (connections)
        if(_value.getConnections() != null) {
            writeBuffer.pushContext("connections", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getConnections().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getConnections()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("connections", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 7, ((Number) (short) 0x00).shortValue());

        // Simple Field (enabled)
        boolean enabled = (boolean) _value.getEnabled();
        writeBuffer.writeBit("enabled", (boolean) (enabled));
        writeBuffer.popContext("PubSubConfigurationDataType");
    }

    public static class PubSubConfigurationDataTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final int noOfPublishedDataSets;
        private final ExtensionObjectDefinition[] publishedDataSets;
        private final int noOfConnections;
        private final ExtensionObjectDefinition[] connections;
        private final boolean enabled;

        public PubSubConfigurationDataTypeBuilder(int noOfPublishedDataSets, ExtensionObjectDefinition[] publishedDataSets, int noOfConnections, ExtensionObjectDefinition[] connections, boolean enabled) {
            this.noOfPublishedDataSets = noOfPublishedDataSets;
            this.publishedDataSets = publishedDataSets;
            this.noOfConnections = noOfConnections;
            this.connections = connections;
            this.enabled = enabled;
        }

        public PubSubConfigurationDataType build() {
            return new PubSubConfigurationDataType(noOfPublishedDataSets, publishedDataSets, noOfConnections, connections, enabled);
        }
    }

}