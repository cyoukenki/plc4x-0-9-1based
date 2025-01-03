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
package org.apache.plc4x.java.opcua.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.io.*;
import org.apache.plc4x.java.opcua.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class SessionSecurityDiagnosticsDataType extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "870";
    }

    // Properties.
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

    public SessionSecurityDiagnosticsDataType(NodeId sessionId, PascalString clientUserIdOfSession, int noOfClientUserIdHistory, PascalString[] clientUserIdHistory, PascalString authenticationMechanism, PascalString encoding, PascalString transportProtocol, MessageSecurityMode securityMode, PascalString securityPolicyUri, PascalByteString clientCertificate) {
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

    public NodeId getSessionId() {
        return sessionId;
    }

    public PascalString getClientUserIdOfSession() {
        return clientUserIdOfSession;
    }

    public int getNoOfClientUserIdHistory() {
        return noOfClientUserIdHistory;
    }

    public PascalString[] getClientUserIdHistory() {
        return clientUserIdHistory;
    }

    public PascalString getAuthenticationMechanism() {
        return authenticationMechanism;
    }

    public PascalString getEncoding() {
        return encoding;
    }

    public PascalString getTransportProtocol() {
        return transportProtocol;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public PascalString getSecurityPolicyUri() {
        return securityPolicyUri;
    }

    public PascalByteString getClientCertificate() {
        return clientCertificate;
    }

    @Override
    public int getLengthInBytes() {
        return getLengthInBits() / 8;
    }

    @Override
    public int getLengthInBits() {
        return getLengthInBitsConditional(false);
    }

    public int getLengthInBitsConditional(boolean lastItem) {
        int lengthInBits = super.getLengthInBitsConditional(lastItem);
        SessionSecurityDiagnosticsDataType _value  = this;

        // Simple field (sessionId)
        lengthInBits += sessionId.getLengthInBits();

        // Simple field (clientUserIdOfSession)
        lengthInBits += clientUserIdOfSession.getLengthInBits();

        // Simple field (noOfClientUserIdHistory)
        lengthInBits += 32;

        // Array field
        if(clientUserIdHistory != null) {
            int i=0;
            for(PascalString element : clientUserIdHistory) {
                boolean last = ++i >= clientUserIdHistory.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        // Simple field (authenticationMechanism)
        lengthInBits += authenticationMechanism.getLengthInBits();

        // Simple field (encoding)
        lengthInBits += encoding.getLengthInBits();

        // Simple field (transportProtocol)
        lengthInBits += transportProtocol.getLengthInBits();

        // Simple field (securityMode)
        lengthInBits += 32;

        // Simple field (securityPolicyUri)
        lengthInBits += securityPolicyUri.getLengthInBits();

        // Simple field (clientCertificate)
        lengthInBits += clientCertificate.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<ExtensionObjectDefinition, ExtensionObjectDefinition> getMessageIO() {
        return new ExtensionObjectDefinitionIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SessionSecurityDiagnosticsDataType)) {
            return false;
        }
        SessionSecurityDiagnosticsDataType that = (SessionSecurityDiagnosticsDataType) o;
        return
            (getSessionId() == that.getSessionId()) &&
            (getClientUserIdOfSession() == that.getClientUserIdOfSession()) &&
            (getNoOfClientUserIdHistory() == that.getNoOfClientUserIdHistory()) &&
            (getClientUserIdHistory() == that.getClientUserIdHistory()) &&
            (getAuthenticationMechanism() == that.getAuthenticationMechanism()) &&
            (getEncoding() == that.getEncoding()) &&
            (getTransportProtocol() == that.getTransportProtocol()) &&
            (getSecurityMode() == that.getSecurityMode()) &&
            (getSecurityPolicyUri() == that.getSecurityPolicyUri()) &&
            (getClientCertificate() == that.getClientCertificate()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getSessionId(),
            getClientUserIdOfSession(),
            getNoOfClientUserIdHistory(),
            getClientUserIdHistory(),
            getAuthenticationMechanism(),
            getEncoding(),
            getTransportProtocol(),
            getSecurityMode(),
            getSecurityPolicyUri(),
            getClientCertificate()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("sessionId", getSessionId())
            .append("clientUserIdOfSession", getClientUserIdOfSession())
            .append("noOfClientUserIdHistory", getNoOfClientUserIdHistory())
            .append("clientUserIdHistory", getClientUserIdHistory())
            .append("authenticationMechanism", getAuthenticationMechanism())
            .append("encoding", getEncoding())
            .append("transportProtocol", getTransportProtocol())
            .append("securityMode", getSecurityMode())
            .append("securityPolicyUri", getSecurityPolicyUri())
            .append("clientCertificate", getClientCertificate())
            .toString();
    }

}
