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

public class SessionSecurityDiagnosticsDataTypeIO implements MessageIO<SessionSecurityDiagnosticsDataType, SessionSecurityDiagnosticsDataType> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionSecurityDiagnosticsDataTypeIO.class);

    @Override
    public SessionSecurityDiagnosticsDataType parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (SessionSecurityDiagnosticsDataType) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, SessionSecurityDiagnosticsDataType value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static SessionSecurityDiagnosticsDataTypeBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("SessionSecurityDiagnosticsDataType");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("sessionId");

        // Simple Field (sessionId)
NodeId sessionId = NodeIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("sessionId");

        readBuffer.pullContext("clientUserIdOfSession");

        // Simple Field (clientUserIdOfSession)
PascalString clientUserIdOfSession = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("clientUserIdOfSession");


        // Simple Field (noOfClientUserIdHistory)
int noOfClientUserIdHistory = readBuffer.readInt("noOfClientUserIdHistory", 32) ;        // Array field (clientUserIdHistory)
        readBuffer.pullContext("clientUserIdHistory", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfClientUserIdHistory > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfClientUserIdHistory) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        PascalString[] clientUserIdHistory;
        {
            int itemCount = Math.max(0, (int) noOfClientUserIdHistory);
            clientUserIdHistory = new PascalString[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
clientUserIdHistory[curItem] = PascalStringIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("clientUserIdHistory", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.pullContext("authenticationMechanism");

        // Simple Field (authenticationMechanism)
PascalString authenticationMechanism = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("authenticationMechanism");

        readBuffer.pullContext("encoding");

        // Simple Field (encoding)
PascalString encoding = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("encoding");

        readBuffer.pullContext("transportProtocol");

        // Simple Field (transportProtocol)
PascalString transportProtocol = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("transportProtocol");

        readBuffer.pullContext("securityMode");

        // Simple Field (securityMode)
        // enum based simple field with type MessageSecurityMode
        MessageSecurityMode securityMode = MessageSecurityMode.enumForValue(readBuffer.readUnsignedLong("MessageSecurityMode", 32));
        readBuffer.closeContext("securityMode");

        readBuffer.pullContext("securityPolicyUri");

        // Simple Field (securityPolicyUri)
PascalString securityPolicyUri = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("securityPolicyUri");

        readBuffer.pullContext("clientCertificate");

        // Simple Field (clientCertificate)
PascalByteString clientCertificate = PascalByteStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("clientCertificate");

        readBuffer.closeContext("SessionSecurityDiagnosticsDataType");
        // Create the instance
        return new SessionSecurityDiagnosticsDataTypeBuilder(sessionId, clientUserIdOfSession, noOfClientUserIdHistory, clientUserIdHistory, authenticationMechanism, encoding, transportProtocol, securityMode, securityPolicyUri, clientCertificate);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, SessionSecurityDiagnosticsDataType _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("SessionSecurityDiagnosticsDataType");

        // Simple Field (sessionId)
        NodeId sessionId = (NodeId) _value.getSessionId();
        writeBuffer.pushContext("sessionId");
        NodeIdIO.staticSerialize(writeBuffer, sessionId);
        writeBuffer.popContext("sessionId");

        // Simple Field (clientUserIdOfSession)
        PascalString clientUserIdOfSession = (PascalString) _value.getClientUserIdOfSession();
        writeBuffer.pushContext("clientUserIdOfSession");
        PascalStringIO.staticSerialize(writeBuffer, clientUserIdOfSession);
        writeBuffer.popContext("clientUserIdOfSession");

        // Simple Field (noOfClientUserIdHistory)
        int noOfClientUserIdHistory = (int) _value.getNoOfClientUserIdHistory();
        writeBuffer.writeInt("noOfClientUserIdHistory", 32, ((Number) (noOfClientUserIdHistory)).intValue());

        // Array Field (clientUserIdHistory)
        if(_value.getClientUserIdHistory() != null) {
            writeBuffer.pushContext("clientUserIdHistory", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getClientUserIdHistory().length;
            int curItem = 0;
            for(PascalString element : _value.getClientUserIdHistory()) {
                boolean lastItem = curItem == (itemCount - 1);
                PascalStringIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("clientUserIdHistory", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (authenticationMechanism)
        PascalString authenticationMechanism = (PascalString) _value.getAuthenticationMechanism();
        writeBuffer.pushContext("authenticationMechanism");
        PascalStringIO.staticSerialize(writeBuffer, authenticationMechanism);
        writeBuffer.popContext("authenticationMechanism");

        // Simple Field (encoding)
        PascalString encoding = (PascalString) _value.getEncoding();
        writeBuffer.pushContext("encoding");
        PascalStringIO.staticSerialize(writeBuffer, encoding);
        writeBuffer.popContext("encoding");

        // Simple Field (transportProtocol)
        PascalString transportProtocol = (PascalString) _value.getTransportProtocol();
        writeBuffer.pushContext("transportProtocol");
        PascalStringIO.staticSerialize(writeBuffer, transportProtocol);
        writeBuffer.popContext("transportProtocol");

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

        // Simple Field (clientCertificate)
        PascalByteString clientCertificate = (PascalByteString) _value.getClientCertificate();
        writeBuffer.pushContext("clientCertificate");
        PascalByteStringIO.staticSerialize(writeBuffer, clientCertificate);
        writeBuffer.popContext("clientCertificate");
        writeBuffer.popContext("SessionSecurityDiagnosticsDataType");
    }

    public static class SessionSecurityDiagnosticsDataTypeBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final NodeId sessionId;
        private final PascalString clientUserIdOfSession;
        private final int noOfClientUserIdHistory;
        private final PascalString[] clientUserIdHistory;
        private final PascalString authenticationMechanism;
        private final PascalString encoding;
        private final PascalString transportProtocol;
        private final MessageSecurityMode securityMode;
        private final PascalString securityPolicyUri;
        private final PascalByteString clientCertificate;

        public SessionSecurityDiagnosticsDataTypeBuilder(NodeId sessionId, PascalString clientUserIdOfSession, int noOfClientUserIdHistory, PascalString[] clientUserIdHistory, PascalString authenticationMechanism, PascalString encoding, PascalString transportProtocol, MessageSecurityMode securityMode, PascalString securityPolicyUri, PascalByteString clientCertificate) {
            this.sessionId = sessionId;
            this.clientUserIdOfSession = clientUserIdOfSession;
            this.noOfClientUserIdHistory = noOfClientUserIdHistory;
            this.clientUserIdHistory = clientUserIdHistory;
            this.authenticationMechanism = authenticationMechanism;
            this.encoding = encoding;
            this.transportProtocol = transportProtocol;
            this.securityMode = securityMode;
            this.securityPolicyUri = securityPolicyUri;
            this.clientCertificate = clientCertificate;
        }

        public SessionSecurityDiagnosticsDataType build() {
            return new SessionSecurityDiagnosticsDataType(sessionId, clientUserIdOfSession, noOfClientUserIdHistory, clientUserIdHistory, authenticationMechanism, encoding, transportProtocol, securityMode, securityPolicyUri, clientCertificate);
        }
    }

}
