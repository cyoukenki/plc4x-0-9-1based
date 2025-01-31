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

public class S7PayloadIO implements MessageIO<S7Payload, S7Payload> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7PayloadIO.class);

    @Override
    public S7Payload parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        if((args == null) || (args.length != 2)) {
            throw new PlcRuntimeException("Wrong number of arguments, expected 2, but got " + args.length);
        }
        Short messageType;
        if(args[0] instanceof Short) {
            messageType = (Short) args[0];
        } else if (args[0] instanceof String) {
            messageType = Short.valueOf((String) args[0]);
        } else {
            throw new PlcRuntimeException("Argument 0 expected to be of type Short or a string which is parseable but was " + args[0].getClass().getName());
        }
        S7Parameter parameter;
        if(args[1] instanceof S7Parameter) {
            parameter = (S7Parameter) args[1];
        } else {
            throw new PlcRuntimeException("Argument 1 expected to be of type S7Parameter or a string which is parseable but was " + args[1].getClass().getName());
        }
        return S7PayloadIO.staticParse(readBuffer, messageType, parameter);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7Payload value, Object... args) throws ParseException {
        S7PayloadIO.staticSerialize(writeBuffer, value);
    }

    public static S7Payload staticParse(ReadBuffer readBuffer, Short messageType, S7Parameter parameter) throws ParseException {
        readBuffer.pullContext("S7Payload");
        int startPos = readBuffer.getPos();
        int curPos;

        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        S7PayloadBuilder builder = null;
                if(EvaluationHelper.equals(parameter.getParameterType(), 0x04) && EvaluationHelper.equals(messageType, 0x03)) {
            builder = S7PayloadReadVarResponseIO.staticParse(readBuffer, parameter);
        } else 
                if(EvaluationHelper.equals(parameter.getParameterType(), 0x05) && EvaluationHelper.equals(messageType, 0x01)) {
            builder = S7PayloadWriteVarRequestIO.staticParse(readBuffer, parameter);
        } else 
                if(EvaluationHelper.equals(parameter.getParameterType(), 0x05) && EvaluationHelper.equals(messageType, 0x03)) {
            builder = S7PayloadWriteVarResponseIO.staticParse(readBuffer, parameter);
        } else 
                if(EvaluationHelper.equals(parameter.getParameterType(), 0x00) && EvaluationHelper.equals(messageType, 0x07)) {
            builder = S7PayloadUserDataIO.staticParse(readBuffer, parameter);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("S7Payload");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7Payload _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7Payload");

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof S7PayloadReadVarResponse) {
            S7PayloadReadVarResponseIO.staticSerialize(writeBuffer, (S7PayloadReadVarResponse) _value);
        } else 
        if(_value instanceof S7PayloadWriteVarRequest) {
            S7PayloadWriteVarRequestIO.staticSerialize(writeBuffer, (S7PayloadWriteVarRequest) _value);
        } else 
        if(_value instanceof S7PayloadWriteVarResponse) {
            S7PayloadWriteVarResponseIO.staticSerialize(writeBuffer, (S7PayloadWriteVarResponse) _value);
        } else 
        if(_value instanceof S7PayloadUserData) {
            S7PayloadUserDataIO.staticSerialize(writeBuffer, (S7PayloadUserData) _value);
        }
        writeBuffer.popContext("S7Payload");
    }

    public static interface S7PayloadBuilder {
        S7Payload build();
    }

}
