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
package org.apache.plc4x.java.modbus.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.modbus.readwrite.*;
import org.apache.plc4x.java.modbus.readwrite.io.*;
import org.apache.plc4x.java.modbus.readwrite.types.*;
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

public class ModbusPDUReadExceptionStatusResponseIO implements MessageIO<ModbusPDUReadExceptionStatusResponse, ModbusPDUReadExceptionStatusResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModbusPDUReadExceptionStatusResponseIO.class);

    @Override
    public ModbusPDUReadExceptionStatusResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (ModbusPDUReadExceptionStatusResponse) new ModbusPDUIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ModbusPDUReadExceptionStatusResponse value, Object... args) throws ParseException {
        new ModbusPDUIO().serialize(writeBuffer, value, args);
    }

    public static ModbusPDUReadExceptionStatusResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("ModbusPDUReadExceptionStatusResponse");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (value)
short value = readBuffer.readUnsignedShort("value", 8) ;
        readBuffer.closeContext("ModbusPDUReadExceptionStatusResponse");
        // Create the instance
        return new ModbusPDUReadExceptionStatusResponseBuilder(value);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ModbusPDUReadExceptionStatusResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ModbusPDUReadExceptionStatusResponse");

        // Simple Field (value)
        short value = (short) _value.getValue();
        writeBuffer.writeUnsignedShort("value", 8, ((Number) (value)).shortValue());
        writeBuffer.popContext("ModbusPDUReadExceptionStatusResponse");
    }

    public static class ModbusPDUReadExceptionStatusResponseBuilder implements ModbusPDUIO.ModbusPDUBuilder {
        private final short value;

        public ModbusPDUReadExceptionStatusResponseBuilder(short value) {
            this.value = value;
        }

        public ModbusPDUReadExceptionStatusResponse build() {
            return new ModbusPDUReadExceptionStatusResponse(value);
        }
    }

}
