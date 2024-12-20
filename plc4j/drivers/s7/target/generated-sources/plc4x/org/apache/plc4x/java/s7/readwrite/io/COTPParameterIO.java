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

public class COTPParameterIO implements MessageIO<COTPParameter, COTPParameter> {

    private static final Logger LOGGER = LoggerFactory.getLogger(COTPParameterIO.class);

    @Override
    public COTPParameter parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        if((args == null) || (args.length != 1)) {
            throw new PlcRuntimeException("Wrong number of arguments, expected 1, but got " + args.length);
        }
        Short rest;
        if(args[0] instanceof Short) {
            rest = (Short) args[0];
        } else if (args[0] instanceof String) {
            rest = Short.valueOf((String) args[0]);
        } else {
            throw new PlcRuntimeException("Argument 0 expected to be of type Short or a string which is parseable but was " + args[0].getClass().getName());
        }
        return COTPParameterIO.staticParse(readBuffer, rest);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, COTPParameter value, Object... args) throws ParseException {
        COTPParameterIO.staticSerialize(writeBuffer, value);
    }

    public static COTPParameter staticParse(ReadBuffer readBuffer, Short rest) throws ParseException {
        readBuffer.pullContext("COTPParameter");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (parameterType) (Used as input to a switch field)
        short parameterType = readBuffer.readUnsignedShort("parameterType", 8);


        // Implicit Field (parameterLength) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        short parameterLength = readBuffer.readUnsignedShort("parameterLength", 8);

        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        COTPParameterBuilder builder = null;
                if(EvaluationHelper.equals(parameterType, 0xC0)) {
            builder = COTPParameterTpduSizeIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(parameterType, 0xC1)) {
            builder = COTPParameterCallingTsapIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(parameterType, 0xC2)) {
            builder = COTPParameterCalledTsapIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(parameterType, 0xC3)) {
            builder = COTPParameterChecksumIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(parameterType, 0xE0)) {
            builder = COTPParameterDisconnectAdditionalInformationIO.staticParse(readBuffer, rest);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("COTPParameter");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, COTPParameter _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("COTPParameter");

        // Discriminator Field (parameterType) (Used as input to a switch field)
        short parameterType = (short) _value.getParameterType();
            writeBuffer.writeUnsignedShort("parameterType", 8, ((Number) (parameterType)).shortValue());

        // Implicit Field (parameterLength) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        short parameterLength = (short) ((_value.getLengthInBytes()) - (2));
        writeBuffer.writeUnsignedShort("parameterLength", 8, ((Number) (parameterLength)).shortValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof COTPParameterTpduSize) {
            COTPParameterTpduSizeIO.staticSerialize(writeBuffer, (COTPParameterTpduSize) _value);
        } else 
        if(_value instanceof COTPParameterCallingTsap) {
            COTPParameterCallingTsapIO.staticSerialize(writeBuffer, (COTPParameterCallingTsap) _value);
        } else 
        if(_value instanceof COTPParameterCalledTsap) {
            COTPParameterCalledTsapIO.staticSerialize(writeBuffer, (COTPParameterCalledTsap) _value);
        } else 
        if(_value instanceof COTPParameterChecksum) {
            COTPParameterChecksumIO.staticSerialize(writeBuffer, (COTPParameterChecksum) _value);
        } else 
        if(_value instanceof COTPParameterDisconnectAdditionalInformation) {
            COTPParameterDisconnectAdditionalInformationIO.staticSerialize(writeBuffer, (COTPParameterDisconnectAdditionalInformation) _value);
        }
        writeBuffer.popContext("COTPParameter");
    }

    public static interface COTPParameterBuilder {
        COTPParameter build();
    }

}
