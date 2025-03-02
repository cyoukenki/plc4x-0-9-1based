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

public class CreateSubscriptionResponseIO implements MessageIO<CreateSubscriptionResponse, CreateSubscriptionResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSubscriptionResponseIO.class);

    @Override
    public CreateSubscriptionResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (CreateSubscriptionResponse) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CreateSubscriptionResponse value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static CreateSubscriptionResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("CreateSubscriptionResponse");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("responseHeader");

        // Simple Field (responseHeader)
ExtensionObjectDefinition responseHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(394) ) ;        readBuffer.closeContext("responseHeader");


        // Simple Field (subscriptionId)
long subscriptionId = readBuffer.readUnsignedLong("subscriptionId", 32) ;

        // Simple Field (revisedPublishingInterval)
double revisedPublishingInterval = ((Supplier<Double>) (() -> { return (double) toFloat(readBuffer, "revisedPublishingInterval", true, 11, 52); })).get() ;

        // Simple Field (revisedLifetimeCount)
long revisedLifetimeCount = readBuffer.readUnsignedLong("revisedLifetimeCount", 32) ;

        // Simple Field (revisedMaxKeepAliveCount)
long revisedMaxKeepAliveCount = readBuffer.readUnsignedLong("revisedMaxKeepAliveCount", 32) ;
        readBuffer.closeContext("CreateSubscriptionResponse");
        // Create the instance
        return new CreateSubscriptionResponseBuilder(responseHeader, subscriptionId, revisedPublishingInterval, revisedLifetimeCount, revisedMaxKeepAliveCount);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CreateSubscriptionResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CreateSubscriptionResponse");

        // Simple Field (responseHeader)
        ExtensionObjectDefinition responseHeader = (ExtensionObjectDefinition) _value.getResponseHeader();
        writeBuffer.pushContext("responseHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, responseHeader);
        writeBuffer.popContext("responseHeader");

        // Simple Field (subscriptionId)
        long subscriptionId = (long) _value.getSubscriptionId();
        writeBuffer.writeUnsignedLong("subscriptionId", 32, ((Number) (subscriptionId)).longValue());

        // Simple Field (revisedPublishingInterval)
        double revisedPublishingInterval = (double) _value.getRevisedPublishingInterval();
        writeBuffer.writeDouble("revisedPublishingInterval", (revisedPublishingInterval),11,52);

        // Simple Field (revisedLifetimeCount)
        long revisedLifetimeCount = (long) _value.getRevisedLifetimeCount();
        writeBuffer.writeUnsignedLong("revisedLifetimeCount", 32, ((Number) (revisedLifetimeCount)).longValue());

        // Simple Field (revisedMaxKeepAliveCount)
        long revisedMaxKeepAliveCount = (long) _value.getRevisedMaxKeepAliveCount();
        writeBuffer.writeUnsignedLong("revisedMaxKeepAliveCount", 32, ((Number) (revisedMaxKeepAliveCount)).longValue());
        writeBuffer.popContext("CreateSubscriptionResponse");
    }

    public static class CreateSubscriptionResponseBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition responseHeader;
        private final long subscriptionId;
        private final double revisedPublishingInterval;
        private final long revisedLifetimeCount;
        private final long revisedMaxKeepAliveCount;

        public CreateSubscriptionResponseBuilder(ExtensionObjectDefinition responseHeader, long subscriptionId, double revisedPublishingInterval, long revisedLifetimeCount, long revisedMaxKeepAliveCount) {
            this.responseHeader = responseHeader;
            this.subscriptionId = subscriptionId;
            this.revisedPublishingInterval = revisedPublishingInterval;
            this.revisedLifetimeCount = revisedLifetimeCount;
            this.revisedMaxKeepAliveCount = revisedMaxKeepAliveCount;
        }

        public CreateSubscriptionResponse build() {
            return new CreateSubscriptionResponse(responseHeader, subscriptionId, revisedPublishingInterval, revisedLifetimeCount, revisedMaxKeepAliveCount);
        }
    }

}
