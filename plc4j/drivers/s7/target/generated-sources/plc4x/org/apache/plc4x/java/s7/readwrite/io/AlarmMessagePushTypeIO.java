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

public class AlarmMessagePushTypeIO implements MessageIO<AlarmMessagePushType, AlarmMessagePushType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmMessagePushTypeIO.class);

    @Override
    public AlarmMessagePushType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return AlarmMessagePushTypeIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AlarmMessagePushType value, Object... args) throws ParseException {
        AlarmMessagePushTypeIO.staticSerialize(writeBuffer, value);
    }

    public static AlarmMessagePushType staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AlarmMessagePushType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("TimeStamp");

        // Simple Field (TimeStamp)
DateAndTime TimeStamp = DateAndTimeIO.staticParse(readBuffer ) ;        readBuffer.closeContext("TimeStamp");


        // Simple Field (functionId)
short functionId = readBuffer.readUnsignedShort("functionId", 8) ;

        // Simple Field (numberOfObjects)
short numberOfObjects = readBuffer.readUnsignedShort("numberOfObjects", 8) ;        // Array field (messageObjects)
        readBuffer.pullContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(numberOfObjects > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (numberOfObjects) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        AlarmMessageObjectPushType[] messageObjects;
        {
            int itemCount = Math.max(0, (int) numberOfObjects);
            messageObjects = new AlarmMessageObjectPushType[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
messageObjects[curItem] = AlarmMessageObjectPushTypeIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("AlarmMessagePushType");
        // Create the instance
        return new AlarmMessagePushType(TimeStamp, functionId, numberOfObjects, messageObjects);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AlarmMessagePushType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AlarmMessagePushType");

        // Simple Field (TimeStamp)
        DateAndTime TimeStamp = (DateAndTime) _value.getTimeStamp();
        writeBuffer.pushContext("TimeStamp");
        DateAndTimeIO.staticSerialize(writeBuffer, TimeStamp);
        writeBuffer.popContext("TimeStamp");

        // Simple Field (functionId)
        short functionId = (short) _value.getFunctionId();
        writeBuffer.writeUnsignedShort("functionId", 8, ((Number) (functionId)).shortValue());

        // Simple Field (numberOfObjects)
        short numberOfObjects = (short) _value.getNumberOfObjects();
        writeBuffer.writeUnsignedShort("numberOfObjects", 8, ((Number) (numberOfObjects)).shortValue());

        // Array Field (messageObjects)
        if(_value.getMessageObjects() != null) {
            writeBuffer.pushContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getMessageObjects().length;
            int curItem = 0;
            for(AlarmMessageObjectPushType element : _value.getMessageObjects()) {
                boolean lastItem = curItem == (itemCount - 1);
                AlarmMessageObjectPushTypeIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("AlarmMessagePushType");
    }

}