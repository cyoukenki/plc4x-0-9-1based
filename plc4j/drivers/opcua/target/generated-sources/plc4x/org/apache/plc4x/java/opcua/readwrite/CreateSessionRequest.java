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

public class CreateSessionRequest extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "461";
    }

    // Properties.
    private final ExtensionObjectDefinition requestHeader;
    private final ExtensionObjectDefinition clientDescription;
    private final PascalString serverUri;
    private final PascalString endpointUrl;
    private final PascalString sessionName;
    private final PascalByteString clientNonce;
    private final PascalByteString clientCertificate;
    private final double requestedSessionTimeout;
    private final long maxResponseMessageSize;

    public CreateSessionRequest(ExtensionObjectDefinition requestHeader, ExtensionObjectDefinition clientDescription, PascalString serverUri, PascalString endpointUrl, PascalString sessionName, PascalByteString clientNonce, PascalByteString clientCertificate, double requestedSessionTimeout, long maxResponseMessageSize) {
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

    public ExtensionObjectDefinition getRequestHeader() {
        return requestHeader;
    }

    public ExtensionObjectDefinition getClientDescription() {
        return clientDescription;
    }

    public PascalString getServerUri() {
        return serverUri;
    }

    public PascalString getEndpointUrl() {
        return endpointUrl;
    }

    public PascalString getSessionName() {
        return sessionName;
    }

    public PascalByteString getClientNonce() {
        return clientNonce;
    }

    public PascalByteString getClientCertificate() {
        return clientCertificate;
    }

    public double getRequestedSessionTimeout() {
        return requestedSessionTimeout;
    }

    public long getMaxResponseMessageSize() {
        return maxResponseMessageSize;
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
        CreateSessionRequest _value  = this;

        // Simple field (requestHeader)
        lengthInBits += requestHeader.getLengthInBits();

        // Simple field (clientDescription)
        lengthInBits += clientDescription.getLengthInBits();

        // Simple field (serverUri)
        lengthInBits += serverUri.getLengthInBits();

        // Simple field (endpointUrl)
        lengthInBits += endpointUrl.getLengthInBits();

        // Simple field (sessionName)
        lengthInBits += sessionName.getLengthInBits();

        // Simple field (clientNonce)
        lengthInBits += clientNonce.getLengthInBits();

        // Simple field (clientCertificate)
        lengthInBits += clientCertificate.getLengthInBits();

        // Simple field (requestedSessionTimeout)
        lengthInBits += 64;

        // Simple field (maxResponseMessageSize)
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
        if (!(o instanceof CreateSessionRequest)) {
            return false;
        }
        CreateSessionRequest that = (CreateSessionRequest) o;
        return
            (getRequestHeader() == that.getRequestHeader()) &&
            (getClientDescription() == that.getClientDescription()) &&
            (getServerUri() == that.getServerUri()) &&
            (getEndpointUrl() == that.getEndpointUrl()) &&
            (getSessionName() == that.getSessionName()) &&
            (getClientNonce() == that.getClientNonce()) &&
            (getClientCertificate() == that.getClientCertificate()) &&
            (getRequestedSessionTimeout() == that.getRequestedSessionTimeout()) &&
            (getMaxResponseMessageSize() == that.getMaxResponseMessageSize()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getRequestHeader(),
            getClientDescription(),
            getServerUri(),
            getEndpointUrl(),
            getSessionName(),
            getClientNonce(),
            getClientCertificate(),
            getRequestedSessionTimeout(),
            getMaxResponseMessageSize()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("requestHeader", getRequestHeader())
            .append("clientDescription", getClientDescription())
            .append("serverUri", getServerUri())
            .append("endpointUrl", getEndpointUrl())
            .append("sessionName", getSessionName())
            .append("clientNonce", getClientNonce())
            .append("clientCertificate", getClientCertificate())
            .append("requestedSessionTimeout", getRequestedSessionTimeout())
            .append("maxResponseMessageSize", getMaxResponseMessageSize())
            .toString();
    }

}
