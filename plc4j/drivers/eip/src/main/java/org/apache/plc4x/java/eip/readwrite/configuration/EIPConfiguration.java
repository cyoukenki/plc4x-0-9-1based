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
package org.apache.plc4x.java.eip.readwrite.configuration;

import java.util.Map;

import org.apache.plc4x.java.eip.readwrite.EIPDriver;
import org.apache.plc4x.java.eip.readwrite.field.EipStruct;
import org.apache.plc4x.java.eip.readwrite.util.MspecRuntime;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.configuration.annotations.ConfigurationParameter;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;
import org.apache.plc4x.java.spi.generation.ReadBuffer;
import org.apache.plc4x.java.spi.generation.ReadBufferByteBased;
import org.apache.plc4x.java.spi.generation.WriteBufferByteBased;
import org.apache.plc4x.java.transport.tcp.TcpTransportConfiguration;

public class EIPConfiguration implements Configuration, TcpTransportConfiguration {

    @ConfigurationParameter
    private int backplane;

    @ConfigurationParameter
    private int slot;

    @ConfigurationParameter
    private int dataPackageByteLength;

    @ConfigurationParameter
    private String structPath;


//    public static final int UCMM_PACKAGE_LENGTH = 482;
//    public static final int DATA_PACKAGE_LENGTH = 446;
    public static final int UCMM_PACKAGE_LENGTH = 496;
    public static final int UCMM_PACKAGE_LENGTH_WRITE = 496;
    public static final int UCMM_PACKAGE_LENGTH_READ = 496;
    public static final int DATA_PACKAGE_LENGTH = 446;

    public int getBackplane() {
        return backplane;
    }

    public void setBackplane(int backpane) {
        this.backplane = backpane;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getDataPackageByteLength() {
        return dataPackageByteLength == 0 ? DATA_PACKAGE_LENGTH : dataPackageByteLength;
    }

    public void setDataPackageByteLength(int dataPackageByteLength) {
        this.dataPackageByteLength = dataPackageByteLength;
    }
    public String getStructPath() {
        structTest();
        return this.structPath;
    }

    public void setStructPath(String structs) {
        this.structPath = structs;
    }

    @Override
    public int getDefaultPort() {
        return EIPDriver.PORT;
    }


    public void structTest(){
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
