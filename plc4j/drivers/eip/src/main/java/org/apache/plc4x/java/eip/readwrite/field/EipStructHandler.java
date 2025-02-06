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
package org.apache.plc4x.java.eip.readwrite.field;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.plc4x.java.api.value.PlcValue;
import org.apache.plc4x.java.spi.values.PlcBOOL;
import org.apache.plc4x.java.spi.values.PlcBYTE;
import org.apache.plc4x.java.spi.values.PlcDINT;
import org.apache.plc4x.java.spi.values.PlcDWORD;
import org.apache.plc4x.java.spi.values.PlcINT;
import org.apache.plc4x.java.spi.values.PlcLINT;
import org.apache.plc4x.java.spi.values.PlcLREAL;
import org.apache.plc4x.java.spi.values.PlcList;
import org.apache.plc4x.java.spi.values.PlcREAL;
import org.apache.plc4x.java.spi.values.PlcSINT;
import org.apache.plc4x.java.spi.values.PlcSTRING;
import org.apache.plc4x.java.spi.values.PlcStruct;
import org.apache.plc4x.java.spi.values.PlcUDINT;
import org.apache.plc4x.java.spi.values.PlcUINT;
import org.apache.plc4x.java.spi.values.PlcULINT;
import org.apache.plc4x.java.spi.values.PlcUSINT;
import org.apache.plc4x.java.spi.values.PlcWORD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.plc4x.java.eip.readwrite.protocol.EipClass3ProtocolLogic;
import org.apache.plc4x.java.eip.readwrite.types.CIPDataTypeCode;

import io.netty.buffer.ByteBuf;

public class EipStructHandler {
    // private Map<String, Map<String, Object>> structs = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(EipStructHandler.class);

    private int index = 0;

    public EipStructHandler initIndex(int index) {
        this.index = index;
        return this;
    }

    public int getIndex() {
        return this.index;
    }

    public PlcStruct parse(Map<String, Object> struct, ByteBuf data) {

        Map<String, PlcValue> result = new LinkedHashMap();
        for (String key : struct.keySet()) {
            Object value = struct.get(key);

            if (value instanceof Map) {
                Map<String, Object> vv = (Map<String, Object>) value;
                result.put(key, parse(vv, data));
            } else {
                EipField field = getFields(value.toString());
                int nb = field.getElementNb();
                CIPDataTypeCode type = field.getType();
                if (nb > 1) {

                    List<PlcValue> list = new ArrayList<>();
                    for (int i = 0; i < nb; i++) {
                        switch (type) {
                            case BYTE:
                                list.add(new PlcBYTE(data.getUnsignedByte(index)));
                                index += type.getSize();
                                break;
                            case UDINT:
                                list.add(new PlcUDINT(data.getUnsignedIntLE(index)));
                                index += type.getSize();
                                break;
                            case DINT:
                                list.add(new PlcDINT(data.getIntLE(index)));
                                index += type.getSize();
                                break;
                            case UINT:
                                list.add(new PlcUINT(data.getUnsignedShortLE(index)));
                                index += type.getSize();
                                break;
                            case INT:
                                list.add(new PlcINT(data.getShortLE(index)));
                                index += type.getSize();
                                break;
                            case WORD:
                                list.add(new PlcWORD(data.getUnsignedShortLE(index)));
                                index += type.getSize();
                                break;
                            case ULINT:
                                list.add(new PlcULINT(data.getLongLE(index)));
                                index += type.getSize();
                                break;
                            case LINT:
                                list.add(new PlcLINT(data.getLongLE(index)));
                                index += type.getSize();
                                break;
                            case USINT:
                                list.add(new PlcUSINT(data.getUnsignedByte(index)));
                                index += type.getSize();
                                break;
                            case SINT:
                                list.add(new PlcSINT(data.getByte(index)));
                                index += type.getSize();
                                break;
                            case REAL:
                                list.add(new PlcREAL(data.getFloatLE(index)));
                                index += type.getSize();
                                break;
                            case LREAL:
                                list.add(new PlcLREAL(data.getDoubleLE(index)));
                                index += type.getSize();
                                break;
                            case BOOL:
                                list.add(new PlcBOOL(data.getBoolean(index)));
                                index += type.getSize()+1;
                                break;
                            case DWORD:
                                list.add(new PlcDWORD(data.getIntLE(index)));
                                index += type.getSize();
                                break;
                            case LWORD:
                                list.add(new PlcDWORD(data.getLongLE(index)));
                                index += type.getSize();
                                break;
                            case STRING:
                                short aShort = data.getShort(index);
                                list.add(new PlcSTRING(
                                        StandardCharsets.UTF_8.decode(data.nioBuffer(2, aShort)).toString()));
                                index += aShort + 2;
                                break;
                            default:
                                return null;
                        }
                    }
                    result.put(key, new PlcList(list));
                } else {
                    switch (type) {
                        case BYTE:
                            result.put(key, new PlcBYTE(data.getUnsignedByte(index)));
                            index += type.getSize();
                            break;
                        case UDINT:
                            result.put(key, new PlcUDINT(data.getUnsignedIntLE(index)));
                            index += type.getSize();
                            break;
                        case DINT:
                            result.put(key, new PlcDINT(data.getIntLE(index)));
                            index += type.getSize();
                            break;
                        case UINT:
                            result.put(key, new PlcUINT(data.getUnsignedShortLE(index)));
                            index += type.getSize();
                            break;
                        case INT:
                            result.put(key, new PlcINT(data.getShortLE(index)));
                            index += type.getSize();
                            break;
                        case WORD:
                            result.put(key, new PlcWORD(data.getUnsignedShortLE(index)));
                            index += type.getSize();
                            break;
                        case ULINT:
                            result.put(key, new PlcULINT(data.getLongLE(index)));
                            index += type.getSize();
                            break;
                        case LINT:
                            result.put(key, new PlcLINT(data.getLongLE(index)));
                            index += type.getSize();
                            break;
                        case USINT:
                            result.put(key, new PlcUSINT(data.getUnsignedByte(index)));
                            index += type.getSize();
                            break;
                        case SINT:
                            result.put(key, new PlcSINT(data.getByte(index)));
                            index += type.getSize();
                            break;
                        case REAL:
                            result.put(key, new PlcREAL(data.getFloatLE(index)));
                            index += type.getSize();
                            break;
                        case LREAL:
                            result.put(key, new PlcLREAL(data.getDoubleLE(index)));
                            index += type.getSize();
                            break;
                        case BOOL:
                            result.put(key, new PlcBOOL(data.getBoolean(index)));
                            index += type.getSize()+1;
                            break;
                        case DWORD:
                            result.put(key, new PlcDWORD(data.getIntLE(index)));
                            index += type.getSize();
                            break;
                        case LWORD:
                            result.put(key, new PlcDWORD(data.getLongLE(index)));
                            index += type.getSize();
                            break;
                        case STRING:
                            short aShort = data.getShort(index);
                            result.put(key,
                                    new PlcSTRING(StandardCharsets.UTF_8.decode(data.nioBuffer(2, aShort)).toString()));
                            index += aShort + 2;
                            break;

                    }
                }

            }

        }
        return new PlcStruct(result);
    }

    private EipField getFields(String value) {

        return EipField.of(value);

    }

}
