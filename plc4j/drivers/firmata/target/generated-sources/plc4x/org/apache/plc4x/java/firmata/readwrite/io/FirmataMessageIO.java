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
package org.apache.plc4x.java.firmata.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.firmata.readwrite.*;
import org.apache.plc4x.java.firmata.readwrite.io.*;
import org.apache.plc4x.java.firmata.readwrite.types.*;
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

public class FirmataMessageIO implements MessageIO<FirmataMessage, FirmataMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirmataMessageIO.class);

    @Override
    public FirmataMessage parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        if((args == null) || (args.length != 1)) {
            throw new PlcRuntimeException("Wrong number of arguments, expected 1, but got " + args.length);
        }
        Boolean response;
        if(args[0] instanceof Boolean) {
            response = (Boolean) args[0];
        } else if (args[0] instanceof String) {
            response = Boolean.valueOf((String) args[0]);
        } else {
            throw new PlcRuntimeException("Argument 0 expected to be of type Boolean or a string which is parseable but was " + args[0].getClass().getName());
        }
        return FirmataMessageIO.staticParse(readBuffer, response);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, FirmataMessage value, Object... args) throws ParseException {
        FirmataMessageIO.staticSerialize(writeBuffer, value);
    }

    public static FirmataMessage staticParse(ReadBuffer readBuffer, Boolean response) throws ParseException {
        readBuffer.pullContext("FirmataMessage");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (messageType) (Used as input to a switch field)
        byte messageType = readBuffer.readUnsignedByte("messageType", 4);


        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        FirmataMessageBuilder builder = null;
                if(EvaluationHelper.equals(messageType, 0xE)) {
            builder = FirmataMessageAnalogIOIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(messageType, 0x9)) {
            builder = FirmataMessageDigitalIOIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(messageType, 0xC)) {
            builder = FirmataMessageSubscribeAnalogPinValueIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(messageType, 0xD)) {
            builder = FirmataMessageSubscribeDigitalPinValueIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(messageType, 0xF)) {
            builder = FirmataMessageCommandIO.staticParse(readBuffer, response);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("FirmataMessage");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, FirmataMessage _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("FirmataMessage");

        // Discriminator Field (messageType) (Used as input to a switch field)
        byte messageType = (byte) _value.getMessageType();
            writeBuffer.writeUnsignedByte("messageType", 4, ((Number) (messageType)).byteValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof FirmataMessageAnalogIO) {
            FirmataMessageAnalogIOIO.staticSerialize(writeBuffer, (FirmataMessageAnalogIO) _value);
        } else 
        if(_value instanceof FirmataMessageDigitalIO) {
            FirmataMessageDigitalIOIO.staticSerialize(writeBuffer, (FirmataMessageDigitalIO) _value);
        } else 
        if(_value instanceof FirmataMessageSubscribeAnalogPinValue) {
            FirmataMessageSubscribeAnalogPinValueIO.staticSerialize(writeBuffer, (FirmataMessageSubscribeAnalogPinValue) _value);
        } else 
        if(_value instanceof FirmataMessageSubscribeDigitalPinValue) {
            FirmataMessageSubscribeDigitalPinValueIO.staticSerialize(writeBuffer, (FirmataMessageSubscribeDigitalPinValue) _value);
        } else 
        if(_value instanceof FirmataMessageCommand) {
            FirmataMessageCommandIO.staticSerialize(writeBuffer, (FirmataMessageCommand) _value);
        }
        writeBuffer.popContext("FirmataMessage");
    }

    public static interface FirmataMessageBuilder {
        FirmataMessage build();
    }

}