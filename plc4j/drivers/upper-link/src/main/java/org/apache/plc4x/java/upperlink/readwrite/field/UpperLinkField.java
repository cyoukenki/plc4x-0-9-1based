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
package org.apache.plc4x.java.upperlink.readwrite.field;

import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.WriteBuffer;
import org.apache.plc4x.java.spi.utils.Serializable;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpperLinkField implements PlcField, Serializable {
    @Override
    public void serialize(WriteBuffer writeBuffer) throws ParseException {

    }

//    private static final Pattern ADDRESS_PATTERN =
//        Pattern.compile("^%(?<rqType>[a-zA-Z]+\\[?[0-9]*\\]?):?(?<dataType>[A-Z]*):?(?<elementNb>[0-9]*)");
//
//    private static final String RQTYPE = "rqType";
//    private static final String ELEMENTS = "elementNb";
//    private static final String TYPE = "dataType";
//
//
//    private final String rqType;
//    private CIPDataTypeCode type;
//    private int elementNb;
//
//    public CIPDataTypeCode getType() {
//        return type;
//    }
//
//    public void setType(CIPDataTypeCode type) {
//        this.type = type;
//    }
//
//    public int getElementNb() {
//        return elementNb;
//    }
//
//    public void setElementNb(int elementNb) {
//        this.elementNb = elementNb;
//    }
//
//    public String getTag() {
//        return rqType;
//    }
//
//    public UpperLinkField(String rqType) {
//        this.rqType = rqType;
//    }
//
//    public UpperLinkField(String rqType, int elementNb) {
//        this.rqType = rqType;
//        this.elementNb = elementNb;
//    }
//
//    public UpperLinkField(String rqType, CIPDataTypeCode type, int elementNb) {
//        this.rqType = rqType;
//        this.type = type;
//        this.elementNb = elementNb;
//    }
//
//    public UpperLinkField(String rqType, CIPDataTypeCode type) {
//        this.rqType = rqType;
//        this.type = type;
//    }
//
//    public static boolean matches(String fieldQuery) {
//        return ADDRESS_PATTERN.matcher(fieldQuery).matches();
//    }
//
//    public static UpperLinkField of(String fieldString) {
//        Matcher matcher = ADDRESS_PATTERN.matcher(fieldString);
//        if (matcher.matches()) {
//            String rqType = matcher.group(RQTYPE);
//            int nb = 0;
//            CIPDataTypeCode type = null;
//            if (!matcher.group(ELEMENTS).isEmpty()) {
//                nb = Integer.parseInt(matcher.group(ELEMENTS));
//            }
//            if (!matcher.group(TYPE).isEmpty()) {
//                type = CIPDataTypeCode.valueOf(matcher.group(TYPE));
//            }
//            if (nb != 0) {
//                if (type != null) {
//                    return new UpperLinkField(rqType, type, nb);
//                }
//                return new UpperLinkField(rqType, nb);
//            } else {
//                if (type != null) {
//                    return new UpperLinkField(rqType, type);
//                }
//                return new UpperLinkField(rqType);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public String getPlcDataType() {
//        return type.toString();
//    }
//
//    @Override
//    public Class<?> getDefaultJavaType() {
//        switch (type) {
//            //ToDo differenciate Short, Integer and Long
//            case INT:
//            case DINT:
//            case SINT:
//            case DWORD:
//            case LINT:
//            case WORD:
//                return Integer.class;
//            case STRING:
//            case STRING36:
//                return String.class;
//            case REAL:
//                return Double.class;
//            case BOOL:
//                return Boolean.class;
//            default:
//                return Object.class;
//        }
//    }
//
//    @Override
//    public void serialize(WriteBuffer writeBuffer) throws ParseException {
//        writeBuffer.pushContext(getClass().getSimpleName());
//
//        writeBuffer.writeString("node", rqType.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), rqType);
//        if (type != null) {
//            writeBuffer.writeString("type", type.name().getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), type.name());
//        }
//        writeBuffer.writeUnsignedInt("elementNb", 16, elementNb);
//        // TODO: remove this (not language agnostic)
//        String defaultJavaType = (type == null ? Object.class : getDefaultJavaType()).getName();
//        writeBuffer.writeString("defaultJavaType", defaultJavaType.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), defaultJavaType);
//
//        writeBuffer.popContext(getClass().getSimpleName());
//    }

}
