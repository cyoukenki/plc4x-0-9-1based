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

public class S7VarRequestParameterItemIO implements MessageIO<S7VarRequestParameterItem, S7VarRequestParameterItem> {

    private static final Logger LOGGER = LoggerFactory.getLogger(S7VarRequestParameterItemIO.class);

    @Override
    public S7VarRequestParameterItem parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return S7VarRequestParameterItemIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, S7VarRequestParameterItem value, Object... args) throws ParseException {
        S7VarRequestParameterItemIO.staticSerialize(writeBuffer, value);
    }

    public static S7VarRequestParameterItem staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("S7VarRequestParameterItem");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (itemType) (Used as input to a switch field)
        short itemType = readBuffer.readUnsignedShort("itemType", 8);


        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        S7VarRequestParameterItemBuilder builder = null;
                if(EvaluationHelper.equals(itemType, 0x12)) {
            builder = S7VarRequestParameterItemAddressIO.staticParse(readBuffer);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("S7VarRequestParameterItem");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, S7VarRequestParameterItem _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("S7VarRequestParameterItem");

        // Discriminator Field (itemType) (Used as input to a switch field)
        short itemType = (short) _value.getItemType();
            writeBuffer.writeUnsignedShort("itemType", 8, ((Number) (itemType)).shortValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof S7VarRequestParameterItemAddress) {
            S7VarRequestParameterItemAddressIO.staticSerialize(writeBuffer, (S7VarRequestParameterItemAddress) _value);
        }
        writeBuffer.popContext("S7VarRequestParameterItem");
    }

    public static interface S7VarRequestParameterItemBuilder {
        S7VarRequestParameterItem build();
    }

}