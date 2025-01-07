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

import java.util.ArrayList;
import java.util.List;

import org.apache.plc4x.java.api.value.PlcValue;

public class EipStruct {
    
    private List<String> sourceFieldStrings = new ArrayList<String>();
    private int crc = 0;
    private String name;

    public EipStruct(List<String> fielsStrings){
        this.sourceFieldStrings = fielsStrings;
    }

    public List<EipField> getFields(){
        List<EipField> fields = new ArrayList<EipField>();
        for (String sourceFieldString : sourceFieldStrings) {
            fields.add(EipField.of(sourceFieldString));
            
        }
        return fields;

    }
    public int getCrc(){
        return this.crc;
    }
    public void setCrc(int crc){
        this.crc = crc;
    }
  
}
