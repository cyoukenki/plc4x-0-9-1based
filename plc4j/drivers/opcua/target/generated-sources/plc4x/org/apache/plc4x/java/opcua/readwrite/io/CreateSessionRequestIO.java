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

public class CreateSessionRequestIO implements MessageIO<CreateSessionRequest, CreateSessionRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateSessionRequestIO.class);

    @Override
    public CreateSessionRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (CreateSessionRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CreateSessionRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static CreateSessionRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("CreateSessionRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");

        readBuffer.pullContext("clientDescription");

        // Simple Field (clientDescription)
ExtensionObjectDefinition clientDescription = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(310) ) ;        readBuffer.closeContext("clientDescription");

        readBuffer.pullContext("serverUri");

        // Simple Field (serverUri)
PascalString serverUri = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("serverUri");

        readBuffer.pullContext("endpointUrl");

        // Simple Field (endpointUrl)
PascalString endpointUrl = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("endpointUrl");

        readBuffer.pullContext("sessionName");

        // Simple Field (sessionName)
PascalString sessionName = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("sessionName");

        readBuffer.pullContext("clientNonce");

        // Simple Field (clientNonce)
PascalByteString clientNonce = PascalByteStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("clientNonce");

        readBuffer.pullContext("clientCertificate");

        // Simple Field (clientCertificate)
PascalByteString clientCertificate = PascalByteStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("clientCertificate");


        // Simple Field (requestedSessionTimeout)
double requestedSessionTimeout = ((Supplier<Double>) (() -> { return (double) toFloat(readBuffer, "requestedSessionTimeout", true, 11, 52); })).get() ;

        // Simple Field (maxResponseMessageSize)
long maxResponseMessageSize = readBuffer.readUnsignedLong("maxResponseMessageSize", 32) ;
        readBuffer.closeContext("CreateSessionRequest");
        // Create the instance
        return new CreateSessionRequestBuilder(requestHeader, clientDescription, serverUri, endpointUrl, sessionName, clientNonce, clientCertificate, requestedSessionTimeout, maxResponseMessageSize);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CreateSessionRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CreateSessionRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (clientDescription)
        ExtensionObjectDefinition clientDescription = (ExtensionObjectDefinition) _value.getClientDescription();
        writeBuffer.pushContext("clientDescription");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, clientDescription);
        writeBuffer.popContext("clientDescription");

        // Simple Field (serverUri)
        PascalString serverUri = (PascalString) _value.getServerUri();
        writeBuffer.pushContext("serverUri");
        PascalStringIO.staticSerialize(writeBuffer, serverUri);
        writeBuffer.popContext("serverUri");

        // Simple Field (endpointUrl)
        PascalString endpointUrl = (PascalString) _value.getEndpointUrl();
        writeBuffer.pushContext("endpointUrl");
        PascalStringIO.staticSerialize(writeBuffer, endpointUrl);
        writeBuffer.popContext("endpointUrl");

        // Simple Field (sessionName)
        PascalString sessionName = (PascalString) _value.getSessionName();
        writeBuffer.pushContext("sessionName");
        PascalStringIO.staticSerialize(writeBuffer, sessionName);
        writeBuffer.popContext("sessionName");

        // Simple Field (clientNonce)
        PascalByteString clientNonce = (PascalByteString) _value.getClientNonce();
        writeBuffer.pushContext("clientNonce");
        PascalByteStringIO.staticSerialize(writeBuffer, clientNonce);
        writeBuffer.popContext("clientNonce");

        // Simple Field (clientCertificate)
        PascalByteString clientCertificate = (PascalByteString) _value.getClientCertificate();
        writeBuffer.pushContext("clientCertificate");
        PascalByteStringIO.staticSerialize(writeBuffer, clientCertificate);
        writeBuffer.popContext("clientCertificate");

        // Simple Field (requestedSessionTimeout)
        double requestedSessionTimeout = (double) _value.getRequestedSessionTimeout();
        writeBuffer.writeDouble("requestedSessionTimeout", (requestedSessionTimeout),11,52);

        // Simple Field (maxResponseMessageSize)
        long maxResponseMessageSize = (long) _value.getMaxResponseMessageSize();
        writeBuffer.writeUnsignedLong("maxResponseMessageSize", 32, ((Number) (maxResponseMessageSize)).longValue());
        writeBuffer.popContext("CreateSessionRequest");
    }

    public static class CreateSessionRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final ExtensionObjectDefinition clientDescription;
        private final PascalString serverUri;
        private final PascalString endpointUrl;
        private final PascalString sessionName;
        private final PascalByteString clientNonce;
        private final PascalByteString clientCertificate;
        private final double requestedSessionTimeout;
        private final long maxResponseMessageSize;

        public CreateSessionRequestBuilder(ExtensionObjectDefinition requestHeader, ExtensionObjectDefinition clientDescription, PascalString serverUri, PascalString endpointUrl, PascalString sessionName, PascalByteString clientNonce, PascalByteString clientCertificate, double requestedSessionTimeout, long maxResponseMessageSize) {
            this.requestHeader = requestHeader;
            this.clientDescription = clientDescription;
            this.serverUri = serverUri;
            this.endpointUrl = endpointUrl;
            this.sessionName = sessionName;
            this.clientNonce = clientNonce;
            this.clientCertificate = clientCertificate;
            this.requestedSessionTimeout = requestedSessionTimeout;
            this.maxResponseMessageSize = maxResponseMessageSize;
        }

        public CreateSessionRequest build() {
            return new CreateSessionRequest(requestHeader, clientDescription, serverUri, endpointUrl, sessionName, clientNonce, clientCertificate, requestedSessionTimeout, maxResponseMessageSize);
        }
    }

}