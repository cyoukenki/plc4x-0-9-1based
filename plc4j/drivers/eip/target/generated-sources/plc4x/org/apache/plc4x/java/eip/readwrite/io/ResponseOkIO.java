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
package org.apache.plc4x.java.eip.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.eip.readwrite.*;
import org.apache.plc4x.java.eip.readwrite.io.*;
import org.apache.plc4x.java.eip.readwrite.types.*;
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

public class ResponseOkIO implements MessageIO<ResponseOk, ResponseOk> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseOkIO.class);

    @Override
    public ResponseOk parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (ResponseOk) new ReadResponseContentIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ResponseOk value, Object... args) throws ParseException {
        new ReadResponseContentIO().serialize(writeBuffer, value, args);
    }

    public static ResponseOkBuilder staticParse(ReadBuffer readBuffer, Integer len) throws ParseException {
        readBuffer.pullContext("ResponseOk");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (extStatus)
short extStatus = readBuffer.readUnsignedShort("extStatus", 8) ;
        readBuffer.pullContext("dataType");
        // Enum field (dataType)
        CIPDataTypeCode dataType = CIPDataTypeCode.enumForValue(readBuffer.readUnsignedInt("CIPDataTypeCode", 16));
        readBuffer.closeContext("dataType");
        // Array field (data)
        readBuffer.pullContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if((len) - (6) > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + ((len) - (6)) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        byte[] data;
        {
            int itemCount = Math.max(0, (int) (len) - (6));
            data = new byte[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
data[curItem] = readBuffer.readSignedByte("", 8) ;            }
        }
            readBuffer.closeContext("data", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("ResponseOk");
        // Create the instance
        return new ResponseOkBuilder(extStatus, dataType, data);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ResponseOk _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ResponseOk");

        // Simple Field (extStatus)
        short extStatus = (short) _value.getExtStatus();
        writeBuffer.writeUnsignedShort("extStatus", 8, ((Number) (extStatus)).shortValue());

        writeBuffer.pushContext("dataType");
        // Enum field (dataType)
        CIPDataTypeCode dataType = (CIPDataTypeCode) _value.getDataType();
        writeBuffer.writeUnsignedInt("CIPDataTypeCode", 16, ((Number) (dataType.getValue())).intValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(dataType.name()));
        writeBuffer.popContext("dataType");

        // Array Field (data)
        if(_value.getData() != null) {
            writeBuffer.pushContext("data", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getData().length;
            int curItem = 0;
            for(byte element : _value.getData()) {
                writeBuffer.writeSignedByte("", 8, ((Number) element).byteValue());
                curItem++;
            }
            writeBuffer.popContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("ResponseOk");
    }

    public static class ResponseOkBuilder implements ReadResponseContentIO.ReadResponseContentBuilder {
        private final short extStatus;
        private final CIPDataTypeCode dataType;
        private final byte[] data;

        public ResponseOkBuilder(short extStatus, CIPDataTypeCode dataType, byte[] data) {
            this.extStatus = extStatus;
            this.dataType = dataType;
            this.data = data;
        }

        public ResponseOk build() {
            return new ResponseOk(extStatus, dataType, data);
        }
    }

}