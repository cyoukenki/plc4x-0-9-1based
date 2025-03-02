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

public class S9F7ResponseIO implements MessageIO<S9F7Response, S9F7Response> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S9F7ResponseIO.class);

    @Override
    public S9F7Response parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S9F7Response) new SecsPacketIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S9F7Response value, Object... args) throws ParseException {
        new SecsPacketIO().serialize(writeBuffer, value, args);
    }

    public static S9F7ResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S9F7Response");
        int startPos = readBuffer.getPos();
        int curPos;

        // Const Field (list2)
        int list2 = readBuffer.readUnsignedInt("list2", 16);
        if(list2 != S9F7Response.LIST2) {
            throw new ParseException("Expected constant value " + S9F7Response.LIST2 + " but got " + list2);
        }
        // Array field (revData)
        readBuffer.pullContext("revData", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(10 > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (10) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        short[] revData;
        {
            int itemCount = Math.max(0, (int) 10);
            revData = new short[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
revData[curItem] = readBuffer.readUnsignedShort("", 8) ;            }
        }
            readBuffer.closeContext("revData", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("S9F7Response");
        // Create the instance
        return new S9F7ResponseBuilder(revData);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S9F7Response _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S9F7Response");

        // Const Field (list2)
        writeBuffer.writeUnsignedInt("list2", 16, ((Number) 0x210a).intValue());

        // Array Field (revData)
        if(_value.getRevData() != null) {
            writeBuffer.pushContext("revData", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getRevData().length;
            int curItem = 0;
            for(short element : _value.getRevData()) {
                writeBuffer.writeUnsignedShort("", 8, ((Number) element).shortValue());
                curItem++;
            }
            writeBuffer.popContext("revData", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("S9F7Response");
    }

    public static class S9F7ResponseBuilder implements SecsPacketIO.SecsPacketBuilder {
        private final short[] revData;

        public S9F7ResponseBuilder(short[] revData) {
            this.revData = revData;
        }

        public S9F7Response build(int deviceID, short PType, short Stype, long systemBytes) {
            return new S9F7Response(deviceID, PType, Stype, systemBytes, revData);
        }
    }

}
