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
package org.apache.plc4x.java.s7.readwrite.types;

import org.apache.plc4x.java.spi.generation.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Code generated by code-generation. DO NOT EDIT.

public enum COTPTpduSize {

    SIZE_128((byte) 0x07, (int) 128),
    SIZE_256((byte) 0x08, (int) 256),
    SIZE_512((byte) 0x09, (int) 512),
    SIZE_1024((byte) 0x0a, (int) 1024),
    SIZE_2048((byte) 0x0b, (int) 2048),
    SIZE_4096((byte) 0x0c, (int) 4096),
    SIZE_8192((byte) 0x0d, (int) 8192);

    private static final Logger logger = LoggerFactory.getLogger(COTPTpduSize.class);

    private static final Map<Byte, COTPTpduSize> map;
    static {
        map = new HashMap<>();
        for (COTPTpduSize value : COTPTpduSize.values()) {
            map.put((byte) value.getValue(), value);
        }
    }

    private byte value;
        private int sizeInBytes;

    COTPTpduSize(byte value, int sizeInBytes) {
        this.value = value;
        this.sizeInBytes = sizeInBytes;
    }

    public byte getValue() {
        return value;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public static COTPTpduSize firstEnumForFieldSizeInBytes(int fieldValue) {
        for (COTPTpduSize _val : COTPTpduSize.values()) {
            if(_val.getSizeInBytes() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<COTPTpduSize> enumsForFieldSizeInBytes(int fieldValue) {
        List<COTPTpduSize> _values = new ArrayList();
        for (COTPTpduSize _val : COTPTpduSize.values()) {
            if(_val.getSizeInBytes() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public static COTPTpduSize enumForValue(byte value) {
        if (!map.containsKey(value)) {
            logger.error("No COTPTpduSize for value {}", value);
        }
        return map.get(value);
    }

    public static Boolean isDefined(byte value) {
        return map.containsKey(value);
    }

}