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

public class ContentFilterElementIO implements MessageIO<ContentFilterElement, ContentFilterElement> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFilterElementIO.class);

    @Override
    public ContentFilterElement parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (ContentFilterElement) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ContentFilterElement value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static ContentFilterElementBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("ContentFilterElement");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("filterOperator");

        // Simple Field (filterOperator)
        // enum based simple field with type FilterOperator
        FilterOperator filterOperator = FilterOperator.enumForValue(readBuffer.readUnsignedLong("FilterOperator", 32));
        readBuffer.closeContext("filterOperator");


        // Simple Field (noOfFilterOperands)
int noOfFilterOperands = readBuffer.readInt("noOfFilterOperands", 32) ;        // Array field (filterOperands)
        readBuffer.pullContext("filterOperands", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfFilterOperands > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfFilterOperands) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObject[] filterOperands;
        {
            int itemCount = Math.max(0, (int) noOfFilterOperands);
            filterOperands = new ExtensionObject[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
filterOperands[curItem] = ExtensionObjectIO.staticParse(readBuffer , (boolean) (true) ) ;            }
        }
            readBuffer.closeContext("filterOperands", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("ContentFilterElement");
        // Create the instance
        return new ContentFilterElementBuilder(filterOperator, noOfFilterOperands, filterOperands);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ContentFilterElement _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ContentFilterElement");

        // Simple Field (filterOperator)
        FilterOperator filterOperator = (FilterOperator) _value.getFilterOperator();
        writeBuffer.pushContext("filterOperator");
        // enum field with type FilterOperator
        writeBuffer.writeUnsignedLong("FilterOperator", 32, ((Number) (filterOperator.getValue())).longValue(), WithReaderWriterArgs.WithAdditionalStringRepresentation(filterOperator.name()));
        writeBuffer.popContext("filterOperator");

        // Simple Field (noOfFilterOperands)
        int noOfFilterOperands = (int) _value.getNoOfFilterOperands();
        writeBuffer.writeInt("noOfFilterOperands", 32, ((Number) (noOfFilterOperands)).intValue());

        // Array Field (filterOperands)
        if(_value.getFilterOperands() != null) {
            writeBuffer.pushContext("filterOperands", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getFilterOperands().length;
            int curItem = 0;
            for(ExtensionObject element : _value.getFilterOperands()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("filterOperands", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("ContentFilterElement");
    }

    public static class ContentFilterElementBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final FilterOperator filterOperator;
        private final int noOfFilterOperands;
        private final ExtensionObject[] filterOperands;

        public ContentFilterElementBuilder(FilterOperator filterOperator, int noOfFilterOperands, ExtensionObject[] filterOperands) {
            this.filterOperator = filterOperator;
            this.noOfFilterOperands = noOfFilterOperands;
            this.filterOperands = filterOperands;
        }

        public ContentFilterElement build() {
            return new ContentFilterElement(filterOperator, noOfFilterOperands, filterOperands);
        }
    }

}