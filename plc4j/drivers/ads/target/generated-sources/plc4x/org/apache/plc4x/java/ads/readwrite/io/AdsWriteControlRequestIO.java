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

public class AdsWriteControlRequestIO implements MessageIO<AdsWriteControlRequest, AdsWriteControlRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdsWriteControlRequestIO.class);

    @Override
    public AdsWriteControlRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (AdsWriteControlRequest) new AdsDataIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AdsWriteControlRequest value, Object... args) throws ParseException {
        new AdsDataIO().serialize(writeBuffer, value, args);
    }

    public static AdsWriteControlRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AdsWriteControlRequest");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (adsState)
int adsState = readBuffer.readUnsignedInt("adsState", 16) ;

        // Simple Field (deviceState)
int deviceState = readBuffer.readUnsignedInt("deviceState", 16) ;
        // Implicit Field (length) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        long length = readBuffer.readUnsignedLong("length", 32);
        // Array field (data)
        readBuffer.pullContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(length > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (length) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        byte[] data;
        {
            int itemCount = Math.max(0, (int) length);
            data = new byte[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
data[curItem] = readBuffer.readSignedByte("", 8) ;            }
        }
            readBuffer.closeContext("data", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("AdsWriteControlRequest");
        // Create the instance
        return new AdsWriteControlRequestBuilder(adsState, deviceState, data);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AdsWriteControlRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AdsWriteControlRequest");

        // Simple Field (adsState)
        int adsState = (int) _value.getAdsState();
        writeBuffer.writeUnsignedInt("adsState", 16, ((Number) (adsState)).intValue());

        // Simple Field (deviceState)
        int deviceState = (int) _value.getDeviceState();
        writeBuffer.writeUnsignedInt("deviceState", 16, ((Number) (deviceState)).intValue());

        // Implicit Field (length) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        long length = (long) (COUNT(_value.getData()));
        writeBuffer.writeUnsignedLong("length", 32, ((Number) (length)).longValue());

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
        writeBuffer.popContext("AdsWriteControlRequest");
    }

    public static class AdsWriteControlRequestBuilder implements AdsDataIO.AdsDataBuilder {
        private final int adsState;
        private final int deviceState;
        private final byte[] data;

        public AdsWriteControlRequestBuilder(int adsState, int deviceState, byte[] data) {
            this.adsState = adsState;
            this.deviceState = deviceState;
            this.data = data;
        }

        public AdsWriteControlRequest build() {
            return new AdsWriteControlRequest(adsState, deviceState, data);
        }
    }

}
