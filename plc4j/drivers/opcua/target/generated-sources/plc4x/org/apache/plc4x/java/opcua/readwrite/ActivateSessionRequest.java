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

public class ActivateSessionRequest extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "467";
    }

    // Properties.
    private final ExtensionObjectDefinition requestHeader;
    private final ExtensionObjectDefinition clientSignature;
    private final int noOfClientSoftwareCertificates;
    private final ExtensionObjectDefinition[] clientSoftwareCertificates;
    private final int noOfLocaleIds;
    private final PascalString[] localeIds;
    private final ExtensionObject userIdentityToken;
    private final ExtensionObjectDefinition userTokenSignature;

    public ActivateSessionRequest(ExtensionObjectDefinition requestHeader, ExtensionObjectDefinition clientSignature, int noOfClientSoftwareCertificates, ExtensionObjectDefinition[] clientSoftwareCertificates, int noOfLocaleIds, PascalString[] localeIds, ExtensionObject userIdentityToken, ExtensionObjectDefinition userTokenSignature) {
        this.requestHeader = requestHeader;
        this.clientSignature = clientSignature;
        this.noOfClientSoftwareCertificates = noOfClientSoftwareCertificates;
        this.clientSoftwareCertificates = clientSoftwareCertificates;
        this.noOfLocaleIds = noOfLocaleIds;
        this.localeIds = localeIds;
        this.userIdentityToken = userIdentityToken;
        this.userTokenSignature = userTokenSignature;
    }

    public ExtensionObjectDefinition getRequestHeader() {
        return requestHeader;
    }

    public ExtensionObjectDefinition getClientSignature() {
        return clientSignature;
    }

    public int getNoOfClientSoftwareCertificates() {
        return noOfClientSoftwareCertificates;
    }

    public ExtensionObjectDefinition[] getClientSoftwareCertificates() {
        return clientSoftwareCertificates;
    }

    public int getNoOfLocaleIds() {
        return noOfLocaleIds;
    }

    public PascalString[] getLocaleIds() {
        return localeIds;
    }

    public ExtensionObject getUserIdentityToken() {
        return userIdentityToken;
    }

    public ExtensionObjectDefinition getUserTokenSignature() {
        return userTokenSignature;
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
        ActivateSessionRequest _value  = this;

        // Simple field (requestHeader)
        lengthInBits += requestHeader.getLengthInBits();

        // Simple field (clientSignature)
        lengthInBits += clientSignature.getLengthInBits();

        // Simple field (noOfClientSoftwareCertificates)
        lengthInBits += 32;

        // Array field
        if(clientSoftwareCertificates != null) {
            int i=0;
            for(ExtensionObjectDefinition element : clientSoftwareCertificates) {
                boolean last = ++i >= clientSoftwareCertificates.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        // Simple field (noOfLocaleIds)
        lengthInBits += 32;

        // Array field
        if(localeIds != null) {
            int i=0;
            for(PascalString element : localeIds) {
                boolean last = ++i >= localeIds.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        // Simple field (userIdentityToken)
        lengthInBits += userIdentityToken.getLengthInBits();

        // Simple field (userTokenSignature)
        lengthInBits += userTokenSignature.getLengthInBits();

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
        if (!(o instanceof ActivateSessionRequest)) {
            return false;
        }
        ActivateSessionRequest that = (ActivateSessionRequest) o;
        return
            (getRequestHeader() == that.getRequestHeader()) &&
            (getClientSignature() == that.getClientSignature()) &&
            (getNoOfClientSoftwareCertificates() == that.getNoOfClientSoftwareCertificates()) &&
            (getClientSoftwareCertificates() == that.getClientSoftwareCertificates()) &&
            (getNoOfLocaleIds() == that.getNoOfLocaleIds()) &&
            (getLocaleIds() == that.getLocaleIds()) &&
            (getUserIdentityToken() == that.getUserIdentityToken()) &&
            (getUserTokenSignature() == that.getUserTokenSignature()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getRequestHeader(),
            getClientSignature(),
            getNoOfClientSoftwareCertificates(),
            getClientSoftwareCertificates(),
            getNoOfLocaleIds(),
            getLocaleIds(),
            getUserIdentityToken(),
            getUserTokenSignature()
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
            .append("clientSignature", getClientSignature())
            .append("noOfClientSoftwareCertificates", getNoOfClientSoftwareCertificates())
            .append("clientSoftwareCertificates", getClientSoftwareCertificates())
            .append("noOfLocaleIds", getNoOfLocaleIds())
            .append("localeIds", getLocaleIds())
            .append("userIdentityToken", getUserIdentityToken())
            .append("userTokenSignature", getUserTokenSignature())
            .toString();
    }

}
