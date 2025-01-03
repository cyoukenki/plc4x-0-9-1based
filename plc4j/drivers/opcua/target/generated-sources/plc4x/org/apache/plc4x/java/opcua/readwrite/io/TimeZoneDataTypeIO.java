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

public class TimeZoneDataTypeIO implements MessageIO<TimeZoneDataType, TimeZoneDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeZoneDataTypeIO.class);

    @Override
    public TimeZoneDataType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (TimeZoneDataType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, TimeZoneDataType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static TimeZoneDataTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("TimeZoneDataType");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (offset)
short offset = readBuffer.readShort("offset", 16) ;
        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 7);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (daylightSavingInOffset)
boolean daylightSavingInOffset = readBuffer.readBit("daylightSavingInOffset") ;
        readBuffer.closeContext("TimeZoneDataType");
        // Create the instance
        return new TimeZoneDataTypeBuilder(offset, daylightSavingInOffset);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, TimeZoneDataType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("TimeZoneDataType");

        // Simple Field (offset)
        short offset = (short) _value.getOffset();
        writeBuffer.writeShort("offset", 16, ((Number) (offset)).shortValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 7, ((Number) (short) 0x00).shortValue());

        // Simple Field (daylightSavingInOffset)
        boolean daylightSavingInOffset = (boolean) _value.getDaylightSavingInOffset();
        writeBuffer.writeBit("daylightSavingInOffset", (boolean) (daylightSavingInOffset));
        writeBuffer.popContext("TimeZoneDataType");
    }

    public static class TimeZoneDataTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final short offset;
        private final boolean daylightSavingInOffset;

        public TimeZoneDataTypeBuilder(short offset, boolean daylightSavingInOffset) {
            this.offset = offset;
            this.daylightSavingInOffset = daylightSavingInOffset;
        }

        public TimeZoneDataType build() {
            return new TimeZoneDataType(offset, daylightSavingInOffset);
        }
    }

}
