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

public class VariantNodeIdIO implements MessageIO<VariantNodeId, VariantNodeId> {

    private static final Logger LOGGER = LoggerFactory.getLogger(VariantNodeIdIO.class);

    @Override
    public VariantNodeId parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (VariantNodeId) new VariantIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, VariantNodeId value, Object... args) throws ParseException {
        new VariantIO().serialize(writeBuffer, value, args);
    }

    public static VariantNodeIdBuilder staticParse(ReadBuffer readBuffer, Boolean arrayLengthSpecified) throws ParseException {
        readBuffer.pullContext("VariantNodeId");
        int startPos = readBuffer.getPos();
        int curPos;

        // Optional Field (arrayLength) (Can be skipped, if a given expression evaluates to false)
        Integer arrayLength = null;
        if(arrayLengthSpecified) {
            arrayLength = readBuffer.readInt("arrayLength", 32);
        }
        // Array field (value)
        readBuffer.pullContext("value", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if((((arrayLength) == (null)) ? 1 : arrayLength) > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + ((((arrayLength) == (null)) ? 1 : arrayLength)) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        NodeId[] value;
        {
            int itemCount = Math.max(0, (int) (((arrayLength) == (null)) ? 1 : arrayLength));
            value = new NodeId[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
value[curItem] = NodeIdIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("value", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("VariantNodeId");
        // Create the instance
        return new VariantNodeIdBuilder(arrayLength, value);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, VariantNodeId _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("VariantNodeId");

        // Optional Field (arrayLength) (Can be skipped, if the value is null)
        Integer arrayLength = null;
        if(_value.getArrayLength() != null) {
            arrayLength = (Integer) _value.getArrayLength();
            writeBuffer.writeInt("arrayLength", 32, ((Number) (arrayLength)).intValue());
        }

        // Array Field (value)
        if(_value.getValue() != null) {
            writeBuffer.pushContext("value", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getValue().length;
            int curItem = 0;
            for(NodeId element : _value.getValue()) {
                boolean lastItem = curItem == (itemCount - 1);
                NodeIdIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("value", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("VariantNodeId");
    }

    public static class VariantNodeIdBuilder implements VariantIO.VariantBuilder {
        private final Integer arrayLength;
        private final NodeId[] value;

        public VariantNodeIdBuilder(Integer arrayLength, NodeId[] value) {
            this.arrayLength = arrayLength;
            this.value = value;
        }

        public VariantNodeId build(boolean arrayLengthSpecified, boolean arrayDimensionsSpecified, Integer noOfArrayDimensions, boolean[] arrayDimensions) {
            return new VariantNodeId(arrayLengthSpecified, arrayDimensionsSpecified, noOfArrayDimensions, arrayDimensions, arrayLength, value);
        }
    }

}
