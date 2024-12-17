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
package org.apache.plc4x.java.upperlink.readwrite.constant;

public enum ErrorContents {
    ERROR_STATUS_0000("0000","No Error",""),
    ERROR_STATUS_0010("0010","Power Outage",""),
    ERROR_STATUS_0011("0011","UPS Low Battery",""),
    ERROR_STATUS_0012("0012","Air Pressure Error",""),
    ERROR_STATUS_0013("0013","Illumination Error",""),
    ERROR_STATUS_0014("0014","Interlock","Front cover or rear cover"),
    ERROR_STATUS_0015("0015","Maintenance Interlock",""),
    ERROR_STATUS_0016("0016","Right Area Sensor",""),
    ERROR_STATUS_0017("0017","Left Area Sensor",""),
    ERROR_STATUS_0018("0018","External Interlock",""),
    ERROR_STATUS_0019("0019","Emergency Stop",""),
    ERROR_STATUS_001A("001A","X-axis Servo Alarm",""),
    ERROR_STATUS_001B("001B","Y-axis Servo Alarm",""),
    ERROR_STATUS_001C("001C","PCB Conveyance Error",""),
    ERROR_STATUS_001D("001D","Mechanism unit error",""),
    ERROR_STATUS_0020("0020","Communication Error of Mechanical Controller",""),
    ERROR_STATUS_0021("0021","Axis Control Unit Error",""),
    ERROR_STATUS_0022("0022","Image Processing Unit Error",""),
    ERROR_STATUS_0023("0023","Camera Communication Error",""),
    ERROR_STATUS_0030("0030","Free disk space error",""),
    ERROR_STATUS_0031("0031","Network error","Only when the network is in use."),
    ERROR_STATUS_0032("0032","Database Error","Only when the data base is in use."),
    ERROR_STATUS_0033("0033","Communication Error","Only when the inspection result communication is also in use."),
    ERROR_STATUS_0040("0040","Upper-link communication error",""),
    ERROR_STATUS_0041("0041","No model code registered",""),
    ERROR_STATUS_0042("0042","No Registered Inspection Programs",""),
    ERROR_STATUS_0043("0043","No Registered Destinations",""),
    ERROR_STATUS_0044("0044","Model setting error","Loading of the inspection program is failed."),
    ERROR_STATUS_0100("0100","Others","Printer output error, etc.");
    private String statusCode;
    private String status;
    private String remark;
    ErrorContents(String statusCode, String status, String remark) {
        this.statusCode = statusCode;
        this.status = status;
        this.remark = remark;
    }
    @Override
    public String toString() {
        return this.statusCode + "-" + this.status + "-" + this.remark;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static String getStatusByCode(String statusCode) {
        ErrorContents[] errorContents = values();    //获取所有枚举集合
        for (ErrorContents errorContent : errorContents) {
            if (errorContent.getStatusCode().equals(statusCode)) {
                return errorContent.getStatus();
            }
        }
        return null;
    }
}
