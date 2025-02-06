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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class PlcStructureConfig {
    TreeMap<String, TreeMap<String, Object>> structs;
    Pattern pattern = Pattern.compile("name:([^;]+);(.*)");

    public PlcStructureConfig(TreeMap<String, TreeMap<String, Object>> source){
        this.structs = source;
    }
    public TreeMap<String, TreeMap<String, Object>> get(){
        return this.structs;
    }
    public TreeMap<String, TreeMap<String, Object>> getSort(){
        TreeMap<String, TreeMap<String, Object>> result = new TreeMap<>();
        for (String key : this.structs.keySet()) {
            result.put(key, this.sortNestedMap(this.structs.get(key)));
        }
        
        return result;
    }

    private TreeMap<String, Object> sortNestedMap(Map<String, Object> map) {
        // 使用TreeMap来保持排序
        TreeMap<String, Object> sortedMap = new TreeMap<>((key1, key2) -> {
            // 提取键中的数字部分进行排序
            int num1 = Integer.parseInt(key1.split(":")[1]);
            int num2 = Integer.parseInt(key2.split(":")[1]);
            return Integer.compare(num1, num2);
        });

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof Map<?, ?>) {
                // 如果值是Map，递归排序
                @SuppressWarnings("unchecked")
                Map<String, Object> subMap = (Map<String, Object>) entry.getValue();
                sortedMap.put(entry.getKey(), sortNestedMap(subMap));
            } else {
                // 否则直接放入
                sortedMap.put(entry.getKey(), entry.getValue());
            }
        }

        return sortedMap;
    }

   
}
