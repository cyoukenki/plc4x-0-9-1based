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
package org.apache.plc4x.java.opcua.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.*;

import org.apache.plc4x.java.opcua.readwrite.types.*;
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

public class ImagePNGIO implements MessageIO<ImagePNG, ImagePNG> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImagePNGIO.class);

    @Override
    public ImagePNG parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return ImagePNGIO.staticParse(readBuffer);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ImagePNG value, Object... args) throws ParseException {
        ImagePNGIO.staticSerialize(writeBuffer, value);
    }

    public static ImagePNG staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("ImagePNG");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.closeContext("ImagePNG");
        // Create the instance
        return new ImagePNG();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ImagePNG _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ImagePNG");
        writeBuffer.popContext("ImagePNG");
    }

}