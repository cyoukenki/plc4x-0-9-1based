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

public class RequestHeaderIO implements MessageIO<RequestHeader, RequestHeader> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestHeaderIO.class);

    @Override
    public RequestHeader parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (RequestHeader) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, RequestHeader value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static RequestHeaderBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("RequestHeader");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("authenticationToken");

        // Simple Field (authenticationToken)
NodeId authenticationToken = NodeIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("authenticationToken");


        // Simple Field (timestamp)
long timestamp = readBuffer.readLong("timestamp", 64) ;

        // Simple Field (requestHandle)
long requestHandle = readBuffer.readUnsignedLong("requestHandle", 32) ;

        // Simple Field (returnDiagnostics)
long returnDiagnostics = readBuffer.readUnsignedLong("returnDiagnostics", 32) ;
        readBuffer.pullContext("auditEntryId");

        // Simple Field (auditEntryId)
PascalString auditEntryId = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("auditEntryId");


        // Simple Field (timeoutHint)
long timeoutHint = readBuffer.readUnsignedLong("timeoutHint", 32) ;
        readBuffer.pullContext("additionalHeader");

        // Simple Field (additionalHeader)
ExtensionObject additionalHeader = ExtensionObjectIO.staticParse(readBuffer , (boolean) (true) ) ;        readBuffer.closeContext("additionalHeader");

        readBuffer.closeContext("RequestHeader");
        // Create the instance
        return new RequestHeaderBuilder(authenticationToken, timestamp, requestHandle, returnDiagnostics, auditEntryId, timeoutHint, additionalHeader);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, RequestHeader _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("RequestHeader");

        // Simple Field (authenticationToken)
        NodeId authenticationToken = (NodeId) _value.getAuthenticationToken();
        writeBuffer.pushContext("authenticationToken");
        NodeIdIO.staticSerialize(writeBuffer, authenticationToken);
        writeBuffer.popContext("authenticationToken");

        // Simple Field (timestamp)
        long timestamp = (long) _value.getTimestamp();
        writeBuffer.writeLong("timestamp", 64, ((Number) (timestamp)).longValue());

        // Simple Field (requestHandle)
        long requestHandle = (long) _value.getRequestHandle();
        writeBuffer.writeUnsignedLong("requestHandle", 32, ((Number) (requestHandle)).longValue());

        // Simple Field (returnDiagnostics)
        long returnDiagnostics = (long) _value.getReturnDiagnostics();
        writeBuffer.writeUnsignedLong("returnDiagnostics", 32, ((Number) (returnDiagnostics)).longValue());

        // Simple Field (auditEntryId)
        PascalString auditEntryId = (PascalString) _value.getAuditEntryId();
        writeBuffer.pushContext("auditEntryId");
        PascalStringIO.staticSerialize(writeBuffer, auditEntryId);
        writeBuffer.popContext("auditEntryId");

        // Simple Field (timeoutHint)
        long timeoutHint = (long) _value.getTimeoutHint();
        writeBuffer.writeUnsignedLong("timeoutHint", 32, ((Number) (timeoutHint)).longValue());

        // Simple Field (additionalHeader)
        ExtensionObject additionalHeader = (ExtensionObject) _value.getAdditionalHeader();
        writeBuffer.pushContext("additionalHeader");
        ExtensionObjectIO.staticSerialize(writeBuffer, additionalHeader);
        writeBuffer.popContext("additionalHeader");
        writeBuffer.popContext("RequestHeader");
    }

    public static class RequestHeaderBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final NodeId authenticationToken;
        private final long timestamp;
        private final long requestHandle;
        private final long returnDiagnostics;
        private final PascalString auditEntryId;
        private final long timeoutHint;
        private final ExtensionObject additionalHeader;

        public RequestHeaderBuilder(NodeId authenticationToken, long timestamp, long requestHandle, long returnDiagnostics, PascalString auditEntryId, long timeoutHint, ExtensionObject additionalHeader) {
            this.authenticationToken = authenticationToken;
            this.timestamp = timestamp;
            this.requestHandle = requestHandle;
            this.returnDiagnostics = returnDiagnostics;
            this.auditEntryId = auditEntryId;
            this.timeoutHint = timeoutHint;
            this.additionalHeader = additionalHeader;
        }

        public RequestHeader build() {
            return new RequestHeader(authenticationToken, timestamp, requestHandle, returnDiagnostics, auditEntryId, timeoutHint, additionalHeader);
        }
    }

}