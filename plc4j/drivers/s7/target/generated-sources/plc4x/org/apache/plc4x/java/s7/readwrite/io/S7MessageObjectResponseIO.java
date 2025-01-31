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

public class S7MessageObjectResponseIO implements MessageIO<S7MessageObjectResponse, S7MessageObjectResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7MessageObjectResponseIO.class);

    @Override
    public S7MessageObjectResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S7MessageObjectResponse) new S7DataAlarmMessageIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7MessageObjectResponse value, Object... args) throws ParseException {
        new S7DataAlarmMessageIO().serialize(writeBuffer, value, args);
    }

    public static S7MessageObjectResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S7MessageObjectResponse");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("returnCode");

        // Simple Field (returnCode)
        // enum based simple field with type DataTransportErrorCode
        DataTransportErrorCode returnCode = DataTransportErrorCode.enumForValue(readBuffer.readUnsignedShort("DataTransportErrorCode", 8));
        readBuffer.closeContext("returnCode");

        readBuffer.pullContext("transportSize");

        // Simple Field (transportSize)
        // enum based simple field with type DataTransportSize
        DataTransportSize transportSize = DataTransportSize.enumForValue(readBuffer.readUnsignedShort("DataTransportSize", 8));
        readBuffer.closeContext("transportSize");

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 8);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }

        readBuffer.closeContext("S7MessageObjectResponse");
        // Create the instance
        return new S7MessageObjectResponseBuilder(returnCode, transportSize);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7MessageObjectResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7MessageObjectResponse");

        // Simple Field (returnCode)
        DataTransportErrorCode returnCode = (DataTransportErrorCode) _value.getReturnCode();
        writeBuffer.pushContext("returnCode");
        // enum field with type DataTransportErrorCode
        writeBuffer.writeUnsignedShort("DataTransportErrorCode", 8, ((Number) (returnCode.getValue())).shortValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(returnCode.name()));
        writeBuffer.popContext("returnCode");

        // Simple Field (transportSize)
        DataTransportSize transportSize = (DataTransportSize) _value.getTransportSize();
        writeBuffer.pushContext("transportSize");
        // enum field with type DataTransportSize
        writeBuffer.writeUnsignedShort("DataTransportSize", 8, ((Number) (transportSize.getValue())).shortValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(transportSize.name()));
        writeBuffer.popContext("transportSize");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 8, ((Number) (short) 0x00).shortValue());
        writeBuffer.popContext("S7MessageObjectResponse");
    }

    public static class S7MessageObjectResponseBuilder implements S7DataAlarmMessageIO.S7DataAlarmMessageBuilder {
        private final DataTransportErrorCode returnCode;
        private final DataTransportSize transportSize;

        public S7MessageObjectResponseBuilder(DataTransportErrorCode returnCode, DataTransportSize transportSize) {
            this.returnCode = returnCode;
            this.transportSize = transportSize;
        }

        public S7MessageObjectResponse build() {
            return new S7MessageObjectResponse(returnCode, transportSize);
        }
    }

}
