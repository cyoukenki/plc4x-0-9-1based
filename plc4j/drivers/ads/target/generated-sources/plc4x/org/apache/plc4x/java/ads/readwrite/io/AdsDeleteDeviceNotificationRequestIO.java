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
package org.apache.plc4x.java.ads.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.readwrite.*;
import org.apache.plc4x.java.ads.readwrite.io.*;
import org.apache.plc4x.java.ads.readwrite.types.*;
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

public class AdsDeleteDeviceNotificationRequestIO implements MessageIO<AdsDeleteDeviceNotificationRequest, AdsDeleteDeviceNotificationRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdsDeleteDeviceNotificationRequestIO.class);

    @Override
    public AdsDeleteDeviceNotificationRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (AdsDeleteDeviceNotificationRequest) new AdsDataIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AdsDeleteDeviceNotificationRequest value, Object... args) throws ParseException {
        new AdsDataIO().serialize(writeBuffer, value, args);
    }

    public static AdsDeleteDeviceNotificationRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AdsDeleteDeviceNotificationRequest");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (notificationHandle)
long notificationHandle = readBuffer.readUnsignedLong("notificationHandle", 32) ;
        readBuffer.closeContext("AdsDeleteDeviceNotificationRequest");
        // Create the instance
        return new AdsDeleteDeviceNotificationRequestBuilder(notificationHandle);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AdsDeleteDeviceNotificationRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AdsDeleteDeviceNotificationRequest");

        // Simple Field (notificationHandle)
        long notificationHandle = (long) _value.getNotificationHandle();
        writeBuffer.writeUnsignedLong("notificationHandle", 32, ((Number) (notificationHandle)).longValue());
        writeBuffer.popContext("AdsDeleteDeviceNotificationRequest");
    }

    public static class AdsDeleteDeviceNotificationRequestBuilder implements AdsDataIO.AdsDataBuilder {
        private final long notificationHandle;

        public AdsDeleteDeviceNotificationRequestBuilder(long notificationHandle) {
            this.notificationHandle = notificationHandle;
        }

        public AdsDeleteDeviceNotificationRequest build() {
            return new AdsDeleteDeviceNotificationRequest(notificationHandle);
        }
    }

}
