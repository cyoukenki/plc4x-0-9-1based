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
package org.apache.plc4x.java.abeth.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.abeth.readwrite.*;
import org.apache.plc4x.java.abeth.readwrite.io.*;
import org.apache.plc4x.java.abeth.readwrite.types.*;
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

public class CIPEncapsulationReadResponseIO implements MessageIO<CIPEncapsulationReadResponse, CIPEncapsulationReadResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CIPEncapsulationReadResponseIO.class);

    @Override
    public CIPEncapsulationReadResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (CIPEncapsulationReadResponse) new CIPEncapsulationPacketIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CIPEncapsulationReadResponse value, Object... args) throws ParseException {
        new CIPEncapsulationPacketIO().serialize(writeBuffer, value, args);
    }

    public static CIPEncapsulationReadResponseBuilder staticParse(ReadBuffer readBuffer, Integer len) throws ParseException {
        readBuffer.pullContext("CIPEncapsulationReadResponse");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("response");

        // Simple Field (response)
DF1ResponseMessage response = DF1ResponseMessageIO.staticParse(readBuffer , (int) (len) ) ;        readBuffer.closeContext("response");

        readBuffer.closeContext("CIPEncapsulationReadResponse");
        // Create the instance
        return new CIPEncapsulationReadResponseBuilder(response);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CIPEncapsulationReadResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CIPEncapsulationReadResponse");

        // Simple Field (response)
        DF1ResponseMessage response = (DF1ResponseMessage) _value.getResponse();
        writeBuffer.pushContext("response");
        DF1ResponseMessageIO.staticSerialize(writeBuffer, response);
        writeBuffer.popContext("response");
        writeBuffer.popContext("CIPEncapsulationReadResponse");
    }

    public static class CIPEncapsulationReadResponseBuilder implements CIPEncapsulationPacketIO.CIPEncapsulationPacketBuilder {
        private final DF1ResponseMessage response;

        public CIPEncapsulationReadResponseBuilder(DF1ResponseMessage response) {
            this.response = response;
        }

        public CIPEncapsulationReadResponse build(long sessionHandle, long status, short[] senderContext, long options) {
            return new CIPEncapsulationReadResponse(sessionHandle, status, senderContext, options, response);
        }
    }

}
