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

public class PubSubGroupDataTypeIO implements MessageIO<PubSubGroupDataType, PubSubGroupDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PubSubGroupDataTypeIO.class);

    @Override
    public PubSubGroupDataType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (PubSubGroupDataType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, PubSubGroupDataType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static PubSubGroupDataTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("PubSubGroupDataType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("name");

        // Simple Field (name)
PascalString name = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("name");

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 7);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (enabled)
boolean enabled = readBuffer.readBit("enabled") ;
        readBuffer.pullContext("securityMode");

        // Simple Field (securityMode)
        // enum based simple field with type MessageSecurityMode
        MessageSecurityMode securityMode = MessageSecurityMode.enumForValue(readBuffer.readUnsignedLong("MessageSecurityMode", 32));
        readBuffer.closeContext("securityMode");

        readBuffer.pullContext("securityGroupId");

        // Simple Field (securityGroupId)
PascalString securityGroupId = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("securityGroupId");


        // Simple Field (noOfSecurityKeyServices)
int noOfSecurityKeyServices = readBuffer.readInt("noOfSecurityKeyServices", 32) ;        // Array field (securityKeyServices)
        readBuffer.pullContext("securityKeyServices", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfSecurityKeyServices > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfSecurityKeyServices) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] securityKeyServices;
        {
            int itemCount = Math.max(0, (int) noOfSecurityKeyServices);
            securityKeyServices = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
securityKeyServices[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(314) ) ;            }
        }
            readBuffer.closeContext("securityKeyServices", WithReaderWriterArgs.WithRenderAsList(true));


        // Simple Field (maxNetworkMessageSize)
long maxNetworkMessageSize = readBuffer.readUnsignedLong("maxNetworkMessageSize", 32) ;

        // Simple Field (noOfGroupProperties)
int noOfGroupProperties = readBuffer.readInt("noOfGroupProperties", 32) ;        // Array field (groupProperties)
        readBuffer.pullContext("groupProperties", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfGroupProperties > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfGroupProperties) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] groupProperties;
        {
            int itemCount = Math.max(0, (int) noOfGroupProperties);
            groupProperties = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
groupProperties[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(14535) ) ;            }
        }
            readBuffer.closeContext("groupProperties", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("PubSubGroupDataType");
        // Create the instance
        return new PubSubGroupDataTypeBuilder(name, enabled, securityMode, securityGroupId, noOfSecurityKeyServices, securityKeyServices, maxNetworkMessageSize, noOfGroupProperties, groupProperties);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, PubSubGroupDataType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("PubSubGroupDataType");

        // Simple Field (name)
        PascalString name = (PascalString) _value.getName();
        writeBuffer.pushContext("name");
        PascalStringIO.staticSerialize(writeBuffer, name);
        writeBuffer.popContext("name");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 7, ((Number) (short) 0x00).shortValue());

        // Simple Field (enabled)
        boolean enabled = (boolean) _value.getEnabled();
        writeBuffer.writeBit("enabled", (boolean) (enabled));

        // Simple Field (securityMode)
        MessageSecurityMode securityMode = (MessageSecurityMode) _value.getSecurityMode();
        writeBuffer.pushContext("securityMode");
        // enum field with type MessageSecurityMode
        writeBuffer.writeUnsignedLong("MessageSecurityMode", 32, ((Number) (securityMode.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(securityMode.name()));
        writeBuffer.popContext("securityMode");

        // Simple Field (securityGroupId)
        PascalString securityGroupId = (PascalString) _value.getSecurityGroupId();
        writeBuffer.pushContext("securityGroupId");
        PascalStringIO.staticSerialize(writeBuffer, securityGroupId);
        writeBuffer.popContext("securityGroupId");

        // Simple Field (noOfSecurityKeyServices)
        int noOfSecurityKeyServices = (int) _value.getNoOfSecurityKeyServices();
        writeBuffer.writeInt("noOfSecurityKeyServices", 32, ((Number) (noOfSecurityKeyServices)).intValue());

        // Array Field (securityKeyServices)
        if(_value.getSecurityKeyServices() != null) {
            writeBuffer.pushContext("securityKeyServices", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getSecurityKeyServices().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getSecurityKeyServices()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("securityKeyServices", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (maxNetworkMessageSize)
        long maxNetworkMessageSize = (long) _value.getMaxNetworkMessageSize();
        writeBuffer.writeUnsignedLong("maxNetworkMessageSize", 32, ((Number) (maxNetworkMessageSize)).longValue());

        // Simple Field (noOfGroupProperties)
        int noOfGroupProperties = (int) _value.getNoOfGroupProperties();
        writeBuffer.writeInt("noOfGroupProperties", 32, ((Number) (noOfGroupProperties)).intValue());

        // Array Field (groupProperties)
        if(_value.getGroupProperties() != null) {
            writeBuffer.pushContext("groupProperties", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getGroupProperties().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getGroupProperties()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("groupProperties", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("PubSubGroupDataType");
    }

    public static class PubSubGroupDataTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final PascalString name;
        private final boolean enabled;
        private final MessageSecurityMode securityMode;
        private final PascalString securityGroupId;
        private final int noOfSecurityKeyServices;
        private final ExtensionObjectDefinition[] securityKeyServices;
        private final long maxNetworkMessageSize;
        private final int noOfGroupProperties;
        private final ExtensionObjectDefinition[] groupProperties;

        public PubSubGroupDataTypeBuilder(PascalString name, boolean enabled, MessageSecurityMode securityMode, PascalString securityGroupId, int noOfSecurityKeyServices, ExtensionObjectDefinition[] securityKeyServices, long maxNetworkMessageSize, int noOfGroupProperties, ExtensionObjectDefinition[] groupProperties) {
            this.name = name;
            this.enabled = enabled;
            this.securityMode = securityMode;
            this.securityGroupId = securityGroupId;
            this.noOfSecurityKeyServices = noOfSecurityKeyServices;
            this.securityKeyServices = securityKeyServices;
            this.maxNetworkMessageSize = maxNetworkMessageSize;
            this.noOfGroupProperties = noOfGroupProperties;
            this.groupProperties = groupProperties;
        }

        public PubSubGroupDataType build() {
            return new PubSubGroupDataType(name, enabled, securityMode, securityGroupId, noOfSecurityKeyServices, securityKeyServices, maxNetworkMessageSize, noOfGroupProperties, groupProperties);
        }
    }

}
