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
package org.apache.plc4x.java.ads.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.readwrite.*;
import org.apache.plc4x.java.ads.readwrite.io.*;
import org.apache.plc4x.java.ads.readwrite.types.*;
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

public class AmsPacketIO implements MessageIO<AmsPacket, AmsPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmsPacketIO.class);

    @Override
    public AmsPacket parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return AmsPacketIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AmsPacket value, Object... args) throws ParseException {
        AmsPacketIO.staticSerialize(writeBuffer, value);
    }

    public static AmsPacket staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AmsPacket");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("targetAmsNetId");

        // Simple Field (targetAmsNetId)
AmsNetId targetAmsNetId = AmsNetIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("targetAmsNetId");


        // Simple Field (targetAmsPort)
int targetAmsPort = readBuffer.readUnsignedInt("targetAmsPort", 16) ;
        readBuffer.pullContext("sourceAmsNetId");

        // Simple Field (sourceAmsNetId)
AmsNetId sourceAmsNetId = AmsNetIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("sourceAmsNetId");


        // Simple Field (sourceAmsPort)
int sourceAmsPort = readBuffer.readUnsignedInt("sourceAmsPort", 16) ;
        readBuffer.pullContext("commandId");

        // Simple Field (commandId)
        // enum based simple field with type CommandId
        CommandId commandId = CommandId.enumForValue(readBuffer.readUnsignedInt("CommandId", 16));
        readBuffer.closeContext("commandId");

        readBuffer.pullContext("state");

        // Simple Field (state)
State state = StateIO.staticParse(readBuffer ) ;        readBuffer.closeContext("state");

        // Implicit Field (length) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        long length = readBuffer.readUnsignedLong("length", 32);


        // Simple Field (errorCode)
long errorCode = readBuffer.readUnsignedLong("errorCode", 32) ;

        // Simple Field (invokeId)
long invokeId = readBuffer.readUnsignedLong("invokeId", 32) ;
        readBuffer.pullContext("data");

        // Simple Field (data)
AdsData data = AdsDataIO.staticParse(readBuffer , (CommandId) (commandId), (boolean) (state.getResponse()) ) ;        readBuffer.closeContext("data");

        readBuffer.closeContext("AmsPacket");
        // Create the instance
        return new AmsPacket(targetAmsNetId, targetAmsPort, sourceAmsNetId, sourceAmsPort, commandId, state, errorCode, invokeId, data);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AmsPacket _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AmsPacket");

        // Simple Field (targetAmsNetId)
        AmsNetId targetAmsNetId = (AmsNetId) _value.getTargetAmsNetId();
        writeBuffer.pushContext("targetAmsNetId");
        AmsNetIdIO.staticSerialize(writeBuffer, targetAmsNetId);
        writeBuffer.popContext("targetAmsNetId");

        // Simple Field (targetAmsPort)
        int targetAmsPort = (int) _value.getTargetAmsPort();
        writeBuffer.writeUnsignedInt("targetAmsPort", 16, ((Number) (targetAmsPort)).intValue());

        // Simple Field (sourceAmsNetId)
        AmsNetId sourceAmsNetId = (AmsNetId) _value.getSourceAmsNetId();
        writeBuffer.pushContext("sourceAmsNetId");
        AmsNetIdIO.staticSerialize(writeBuffer, sourceAmsNetId);
        writeBuffer.popContext("sourceAmsNetId");

        // Simple Field (sourceAmsPort)
        int sourceAmsPort = (int) _value.getSourceAmsPort();
        writeBuffer.writeUnsignedInt("sourceAmsPort", 16, ((Number) (sourceAmsPort)).intValue());

        // Simple Field (commandId)
        CommandId commandId = (CommandId) _value.getCommandId();
        writeBuffer.pushContext("commandId");
        // enum field with type CommandId
        writeBuffer.writeUnsignedInt("CommandId", 16, ((Number) (commandId.getValue())).intValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(commandId.name()));
        writeBuffer.popContext("commandId");

        // Simple Field (state)
        State state = (State) _value.getState();
        writeBuffer.pushContext("state");
        StateIO.staticSerialize(writeBuffer, state);
        writeBuffer.popContext("state");

        // Implicit Field (length) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        long length = (long) (_value.getData().getLengthInBytes());
        writeBuffer.writeUnsignedLong("length", 32, ((Number) (length)).longValue());

        // Simple Field (errorCode)
        long errorCode = (long) _value.getErrorCode();
        writeBuffer.writeUnsignedLong("errorCode", 32, ((Number) (errorCode)).longValue());

        // Simple Field (invokeId)
        long invokeId = (long) _value.getInvokeId();
        writeBuffer.writeUnsignedLong("invokeId", 32, ((Number) (invokeId)).longValue());

        // Simple Field (data)
        AdsData data = (AdsData) _value.getData();
        writeBuffer.pushContext("data");
        AdsDataIO.staticSerialize(writeBuffer, data);
        writeBuffer.popContext("data");
        writeBuffer.popContext("AmsPacket");
    }

}