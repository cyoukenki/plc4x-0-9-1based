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

public class CommandTypeIO implements MessageIO<CommandType, CommandType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandTypeIO.class);

    @Override
    public CommandType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return CommandTypeIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CommandType value, Object... args) throws ParseException {
        CommandTypeIO.staticSerialize(writeBuffer, value);
    }

    public static CommandType staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("CommandType");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (Command) (Used as input to a switch field)
        long Command = readBuffer.readUnsignedLong("Command", 32);


        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        CommandTypeBuilder builder = null;
                if(EvaluationHelper.equals(Command, 0x30343031)) {
            builder = ReadIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(Command, 0x31343031)) {
            builder = WriteIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(Command, 0x30343033)) {
            builder = ReadrandomIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(Command, 0x31343032)) {
            builder = WriterandomIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(Command, 0x30363139)) {
            builder = SelftestIO.staticParse(readBuffer);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("CommandType");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CommandType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CommandType");

        // Discriminator Field (Command) (Used as input to a switch field)
        long Command = (long) _value.getCommand();
            writeBuffer.writeUnsignedLong("Command", 32, ((Number) (Command)).longValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof Read) {
            ReadIO.staticSerialize(writeBuffer, (Read) _value);
        } else 
        if(_value instanceof Write) {
            WriteIO.staticSerialize(writeBuffer, (Write) _value);
        } else 
        if(_value instanceof Readrandom) {
            ReadrandomIO.staticSerialize(writeBuffer, (Readrandom) _value);
        } else 
        if(_value instanceof Writerandom) {
            WriterandomIO.staticSerialize(writeBuffer, (Writerandom) _value);
        } else 
        if(_value instanceof Selftest) {
            SelftestIO.staticSerialize(writeBuffer, (Selftest) _value);
        }
        writeBuffer.popContext("CommandType");
    }

    public static interface CommandTypeBuilder {
        CommandType build();
    }

}