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
package org.apache.plc4x.java.abeth.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.abeth.readwrite.*;
import org.apache.plc4x.java.abeth.readwrite.io.*;
import org.apache.plc4x.java.abeth.readwrite.types.*;
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

public class DF1RequestProtectedTypedLogicalReadIO implements MessageIO<DF1RequestProtectedTypedLogicalRead, DF1RequestProtectedTypedLogicalRead> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DF1RequestProtectedTypedLogicalReadIO.class);

    @Override
    public DF1RequestProtectedTypedLogicalRead parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (DF1RequestProtectedTypedLogicalRead) new DF1RequestCommandIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, DF1RequestProtectedTypedLogicalRead value, Object... args) throws ParseException {
        new DF1RequestCommandIO().serialize(writeBuffer, value, args);
    }

    public static DF1RequestProtectedTypedLogicalReadBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("DF1RequestProtectedTypedLogicalRead");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (byteSize)
short byteSize = readBuffer.readUnsignedShort("byteSize", 8) ;

        // Simple Field (fileNumber)
short fileNumber = readBuffer.readUnsignedShort("fileNumber", 8) ;

        // Simple Field (fileType)
short fileType = readBuffer.readUnsignedShort("fileType", 8) ;

        // Simple Field (elementNumber)
short elementNumber = readBuffer.readUnsignedShort("elementNumber", 8) ;

        // Simple Field (subElementNumber)
short subElementNumber = readBuffer.readUnsignedShort("subElementNumber", 8) ;
        readBuffer.closeContext("DF1RequestProtectedTypedLogicalRead");
        // Create the instance
        return new DF1RequestProtectedTypedLogicalReadBuilder(byteSize, fileNumber, fileType, elementNumber, subElementNumber);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, DF1RequestProtectedTypedLogicalRead _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("DF1RequestProtectedTypedLogicalRead");

        // Simple Field (byteSize)
        short byteSize = (short) _value.getByteSize();
        writeBuffer.writeUnsignedShort("byteSize", 8, ((Number) (byteSize)).shortValue());

        // Simple Field (fileNumber)
        short fileNumber = (short) _value.getFileNumber();
        writeBuffer.writeUnsignedShort("fileNumber", 8, ((Number) (fileNumber)).shortValue());

        // Simple Field (fileType)
        short fileType = (short) _value.getFileType();
        writeBuffer.writeUnsignedShort("fileType", 8, ((Number) (fileType)).shortValue());

        // Simple Field (elementNumber)
        short elementNumber = (short) _value.getElementNumber();
        writeBuffer.writeUnsignedShort("elementNumber", 8, ((Number) (elementNumber)).shortValue());

        // Simple Field (subElementNumber)
        short subElementNumber = (short) _value.getSubElementNumber();
        writeBuffer.writeUnsignedShort("subElementNumber", 8, ((Number) (subElementNumber)).shortValue());
        writeBuffer.popContext("DF1RequestProtectedTypedLogicalRead");
    }

    public static class DF1RequestProtectedTypedLogicalReadBuilder implements DF1RequestCommandIO.DF1RequestCommandBuilder {
        private final short byteSize;
        private final short fileNumber;
        private final short fileType;
        private final short elementNumber;
        private final short subElementNumber;

        public DF1RequestProtectedTypedLogicalReadBuilder(short byteSize, short fileNumber, short fileType, short elementNumber, short subElementNumber) {
            this.byteSize = byteSize;
            this.fileNumber = fileNumber;
            this.fileType = fileType;
            this.elementNumber = elementNumber;
            this.subElementNumber = subElementNumber;
        }

        public DF1RequestProtectedTypedLogicalRead build() {
            return new DF1RequestProtectedTypedLogicalRead(byteSize, fileNumber, fileType, elementNumber, subElementNumber);
        }
    }

}