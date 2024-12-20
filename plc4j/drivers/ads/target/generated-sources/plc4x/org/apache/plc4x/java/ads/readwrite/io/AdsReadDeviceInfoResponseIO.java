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
package org.apache.plc4x.java.ads.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.ads.readwrite.*;
import org.apache.plc4x.java.ads.readwrite.io.*;
import org.apache.plc4x.java.ads.readwrite.types.*;
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

public class AdsReadDeviceInfoResponseIO implements MessageIO<AdsReadDeviceInfoResponse, AdsReadDeviceInfoResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdsReadDeviceInfoResponseIO.class);

    @Override
    public AdsReadDeviceInfoResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (AdsReadDeviceInfoResponse) new AdsDataIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AdsReadDeviceInfoResponse value, Object... args) throws ParseException {
        new AdsDataIO().serialize(writeBuffer, value, args);
    }

    public static AdsReadDeviceInfoResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AdsReadDeviceInfoResponse");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("result");

        // Simple Field (result)
        // enum based simple field with type ReturnCode
        ReturnCode result = ReturnCode.enumForValue(readBuffer.readUnsignedLong("ReturnCode", 32));
        readBuffer.closeContext("result");


        // Simple Field (majorVersion)
short majorVersion = readBuffer.readUnsignedShort("majorVersion", 8) ;

        // Simple Field (minorVersion)
short minorVersion = readBuffer.readUnsignedShort("minorVersion", 8) ;

        // Simple Field (version)
int version = readBuffer.readUnsignedInt("version", 16) ;        // Array field (device)
        readBuffer.pullContext("device", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(16 > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (16) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        byte[] device;
        {
            int itemCount = Math.max(0, (int) 16);
            device = new byte[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
device[curItem] = readBuffer.readSignedByte("", 8) ;            }
        }
            readBuffer.closeContext("device", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("AdsReadDeviceInfoResponse");
        // Create the instance
        return new AdsReadDeviceInfoResponseBuilder(result, majorVersion, minorVersion, version, device);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AdsReadDeviceInfoResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AdsReadDeviceInfoResponse");

        // Simple Field (result)
        ReturnCode result = (ReturnCode) _value.getResult();
        writeBuffer.pushContext("result");
        // enum field with type ReturnCode
        writeBuffer.writeUnsignedLong("ReturnCode", 32, ((Number) (result.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(result.name()));
        writeBuffer.popContext("result");

        // Simple Field (majorVersion)
        short majorVersion = (short) _value.getMajorVersion();
        writeBuffer.writeUnsignedShort("majorVersion", 8, ((Number) (majorVersion)).shortValue());

        // Simple Field (minorVersion)
        short minorVersion = (short) _value.getMinorVersion();
        writeBuffer.writeUnsignedShort("minorVersion", 8, ((Number) (minorVersion)).shortValue());

        // Simple Field (version)
        int version = (int) _value.getVersion();
        writeBuffer.writeUnsignedInt("version", 16, ((Number) (version)).intValue());

        // Array Field (device)
        if(_value.getDevice() != null) {
            writeBuffer.pushContext("device", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getDevice().length;
            int curItem = 0;
            for(byte element : _value.getDevice()) {
                writeBuffer.writeSignedByte("", 8, ((Number) element).byteValue());
                curItem++;
            }
            writeBuffer.popContext("device", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("AdsReadDeviceInfoResponse");
    }

    public static class AdsReadDeviceInfoResponseBuilder implements AdsDataIO.AdsDataBuilder {
        private final ReturnCode result;
        private final short majorVersion;
        private final short minorVersion;
        private final int version;
        private final byte[] device;

        public AdsReadDeviceInfoResponseBuilder(ReturnCode result, short majorVersion, short minorVersion, int version, byte[] device) {
            this.result = result;
            this.majorVersion = majorVersion;
            this.minorVersion = minorVersion;
            this.version = version;
            this.device = device;
        }

        public AdsReadDeviceInfoResponse build() {
            return new AdsReadDeviceInfoResponse(result, majorVersion, minorVersion, version, device);
        }
    }

}
