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
package org.apache.plc4x.java.eip.readwrite.field;

import java.util.HashMap;
import java.util.Map;

import org.apache.plc4x.java.api.value.PlcValue;
import org.apache.plc4x.java.spi.values.PlcStruct;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

public class TestStruct {
    public static void main(String[] args) {
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> subData = new HashMap<>();
        Map<String,Object> subData1 = new HashMap<>();
        
        data.put("t1", "%t1:INT");
        data.put("t2", "%t2:REAL");
        subData.put("t1", "%t1:INT");
        subData.put("t2", "%t2:INT");
        data.put("t3", subData);
        subData1.put("aaaa", "%t2:LREAL");
        
        subData.put("subsub", subData1);

        System.out.println("source:"+data.toString());
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.buffer(200);
        int index = 0;
        EipStructHandler structHandler = new EipStructHandler().initIndex(10);

        PlcValue result = structHandler.parse(data, byteBuf);
        Map<String,? extends PlcValue> res  =  result.getStruct();
        System.out.println(res.toString());
        System.out.println("index:"+structHandler.getIndex());

    }
}
