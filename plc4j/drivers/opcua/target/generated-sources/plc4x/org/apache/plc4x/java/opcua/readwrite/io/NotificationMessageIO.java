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

public class NotificationMessageIO implements MessageIO<NotificationMessage, NotificationMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationMessageIO.class);

    @Override
    public NotificationMessage parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (NotificationMessage) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, NotificationMessage value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static NotificationMessageBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("NotificationMessage");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (sequenceNumber)
long sequenceNumber = readBuffer.readUnsignedLong("sequenceNumber", 32) ;

        // Simple Field (publishTime)
long publishTime = readBuffer.readLong("publishTime", 64) ;

        // Simple Field (noOfNotificationData)
int noOfNotificationData = readBuffer.readInt("noOfNotificationData", 32) ;        // Array field (notificationData)
        readBuffer.pullContext("notificationData", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfNotificationData > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfNotificationData) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObject[] notificationData;
        {
            int itemCount = Math.max(0, (int) noOfNotificationData);
            notificationData = new ExtensionObject[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
notificationData[curItem] = ExtensionObjectIO.staticParse(readBuffer , (boolean) (true) ) ;            }
        }
            readBuffer.closeContext("notificationData", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("NotificationMessage");
        // Create the instance
        return new NotificationMessageBuilder(sequenceNumber, publishTime, noOfNotificationData, notificationData);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, NotificationMessage _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("NotificationMessage");

        // Simple Field (sequenceNumber)
        long sequenceNumber = (long) _value.getSequenceNumber();
        writeBuffer.writeUnsignedLong("sequenceNumber", 32, ((Number) (sequenceNumber)).longValue());

        // Simple Field (publishTime)
        long publishTime = (long) _value.getPublishTime();
        writeBuffer.writeLong("publishTime", 64, ((Number) (publishTime)).longValue());

        // Simple Field (noOfNotificationData)
        int noOfNotificationData = (int) _value.getNoOfNotificationData();
        writeBuffer.writeInt("noOfNotificationData", 32, ((Number) (noOfNotificationData)).intValue());

        // Array Field (notificationData)
        if(_value.getNotificationData() != null) {
            writeBuffer.pushContext("notificationData", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getNotificationData().length;
            int curItem = 0;
            for(ExtensionObject element : _value.getNotificationData()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("notificationData", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("NotificationMessage");
    }

    public static class NotificationMessageBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final long sequenceNumber;
        private final long publishTime;
        private final int noOfNotificationData;
        private final ExtensionObject[] notificationData;

        public NotificationMessageBuilder(long sequenceNumber, long publishTime, int noOfNotificationData, ExtensionObject[] notificationData) {
            this.sequenceNumber = sequenceNumber;
            this.publishTime = publishTime;
            this.noOfNotificationData = noOfNotificationData;
            this.notificationData = notificationData;
        }

        public NotificationMessage build() {
            return new NotificationMessage(sequenceNumber, publishTime, noOfNotificationData, notificationData);
        }
    }

}
