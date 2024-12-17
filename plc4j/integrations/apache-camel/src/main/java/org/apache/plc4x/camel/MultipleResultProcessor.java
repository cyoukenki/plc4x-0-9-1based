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

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MultipleResultProcessor implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MultipleResultProcessor.class);
    private String multipleResultKey;
    private List<String> resultMappingList;

    public void setMultipleResultKey(String multipleResultKey) {
        this.multipleResultKey = multipleResultKey;
    }

    public void setResultMappingList(List<String> resultMappingList) {
        this.resultMappingList = resultMappingList;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Map resultMap = exchange.getIn().getBody(Map.class);
        Map mappingMap = new LinkedHashMap();
        if(resultMap.containsKey(multipleResultKey)) {//获取读取结果和xml定义映射匹配
            if(resultMap.get(multipleResultKey) instanceof List) {
                List resultList = (List) resultMap.get(multipleResultKey);
                if(resultList.size() == resultMappingList.size()) {//获取读取结果和xml定义映射数量匹配
                    for (int i = 0; i<resultMappingList.size(); i++ ) {
                        String mappingKey = resultMappingList.get(i);
                        String mappingValue = String.valueOf(resultList.get(i));
                        mappingMap.put(mappingKey,mappingValue);
                    }
                    resultMap.remove(multipleResultKey);//移除原结果数组
                    resultMap.put(multipleResultKey,mappingMap);//添加映射map结果
                } else {
                    //数量不一致
                    LOGGER.error("{} mapping count is incorrectly ,please check xml mapping setting",multipleResultKey);
                }
            } else { //结果非List  结果数量=1
                if(resultMappingList.size() == 1) {//获取结果和xml定义映射数量匹配
                    String mappingValue = String.valueOf(resultMap.get(multipleResultKey));
                    String mappingKey = resultMappingList.get(0);
                    mappingMap.put(mappingKey,mappingValue);
                    resultMap.remove(multipleResultKey);//移除原结果
                    resultMap.put(multipleResultKey,mappingMap);//添加映射map结果
                } else {
                    //数量不一致
                    LOGGER.error("{} mapping count is incorrectly ,please check xml mapping setting",multipleResultKey);
                }
            }
        } else {//返回结果不包含multipleResultKey
            LOGGER.error("The return result does not contain multipleResultKey {}",multipleResultKey);
        }
        exchange.getIn().setBody(resultMap);
    }
}
