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

public class GetEndpointsRequestIO implements MessageIO<GetEndpointsRequest, GetEndpointsRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetEndpointsRequestIO.class);

    @Override
    public GetEndpointsRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (GetEndpointsRequest) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, GetEndpointsRequest value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static GetEndpointsRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("GetEndpointsRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("requestHeader");

        // Simple Field (requestHeader)
ExtensionObjectDefinition requestHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(391) ) ;        readBuffer.closeContext("requestHeader");

        readBuffer.pullContext("endpointUrl");

        // Simple Field (endpointUrl)
PascalString endpointUrl = PascalStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("endpointUrl");


        // Simple Field (noOfLocaleIds)
int noOfLocaleIds = readBuffer.readInt("noOfLocaleIds", 32) ;        // Array field (localeIds)
        readBuffer.pullContext("localeIds", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfLocaleIds > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfLocaleIds) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        PascalString[] localeIds;
        {
            int itemCount = Math.max(0, (int) noOfLocaleIds);
            localeIds = new PascalString[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
localeIds[curItem] = PascalStringIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("localeIds", WithReaderWriterArgs.WithRenderAsList(true));


        // Simple Field (noOfProfileUris)
int noOfProfileUris = readBuffer.readInt("noOfProfileUris", 32) ;        // Array field (profileUris)
        readBuffer.pullContext("profileUris", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfProfileUris > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfProfileUris) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        PascalString[] profileUris;
        {
            int itemCount = Math.max(0, (int) noOfProfileUris);
            profileUris = new PascalString[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
profileUris[curItem] = PascalStringIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("profileUris", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("GetEndpointsRequest");
        // Create the instance
        return new GetEndpointsRequestBuilder(requestHeader, endpointUrl, noOfLocaleIds, localeIds, noOfProfileUris, profileUris);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, GetEndpointsRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("GetEndpointsRequest");

        // Simple Field (requestHeader)
        ExtensionObjectDefinition requestHeader = (ExtensionObjectDefinition) _value.getRequestHeader();
        writeBuffer.pushContext("requestHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, requestHeader);
        writeBuffer.popContext("requestHeader");

        // Simple Field (endpointUrl)
        PascalString endpointUrl = (PascalString) _value.getEndpointUrl();
        writeBuffer.pushContext("endpointUrl");
        PascalStringIO.staticSerialize(writeBuffer, endpointUrl);
        writeBuffer.popContext("endpointUrl");

        // Simple Field (noOfLocaleIds)
        int noOfLocaleIds = (int) _value.getNoOfLocaleIds();
        writeBuffer.writeInt("noOfLocaleIds", 32, ((Number) (noOfLocaleIds)).intValue());

        // Array Field (localeIds)
        if(_value.getLocaleIds() != null) {
            writeBuffer.pushContext("localeIds", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getLocaleIds().length;
            int curItem = 0;
            for(PascalString element : _value.getLocaleIds()) {
                boolean lastItem = curItem == (itemCount - 1);
                PascalStringIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("localeIds", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (noOfProfileUris)
        int noOfProfileUris = (int) _value.getNoOfProfileUris();
        writeBuffer.writeInt("noOfProfileUris", 32, ((Number) (noOfProfileUris)).intValue());

        // Array Field (profileUris)
        if(_value.getProfileUris() != null) {
            writeBuffer.pushContext("profileUris", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getProfileUris().length;
            int curItem = 0;
            for(PascalString element : _value.getProfileUris()) {
                boolean lastItem = curItem == (itemCount - 1);
                PascalStringIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("profileUris", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("GetEndpointsRequest");
    }

    public static class GetEndpointsRequestBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition requestHeader;
        private final PascalString endpointUrl;
        private final int noOfLocaleIds;
        private final PascalString[] localeIds;
        private final int noOfProfileUris;
        private final PascalString[] profileUris;

        public GetEndpointsRequestBuilder(ExtensionObjectDefinition requestHeader, PascalString endpointUrl, int noOfLocaleIds, PascalString[] localeIds, int noOfProfileUris, PascalString[] profileUris) {
            this.requestHeader = requestHeader;
            this.endpointUrl = endpointUrl;
            this.noOfLocaleIds = noOfLocaleIds;
            this.localeIds = localeIds;
            this.noOfProfileUris = noOfProfileUris;
            this.profileUris = profileUris;
        }

        public GetEndpointsRequest build() {
            return new GetEndpointsRequest(requestHeader, endpointUrl, noOfLocaleIds, localeIds, noOfProfileUris, profileUris);
        }
    }

}
