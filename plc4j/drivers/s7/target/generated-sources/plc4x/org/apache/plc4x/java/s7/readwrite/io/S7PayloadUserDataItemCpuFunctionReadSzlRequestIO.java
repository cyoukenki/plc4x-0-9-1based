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

public class S7PayloadUserDataItemCpuFunctionReadSzlRequestIO implements MessageIO<S7PayloadUserDataItemCpuFunctionReadSzlRequest, S7PayloadUserDataItemCpuFunctionReadSzlRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7PayloadUserDataItemCpuFunctionReadSzlRequestIO.class);

    @Override
    public S7PayloadUserDataItemCpuFunctionReadSzlRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S7PayloadUserDataItemCpuFunctionReadSzlRequest) new S7PayloadUserDataItemIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7PayloadUserDataItemCpuFunctionReadSzlRequest value, Object... args) throws ParseException {
        new S7PayloadUserDataItemIO().serialize(writeBuffer, value, args);
    }

    public static S7PayloadUserDataItemCpuFunctionReadSzlRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S7PayloadUserDataItemCpuFunctionReadSzlRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("szlId");

        // Simple Field (szlId)
SzlId szlId = SzlIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("szlId");


        // Simple Field (szlIndex)
int szlIndex = readBuffer.readUnsignedInt("szlIndex", 16) ;
        readBuffer.closeContext("S7PayloadUserDataItemCpuFunctionReadSzlRequest");
        // Create the instance
        return new S7PayloadUserDataItemCpuFunctionReadSzlRequestBuilder(szlId, szlIndex);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7PayloadUserDataItemCpuFunctionReadSzlRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7PayloadUserDataItemCpuFunctionReadSzlRequest");

        // Simple Field (szlId)
        SzlId szlId = (SzlId) _value.getSzlId();
        writeBuffer.pushContext("szlId");
        SzlIdIO.staticSerialize(writeBuffer, szlId);
        writeBuffer.popContext("szlId");

        // Simple Field (szlIndex)
        int szlIndex = (int) _value.getSzlIndex();
        writeBuffer.writeUnsignedInt("szlIndex", 16, ((Number) (szlIndex)).intValue());
        writeBuffer.popContext("S7PayloadUserDataItemCpuFunctionReadSzlRequest");
    }

    public static class S7PayloadUserDataItemCpuFunctionReadSzlRequestBuilder implements S7PayloadUserDataItemIO.S7PayloadUserDataItemBuilder {
        private final SzlId szlId;
        private final int szlIndex;

        public S7PayloadUserDataItemCpuFunctionReadSzlRequestBuilder(SzlId szlId, int szlIndex) {
            this.szlId = szlId;
            this.szlIndex = szlIndex;
        }

        public S7PayloadUserDataItemCpuFunctionReadSzlRequest build(DataTransportErrorCode returnCode, DataTransportSize transportSize) {
            return new S7PayloadUserDataItemCpuFunctionReadSzlRequest(returnCode, transportSize, szlId, szlIndex);
        }
    }

}