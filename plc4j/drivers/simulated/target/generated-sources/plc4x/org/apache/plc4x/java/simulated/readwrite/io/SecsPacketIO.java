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
package org.apache.plc4x.java.simulated.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.simulated.readwrite.*;
import org.apache.plc4x.java.simulated.readwrite.io.*;
import org.apache.plc4x.java.simulated.readwrite.types.*;
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

public class SecsPacketIO implements MessageIO<SecsPacket, SecsPacket> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecsPacketIO.class);

    @Override
    public SecsPacket parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return SecsPacketIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, SecsPacket value, Object... args) throws ParseException {
        SecsPacketIO.staticSerialize(writeBuffer, value);
    }

    public static SecsPacket staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("SecsPacket");
        int startPos = readBuffer.getPos();
        int curPos;

        // Implicit Field (len) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        long len = readBuffer.readUnsignedLong("len", 32);


        // Simple Field (deviceID)
int deviceID = readBuffer.readUnsignedInt("deviceID", 16) ;
        // Discriminator Field (streamFunction) (Used as input to a switch field)
        int streamFunction = readBuffer.readUnsignedInt("streamFunction", 16);



        // Simple Field (PType)
short PType = readBuffer.readUnsignedShort("PType", 8) ;

        // Simple Field (Stype)
short Stype = readBuffer.readUnsignedShort("Stype", 8) ;

        // Simple Field (systemBytes)
long systemBytes = readBuffer.readUnsignedLong("systemBytes", 32) ;
        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        SecsPacketBuilder builder = null;
                if(EvaluationHelper.equals(streamFunction, 0x860b)) {
            builder = S6F11RequestIO.staticParse(readBuffer);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("SecsPacket");
        // Create the instance
        return builder.build(deviceID, PType, Stype, systemBytes);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, SecsPacket _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("SecsPacket");

        // Implicit Field (len) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        long len = (long) ((_value.getLengthInBytes()) - (4));
        writeBuffer.writeUnsignedLong("len", 32, ((Number) (len)).longValue());

        // Simple Field (deviceID)
        int deviceID = (int) _value.getDeviceID();
        writeBuffer.writeUnsignedInt("deviceID", 16, ((Number) (deviceID)).intValue());

        // Discriminator Field (streamFunction) (Used as input to a switch field)
        int streamFunction = (int) _value.getStreamFunction();
            writeBuffer.writeUnsignedInt("streamFunction", 16, ((Number) (streamFunction)).intValue());

        // Simple Field (PType)
        short PType = (short) _value.getPType();
        writeBuffer.writeUnsignedShort("PType", 8, ((Number) (PType)).shortValue());

        // Simple Field (Stype)
        short Stype = (short) _value.getStype();
        writeBuffer.writeUnsignedShort("Stype", 8, ((Number) (Stype)).shortValue());

        // Simple Field (systemBytes)
        long systemBytes = (long) _value.getSystemBytes();
        writeBuffer.writeUnsignedLong("systemBytes", 32, ((Number) (systemBytes)).longValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof S6F11Request) {
            S6F11RequestIO.staticSerialize(writeBuffer, (S6F11Request) _value);
        }
        writeBuffer.popContext("SecsPacket");
    }

    public static interface SecsPacketBuilder {
        SecsPacket build(int deviceID, short PType, short Stype, long systemBytes);
    }

}
