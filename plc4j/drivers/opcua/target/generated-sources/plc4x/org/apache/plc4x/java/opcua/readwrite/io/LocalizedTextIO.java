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
import org.apache.plc4x.java.opcua.readwrite.io.*;
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

public class LocalizedTextIO implements MessageIO<LocalizedText, LocalizedText> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalizedTextIO.class);

    @Override
    public LocalizedText parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return LocalizedTextIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, LocalizedText value, Object... args) throws ParseException {
        LocalizedTextIO.staticSerialize(writeBuffer, value);
    }

    public static LocalizedText staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("LocalizedText");
        int startPos = readBuffer.getPos();
        int curPos;

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 6);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (textSpecified)
boolean textSpecified = readBuffer.readBit("textSpecified") ;

        // Simple Field (localeSpecified)
boolean localeSpecified = readBuffer.readBit("localeSpecified") ;
        // Optional Field (locale) (Can be skipped, if a given expression evaluates to false)
        PascalString locale = null;
        if(localeSpecified) {
            locale = PascalStringIO.staticParse(readBuffer);
        }

        // Optional Field (text) (Can be skipped, if a given expression evaluates to false)
        PascalString text = null;
        if(textSpecified) {
            text = PascalStringIO.staticParse(readBuffer);
        }

        readBuffer.closeContext("LocalizedText");
        // Create the instance
        return new LocalizedText(textSpecified, localeSpecified, locale, text);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, LocalizedText _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("LocalizedText");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 6, ((Number) (short) 0x00).shortValue());

        // Simple Field (textSpecified)
        boolean textSpecified = (boolean) _value.getTextSpecified();
        writeBuffer.writeBit("textSpecified", (boolean) (textSpecified));

        // Simple Field (localeSpecified)
        boolean localeSpecified = (boolean) _value.getLocaleSpecified();
        writeBuffer.writeBit("localeSpecified", (boolean) (localeSpecified));

        // Optional Field (locale) (Can be skipped, if the value is null)
        PascalString locale = null;
        if(_value.getLocale() != null) {
            locale = (PascalString) _value.getLocale();
            PascalStringIO.staticSerialize(writeBuffer, locale);
        }

        // Optional Field (text) (Can be skipped, if the value is null)
        PascalString text = null;
        if(_value.getText() != null) {
            text = (PascalString) _value.getText();
            PascalStringIO.staticSerialize(writeBuffer, text);
        }
        writeBuffer.popContext("LocalizedText");
    }

}