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

public class OpcuaCloseRequestIO implements MessageIO<OpcuaCloseRequest, OpcuaCloseRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpcuaCloseRequestIO.class);

    @Override
    public OpcuaCloseRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (OpcuaCloseRequest) new MessagePDUIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, OpcuaCloseRequest value, Object... args) throws ParseException {
        new MessagePDUIO().serialize(writeBuffer, value, args);
    }

    public static OpcuaCloseRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("OpcuaCloseRequest");
        int startPos = readBuffer.getPos();
        int curPos;


        // Simple Field (chunk)
String chunk = readBuffer.readString("chunk", 8, "UTF-8") ;
        // Implicit Field (messageSize) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int messageSize = readBuffer.readInt("messageSize", 32);


        // Simple Field (secureChannelId)
int secureChannelId = readBuffer.readInt("secureChannelId", 32) ;

        // Simple Field (secureTokenId)
int secureTokenId = readBuffer.readInt("secureTokenId", 32) ;

        // Simple Field (sequenceNumber)
int sequenceNumber = readBuffer.readInt("sequenceNumber", 32) ;

        // Simple Field (requestId)
int requestId = readBuffer.readInt("requestId", 32) ;
        readBuffer.pullContext("message");

        // Simple Field (message)
ExtensionObject message = ExtensionObjectIO.staticParse(readBuffer , (boolean) (false) ) ;        readBuffer.closeContext("message");

        readBuffer.closeContext("OpcuaCloseRequest");
        // Create the instance
        return new OpcuaCloseRequestBuilder(chunk, secureChannelId, secureTokenId, sequenceNumber, requestId, message);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, OpcuaCloseRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("OpcuaCloseRequest");

        // Simple Field (chunk)
        String chunk = (String) _value.getChunk();
        writeBuffer.writeString("chunk", 8, "UTF-8", (String) (chunk));

        // Implicit Field (messageSize) (Used for parsing, but it's value is not stored as it's implicitly given by the objects content)
        int messageSize = (int) (_value.getLengthInBytes());
        writeBuffer.writeInt("messageSize", 32, ((Number) (messageSize)).intValue());

        // Simple Field (secureChannelId)
        int secureChannelId = (int) _value.getSecureChannelId();
        writeBuffer.writeInt("secureChannelId", 32, ((Number) (secureChannelId)).intValue());

        // Simple Field (secureTokenId)
        int secureTokenId = (int) _value.getSecureTokenId();
        writeBuffer.writeInt("secureTokenId", 32, ((Number) (secureTokenId)).intValue());

        // Simple Field (sequenceNumber)
        int sequenceNumber = (int) _value.getSequenceNumber();
        writeBuffer.writeInt("sequenceNumber", 32, ((Number) (sequenceNumber)).intValue());

        // Simple Field (requestId)
        int requestId = (int) _value.getRequestId();
        writeBuffer.writeInt("requestId", 32, ((Number) (requestId)).intValue());

        // Simple Field (message)
        ExtensionObject message = (ExtensionObject) _value.getMessage();
        writeBuffer.pushContext("message");
        ExtensionObjectIO.staticSerialize(writeBuffer, message);
        writeBuffer.popContext("message");
        writeBuffer.popContext("OpcuaCloseRequest");
    }

    public static class OpcuaCloseRequestBuilder implements MessagePDUIO.MessagePDUBuilder {
        private final String chunk;
        private final int secureChannelId;
        private final int secureTokenId;
        private final int sequenceNumber;
        private final int requestId;
        private final ExtensionObject message;

        public OpcuaCloseRequestBuilder(String chunk, int secureChannelId, int secureTokenId, int sequenceNumber, int requestId, ExtensionObject message) {
            this.chunk = chunk;
            this.secureChannelId = secureChannelId;
            this.secureTokenId = secureTokenId;
            this.sequenceNumber = sequenceNumber;
            this.requestId = requestId;
            this.message = message;
        }

        public OpcuaCloseRequest build() {
            return new OpcuaCloseRequest(chunk, secureChannelId, secureTokenId, sequenceNumber, requestId, message);
        }
    }

}