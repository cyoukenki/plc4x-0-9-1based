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
package org.apache.plc4x.java.secsgem.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.io.*;
import org.apache.plc4x.java.secsgem.readwrite.types.*;
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

public class S6F16ResponseIO implements MessageIO<S6F16Response, S6F16Response> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S6F16ResponseIO.class);

    @Override
    public S6F16Response parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S6F16Response) new SecsPacketIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S6F16Response value, Object... args) throws ParseException {
        new SecsPacketIO().serialize(writeBuffer, value, args);
    }

    public static S6F16ResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S6F16Response");
        int startPos = readBuffer.getPos();
        int curPos;

        // Const Field (list3)
        int list3 = readBuffer.readUnsignedInt("list3", 16);
        if(list3 != S6F16Response.LIST3) {
            throw new ParseException("Expected constant value " + S6F16Response.LIST3 + " but got " + list3);
        }

        readBuffer.pullContext("dataId");

        // Simple Field (dataId)
DataStruct dataId = DataStructIO.staticParse(readBuffer ) ;        readBuffer.closeContext("dataId");

        readBuffer.pullContext("ceId");

        // Simple Field (ceId)
DataStruct ceId = DataStructIO.staticParse(readBuffer ) ;        readBuffer.closeContext("ceId");

        readBuffer.pullContext("symbolTypeDS");

        // Simple Field (symbolTypeDS)
DataStruct2 symbolTypeDS = DataStruct2IO.staticParse(readBuffer ) ;        readBuffer.closeContext("symbolTypeDS");
        // Array field (values)
        readBuffer.pullContext("values", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(symbolTypeDS.getIdentifier() > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (symbolTypeDS.getIdentifier()) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        RPTIDListDataStruct[] values;
        {
            int itemCount = Math.max(0, (int) symbolTypeDS.getIdentifier());
            values = new RPTIDListDataStruct[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
values[curItem] = RPTIDListDataStructIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("values", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("S6F16Response");
        // Create the instance
        return new S6F16ResponseBuilder(dataId, ceId, symbolTypeDS, values);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S6F16Response _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S6F16Response");

        // Const Field (list3)
        writeBuffer.writeUnsignedInt("list3", 16, ((Number) 0x0103).intValue());

        // Simple Field (dataId)
        DataStruct dataId = (DataStruct) _value.getDataId();
        writeBuffer.pushContext("dataId");
        DataStructIO.staticSerialize(writeBuffer, dataId);
        writeBuffer.popContext("dataId");

        // Simple Field (ceId)
        DataStruct ceId = (DataStruct) _value.getCeId();
        writeBuffer.pushContext("ceId");
        DataStructIO.staticSerialize(writeBuffer, ceId);
        writeBuffer.popContext("ceId");

        // Simple Field (symbolTypeDS)
        DataStruct2 symbolTypeDS = (DataStruct2) _value.getSymbolTypeDS();
        writeBuffer.pushContext("symbolTypeDS");
        DataStruct2IO.staticSerialize(writeBuffer, symbolTypeDS);
        writeBuffer.popContext("symbolTypeDS");

        // Array Field (values)
        if(_value.getValues() != null) {
            writeBuffer.pushContext("values", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getValues().length;
            int curItem = 0;
            for(RPTIDListDataStruct element : _value.getValues()) {
                boolean lastItem = curItem == (itemCount - 1);
                RPTIDListDataStructIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("values", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("S6F16Response");
    }

    public static class S6F16ResponseBuilder implements SecsPacketIO.SecsPacketBuilder {
        private final DataStruct dataId;
        private final DataStruct ceId;
        private final DataStruct2 symbolTypeDS;
        private final RPTIDListDataStruct[] values;

        public S6F16ResponseBuilder(DataStruct dataId, DataStruct ceId, DataStruct2 symbolTypeDS, RPTIDListDataStruct[] values) {
            this.dataId = dataId;
            this.ceId = ceId;
            this.symbolTypeDS = symbolTypeDS;
            this.values = values;
        }

        public S6F16Response build(int deviceID, short PType, short Stype, long systemBytes) {
            return new S6F16Response(deviceID, PType, Stype, systemBytes, dataId, ceId, symbolTypeDS, values);
        }
    }

}