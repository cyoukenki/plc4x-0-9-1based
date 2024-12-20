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
package org.apache.plc4x.java.opcua.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.*;

import org.apache.plc4x.java.opcua.readwrite.types.*;
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

public class PascalStringIO implements MessageIO<PascalString, PascalString> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PascalStringIO.class);

    @Override
    public PascalString parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return PascalStringIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, PascalString value, Object... args) throws ParseException {
        PascalStringIO.staticSerialize(writeBuffer, value);
    }

    public static PascalString staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("PascalString");
        int startPos = readBuffer.getPos();
        int curPos;

        // Implicit Field (sLength) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int sLength = readBuffer.readInt("sLength", 32);


        // Simple Field (stringValue)
String stringValue = readBuffer.readString("stringValue", (((sLength) == (-(1))) ? 0 : (sLength) * (8)), "'UTF-8'") ;
        // Virtual field (Just declare a local variable so we can access it in the parser)
        int stringLength = (int) ((((stringValue.length()) == (-(1))) ? 0 : stringValue.length()));

        readBuffer.closeContext("PascalString");
        // Create the instance
        return new PascalString(stringValue);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, PascalString _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("PascalString");

        // Implicit Field (sLength) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int sLength = (int) ((((_value.getStringValue().length()) == (0)) ? -(1) : _value.getStringValue().length()));
        writeBuffer.writeInt("sLength", 32, ((Number) (sLength)).intValue());

        // Simple Field (stringValue)
        String stringValue = (String) _value.getStringValue();
        writeBuffer.writeString("stringValue", ((((((_value.getStringValue().length()) == (0)) ? -(1) : _value.getStringValue().length())) == (-(1))) ? 0 : ((((_value.getStringValue().length()) == (0)) ? -(1) : _value.getStringValue().length())) * (8)), "'UTF-8'", (String) (stringValue));
        writeBuffer.popContext("PascalString");
    }

}
