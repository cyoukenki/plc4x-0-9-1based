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

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.spi.values.PlcDINT;
import org.apache.plc4x.java.spi.values.PlcUDINT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DataConversionProcessor implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataConversionProcessor.class);
    private Map<String, Object> dataConversionMap;
    private String conversionType;
    private boolean asciiReverse = false;
    private List<String> conversionToStringList;//需要转换为ASCII码的变量

    public Map<String, Object> getDataConversionMap() {
        return dataConversionMap;
    }

    public void setDataConversionMap(Map<String, Object> dataConversionMap) {
        this.dataConversionMap = dataConversionMap;
    }

    public String getConversionType() {
        return conversionType;
    }

    public void setConversionType(String conversionType) {
        this.conversionType = conversionType;
    }

    public boolean isAsciiReverse() {
        return asciiReverse;
    }

    public void setAsciiReverse(boolean asciiReverse) {
        this.asciiReverse = asciiReverse;
    }

    public List<String> getConversionToStringList() {
        return conversionToStringList;
    }

    public void setConversionToStringList(List<String> conversionToStringList) {
        this.conversionToStringList = conversionToStringList;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Map<String, Object> dataMap = (Map) exchange.getIn().getHeader("dataMap");
        for (Map.Entry entry : dataConversionMap.entrySet()
        ) {
            String value = (String) entry.getValue();//<entry key="DATA1" value="40031:DINT[7]"/>
            String key = (String) entry.getKey();
            if (!StringUtils.isEmpty(conversionType) && value.contains(conversionType)) {//包含要转换的数据类型
                switch (conversionType) {
                    case "DINT":
                        Object dataConent = dataMap.get(key);
                        if (dataConent instanceof List) {//集合
                            List<Object> list = (List<Object>) dataConent;
                            List<Object> conversionDataList = new ArrayList<>();
                            for (int i = 0; i < list.size(); i++) {
                                PlcDINT plcDINT = new PlcDINT(swapInt16Bits(((PlcDINT) list.get(i)).getInteger()));
                                conversionDataList.add(plcDINT);
                            }
                            dataMap.put(key, conversionDataList);
                        } else {
                            Integer i = swapInt16Bits((Integer) dataConent);
                            dataMap.put(key, i);
                        }
                        break;
                }
            }
            if (conversionToStringList != null) {
                if (conversionToStringList.contains(key)) {
                    Object dataConent2 = dataMap.get(key);
                    if (dataConent2 instanceof List) {//集合
                        StringBuilder stringBuilder = new StringBuilder();
                        List<Object> list = (List<Object>) dataConent2;
                        for (int i = 0; i < list.size(); i++) {
                            try {
                                String dataStr = hexadecimalToString(Integer.toHexString(((PlcUDINT) list.get(i)).getInteger()));
                                String tempStr = StringUtils.isEmpty(dataStr) ? "" : dataStr;
                                if (asciiReverse) {
                                    String swapStr = tempStr.length() > 1 ? swap(dataStr, 0, 1) : tempStr;
                                    stringBuilder.append(swapStr);
                                } else {
                                    stringBuilder.append(tempStr);
                                }
                            } catch (Exception e) {
                                LOGGER.warn("there have value that cannot be converted :{}", list.get(i));
                            }
                        }
                        dataMap.put(key, stringBuilder.toString().trim());
                    } else {
                        String dataStr = hexadecimalToString(Integer.toHexString((Integer) dataConent2));
                        dataMap.put(key, dataStr);
                    }
                }
            }
        }
        String jsonResult = new Gson().toJson(dataMap);
        exchange.getIn().setBody(jsonResult);
        exchange.getIn().setBody(dataMap);
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

    public static int swapInt16Bits(int value) {
        int first16Bits = value & 0xFFFF0000; // 提取前16位
        int last16Bits = value & 0x0000FFFF; // 提取后16位

        int swappedValue = (last16Bits << 16) | (first16Bits >>> 16); // 交换前16位和后16位

        return swappedValue;
    }

    public static String swap(String str, int i, int j) {

        StringBuilder strB = new StringBuilder(str);

        char l = strB.charAt(i), r = strB.charAt(j);

        strB.setCharAt(i, r);

        strB.setCharAt(j, l);

        return strB.toString();

    }
}
