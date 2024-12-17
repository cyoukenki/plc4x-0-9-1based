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

public enum OperationStatus {
    OPERATION_STATUS_0000("0000","Initial status",""),
    OPERATION_STATUS_0001("0001","Waiting for model selection",""),
    OPERATION_STATUS_0002("0002","Setup change in operation",""),
    OPERATION_STATUS_0003("0003","Waiting for input of lot number and PCB ID",""),
    OPERATION_STATUS_0004("0004","Waiting for PCB unloading",""),
    OPERATION_STATUS_0005("0005","Inspection in execution",""),
    OPERATION_STATUS_0006("0006","Pass in execution",""),
    OPERATION_STATUS_0007("0007","Waiting for PCB unloading",""),
    OPERATION_STATUS_0010("0010","Model in setting","Inspection program in loading"),
    OPERATION_STATUS_0011("0011","Rail width information in output","Upper-link communication and rail width information output in execution"),
    OPERATION_STATUS_0012("0012","Model change in confirmation","Upper-link communication and model change in execution"),
    OPERATION_STATUS_0013("0013","Lot number input in confirmation","Upper-link communication and lot number capture in execution"),
    OPERATION_STATUS_0014("0014","PCB ID input in confirmation","Upper-link communication and PCB ID capture in execution"),
    OPERATION_STATUS_0015("0015","PCB ID in output","Upper-link communication and PCB ID output in execution"),
    OPERATION_STATUS_0020("0020","PCB loading at a stop",""),
    OPERATION_STATUS_0021("0021","Inspection at a stop",""),
    OPERATION_STATUS_0022("0022","Pass at a stop",""),
    OPERATION_STATUS_0023("0023","PCB unloading at a stop",""),
    OPERATION_STATUS_0050("0050","Error occurred","");
    private String statusCode;
    private String status;
    private String remark;
    OperationStatus(String statusCode, String status,String remark) {
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
        OperationStatus [] operationStatuses = values();    //获取所有枚举集合
        for (OperationStatus operationStatus : operationStatuses) {
            if (operationStatus.getStatusCode().equals(statusCode)) {
                return operationStatus.getStatus();
            }
        }
        return null;
    }
}
