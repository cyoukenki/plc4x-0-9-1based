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
package org.apache.plc4x.java.profinet.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.profinet.readwrite.*;
import org.apache.plc4x.java.profinet.readwrite.io.*;
import org.apache.plc4x.java.profinet.readwrite.types.*;
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

public class Ethernet_FrameIO implements MessageIO<Ethernet_Frame, Ethernet_Frame> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Ethernet_FrameIO.class);

    @Override
    public Ethernet_Frame parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return Ethernet_FrameIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, Ethernet_Frame value, Object... args) throws ParseException {
        Ethernet_FrameIO.staticSerialize(writeBuffer, value);
    }

    public static Ethernet_Frame staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("Ethernet_Frame");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("destination");

        // Simple Field (destination)
MacAddress destination = MacAddressIO.staticParse(readBuffer ) ;        readBuffer.closeContext("destination");

        readBuffer.pullContext("source");

        // Simple Field (source)
MacAddress source = MacAddressIO.staticParse(readBuffer ) ;        readBuffer.closeContext("source");

        readBuffer.pullContext("payload");

        // Simple Field (payload)
Ethernet_FramePayload payload = Ethernet_FramePayloadIO.staticParse(readBuffer ) ;        readBuffer.closeContext("payload");

        readBuffer.closeContext("Ethernet_Frame");
        // Create the instance
        return new Ethernet_Frame(destination, source, payload);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, Ethernet_Frame _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("Ethernet_Frame");

        // Simple Field (destination)
        MacAddress destination = (MacAddress) _value.getDestination();
        writeBuffer.pushContext("destination");
        MacAddressIO.staticSerialize(writeBuffer, destination);
        writeBuffer.popContext("destination");

        // Simple Field (source)
        MacAddress source = (MacAddress) _value.getSource();
        writeBuffer.pushContext("source");
        MacAddressIO.staticSerialize(writeBuffer, source);
        writeBuffer.popContext("source");

        // Simple Field (payload)
        Ethernet_FramePayload payload = (Ethernet_FramePayload) _value.getPayload();
        writeBuffer.pushContext("payload");
        Ethernet_FramePayloadIO.staticSerialize(writeBuffer, payload);
        writeBuffer.popContext("payload");
        writeBuffer.popContext("Ethernet_Frame");
    }

}