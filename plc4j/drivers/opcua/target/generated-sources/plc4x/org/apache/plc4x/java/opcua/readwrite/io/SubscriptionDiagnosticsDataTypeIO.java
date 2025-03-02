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

public class SubscriptionDiagnosticsDataTypeIO implements MessageIO<SubscriptionDiagnosticsDataType, SubscriptionDiagnosticsDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionDiagnosticsDataTypeIO.class);

    @Override
    public SubscriptionDiagnosticsDataType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (SubscriptionDiagnosticsDataType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, SubscriptionDiagnosticsDataType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static SubscriptionDiagnosticsDataTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("SubscriptionDiagnosticsDataType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("sessionId");

        // Simple Field (sessionId)
NodeId sessionId = NodeIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("sessionId");


        // Simple Field (subscriptionId)
long subscriptionId = readBuffer.readUnsignedLong("subscriptionId", 32) ;

        // Simple Field (priority)
short priority = readBuffer.readUnsignedShort("priority", 8) ;

        // Simple Field (publishingInterval)
double publishingInterval = ((Supplier<Double>) (() -> { return (double) toFloat(readBuffer, "publishingInterval", true, 11, 52); })).get() ;

        // Simple Field (maxKeepAliveCount)
long maxKeepAliveCount = readBuffer.readUnsignedLong("maxKeepAliveCount", 32) ;

        // Simple Field (maxLifetimeCount)
long maxLifetimeCount = readBuffer.readUnsignedLong("maxLifetimeCount", 32) ;

        // Simple Field (maxNotificationsPerPublish)
long maxNotificationsPerPublish = readBuffer.readUnsignedLong("maxNotificationsPerPublish", 32) ;
        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 7);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (publishingEnabled)
boolean publishingEnabled = readBuffer.readBit("publishingEnabled") ;

        // Simple Field (modifyCount)
long modifyCount = readBuffer.readUnsignedLong("modifyCount", 32) ;

        // Simple Field (enableCount)
long enableCount = readBuffer.readUnsignedLong("enableCount", 32) ;

        // Simple Field (disableCount)
long disableCount = readBuffer.readUnsignedLong("disableCount", 32) ;

        // Simple Field (republishRequestCount)
long republishRequestCount = readBuffer.readUnsignedLong("republishRequestCount", 32) ;

        // Simple Field (republishMessageRequestCount)
long republishMessageRequestCount = readBuffer.readUnsignedLong("republishMessageRequestCount", 32) ;

        // Simple Field (republishMessageCount)
long republishMessageCount = readBuffer.readUnsignedLong("republishMessageCount", 32) ;

        // Simple Field (transferRequestCount)
long transferRequestCount = readBuffer.readUnsignedLong("transferRequestCount", 32) ;

        // Simple Field (transferredToAltClientCount)
long transferredToAltClientCount = readBuffer.readUnsignedLong("transferredToAltClientCount", 32) ;

        // Simple Field (transferredToSameClientCount)
long transferredToSameClientCount = readBuffer.readUnsignedLong("transferredToSameClientCount", 32) ;

        // Simple Field (publishRequestCount)
long publishRequestCount = readBuffer.readUnsignedLong("publishRequestCount", 32) ;

        // Simple Field (dataChangeNotificationsCount)
long dataChangeNotificationsCount = readBuffer.readUnsignedLong("dataChangeNotificationsCount", 32) ;

        // Simple Field (eventNotificationsCount)
long eventNotificationsCount = readBuffer.readUnsignedLong("eventNotificationsCount", 32) ;

        // Simple Field (notificationsCount)
long notificationsCount = readBuffer.readUnsignedLong("notificationsCount", 32) ;

        // Simple Field (latePublishRequestCount)
long latePublishRequestCount = readBuffer.readUnsignedLong("latePublishRequestCount", 32) ;

        // Simple Field (currentKeepAliveCount)
long currentKeepAliveCount = readBuffer.readUnsignedLong("currentKeepAliveCount", 32) ;

        // Simple Field (currentLifetimeCount)
long currentLifetimeCount = readBuffer.readUnsignedLong("currentLifetimeCount", 32) ;

        // Simple Field (unacknowledgedMessageCount)
long unacknowledgedMessageCount = readBuffer.readUnsignedLong("unacknowledgedMessageCount", 32) ;

        // Simple Field (discardedMessageCount)
long discardedMessageCount = readBuffer.readUnsignedLong("discardedMessageCount", 32) ;

        // Simple Field (monitoredItemCount)
long monitoredItemCount = readBuffer.readUnsignedLong("monitoredItemCount", 32) ;

        // Simple Field (disabledMonitoredItemCount)
long disabledMonitoredItemCount = readBuffer.readUnsignedLong("disabledMonitoredItemCount", 32) ;

        // Simple Field (monitoringQueueOverflowCount)
long monitoringQueueOverflowCount = readBuffer.readUnsignedLong("monitoringQueueOverflowCount", 32) ;

        // Simple Field (nextSequenceNumber)
long nextSequenceNumber = readBuffer.readUnsignedLong("nextSequenceNumber", 32) ;

        // Simple Field (eventQueueOverFlowCount)
long eventQueueOverFlowCount = readBuffer.readUnsignedLong("eventQueueOverFlowCount", 32) ;
        readBuffer.closeContext("SubscriptionDiagnosticsDataType");
        // Create the instance
        return new SubscriptionDiagnosticsDataTypeBuilder(sessionId, subscriptionId, priority, publishingInterval, maxKeepAliveCount, maxLifetimeCount, maxNotificationsPerPublish, publishingEnabled, modifyCount, enableCount, disableCount, republishRequestCount, republishMessageRequestCount, republishMessageCount, transferRequestCount, transferredToAltClientCount, transferredToSameClientCount, publishRequestCount, dataChangeNotificationsCount, eventNotificationsCount, notificationsCount, latePublishRequestCount, currentKeepAliveCount, currentLifetimeCount, unacknowledgedMessageCount, discardedMessageCount, monitoredItemCount, disabledMonitoredItemCount, monitoringQueueOverflowCount, nextSequenceNumber, eventQueueOverFlowCount);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, SubscriptionDiagnosticsDataType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("SubscriptionDiagnosticsDataType");

        // Simple Field (sessionId)
        NodeId sessionId = (NodeId) _value.getSessionId();
        writeBuffer.pushContext("sessionId");
        NodeIdIO.staticSerialize(writeBuffer, sessionId);
        writeBuffer.popContext("sessionId");

        // Simple Field (subscriptionId)
        long subscriptionId = (long) _value.getSubscriptionId();
        writeBuffer.writeUnsignedLong("subscriptionId", 32, ((Number) (subscriptionId)).longValue());

        // Simple Field (priority)
        short priority = (short) _value.getPriority();
        writeBuffer.writeUnsignedShort("priority", 8, ((Number) (priority)).shortValue());

        // Simple Field (publishingInterval)
        double publishingInterval = (double) _value.getPublishingInterval();
        writeBuffer.writeDouble("publishingInterval", (publishingInterval),11,52);

        // Simple Field (maxKeepAliveCount)
        long maxKeepAliveCount = (long) _value.getMaxKeepAliveCount();
        writeBuffer.writeUnsignedLong("maxKeepAliveCount", 32, ((Number) (maxKeepAliveCount)).longValue());

        // Simple Field (maxLifetimeCount)
        long maxLifetimeCount = (long) _value.getMaxLifetimeCount();
        writeBuffer.writeUnsignedLong("maxLifetimeCount", 32, ((Number) (maxLifetimeCount)).longValue());

        // Simple Field (maxNotificationsPerPublish)
        long maxNotificationsPerPublish = (long) _value.getMaxNotificationsPerPublish();
        writeBuffer.writeUnsignedLong("maxNotificationsPerPublish", 32, ((Number) (maxNotificationsPerPublish)).longValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 7, ((Number) (short) 0x00).shortValue());

        // Simple Field (publishingEnabled)
        boolean publishingEnabled = (boolean) _value.getPublishingEnabled();
        writeBuffer.writeBit("publishingEnabled", (boolean) (publishingEnabled));

        // Simple Field (modifyCount)
        long modifyCount = (long) _value.getModifyCount();
        writeBuffer.writeUnsignedLong("modifyCount", 32, ((Number) (modifyCount)).longValue());

        // Simple Field (enableCount)
        long enableCount = (long) _value.getEnableCount();
        writeBuffer.writeUnsignedLong("enableCount", 32, ((Number) (enableCount)).longValue());

        // Simple Field (disableCount)
        long disableCount = (long) _value.getDisableCount();
        writeBuffer.writeUnsignedLong("disableCount", 32, ((Number) (disableCount)).longValue());

        // Simple Field (republishRequestCount)
        long republishRequestCount = (long) _value.getRepublishRequestCount();
        writeBuffer.writeUnsignedLong("republishRequestCount", 32, ((Number) (republishRequestCount)).longValue());

        // Simple Field (republishMessageRequestCount)
        long republishMessageRequestCount = (long) _value.getRepublishMessageRequestCount();
        writeBuffer.writeUnsignedLong("republishMessageRequestCount", 32, ((Number) (republishMessageRequestCount)).longValue());

        // Simple Field (republishMessageCount)
        long republishMessageCount = (long) _value.getRepublishMessageCount();
        writeBuffer.writeUnsignedLong("republishMessageCount", 32, ((Number) (republishMessageCount)).longValue());

        // Simple Field (transferRequestCount)
        long transferRequestCount = (long) _value.getTransferRequestCount();
        writeBuffer.writeUnsignedLong("transferRequestCount", 32, ((Number) (transferRequestCount)).longValue());

        // Simple Field (transferredToAltClientCount)
        long transferredToAltClientCount = (long) _value.getTransferredToAltClientCount();
        writeBuffer.writeUnsignedLong("transferredToAltClientCount", 32, ((Number) (transferredToAltClientCount)).longValue());

        // Simple Field (transferredToSameClientCount)
        long transferredToSameClientCount = (long) _value.getTransferredToSameClientCount();
        writeBuffer.writeUnsignedLong("transferredToSameClientCount", 32, ((Number) (transferredToSameClientCount)).longValue());

        // Simple Field (publishRequestCount)
        long publishRequestCount = (long) _value.getPublishRequestCount();
        writeBuffer.writeUnsignedLong("publishRequestCount", 32, ((Number) (publishRequestCount)).longValue());

        // Simple Field (dataChangeNotificationsCount)
        long dataChangeNotificationsCount = (long) _value.getDataChangeNotificationsCount();
        writeBuffer.writeUnsignedLong("dataChangeNotificationsCount", 32, ((Number) (dataChangeNotificationsCount)).longValue());

        // Simple Field (eventNotificationsCount)
        long eventNotificationsCount = (long) _value.getEventNotificationsCount();
        writeBuffer.writeUnsignedLong("eventNotificationsCount", 32, ((Number) (eventNotificationsCount)).longValue());

        // Simple Field (notificationsCount)
        long notificationsCount = (long) _value.getNotificationsCount();
        writeBuffer.writeUnsignedLong("notificationsCount", 32, ((Number) (notificationsCount)).longValue());

        // Simple Field (latePublishRequestCount)
        long latePublishRequestCount = (long) _value.getLatePublishRequestCount();
        writeBuffer.writeUnsignedLong("latePublishRequestCount", 32, ((Number) (latePublishRequestCount)).longValue());

        // Simple Field (currentKeepAliveCount)
        long currentKeepAliveCount = (long) _value.getCurrentKeepAliveCount();
        writeBuffer.writeUnsignedLong("currentKeepAliveCount", 32, ((Number) (currentKeepAliveCount)).longValue());

        // Simple Field (currentLifetimeCount)
        long currentLifetimeCount = (long) _value.getCurrentLifetimeCount();
        writeBuffer.writeUnsignedLong("currentLifetimeCount", 32, ((Number) (currentLifetimeCount)).longValue());

        // Simple Field (unacknowledgedMessageCount)
        long unacknowledgedMessageCount = (long) _value.getUnacknowledgedMessageCount();
        writeBuffer.writeUnsignedLong("unacknowledgedMessageCount", 32, ((Number) (unacknowledgedMessageCount)).longValue());

        // Simple Field (discardedMessageCount)
        long discardedMessageCount = (long) _value.getDiscardedMessageCount();
        writeBuffer.writeUnsignedLong("discardedMessageCount", 32, ((Number) (discardedMessageCount)).longValue());

        // Simple Field (monitoredItemCount)
        long monitoredItemCount = (long) _value.getMonitoredItemCount();
        writeBuffer.writeUnsignedLong("monitoredItemCount", 32, ((Number) (monitoredItemCount)).longValue());

        // Simple Field (disabledMonitoredItemCount)
        long disabledMonitoredItemCount = (long) _value.getDisabledMonitoredItemCount();
        writeBuffer.writeUnsignedLong("disabledMonitoredItemCount", 32, ((Number) (disabledMonitoredItemCount)).longValue());

        // Simple Field (monitoringQueueOverflowCount)
        long monitoringQueueOverflowCount = (long) _value.getMonitoringQueueOverflowCount();
        writeBuffer.writeUnsignedLong("monitoringQueueOverflowCount", 32, ((Number) (monitoringQueueOverflowCount)).longValue());

        // Simple Field (nextSequenceNumber)
        long nextSequenceNumber = (long) _value.getNextSequenceNumber();
        writeBuffer.writeUnsignedLong("nextSequenceNumber", 32, ((Number) (nextSequenceNumber)).longValue());

        // Simple Field (eventQueueOverFlowCount)
        long eventQueueOverFlowCount = (long) _value.getEventQueueOverFlowCount();
        writeBuffer.writeUnsignedLong("eventQueueOverFlowCount", 32, ((Number) (eventQueueOverFlowCount)).longValue());
        writeBuffer.popContext("SubscriptionDiagnosticsDataType");
    }

    public static class SubscriptionDiagnosticsDataTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final NodeId sessionId;
        private final long subscriptionId;
        private final short priority;
        private final double publishingInterval;
        private final long maxKeepAliveCount;
        private final long maxLifetimeCount;
        private final long maxNotificationsPerPublish;
        private final boolean publishingEnabled;
        private final long modifyCount;
        private final long enableCount;
        private final long disableCount;
        private final long republishRequestCount;
        private final long republishMessageRequestCount;
        private final long republishMessageCount;
        private final long transferRequestCount;
        private final long transferredToAltClientCount;
        private final long transferredToSameClientCount;
        private final long publishRequestCount;
        private final long dataChangeNotificationsCount;
        private final long eventNotificationsCount;
        private final long notificationsCount;
        private final long latePublishRequestCount;
        private final long currentKeepAliveCount;
        private final long currentLifetimeCount;
        private final long unacknowledgedMessageCount;
        private final long discardedMessageCount;
        private final long monitoredItemCount;
        private final long disabledMonitoredItemCount;
        private final long monitoringQueueOverflowCount;
        private final long nextSequenceNumber;
        private final long eventQueueOverFlowCount;

        public SubscriptionDiagnosticsDataTypeBuilder(NodeId sessionId, long subscriptionId, short priority, double publishingInterval, long maxKeepAliveCount, long maxLifetimeCount, long maxNotificationsPerPublish, boolean publishingEnabled, long modifyCount, long enableCount, long disableCount, long republishRequestCount, long republishMessageRequestCount, long republishMessageCount, long transferRequestCount, long transferredToAltClientCount, long transferredToSameClientCount, long publishRequestCount, long dataChangeNotificationsCount, long eventNotificationsCount, long notificationsCount, long latePublishRequestCount, long currentKeepAliveCount, long currentLifetimeCount, long unacknowledgedMessageCount, long discardedMessageCount, long monitoredItemCount, long disabledMonitoredItemCount, long monitoringQueueOverflowCount, long nextSequenceNumber, long eventQueueOverFlowCount) {
            this.sessionId = sessionId;
            this.subscriptionId = subscriptionId;
            this.priority = priority;
            this.publishingInterval = publishingInterval;
            this.maxKeepAliveCount = maxKeepAliveCount;
            this.maxLifetimeCount = maxLifetimeCount;
            this.maxNotificationsPerPublish = maxNotificationsPerPublish;
            this.publishingEnabled = publishingEnabled;
            this.modifyCount = modifyCount;
            this.enableCount = enableCount;
            this.disableCount = disableCount;
            this.republishRequestCount = republishRequestCount;
            this.republishMessageRequestCount = republishMessageRequestCount;
            this.republishMessageCount = republishMessageCount;
            this.transferRequestCount = transferRequestCount;
            this.transferredToAltClientCount = transferredToAltClientCount;
            this.transferredToSameClientCount = transferredToSameClientCount;
            this.publishRequestCount = publishRequestCount;
            this.dataChangeNotificationsCount = dataChangeNotificationsCount;
            this.eventNotificationsCount = eventNotificationsCount;
            this.notificationsCount = notificationsCount;
            this.latePublishRequestCount = latePublishRequestCount;
            this.currentKeepAliveCount = currentKeepAliveCount;
            this.currentLifetimeCount = currentLifetimeCount;
            this.unacknowledgedMessageCount = unacknowledgedMessageCount;
            this.discardedMessageCount = discardedMessageCount;
            this.monitoredItemCount = monitoredItemCount;
            this.disabledMonitoredItemCount = disabledMonitoredItemCount;
            this.monitoringQueueOverflowCount = monitoringQueueOverflowCount;
            this.nextSequenceNumber = nextSequenceNumber;
            this.eventQueueOverFlowCount = eventQueueOverFlowCount;
        }

        public SubscriptionDiagnosticsDataType build() {
            return new SubscriptionDiagnosticsDataType(sessionId, subscriptionId, priority, publishingInterval, maxKeepAliveCount, maxLifetimeCount, maxNotificationsPerPublish, publishingEnabled, modifyCount, enableCount, disableCount, republishRequestCount, republishMessageRequestCount, republishMessageCount, transferRequestCount, transferredToAltClientCount, transferredToSameClientCount, publishRequestCount, dataChangeNotificationsCount, eventNotificationsCount, notificationsCount, latePublishRequestCount, currentKeepAliveCount, currentLifetimeCount, unacknowledgedMessageCount, discardedMessageCount, monitoredItemCount, disabledMonitoredItemCount, monitoringQueueOverflowCount, nextSequenceNumber, eventQueueOverFlowCount);
        }
    }

}
