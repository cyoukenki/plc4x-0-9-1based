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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.plc4x.java.eip.readwrite.EIPDriver;
import org.apache.plc4x.java.eip.readwrite.field.EipStruct;
import org.apache.plc4x.java.eip.readwrite.protocol.EipClass3ProtocolLogic;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.configuration.annotations.ConfigurationParameter;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;
import org.apache.plc4x.java.spi.generation.ReadBuffer;
import org.apache.plc4x.java.spi.generation.ReadBufferByteBased;
import org.apache.plc4x.java.spi.generation.WriteBufferByteBased;
import org.apache.plc4x.java.transport.tcp.TcpTransportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class EIPConfiguration implements Configuration, TcpTransportConfiguration {

     private static final Logger logger = LoggerFactory.getLogger(EIPConfiguration.class);

    @ConfigurationParameter
    private int backplane;

    @ConfigurationParameter
    private int slot;

    @ConfigurationParameter
    private int dataPackageByteLength;

    @ConfigurationParameter
    private String structs;


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
    public String getStructs() {
       
        return this.structs;
    }
    public Map<String,Map<String,Object>> getStructInstance(){
        try{
            Map<String,Map<String,Object>> res =  new Gson().fromJson(this.structs, LinkedHashMap.class);
            return res;
        }catch (Exception e){
            logger.warn("Undefined correct structure data source .");
            return null;
        }
      
       
       
    }

    public void setStructs(String structs) {
        this.structs = structs;
    }

    @Override
    public int getDefaultPort() {
        return EIPDriver.PORT;
    }


    

}
