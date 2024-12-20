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

public enum TransportSize {

    BOOL((byte) 0x01, (boolean) true, (boolean) true, (short) 0x01, (short) 1, (boolean) true, (boolean) true, (short) 'X', (boolean) true, DataTransportSize.BIT, null, (String) "IEC61131_BOOL"),
    BYTE((byte) 0x02, (boolean) true, (boolean) true, (short) 0x02, (short) 1, (boolean) true, (boolean) true, (short) 'B', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, null, (String) "IEC61131_BYTE"),
    WORD((byte) 0x03, (boolean) true, (boolean) true, (short) 0x04, (short) 2, (boolean) true, (boolean) true, (short) 'W', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, null, (String) "IEC61131_WORD"),
    DWORD((byte) 0x04, (boolean) true, (boolean) true, (short) 0x06, (short) 4, (boolean) true, (boolean) true, (short) 'D', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, TransportSize.WORD, (String) "IEC61131_DWORD"),
    LWORD((byte) 0x05, (boolean) false, (boolean) false, (short) 0x00, (short) 8, (boolean) false, (boolean) false, (short) 'X', (boolean) true, null, null, (String) "IEC61131_LWORD"),
    INT((byte) 0x06, (boolean) true, (boolean) true, (short) 0x05, (short) 2, (boolean) true, (boolean) true, (short) 'W', (boolean) true, DataTransportSize.INTEGER, null, (String) "IEC61131_INT"),
    UINT((byte) 0x07, (boolean) false, (boolean) true, (short) 0x05, (short) 2, (boolean) false, (boolean) true, (short) 'W', (boolean) true, DataTransportSize.INTEGER, TransportSize.INT, (String) "IEC61131_UINT"),
    SINT((byte) 0x08, (boolean) false, (boolean) true, (short) 0x02, (short) 1, (boolean) false, (boolean) true, (short) 'B', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, TransportSize.INT, (String) "IEC61131_SINT"),
    USINT((byte) 0x09, (boolean) false, (boolean) true, (short) 0x02, (short) 1, (boolean) false, (boolean) true, (short) 'B', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, TransportSize.INT, (String) "IEC61131_USINT"),
    DINT((byte) 0x0A, (boolean) true, (boolean) true, (short) 0x07, (short) 4, (boolean) true, (boolean) true, (short) 'D', (boolean) true, DataTransportSize.INTEGER, TransportSize.INT, (String) "IEC61131_DINT"),
    UDINT((byte) 0x0B, (boolean) false, (boolean) true, (short) 0x07, (short) 4, (boolean) false, (boolean) true, (short) 'D', (boolean) true, DataTransportSize.INTEGER, TransportSize.INT, (String) "IEC61131_UDINT"),
    LINT((byte) 0x0C, (boolean) false, (boolean) false, (short) 0x00, (short) 8, (boolean) false, (boolean) false, (short) 'X', (boolean) true, null, TransportSize.INT, (String) "IEC61131_LINT"),
    ULINT((byte) 0x0D, (boolean) false, (boolean) false, (short) 0x00, (short) 16, (boolean) false, (boolean) false, (short) 'X', (boolean) true, null, TransportSize.INT, (String) "IEC61131_ULINT"),
    REAL((byte) 0x0E, (boolean) true, (boolean) true, (short) 0x08, (short) 4, (boolean) true, (boolean) true, (short) 'D', (boolean) true, DataTransportSize.REAL, null, (String) "IEC61131_REAL"),
    LREAL((byte) 0x0F, (boolean) false, (boolean) false, (short) 0x30, (short) 8, (boolean) false, (boolean) true, (short) 'X', (boolean) true, null, TransportSize.REAL, (String) "IEC61131_LREAL"),
    CHAR((byte) 0x10, (boolean) true, (boolean) true, (short) 0x03, (short) 1, (boolean) true, (boolean) true, (short) 'B', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, null, (String) "IEC61131_CHAR"),
    WCHAR((byte) 0x11, (boolean) false, (boolean) true, (short) 0x13, (short) 2, (boolean) false, (boolean) true, (short) 'X', (boolean) true, null, null, (String) "IEC61131_WCHAR"),
    STRING((byte) 0x12, (boolean) true, (boolean) true, (short) 0x03, (short) 1, (boolean) true, (boolean) true, (short) 'X', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, null, (String) "IEC61131_STRING"),
    WSTRING((byte) 0x13, (boolean) false, (boolean) true, (short) 0x00, (short) 2, (boolean) false, (boolean) true, (short) 'X', (boolean) true, null, null, (String) "IEC61131_WSTRING"),
    TIME((byte) 0x14, (boolean) true, (boolean) true, (short) 0x0B, (short) 4, (boolean) true, (boolean) true, (short) 'X', (boolean) true, null, null, (String) "IEC61131_TIME"),
    LTIME((byte) 0x16, (boolean) false, (boolean) false, (short) 0x00, (short) 8, (boolean) false, (boolean) false, (short) 'X', (boolean) true, null, TransportSize.TIME, (String) "IEC61131_LTIME"),
    DATE((byte) 0x17, (boolean) true, (boolean) true, (short) 0x09, (short) 2, (boolean) true, (boolean) true, (short) 'X', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, null, (String) "IEC61131_DATE"),
    TIME_OF_DAY((byte) 0x18, (boolean) true, (boolean) true, (short) 0x06, (short) 4, (boolean) true, (boolean) true, (short) 'X', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, null, (String) "IEC61131_TIME_OF_DAY"),
    TOD((byte) 0x19, (boolean) true, (boolean) true, (short) 0x06, (short) 4, (boolean) true, (boolean) true, (short) 'X', (boolean) true, DataTransportSize.BYTE_WORD_DWORD, null, (String) "IEC61131_TIME_OF_DAY"),
    DATE_AND_TIME((byte) 0x1A, (boolean) true, (boolean) false, (short) 0x0F, (short) 12, (boolean) true, (boolean) false, (short) 'X', (boolean) true, null, null, (String) "IEC61131_DATE_AND_TIME"),
    DT((byte) 0x1B, (boolean) true, (boolean) false, (short) 0x0F, (short) 12, (boolean) true, (boolean) false, (short) 'X', (boolean) true, null, null, (String) "IEC61131_DATE_AND_TIME");

    private static final Logger logger = LoggerFactory.getLogger(TransportSize.class);

    private static final Map<Byte, TransportSize> map;
    static {
        map = new HashMap<>();
        for (TransportSize value : TransportSize.values()) {
            map.put((byte) value.getValue(), value);
        }
    }

    private byte value;
        private boolean supported_S7_300;
        private boolean supported_LOGO;
        private short code;
        private short sizeInBytes;
        private boolean supported_S7_400;
        private boolean supported_S7_1200;
        private short shortName;
        private boolean supported_S7_1500;
        private DataTransportSize dataTransportSize;
        private TransportSize baseType;
        private String dataProtocolId;

    TransportSize(byte value, boolean supported_S7_300, boolean supported_LOGO, short code, short sizeInBytes, boolean supported_S7_400, boolean supported_S7_1200, short shortName, boolean supported_S7_1500, DataTransportSize dataTransportSize, TransportSize baseType, String dataProtocolId) {
        this.value = value;
        this.supported_S7_300 = supported_S7_300;
        this.supported_LOGO = supported_LOGO;
        this.code = code;
        this.sizeInBytes = sizeInBytes;
        this.supported_S7_400 = supported_S7_400;
        this.supported_S7_1200 = supported_S7_1200;
        this.shortName = shortName;
        this.supported_S7_1500 = supported_S7_1500;
        this.dataTransportSize = dataTransportSize;
        this.baseType = baseType;
        this.dataProtocolId = dataProtocolId;
    }

    public byte getValue() {
        return value;
    }

    public boolean getSupported_S7_300() {
        return supported_S7_300;
    }

    public static TransportSize firstEnumForFieldSupported_S7_300(boolean fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_300() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldSupported_S7_300(boolean fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_300() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public boolean getSupported_LOGO() {
        return supported_LOGO;
    }

    public static TransportSize firstEnumForFieldSupported_LOGO(boolean fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_LOGO() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldSupported_LOGO(boolean fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_LOGO() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public short getCode() {
        return code;
    }

    public static TransportSize firstEnumForFieldCode(short fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getCode() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldCode(short fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getCode() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public short getSizeInBytes() {
        return sizeInBytes;
    }

    public static TransportSize firstEnumForFieldSizeInBytes(short fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSizeInBytes() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldSizeInBytes(short fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSizeInBytes() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public boolean getSupported_S7_400() {
        return supported_S7_400;
    }

    public static TransportSize firstEnumForFieldSupported_S7_400(boolean fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_400() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldSupported_S7_400(boolean fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_400() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public boolean getSupported_S7_1200() {
        return supported_S7_1200;
    }

    public static TransportSize firstEnumForFieldSupported_S7_1200(boolean fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_1200() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldSupported_S7_1200(boolean fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_1200() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public short getShortName() {
        return shortName;
    }

    public static TransportSize firstEnumForFieldShortName(short fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getShortName() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldShortName(short fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getShortName() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public boolean getSupported_S7_1500() {
        return supported_S7_1500;
    }

    public static TransportSize firstEnumForFieldSupported_S7_1500(boolean fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_1500() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldSupported_S7_1500(boolean fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getSupported_S7_1500() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public DataTransportSize getDataTransportSize() {
        return dataTransportSize;
    }

    public static TransportSize firstEnumForFieldDataTransportSize(DataTransportSize fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getDataTransportSize() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldDataTransportSize(DataTransportSize fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getDataTransportSize() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public TransportSize getBaseType() {
        return baseType;
    }

    public static TransportSize firstEnumForFieldBaseType(TransportSize fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getBaseType() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldBaseType(TransportSize fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getBaseType() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public String getDataProtocolId() {
        return dataProtocolId;
    }

    public static TransportSize firstEnumForFieldDataProtocolId(String fieldValue) {
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getDataProtocolId() == fieldValue) {
                return _val;
            }
        }
        return null;
    }

    public static List<TransportSize> enumsForFieldDataProtocolId(String fieldValue) {
        List<TransportSize> _values = new ArrayList();
        for (TransportSize _val : TransportSize.values()) {
            if(_val.getDataProtocolId() == fieldValue) {
                _values.add(_val);
            }
        }
        return _values;
    }

    public static TransportSize enumForValue(byte value) {
        if (!map.containsKey(value)) {
            logger.error("No TransportSize for value {}", value);
        }
        return map.get(value);
    }

    public static Boolean isDefined(byte value) {
        return map.containsKey(value);
    }

}
