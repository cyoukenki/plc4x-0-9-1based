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
package org.apache.plc4x.java.modbus.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.modbus.readwrite.*;
import org.apache.plc4x.java.modbus.readwrite.io.*;
import org.apache.plc4x.java.modbus.readwrite.types.*;
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

public class ModbusPDUReadInputRegistersRequestIO implements MessageIO<ModbusPDUReadInputRegistersRequest, ModbusPDUReadInputRegistersRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModbusPDUReadInputRegistersRequestIO.class);

    @Override
    public ModbusPDUReadInputRegistersRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (ModbusPDUReadInputRegistersRequest) new ModbusPDUIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ModbusPDUReadInputRegistersRequest value, Object... args) throws ParseException {
        new ModbusPDUIO().serialize(writeBuffer, value, args);
    }

    public static ModbusPDUReadInputRegistersRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("ModbusPDUReadInputRegistersRequest");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (startingAddress)
int startingAddress = readBuffer.readUnsignedInt("startingAddress", 16) ;

        // Simple Field (quantity)
int quantity = readBuffer.readUnsignedInt("quantity", 16) ;
        readBuffer.closeContext("ModbusPDUReadInputRegistersRequest");
        // Create the instance
        return new ModbusPDUReadInputRegistersRequestBuilder(startingAddress, quantity);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ModbusPDUReadInputRegistersRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ModbusPDUReadInputRegistersRequest");

        // Simple Field (startingAddress)
        int startingAddress = (int) _value.getStartingAddress();
        writeBuffer.writeUnsignedInt("startingAddress", 16, ((Number) (startingAddress)).intValue());

        // Simple Field (quantity)
        int quantity = (int) _value.getQuantity();
        writeBuffer.writeUnsignedInt("quantity", 16, ((Number) (quantity)).intValue());
        writeBuffer.popContext("ModbusPDUReadInputRegistersRequest");
    }

    public static class ModbusPDUReadInputRegistersRequestBuilder implements ModbusPDUIO.ModbusPDUBuilder {
        private final int startingAddress;
        private final int quantity;

        public ModbusPDUReadInputRegistersRequestBuilder(int startingAddress, int quantity) {
            this.startingAddress = startingAddress;
            this.quantity = quantity;
        }

        public ModbusPDUReadInputRegistersRequest build() {
            return new ModbusPDUReadInputRegistersRequest(startingAddress, quantity);
        }
    }

}