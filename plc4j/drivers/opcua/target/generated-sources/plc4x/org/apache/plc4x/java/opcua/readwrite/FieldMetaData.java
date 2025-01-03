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

public class FieldMetaData extends ExtensionObjectDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "14526";
    }

    // Properties.
    private final PascalString name;
    private final LocalizedText description;
    private final DataSetFieldFlags fieldFlags;
    private final short builtInType;
    private final NodeId dataType;
    private final int valueRank;
    private final int noOfArrayDimensions;
    private final long[] arrayDimensions;
    private final long maxStringLength;
    private final GuidValue dataSetFieldId;
    private final int noOfProperties;
    private final ExtensionObjectDefinition[] properties;

    public FieldMetaData(PascalString name, LocalizedText description, DataSetFieldFlags fieldFlags, short builtInType, NodeId dataType, int valueRank, int noOfArrayDimensions, long[] arrayDimensions, long maxStringLength, GuidValue dataSetFieldId, int noOfProperties, ExtensionObjectDefinition[] properties) {
        this.name = name;
        this.description = description;
        this.fieldFlags = fieldFlags;
        this.builtInType = builtInType;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.noOfArrayDimensions = noOfArrayDimensions;
        this.arrayDimensions = arrayDimensions;
        this.maxStringLength = maxStringLength;
        this.dataSetFieldId = dataSetFieldId;
        this.noOfProperties = noOfProperties;
        this.properties = properties;
    }

    public PascalString getName() {
        return name;
    }

    public LocalizedText getDescription() {
        return description;
    }

    public DataSetFieldFlags getFieldFlags() {
        return fieldFlags;
    }

    public short getBuiltInType() {
        return builtInType;
    }

    public NodeId getDataType() {
        return dataType;
    }

    public int getValueRank() {
        return valueRank;
    }

    public int getNoOfArrayDimensions() {
        return noOfArrayDimensions;
    }

    public long[] getArrayDimensions() {
        return arrayDimensions;
    }

    public long getMaxStringLength() {
        return maxStringLength;
    }

    public GuidValue getDataSetFieldId() {
        return dataSetFieldId;
    }

    public int getNoOfProperties() {
        return noOfProperties;
    }

    public ExtensionObjectDefinition[] getProperties() {
        return properties;
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
        FieldMetaData _value  = this;

        // Simple field (name)
        lengthInBits += name.getLengthInBits();

        // Simple field (description)
        lengthInBits += description.getLengthInBits();

        // Simple field (fieldFlags)
        lengthInBits += 16;

        // Simple field (builtInType)
        lengthInBits += 8;

        // Simple field (dataType)
        lengthInBits += dataType.getLengthInBits();

        // Simple field (valueRank)
        lengthInBits += 32;

        // Simple field (noOfArrayDimensions)
        lengthInBits += 32;

        // Array field
        if(arrayDimensions != null) {
            lengthInBits += 32 * arrayDimensions.length;
        }

        // Simple field (maxStringLength)
        lengthInBits += 32;

        // Simple field (dataSetFieldId)
        lengthInBits += dataSetFieldId.getLengthInBits();

        // Simple field (noOfProperties)
        lengthInBits += 32;

        // Array field
        if(properties != null) {
            int i=0;
            for(ExtensionObjectDefinition element : properties) {
                boolean last = ++i >= properties.length;
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
        if (!(o instanceof FieldMetaData)) {
            return false;
        }
        FieldMetaData that = (FieldMetaData) o;
        return
            (getName() == that.getName()) &&
            (getDescription() == that.getDescription()) &&
            (getFieldFlags() == that.getFieldFlags()) &&
            (getBuiltInType() == that.getBuiltInType()) &&
            (getDataType() == that.getDataType()) &&
            (getValueRank() == that.getValueRank()) &&
            (getNoOfArrayDimensions() == that.getNoOfArrayDimensions()) &&
            (getArrayDimensions() == that.getArrayDimensions()) &&
            (getMaxStringLength() == that.getMaxStringLength()) &&
            (getDataSetFieldId() == that.getDataSetFieldId()) &&
            (getNoOfProperties() == that.getNoOfProperties()) &&
            (getProperties() == that.getProperties()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getName(),
            getDescription(),
            getFieldFlags(),
            getBuiltInType(),
            getDataType(),
            getValueRank(),
            getNoOfArrayDimensions(),
            getArrayDimensions(),
            getMaxStringLength(),
            getDataSetFieldId(),
            getNoOfProperties(),
            getProperties()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("name", getName())
            .append("description", getDescription())
            .append("fieldFlags", getFieldFlags())
            .append("builtInType", getBuiltInType())
            .append("dataType", getDataType())
            .append("valueRank", getValueRank())
            .append("noOfArrayDimensions", getNoOfArrayDimensions())
            .append("arrayDimensions", getArrayDimensions())
            .append("maxStringLength", getMaxStringLength())
            .append("dataSetFieldId", getDataSetFieldId())
            .append("noOfProperties", getNoOfProperties())
            .append("properties", getProperties())
            .toString();
    }

}
