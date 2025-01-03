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
import org.apache.plc4x.java.opcua.readwrite.io.*;
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

public class RegisterServerRequestIO implements MessageIO<RegisterServerRequest, RegisterServerRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterServerRequestIO.class);

    @Override
    public RegisterServerRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (RegisterServerRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, RegisterServerRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static RegisterServerRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("RegisterServerRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");

        readBuffer.pullContext("server");

        // Simple Field (server)
ExtensionObjectDefinition server = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(434) ) ;        readBuffer.closeContext("server");

        readBuffer.closeContext("RegisterServerRequest");
        // Create the instance
        return new RegisterServerRequestBuilder(requestHeader, server);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, RegisterServerRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("RegisterServerRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (server)
        ExtensionObjectDefinition server = (ExtensionObjectDefinition) _value.getServer();
        writeBuffer.pushContext("server");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, server);
        writeBuffer.popContext("server");
        writeBuffer.popContext("RegisterServerRequest");
    }

    public static class RegisterServerRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final ExtensionObjectDefinition server;

        public RegisterServerRequestBuilder(ExtensionObjectDefinition requestHeader, ExtensionObjectDefinition server) {
            this.requestHeader = requestHeader;
            this.server = server;
        }

        public RegisterServerRequest build() {
            return new RegisterServerRequest(requestHeader, server);
        }
    }

}
