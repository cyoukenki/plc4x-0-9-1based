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

public class MonitoredItemModifyResultIO implements MessageIO<MonitoredItemModifyResult, MonitoredItemModifyResult> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredItemModifyResultIO.class);

    @Override
    public MonitoredItemModifyResult parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (MonitoredItemModifyResult) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, MonitoredItemModifyResult value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static MonitoredItemModifyResultBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("MonitoredItemModifyResult");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("statusCode");

        // Simple Field (statusCode)
StatusCode statusCode = StatusCodeIO.staticParse(readBuffer ) ;        readBuffer.closeContext("statusCode");


        // Simple Field (revisedSamplingInterval)
double revisedSamplingInterval = ((Supplier<Double>) (() -> { return (double) toFloat(readBuffer, "revisedSamplingInterval", true, 11, 52); })).get() ;

        // Simple Field (revisedQueueSize)
long revisedQueueSize = readBuffer.readUnsignedLong("revisedQueueSize", 32) ;
        readBuffer.pullContext("filterResult");

        // Simple Field (filterResult)
ExtensionObject filterResult = ExtensionObjectIO.staticParse(readBuffer , (boolean) (true) ) ;        readBuffer.closeContext("filterResult");

        readBuffer.closeContext("MonitoredItemModifyResult");
        // Create the instance
        return new MonitoredItemModifyResultBuilder(statusCode, revisedSamplingInterval, revisedQueueSize, filterResult);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, MonitoredItemModifyResult _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("MonitoredItemModifyResult");

        // Simple Field (statusCode)
        StatusCode statusCode = (StatusCode) _value.getStatusCode();
        writeBuffer.pushContext("statusCode");
        StatusCodeIO.staticSerialize(writeBuffer, statusCode);
        writeBuffer.popContext("statusCode");

        // Simple Field (revisedSamplingInterval)
        double revisedSamplingInterval = (double) _value.getRevisedSamplingInterval();
        writeBuffer.writeDouble("revisedSamplingInterval", (revisedSamplingInterval),11,52);

        // Simple Field (revisedQueueSize)
        long revisedQueueSize = (long) _value.getRevisedQueueSize();
        writeBuffer.writeUnsignedLong("revisedQueueSize", 32, ((Number) (revisedQueueSize)).longValue());

        // Simple Field (filterResult)
        ExtensionObject filterResult = (ExtensionObject) _value.getFilterResult();
        writeBuffer.pushContext("filterResult");
        ExtensionObjectIO.staticSerialize(writeBuffer, filterResult);
        writeBuffer.popContext("filterResult");
        writeBuffer.popContext("MonitoredItemModifyResult");
    }

    public static class MonitoredItemModifyResultBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final StatusCode statusCode;
        private final double revisedSamplingInterval;
        private final long revisedQueueSize;
        private final ExtensionObject filterResult;

        public MonitoredItemModifyResultBuilder(StatusCode statusCode, double revisedSamplingInterval, long revisedQueueSize, ExtensionObject filterResult) {
            this.statusCode = statusCode;
            this.revisedSamplingInterval = revisedSamplingInterval;
            this.revisedQueueSize = revisedQueueSize;
            this.filterResult = filterResult;
        }

        public MonitoredItemModifyResult build() {
            return new MonitoredItemModifyResult(statusCode, revisedSamplingInterval, revisedQueueSize, filterResult);
        }
    }

}
