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
package org.apache.plc4x.java.eip.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.eip.readwrite.*;
import org.apache.plc4x.java.eip.readwrite.io.*;
import org.apache.plc4x.java.eip.readwrite.types.*;
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

public class CipReadResponseIO implements MessageIO<CipReadResponse, CipReadResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CipReadResponseIO.class);

    @Override
    public CipReadResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (CipReadResponse) new CipServiceIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CipReadResponse value, Object... args) throws ParseException {
        new CipServiceIO().serialize(writeBuffer, value, args);
    }

    public static CipReadResponseBuilder staticParse(ReadBuffer readBuffer, Integer serviceLen) throws ParseException {
        readBuffer.pullContext("CipReadResponse");
        int startPos = readBuffer.getPos();
        int curPos;

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 8);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }

        readBuffer.pullContext("readResponseContent");

        // Simple Field (readResponseContent)
ReadResponseContent readResponseContent = ReadResponseContentIO.staticParse(readBuffer , (int) (serviceLen) ) ;        readBuffer.closeContext("readResponseContent");

        readBuffer.closeContext("CipReadResponse");
        // Create the instance
        return new CipReadResponseBuilder(readResponseContent);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CipReadResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CipReadResponse");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 8, ((Number) (short) 0x00).shortValue());

        // Simple Field (readResponseContent)
        ReadResponseContent readResponseContent = (ReadResponseContent) _value.getReadResponseContent();
        writeBuffer.pushContext("readResponseContent");
        ReadResponseContentIO.staticSerialize(writeBuffer, readResponseContent);
        writeBuffer.popContext("readResponseContent");
        writeBuffer.popContext("CipReadResponse");
    }

    public static class CipReadResponseBuilder implements CipServiceIO.CipServiceBuilder {
        private final ReadResponseContent readResponseContent;

        public CipReadResponseBuilder(ReadResponseContent readResponseContent) {
            this.readResponseContent = readResponseContent;
        }

        public CipReadResponse build() {
            return new CipReadResponse(readResponseContent);
        }
    }

}