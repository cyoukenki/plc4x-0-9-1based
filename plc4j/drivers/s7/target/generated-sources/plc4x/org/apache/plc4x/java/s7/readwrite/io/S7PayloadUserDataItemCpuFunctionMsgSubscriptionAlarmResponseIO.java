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
package org.apache.plc4x.java.s7.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.s7.readwrite.*;
import org.apache.plc4x.java.s7.readwrite.io.*;
import org.apache.plc4x.java.s7.readwrite.types.*;
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

public class S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponseIO implements MessageIO<S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse, S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponseIO.class);

    @Override
    public S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) new S7PayloadUserDataItemIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse value, Object... args) throws ParseException {
        new S7PayloadUserDataItemIO().serialize(writeBuffer, value, args);
    }

    public static S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (result)
short result = readBuffer.readUnsignedShort("result", 8) ;

        // Simple Field (reserved01)
short reserved01 = readBuffer.readUnsignedShort("reserved01", 8) ;
        readBuffer.pullContext("alarmType");

        // Simple Field (alarmType)
        // enum based simple field with type AlarmType
        AlarmType alarmType = AlarmType.enumForValue(readBuffer.readUnsignedShort("AlarmType", 8));
        readBuffer.closeContext("alarmType");


        // Simple Field (reserved02)
short reserved02 = readBuffer.readUnsignedShort("reserved02", 8) ;

        // Simple Field (reserved03)
short reserved03 = readBuffer.readUnsignedShort("reserved03", 8) ;
        readBuffer.closeContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse");
        // Create the instance
        return new S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponseBuilder(result, reserved01, alarmType, reserved02, reserved03);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse");

        // Simple Field (result)
        short result = (short) _value.getResult();
        writeBuffer.writeUnsignedShort("result", 8, ((Number) (result)).shortValue());

        // Simple Field (reserved01)
        short reserved01 = (short) _value.getReserved01();
        writeBuffer.writeUnsignedShort("reserved01", 8, ((Number) (reserved01)).shortValue());

        // Simple Field (alarmType)
        AlarmType alarmType = (AlarmType) _value.getAlarmType();
        writeBuffer.pushContext("alarmType");
        // enum field with type AlarmType
        writeBuffer.writeUnsignedShort("AlarmType", 8, ((Number) (alarmType.getValue())).shortValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(alarmType.name()));
        writeBuffer.popContext("alarmType");

        // Simple Field (reserved02)
        short reserved02 = (short) _value.getReserved02();
        writeBuffer.writeUnsignedShort("reserved02", 8, ((Number) (reserved02)).shortValue());

        // Simple Field (reserved03)
        short reserved03 = (short) _value.getReserved03();
        writeBuffer.writeUnsignedShort("reserved03", 8, ((Number) (reserved03)).shortValue());
        writeBuffer.popContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse");
    }

    public static class S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponseBuilder implements S7PayloadUserDataItemIO.S7PayloadUserDataItemBuilder {
        private final short result;
        private final short reserved01;
        private final AlarmType alarmType;
        private final short reserved02;
        private final short reserved03;

        public S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponseBuilder(short result, short reserved01, AlarmType alarmType, short reserved02, short reserved03) {
            this.result = result;
            this.reserved01 = reserved01;
            this.alarmType = alarmType;
            this.reserved02 = reserved02;
            this.reserved03 = reserved03;
        }

        public S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse build(DataTransportErrorCode returnCode, DataTransportSize transportSize) {
            return new S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse(returnCode, transportSize, result, reserved01, alarmType, reserved02, reserved03);
        }
    }

}