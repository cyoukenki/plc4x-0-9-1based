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
package org.apache.plc4x.java.profinet.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.profinet.readwrite.*;
import org.apache.plc4x.java.profinet.readwrite.io.*;
import org.apache.plc4x.java.profinet.readwrite.types.*;
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

public class PnIoCm_Block_ModuleDiffIO implements MessageIO<PnIoCm_Block_ModuleDiff, PnIoCm_Block_ModuleDiff> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PnIoCm_Block_ModuleDiffIO.class);

    @Override
    public PnIoCm_Block_ModuleDiff parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (PnIoCm_Block_ModuleDiff) new PnIoCm_BlockIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, PnIoCm_Block_ModuleDiff value, Object... args) throws ParseException {
        new PnIoCm_BlockIO().serialize(writeBuffer, value, args);
    }

    public static PnIoCm_Block_ModuleDiffBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("PnIoCm_Block_ModuleDiff");
        int startPos = readBuffer.getPos();
        int curPos;

        // Implicit Field (numberOfApis) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int numberOfApis = readBuffer.readUnsignedInt("numberOfApis", 16);
        // Array field (apis)
        readBuffer.pullContext("apis", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(numberOfApis > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (numberOfApis) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        PnIoCm_ModuleDiffBlockApi[] apis;
        {
            int itemCount = Math.max(0, (int) numberOfApis);
            apis = new PnIoCm_ModuleDiffBlockApi[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
apis[curItem] = PnIoCm_ModuleDiffBlockApiIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("apis", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("PnIoCm_Block_ModuleDiff");
        // Create the instance
        return new PnIoCm_Block_ModuleDiffBuilder(apis);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, PnIoCm_Block_ModuleDiff _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("PnIoCm_Block_ModuleDiff");

        // Implicit Field (numberOfApis) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int numberOfApis = (int) (COUNT(_value.getApis()));
        writeBuffer.writeUnsignedInt("numberOfApis", 16, ((Number) (numberOfApis)).intValue());

        // Array Field (apis)
        if(_value.getApis() != null) {
            writeBuffer.pushContext("apis", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getApis().length;
            int curItem = 0;
            for(PnIoCm_ModuleDiffBlockApi element : _value.getApis()) {
                boolean lastItem = curItem == (itemCount - 1);
                PnIoCm_ModuleDiffBlockApiIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("apis", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("PnIoCm_Block_ModuleDiff");
    }

    public static class PnIoCm_Block_ModuleDiffBuilder implements PnIoCm_BlockIO.PnIoCm_BlockBuilder {
        private final PnIoCm_ModuleDiffBlockApi[] apis;

        public PnIoCm_Block_ModuleDiffBuilder(PnIoCm_ModuleDiffBlockApi[] apis) {
            this.apis = apis;
        }

        public PnIoCm_Block_ModuleDiff build(short blockVersionHigh, short blockVersionLow) {
            return new PnIoCm_Block_ModuleDiff(blockVersionHigh, blockVersionLow, apis);
        }
    }

}