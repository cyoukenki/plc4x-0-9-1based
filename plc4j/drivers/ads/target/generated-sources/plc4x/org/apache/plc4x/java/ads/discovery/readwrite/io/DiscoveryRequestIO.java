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
package org.apache.plc4x.java.ads.discovery.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.discovery.readwrite.*;
import org.apache.plc4x.java.ads.discovery.readwrite.io.*;
import org.apache.plc4x.java.ads.discovery.readwrite.types.*;
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

public class DiscoveryRequestIO implements MessageIO<DiscoveryRequest, DiscoveryRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscoveryRequestIO.class);

    @Override
    public DiscoveryRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (DiscoveryRequest) new AdsDiscoveryIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, DiscoveryRequest value, Object... args) throws ParseException {
        new AdsDiscoveryIO().serialize(writeBuffer, value, args);
    }

    public static DiscoveryRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("DiscoveryRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("amsNetId");

        // Simple Field (amsNetId)
AmsNetId amsNetId = AmsNetIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("amsNetId");

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            int reserved = readBuffer.readUnsignedInt("reserved", 16);
            if(reserved != (int) 0x1027) {
                LOGGER.info("Expected constant value " + 0x1027 + " but got " + reserved + " for reserved field.");
            }
        }

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            long reserved = readBuffer.readUnsignedLong("reserved", 32);
            if(reserved != (long) 0x00000000L) {
                LOGGER.info("Expected constant value " + 0x00000000L + " but got " + reserved + " for reserved field.");
            }
        }

        readBuffer.closeContext("DiscoveryRequest");
        // Create the instance
        return new DiscoveryRequestBuilder(amsNetId);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, DiscoveryRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("DiscoveryRequest");

        // Simple Field (amsNetId)
        AmsNetId amsNetId = (AmsNetId) _value.getAmsNetId();
        writeBuffer.pushContext("amsNetId");
        AmsNetIdIO.staticSerialize(writeBuffer, amsNetId);
        writeBuffer.popContext("amsNetId");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedInt("reserved", 16, ((Number) (int) 0x1027).intValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedLong("reserved", 32, ((Number) (long) 0x00000000L).longValue());
        writeBuffer.popContext("DiscoveryRequest");
    }

    public static class DiscoveryRequestBuilder implements AdsDiscoveryIO.AdsDiscoveryBuilder {
        private final AmsNetId amsNetId;

        public DiscoveryRequestBuilder(AmsNetId amsNetId) {
            this.amsNetId = amsNetId;
        }

        public DiscoveryRequest build(Operation operation, Direction direction) {
            return new DiscoveryRequest(operation, direction, amsNetId);
        }
    }

}
