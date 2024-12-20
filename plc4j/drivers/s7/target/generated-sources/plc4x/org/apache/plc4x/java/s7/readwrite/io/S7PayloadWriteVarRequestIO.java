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

public class S7PayloadWriteVarRequestIO implements MessageIO<S7PayloadWriteVarRequest, S7PayloadWriteVarRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7PayloadWriteVarRequestIO.class);

    @Override
    public S7PayloadWriteVarRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S7PayloadWriteVarRequest) new S7PayloadIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7PayloadWriteVarRequest value, Object... args) throws ParseException {
        new S7PayloadIO().serialize(writeBuffer, value, args);
    }

    public static S7PayloadWriteVarRequestBuilder staticParse(ReadBuffer readBuffer, S7Parameter parameter) throws ParseException {
        readBuffer.pullContext("S7PayloadWriteVarRequest");
        int startPos = readBuffer.getPos();
        int curPos;
        // Array field (items)
        readBuffer.pullContext("items", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(COUNT(CAST(parameter, S7ParameterWriteVarRequest.class).getItems()) > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (COUNT(CAST(parameter, S7ParameterWriteVarRequest.class).getItems())) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        S7VarPayloadDataItem[] items;
        {
            int itemCount = Math.max(0, (int) COUNT(CAST(parameter, S7ParameterWriteVarRequest.class).getItems()));
            items = new S7VarPayloadDataItem[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
items[curItem] = S7VarPayloadDataItemIO.staticParse(readBuffer , (boolean) (lastItem) ) ;            }
        }
            readBuffer.closeContext("items", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("S7PayloadWriteVarRequest");
        // Create the instance
        return new S7PayloadWriteVarRequestBuilder(items);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7PayloadWriteVarRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7PayloadWriteVarRequest");

        // Array Field (items)
        if(_value.getItems() != null) {
            writeBuffer.pushContext("items", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getItems().length;
            int curItem = 0;
            for(S7VarPayloadDataItem element : _value.getItems()) {
                boolean lastItem = curItem == (itemCount - 1);
                S7VarPayloadDataItemIO.staticSerialize(writeBuffer, element, lastItem);
                curItem++;
            }
            writeBuffer.popContext("items", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("S7PayloadWriteVarRequest");
    }

    public static class S7PayloadWriteVarRequestBuilder implements S7PayloadIO.S7PayloadBuilder {
        private final S7VarPayloadDataItem[] items;

        public S7PayloadWriteVarRequestBuilder(S7VarPayloadDataItem[] items) {
            this.items = items;
        }

        public S7PayloadWriteVarRequest build() {
            return new S7PayloadWriteVarRequest(items);
        }
    }

}
