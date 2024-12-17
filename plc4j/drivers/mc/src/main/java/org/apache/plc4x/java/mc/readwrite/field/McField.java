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
package org.apache.plc4x.java.mc.readwrite.field;

import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.mc.readwrite.types.Devicecode;
import org.apache.plc4x.java.mc.readwrite.types.McDataType;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.WriteBuffer;
import org.apache.plc4x.java.spi.utils.Serializable;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class McField implements PlcField, Serializable {
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^(?<deviceCode>[a-zA-Z_.0-9]+\\[?[0-9]*\\]?):(?<wordStart>[0-9]*(.[0-9]{1,})?):(?<datatype>[a-zA-Z_]+)?(\\[(?<quantity>\\d+)])?");
    private static final String DEVICE_CODE = "deviceCode";
    private static final String WORD_STATR = "wordStart";
    //    private static final String LENGHT ="lenght";
    private static final String DATATYPE = "datatype";
    private static final String QUANTITY = "quantity";

    private Devicecode deviceCode;
    private McDataType dataType;
    private String wordStart;
    private int quantity;

    public Devicecode getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Devicecode deviceCode) {
        this.deviceCode = deviceCode;
    }

    public McDataType getDataType() {
        return dataType;
    }

    public void setDataType(McDataType dataType) {
        this.dataType = dataType;
    }

    public String getWordStart() {
        return wordStart;
    }

    public void setWordStart(String wordStart) {
        this.wordStart = wordStart;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public McField(Devicecode deviceCode, McDataType dataType, String wordStart, int quantity) {
        this.deviceCode = deviceCode;
        this.dataType = dataType;
        this.wordStart = wordStart;
        this.quantity = quantity;
    }


    public static boolean matches(String fieldQuery) {
        return ADDRESS_PATTERN.matcher(fieldQuery).matches();
    }

    public static McField of(String fieldString) {
        Matcher matcher = ADDRESS_PATTERN.matcher(fieldString);
        if (matcher.matches()) {
            Devicecode deviceCode = null;
            McDataType dataType = null;
            if (!matcher.group(DEVICE_CODE).isEmpty()) {
                deviceCode = Devicecode.valueOf(matcher.group(DEVICE_CODE));
            }
            if (!matcher.group(DATATYPE).isEmpty()) {
                dataType = McDataType.valueOf(matcher.group(DATATYPE));
            }
            String wordStart = "";
            if (!matcher.group(WORD_STATR).isEmpty()) {
                wordStart = String.valueOf(matcher.group(WORD_STATR));
            }
            int quantity = 1;
            if (matcher.group(QUANTITY) != null && !matcher.group(QUANTITY).isEmpty()) {
                quantity = Integer.parseInt(matcher.group(QUANTITY));
            }
            return new McField(deviceCode, dataType, wordStart, quantity);
        }
        return null;
    }


    @Override
    public void serialize(WriteBuffer writeBuffer) throws ParseException {
        writeBuffer.pushContext(getClass().getSimpleName());
        if (deviceCode != null) {
            writeBuffer.writeString("deviceCode", deviceCode.name().getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), deviceCode.name());
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
