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

public class ReadSubcommandTypeIO implements MessageIO<ReadSubcommandType, ReadSubcommandType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadSubcommandTypeIO.class);

    @Override
    public ReadSubcommandType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return ReadSubcommandTypeIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ReadSubcommandType value, Object... args) throws ParseException {
        ReadSubcommandTypeIO.staticSerialize(writeBuffer, value);
    }

    public static ReadSubcommandType staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("ReadSubcommandType");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (Subcommand) (Used as input to a switch field)
        long Subcommand = readBuffer.readUnsignedLong("Subcommand", 32);


        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        ReadSubcommandTypeBuilder builder = null;
                if(EvaluationHelper.equals(Subcommand, 0x30303031)) {
            builder = ReadbybitIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(Subcommand, 0x30303030)) {
            builder = ReadbywordIO.staticParse(readBuffer);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("ReadSubcommandType");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ReadSubcommandType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ReadSubcommandType");

        // Discriminator Field (Subcommand) (Used as input to a switch field)
        long Subcommand = (long) _value.getSubcommand();
            writeBuffer.writeUnsignedLong("Subcommand", 32, ((Number) (Subcommand)).longValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof Readbybit) {
            ReadbybitIO.staticSerialize(writeBuffer, (Readbybit) _value);
        } else 
        if(_value instanceof Readbyword) {
            ReadbywordIO.staticSerialize(writeBuffer, (Readbyword) _value);
        }
        writeBuffer.popContext("ReadSubcommandType");
    }

    public static interface ReadSubcommandTypeBuilder {
        ReadSubcommandType build();
    }

}
