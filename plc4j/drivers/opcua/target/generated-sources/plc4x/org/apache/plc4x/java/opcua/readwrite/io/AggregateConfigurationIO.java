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

public class AggregateConfigurationIO implements MessageIO<AggregateConfiguration, AggregateConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AggregateConfigurationIO.class);

    @Override
    public AggregateConfiguration parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (AggregateConfiguration) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AggregateConfiguration value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static AggregateConfigurationBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AggregateConfiguration");
        int startPos = readBuffer.getPos();
        int curPos;

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 6);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (treatUncertainAsBad)
boolean treatUncertainAsBad = readBuffer.readBit("treatUncertainAsBad") ;

        // Simple Field (useServerCapabilitiesDefaults)
boolean useServerCapabilitiesDefaults = readBuffer.readBit("useServerCapabilitiesDefaults") ;

        // Simple Field (percentDataBad)
short percentDataBad = readBuffer.readUnsignedShort("percentDataBad", 8) ;

        // Simple Field (percentDataGood)
short percentDataGood = readBuffer.readUnsignedShort("percentDataGood", 8) ;
        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 7);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (useSlopedExtrapolation)
boolean useSlopedExtrapolation = readBuffer.readBit("useSlopedExtrapolation") ;
        readBuffer.closeContext("AggregateConfiguration");
        // Create the instance
        return new AggregateConfigurationBuilder(treatUncertainAsBad, useServerCapabilitiesDefaults, percentDataBad, percentDataGood, useSlopedExtrapolation);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AggregateConfiguration _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AggregateConfiguration");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 6, ((Number) (short) 0x00).shortValue());

        // Simple Field (treatUncertainAsBad)
        boolean treatUncertainAsBad = (boolean) _value.getTreatUncertainAsBad();
        writeBuffer.writeBit("treatUncertainAsBad", (boolean) (treatUncertainAsBad));

        // Simple Field (useServerCapabilitiesDefaults)
        boolean useServerCapabilitiesDefaults = (boolean) _value.getUseServerCapabilitiesDefaults();
        writeBuffer.writeBit("useServerCapabilitiesDefaults", (boolean) (useServerCapabilitiesDefaults));

        // Simple Field (percentDataBad)
        short percentDataBad = (short) _value.getPercentDataBad();
        writeBuffer.writeUnsignedShort("percentDataBad", 8, ((Number) (percentDataBad)).shortValue());

        // Simple Field (percentDataGood)
        short percentDataGood = (short) _value.getPercentDataGood();
        writeBuffer.writeUnsignedShort("percentDataGood", 8, ((Number) (percentDataGood)).shortValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 7, ((Number) (short) 0x00).shortValue());

        // Simple Field (useSlopedExtrapolation)
        boolean useSlopedExtrapolation = (boolean) _value.getUseSlopedExtrapolation();
        writeBuffer.writeBit("useSlopedExtrapolation", (boolean) (useSlopedExtrapolation));
        writeBuffer.popContext("AggregateConfiguration");
    }

    public static class AggregateConfigurationBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final boolean treatUncertainAsBad;
        private final boolean useServerCapabilitiesDefaults;
        private final short percentDataBad;
        private final short percentDataGood;
        private final boolean useSlopedExtrapolation;

        public AggregateConfigurationBuilder(boolean treatUncertainAsBad, boolean useServerCapabilitiesDefaults, short percentDataBad, short percentDataGood, boolean useSlopedExtrapolation) {
            this.treatUncertainAsBad = treatUncertainAsBad;
            this.useServerCapabilitiesDefaults = useServerCapabilitiesDefaults;
            this.percentDataBad = percentDataBad;
            this.percentDataGood = percentDataGood;
            this.useSlopedExtrapolation = useSlopedExtrapolation;
        }

        public AggregateConfiguration build() {
            return new AggregateConfiguration(treatUncertainAsBad, useServerCapabilitiesDefaults, percentDataBad, percentDataGood, useSlopedExtrapolation);
        }
    }

}