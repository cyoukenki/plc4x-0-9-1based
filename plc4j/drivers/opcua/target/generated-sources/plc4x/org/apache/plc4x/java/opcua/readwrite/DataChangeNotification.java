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

public class DataChangeNotification extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "811";
    }

    // Properties.
    private final int noOfMonitoredItems;
    private final ExtensionObjectDefinition[] monitoredItems;
    private final int noOfDiagnosticInfos;
    private final DiagnosticInfo[] diagnosticInfos;

    public DataChangeNotification(int noOfMonitoredItems, ExtensionObjectDefinition[] monitoredItems, int noOfDiagnosticInfos, DiagnosticInfo[] diagnosticInfos) {
        this.noOfMonitoredItems = noOfMonitoredItems;
        this.monitoredItems = monitoredItems;
        this.noOfDiagnosticInfos = noOfDiagnosticInfos;
        this.diagnosticInfos = diagnosticInfos;
    }

    public int getNoOfMonitoredItems() {
        return noOfMonitoredItems;
    }

    public ExtensionObjectDefinition[] getMonitoredItems() {
        return monitoredItems;
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
        DataChangeNotification _value  = this;

        // Implicit Field (notificationLength)
        lengthInBits += 32;
        //int notificationLength = (int) (_value.getLengthInBytes());

        // Simple field (noOfMonitoredItems)
        lengthInBits += 32;

        // Array field
        if(monitoredItems != null) {
            int i=0;
            for(ExtensionObjectDefinition element : monitoredItems) {
                boolean last = ++i >= monitoredItems.length;
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
        if (!(o instanceof DataChangeNotification)) {
            return false;
        }
        DataChangeNotification that = (DataChangeNotification) o;
        return
            (getNoOfMonitoredItems() == that.getNoOfMonitoredItems()) &&
            (getMonitoredItems() == that.getMonitoredItems()) &&
            (getNoOfDiagnosticInfos() == that.getNoOfDiagnosticInfos()) &&
            (getDiagnosticInfos() == that.getDiagnosticInfos()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getNoOfMonitoredItems(),
            getMonitoredItems(),
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
            .append("noOfMonitoredItems", getNoOfMonitoredItems())
            .append("monitoredItems", getMonitoredItems())
            .append("noOfDiagnosticInfos", getNoOfDiagnosticInfos())
            .append("diagnosticInfos", getDiagnosticInfos())
            .toString();
    }

}