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
package org.apache.plc4x.java.canopen.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.canopen.readwrite.*;
import org.apache.plc4x.java.canopen.readwrite.io.*;
import org.apache.plc4x.java.canopen.readwrite.types.*;
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

public class CANOpenMPDOIO implements MessageIO<CANOpenMPDO, CANOpenMPDO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CANOpenMPDOIO.class);

    @Override
    public CANOpenMPDO parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return CANOpenMPDOIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CANOpenMPDO value, Object... args) throws ParseException {
        CANOpenMPDOIO.staticSerialize(writeBuffer, value);
    }

    public static CANOpenMPDO staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("CANOpenMPDO");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (node)
short node = readBuffer.readUnsignedShort("node", 8) ;
        readBuffer.pullContext("address");

        // Simple Field (address)
IndexAddress address = IndexAddressIO.staticParse(readBuffer ) ;        readBuffer.closeContext("address");
        // Array field (data)
        readBuffer.pullContext("data", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(4 > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (4) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        byte[] data;
        {
            int itemCount = Math.max(0, (int) 4);
            data = new byte[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                
data[curItem] = readBuffer.readSignedByte("", 8) ;            }
        }
            readBuffer.closeContext("data", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("CANOpenMPDO");
        // Create the instance
        return new CANOpenMPDO(node, address, data);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CANOpenMPDO _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CANOpenMPDO");

        // Simple Field (node)
        short node = (short) _value.getNode();
        writeBuffer.writeUnsignedShort("node", 8, ((Number) (node)).shortValue());

        // Simple Field (address)
        IndexAddress address = (IndexAddress) _value.getAddress();
        writeBuffer.pushContext("address");
        IndexAddressIO.staticSerialize(writeBuffer, address);
        writeBuffer.popContext("address");

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
        writeBuffer.popContext("CANOpenMPDO");
    }

}