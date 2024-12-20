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

public class AxisInformationIO implements MessageIO<AxisInformation, AxisInformation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AxisInformationIO.class);

    @Override
    public AxisInformation parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (AxisInformation) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AxisInformation value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static AxisInformationBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AxisInformation");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("engineeringUnits");

        // Simple Field (engineeringUnits)
ExtensionObjectDefinition engineeringUnits = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(889) ) ;        readBuffer.closeContext("engineeringUnits");

        readBuffer.pullContext("eURange");

        // Simple Field (eURange)
ExtensionObjectDefinition eURange = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(886) ) ;        readBuffer.closeContext("eURange");

        readBuffer.pullContext("title");

        // Simple Field (title)
LocalizedText title = LocalizedTextIO.staticParse(readBuffer ) ;        readBuffer.closeContext("title");

        readBuffer.pullContext("axisScaleType");

        // Simple Field (axisScaleType)
        // enum based simple field with type AxisScaleEnumeration
        AxisScaleEnumeration axisScaleType = AxisScaleEnumeration.enumForValue(readBuffer.readUnsignedLong("AxisScaleEnumeration", 32));
        readBuffer.closeContext("axisScaleType");


        // Simple Field (noOfAxisSteps)
int noOfAxisSteps = readBuffer.readInt("noOfAxisSteps", 32) ;        // Array field (axisSteps)
        readBuffer.pullContext("axisSteps", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfAxisSteps > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfAxisSteps) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        double[] axisSteps;
        {
            int itemCount = Math.max(0, (int) noOfAxisSteps);
            axisSteps = new double[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
axisSteps[curItem] = ((Supplier<Double>) (() -> { return (double) toFloat(readBuffer, "", true, 11, 52); })).get() ;            }
        }
            readBuffer.closeContext("axisSteps", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("AxisInformation");
        // Create the instance
        return new AxisInformationBuilder(engineeringUnits, eURange, title, axisScaleType, noOfAxisSteps, axisSteps);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AxisInformation _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AxisInformation");

        // Simple Field (engineeringUnits)
        ExtensionObjectDefinition engineeringUnits = (ExtensionObjectDefinition) _value.getEngineeringUnits();
        writeBuffer.pushContext("engineeringUnits");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, engineeringUnits);
        writeBuffer.popContext("engineeringUnits");

        // Simple Field (eURange)
        ExtensionObjectDefinition eURange = (ExtensionObjectDefinition) _value.getEURange();
        writeBuffer.pushContext("eURange");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, eURange);
        writeBuffer.popContext("eURange");

        // Simple Field (title)
        LocalizedText title = (LocalizedText) _value.getTitle();
        writeBuffer.pushContext("title");
        LocalizedTextIO.staticSerialize(writeBuffer, title);
        writeBuffer.popContext("title");

        // Simple Field (axisScaleType)
        AxisScaleEnumeration axisScaleType = (AxisScaleEnumeration) _value.getAxisScaleType();
        writeBuffer.pushContext("axisScaleType");
        // enum field with type AxisScaleEnumeration
        writeBuffer.writeUnsignedLong("AxisScaleEnumeration", 32, ((Number) (axisScaleType.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(axisScaleType.name()));
        writeBuffer.popContext("axisScaleType");

        // Simple Field (noOfAxisSteps)
        int noOfAxisSteps = (int) _value.getNoOfAxisSteps();
        writeBuffer.writeInt("noOfAxisSteps", 32, ((Number) (noOfAxisSteps)).intValue());

        // Array Field (axisSteps)
        if(_value.getAxisSteps() != null) {
            writeBuffer.pushContext("axisSteps", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getAxisSteps().length;
            int curItem = 0;
            for(double element : _value.getAxisSteps()) {
                writeBuffer.writeDouble("", element,11,52);
                curItem++;
            }
            writeBuffer.popContext("axisSteps", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("AxisInformation");
    }

    public static class AxisInformationBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition engineeringUnits;
        private final ExtensionObjectDefinition eURange;
        private final LocalizedText title;
        private final AxisScaleEnumeration axisScaleType;
        private final int noOfAxisSteps;
        private final double[] axisSteps;

        public AxisInformationBuilder(ExtensionObjectDefinition engineeringUnits, ExtensionObjectDefinition eURange, LocalizedText title, AxisScaleEnumeration axisScaleType, int noOfAxisSteps, double[] axisSteps) {
            this.engineeringUnits = engineeringUnits;
            this.eURange = eURange;
            this.title = title;
            this.axisScaleType = axisScaleType;
            this.noOfAxisSteps = noOfAxisSteps;
            this.axisSteps = axisSteps;
        }

        public AxisInformation build() {
            return new AxisInformation(engineeringUnits, eURange, title, axisScaleType, noOfAxisSteps, axisSteps);
        }
    }

}
