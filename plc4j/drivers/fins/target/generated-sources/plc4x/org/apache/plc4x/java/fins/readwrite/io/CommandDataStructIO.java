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
package org.apache.plc4x.java.fins.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.fins.readwrite.*;
import org.apache.plc4x.java.fins.readwrite.io.*;
import org.apache.plc4x.java.fins.readwrite.types.*;
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

public class CommandDataStructIO implements MessageIO<CommandDataStruct, CommandDataStruct> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandDataStructIO.class);

    @Override
    public CommandDataStruct parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        if((args == null) || (args.length != 1)) {
            throw new PlcRuntimeException("Wrong number of arguments, expected 1, but got " + args.length);
        }
        Integer commandDataStructLen;
        if(args[0] instanceof Integer) {
            commandDataStructLen = (Integer) args[0];
        } else if (args[0] instanceof String) {
            commandDataStructLen = Integer.valueOf((String) args[0]);
        } else {
            throw new PlcRuntimeException("Argument 0 expected to be of type Integer or a string which is parseable but was " + args[0].getClass().getName());
        }
        return CommandDataStructIO.staticParse(readBuffer, commandDataStructLen);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CommandDataStruct value, Object... args) throws ParseException {
        CommandDataStructIO.staticSerialize(writeBuffer, value);
    }

    public static CommandDataStruct staticParse(ReadBuffer readBuffer, Integer commandDataStructLen) throws ParseException {
        readBuffer.pullContext("CommandDataStruct");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (finsCommand) (Used as input to a switch field)
        int finsCommand = readBuffer.readUnsignedInt("finsCommand", 16);


        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        CommandDataStructBuilder builder = null;
                if(EvaluationHelper.equals(finsCommand, 0x0101)) {
            builder = ReadBitsResIO.staticParse(readBuffer, commandDataStructLen);
        } else 
                if(EvaluationHelper.equals(finsCommand, 0x0101)) {
            builder = ReadBitsReqIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(finsCommand, 0x0102)) {
            builder = WriteBitsResIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(finsCommand, 0x0102)) {
            builder = WriteBitsReqIO.staticParse(readBuffer);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("CommandDataStruct");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CommandDataStruct _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CommandDataStruct");

        // Discriminator Field (finsCommand) (Used as input to a switch field)
        int finsCommand = (int) _value.getFinsCommand();
            writeBuffer.writeUnsignedInt("finsCommand", 16, ((Number) (finsCommand)).intValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof ReadBitsRes) {
            ReadBitsResIO.staticSerialize(writeBuffer, (ReadBitsRes) _value);
        } else 
        if(_value instanceof ReadBitsReq) {
            ReadBitsReqIO.staticSerialize(writeBuffer, (ReadBitsReq) _value);
        } else 
        if(_value instanceof WriteBitsRes) {
            WriteBitsResIO.staticSerialize(writeBuffer, (WriteBitsRes) _value);
        } else 
        if(_value instanceof WriteBitsReq) {
            WriteBitsReqIO.staticSerialize(writeBuffer, (WriteBitsReq) _value);
        }
        writeBuffer.popContext("CommandDataStruct");
    }

    public static interface CommandDataStructBuilder {
        CommandDataStruct build();
    }

}