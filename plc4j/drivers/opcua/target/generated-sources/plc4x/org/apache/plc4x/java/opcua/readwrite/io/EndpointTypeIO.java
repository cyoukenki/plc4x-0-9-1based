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

public class EndpointTypeIO implements MessageIO<EndpointType, EndpointType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointTypeIO.class);

    @Override
    public EndpointType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (EndpointType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, EndpointType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static EndpointTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("EndpointType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("endpointUrl");

        // Simple Field (endpointUrl)
PascalString endpointUrl = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("endpointUrl");

        readBuffer.pullContext("securityMode");

        // Simple Field (securityMode)
        // enum based simple field with type MessageSecurityMode
        MessageSecurityMode securityMode = MessageSecurityMode.enumForValue(readBuffer.readUnsignedLong("MessageSecurityMode", 32));
        readBuffer.closeContext("securityMode");

        readBuffer.pullContext("securityPolicyUri");

        // Simple Field (securityPolicyUri)
PascalString securityPolicyUri = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("securityPolicyUri");

        readBuffer.pullContext("transportProfileUri");

        // Simple Field (transportProfileUri)
PascalString transportProfileUri = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("transportProfileUri");

        readBuffer.closeContext("EndpointType");
        // Create the instance
        return new EndpointTypeBuilder(endpointUrl, securityMode, securityPolicyUri, transportProfileUri);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, EndpointType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("EndpointType");

        // Simple Field (endpointUrl)
        PascalString endpointUrl = (PascalString) _value.getEndpointUrl();
        writeBuffer.pushContext("endpointUrl");
        PascalStringIO.staticSerialize(writeBuffer, endpointUrl);
        writeBuffer.popContext("endpointUrl");

        // Simple Field (securityMode)
        MessageSecurityMode securityMode = (MessageSecurityMode) _value.getSecurityMode();
        writeBuffer.pushContext("securityMode");
        // enum field with type MessageSecurityMode
        writeBuffer.writeUnsignedLong("MessageSecurityMode", 32, ((Number) (securityMode.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(securityMode.name()));
        writeBuffer.popContext("securityMode");

        // Simple Field (securityPolicyUri)
        PascalString securityPolicyUri = (PascalString) _value.getSecurityPolicyUri();
        writeBuffer.pushContext("securityPolicyUri");
        PascalStringIO.staticSerialize(writeBuffer, securityPolicyUri);
        writeBuffer.popContext("securityPolicyUri");

        // Simple Field (transportProfileUri)
        PascalString transportProfileUri = (PascalString) _value.getTransportProfileUri();
        writeBuffer.pushContext("transportProfileUri");
        PascalStringIO.staticSerialize(writeBuffer, transportProfileUri);
        writeBuffer.popContext("transportProfileUri");
        writeBuffer.popContext("EndpointType");
    }

    public static class EndpointTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final PascalString endpointUrl;
        private final MessageSecurityMode securityMode;
        private final PascalString securityPolicyUri;
        private final PascalString transportProfileUri;

        public EndpointTypeBuilder(PascalString endpointUrl, MessageSecurityMode securityMode, PascalString securityPolicyUri, PascalString transportProfileUri) {
            this.endpointUrl = endpointUrl;
            this.securityMode = securityMode;
            this.securityPolicyUri = securityPolicyUri;
            this.transportProfileUri = transportProfileUri;
        }

        public EndpointType build() {
            return new EndpointType(endpointUrl, securityMode, securityPolicyUri, transportProfileUri);
        }
    }

}