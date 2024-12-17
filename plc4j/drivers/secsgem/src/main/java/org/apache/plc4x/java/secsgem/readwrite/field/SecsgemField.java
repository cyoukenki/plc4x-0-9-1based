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
package org.apache.plc4x.java.secsgem.readwrite.field;

import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.WriteBuffer;
import org.apache.plc4x.java.spi.utils.Serializable;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecsgemField implements PlcField, Serializable {

    private static final Pattern ADDRESS_PATTERN =
        Pattern.compile("^(?<reqType>[a-zA-Z_.0-9]*):(?<fieldName>[a-zA-Z_.0-9]*):?(?<fieldValue>[a-zA-Z_.0-9]*)");

    private static final String REQ_TYPE = "reqType";
    private static final String FIELD_NAME = "fieldName";
    private static final String FIELD_VALUE = "fieldValue";


    private String reqType;
    private String fieldName;
    private String fieldValue;

    public SecsgemField(String reqType, String fieldName, String fieldValue) {
        this.reqType = reqType;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public static boolean matches(String fieldQuery) {
        return ADDRESS_PATTERN.matcher(fieldQuery).matches();
    }

    public static SecsgemField of(String fieldString) {
        Matcher matcher = ADDRESS_PATTERN.matcher(fieldString);
        if (matcher.matches()) {
            String reqType = matcher.group(REQ_TYPE);
            String fieldName = matcher.group(FIELD_NAME);
            String fieldValue = matcher.group(FIELD_VALUE);
           return  new SecsgemField(reqType,fieldName,fieldValue);
        }
        return null;
    }

    @Override
    public void serialize(WriteBuffer writeBuffer) throws ParseException {
        writeBuffer.pushContext(getClass().getSimpleName());
        writeBuffer.writeString("reqType", reqType.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), reqType);
        writeBuffer.writeString("fieldName", fieldName.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), fieldName);
        writeBuffer.writeString("fieldValue", fieldValue.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), fieldValue);
        writeBuffer.popContext(getClass().getSimpleName());
    }

}
