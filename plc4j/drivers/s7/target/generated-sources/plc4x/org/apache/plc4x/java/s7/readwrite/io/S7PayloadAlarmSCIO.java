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

public class S7PayloadAlarmSCIO implements MessageIO<S7PayloadAlarmSC, S7PayloadAlarmSC> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7PayloadAlarmSCIO.class);

    @Override
    public S7PayloadAlarmSC parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (S7PayloadAlarmSC) new S7PayloadUserDataItemIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7PayloadAlarmSC value, Object... args) throws ParseException {
        new S7PayloadUserDataItemIO().serialize(writeBuffer, value, args);
    }

    public static S7PayloadAlarmSCBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S7PayloadAlarmSC");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("alarmMessage");

        // Simple Field (alarmMessage)
AlarmMessagePushType alarmMessage = AlarmMessagePushTypeIO.staticParse(readBuffer ) ;        readBuffer.closeContext("alarmMessage");

        readBuffer.closeContext("S7PayloadAlarmSC");
        // Create the instance
        return new S7PayloadAlarmSCBuilder(alarmMessage);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7PayloadAlarmSC _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7PayloadAlarmSC");

        // Simple Field (alarmMessage)
        AlarmMessagePushType alarmMessage = (AlarmMessagePushType) _value.getAlarmMessage();
        writeBuffer.pushContext("alarmMessage");
        AlarmMessagePushTypeIO.staticSerialize(writeBuffer, alarmMessage);
        writeBuffer.popContext("alarmMessage");
        writeBuffer.popContext("S7PayloadAlarmSC");
    }

    public static class S7PayloadAlarmSCBuilder implements S7PayloadUserDataItemIO.S7PayloadUserDataItemBuilder {
        private final AlarmMessagePushType alarmMessage;

        public S7PayloadAlarmSCBuilder(AlarmMessagePushType alarmMessage) {
            this.alarmMessage = alarmMessage;
        }

        public S7PayloadAlarmSC build(DataTransportErrorCode returnCode, DataTransportSize transportSize) {
            return new S7PayloadAlarmSC(returnCode, transportSize, alarmMessage);
        }
    }

}