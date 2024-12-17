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
package org.apache.plc4x.camel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinaryProcessorMap_1 implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryProcessorMap_1.class);
    private List<String> inParam;
    private List<String> outParam;
    private boolean littleEndian = false;

    public void setInParam(List<String> inParam) {
        this.inParam = inParam;
    }

    public void setOutParam(List<String> outParam) {
        this.outParam = outParam;
    }

    public void setLittleEndian(boolean littleEndian) {
        this.littleEndian = littleEndian;
    }
    public static int[] bit8 = new int[]{0b1, 0b10, 0b100, 0b1000, 0b10000, 0b100000, 0b1000000, 0b10000000};
    @Override
    public void process(Exchange exchange) throws Exception {
        ByteBuf byteBuffer = null;
        if (exchange.getIn().getBody() instanceof byte[]) {
            byte[] body = (byte[]) exchange.getIn().getBody();
            byteBuffer = Unpooled.wrappedBuffer(body);
            List<Object> list = new ArrayList<>();
            for (int i = 0; i < body.length; i++) {
                list.add(body[i]);
            }
            LOGGER.info("receive binary size is {} and  byte[] is {}", list.size(),list.toString());
        }
        if (exchange.getIn().getBody() instanceof String) {
            byte[] body = exchange.getIn().getBody().toString().getBytes();
            byteBuffer = Unpooled.wrappedBuffer(body);
        }
        int index = 0;
        Map<String, Object> dataMap = new HashMap<>();
        //Header信息处理
        short msId = byteBuffer.getShort(index);//消息id
        index+=2;
        short cId = byteBuffer.getShort(index);
        index+=2;
        //预留12 byte
        index+=12;
        //循环配置文件,组装json信息
        for (int i = 0; i < inParam.size(); i++) {
            String[] inParamStrArray = parseParam(inParam.get(i));
            DataTypeCode dataTypeCode = BinaryProcessorMap_1.DataTypeCode.map.get(inParamStrArray[0]);
            //计算dataNum
            int[] countDataNumResult = countDataNum(inParamStrArray[1], byteBuffer, index);
            index += countDataNumResult[0];
            int dataNum = countDataNumResult[1];
            List<Object> dataList = new ArrayList<>();
            index = formData(byteBuffer, dataTypeCode, dataNum, index, dataList);
            dataMap.put(outParam.get(i), dataList);
        }
        exchange.getIn().setBody(dataMap);
        byteBuffer.clear();
    }

//    public static void main(String[] args) {
//        try {
//            byte[] body = new byte[]{
//                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
//                1, 0, 6, 8, 10, 12, 0, 2, 18, 20, 1, 1, 0, 0
//            };
//            ByteBuf byteBuffer = Unpooled.wrappedBuffer(body);
//            String s1 = "5";
//            String s2 = "[]";
//            String s3 = "[5]";
//            int[] result = countDataNum(s1, byteBuffer, 0);
//            System.out.println(result[0] + "      " + result[1]);
//            result = countDataNum(s2, byteBuffer, 0);
//            System.out.println(result[0] + "      " + result[1]);
//            result = countDataNum(s3, byteBuffer, 0);
//            System.out.println(result[0] + "      " + result[1]);
//            BinaryProcessorMap_1 binaryProcessorMap_1 = new BinaryProcessorMap_1();
//            binaryProcessorMap_1.setInParam(new ArrayList<String>() {{
//                add("INT");
//                add("INT:2");
//                add("INT[]");
//                add("INT[1]");
//            }});
//            binaryProcessorMap_1.setOutParam(new ArrayList<String>() {{
//                add("aa");
//                add("bb");
//                add("cc");
//                add("dd");
//            }});
//
//            Map map = binaryProcessorMap_1.process(body);
////        Object o = exchange.getIn().getBody();
//            int a = 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Map<String, Object> process(byte[] body) throws Exception {
//        ByteBuf byteBuffer = Unpooled.wrappedBuffer(body);
////        if (exchange.getIn().getBody() instanceof byte[]) {
////            byte[] body = (byte[]) exchange.getIn().getBody();
////            byteBuffer = Unpooled.wrappedBuffer(body);
////            List<Object> list = new ArrayList<>();
////            for (int i = 0; i < body.length; i++) {
////                list.add(body[i]);
////            }
////            LOGGER.info("receive binary size is {} and  byte[] is {}", list.size(),list.toString());
////        }
////        if (exchange.getIn().getBody() instanceof String) {
////            byte[] body = exchange.getIn().getBody().toString().getBytes();
////            byteBuffer = Unpooled.wrappedBuffer(body);
////        }
//        int index = 0;
//        Map<String, Object> dataMap = new HashMap<>();
//        //Header信息处理
//        short msId = byteBuffer.getShort(index);//消息id
//        index += 2;
//        short cId = byteBuffer.getShort(index);
//        index += 2;
//        //预留12 byte
//        index += 12;
//        //循环配置文件,组装json信息
//        for (int i = 0; i < inParam.size(); i++) {
//            String[] inParamStrArray = parseParam(inParam.get(i));
//            DataTypeCode dataTypeCode = BinaryProcessorMap_1.DataTypeCode.map.get(inParamStrArray[0]);
//
//            //计算dataNum
////            int dataNum = 1;
////            if(inParamStrArray.length >= 1){
//            int[] countDataNumResult = countDataNum(inParamStrArray[1], byteBuffer, index);
//            index += countDataNumResult[0];
//            int dataNum = countDataNumResult[1];
////            }
//            List<Object> dataList = new ArrayList<>();
//            index = formData(byteBuffer, dataTypeCode, dataNum, index, dataList);
//
//            dataMap.put(outParam.get(i), dataList);
//        }
//        return dataMap;
//    }

    private static String[] parseParam(String param) {
        int index = param.indexOf(':');
        if (index > 1) {
            return new String[]{param.substring(0, index), param.substring(index + 1)};
        }
        index = param.indexOf("[");
        if (index > 1) {
            return new String[]{param.substring(0, index), param.substring(index)};
        }
        return new String[]{param, "1"};
    }

    private static int[] countDataNum(String lenS, ByteBuf byteBuffer,int index){
        int[] result = new int[2];
        String regexNum = "^[0-9]*$";
        Pattern patternNum = Pattern.compile(regexNum);
        if(patternNum.matcher(lenS).matches()){
            //若为纯数字
            result[1] = Integer.parseInt(lenS);
            return result;
        }

        String regex = "(\\[(.*?)])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(lenS);
        while (matcher.find()) {
            String numS = matcher.group(1);
            String num = numS.substring(1, numS.length()-1);
            if(num.trim().equalsIgnoreCase("")){
                // 可变长的数据
                result[0] = 2;
                result[1] = byteBuffer.getShort(index);
                return result;
            }else if(patternNum.matcher(num).matches()){

                int l = Integer.parseInt(num);
                result[1] = l;
                return result;
            }
        }
        throw  new RuntimeException("invalid param["+ lenS +"]");
    }

    private int formData(ByteBuf byteBuffer, DataTypeCode dataTypeCode, int dataNum, int index, List<Object> dataList){

        if (dataTypeCode == DataTypeCode.STRING) {//STRING类型单独处理，转换字符串
            byte[] bytes = new byte[dataNum];
            byteBuffer.getBytes(index,bytes);
            String strData = new String(bytes);
            index+=dataNum;
            dataList.add(strData);

        } else {
            for (int j = 0; j < dataNum; j++) {
                Object data = null;
                switch (dataTypeCode) {
                    case BYTE:
                        data = byteBuffer.getByte(index);
                        dataList.add(data);
                        index += 1;
                        break;
                    case BYTE_BOOL:
                        data = byteBuffer.getByte(index);
                        int[] arr = byte2BitArray(Byte.toUnsignedInt(byteBuffer.getByte(index)), bit8);
                        Arrays.stream(arr).forEach(n -> dataList.add(n));
                        index += 1;
                        break;
                    case BOOL:
                        data = byteBuffer.getBoolean(index);
                        dataList.add(data);
                        index += 2;
                        break;
                    case USINT:
                        data =  byteBuffer.getUnsignedByte(index);
                        dataList.add(data);
                        index += 1;
                        break;
                    case SINT:
                        data = byteBuffer.getByte(index);
                        dataList.add(data);
                        index += 1;
                        break;
                    case UINT:
                        if (littleEndian) {
                            data = byteBuffer.getUnsignedShortLE(index);
                        } else {
                            data = byteBuffer.getUnsignedShort(index);
                        }
                        dataList.add(data);
                        index += 2;
                        break;
                    case INT:
                        if (littleEndian) {
                            data = byteBuffer.getShortLE(index);
                        } else {
                            data = byteBuffer.getShort(index);
                        }
                        dataList.add(data);
                        index += 2;
                        break;
                    case UDINT:
                        if (littleEndian) {
                            data = byteBuffer.getUnsignedIntLE(index);
                        } else {
                            data = byteBuffer.getUnsignedInt(index);
                        }
                        dataList.add(data);
                        index += 4;
                        break;
                    case DINT:
                        if (littleEndian) {
                            data = byteBuffer.getIntLE(index);
                        } else {
                            data = byteBuffer.getInt(index);
                        }
                        dataList.add(data);
                        index += 4;
                        break;
                    case REAL:
                        if (littleEndian) {
                            data = byteBuffer.getFloatLE(index);
                        } else {
                            data = byteBuffer.getFloat(index);
                        }
                        dataList.add(data);
                        index += 4;
                        break;
                    case LREAL:
                        if (littleEndian) {
                            data = byteBuffer.getDoubleLE(index);
                        } else {
                            data = byteBuffer.getDouble(index);
                        }
                        dataList.add(data);
                        index += 8;
                        break;
                    case ULINT:
                    case LINT:
                        if (littleEndian) {
                            data = byteBuffer.getLongLE(index);
                        } else {
                            data = byteBuffer.getLong(index);
                        }
                        dataList.add(data);
                        index += 8;
                        break;
                    case WORD:
                        if (littleEndian) {
                            data = byteBuffer.getUnsignedShortLE(index);
                        } else {
                            data = byteBuffer.getShort(index);
                        }
                        dataList.add(data);
                        index += 2;
                        break;
                    case DWORD:
                        if (littleEndian) {
                            data = byteBuffer.getIntLE(index);
                        } else {
                            data = byteBuffer.getInt(index);
                        }
                        dataList.add(data);
                        index += 4;
                        break;
                    case LWORD:
                        if (littleEndian) {
                            data = byteBuffer.getLongLE(index);
                        } else {
                            data = byteBuffer.getLong(index);
                        }
                        dataList.add(data);
                        index += 8;
                        break;
                    default:
                        break;
                }
            }
        }
        return index;
    }
    private enum DataTypeCode {
        BYTE_BOOL((short) 1),
        BYTE((short) 1),
        BOOL((short) 1),
        SINT((short) 1),
        INT((short) 2),
        DINT((short) 4),
        REAL((short) 4),
        LREAL((short) 8),
        USINT((short) 1),
        UINT((short) 2),
        UDINT((short) 4),
        LINT((short) 8),
        ULINT((short) 8),
        STRING((short) 1),
        WORD((short) 2),
        DWORD((short) 4),
        LWORD((short) 8);

        private static final Logger logger = LoggerFactory.getLogger(DataTypeCode.class);

        private static final Map<String, DataTypeCode> map;

        static {
            map = new HashMap<>();
            for (DataTypeCode value : DataTypeCode.values()) {
                map.put(value.name(), value);
            }
        }

        private short size;

        DataTypeCode(short size) {
            this.size = size;
        }


        public short getSize() {
            return size;
        }

        public static DataTypeCode firstEnumForFieldSize(short fieldValue) {
            for (DataTypeCode _val : DataTypeCode.values()) {
                if (_val.getSize() == fieldValue) {
                    return _val;
                }
            }
            return null;
        }

        public static List<DataTypeCode> enumsForFieldSize(short fieldValue) {
            List<DataTypeCode> _values = new ArrayList();
            for (DataTypeCode _val : DataTypeCode.values()) {
                if (_val.getSize() == fieldValue) {
                    _values.add(_val);
                }
            }
            return _values;
        }

        public static DataTypeCode enumForValue(String name) {
            if (!map.containsKey(name)) {
                logger.error("No SecsDataTypeCode for value {}", name);
            }
            return map.get(name);
        }

        public static Boolean isDefined(int value) {
            return map.containsKey(value);
        }

    }

    public float swap(float value) {
        int bytes = Float.floatToIntBits(value);
        int b1 = (bytes >> 0) & 0xff;
        int b2 = (bytes >> 8) & 0xff;
        int b3 = (bytes >> 16) & 0xff;
        int b4 = (bytes >> 24) & 0xff;
        return Float.intBitsToFloat(b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0);
    }

    public static String hexadecimalToString(String str) {
        String result = new String();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i = i + 2) {
            String st = "" + charArray[i] + "" + charArray[i + 1];
            char ch = (char) Integer.parseInt(st, 16);
            result = result + ch;
        }
        return result;
    }

    public static int[] byte2BitArray(int num, int[] bits) {
        int[] intArray = new int[bits.length];
        for (int i = 0; i < bits.length; i++) {
            if ((num & bits[i]) > 0) {
                intArray[bits.length - i - 1] = 1;
            }
        }
        int start = 0;
        int end = intArray.length - 1;
        while (start < end) {
            int temp = intArray[start];
            intArray[start] = intArray[end];
            intArray[end] = temp;
            start++;
            end--;
        }
        return intArray;
    }
}
