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
package org.apache.plc4x.java.secsgem.readwrite.types;

import org.apache.plc4x.java.spi.generation.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Code generated by code-generation. DO NOT EDIT.

public enum SecsDataTypeCode2 {

    BYTE((short) 8, (short) 1),
    BOOL((short) 9, (short) 1),
    STRING((short) 16, (short) 256),
    SINT((short) 25, (short) 1),
    INT((short) 26, (short) 2),
    DINT((short) 28, (short) 4),
    REAL((short) 36, (short) 4),
    LREAL((short) 32, (short) 8),
    USINT((short) 41, (short) 1),
    UINT((short) 42, (short) 2),
    UDINT((short) 44, (short) 4),
    LINT((short) 24, (short) 8),
    ULINT((short) 40, (short) 8);

    private static final Logger logger = LoggerFactory.getLogger(SecsDataTypeCode2.class);

    private static final Map<Short, SecsDataTypeCode2> map;
    static {
        map = new HashMap<>();
        for (SecsDataTypeCode2 value : SecsDataTypeCode2.values()) {
            map.put((short) value.getValue(), value);
        }
    }

    private short value;
        private short size;

    SecsDataTypeCode2(short value, short size) {
        this.value = value;
        this.size = size;
    }

    public short getValue() {
        return value;
    }

    public short getSize() {
        return size;
    }

    public static SecsDataTypeCode2 firstEnumForFieldSize(short fieldValue) {
        for (SecsDataTypeCode2 _val : SecsDataTypeCode2.values()) {
            if(_val.getSize() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<SecsDataTypeCode2> enumsForFieldSize(short fieldValue) {
        List<SecsDataTypeCode2> _values = new ArrayList();
        for (SecsDataTypeCode2 _val : SecsDataTypeCode2.values()) {
            if(_val.getSize() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public static SecsDataTypeCode2 enumForValue(short value) {
        if (!map.containsKey(value)) {
            logger.error("No SecsDataTypeCode2 for value {}", value);
        }
        return map.get(value);
    }

    public static Boolean isDefined(short value) {
        return map.containsKey(value);
    }

}