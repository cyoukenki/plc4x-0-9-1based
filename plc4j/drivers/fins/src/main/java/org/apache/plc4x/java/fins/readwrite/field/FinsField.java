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
package org.apache.plc4x.java.fins.readwrite.field;

import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.fins.readwrite.types.FinsDataType;
import org.apache.plc4x.java.fins.readwrite.types.FinsDataTypeCode;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.WriteBuffer;
import org.apache.plc4x.java.spi.utils.Serializable;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinsField implements PlcField, Serializable {
    //40001:WORD[2]
    //D:100:WORD[2]
    private static final Pattern ADDRESS_PATTERN =
//        Pattern.compile("^(?<areaType>[a-zA-Z_.0-9]+\\[?[0-9]*\\]?):?(?<wordStart>[0-9]*):?(?<lenght>[0-9]*)");
        Pattern.compile("^(?<areaType>[a-zA-Z_.0-9]+\\[?[0-9]*\\]?):(?<wordStart>[0-9]*(.[0-9]{1,})?):(?<datatype>[a-zA-Z_]+)?(\\[(?<quantity>\\d+)])?");
    private static final String AREA_TYPE = "areaType";
    private static final String WORD_STATR = "wordStart";
    //    private static final String LENGHT ="lenght";
    private static final String DATATYPE = "datatype";
    private static final String QUANTITY = "quantity";

    private FinsDataTypeCode areaType;
    private FinsDataType dataType;
    private String wordStart;
    //    private int lenght;
    private int quantity;

    public FinsDataTypeCode getAreaType() {
        return areaType;
    }

    public void setAreaType(FinsDataTypeCode areaType) {
        this.areaType = areaType;
    }

    public String getWordStart() {
        return wordStart;
    }

    public void setWordStart(String wordStart) {
        this.wordStart = wordStart;
    }

    public FinsDataType getDataType() {
        return dataType;
    }

    public void setDataType(FinsDataType dataType) {
        this.dataType = dataType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public FinsField(FinsDataTypeCode areaType) {
        this.areaType = areaType;
    }

    public FinsField(FinsDataTypeCode areaType, String wordStart) {
        this.areaType = areaType;
        this.wordStart = wordStart;
    }

//    public FinsField(FinsDataTypeCode areaType, int wordStart, int lenght) {
//        this.areaType = areaType;
//        this.wordStart = wordStart;
//        this.lenght = lenght;
//    }

    public FinsField(FinsDataTypeCode areaType, FinsDataType dataType, String wordStart, int quantity) {
        this.areaType = areaType;
        this.dataType = dataType;
        this.wordStart = wordStart;
        this.quantity = quantity;
    }

    public static boolean matches(String fieldQuery) {
        return ADDRESS_PATTERN.matcher(fieldQuery).matches();
    }

    public static FinsField of(String fieldString) {
        Matcher matcher = ADDRESS_PATTERN.matcher(fieldString);
        if (matcher.matches()) {
            FinsDataTypeCode areaType = null;
            FinsDataType dataType = null;
            if (!matcher.group(AREA_TYPE).isEmpty()) {
                areaType = FinsDataTypeCode.valueOf(matcher.group(AREA_TYPE));
            }
            if (!matcher.group(DATATYPE).isEmpty()) {
                dataType = FinsDataType.valueOf(matcher.group(DATATYPE));
            }
            String wordStart = "";
            if (!matcher.group(WORD_STATR).isEmpty()) {
                wordStart = String.valueOf(matcher.group(WORD_STATR));
            }
            int quantity = 1;
            if (matcher.group(QUANTITY) != null && !matcher.group(QUANTITY).isEmpty()) {
                quantity = Integer.parseInt(matcher.group(QUANTITY));
            }
            return new FinsField(areaType, dataType, wordStart, quantity);
        }
        return null;
    }


    @Override
    public void serialize(WriteBuffer writeBuffer) throws ParseException {
        writeBuffer.pushContext(getClass().getSimpleName());
        if (areaType != null) {
            writeBuffer.writeString("areaType", areaType.name().getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), areaType.name());
        }
        if (dataType != null) {
            writeBuffer.writeString("dataType", dataType.name().getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), dataType.name());
        }
        writeBuffer.writeString("wordStart", wordStart.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), wordStart);
        writeBuffer.writeUnsignedInt("quantity", 16, quantity);
        // TODO: remove this (not language agnostic)
        writeBuffer.popContext(getClass().getSimpleName());
    }
}
