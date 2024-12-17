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
package org.apache.plc4x.java.secsgem.readwrite.constant;

public class ServerConstants {

    public static final int ACCEPT_YES = 1;
    public static final int ACCEPT_NO = 2;

    public static final int EXECUTE_SUCCESS = 1;
    public static final int EXECUTE_ERROR = 0;
    public static final int EXECUTE_DEFAULT = 9999;


    public static final String PROTOCOL_EIP = "Ethernet/IP";
    public static final String PROTOCOL_MODBUS = "Modbus";
    public static final String PROTOCOL_S7 = "S7";
    public static final String PROTOCOL_FINS = "Fins";
    public static final String PROTOCOL_OPCUA = "OPCUA";
    public static final String PROTOCOL_AB_ETH = "AB-ETH";
    public static final String PROTOCOL_ADS_AMS = "ADS/AMS";
    public static final String PROTOCOL_BACNET_IP = "BACnet/IP";
    public static final String PROTOCOL_CANOPEN = "CANopen";
    public static final String PROTOCOL_DELTAV = "DeltaV";
    public static final String PROTOCOL_DF1 = "DF1";
    public static final String PROTOCOL_FIRMATA = "Firmata";
    public static final String PROTOCOL_KNXNET_IP = "KNXnet/IP";
    public static final String PROTOCOL_PROFINET = "Profinet";


    public static final String DATA_DEFINITION_TYPE_EQUIPMENT_CONSTANT = "Equipment Constant";
    public static final String DATA_DEFINITION_TYPE_STATUS_VARIABLE = "Status Variable";
    public static final String DATA_DEFINITION_TYPE_DISCRETE_VARIABLE = "Discrete Variable";
    //    public static final String DATA_DEFINITION_TYPE_EQUIPMENT_VARIABLE = "Equipment Variable";//移除
    public static final String DATA_DEFINITION_TYPE_CONTROLLER_VARIABLE = "Controller Variable";
    public static final String DATA_DEFINITION_TYPE_COMMANDPARAMETER = "Command Parameter";
    public static final String DATA_DEFINITION_TYPE_REPORT_DEFINITION = "Report Definition";//事件上报 report定义
    public static final String MODEL_SETTING_TYPE_COMMUNICATION_STATE = "Communication State";
    public static final String MODEL_SETTING_TYPE_CONTROL_STATE = "Control State";
    public static final String EVENT_DEFINITION = "Event Definition";//事件上报  事件定义
    public static final String EQ2HOST_COMMAND_S10F1 = "S10F1_E2H";//S10F1  eq->host 状态定义变量
    public static final String EQ2HOST_COMMAND_S1F1 = "S1F1_E2H";//S1F1  eq->host 状态定义变量
    public static final String EQ2HOST_COMMAND_S1F13 = "S1F13_E2H";//S1F13  eq->host 状态定义变量
    public static final String EQ2HOST_COMMAND_S2F17 = "S2F17_E2H";//S2F17  eq->host 状态定义变量
    public static final String EQ_STATUS = "EqStatus";//eqStatus
    public static final String S1F15_S1F17_SIGN = "S1F15_S1F17_SIGN";//eqStatus

    public static final String EQUIPMENT_STATUS_OFF_LINE = "Equipment OFF-Line";
    public static final String EQUIPMENT_STATUS_ON_LINE = "ON-Line";
    public static final String ON_LINE = "ON-LINE";
    public static final String OFF_LINE = "OFF-LINE";
    public static final String ALARM_STATUS_ENABLE = "Enable";
    public static final String ALARM_STATUS_DISABLE = "Disable";
    public static final String EVENT_STATUS_ENABLE = "Enable";
    public static final String EVENT_STATUS_DISABLE = "Disable";

    public static final String REMOTE_COMMAND_PPSELECT = "PPSELECT";

    public static final String WEBSOCKET_TOPIC = "Middle_Ware_Realtime_Message";

    public static final String STREAM_FUNCTION_S1 = "81";
    public static final String STREAM_FUNCTION_S2 = "82";
    public static final String STREAM_FUNCTION_S3 = "83";
    public static final String STREAM_FUNCTION_S4 = "84";
    public static final String STREAM_FUNCTION_S5 = "85";
    public static final String STREAM_FUNCTION_S6 = "86";
    public static final String STREAM_FUNCTION_S7 = "87";
    public static final String STREAM_FUNCTION_S8 = "88";
    public static final String STREAM_FUNCTION_S9 = "89";
    public static final String STREAM_FUNCTION_S10 = "8a";
    public static final String STREAM_FUNCTION_S11 = "8b";
    public static final String STREAM_FUNCTION_S12 = "8c";
    public static final String STREAM_FUNCTION_S13 = "8d";
    public static final String STREAM_FUNCTION_S14 = "8e";
    public static final String STREAM_FUNCTION_S15 = "8f";
    public static final String STREAM_FUNCTION_S16 = "90";
    public static final String STREAM_FUNCTION_S17 = "91";

    //    public static final int T3_TIME = 30000;
    public static final String COMMAND_S1F1 = "S1F1";
    public static final String COMMAND_S1F2 = "S1F2";
    public static final String COMMAND_S1F13 = "S1F13";
    public static final String COMMAND_S1F14 = "S1F14";
    public static final String COMMAND_S1F3 = "S1F3";
    public static final String COMMAND_S1F4 = "S1F4";
    public static final String COMMAND_S1F11 = "S1F11";
    public static final String COMMAND_S1F15 = "S1F15";
    public static final String COMMAND_S1F17 = "S1F17";
    public static final String COMMAND_S2F13 = "S2F13";
    public static final String COMMAND_S2F14 = "S2F14";
    public static final String COMMAND_S2F15 = "S2F15";
    public static final String COMMAND_S2F16 = "S2F16";
    public static final String COMMAND_S2F17 = "S2F17";
    public static final String COMMAND_S2F18 = "S2F18";
    public static final String COMMAND_S2F23 = "S2F23";
    public static final String COMMAND_S2F24 = "S2F24";
    public static final String COMMAND_S2F29 = "S2F29";
    public static final String COMMAND_S2F30 = "S2F30";
    public static final String COMMAND_S2F31 = "S2F31";
    public static final String COMMAND_S2F32 = "S2F32";
    public static final String COMMAND_S2F33 = "S2F33";
    public static final String COMMAND_S2F34 = "S2F34";
    public static final String COMMAND_S2F35 = "S2F35";
    public static final String COMMAND_S2F36 = "S2F36";
    public static final String COMMAND_S2F37 = "S2F37";
    public static final String COMMAND_S2F38 = "S2F38";
    public static final String COMMAND_S2F41 = "S2F41";
    public static final String COMMAND_S2F42 = "S2F42";
    public static final String COMMAND_S5F1 = "S5F1";
    public static final String COMMAND_S5F2 = "S5F2";
    public static final String COMMAND_S5F3 = "S5F3";
    public static final String COMMAND_S5F4 = "S5F4";
    public static final String COMMAND_S5F5 = "S5F5";
    public static final String COMMAND_S5F6 = "S5F6";
    public static final String COMMAND_S5F7 = "S5F7";
    public static final String COMMAND_S5F8 = "S5F8";
    public static final String COMMAND_S6F1 = "S6F1";
    public static final String COMMAND_S6F2 = "S6F2";
    public static final String COMMAND_S6F11 = "S6F11";
    public static final String COMMAND_S6F12 = "S6F12";
    public static final String COMMAND_S6F15 = "S6F15";
    public static final String COMMAND_S6F19 = "S6F19";
    public static final String COMMAND_S6F20 = "S6F20";
    public static final String COMMAND_S7F1 = "S7F1";
    public static final String COMMAND_S7F2 = "S7F2";
    public static final String COMMAND_S7F3 = "S7F3";
    public static final String COMMAND_S7F4 = "S7F4";
    public static final String COMMAND_S7F5 = "S7F5";
    public static final String COMMAND_S7F6 = "S7F6";
    public static final String COMMAND_S7F17 = "S7F17";
    public static final String COMMAND_S7F18 = "S7F18";
    public static final String COMMAND_S7F19 = "S7F19";
    public static final String COMMAND_S7F20 = "S7F20";
    public static final String COMMAND_S7F23 = "S7F23";
    public static final String COMMAND_S7F24 = "S7F24";
    public static final String COMMAND_S7F25 = "S7F25";
    public static final String COMMAND_S7F26 = "S7F26";
    public static final String COMMAND_S10F1 = "S10F1";
    public static final String COMMAND_S10F2 = "S10F2";
    public static final String COMMAND_S10F3 = "S10F3";
    public static final String COMMAND_S10F4 = "S10F4";
    public static final String COMMAND_S10F5 = "S10F5";
    public static final String COMMAND_S10F6 = "S10F6";
    public static final String DEVICE_INFO_REQUEST = "DeviceInfoRequest";
    public static final String REQUEST_FUNCTION_TYPE_BASE_SETTING = "BASE_SETTING";
    public static final String REQUEST_FUNCTION_TYPE_DATA_COMMAND = "DATA_COMMAND";
    public static final String REQUEST_FUNCTION_TYPE_CONNECT = "CONNECT";
    public static final String REQUEST_FUNCTION_TYPE_DISCONNECT = "DISCONNECT";

    public static final String REQUEST_FUNCTION_TYPE_COMMUNICATION_STATE = "Communication State";
    public static final String REQUEST_FUNCTION_TYPE_CONTROL_STATE = "Control State";
}
