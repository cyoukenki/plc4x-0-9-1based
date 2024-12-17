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
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BitConvertProcessor implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BitConvertProcessor.class);

    public static int[] bit8 = new int[]{0b1, 0b10, 0b100, 0b1000, 0b10000, 0b100000, 0b1000000, 0b10000000};

    @Override
    public void process(Exchange exchange) throws Exception {
        Object body = exchange.getIn().getBody();
        Map<Object, Object> resultMap = new HashMap<>();
        if (body instanceof Map) {
            Map<Object, Object> dataMap = (Map<Object, Object>) body;
            Set<Map.Entry<Object, Object>> entries = dataMap.entrySet();
            for (Map.Entry entry : entries) {
                List<Object> values = new ArrayList<>();
                List<Integer> bitResultList = new ArrayList<>();
                if (entry.getValue() instanceof List) {
                    List<Object> entryValue = (List<Object>) entry.getValue();
                    values.addAll(entryValue);
                } else {
                    values.add(entry.getValue());
                }
                for (Object obj : values) {
                    int value = Integer.parseInt(obj.toString());
                    int[] arr = byte2BitArray(value, bit8);
                    Arrays.stream(arr).forEach(n -> bitResultList.add(n));
                }
                resultMap.put(entry.getKey(), bitResultList);
            }
            exchange.getIn().setBody(resultMap);
        } else {
            LOGGER.warn("This data type not currently supported!");
        }
    }
//    public String int2Bit8(String arrayString) {
//        JsonArray jsonArray = new Gson().fromJson(arrayString, JsonArray.class);
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < jsonArray.size(); i++) {
//            int[] arr = byte2BitArray(jsonArray.get(i).getAsInt(), bit8);
//            Arrays.stream(arr).forEach(n -> stringBuffer.append(n + ","));
//        }
//        arrayString = stringBuffer.toString();
//        return "[" + arrayString.substring(0, arrayString.length() - 1) + "]";
//    }

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
