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
package org.apache.plc4x.java.secsgem.readwrite.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.plc4x.java.api.types.PlcRequestType;
import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.constant.ServerConstants;
import org.apache.plc4x.java.secsgem.readwrite.internal.DataHolder;
import org.apache.plc4x.java.secsgem.readwrite.model.BaseSettingInfo;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.messages.DefaultRequestMessage;
import org.apache.plc4x.java.spi.messages.DefaultResponseMessage;
import org.apache.plc4x.java.spi.messages.DefaultServerConnectorListener;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.plc4x.java.secsgem.readwrite.constant.ServerConstants.REQUEST_FUNCTION_TYPE_BASE_SETTING;

public class LogicRequestUtils {
    private static Gson gson = new Gson();

    /**
     * 初始化基础配置参数
     */
    public static boolean initBaseSetting(ConversationContext<SecsPacket> context) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setRequestFunctionType(REQUEST_FUNCTION_TYPE_BASE_SETTING);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        Object data = defaultResponseMessage.getData();
        BaseSettingInfo baseSettingInfo = gson.fromJson(gson.toJson(data), BaseSettingInfo.class);
        if (baseSettingInfo == null) {
            return false;
        } else {
            DataHolder.getInstance().setBaseSettingInfo(baseSettingInfo);
            return true;
        }
    }

    public static JsonObject initModelSettings(ConversationContext<SecsPacket> context, String modelSettingType) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setRequestFunctionType(modelSettingType);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        JsonObject data = (JsonObject) defaultResponseMessage.getData();
        return data;
    }

    public static DefaultResponseMessage sendS1F3Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S1F3);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStruct[] values = ((S1F3Request) secsPacket).getValues();
        DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S1F3Request) secsPacket).getValues());//请求数据
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", vidvalues);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS1F11Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S1F11);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S1F11Request) secsPacket).getValues());//请求数据
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", vidvalues);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F13Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F13);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S2F13Request) secsPacket).getValues());//请求数据
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", vidvalues);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F15Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F15);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        ECID1DataStruct[] values = ((S2F15Request) secsPacket).getValues();//请求数据
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", values);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F17Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F17);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F29Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F29);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S2F29Request) secsPacket).getValues());//请求数据
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", vidvalues);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F31Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DataStructNormal ctime = (DataStructNormal) ((S2F31Request) secsPacket).getCtime();//获取请求时间参数
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F31);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", ctime);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F23Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F23);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal[] svidData = SecsDataUtils.convertToDataStructs(((S2F23Request) secsPacket).getValues());
        DataStructNormal trid = (DataStructNormal) ((S2F23Request) secsPacket).getTrid();//trid ID
        DataStructNormal dsper = (DataStructNormal) ((S2F23Request) secsPacket).getDsper();//时间间隔
        DataStructNormal totsmp = (DataStructNormal) ((S2F23Request) secsPacket).getTotsmp();//总样本
        DataStructNormal repgsz = (DataStructNormal) ((S2F23Request) secsPacket).getRepgsz();//数据组大小
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", svidData);
        queryFiledsMap.put("trid", trid);
        queryFiledsMap.put("dsper", dsper);
        queryFiledsMap.put("totsmp", totsmp);
        queryFiledsMap.put("repgsz", repgsz);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F33Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F33);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        RPTIDListDataStruct[] values = (RPTIDListDataStruct[]) ((S2F33Request) secsPacket).getValues();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", values);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F35Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F35);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        CEIDListDataStruct[] values = (CEIDListDataStruct[]) ((S2F35Request) secsPacket).getValues();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", values);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F37Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F37);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal ceedDataStruct = (DataStructNormal) ((S2F37Request) secsPacket).getCEED();
        DataStructNormal[] values = SecsDataUtils.convertToDataStructs(((S2F37Request) secsPacket).getValues());
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", values);
        queryFiledsMap.put("ceed", ceedDataStruct);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS2F41Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S2F41);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal rcmdDataStruct = (DataStructNormal) ((S2F41Request) secsPacket).getRcmd();
        ParametersData[] values = ((S2F41Request) secsPacket).getValues();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", values);
        queryFiledsMap.put("rcmd", rcmdDataStruct);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS6F15Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S6F15);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal ceidDataStruct = (DataStructNormal) ((S6F15Request) secsPacket).getCeid();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", ceidDataStruct);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS6F19Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S6F19);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal rptidDataStruct = (DataStructNormal) ((S6F19Request) secsPacket).getRptid();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", rptidDataStruct);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS7F17Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S7F17);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal[] ppidDataStructs = SecsDataUtils.convertToDataStructs(((S7F17Request) secsPacket).getValues());
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", ppidDataStructs);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS7F19Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S7F19);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS7F23Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S7F23);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal ppidDataStruct = (DataStructNormal) ((S7F23Request) secsPacket).getPpid();
        DataStructNormal mdlnDataStruct = (DataStructNormal) ((S7F23Request) secsPacket).getMDLN();
        DataStructNormal softRevDataStruct = (DataStructNormal) ((S7F23Request) secsPacket).getSOFTREV();
        ProcessDataStruct[] processDataStructs = ((S7F23Request) secsPacket).getValues();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", processDataStructs);
        queryFiledsMap.put("ppid", ppidDataStruct);
        queryFiledsMap.put("mdln", mdlnDataStruct);
        queryFiledsMap.put("softRev", softRevDataStruct);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS7F25Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S7F25);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DataStructNormal ppidDataStruct = (DataStructNormal) ((S7F25Request) secsPacket).getPPID();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("params", ppidDataStruct);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS1F15Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S1F15);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS1F17Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S1F17);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static DefaultResponseMessage sendS5F3Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S5F3);//指令
        DataStructNormal aled = (DataStructNormal) ((S5F3Request) secsPacket).getAled();
        DataStructNormal alid = (DataStructNormal) ((S5F3Request) secsPacket).getAlid();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("aled", aled);
        queryFiledsMap.put("alid", alid);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }
    public static DefaultResponseMessage sendS5F5Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S5F5);//指令
        short dataType = ((S5F5Request) secsPacket).getDataType();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("dataType", dataType);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }
    public static DefaultResponseMessage sendS5F7Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S5F7);//指令
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }
    public static DefaultResponseMessage sendS7F1Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S7F1);//指令
        DataStructNormal ppid = (DataStructNormal) ((S7F1Request) secsPacket).getPPID();
        DataStructNormal length = (DataStructNormal) ((S7F1Request) secsPacket).getLENGTH();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("ppid", ppid);
        queryFiledsMap.put("length", length);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }
    public static DefaultResponseMessage sendS7F3Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S7F3);//指令
        DataStructNormal ppid = (DataStructNormal) ((S7F3Request) secsPacket).getPpid();
        byte[] body = ((S7F3Request) secsPacket).getData();
        short dataType = ((S7F3Request) secsPacket).getDataType();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("ppid", ppid);
        queryFiledsMap.put("ppbody", body);
        queryFiledsMap.put("dataType", dataType);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }
    public static DefaultResponseMessage sendS7F5Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S7F5);//指令
        DataStructNormal ppid = (DataStructNormal) ((S7F5Request) secsPacket).getPPID();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("ppid", ppid);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }
    public static DefaultResponseMessage sendS10F3Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S10F3);//指令
        DataStructNormal textDataStruct = (DataStructNormal) ((S10F3Request) secsPacket).getText();
        DataStructNormal tidDataStruct = (DataStructNormal) ((S10F3Request) secsPacket).getTid();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("tid", tidDataStruct);
        queryFiledsMap.put("text", textDataStruct);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }
    public static DefaultResponseMessage sendS10F5Request(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        DefaultServerConnectorListener defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        defaultRequestMessage.setProtocolCommond(ServerConstants.COMMAND_S10F5);//指令
        DataStructNormal[] values = SecsDataUtils.convertToDataStructs(((S10F5Request) secsPacket).getValues());
        DataStructNormal tidDataStruct = (DataStructNormal) ((S10F5Request) secsPacket).getTID();
        Map<String, Object> queryFiledsMap = new LinkedHashMap<>();
        queryFiledsMap.put("tid", tidDataStruct);
        queryFiledsMap.put("texts", values);
        defaultRequestMessage.setQueryfileds(queryFiledsMap);
        defaultRequestMessage.setRequestFunctionType(ServerConstants.REQUEST_FUNCTION_TYPE_DATA_COMMAND);//数据指令
        DefaultResponseMessage defaultResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
        return defaultResponseMessage;
    }

    public static void sendConnectCommand(DefaultServerConnectorListener defaultServerConnectorListener, String command) {
        DefaultRequestMessage defaultRequestMessage = new DefaultRequestMessage();
        defaultRequestMessage.setRequestFunctionType(command);
        defaultServerConnectorListener.onRequestMessage(defaultRequestMessage);
    }
}
