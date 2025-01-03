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

public class OpenSecureChannelRequestIO implements MessageIO<OpenSecureChannelRequest, OpenSecureChannelRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenSecureChannelRequestIO.class);

    @Override
    public OpenSecureChannelRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (OpenSecureChannelRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, OpenSecureChannelRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static OpenSecureChannelRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("OpenSecureChannelRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");


        // Simple Field (clientProtocolVersion)
long clientProtocolVersion = readBuffer.readUnsignedLong("clientProtocolVersion", 32) ;
        readBuffer.pullContext("requestType");

        // Simple Field (requestType)
        // enum based simple field with type SecurityTokenRequestType
        SecurityTokenRequestType requestType = SecurityTokenRequestType.enumForValue(readBuffer.readUnsignedLong("SecurityTokenRequestType", 32));
        readBuffer.closeContext("requestType");

        readBuffer.pullContext("securityMode");

        // Simple Field (securityMode)
        // enum based simple field with type MessageSecurityMode
        MessageSecurityMode securityMode = MessageSecurityMode.enumForValue(readBuffer.readUnsignedLong("MessageSecurityMode", 32));
        readBuffer.closeContext("securityMode");

        readBuffer.pullContext("clientNonce");

        // Simple Field (clientNonce)
PascalByteString clientNonce = PascalByteStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("clientNonce");


        // Simple Field (requestedLifetime)
long requestedLifetime = readBuffer.readUnsignedLong("requestedLifetime", 32) ;
        readBuffer.closeContext("OpenSecureChannelRequest");
        // Create the instance
        return new OpenSecureChannelRequestBuilder(requestHeader, clientProtocolVersion, requestType, securityMode, clientNonce, requestedLifetime);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, OpenSecureChannelRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("OpenSecureChannelRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (clientProtocolVersion)
        long clientProtocolVersion = (long) _value.getClientProtocolVersion();
        writeBuffer.writeUnsignedLong("clientProtocolVersion", 32, ((Number) (clientProtocolVersion)).longValue());

        // Simple Field (requestType)
        SecurityTokenRequestType requestType = (SecurityTokenRequestType) _value.getRequestType();
        writeBuffer.pushContext("requestType");
        // enum field with type SecurityTokenRequestType
        writeBuffer.writeUnsignedLong("SecurityTokenRequestType", 32, ((Number) (requestType.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(requestType.name()));
        writeBuffer.popContext("requestType");

        // Simple Field (securityMode)
        MessageSecurityMode securityMode = (MessageSecurityMode) _value.getSecurityMode();
        writeBuffer.pushContext("securityMode");
        // enum field with type MessageSecurityMode
        writeBuffer.writeUnsignedLong("MessageSecurityMode", 32, ((Number) (securityMode.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(securityMode.name()));
        writeBuffer.popContext("securityMode");

        // Simple Field (clientNonce)
        PascalByteString clientNonce = (PascalByteString) _value.getClientNonce();
        writeBuffer.pushContext("clientNonce");
        PascalByteStringIO.staticSerialize(writeBuffer, clientNonce);
        writeBuffer.popContext("clientNonce");

        // Simple Field (requestedLifetime)
        long requestedLifetime = (long) _value.getRequestedLifetime();
        writeBuffer.writeUnsignedLong("requestedLifetime", 32, ((Number) (requestedLifetime)).longValue());
        writeBuffer.popContext("OpenSecureChannelRequest");
    }

    public static class OpenSecureChannelRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final long clientProtocolVersion;
        private final SecurityTokenRequestType requestType;
        private final MessageSecurityMode securityMode;
        private final PascalByteString clientNonce;
        private final long requestedLifetime;

        public OpenSecureChannelRequestBuilder(ExtensionObjectDefinition requestHeader, long clientProtocolVersion, SecurityTokenRequestType requestType, MessageSecurityMode securityMode, PascalByteString clientNonce, long requestedLifetime) {
            this.requestHeader = requestHeader;
            this.clientProtocolVersion = clientProtocolVersion;
            this.requestType = requestType;
            this.securityMode = securityMode;
            this.clientNonce = clientNonce;
            this.requestedLifetime = requestedLifetime;
        }

        public OpenSecureChannelRequest build() {
            return new OpenSecureChannelRequest(requestHeader, clientProtocolVersion, requestType, securityMode, clientNonce, requestedLifetime);
        }
    }

}
