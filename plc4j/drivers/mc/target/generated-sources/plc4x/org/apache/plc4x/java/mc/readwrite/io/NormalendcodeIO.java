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
package org.apache.plc4x.java.mc.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.mc.readwrite.*;
import org.apache.plc4x.java.mc.readwrite.io.*;
import org.apache.plc4x.java.mc.readwrite.types.*;
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

public class NormalendcodeIO implements MessageIO<Normalendcode, Normalendcode> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NormalendcodeIO.class);

    @Override
    public Normalendcode parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (Normalendcode) new EndcodeTypeIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, Normalendcode value, Object... args) throws ParseException {
        new EndcodeTypeIO().serialize(writeBuffer, value, args);
    }

    public static NormalendcodeBuilder staticParse(ReadBuffer readBuffer, Integer responsedatalength) throws ParseException {
        readBuffer.pullContext("Normalendcode");
        int startPos = readBuffer.getPos();
        int curPos;
        // Array field (data)
        readBuffer.pullContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.calculateResponseDateLength(responsedatalength) > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.calculateResponseDateLength(responsedatalength)) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        byte[] data;
        {
            int itemCount = Math.max(0, (int) org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.calculateResponseDateLength(responsedatalength));
            data = new byte[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
data[curItem] = readBuffer.readSignedByte("", 8) ;            }
        }
            readBuffer.closeContext("data", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("Normalendcode");
        // Create the instance
        return new NormalendcodeBuilder(data);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, Normalendcode _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("Normalendcode");

        // Array Field (data)
        if(_value.getData() != null) {
            writeBuffer.pushContext("data", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getData().length;
            int curItem = 0;
            for(byte element : _value.getData()) {
                writeBuffer.writeSignedByte("", 8, ((Number) element).byteValue());
                curItem++;
            }
            writeBuffer.popContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("Normalendcode");
    }

    public static class NormalendcodeBuilder implements EndcodeTypeIO.EndcodeTypeBuilder {
        private final byte[] data;

        public NormalendcodeBuilder(byte[] data) {
            this.data = data;
        }

        public Normalendcode build() {
            return new Normalendcode(data);
        }
    }

}