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

public class PnDcp_Block_DevicePropertiesDeviceInstanceIO implements MessageIO<PnDcp_Block_DevicePropertiesDeviceInstance, PnDcp_Block_DevicePropertiesDeviceInstance> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PnDcp_Block_DevicePropertiesDeviceInstanceIO.class);

    @Override
    public PnDcp_Block_DevicePropertiesDeviceInstance parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (PnDcp_Block_DevicePropertiesDeviceInstance) new PnDcp_BlockIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, PnDcp_Block_DevicePropertiesDeviceInstance value, Object... args) throws ParseException {
        new PnDcp_BlockIO().serialize(writeBuffer, value, args);
    }

    public static PnDcp_Block_DevicePropertiesDeviceInstanceBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("PnDcp_Block_DevicePropertiesDeviceInstance");
        int startPos = readBuffer.getPos();
        int curPos;

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            int reserved = readBuffer.readUnsignedInt("reserved", 16);
            if(reserved != (int) 0x0000) {
                LOGGER.info("Expected constant value " + 0x0000 + " but got " + reserved + " for reserved field.");
            }
        }


        // Simple Field (deviceInstanceHigh)
short deviceInstanceHigh = readBuffer.readUnsignedShort("deviceInstanceHigh", 8) ;

        // Simple Field (deviceInstanceLow)
short deviceInstanceLow = readBuffer.readUnsignedShort("deviceInstanceLow", 8) ;
        readBuffer.closeContext("PnDcp_Block_DevicePropertiesDeviceInstance");
        // Create the instance
        return new PnDcp_Block_DevicePropertiesDeviceInstanceBuilder(deviceInstanceHigh, deviceInstanceLow);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, PnDcp_Block_DevicePropertiesDeviceInstance _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("PnDcp_Block_DevicePropertiesDeviceInstance");

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedInt("reserved", 16, ((Number) (int) 0x0000).intValue());

        // Simple Field (deviceInstanceHigh)
        short deviceInstanceHigh = (short) _value.getDeviceInstanceHigh();
        writeBuffer.writeUnsignedShort("deviceInstanceHigh", 8, ((Number) (deviceInstanceHigh)).shortValue());

        // Simple Field (deviceInstanceLow)
        short deviceInstanceLow = (short) _value.getDeviceInstanceLow();
        writeBuffer.writeUnsignedShort("deviceInstanceLow", 8, ((Number) (deviceInstanceLow)).shortValue());
        writeBuffer.popContext("PnDcp_Block_DevicePropertiesDeviceInstance");
    }

    public static class PnDcp_Block_DevicePropertiesDeviceInstanceBuilder implements PnDcp_BlockIO.PnDcp_BlockBuilder {
        private final short deviceInstanceHigh;
        private final short deviceInstanceLow;

        public PnDcp_Block_DevicePropertiesDeviceInstanceBuilder(short deviceInstanceHigh, short deviceInstanceLow) {
            this.deviceInstanceHigh = deviceInstanceHigh;
            this.deviceInstanceLow = deviceInstanceLow;
        }

        public PnDcp_Block_DevicePropertiesDeviceInstance build() {
            return new PnDcp_Block_DevicePropertiesDeviceInstance(deviceInstanceHigh, deviceInstanceLow);
        }
    }

}