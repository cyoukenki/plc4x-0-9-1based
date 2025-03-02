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
package org.apache.plc4x.java.mc.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.mc.readwrite.*;
import org.apache.plc4x.java.mc.readwrite.io.*;
import org.apache.plc4x.java.mc.readwrite.types.*;
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

public class DataStruct14020000wordIO implements MessageIO<DataStruct14020000word, DataStruct14020000word> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataStruct14020000wordIO.class);

    @Override
    public DataStruct14020000word parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return DataStruct14020000wordIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, DataStruct14020000word value, Object... args) throws ParseException {
        DataStruct14020000wordIO.staticSerialize(writeBuffer, value);
    }

    public static DataStruct14020000word staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("DataStruct14020000word");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("devicecoderadom");
        // Enum field (devicecoderadom)
        Devicecode devicecoderadom = Devicecode.enumForValue(readBuffer.readShort("Devicecode", 16));
        readBuffer.closeContext("devicecoderadom");


        // Simple Field (deviceNoradom)
BigInteger deviceNoradom = readBuffer.readUnsignedBigInteger("deviceNoradom", 48) ;

        // Simple Field (wordvalue14020001)
int wordvalue14020001 = readBuffer.readUnsignedInt("wordvalue14020001", 16) ;
        readBuffer.closeContext("DataStruct14020000word");
        // Create the instance
        return new DataStruct14020000word(devicecoderadom, deviceNoradom, wordvalue14020001);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, DataStruct14020000word _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("DataStruct14020000word");

        writeBuffer.pushContext("devicecoderadom");
        // Enum field (devicecoderadom)
        Devicecode devicecoderadom = (Devicecode) _value.getDevicecoderadom();
        writeBuffer.writeShort("Devicecode", 16, ((Number) (devicecoderadom.getValue())).shortValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(devicecoderadom.name()));
        writeBuffer.popContext("devicecoderadom");

        // Simple Field (deviceNoradom)
        BigInteger deviceNoradom = (BigInteger) _value.getDeviceNoradom();
        writeBuffer.writeUnsignedBigInteger("deviceNoradom", 48, (BigInteger) (deviceNoradom));

        // Simple Field (wordvalue14020001)
        int wordvalue14020001 = (int) _value.getWordvalue14020001();
        writeBuffer.writeUnsignedInt("wordvalue14020001", 16, ((Number) (wordvalue14020001)).intValue());
        writeBuffer.popContext("DataStruct14020000word");
    }

}
