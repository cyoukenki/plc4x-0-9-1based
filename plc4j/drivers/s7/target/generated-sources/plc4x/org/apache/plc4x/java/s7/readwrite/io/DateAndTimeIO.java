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

public class DateAndTimeIO implements MessageIO<DateAndTime, DateAndTime> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateAndTimeIO.class);

    @Override
    public DateAndTime parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return DateAndTimeIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, DateAndTime value, Object... args) throws ParseException {
        DateAndTimeIO.staticSerialize(writeBuffer, value);
    }

    public static DateAndTime staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("DateAndTime");
        int startPos = readBuffer.getPos();
        int curPos;

        // Manual Field (year)
        short year = (short) (org.apache.plc4x.java.s7.utils.S7EventHelper.BcdToInt(readBuffer));

        // Manual Field (month)
        short month = (short) (org.apache.plc4x.java.s7.utils.S7EventHelper.BcdToInt(readBuffer));

        // Manual Field (day)
        short day = (short) (org.apache.plc4x.java.s7.utils.S7EventHelper.BcdToInt(readBuffer));

        // Manual Field (hour)
        short hour = (short) (org.apache.plc4x.java.s7.utils.S7EventHelper.BcdToInt(readBuffer));

        // Manual Field (minutes)
        short minutes = (short) (org.apache.plc4x.java.s7.utils.S7EventHelper.BcdToInt(readBuffer));

        // Manual Field (seconds)
        short seconds = (short) (org.apache.plc4x.java.s7.utils.S7EventHelper.BcdToInt(readBuffer));

        // Manual Field (msec)
        int msec = (int) (org.apache.plc4x.java.s7.utils.S7EventHelper.S7msecToInt(readBuffer));


        // Simple Field (dow)
byte dow = readBuffer.readUnsignedByte("dow", 4) ;
        readBuffer.closeContext("DateAndTime");
        // Create the instance
        return new DateAndTime(year, month, day, hour, minutes, seconds, msec, dow);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, DateAndTime _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("DateAndTime");

        // Manual Field (year)
        org.apache.plc4x.java.s7.utils.S7EventHelper.ByteToBcd(writeBuffer, _value.getYear());

        // Manual Field (month)
        org.apache.plc4x.java.s7.utils.S7EventHelper.ByteToBcd(writeBuffer, _value.getMonth());

        // Manual Field (day)
        org.apache.plc4x.java.s7.utils.S7EventHelper.ByteToBcd(writeBuffer, _value.getDay());

        // Manual Field (hour)
        org.apache.plc4x.java.s7.utils.S7EventHelper.ByteToBcd(writeBuffer, _value.getHour());

        // Manual Field (minutes)
        org.apache.plc4x.java.s7.utils.S7EventHelper.ByteToBcd(writeBuffer, _value.getMinutes());

        // Manual Field (seconds)
        org.apache.plc4x.java.s7.utils.S7EventHelper.ByteToBcd(writeBuffer, _value.getSeconds());

        // Manual Field (msec)
        org.apache.plc4x.java.s7.utils.S7EventHelper.IntToS7msec(writeBuffer, _value.getMsec());

        // Simple Field (dow)
        byte dow = (byte) _value.getDow();
        writeBuffer.writeUnsignedByte("dow", 4, ((Number) (dow)).byteValue());
        writeBuffer.popContext("DateAndTime");
    }

}