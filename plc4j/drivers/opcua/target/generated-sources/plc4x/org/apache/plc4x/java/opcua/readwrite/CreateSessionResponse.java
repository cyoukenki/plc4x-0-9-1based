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

public class CreateSessionResponse extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "464";
    }

    // Properties.
    private final ExtensionObjectDefinition responseHeader;
    private final NodeId sessionId;
    private final NodeId authenticationToken;
    private final double revisedSessionTimeout;
    private final PascalByteString serverNonce;
    private final PascalByteString serverCertificate;
    private final int noOfServerEndpoints;
    private final ExtensionObjectDefinition[] serverEndpoints;
    private final int noOfServerSoftwareCertificates;
    private final ExtensionObjectDefinition[] serverSoftwareCertificates;
    private final ExtensionObjectDefinition serverSignature;
    private final long maxRequestMessageSize;

    public CreateSessionResponse(ExtensionObjectDefinition responseHeader, NodeId sessionId, NodeId authenticationToken, double revisedSessionTimeout, PascalByteString serverNonce, PascalByteString serverCertificate, int noOfServerEndpoints, ExtensionObjectDefinition[] serverEndpoints, int noOfServerSoftwareCertificates, ExtensionObjectDefinition[] serverSoftwareCertificates, ExtensionObjectDefinition serverSignature, long maxRequestMessageSize) {
        this.responseHeader = responseHeader;
        this.sessionId = sessionId;
        this.authenticationToken = authenticationToken;
        this.revisedSessionTimeout = revisedSessionTimeout;
        this.serverNonce = serverNonce;
        this.serverCertificate = serverCertificate;
        this.noOfServerEndpoints = noOfServerEndpoints;
        this.serverEndpoints = serverEndpoints;
        this.noOfServerSoftwareCertificates = noOfServerSoftwareCertificates;
        this.serverSoftwareCertificates = serverSoftwareCertificates;
        this.serverSignature = serverSignature;
        this.maxRequestMessageSize = maxRequestMessageSize;
    }

    public ExtensionObjectDefinition getResponseHeader() {
        return responseHeader;
    }

    public NodeId getSessionId() {
        return sessionId;
    }

    public NodeId getAuthenticationToken() {
        return authenticationToken;
    }

    public double getRevisedSessionTimeout() {
        return revisedSessionTimeout;
    }

    public PascalByteString getServerNonce() {
        return serverNonce;
    }

    public PascalByteString getServerCertificate() {
        return serverCertificate;
    }

    public int getNoOfServerEndpoints() {
        return noOfServerEndpoints;
    }

    public ExtensionObjectDefinition[] getServerEndpoints() {
        return serverEndpoints;
    }

    public int getNoOfServerSoftwareCertificates() {
        return noOfServerSoftwareCertificates;
    }

    public ExtensionObjectDefinition[] getServerSoftwareCertificates() {
        return serverSoftwareCertificates;
    }

    public ExtensionObjectDefinition getServerSignature() {
        return serverSignature;
    }

    public long getMaxRequestMessageSize() {
        return maxRequestMessageSize;
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
        CreateSessionResponse _value  = this;

        // Simple field (responseHeader)
        lengthInBits += responseHeader.getLengthInBits();

        // Simple field (sessionId)
        lengthInBits += sessionId.getLengthInBits();

        // Simple field (authenticationToken)
        lengthInBits += authenticationToken.getLengthInBits();

        // Simple field (revisedSessionTimeout)
        lengthInBits += 64;

        // Simple field (serverNonce)
        lengthInBits += serverNonce.getLengthInBits();

        // Simple field (serverCertificate)
        lengthInBits += serverCertificate.getLengthInBits();

        // Simple field (noOfServerEndpoints)
        lengthInBits += 32;

        // Array field
        if(serverEndpoints != null) {
            int i=0;
            for(ExtensionObjectDefinition element : serverEndpoints) {
                boolean last = ++i >= serverEndpoints.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        // Simple field (noOfServerSoftwareCertificates)
        lengthInBits += 32;

        // Array field
        if(serverSoftwareCertificates != null) {
            int i=0;
            for(ExtensionObjectDefinition element : serverSoftwareCertificates) {
                boolean last = ++i >= serverSoftwareCertificates.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        // Simple field (serverSignature)
        lengthInBits += serverSignature.getLengthInBits();

        // Simple field (maxRequestMessageSize)
        lengthInBits += 32;

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
        if (!(o instanceof CreateSessionResponse)) {
            return false;
        }
        CreateSessionResponse that = (CreateSessionResponse) o;
        return
            (getResponseHeader() == that.getResponseHeader()) &&
            (getSessionId() == that.getSessionId()) &&
            (getAuthenticationToken() == that.getAuthenticationToken()) &&
            (getRevisedSessionTimeout() == that.getRevisedSessionTimeout()) &&
            (getServerNonce() == that.getServerNonce()) &&
            (getServerCertificate() == that.getServerCertificate()) &&
            (getNoOfServerEndpoints() == that.getNoOfServerEndpoints()) &&
            (getServerEndpoints() == that.getServerEndpoints()) &&
            (getNoOfServerSoftwareCertificates() == that.getNoOfServerSoftwareCertificates()) &&
            (getServerSoftwareCertificates() == that.getServerSoftwareCertificates()) &&
            (getServerSignature() == that.getServerSignature()) &&
            (getMaxRequestMessageSize() == that.getMaxRequestMessageSize()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getResponseHeader(),
            getSessionId(),
            getAuthenticationToken(),
            getRevisedSessionTimeout(),
            getServerNonce(),
            getServerCertificate(),
            getNoOfServerEndpoints(),
            getServerEndpoints(),
            getNoOfServerSoftwareCertificates(),
            getServerSoftwareCertificates(),
            getServerSignature(),
            getMaxRequestMessageSize()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("responseHeader", getResponseHeader())
            .append("sessionId", getSessionId())
            .append("authenticationToken", getAuthenticationToken())
            .append("revisedSessionTimeout", getRevisedSessionTimeout())
            .append("serverNonce", getServerNonce())
            .append("serverCertificate", getServerCertificate())
            .append("noOfServerEndpoints", getNoOfServerEndpoints())
            .append("serverEndpoints", getServerEndpoints())
            .append("noOfServerSoftwareCertificates", getNoOfServerSoftwareCertificates())
            .append("serverSoftwareCertificates", getServerSoftwareCertificates())
            .append("serverSignature", getServerSignature())
            .append("maxRequestMessageSize", getMaxRequestMessageSize())
            .toString();
    }

}