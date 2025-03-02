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

public class ReadRequestIO implements MessageIO<ReadRequest, ReadRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadRequestIO.class);

    @Override
    public ReadRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (ReadRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ReadRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static ReadRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("ReadRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");


        // Simple Field (maxAge)
double maxAge = ((Supplier<Double>) (() -> { return (double) toFloat(readBuffer, "maxAge", true, 11, 52); })).get() ;
        readBuffer.pullContext("timestampsToReturn");

        // Simple Field (timestampsToReturn)
        // enum based simple field with type TimestampsToReturn
        TimestampsToReturn timestampsToReturn = TimestampsToReturn.enumForValue(readBuffer.readUnsignedLong("TimestampsToReturn", 32));
        readBuffer.closeContext("timestampsToReturn");


        // Simple Field (noOfNodesToRead)
int noOfNodesToRead = readBuffer.readInt("noOfNodesToRead", 32) ;        // Array field (nodesToRead)
        readBuffer.pullContext("nodesToRead", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfNodesToRead > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfNodesToRead) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] nodesToRead;
        {
            int itemCount = Math.max(0, (int) noOfNodesToRead);
            nodesToRead = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
nodesToRead[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(628) ) ;            }
        }
            readBuffer.closeContext("nodesToRead", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("ReadRequest");
        // Create the instance
        return new ReadRequestBuilder(requestHeader, maxAge, timestampsToReturn, noOfNodesToRead, nodesToRead);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ReadRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ReadRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (maxAge)
        double maxAge = (double) _value.getMaxAge();
        writeBuffer.writeDouble("maxAge", (maxAge),11,52);

        // Simple Field (timestampsToReturn)
        TimestampsToReturn timestampsToReturn = (TimestampsToReturn) _value.getTimestampsToReturn();
        writeBuffer.pushContext("timestampsToReturn");
        // enum field with type TimestampsToReturn
        writeBuffer.writeUnsignedLong("TimestampsToReturn", 32, ((Number) (timestampsToReturn.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(timestampsToReturn.name()));
        writeBuffer.popContext("timestampsToReturn");

        // Simple Field (noOfNodesToRead)
        int noOfNodesToRead = (int) _value.getNoOfNodesToRead();
        writeBuffer.writeInt("noOfNodesToRead", 32, ((Number) (noOfNodesToRead)).intValue());

        // Array Field (nodesToRead)
        if(_value.getNodesToRead() != null) {
            writeBuffer.pushContext("nodesToRead", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getNodesToRead().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getNodesToRead()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("nodesToRead", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("ReadRequest");
    }

    public static class ReadRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final double maxAge;
        private final TimestampsToReturn timestampsToReturn;
        private final int noOfNodesToRead;
        private final ExtensionObjectDefinition[] nodesToRead;

        public ReadRequestBuilder(ExtensionObjectDefinition requestHeader, double maxAge, TimestampsToReturn timestampsToReturn, int noOfNodesToRead, ExtensionObjectDefinition[] nodesToRead) {
            this.requestHeader = requestHeader;
            this.maxAge = maxAge;
            this.timestampsToReturn = timestampsToReturn;
            this.noOfNodesToRead = noOfNodesToRead;
            this.nodesToRead = nodesToRead;
        }

        public ReadRequest build() {
            return new ReadRequest(requestHeader, maxAge, timestampsToReturn, noOfNodesToRead, nodesToRead);
        }
    }

}
