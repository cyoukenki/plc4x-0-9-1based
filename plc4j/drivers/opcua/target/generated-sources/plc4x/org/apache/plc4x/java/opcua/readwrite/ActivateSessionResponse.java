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

public class ActivateSessionResponse extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "470";
    }

    // Properties.
    private final ExtensionObjectDefinition responseHeader;
    private final PascalByteString serverNonce;
    private final int noOfResults;
    private final StatusCode[] results;
    private final int noOfDiagnosticInfos;
    private final DiagnosticInfo[] diagnosticInfos;

    public ActivateSessionResponse(ExtensionObjectDefinition responseHeader, PascalByteString serverNonce, int noOfResults, StatusCode[] results, int noOfDiagnosticInfos, DiagnosticInfo[] diagnosticInfos) {
        this.responseHeader = responseHeader;
        this.serverNonce = serverNonce;
        this.noOfResults = noOfResults;
        this.results = results;
        this.noOfDiagnosticInfos = noOfDiagnosticInfos;
        this.diagnosticInfos = diagnosticInfos;
    }

    public ExtensionObjectDefinition getResponseHeader() {
        return responseHeader;
    }

    public PascalByteString getServerNonce() {
        return serverNonce;
    }

    public int getNoOfResults() {
        return noOfResults;
    }

    public StatusCode[] getResults() {
        return results;
    }

    public int getNoOfDiagnosticInfos() {
        return noOfDiagnosticInfos;
    }

    public DiagnosticInfo[] getDiagnosticInfos() {
        return diagnosticInfos;
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
        ActivateSessionResponse _value  = this;

        // Simple field (responseHeader)
        lengthInBits += responseHeader.getLengthInBits();

        // Simple field (serverNonce)
        lengthInBits += serverNonce.getLengthInBits();

        // Simple field (noOfResults)
        lengthInBits += 32;

        // Array field
        if(results != null) {
            int i=0;
            for(StatusCode element : results) {
                boolean last = ++i >= results.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

        // Simple field (noOfDiagnosticInfos)
        lengthInBits += 32;

        // Array field
        if(diagnosticInfos != null) {
            int i=0;
            for(DiagnosticInfo element : diagnosticInfos) {
                boolean last = ++i >= diagnosticInfos.length;
                lengthInBits += element.getLengthInBitsConditional(last);
            }
        }

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
        if (!(o instanceof ActivateSessionResponse)) {
            return false;
        }
        ActivateSessionResponse that = (ActivateSessionResponse) o;
        return
            (getResponseHeader() == that.getResponseHeader()) &&
            (getServerNonce() == that.getServerNonce()) &&
            (getNoOfResults() == that.getNoOfResults()) &&
            (getResults() == that.getResults()) &&
            (getNoOfDiagnosticInfos() == that.getNoOfDiagnosticInfos()) &&
            (getDiagnosticInfos() == that.getDiagnosticInfos()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getResponseHeader(),
            getServerNonce(),
            getNoOfResults(),
            getResults(),
            getNoOfDiagnosticInfos(),
            getDiagnosticInfos()
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
            .append("serverNonce", getServerNonce())
            .append("noOfResults", getNoOfResults())
            .append("results", getResults())
            .append("noOfDiagnosticInfos", getNoOfDiagnosticInfos())
            .append("diagnosticInfos", getDiagnosticInfos())
            .toString();
    }

}
