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

import java.util.*;

public class DataSpliter {
    /**
     * 拆分BinaryProcessor 二进制转换后的map集合
     * @param dataMap
     * @return
     */
    public List<Map<String, Object>> splitBinaryMapMessage(Map<String,Object> dataMap) {
        List<Map<String, Object>> maps = combineMapInOrder(dataMap);
        return maps;
    }
    public static List<Map<String, Object>> combineMapInOrder(Map<String, Object> originalMap) {
        List<Map<String, Object>> combinedMaps = new ArrayList<>();
        int valuesArraySize = 0;
        for(Map.Entry entry : originalMap.entrySet()){
            String datakey = (String) entry.getKey();
            List dataValueList = (List) entry.getValue();
            if(dataValueList.size() > valuesArraySize) {
                valuesArraySize = dataValueList.size();
            }
        }
        for (int i = 0; i < valuesArraySize; i++) {
            Map<String, Object> combinedMap = new LinkedHashMap<>();
            for(Map.Entry entry : originalMap.entrySet()){
                String datakey = (String) entry.getKey();
                List dataValueList = (List) entry.getValue();
                if(dataValueList.size() == 1) {
                    combinedMap.put(datakey,dataValueList.get(0));
                } else if(dataValueList.size()-1 < i) {
                    combinedMap.put(datakey,0);
                } else {
                    combinedMap.put(datakey,dataValueList.get(i));
                }
            }
            combinedMaps.add(combinedMap);
        }

        return combinedMaps;
    }
}
