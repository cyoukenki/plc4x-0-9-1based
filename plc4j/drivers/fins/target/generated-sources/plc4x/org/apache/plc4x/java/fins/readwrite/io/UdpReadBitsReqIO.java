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
package org.apache.plc4x.java.fins.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.fins.readwrite.*;
import org.apache.plc4x.java.fins.readwrite.io.*;
import org.apache.plc4x.java.fins.readwrite.types.*;
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

public class UdpReadBitsReqIO implements MessageIO<UdpReadBitsReq, UdpReadBitsReq> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpReadBitsReqIO.class);

    @Override
    public UdpReadBitsReq parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (UdpReadBitsReq) new FinsUdpPacketIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, UdpReadBitsReq value, Object... args) throws ParseException {
        new FinsUdpPacketIO().serialize(writeBuffer, value, args);
    }

    public static UdpReadBitsReqBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("UdpReadBitsReq");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("dataType");
        // Enum field (dataType)
        FinsDataTypeCode dataType = FinsDataTypeCode.enumForValue(readBuffer.readUnsignedShort("FinsDataTypeCode", 8));
        readBuffer.closeContext("dataType");


        // Simple Field (wordStart)
int wordStart = readBuffer.readUnsignedInt("wordStart", 16) ;

        // Simple Field (bitsStart)
short bitsStart = readBuffer.readUnsignedShort("bitsStart", 8) ;

        // Simple Field (lenght)
int lenght = readBuffer.readUnsignedInt("lenght", 16) ;
        readBuffer.closeContext("UdpReadBitsReq");
        // Create the instance
        return new UdpReadBitsReqBuilder(dataType, wordStart, bitsStart, lenght);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, UdpReadBitsReq _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("UdpReadBitsReq");

        writeBuffer.pushContext("dataType");
        // Enum field (dataType)
        FinsDataTypeCode dataType = (FinsDataTypeCode) _value.getDataType();
        writeBuffer.writeUnsignedShort("FinsDataTypeCode", 8, ((Number) (dataType.getValue())).shortValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(dataType.name()));
        writeBuffer.popContext("dataType");

        // Simple Field (wordStart)
        int wordStart = (int) _value.getWordStart();
        writeBuffer.writeUnsignedInt("wordStart", 16, ((Number) (wordStart)).intValue());

        // Simple Field (bitsStart)
        short bitsStart = (short) _value.getBitsStart();
        writeBuffer.writeUnsignedShort("bitsStart", 8, ((Number) (bitsStart)).shortValue());

        // Simple Field (lenght)
        int lenght = (int) _value.getLenght();
        writeBuffer.writeUnsignedInt("lenght", 16, ((Number) (lenght)).intValue());
        writeBuffer.popContext("UdpReadBitsReq");
    }

    public static class UdpReadBitsReqBuilder implements FinsUdpPacketIO.FinsUdpPacketBuilder {
        private final FinsDataTypeCode dataType;
        private final int wordStart;
        private final short bitsStart;
        private final int lenght;

        public UdpReadBitsReqBuilder(FinsDataTypeCode dataType, int wordStart, short bitsStart, int lenght) {
            this.dataType = dataType;
            this.wordStart = wordStart;
            this.bitsStart = bitsStart;
            this.lenght = lenght;
        }

        public UdpReadBitsReq build(short ICF, short DA1, short SA1, short SID) {
            return new UdpReadBitsReq(ICF, DA1, SA1, SID, dataType, wordStart, bitsStart, lenght);
        }
    }

}