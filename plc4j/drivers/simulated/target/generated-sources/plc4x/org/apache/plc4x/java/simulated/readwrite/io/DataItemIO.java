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
package org.apache.plc4x.java.simulated.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.EvaluationHelper;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.ReadBuffer;
import org.apache.plc4x.java.spi.generation.WriteBufferByteBased;
import org.apache.plc4x.java.simulated.readwrite.*;
import org.apache.plc4x.java.simulated.readwrite.types.*;
import org.apache.plc4x.java.spi.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.time.*;
import java.util.*;
import java.util.function.Supplier;

// Code generated by code-generation. DO NOT EDIT.

public class DataItemIO {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataItemIO.class);
    public static PlcValue staticParse(ReadBuffer readBuffer, String dataType, Integer numberOfValues) throws ParseException {
if(EvaluationHelper.equals(dataType, "BOOL") && EvaluationHelper.equals(numberOfValues, 1)) { // BOOL

            // Simple Field (value)
            Boolean value = readBuffer.readBit("");

            return new PlcBOOL(value);
        } else if(EvaluationHelper.equals(dataType, "BOOL")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcBOOL((Boolean) readBuffer.readBit("")));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "BYTE") && EvaluationHelper.equals(numberOfValues, 1)) { // BYTE

            // Simple Field (value)
            Short value = readBuffer.readUnsignedShort("", 8);

            return new PlcBYTE(value);
        } else if(EvaluationHelper.equals(dataType, "BYTE")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcUINT((Short) readBuffer.readUnsignedShort("", 8)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "WORD") && EvaluationHelper.equals(numberOfValues, 1)) { // WORD

            // Simple Field (value)
            Integer value = readBuffer.readUnsignedInt("", 16);

            return new PlcWORD(value);
        } else if(EvaluationHelper.equals(dataType, "WORD")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcUDINT((Integer) readBuffer.readUnsignedInt("", 16)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "DWORD") && EvaluationHelper.equals(numberOfValues, 1)) { // DWORD

            // Simple Field (value)
            Long value = readBuffer.readUnsignedLong("", 32);

            return new PlcDWORD(value);
        } else if(EvaluationHelper.equals(dataType, "DWORD")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcULINT((Long) readBuffer.readUnsignedLong("", 32)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "LWORD") && EvaluationHelper.equals(numberOfValues, 1)) { // LWORD

            // Simple Field (value)
            BigInteger value = readBuffer.readUnsignedBigInteger("", 64);

            return new PlcLWORD(value);
        } else if(EvaluationHelper.equals(dataType, "LWORD")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcLINT((BigInteger) readBuffer.readUnsignedBigInteger("", 64)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "SINT") && EvaluationHelper.equals(numberOfValues, 1)) { // SINT

            // Simple Field (value)
            Byte value = readBuffer.readSignedByte("", 8);

            return new PlcSINT(value);
        } else if(EvaluationHelper.equals(dataType, "SINT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcSINT((Byte) readBuffer.readSignedByte("", 8)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "INT") && EvaluationHelper.equals(numberOfValues, 1)) { // INT

            // Simple Field (value)
            Short value = readBuffer.readShort("", 16);

            return new PlcINT(value);
        } else if(EvaluationHelper.equals(dataType, "INT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcINT((Short) readBuffer.readShort("", 16)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "DINT") && EvaluationHelper.equals(numberOfValues, 1)) { // DINT

            // Simple Field (value)
            Integer value = readBuffer.readInt("", 32);

            return new PlcDINT(value);
        } else if(EvaluationHelper.equals(dataType, "DINT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcDINT((Integer) readBuffer.readInt("", 32)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "LINT") && EvaluationHelper.equals(numberOfValues, 1)) { // LINT

            // Simple Field (value)
            Long value = readBuffer.readLong("", 64);

            return new PlcLINT(value);
        } else if(EvaluationHelper.equals(dataType, "LINT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcLINT((Long) readBuffer.readLong("", 64)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "USINT") && EvaluationHelper.equals(numberOfValues, 1)) { // USINT

            // Simple Field (value)
            Short value = readBuffer.readUnsignedShort("", 8);

            return new PlcUSINT(value);
        } else if(EvaluationHelper.equals(dataType, "USINT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcUINT((Short) readBuffer.readUnsignedShort("", 8)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "UINT") && EvaluationHelper.equals(numberOfValues, 1)) { // UINT

            // Simple Field (value)
            Integer value = readBuffer.readUnsignedInt("", 16);

            return new PlcUINT(value);
        } else if(EvaluationHelper.equals(dataType, "UINT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcUDINT((Integer) readBuffer.readUnsignedInt("", 16)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "UDINT") && EvaluationHelper.equals(numberOfValues, 1)) { // UDINT

            // Simple Field (value)
            Long value = readBuffer.readUnsignedLong("", 32);

            return new PlcUDINT(value);
        } else if(EvaluationHelper.equals(dataType, "UDINT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcULINT((Long) readBuffer.readUnsignedLong("", 32)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "ULINT") && EvaluationHelper.equals(numberOfValues, 1)) { // ULINT

            // Simple Field (value)
            BigInteger value = readBuffer.readUnsignedBigInteger("", 64);

            return new PlcULINT(value);
        } else if(EvaluationHelper.equals(dataType, "ULINT")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcLINT((BigInteger) readBuffer.readUnsignedBigInteger("", 64)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "REAL") && EvaluationHelper.equals(numberOfValues, 1)) { // REAL

            // Simple Field (value)
            Float value = ((Supplier<Float>) (() -> {
            return (float) toFloat(readBuffer, "", true, 8, 23);
        })).get();

            return new PlcREAL(value);
        } else if(EvaluationHelper.equals(dataType, "REAL")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcREAL((Float) ((Supplier<Float>) (() -> {
            return (float) toFloat(readBuffer, "", true, 8, 23);
        })).get()));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "LREAL") && EvaluationHelper.equals(numberOfValues, 1)) { // LREAL

            // Simple Field (value)
            Double value = ((Supplier<Double>) (() -> {
            return (double) toFloat(readBuffer, "", true, 11, 52);
        })).get();

            return new PlcLREAL(value);
        } else if(EvaluationHelper.equals(dataType, "LREAL")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcLREAL((Double) ((Supplier<Double>) (() -> {
            return (double) toFloat(readBuffer, "", true, 11, 52);
        })).get()));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "CHAR") && EvaluationHelper.equals(numberOfValues, 1)) { // CHAR

            // Simple Field (value)
            Short value = readBuffer.readUnsignedShort("", 8);

            return new PlcCHAR(value);
        } else if(EvaluationHelper.equals(dataType, "CHAR")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcUINT((Short) readBuffer.readUnsignedShort("", 8)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "WCHAR") && EvaluationHelper.equals(numberOfValues, 1)) { // WCHAR

            // Simple Field (value)
            Integer value = readBuffer.readUnsignedInt("", 16);

            return new PlcWCHAR(value);
        } else if(EvaluationHelper.equals(dataType, "WCHAR")) { // List

            // Array field (value)
            // Count array
            if(numberOfValues > Integer.MAX_VALUE) {
                throw new ParseException("Array count of " + (numberOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
            }
            List<PlcValue> value;
            {
                int itemCount = (int) numberOfValues;
                value = new LinkedList<>();
                for(int curItem = 0; curItem < itemCount; curItem++) {
                    value.add(new PlcUDINT((Integer) readBuffer.readUnsignedInt("", 16)));
                }
            }

            return new PlcList(value);
        } else if(EvaluationHelper.equals(dataType, "STRING")) { // STRING

            // Simple Field (value)
            String value = readBuffer.readString("", 255, "'UTF-8'");

            return new PlcSTRING(value);
        } else if(EvaluationHelper.equals(dataType, "WSTRING")) { // STRING

            // Simple Field (value)
            String value = readBuffer.readString("", 255, "'UTF-8'");

            return new PlcSTRING(value);
        }
        return null;
    }

    public static WriteBufferByteBased staticSerialize(PlcValue _value, String dataType, Integer numberOfValues) throws ParseException {
        return staticSerialize(_value, dataType, numberOfValues, false);
    }

    public static WriteBufferByteBased staticSerialize(PlcValue _value, String dataType, Integer numberOfValues, boolean littleEndian) throws ParseException {
        if(EvaluationHelper.equals(dataType,"BOOL") && EvaluationHelper.equals(numberOfValues,1)) { // BOOL
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 1) / 8.0f), littleEndian);

            // Simple Field (value)
            boolean value = (boolean) _value.getBoolean();
            writeBuffer.writeBit("", (boolean) (value));
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"BOOL")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 1) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                boolean value = (boolean) val.getBoolean();
                writeBuffer.writeBit("", (boolean) (value));
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"BYTE") && EvaluationHelper.equals(numberOfValues,1)) { // BYTE
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 8) / 8.0f), littleEndian);

            // Simple Field (value)
            short value = (short) _value.getShort();
            writeBuffer.writeUnsignedShort("", 8, ((Number) (value)).shortValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"BYTE")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 8) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                short value = (short) val.getShort();
                writeBuffer.writeUnsignedShort("", 8, ((Number) (value)).shortValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"WORD") && EvaluationHelper.equals(numberOfValues,1)) { // WORD
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 16) / 8.0f), littleEndian);

            // Simple Field (value)
            int value = (int) _value.getInt();
            writeBuffer.writeUnsignedInt("", 16, ((Number) (value)).intValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"WORD")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 16) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                int value = (int) val.getInt();
                writeBuffer.writeUnsignedInt("", 16, ((Number) (value)).intValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"DWORD") && EvaluationHelper.equals(numberOfValues,1)) { // DWORD
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 32) / 8.0f), littleEndian);

            // Simple Field (value)
            long value = (long) _value.getLong();
            writeBuffer.writeUnsignedLong("", 32, ((Number) (value)).longValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"DWORD")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 32) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                long value = (long) val.getLong();
                writeBuffer.writeUnsignedLong("", 32, ((Number) (value)).longValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"LWORD") && EvaluationHelper.equals(numberOfValues,1)) { // LWORD
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 64) / 8.0f), littleEndian);

            // Simple Field (value)
            BigInteger value = (BigInteger) _value.getBigInteger();
            writeBuffer.writeUnsignedBigInteger("", 64, (BigInteger) (value));
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"LWORD")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 64) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                BigInteger value = (BigInteger) val.getBigInteger();
                writeBuffer.writeUnsignedBigInteger("", 64, (BigInteger) (value));
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"SINT") && EvaluationHelper.equals(numberOfValues,1)) { // SINT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 8) / 8.0f), littleEndian);

            // Simple Field (value)
            byte value = (byte) _value.getByte();
            writeBuffer.writeSignedByte("", 8, ((Number) (value)).byteValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"SINT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 8) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                byte value = (byte) val.getByte();
                writeBuffer.writeSignedByte("", 8, ((Number) (value)).byteValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"INT") && EvaluationHelper.equals(numberOfValues,1)) { // INT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 16) / 8.0f), littleEndian);

            // Simple Field (value)
            short value = (short) _value.getShort();
            writeBuffer.writeShort("", 16, ((Number) (value)).shortValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"INT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 16) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                short value = (short) val.getShort();
                writeBuffer.writeShort("", 16, ((Number) (value)).shortValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"DINT") && EvaluationHelper.equals(numberOfValues,1)) { // DINT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 32) / 8.0f), littleEndian);

            // Simple Field (value)
            int value = (int) _value.getInt();
            writeBuffer.writeInt("", 32, ((Number) (value)).intValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"DINT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 32) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                int value = (int) val.getInt();
                writeBuffer.writeInt("", 32, ((Number) (value)).intValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"LINT") && EvaluationHelper.equals(numberOfValues,1)) { // LINT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 64) / 8.0f), littleEndian);

            // Simple Field (value)
            long value = (long) _value.getLong();
            writeBuffer.writeLong("", 64, ((Number) (value)).longValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"LINT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 64) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                long value = (long) val.getLong();
                writeBuffer.writeLong("", 64, ((Number) (value)).longValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"USINT") && EvaluationHelper.equals(numberOfValues,1)) { // USINT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 8) / 8.0f), littleEndian);

            // Simple Field (value)
            short value = (short) _value.getShort();
            writeBuffer.writeUnsignedShort("", 8, ((Number) (value)).shortValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"USINT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 8) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                short value = (short) val.getShort();
                writeBuffer.writeUnsignedShort("", 8, ((Number) (value)).shortValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"UINT") && EvaluationHelper.equals(numberOfValues,1)) { // UINT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 16) / 8.0f), littleEndian);

            // Simple Field (value)
            int value = (int) _value.getInt();
            writeBuffer.writeUnsignedInt("", 16, ((Number) (value)).intValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"UINT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 16) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                int value = (int) val.getInt();
                writeBuffer.writeUnsignedInt("", 16, ((Number) (value)).intValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"UDINT") && EvaluationHelper.equals(numberOfValues,1)) { // UDINT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 32) / 8.0f), littleEndian);

            // Simple Field (value)
            long value = (long) _value.getLong();
            writeBuffer.writeUnsignedLong("", 32, ((Number) (value)).longValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"UDINT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 32) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                long value = (long) val.getLong();
                writeBuffer.writeUnsignedLong("", 32, ((Number) (value)).longValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"ULINT") && EvaluationHelper.equals(numberOfValues,1)) { // ULINT
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 64) / 8.0f), littleEndian);

            // Simple Field (value)
            BigInteger value = (BigInteger) _value.getBigInteger();
            writeBuffer.writeUnsignedBigInteger("", 64, (BigInteger) (value));
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"ULINT")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 64) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                BigInteger value = (BigInteger) val.getBigInteger();
                writeBuffer.writeUnsignedBigInteger("", 64, (BigInteger) (value));
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"REAL") && EvaluationHelper.equals(numberOfValues,1)) { // REAL
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 32) / 8.0f), littleEndian);

            // Simple Field (value)
            float value = (float) _value.getFloat();
            writeBuffer.writeFloat("", (value),8,23);
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"REAL")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 32) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                float value = (float) val.getFloat();
                writeBuffer.writeFloat("", (value),8,23);
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"LREAL") && EvaluationHelper.equals(numberOfValues,1)) { // LREAL
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 64) / 8.0f), littleEndian);

            // Simple Field (value)
            double value = (double) _value.getDouble();
            writeBuffer.writeDouble("", (value),11,52);
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"LREAL")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 64) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                double value = (double) val.getDouble();
                writeBuffer.writeDouble("", (value),11,52);
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"CHAR") && EvaluationHelper.equals(numberOfValues,1)) { // CHAR
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 8) / 8.0f), littleEndian);

            // Simple Field (value)
            short value = (short) _value.getShort();
            writeBuffer.writeUnsignedShort("", 8, ((Number) (value)).shortValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"CHAR")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 8) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                short value = (short) val.getShort();
                writeBuffer.writeUnsignedShort("", 8, ((Number) (value)).shortValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"WCHAR") && EvaluationHelper.equals(numberOfValues,1)) { // WCHAR
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 16) / 8.0f), littleEndian);

            // Simple Field (value)
            int value = (int) _value.getInt();
            writeBuffer.writeUnsignedInt("", 16, ((Number) (value)).intValue());
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"WCHAR")) { // List
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) (numberOfValues * 16) + 0) / 8.0f), littleEndian);

            PlcList values = (PlcList) _value;

            for (PlcValue val : ((List<PlcValue>) values.getList())) {
                int value = (int) val.getInt();
                writeBuffer.writeUnsignedInt("", 16, ((Number) (value)).intValue());
            }

            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"STRING")) { // STRING
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 255 + 0) / 8.0f), littleEndian);

            // Simple Field (value)
            String value = (String) _value.getString();
            writeBuffer.writeString("", 255, "'UTF-8'", (String) (value));
            return writeBuffer;
        } else if(EvaluationHelper.equals(dataType,"WSTRING")) { // STRING
        WriteBufferByteBased writeBuffer = new WriteBufferByteBased((int) Math.ceil(((float) 255 + 0) / 8.0f), littleEndian);

            // Simple Field (value)
            String value = (String) _value.getString();
            writeBuffer.writeString("", 255, "'UTF-8'", (String) (value));
            return writeBuffer;
        }
        return null;
    }

}