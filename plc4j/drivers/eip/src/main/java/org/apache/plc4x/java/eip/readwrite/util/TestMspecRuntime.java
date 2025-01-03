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
package org.apache.plc4x.java.eip.readwrite.util;

import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;
import org.apache.plc4x.java.spi.generation.ReadBuffer;
import org.apache.plc4x.java.spi.generation.ReadBufferByteBased;
import org.apache.plc4x.java.spi.generation.WriteBufferByteBased;
// import org.apache.plc4x.java.eip.struct.*;

public class TestMspecRuntime {
    public static void main(String[] args) {
        new MspecRuntime("plc4j/drivers/eip/src/main/java/org/apache/plc4x/java/eip/readwrite/util/structs/struct_aaa.mspec").execute();
       System.out.println(System.getProperty("user.dir"));
        try {
            Thread.sleep(2000);

            // 动态加载类 MyClass
            Class<?> clazz = Class.forName("org.apache.plc4x.java.eip.struct.io.MachineStructIO");

            // 创建 MyClass 的实例
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // 将实例转换为接口类型
            if (instance instanceof MessageIO) {
                MessageIO myInterface = (MessageIO) instance;
                ReadBuffer rf = new ReadBufferByteBased(new byte[]{1,2,3,3,3,1,1,1,1});
                Message res =(Message) myInterface.parse(rf,null);
                System.out.println(res.toString()+";"+res.getLengthInBytes());

                WriteBufferByteBased wb = new WriteBufferByteBased(9);
                // MachineStruct ms = new MachineStruct((short)2,(byte)1,(int)1,(byte)2);
                // myInterface.serialize(wb, ms, null);
                // System.out.println(wb.toString()+";"+wb.getData().length);
                // for (byte da : wb.getData()) {
                //     System.out.println(da);
                // }
            } else {
                System.out.println("The object does not implement MyInterface.");
            }

           

        } catch (Exception e) {
            e.printStackTrace();
        }
   
    }
}
