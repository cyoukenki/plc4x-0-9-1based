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

public class DeleteMonitoredItemsRequestIO implements MessageIO<DeleteMonitoredItemsRequest, DeleteMonitoredItemsRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteMonitoredItemsRequestIO.class);

    @Override
    public DeleteMonitoredItemsRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (DeleteMonitoredItemsRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, DeleteMonitoredItemsRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static DeleteMonitoredItemsRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("DeleteMonitoredItemsRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");


        // Simple Field (subscriptionId)
long subscriptionId = readBuffer.readUnsignedLong("subscriptionId", 32) ;

        // Simple Field (noOfMonitoredItemIds)
int noOfMonitoredItemIds = readBuffer.readInt("noOfMonitoredItemIds", 32) ;        // Array field (monitoredItemIds)
        readBuffer.pullContext("monitoredItemIds", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfMonitoredItemIds > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfMonitoredItemIds) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        long[] monitoredItemIds;
        {
            int itemCount = Math.max(0, (int) noOfMonitoredItemIds);
            monitoredItemIds = new long[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
monitoredItemIds[curItem] = readBuffer.readUnsignedLong("", 32) ;            }
        }
            readBuffer.closeContext("monitoredItemIds", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("DeleteMonitoredItemsRequest");
        // Create the instance
        return new DeleteMonitoredItemsRequestBuilder(requestHeader, subscriptionId, noOfMonitoredItemIds, monitoredItemIds);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, DeleteMonitoredItemsRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("DeleteMonitoredItemsRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (subscriptionId)
        long subscriptionId = (long) _value.getSubscriptionId();
        writeBuffer.writeUnsignedLong("subscriptionId", 32, ((Number) (subscriptionId)).longValue());

        // Simple Field (noOfMonitoredItemIds)
        int noOfMonitoredItemIds = (int) _value.getNoOfMonitoredItemIds();
        writeBuffer.writeInt("noOfMonitoredItemIds", 32, ((Number) (noOfMonitoredItemIds)).intValue());

        // Array Field (monitoredItemIds)
        if(_value.getMonitoredItemIds() != null) {
            writeBuffer.pushContext("monitoredItemIds", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getMonitoredItemIds().length;
            int curItem = 0;
            for(long element : _value.getMonitoredItemIds()) {
                writeBuffer.writeUnsignedLong("", 32, ((Number) element).longValue());
                curItem++;
            }
            writeBuffer.popContext("monitoredItemIds", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("DeleteMonitoredItemsRequest");
    }

    public static class DeleteMonitoredItemsRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final long subscriptionId;
        private final int noOfMonitoredItemIds;
        private final long[] monitoredItemIds;

        public DeleteMonitoredItemsRequestBuilder(ExtensionObjectDefinition requestHeader, long subscriptionId, int noOfMonitoredItemIds, long[] monitoredItemIds) {
            this.requestHeader = requestHeader;
            this.subscriptionId = subscriptionId;
            this.noOfMonitoredItemIds = noOfMonitoredItemIds;
            this.monitoredItemIds = monitoredItemIds;
        }

        public DeleteMonitoredItemsRequest build() {
            return new DeleteMonitoredItemsRequest(requestHeader, subscriptionId, noOfMonitoredItemIds, monitoredItemIds);
        }
    }

}