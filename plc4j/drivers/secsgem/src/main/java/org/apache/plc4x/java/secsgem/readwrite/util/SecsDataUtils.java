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
package org.apache.plc4x.java.secsgem.readwrite.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.value.PlcValue;
import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode2;
import org.apache.plc4x.java.spi.values.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode.*;

public class SecsDataUtils {
    public static Object parseDataToObject(SecsDataTypeCode dataType, byte[] dataBytes) {
        ByteBuf byteBuffer = Unpooled.wrappedBuffer(dataBytes);
        Object data = null;
        switch (dataType) {
            case BYTE:
                data = byteBuffer.getUnsignedByte(0);
                break;
            case BOOL:
                data = byteBuffer.getBoolean(0);
                break;
            case USINT:
            case SINT:
                data = Byte.toUnsignedInt(byteBuffer.getByte(0));
                break;
            case UINT:
            case INT:
                data = byteBuffer.getShort(0);
                break;
            case UDINT:
            case DINT:
                data = byteBuffer.getInt(0);
                break;
            case REAL:
                data = byteBuffer.getFloat(0);
                break;
            case LREAL:
                data = byteBuffer.getDouble(0);
                break;
            case ULINT:
            case LINT:
                data = byteBuffer.getLong(0);
                break;
            case STRING:
                data = new String(dataBytes);
                break;
            case LIST:
                data = byteBuffer.getByte(0);
                break;
            default:
                break;
        }
        byteBuffer.clear();
        return data;
    }

    /**
     * 根据请求数据计算数据类型和数据长度位
     *
     * @param vidvalues
     * @return
     */
    public static DataStruct2 calculateDataLength(Object[] vidvalues) {
        DataStruct2 dataStruct2;
        if (vidvalues.length <= 255) {
            dataStruct2 = new DataShort((short) 1, (short) vidvalues.length);
        } else if (vidvalues.length > 255 && vidvalues.length <= 65535) {
            dataStruct2 = new DataMedium((short) 2, vidvalues.length);
        } else {
            dataStruct2 = new DataLong((short) 3, vidvalues.length);
        }
        return dataStruct2;
    }

    /**
     * 根据ppbody数据类型和ppbody设置数量计算byteLength
     *
     * @param dataType
     * @param num
     * @return
     */
    public static int calculateDataByteLength(SecsDataTypeCode dataType, int num) {
        int byteLength = 0;
        switch (dataType) {
            case STRING:
            case BYTE:
            case BOOL:
            case USINT:
            case SINT:
                byteLength = num;
                break;
            case UINT:
            case INT:
                byteLength = num * 2;
                break;
            case UDINT:
            case DINT:
            case REAL:
                byteLength = num * 4;
                break;
            case LREAL:
            case ULINT:
            case LINT:
                byteLength = num * 8;
                break;
            default:
                break;
        }
        return byteLength;
    }

    /**
     * 根据数据类型和数据内容计算数据个数
     *
     * @param dataType
     * @param dataBytes
     * @return
     */
    public static int calculateDataNum(SecsDataTypeCode dataType, byte[] dataBytes) {
        int dataLength = 0;
        if (dataType == SecsDataTypeCode.STRING) {
            dataLength = new String(dataBytes).length();
        } else {
            switch (dataType) {
                case BYTE:
                case BOOL:
                case USINT:
                case SINT:
                    dataLength = dataBytes.length;
                    break;
                case UINT:
                case INT:
                    dataLength = dataBytes.length / 2;
                    break;
                case UDINT:
                case DINT:
                case REAL:
                    dataLength = dataBytes.length / 4;
                    break;
                case LREAL:
                case ULINT:
                case LINT:
                    dataLength = dataBytes.length / 8;
                    break;
                default:
                    break;
            }
        }
        return dataLength;
    }


    public static int calculateDataNum2(SecsDataTypeCode2 dataType, byte[] dataBytes) {
        int dataLength = 0;
        if (dataType == SecsDataTypeCode2.STRING) {
            dataLength = new String(dataBytes).length();
        } else {
            switch (dataType) {
                case BYTE:
                case BOOL:
                case USINT:
                case SINT:
                    dataLength = dataBytes.length;
                    break;
                case UINT:
                case INT:
                    dataLength = dataBytes.length / 2;
                    break;
                case UDINT:
                case DINT:
                case REAL:
                    dataLength = dataBytes.length / 4;
                    break;
                case LREAL:
                case ULINT:
                case LINT:
                    dataLength = dataBytes.length / 8;
                    break;
                default:
                    break;
            }
        }
        return dataLength;
    }

    /**
     * 根据请求数据计算DataStruct3数据类型和数据长度位
     *
     * @param body
     * @param secsDataTypeCode2
     * @return
     */
    public static DataStruct3 calculateArrayDataLength(byte[] body, SecsDataTypeCode2 secsDataTypeCode2) {
        SecsDataTypeCode secsDataTypeCode = SecsDataTypeCode.valueOf(secsDataTypeCode2.name());
        DataStruct3 dataStruct3 = null;
        int dataLength = body.length;
        if (secsDataTypeCode == SecsDataTypeCode.STRING) {
            dataLength = new String(body).length();
            if (dataLength <= 255) {
                dataStruct3 = new ASCTypeShort((short) 65, (short) dataLength);
            } else if (dataLength > 255 && dataLength <= 65535) {
                dataStruct3 = new ASCTypeMedium((short) 66, dataLength);
            } else {
                dataStruct3 = new ASCTypeLong((short) 67, dataLength);
            }
        } else {
            switch (secsDataTypeCode) {
                case BYTE:
//                    dataLength = body.length;
                    if (dataLength <= 255) {
                        dataStruct3 = new BYTETypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new BYTETypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new BYTETypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case BOOL:
//                    dataLength = body.length / 2;
                    if (dataLength <= 255) {
                        dataStruct3 = new BOOLTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new BOOLTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new BOOLTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case USINT:
//                    dataLength = body.length;
                    if (dataLength <= 255) {
                        dataStruct3 = new USINTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new USINTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new USINTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case SINT:
//                    dataLength = body.length;
                    if (dataLength <= 255) {
                        dataStruct3 = new SINTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new SINTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new SINTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case UINT:
//                    dataLength = body.length / 2;
                    if (dataLength <= 255) {
                        dataStruct3 = new UINTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new UINTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new UINTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case INT:
//                    dataLength = body.length / 2;
                    if (dataLength <= 255) {
                        dataStruct3 = new INTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new INTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new INTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case UDINT:
//                    dataLength = body.length / 4;
                    if (dataLength <= 255) {
                        dataStruct3 = new UDINTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new UDINTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new UDINTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case DINT:
//                    dataLength = body.length / 4;
                    if (dataLength <= 255) {
                        dataStruct3 = new DINTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new DINTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new DINTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case REAL:
//                    dataLength = body.length / 4;
                    if (dataLength <= 255) {
                        dataStruct3 = new REALTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new REALTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new REALTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case LREAL:
//                    dataLength = body.length / 8;
                    if (dataLength <= 255) {
                        dataStruct3 = new LREALTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new LREALTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new LREALTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case ULINT:
//                    dataLength = body.length / 8;
                    if (dataLength <= 255) {
                        dataStruct3 = new ULINTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new ULINTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new ULINTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                case LINT:
//                    dataLength = body.length / 8;
                    if (dataLength <= 255) {
                        dataStruct3 = new LINTTypeShort(secsDataTypeCode.getValue(), (short) dataLength);
                    } else if (dataLength > 255 && dataLength <= 65535) {
                        dataStruct3 = new LINTTypeMedium(secsDataTypeCode.getValue(), dataLength);
                    } else {
                        dataStruct3 = new LINTTypeLong(secsDataTypeCode.getValue(), dataLength);
                    }
                    break;
                default:
                    break;
            }
        }

        return dataStruct3;
    }

    public static SecsDataTypeCode parsetSecsDataType(String format) {
        SecsDataTypeCode secsDataTypeCode = null;
        switch (format) {
            case "L":
                secsDataTypeCode = SecsDataTypeCode.LIST;
                break;
            case "B":
                secsDataTypeCode = SecsDataTypeCode.BYTE;
                break;
            case "BOOLEAN":
                secsDataTypeCode = SecsDataTypeCode.BOOL;
                break;
            case "A":
                secsDataTypeCode = SecsDataTypeCode.STRING;
                break;
            case "I1":
                secsDataTypeCode = SecsDataTypeCode.SINT;
                break;
            case "I2":
                secsDataTypeCode = SecsDataTypeCode.INT;
                break;
            case "I4":
                secsDataTypeCode = SecsDataTypeCode.DINT;
                break;
            case "I8":
                secsDataTypeCode = SecsDataTypeCode.LINT;
                break;
            case "F4":
                secsDataTypeCode = SecsDataTypeCode.REAL;
                break;
            case "F8":
                secsDataTypeCode = SecsDataTypeCode.LREAL;
                break;
            case "U1":
                secsDataTypeCode = SecsDataTypeCode.USINT;
                break;
            case "U4":
                secsDataTypeCode = SecsDataTypeCode.UDINT;
                break;
            case "U8":
                secsDataTypeCode = SecsDataTypeCode.ULINT;
                break;
            case "U2":
                secsDataTypeCode = SecsDataTypeCode.UINT;
                break;
        }
        return secsDataTypeCode;
    }

    public static String reverseSecsDataType(SecsDataTypeCode secsDataTypeCode) {
        String format = null;
        switch (secsDataTypeCode) {
            case LIST:
                format = "L";
                break;
            case BYTE:
                format = "B";
                break;
            case BOOL:
                format = "BOOLEAN";
                break;
            case STRING:
                format = "A";
                break;
            case SINT:
                format = "I1";
                break;
            case USINT:
                format = "U1";
                break;
            case REAL:
                format = "F4";
                break;
            case LREAL:
                format = "F8";
                break;
            case INT:
                format = "I2";
                break;
            case UINT:
                format = "U2";
                break;
            case DINT:
                format = "I4";
                break;
            case UDINT:
                format = "U4";
                break;
            case LINT:
                format = "I8";
            case ULINT:
                format = "U8";
                break;
        }
        return format;
    }

    public static String getDataStructValueByFormat(DataStructNormal dataStruct) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(dataStruct.getData());
        String param = null;
        switch (dataStruct.getDataType()) {
            case LIST:
                break;
            case BYTE:
                param = String.valueOf(byteBuf.getByte(0));
                break;
            case BOOL:
                param = String.valueOf(byteBuf.getBoolean(0));
                break;
            case STRING:
                param = new String(byteBuf.array());
                break;
            case SINT:
                param = String.valueOf(byteBuf.getByte(0));
                break;
            case USINT:
                param = String.valueOf(byteBuf.getUnsignedByte(0));
                break;
            case REAL:
                param = String.valueOf(byteBuf.getFloat(0));
                break;
            case LREAL:
                param = String.valueOf(byteBuf.getDouble(0));
                break;
            case INT:
                param = String.valueOf(byteBuf.getShort(0));
                break;
            case UINT:
                param = String.valueOf(byteBuf.getUnsignedShort(0));
                break;
            case DINT:
                param = String.valueOf(byteBuf.getInt(0));
                break;
            case UDINT:
                param = String.valueOf(byteBuf.getUnsignedInt(0));
                break;
            case LINT:
            case ULINT:
                param = String.valueOf(byteBuf.getLong(0));
                break;
        }
        return param;
    }

    public static DataStruct buildDataStruct(SecsDataTypeCode secsDataTypeCode, Object value) {
        DataStruct vidLen = null;
        switch (secsDataTypeCode) {
            case BOOL:
                vidLen = new DataStructNormal(BOOL, (short) new PlcBOOL(String.valueOf(value)).getBytes().length, new PlcBOOL(String.valueOf(value)).getBytes());
                break;
            case INT:
                vidLen = new DataStructNormal(INT, (short) new PlcINT(String.valueOf(value)).getBytes().length, new PlcINT(String.valueOf(value)).getBytes());
                break;
            case BYTE:
                vidLen = new DataStructNormal(BYTE, (short) new PlcBYTE(String.valueOf(value)).getBytes().length, new PlcBYTE(String.valueOf(value)).getBytes());
                break;
            case UINT:
                vidLen = new DataStructNormal(UINT, (short) new PlcUINT(String.valueOf(value)).getBytes().length, new PlcUINT(String.valueOf(value)).getBytes());
                break;
            case DINT:
                vidLen = new DataStructNormal(DINT, (short) new PlcDINT(String.valueOf(value)).getBytes().length, new PlcDINT(String.valueOf(value)).getBytes());
                break;
            case UDINT:
                vidLen = new DataStructNormal(UDINT, (short) new PlcUDINT(String.valueOf(value)).getBytes().length, new PlcUDINT(String.valueOf(value)).getBytes());
                break;
            case SINT:
                vidLen = new DataStructNormal(SINT, (short) new PlcSINT(String.valueOf(value)).getBytes().length, new PlcSINT(String.valueOf(value)).getBytes());
                break;
            case USINT:
                vidLen = new DataStructNormal(USINT, (short) new PlcUSINT(String.valueOf(value)).getBytes().length, new PlcUSINT(String.valueOf(value)).getBytes());
                break;
            case LINT:
                vidLen = new DataStructNormal(LINT, (short) new PlcLINT(String.valueOf(value)).getBytes().length, new PlcLINT(String.valueOf(value)).getBytes());
                break;
            case ULINT:
                vidLen = new DataStructNormal(ULINT, (short) new PlcULINT(String.valueOf(value)).getBytes().length, new PlcULINT(String.valueOf(value)).getBytes());
                break;
            case REAL:
                vidLen = new DataStructNormal(REAL, (short) new PlcREAL(String.valueOf(value)).getBytes().length, new PlcREAL(String.valueOf(value)).getBytes());
                break;
            case LREAL:
                vidLen = new DataStructNormal(LREAL, (short) new PlcLREAL(String.valueOf(value)).getBytes().length, new PlcLREAL(String.valueOf(value)).getBytes());
                break;
            case STRING:
                vidLen = new DataStructNormal(SecsDataTypeCode.STRING, (short) String.valueOf(value).getBytes().length, String.valueOf(value).getBytes());
                break;
        }
        return vidLen;
    }

    public static List<Boolean> conversionToBooleanValues(PlcValue values) {
        List<Boolean> booleanList = new ArrayList<>();
        int bitLength = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.getLength(); i++) {
            String binaryString = "";
            PlcValue plcValue = values.getIndex(i);
            if (plcValue instanceof PlcBOOL) {
//                bitLength = 1;
                binaryString = String.valueOf(plcValue.getInt());
            }
            if (plcValue instanceof PlcUSINT || plcValue instanceof PlcSINT || plcValue instanceof PlcBYTE) {
//                bitLength = 8;
                binaryString = StringUtils.reverse(String.format("%8s", Integer.toBinaryString(plcValue.getByte())).replace(' ', '0'));
            }
            if (plcValue instanceof PlcUINT || plcValue instanceof PlcINT) {
//                bitLength = 16;
                binaryString = StringUtils.reverse(String.format("%16s", Integer.toBinaryString(plcValue.getShort())).replace(' ', '0'));
            }
            if (plcValue instanceof PlcUDINT || plcValue instanceof PlcDINT) {
//                bitLength = 32;
                binaryString = StringUtils.reverse(String.format("%32s", Integer.toBinaryString(plcValue.getInt())).replace(' ', '0'));
            }
            if (plcValue instanceof PlcULINT || plcValue instanceof PlcLINT) {
//                bitLength = 64;
                binaryString = StringUtils.reverse(String.format("%64s", Long.toBinaryString(plcValue.getLong())).replace(' ', '0'));
            }
            sb.append(binaryString);
        }
        String binaryStrings = sb.toString();
        for (int i = 0; i < binaryStrings.length(); i++) {
            booleanList.add(new PlcBOOL(String.valueOf(binaryStrings.charAt(i))).getBoolean());
        }
        return booleanList;
    }

    public static DataStructNormal[] convertToDataStructs(DataStruct[] dataStructList) {
        List<DataStructNormal> list = new ArrayList<>();
        for (int i = 0; i < dataStructList.length; i++) {
            if (dataStructList[i] instanceof DataStructNormal) {
                list.add((DataStructNormal) dataStructList[i]);
            }
        }
        return list.toArray(new DataStructNormal[0]);
    }
}
