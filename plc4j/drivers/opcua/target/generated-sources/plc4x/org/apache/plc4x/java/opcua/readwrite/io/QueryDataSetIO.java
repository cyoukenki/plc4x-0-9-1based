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

public class QueryDataSetIO implements MessageIO<QueryDataSet, QueryDataSet> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDataSetIO.class);

    @Override
    public QueryDataSet parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (QueryDataSet) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, QueryDataSet value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static QueryDataSetBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("QueryDataSet");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("nodeId");

        // Simple Field (nodeId)
ExpandedNodeId nodeId = ExpandedNodeIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("nodeId");

        readBuffer.pullContext("typeDefinitionNode");

        // Simple Field (typeDefinitionNode)
ExpandedNodeId typeDefinitionNode = ExpandedNodeIdIO.staticParse(readBuffer ) ;        readBuffer.closeContext("typeDefinitionNode");


        // Simple Field (noOfValues)
int noOfValues = readBuffer.readInt("noOfValues", 32) ;        // Array field (values)
        readBuffer.pullContext("values", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfValues > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfValues) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        Variant[] values;
        {
            int itemCount = Math.max(0, (int) noOfValues);
            values = new Variant[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
values[curItem] = VariantIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("values", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.closeContext("QueryDataSet");
        // Create the instance
        return new QueryDataSetBuilder(nodeId, typeDefinitionNode, noOfValues, values);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, QueryDataSet _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("QueryDataSet");

        // Simple Field (nodeId)
        ExpandedNodeId nodeId = (ExpandedNodeId) _value.getNodeId();
        writeBuffer.pushContext("nodeId");
        ExpandedNodeIdIO.staticSerialize(writeBuffer, nodeId);
        writeBuffer.popContext("nodeId");

        // Simple Field (typeDefinitionNode)
        ExpandedNodeId typeDefinitionNode = (ExpandedNodeId) _value.getTypeDefinitionNode();
        writeBuffer.pushContext("typeDefinitionNode");
        ExpandedNodeIdIO.staticSerialize(writeBuffer, typeDefinitionNode);
        writeBuffer.popContext("typeDefinitionNode");

        // Simple Field (noOfValues)
        int noOfValues = (int) _value.getNoOfValues();
        writeBuffer.writeInt("noOfValues", 32, ((Number) (noOfValues)).intValue());

        // Array Field (values)
        if(_value.getValues() != null) {
            writeBuffer.pushContext("values", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getValues().length;
            int curItem = 0;
            for(Variant element : _value.getValues()) {
                boolean lastItem = curItem == (itemCount - 1);
                VariantIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("values", WithReaderWriterArgs.WithRenderAsList(true));
        }
        writeBuffer.popContext("QueryDataSet");
    }

    public static class QueryDataSetBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExpandedNodeId nodeId;
        private final ExpandedNodeId typeDefinitionNode;
        private final int noOfValues;
        private final Variant[] values;

        public QueryDataSetBuilder(ExpandedNodeId nodeId, ExpandedNodeId typeDefinitionNode, int noOfValues, Variant[] values) {
            this.nodeId = nodeId;
            this.typeDefinitionNode = typeDefinitionNode;
            this.noOfValues = noOfValues;
            this.values = values;
        }

        public QueryDataSet build() {
            return new QueryDataSet(nodeId, typeDefinitionNode, noOfValues, values);
        }
    }

}
