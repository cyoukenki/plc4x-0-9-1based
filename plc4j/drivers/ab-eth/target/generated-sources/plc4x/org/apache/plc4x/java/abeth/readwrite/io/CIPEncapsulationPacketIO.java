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
package org.apache.plc4x.java.abeth.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.abeth.readwrite.*;
import org.apache.plc4x.java.abeth.readwrite.io.*;
import org.apache.plc4x.java.abeth.readwrite.types.*;
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

public class CIPEncapsulationPacketIO implements MessageIO<CIPEncapsulationPacket, CIPEncapsulationPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CIPEncapsulationPacketIO.class);

    @Override
    public CIPEncapsulationPacket parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return CIPEncapsulationPacketIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CIPEncapsulationPacket value, Object... args) throws ParseException {
        CIPEncapsulationPacketIO.staticSerialize(writeBuffer, value);
    }

    public static CIPEncapsulationPacket staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("CIPEncapsulationPacket");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (commandType) (Used as input to a switch field)
        int commandType = readBuffer.readUnsignedInt("commandType", 16);


        // Implicit Field (len) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int len = readBuffer.readUnsignedInt("len", 16);


        // Simple Field (sessionHandle)
long sessionHandle = readBuffer.readUnsignedLong("sessionHandle", 32) ;

        // Simple Field (status)
long status = readBuffer.readUnsignedLong("status", 32) ;        // Array field (senderContext)
        readBuffer.pullContext("senderContext", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(8 > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (8) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        short[] senderContext;
        {
            int itemCount = Math.max(0, (int) 8);
            senderContext = new short[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
senderContext[curItem] = readBuffer.readUnsignedShort("", 8) ;            }
        }
            readBuffer.closeContext("senderContext", WithReaderWriterArgs.WithRenderAsList(true));


        // Simple Field (options)
long options = readBuffer.readUnsignedLong("options", 32) ;
        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            long reserved = readBuffer.readUnsignedLong("reserved", 32);
            if(reserved != (long) 0x00000000) {
                LOGGER.info("Expected constant value " + 0x00000000 + " but got " + reserved + " for reserved field.");
            }
        }

        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        CIPEncapsulationPacketBuilder builder = null;
                if(EvaluationHelper.equals(commandType, 0x0101)) {
            builder = CIPEncapsulationConnectionRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(commandType, 0x0201)) {
            builder = CIPEncapsulationConnectionResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(commandType, 0x0107)) {
            builder = CIPEncapsulationReadRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(commandType, 0x0207)) {
            builder = CIPEncapsulationReadResponseIO.staticParse(readBuffer, len);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("CIPEncapsulationPacket");
        // Create the instance
        return builder.build(sessionHandle, status, senderContext, options);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CIPEncapsulationPacket _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CIPEncapsulationPacket");

        // Discriminator Field (commandType) (Used as input to a switch field)
        int commandType = (int) _value.getCommandType();
            writeBuffer.writeUnsignedInt("commandType", 16, ((Number) (commandType)).intValue());

        // Implicit Field (len) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int len = (int) ((_value.getLengthInBytes()) - (28));
        writeBuffer.writeUnsignedInt("len", 16, ((Number) (len)).intValue());

        // Simple Field (sessionHandle)
        long sessionHandle = (long) _value.getSessionHandle();
        writeBuffer.writeUnsignedLong("sessionHandle", 32, ((Number) (sessionHandle)).longValue());

        // Simple Field (status)
        long status = (long) _value.getStatus();
        writeBuffer.writeUnsignedLong("status", 32, ((Number) (status)).longValue());

        // Array Field (senderContext)
        if(_value.getSenderContext() != null) {
            writeBuffer.pushContext("senderContext", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getSenderContext().length;
            int curItem = 0;
            for(short element : _value.getSenderContext()) {
                writeBuffer.writeUnsignedShort("", 8, ((Number) element).shortValue());
                curItem++;
            }
            writeBuffer.popContext("senderContext", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (options)
        long options = (long) _value.getOptions();
        writeBuffer.writeUnsignedLong("options", 32, ((Number) (options)).longValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedLong("reserved", 32, ((Number) (long) 0x00000000).longValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof CIPEncapsulationConnectionRequest) {
            CIPEncapsulationConnectionRequestIO.staticSerialize(writeBuffer, (CIPEncapsulationConnectionRequest) _value);
        } else 
        if(_value instanceof CIPEncapsulationConnectionResponse) {
            CIPEncapsulationConnectionResponseIO.staticSerialize(writeBuffer, (CIPEncapsulationConnectionResponse) _value);
        } else 
        if(_value instanceof CIPEncapsulationReadRequest) {
            CIPEncapsulationReadRequestIO.staticSerialize(writeBuffer, (CIPEncapsulationReadRequest) _value);
        } else 
        if(_value instanceof CIPEncapsulationReadResponse) {
            CIPEncapsulationReadResponseIO.staticSerialize(writeBuffer, (CIPEncapsulationReadResponse) _value);
        }
        writeBuffer.popContext("CIPEncapsulationPacket");
    }

    public static interface CIPEncapsulationPacketBuilder {
        CIPEncapsulationPacket build(long sessionHandle, long status, short[] senderContext, long options);
    }

}