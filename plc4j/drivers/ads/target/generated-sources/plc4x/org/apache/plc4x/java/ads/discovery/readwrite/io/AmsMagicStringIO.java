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
package org.apache.plc4x.java.ads.discovery.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.discovery.readwrite.*;

import org.apache.plc4x.java.ads.discovery.readwrite.types.*;
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

public class AmsMagicStringIO implements MessageIO<AmsMagicString, AmsMagicString> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmsMagicStringIO.class);

    @Override
    public AmsMagicString parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return AmsMagicStringIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AmsMagicString value, Object... args) throws ParseException {
        AmsMagicStringIO.staticSerialize(writeBuffer, value);
    }

    public static AmsMagicString staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AmsMagicString");
        int startPos = readBuffer.getPos();
        int curPos;

        // Implicit Field (len) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int len = readBuffer.readUnsignedInt("len", 16);

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 8);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }
        // Array field (text)
        readBuffer.pullContext("text", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if((len) - (1) > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + ((len) - (1)) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        byte[] text;
        {
            int itemCount = Math.max(0, (int) (len) - (1));
            text = new byte[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
text[curItem] = readBuffer.readSignedByte("", 8) ;            }
        }
            readBuffer.closeContext("text", WithReaderWriterArgs.WithRenderAsList(true));

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 8);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }

        readBuffer.closeContext("AmsMagicString");
        // Create the instance
        return new AmsMagicString(text);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AmsMagicString _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AmsMagicString");

        // Implicit Field (len) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int len = (int) ((COUNT(_value.getText())) + (1));
        writeBuffer.writeUnsignedInt("len", 16, ((Number) (len)).intValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 8, ((Number) (short) 0x00).shortValue());

        // Array Field (text)
        if(_value.getText() != null) {
            writeBuffer.pushContext("text", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getText().length;
            int curItem = 0;
            for(byte element : _value.getText()) {
                writeBuffer.writeSignedByte("", 8, ((Number) element).byteValue());
                curItem++;
            }
            writeBuffer.popContext("text", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 8, ((Number) (short) 0x00).shortValue());
        writeBuffer.popContext("AmsMagicString");
    }

}
