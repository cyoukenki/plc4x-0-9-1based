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
package org.apache.plc4x.java.s7.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.s7.readwrite.*;
import org.apache.plc4x.java.s7.readwrite.io.*;
import org.apache.plc4x.java.s7.readwrite.types.*;
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

public class AssociatedValueTypeIO implements MessageIO<AssociatedValueType, AssociatedValueType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssociatedValueTypeIO.class);

    @Override
    public AssociatedValueType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return AssociatedValueTypeIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AssociatedValueType value, Object... args) throws ParseException {
        AssociatedValueTypeIO.staticSerialize(writeBuffer, value);
    }

    public static AssociatedValueType staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AssociatedValueType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("returnCode");

        // Simple Field (returnCode)
        // enum based simple field with type DataTransportErrorCode
        DataTransportErrorCode returnCode = DataTransportErrorCode.enumForValue(readBuffer.readUnsignedShort("DataTransportErrorCode", 8));
        readBuffer.closeContext("returnCode");

        readBuffer.pullContext("transportSize");

        // Simple Field (transportSize)
        // enum based simple field with type DataTransportSize
        DataTransportSize transportSize = DataTransportSize.enumForValue(readBuffer.readUnsignedShort("DataTransportSize", 8));
        readBuffer.closeContext("transportSize");

        // Manual Field (valueLength)
        int valueLength = (int) (org.apache.plc4x.java.s7.utils.S7EventHelper.RightShift3(readBuffer));
        // Array field (data)
        readBuffer.pullContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(org.apache.plc4x.java.s7.utils.S7EventHelper.EventItemLength(readBuffer, valueLength) > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (org.apache.plc4x.java.s7.utils.S7EventHelper.EventItemLength(readBuffer, valueLength)) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        short[] data;
        {
            int itemCount = Math.max(0, (int) org.apache.plc4x.java.s7.utils.S7EventHelper.EventItemLength(readBuffer, valueLength));
            data = new short[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
data[curItem] = readBuffer.readUnsignedShort("", 8) ;            }
        }
            readBuffer.closeContext("data", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("AssociatedValueType");
        // Create the instance
        return new AssociatedValueType(returnCode, transportSize, valueLength, data);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AssociatedValueType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AssociatedValueType");

        // Simple Field (returnCode)
        DataTransportErrorCode returnCode = (DataTransportErrorCode) _value.getReturnCode();
        writeBuffer.pushContext("returnCode");
        // enum field with type DataTransportErrorCode
        writeBuffer.writeUnsignedShort("DataTransportErrorCode", 8, ((Number) (returnCode.getValue())).shortValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(returnCode.name()));
        writeBuffer.popContext("returnCode");

        // Simple Field (transportSize)
        DataTransportSize transportSize = (DataTransportSize) _value.getTransportSize();
        writeBuffer.pushContext("transportSize");
        // enum field with type DataTransportSize
        writeBuffer.writeUnsignedShort("DataTransportSize", 8, ((Number) (transportSize.getValue())).shortValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(transportSize.name()));
        writeBuffer.popContext("transportSize");

        // Manual Field (valueLength)
        org.apache.plc4x.java.s7.utils.S7EventHelper.LeftShift3(writeBuffer, _value.getValueLength());

        // Array Field (data)
        if(_value.getData() != null) {
            writeBuffer.pushContext("data", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getData().length;
            int curItem = 0;
            for(short element : _value.getData()) {
                writeBuffer.writeUnsignedShort("", 8, ((Number) element).shortValue());
                curItem++;
            }
            writeBuffer.popContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("AssociatedValueType");
    }

}
