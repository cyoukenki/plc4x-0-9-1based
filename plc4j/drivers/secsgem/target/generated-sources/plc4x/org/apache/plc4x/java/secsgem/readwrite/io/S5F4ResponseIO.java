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
package org.apache.plc4x.java.secsgem.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.io.*;
import org.apache.plc4x.java.secsgem.readwrite.types.*;
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

public class S5F4ResponseIO implements MessageIO<S5F4Response, S5F4Response> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S5F4ResponseIO.class);

    @Override
    public S5F4Response parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S5F4Response) new SecsPacketIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S5F4Response value, Object... args) throws ParseException {
        new SecsPacketIO().serialize(writeBuffer, value, args);
    }

    public static S5F4ResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S5F4Response");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("ackc5");

        // Simple Field (ackc5)
DataStruct ackc5 = DataStructIO.staticParse(readBuffer ) ;        readBuffer.closeContext("ackc5");

        readBuffer.closeContext("S5F4Response");
        // Create the instance
        return new S5F4ResponseBuilder(ackc5);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S5F4Response _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S5F4Response");

        // Simple Field (ackc5)
        DataStruct ackc5 = (DataStruct) _value.getAckc5();
        writeBuffer.pushContext("ackc5");
        DataStructIO.staticSerialize(writeBuffer, ackc5);
        writeBuffer.popContext("ackc5");
        writeBuffer.popContext("S5F4Response");
    }

    public static class S5F4ResponseBuilder implements SecsPacketIO.SecsPacketBuilder {
        private final DataStruct ackc5;

        public S5F4ResponseBuilder(DataStruct ackc5) {
            this.ackc5 = ackc5;
        }

        public S5F4Response build(int deviceID, short PType, short Stype, long systemBytes) {
            return new S5F4Response(deviceID, PType, Stype, systemBytes, ackc5);
        }
    }

}
