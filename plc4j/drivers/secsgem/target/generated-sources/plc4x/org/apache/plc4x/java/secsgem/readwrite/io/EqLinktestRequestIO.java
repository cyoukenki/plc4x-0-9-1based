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
package org.apache.plc4x.java.secsgem.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.io.*;
import org.apache.plc4x.java.secsgem.readwrite.types.*;
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

public class EqLinktestRequestIO implements MessageIO<EqLinktestRequest, EqLinktestRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EqLinktestRequestIO.class);

    @Override
    public EqLinktestRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (EqLinktestRequest) new SecsPacketIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, EqLinktestRequest value, Object... args) throws ParseException {
        new SecsPacketIO().serialize(writeBuffer, value, args);
    }

    public static EqLinktestRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("EqLinktestRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.closeContext("EqLinktestRequest");
        // Create the instance
        return new EqLinktestRequestBuilder();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, EqLinktestRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("EqLinktestRequest");
        writeBuffer.popContext("EqLinktestRequest");
    }

    public static class EqLinktestRequestBuilder implements SecsPacketIO.SecsPacketBuilder {

        public EqLinktestRequestBuilder() {
        }

        public EqLinktestRequest build(int deviceID, short PType, short Stype, long systemBytes) {
            return new EqLinktestRequest(deviceID, PType, Stype, systemBytes);
        }
    }

}