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

public class QueryFirstResponseIO implements MessageIO<QueryFirstResponse, QueryFirstResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryFirstResponseIO.class);

    @Override
    public QueryFirstResponse parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (QueryFirstResponse) new ExtensionObjectDefinitionIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, QueryFirstResponse value, Object... args) throws ParseException {
        new ExtensionObjectDefinitionIO().serialize(writeBuffer, value, args);
    }

    public static QueryFirstResponseBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("QueryFirstResponse");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.pullContext("responseHeader");

        // Simple Field (responseHeader)
ExtensionObjectDefinition responseHeader = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(394) ) ;        readBuffer.closeContext("responseHeader");


        // Simple Field (noOfQueryDataSets)
int noOfQueryDataSets = readBuffer.readInt("noOfQueryDataSets", 32) ;        // Array field (queryDataSets)
        readBuffer.pullContext("queryDataSets", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfQueryDataSets > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfQueryDataSets) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] queryDataSets;
        {
            int itemCount = Math.max(0, (int) noOfQueryDataSets);
            queryDataSets = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
queryDataSets[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(579) ) ;            }
        }
            readBuffer.closeContext("queryDataSets", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.pullContext("continuationPoint");

        // Simple Field (continuationPoint)
PascalByteString continuationPoint = PascalByteStringIO.staticParse(readBuffer ) ;        readBuffer.closeContext("continuationPoint");


        // Simple Field (noOfParsingResults)
int noOfParsingResults = readBuffer.readInt("noOfParsingResults", 32) ;        // Array field (parsingResults)
        readBuffer.pullContext("parsingResults", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfParsingResults > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfParsingResults) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        ExtensionObjectDefinition[] parsingResults;
        {
            int itemCount = Math.max(0, (int) noOfParsingResults);
            parsingResults = new ExtensionObjectDefinition[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
parsingResults[curItem] = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(612) ) ;            }
        }
            readBuffer.closeContext("parsingResults", WithReaderWriterArgs.WithRenderAsList(true));


        // Simple Field (noOfDiagnosticInfos)
int noOfDiagnosticInfos = readBuffer.readInt("noOfDiagnosticInfos", 32) ;        // Array field (diagnosticInfos)
        readBuffer.pullContext("diagnosticInfos", WithReaderWriterArgs.WithRenderAsList(true));
        // Count array
        if(noOfDiagnosticInfos > Integer.MAX_VALUE) {
            throw new ParseException("Array count of " + (noOfDiagnosticInfos) + " exceeds the maximum allowed count of " + Integer.MAX_VALUE);
        }
        DiagnosticInfo[] diagnosticInfos;
        {
            int itemCount = Math.max(0, (int) noOfDiagnosticInfos);
            diagnosticInfos = new DiagnosticInfo[itemCount];
            for(int curItem = 0; curItem < itemCount; curItem++) {
                boolean lastItem = curItem == (itemCount - 1);
diagnosticInfos[curItem] = DiagnosticInfoIO.staticParse(readBuffer ) ;            }
        }
            readBuffer.closeContext("diagnosticInfos", WithReaderWriterArgs.WithRenderAsList(true));

        readBuffer.pullContext("filterResult");

        // Simple Field (filterResult)
ExtensionObjectDefinition filterResult = ExtensionObjectDefinitionIO.staticParse(readBuffer , String.valueOf(609) ) ;        readBuffer.closeContext("filterResult");

        readBuffer.closeContext("QueryFirstResponse");
        // Create the instance
        return new QueryFirstResponseBuilder(responseHeader, noOfQueryDataSets, queryDataSets, continuationPoint, noOfParsingResults, parsingResults, noOfDiagnosticInfos, diagnosticInfos, filterResult);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, QueryFirstResponse _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("QueryFirstResponse");

        // Simple Field (responseHeader)
        ExtensionObjectDefinition responseHeader = (ExtensionObjectDefinition) _value.getResponseHeader();
        writeBuffer.pushContext("responseHeader");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, responseHeader);
        writeBuffer.popContext("responseHeader");

        // Simple Field (noOfQueryDataSets)
        int noOfQueryDataSets = (int) _value.getNoOfQueryDataSets();
        writeBuffer.writeInt("noOfQueryDataSets", 32, ((Number) (noOfQueryDataSets)).intValue());

        // Array Field (queryDataSets)
        if(_value.getQueryDataSets() != null) {
            writeBuffer.pushContext("queryDataSets", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getQueryDataSets().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getQueryDataSets()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("queryDataSets", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (continuationPoint)
        PascalByteString continuationPoint = (PascalByteString) _value.getContinuationPoint();
        writeBuffer.pushContext("continuationPoint");
        PascalByteStringIO.staticSerialize(writeBuffer, continuationPoint);
        writeBuffer.popContext("continuationPoint");

        // Simple Field (noOfParsingResults)
        int noOfParsingResults = (int) _value.getNoOfParsingResults();
        writeBuffer.writeInt("noOfParsingResults", 32, ((Number) (noOfParsingResults)).intValue());

        // Array Field (parsingResults)
        if(_value.getParsingResults() != null) {
            writeBuffer.pushContext("parsingResults", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getParsingResults().length;
            int curItem = 0;
            for(ExtensionObjectDefinition element : _value.getParsingResults()) {
                boolean lastItem = curItem == (itemCount - 1);
                ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("parsingResults", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (noOfDiagnosticInfos)
        int noOfDiagnosticInfos = (int) _value.getNoOfDiagnosticInfos();
        writeBuffer.writeInt("noOfDiagnosticInfos", 32, ((Number) (noOfDiagnosticInfos)).intValue());

        // Array Field (diagnosticInfos)
        if(_value.getDiagnosticInfos() != null) {
            writeBuffer.pushContext("diagnosticInfos", WithReaderWriterArgs.WithRenderAsList(true));
            int itemCount = (int) _value.getDiagnosticInfos().length;
            int curItem = 0;
            for(DiagnosticInfo element : _value.getDiagnosticInfos()) {
                boolean lastItem = curItem == (itemCount - 1);
                DiagnosticInfoIO.staticSerialize(writeBuffer, element);
                curItem++;
            }
            writeBuffer.popContext("diagnosticInfos", WithReaderWriterArgs.WithRenderAsList(true));
        }

        // Simple Field (filterResult)
        ExtensionObjectDefinition filterResult = (ExtensionObjectDefinition) _value.getFilterResult();
        writeBuffer.pushContext("filterResult");
        ExtensionObjectDefinitionIO.staticSerialize(writeBuffer, filterResult);
        writeBuffer.popContext("filterResult");
        writeBuffer.popContext("QueryFirstResponse");
    }

    public static class QueryFirstResponseBuilder implements ExtensionObjectDefinitionIO.ExtensionObjectDefinitionBuilder {
        private final ExtensionObjectDefinition responseHeader;
        private final int noOfQueryDataSets;
        private final ExtensionObjectDefinition[] queryDataSets;
        private final PascalByteString continuationPoint;
        private final int noOfParsingResults;
        private final ExtensionObjectDefinition[] parsingResults;
        private final int noOfDiagnosticInfos;
        private final DiagnosticInfo[] diagnosticInfos;
        private final ExtensionObjectDefinition filterResult;

        public QueryFirstResponseBuilder(ExtensionObjectDefinition responseHeader, int noOfQueryDataSets, ExtensionObjectDefinition[] queryDataSets, PascalByteString continuationPoint, int noOfParsingResults, ExtensionObjectDefinition[] parsingResults, int noOfDiagnosticInfos, DiagnosticInfo[] diagnosticInfos, ExtensionObjectDefinition filterResult) {
            this.responseHeader = responseHeader;
            this.noOfQueryDataSets = noOfQueryDataSets;
            this.queryDataSets = queryDataSets;
            this.continuationPoint = continuationPoint;
            this.noOfParsingResults = noOfParsingResults;
            this.parsingResults = parsingResults;
            this.noOfDiagnosticInfos = noOfDiagnosticInfos;
            this.diagnosticInfos = diagnosticInfos;
            this.filterResult = filterResult;
        }

        public QueryFirstResponse build() {
            return new QueryFirstResponse(responseHeader, noOfQueryDataSets, queryDataSets, continuationPoint, noOfParsingResults, parsingResults, noOfDiagnosticInfos, diagnosticInfos, filterResult);
        }
    }

}