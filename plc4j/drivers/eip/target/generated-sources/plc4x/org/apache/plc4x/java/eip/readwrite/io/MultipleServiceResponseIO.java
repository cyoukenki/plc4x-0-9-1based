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

public class MultipleServiceResponseIO implements MessageIO<MultipleServiceResponse, MultipleServiceResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipleServiceResponseIO.class);

    @Override
    public MultipleServiceResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (MultipleServiceResponse) new CipServiceIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, MultipleServiceResponse value, Object... args) throws ParseException {
        new CipServiceIO().serialize(writeBuffer, value, args);
    }

    public static MultipleServiceResponseBuilder staticParse(ReadBuffer readBuffer, Integer serviceLen) throws ParseException {
        readBuffer.pullContext("MultipleServiceResponse");
        int startPos = readBuffer.getPos();
        int curPos;

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 8);
            if(reserved != (short) 0x0) {
                LOGGER.info("Expected constant value " + 0x0 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (status)
short status = readBuffer.readUnsignedShort("status", 8) ;

        // Simple Field (extStatus)
short extStatus = readBuffer.readUnsignedShort("extStatus", 8) ;

        // Simple Field (serviceNb)
int serviceNb = readBuffer.readUnsignedInt("serviceNb", 16) ;        // Array field (offsets)
        readBuffer.pullContext("offsets", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(serviceNb > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (serviceNb) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        int[] offsets;
        {
            int itemCount = Math.max(0, (int) serviceNb);
            offsets = new int[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
offsets[curItem] = readBuffer.readUnsignedInt("", 16) ;            }
        }
            readBuffer.closeContext("offsets", WithReaderWriterArgs.WithRenderAsList(true));
        // Array field (servicesData)
        readBuffer.pullContext("servicesData", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(((serviceLen) - (6)) - (((2) * (serviceNb))) > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (((serviceLen) - (6)) - (((2) * (serviceNb)))) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        byte[] servicesData;
        {
            int itemCount = Math.max(0, (int) ((serviceLen) - (6)) - (((2) * (serviceNb))));
            servicesData = new byte[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
servicesData[curItem] = readBuffer.readSignedByte("", 8) ;            }
        }
            readBuffer.closeContext("servicesData", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("MultipleServiceResponse");
        // Create the instance
        return new MultipleServiceResponseBuilder(status, extStatus, serviceNb, offsets, servicesData);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, MultipleServiceResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("MultipleServiceResponse");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 8, ((Number) (short) 0x0).shortValue());

        // Simple Field (status)
        short status = (short) _value.getStatus();
        writeBuffer.writeUnsignedShort("status", 8, ((Number) (status)).shortValue());

        // Simple Field (extStatus)
        short extStatus = (short) _value.getExtStatus();
        writeBuffer.writeUnsignedShort("extStatus", 8, ((Number) (extStatus)).shortValue());

        // Simple Field (serviceNb)
        int serviceNb = (int) _value.getServiceNb();
        writeBuffer.writeUnsignedInt("serviceNb", 16, ((Number) (serviceNb)).intValue());

        // Array Field (offsets)
        if(_value.getOffsets() != null) {
            writeBuffer.pushContext("offsets", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getOffsets().length;
            int curItem = 0;
            for(int element : _value.getOffsets()) {
                writeBuffer.writeUnsignedInt("", 16, ((Number) element).intValue());
                curItem++;
            }
            writeBuffer.popContext("offsets", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Array Field (servicesData)
        if(_value.getServicesData() != null) {
            writeBuffer.pushContext("servicesData", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getServicesData().length;
            int curItem = 0;
            for(byte element : _value.getServicesData()) {
                writeBuffer.writeSignedByte("", 8, ((Number) element).byteValue());
                curItem++;
            }
            writeBuffer.popContext("servicesData", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("MultipleServiceResponse");
    }

    public static class MultipleServiceResponseBuilder implements CipServiceIO.CipServiceBuilder {
        private final short status;
        private final short extStatus;
        private final int serviceNb;
        private final int[] offsets;
        private final byte[] servicesData;

        public MultipleServiceResponseBuilder(short status, short extStatus, int serviceNb, int[] offsets, byte[] servicesData) {
            this.status = status;
            this.extStatus = extStatus;
            this.serviceNb = serviceNb;
            this.offsets = offsets;
            this.servicesData = servicesData;
        }

        public MultipleServiceResponse build() {
            return new MultipleServiceResponse(status, extStatus, serviceNb, offsets, servicesData);
        }
    }

}
