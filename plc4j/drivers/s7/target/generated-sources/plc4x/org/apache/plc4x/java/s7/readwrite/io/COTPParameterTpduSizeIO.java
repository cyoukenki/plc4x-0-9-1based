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

public class COTPParameterTpduSizeIO implements MessageIO<COTPParameterTpduSize, COTPParameterTpduSize> {

    private static final Logger LOGGER = LoggerFactory.getLogger(COTPParameterTpduSizeIO.class);

    @Override
    public COTPParameterTpduSize parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (COTPParameterTpduSize) new COTPParameterIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, COTPParameterTpduSize value, Object... args) throws ParseException {
        new COTPParameterIO().serialize(writeBuffer, value, args);
    }

    public static COTPParameterTpduSizeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("COTPParameterTpduSize");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("tpduSize");

        // Simple Field (tpduSize)
        // enum based simple field with type COTPTpduSize
        COTPTpduSize tpduSize = COTPTpduSize.enumForValue(readBuffer.readSignedByte("COTPTpduSize", 8));
        readBuffer.closeContext("tpduSize");

        readBuffer.closeContext("COTPParameterTpduSize");
        // Create the instance
        return new COTPParameterTpduSizeBuilder(tpduSize);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, COTPParameterTpduSize _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("COTPParameterTpduSize");

        // Simple Field (tpduSize)
        COTPTpduSize tpduSize = (COTPTpduSize) _value.getTpduSize();
        writeBuffer.pushContext("tpduSize");
        // enum field with type COTPTpduSize
        writeBuffer.writeSignedByte("COTPTpduSize", 8, ((Number) (tpduSize.getValue())).byteValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(tpduSize.name()));
        writeBuffer.popContext("tpduSize");
        writeBuffer.popContext("COTPParameterTpduSize");
    }

    public static class COTPParameterTpduSizeBuilder implements COTPParameterIO.COTPParameterBuilder {
        private final COTPTpduSize tpduSize;

        public COTPParameterTpduSizeBuilder(COTPTpduSize tpduSize) {
            this.tpduSize = tpduSize;
        }

        public COTPParameterTpduSize build() {
            return new COTPParameterTpduSize(tpduSize);
        }
    }

}