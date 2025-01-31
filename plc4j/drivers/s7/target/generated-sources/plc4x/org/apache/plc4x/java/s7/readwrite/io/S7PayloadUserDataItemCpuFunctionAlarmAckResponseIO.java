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

public class S7PayloadUserDataItemCpuFunctionAlarmAckResponseIO implements MessageIO<S7PayloadUserDataItemCpuFunctionAlarmAckResponse, S7PayloadUserDataItemCpuFunctionAlarmAckResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7PayloadUserDataItemCpuFunctionAlarmAckResponseIO.class);

    @Override
    public S7PayloadUserDataItemCpuFunctionAlarmAckResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S7PayloadUserDataItemCpuFunctionAlarmAckResponse) new S7PayloadUserDataItemIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7PayloadUserDataItemCpuFunctionAlarmAckResponse value, Object... args) throws ParseException {
        new S7PayloadUserDataItemIO().serialize(writeBuffer, value, args);
    }

    public static S7PayloadUserDataItemCpuFunctionAlarmAckResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S7PayloadUserDataItemCpuFunctionAlarmAckResponse");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (functionId)
short functionId = readBuffer.readUnsignedShort("functionId", 8) ;
        // Implicit Field (numberOfObjects) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        short numberOfObjects = readBuffer.readUnsignedShort("numberOfObjects", 8);
        // Array field (messageObjects)
        readBuffer.pullContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(numberOfObjects > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (numberOfObjects) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        short[] messageObjects;
        {
            int itemCount = Math.max(0, (int) numberOfObjects);
            messageObjects = new short[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
messageObjects[curItem] = readBuffer.readUnsignedShort("", 8) ;            }
        }
            readBuffer.closeContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("S7PayloadUserDataItemCpuFunctionAlarmAckResponse");
        // Create the instance
        return new S7PayloadUserDataItemCpuFunctionAlarmAckResponseBuilder(functionId, messageObjects);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7PayloadUserDataItemCpuFunctionAlarmAckResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7PayloadUserDataItemCpuFunctionAlarmAckResponse");

        // Simple Field (functionId)
        short functionId = (short) _value.getFunctionId();
        writeBuffer.writeUnsignedShort("functionId", 8, ((Number) (functionId)).shortValue());

        // Implicit Field (numberOfObjects) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        short numberOfObjects = (short) (COUNT(_value.getMessageObjects()));
        writeBuffer.writeUnsignedShort("numberOfObjects", 8, ((Number) (numberOfObjects)).shortValue());

        // Array Field (messageObjects)
        if(_value.getMessageObjects() != null) {
            writeBuffer.pushContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getMessageObjects().length;
            int curItem = 0;
            for(short element : _value.getMessageObjects()) {
                writeBuffer.writeUnsignedShort("", 8, ((Number) element).shortValue());
                curItem++;
            }
            writeBuffer.popContext("messageObjects", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("S7PayloadUserDataItemCpuFunctionAlarmAckResponse");
    }

    public static class S7PayloadUserDataItemCpuFunctionAlarmAckResponseBuilder implements S7PayloadUserDataItemIO.S7PayloadUserDataItemBuilder {
        private final short functionId;
        private final short[] messageObjects;

        public S7PayloadUserDataItemCpuFunctionAlarmAckResponseBuilder(short functionId, short[] messageObjects) {
            this.functionId = functionId;
            this.messageObjects = messageObjects;
        }

        public S7PayloadUserDataItemCpuFunctionAlarmAckResponse build(DataTransportErrorCode returnCode, DataTransportSize transportSize) {
            return new S7PayloadUserDataItemCpuFunctionAlarmAckResponse(returnCode, transportSize, functionId, messageObjects);
        }
    }

}
