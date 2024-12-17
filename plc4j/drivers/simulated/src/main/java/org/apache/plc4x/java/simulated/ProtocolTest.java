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
package org.apache.plc4x.java.simulated;

import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.simulated.readwrite.*;
import org.apache.plc4x.java.simulated.readwrite.types.*;

import io.vavr.API.Match;

public class ProtocolTest {
    static DataStruct[] values = new DataStruct[1];
    static VidData[] zz = new VidData[1];
    static ReportData[] rds = new ReportData[1];
    public static void main(String[] args) {
        byte[] bb = new byte[4];
        bb[0] = 0x00;
        bb[1] = 0x00;
        bb[2] = 0x00;
        bb[3] = 0x0c;
        DataStruct value = new DataStruct(SecsDataTypeCode.INT,1,bb);
    
        values[0] = value;
        DataStruct aid = new DataStruct(SecsDataTypeCode.INT,1,bb);
        DataStruct bid = new DataStruct(SecsDataTypeCode.INT,1,bb);

        VidData vd = new VidData((short)1,values);
        zz[0] = vd;
        ReportData rd = new ReportData((short)1,aid,zz,bid);
        rds[0] = rd;
        System.out.println(new S6F11Request(12,(short)1,(short)1,(long)111,aid,bid,rds).toString(ToStringStyle.MULTI_LINE_STYLE));
    }
}
