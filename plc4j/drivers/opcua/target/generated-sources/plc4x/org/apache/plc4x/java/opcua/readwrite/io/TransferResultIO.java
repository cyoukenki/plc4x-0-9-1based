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

public class TransferResultIO implements MessageIO<TransferResult, TransferResult> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferResultIO.class);

    @Override
    public TransferResult parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (TransferResult) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, TransferResult value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static TransferResultBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("TransferResult");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("statusCode");

        // Simple Field (statusCode)
StatusCode statusCode = StatusCodeIO.staticParse(readBuffer ) ;        readBuffer.closeContext("statusCode");


        // Simple Field (noOfAvailableSequenceNumbers)
int noOfAvailableSequenceNumbers = readBuffer.readInt("noOfAvailableSequenceNumbers", 32) ;        // Array field (availableSequenceNumbers)
        readBuffer.pullContext("availableSequenceNumbers", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfAvailableSequenceNumbers > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfAvailableSequenceNumbers) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        long[] availableSequenceNumbers;
        {
            int itemCount = Math.max(0, (int) noOfAvailableSequenceNumbers);
            availableSequenceNumbers = new long[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
availableSequenceNumbers[curItem] = readBuffer.readUnsignedLong("", 32) ;            }
        }
            readBuffer.closeContext("availableSequenceNumbers", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("TransferResult");
        // Create the instance
        return new TransferResultBuilder(statusCode, noOfAvailableSequenceNumbers, availableSequenceNumbers);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, TransferResult _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("TransferResult");

        // Simple Field (statusCode)
        StatusCode statusCode = (StatusCode) _value.getStatusCode();
        writeBuffer.pushContext("statusCode");
        StatusCodeIO.staticSerialize(writeBuffer, statusCode);
        writeBuffer.popContext("statusCode");

        // Simple Field (noOfAvailableSequenceNumbers)
        int noOfAvailableSequenceNumbers = (int) _value.getNoOfAvailableSequenceNumbers();
        writeBuffer.writeInt("noOfAvailableSequenceNumbers", 32, ((Number) (noOfAvailableSequenceNumbers)).intValue());

        // Array Field (availableSequenceNumbers)
        if(_value.getAvailableSequenceNumbers() != null) {
            writeBuffer.pushContext("availableSequenceNumbers", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getAvailableSequenceNumbers().length;
            int curItem = 0;
            for(long element : _value.getAvailableSequenceNumbers()) {
                writeBuffer.writeUnsignedLong("", 32, ((Number) element).longValue());
                curItem++;
            }
            writeBuffer.popContext("availableSequenceNumbers", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("TransferResult");
    }

    public static class TransferResultBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final StatusCode statusCode;
        private final int noOfAvailableSequenceNumbers;
        private final long[] availableSequenceNumbers;

        public TransferResultBuilder(StatusCode statusCode, int noOfAvailableSequenceNumbers, long[] availableSequenceNumbers) {
            this.statusCode = statusCode;
            this.noOfAvailableSequenceNumbers = noOfAvailableSequenceNumbers;
            this.availableSequenceNumbers = availableSequenceNumbers;
        }

        public TransferResult build() {
            return new TransferResult(statusCode, noOfAvailableSequenceNumbers, availableSequenceNumbers);
        }
    }

}
