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

public class AddReferencesRequestIO implements MessageIO<AddReferencesRequest, AddReferencesRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddReferencesRequestIO.class);

    @Override
    public AddReferencesRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (AddReferencesRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, AddReferencesRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static AddReferencesRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("AddReferencesRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");


        // Simple Field (noOfReferencesToAdd)
int noOfReferencesToAdd = readBuffer.readInt("noOfReferencesToAdd", 32) ;        // Array field (referencesToAdd)
        readBuffer.pullContext("referencesToAdd", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfReferencesToAdd > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfReferencesToAdd) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] referencesToAdd;
        {
            int itemCount = Math.max(0, (int) noOfReferencesToAdd);
            referencesToAdd = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
referencesToAdd[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(381) ) ;            }
        }
            readBuffer.closeContext("referencesToAdd", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("AddReferencesRequest");
        // Create the instance
        return new AddReferencesRequestBuilder(requestHeader, noOfReferencesToAdd, referencesToAdd);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, AddReferencesRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("AddReferencesRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (noOfReferencesToAdd)
        int noOfReferencesToAdd = (int) _value.getNoOfReferencesToAdd();
        writeBuffer.writeInt("noOfReferencesToAdd", 32, ((Number) (noOfReferencesToAdd)).intValue());

        // Array Field (referencesToAdd)
        if(_value.getReferencesToAdd() != null) {
            writeBuffer.pushContext("referencesToAdd", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getReferencesToAdd().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getReferencesToAdd()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("referencesToAdd", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("AddReferencesRequest");
    }

    public static class AddReferencesRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final int noOfReferencesToAdd;
        private final ExtensionObjectDefinition[] referencesToAdd;

        public AddReferencesRequestBuilder(ExtensionObjectDefinition requestHeader, int noOfReferencesToAdd, ExtensionObjectDefinition[] referencesToAdd) {
            this.requestHeader = requestHeader;
            this.noOfReferencesToAdd = noOfReferencesToAdd;
            this.referencesToAdd = referencesToAdd;
        }

        public AddReferencesRequest build() {
            return new AddReferencesRequest(requestHeader, noOfReferencesToAdd, referencesToAdd);
        }
    }

}