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

public class SetPublishingModeRequestIO implements MessageIO<SetPublishingModeRequest, SetPublishingModeRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetPublishingModeRequestIO.class);

    @Override
    public SetPublishingModeRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (SetPublishingModeRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, SetPublishingModeRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static SetPublishingModeRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("SetPublishingModeRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 7);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (publishingEnabled)
boolean publishingEnabled = readBuffer.readBit("publishingEnabled") ;

        // Simple Field (noOfSubscriptionIds)
int noOfSubscriptionIds = readBuffer.readInt("noOfSubscriptionIds", 32) ;        // Array field (subscriptionIds)
        readBuffer.pullContext("subscriptionIds", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfSubscriptionIds > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfSubscriptionIds) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        long[] subscriptionIds;
        {
            int itemCount = Math.max(0, (int) noOfSubscriptionIds);
            subscriptionIds = new long[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
subscriptionIds[curItem] = readBuffer.readUnsignedLong("", 32) ;            }
        }
            readBuffer.closeContext("subscriptionIds", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("SetPublishingModeRequest");
        // Create the instance
        return new SetPublishingModeRequestBuilder(requestHeader, publishingEnabled, noOfSubscriptionIds, subscriptionIds);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, SetPublishingModeRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("SetPublishingModeRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 7, ((Number) (short) 0x00).shortValue());

        // Simple Field (publishingEnabled)
        boolean publishingEnabled = (boolean) _value.getPublishingEnabled();
        writeBuffer.writeBit("publishingEnabled", (boolean) (publishingEnabled));

        // Simple Field (noOfSubscriptionIds)
        int noOfSubscriptionIds = (int) _value.getNoOfSubscriptionIds();
        writeBuffer.writeInt("noOfSubscriptionIds", 32, ((Number) (noOfSubscriptionIds)).intValue());

        // Array Field (subscriptionIds)
        if(_value.getSubscriptionIds() != null) {
            writeBuffer.pushContext("subscriptionIds", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getSubscriptionIds().length;
            int curItem = 0;
            for(long element : _value.getSubscriptionIds()) {
                writeBuffer.writeUnsignedLong("", 32, ((Number) element).longValue());
                curItem++;
            }
            writeBuffer.popContext("subscriptionIds", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("SetPublishingModeRequest");
    }

    public static class SetPublishingModeRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final boolean publishingEnabled;
        private final int noOfSubscriptionIds;
        private final long[] subscriptionIds;

        public SetPublishingModeRequestBuilder(ExtensionObjectDefinition requestHeader, boolean publishingEnabled, int noOfSubscriptionIds, long[] subscriptionIds) {
            this.requestHeader = requestHeader;
            this.publishingEnabled = publishingEnabled;
            this.noOfSubscriptionIds = noOfSubscriptionIds;
            this.subscriptionIds = subscriptionIds;
        }

        public SetPublishingModeRequest build() {
            return new SetPublishingModeRequest(requestHeader, publishingEnabled, noOfSubscriptionIds, subscriptionIds);
        }
    }

}
