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

public class StructureFieldIO implements MessageIO<StructureField, StructureField> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StructureFieldIO.class);

    @Override
    public StructureField parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (StructureField) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, StructureField value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static StructureFieldBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("StructureField");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("name");

        // Simple Field (name)
PascalString name = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("name");

        readBuffer.pullContext("description");

        // Simple Field (description)
LocalizedText description = LocalizedTextIO.staticParse(readBuffer ) ;        readBuffer.closeContext("description");

        readBuffer.pullContext("dataType");

        // Simple Field (dataType)
NodeId dataType = NodeIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("dataType");


        // Simple Field (valueRank)
int valueRank = readBuffer.readInt("valueRank", 32) ;

        // Simple Field (noOfArrayDimensions)
int noOfArrayDimensions = readBuffer.readInt("noOfArrayDimensions", 32) ;        // Array field (arrayDimensions)
        readBuffer.pullContext("arrayDimensions", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfArrayDimensions > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfArrayDimensions) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        long[] arrayDimensions;
        {
            int itemCount = Math.max(0, (int) noOfArrayDimensions);
            arrayDimensions = new long[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
arrayDimensions[curItem] = readBuffer.readUnsignedLong("", 32) ;            }
        }
            readBuffer.closeContext("arrayDimensions", WithReaderWriterArgs.WithRenderAsList(true));


        // Simple Field (maxStringLength)
long maxStringLength = readBuffer.readUnsignedLong("maxStringLength", 32) ;
        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 7);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (isOptional)
boolean isOptional = readBuffer.readBit("isOptional") ;
        readBuffer.closeContext("StructureField");
        // Create the instance
        return new StructureFieldBuilder(name, description, dataType, valueRank, noOfArrayDimensions, arrayDimensions, maxStringLength, isOptional);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, StructureField _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("StructureField");

        // Simple Field (name)
        PascalString name = (PascalString) _value.getName();
        writeBuffer.pushContext("name");
        PascalStringIO.staticSerialize(writeBuffer, name);
        writeBuffer.popContext("name");

        // Simple Field (description)
        LocalizedText description = (LocalizedText) _value.getDescription();
        writeBuffer.pushContext("description");
        LocalizedTextIO.staticSerialize(writeBuffer, description);
        writeBuffer.popContext("description");

        // Simple Field (dataType)
        NodeId dataType = (NodeId) _value.getDataType();
        writeBuffer.pushContext("dataType");
        NodeIdIO.staticSerialize(writeBuffer, dataType);
        writeBuffer.popContext("dataType");

        // Simple Field (valueRank)
        int valueRank = (int) _value.getValueRank();
        writeBuffer.writeInt("valueRank", 32, ((Number) (valueRank)).intValue());

        // Simple Field (noOfArrayDimensions)
        int noOfArrayDimensions = (int) _value.getNoOfArrayDimensions();
        writeBuffer.writeInt("noOfArrayDimensions", 32, ((Number) (noOfArrayDimensions)).intValue());

        // Array Field (arrayDimensions)
        if(_value.getArrayDimensions() != null) {
            writeBuffer.pushContext("arrayDimensions", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getArrayDimensions().length;
            int curItem = 0;
            for(long element : _value.getArrayDimensions()) {
                writeBuffer.writeUnsignedLong("", 32, ((Number) element).longValue());
                curItem++;
            }
            writeBuffer.popContext("arrayDimensions", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (maxStringLength)
        long maxStringLength = (long) _value.getMaxStringLength();
        writeBuffer.writeUnsignedLong("maxStringLength", 32, ((Number) (maxStringLength)).longValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 7, ((Number) (short) 0x00).shortValue());

        // Simple Field (isOptional)
        boolean isOptional = (boolean) _value.getIsOptional();
        writeBuffer.writeBit("isOptional", (boolean) (isOptional));
        writeBuffer.popContext("StructureField");
    }

    public static class StructureFieldBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final PascalString name;
        private final LocalizedText description;
        private final NodeId dataType;
        private final int valueRank;
        private final int noOfArrayDimensions;
        private final long[] arrayDimensions;
        private final long maxStringLength;
        private final boolean isOptional;

        public StructureFieldBuilder(PascalString name, LocalizedText description, NodeId dataType, int valueRank, int noOfArrayDimensions, long[] arrayDimensions, long maxStringLength, boolean isOptional) {
            this.name = name;
            this.description = description;
            this.dataType = dataType;
            this.valueRank = valueRank;
            this.noOfArrayDimensions = noOfArrayDimensions;
            this.arrayDimensions = arrayDimensions;
            this.maxStringLength = maxStringLength;
            this.isOptional = isOptional;
        }

        public StructureField build() {
            return new StructureField(name, description, dataType, valueRank, noOfArrayDimensions, arrayDimensions, maxStringLength, isOptional);
        }
    }

}