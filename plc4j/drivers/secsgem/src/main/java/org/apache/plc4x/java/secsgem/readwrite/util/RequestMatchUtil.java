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

import org.apache.plc4x.java.api.types.PlcRequestProtocolType;
import org.apache.plc4x.java.api.types.PlcRequestType;
import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.internal.DataHolder;
import org.apache.plc4x.java.secsgem.readwrite.model.*;
import org.apache.plc4x.java.secsgem.readwrite.protocol.SecsgemProtocolLogic;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode;
import org.apache.plc4x.java.spi.messages.DefaultPlcRequestMessage;
import org.apache.plc4x.java.spi.messages.ResItem;
import org.apache.plc4x.java.spi.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode.*;

public class RequestMatchUtil {
    public static final String FILED_PREFIX = "%";
    public static final String EIP_PROTOCOL = "eip";
    public static final String S7_PROTOCOL = "s7";
    public static final String FINS_PROTOCOL = "fins";
    public static final String MODBUS_PROTOCOL = "modbus";
    private static final Logger logger = LoggerFactory.getLogger(SecsgemProtocolLogic.class);

    /**
     * 根据需要查询的变量信息 组装请求消息（例：SVID  查询不同plc的svid值）
     *
     * @param dataDefinitionInfoList
     * @param queryParams
     * @return
     */
    public static DefaultPlcRequestMessage buildStatusValueReadRequestMessage(List<DataDefinitionInfo> dataDefinitionInfoList, List<String> queryParams) {
        DefaultPlcRequestMessage defaultPlcRequestMessage = null;
        boolean exit = true;
        if (dataDefinitionInfoList != null) {
            Map<String, Object> queryFields = new LinkedHashMap<>();
            for (int i = 0; i < queryParams.size(); i++) {
                String queryParam = queryParams.get(i);
                exit = dataDefinitionInfoList.stream().anyMatch(varibableItem -> String.valueOf(varibableItem.getDataId()).equals(queryParam));
                if (!exit) {
                    break;
                }
            }
            if (exit) {
                defaultPlcRequestMessage = new DefaultPlcRequestMessage();
                for (int i = 0; i < queryParams.size(); i++) {
                    String queryParam = queryParams.get(i);
                    DataDefinitionInfo dataDefinitionInfo = dataDefinitionInfoList.stream().filter(varibableItem -> String.valueOf(varibableItem.getDataId()).equals(queryParam)).findFirst().get();
                    String hVariableName = String.valueOf(dataDefinitionInfo.getDataId());
                    String pVariableName = dataDefinitionInfo.getLinkVariable();//plc定义的变量名称
                    String sDataType = RequestMatchUtil.parsetSecsDataType(dataDefinitionInfo.getFormat()).name();
                    defaultPlcRequestMessage.setRequestUrl(DataHolder.getInstance().getProtocalDriverUrl());
                    String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
                    int dataSize = Integer.parseInt(dataDefinitionInfo.getDataSize());
                    switch (protocol) {
                        case EIP_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.EIP);
                            int arrayIndex = 0;
                            String tagFinal = pVariableName;
                            if (pVariableName.contains("[") && dataSize > 1) {
                                tagFinal = pVariableName.substring(0, pVariableName.indexOf("["));
                                String index = pVariableName.substring(pVariableName.indexOf("[") + 1, pVariableName.indexOf("]"));
                                arrayIndex = Integer.parseInt(index);
                            }
                            if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                                queryFields.put(hVariableName + "-" + i, FILED_PREFIX + tagFinal + "[" + arrayIndex + "]:" + sDataType + ":" + dataSize);
                            } else {
                                queryFields.put(hVariableName + "-" + i, FILED_PREFIX + tagFinal + ":" + sDataType);
                            }
                            break;
                        case S7_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.S7);
                            if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                                queryFields.put(hVariableName + "-" + i, FILED_PREFIX + pVariableName + ":" + sDataType + "[" + dataSize + "]");
                            } else {
                                queryFields.put(hVariableName + "-" + i, FILED_PREFIX + pVariableName + ":" + sDataType);
                            }
                            break;
                        case FINS_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.FINS);
                            if (dataSize > 1) {
                                queryFields.put(hVariableName + "-" + i, pVariableName + ":" + sDataType + "[" + dataSize + "]");
                            } else {
                                queryFields.put(hVariableName + "-" + i, pVariableName + ":" + sDataType);
                            }
                            break;
                        case MODBUS_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.MODBUS);
                            if (dataSize > 1) {
                                queryFields.put(hVariableName + "-" + i, pVariableName + ":" + sDataType + "[" + dataSize + "]");
                            } else {
                                queryFields.put(hVariableName + "-" + i, pVariableName + ":" + sDataType);
                            }
                            break;
                    }
                }
                defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
                defaultPlcRequestMessage.setQueryfileds(queryFields);
            }
            if (queryParams.size() == 0) {
                defaultPlcRequestMessage = new DefaultPlcRequestMessage();
                for (int j = 0; j < dataDefinitionInfoList.size(); j++) {
                    DataDefinitionInfo varibableItem = dataDefinitionInfoList.get(j);
                    String hVariableName = String.valueOf(varibableItem.getDataId());
                    String pVariableName = varibableItem.getLinkVariable();//plc定义的变量名称
                    String sDataType = RequestMatchUtil.parsetSecsDataType(varibableItem.getFormat()).name();
                    defaultPlcRequestMessage.setRequestUrl(DataHolder.getInstance().getProtocalDriverUrl());
                    String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
                    int dataSize = Integer.parseInt(varibableItem.getDataSize());
                    switch (protocol) {
                        case EIP_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.EIP);
                            int arrayIndex = 0;
                            String tagFinal = pVariableName;
                            if (pVariableName.contains("[") && dataSize > 1) {
                                tagFinal = pVariableName.substring(0, pVariableName.indexOf("["));
                                String index = pVariableName.substring(pVariableName.indexOf("[") + 1, pVariableName.indexOf("]"));
                                arrayIndex = Integer.parseInt(index);
                            }
                            if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                                queryFields.put(hVariableName + "-" + j, FILED_PREFIX + tagFinal + "[" + arrayIndex + "]:" + sDataType + ":" + dataSize);
                            } else {
                                queryFields.put(hVariableName + "-" + j, FILED_PREFIX + tagFinal + ":" + sDataType);
                            }
                            break;
                        case S7_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.S7);
                            if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                                queryFields.put(hVariableName + "-" + j, FILED_PREFIX + pVariableName + ":" + sDataType + "[" + dataSize + "]");
                            } else {
                                queryFields.put(hVariableName + "-" + j, FILED_PREFIX + pVariableName + ":" + sDataType);
                            }
                            break;
                        case FINS_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.FINS);
                            if (dataSize > 1) {
                                queryFields.put(hVariableName + "-" + j, pVariableName + ":" + sDataType + "[" + dataSize + "]");
                            } else {
                                queryFields.put(hVariableName + "-" + j, pVariableName + ":" + sDataType);
                            }
                            break;
                        case MODBUS_PROTOCOL:
                            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.MODBUS);
                            if (dataSize > 1) {
                                queryFields.put(hVariableName + "-" + j, pVariableName + ":" + sDataType + "[" + dataSize + "]");
                            } else {
                                queryFields.put(hVariableName + "-" + j, pVariableName + ":" + sDataType);
                            }
                            break;

                    }
                }
                defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
                defaultPlcRequestMessage.setQueryfileds(queryFields);
            }
        }
        return defaultPlcRequestMessage;
    }

    /**
     * 根据streamFunction 组装请求消息
     *
     * @param dataDefinitionInfo 参数定义信息
     * @return
     */
    public static DefaultPlcRequestMessage buildReadRequestMessage(DataDefinitionInfo dataDefinitionInfo) {
        DefaultPlcRequestMessage defaultPlcRequestMessage = new DefaultPlcRequestMessage();
        defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultPlcRequestMessage.setRequestUrl(DataHolder.getInstance().getProtocalDriverUrl());
        Map<String, Object> queryFields = buildReadParam(dataDefinitionInfo);
        defaultPlcRequestMessage.setQueryfileds(queryFields);
        String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
        switch (protocol) {
            case EIP_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.EIP);
                break;
            case S7_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.S7);
                break;
            case FINS_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.FINS);
                break;
            case MODBUS_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.MODBUS);
                break;
        }
        return defaultPlcRequestMessage;
    }

    /**
     * 根据streamFunction 组装请求消息
     *
     * @param dataDefinitionInfos 参数定义信息
     * @return
     */
    public static DefaultPlcRequestMessage buildReadRequestMessages(List<DataDefinitionInfo> dataDefinitionInfos) {
        DefaultPlcRequestMessage defaultPlcRequestMessage = new DefaultPlcRequestMessage();
        defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultPlcRequestMessage.setRequestUrl(DataHolder.getInstance().getProtocalDriverUrl());
        Map<String, Object> queryFields = buildReadParams(dataDefinitionInfos);
        defaultPlcRequestMessage.setQueryfileds(queryFields);
        String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
        switch (protocol) {
            case EIP_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.EIP);
                break;
            case S7_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.S7);
                break;
            case FINS_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.FINS);
                break;
            case MODBUS_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.MODBUS);
                break;
        }
        return defaultPlcRequestMessage;
    }

    /**
     * 根据变量组装请求参数
     *
     * @param dataDefinitionInfo
     * @return
     */
    private static Map<String, Object> buildReadParam(DataDefinitionInfo dataDefinitionInfo) {
        String linkedVariableName = dataDefinitionInfo.getLinkVariable();//plc定义的变量名称
        String variableName = dataDefinitionInfo.getLabel();
        String sDataType = RequestMatchUtil.parsetSecsDataType(dataDefinitionInfo.getFormat()).name();
        int dataSize = Integer.parseInt(dataDefinitionInfo.getDataSize());
        Map<String, Object> queryFields = new LinkedHashMap<>();
        String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
        switch (protocol) {
            case EIP_PROTOCOL:
                if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                    queryFields.put(variableName, FILED_PREFIX + linkedVariableName + "[0]:" + sDataType + ":" + dataSize);
                } else {
                    queryFields.put(variableName, FILED_PREFIX + linkedVariableName + ":" + sDataType);
                }
                break;
            case S7_PROTOCOL:
                if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                    queryFields.put(variableName, FILED_PREFIX + linkedVariableName + ":" + sDataType + "[" + dataSize + "]");
                } else {
                    queryFields.put(variableName, FILED_PREFIX + linkedVariableName + ":" + sDataType);
                }
                break;
            case FINS_PROTOCOL:
                if (dataSize > 1) {
                    queryFields.put(variableName, linkedVariableName + ":" + sDataType + "[" + dataSize + "]");
                } else {
                    queryFields.put(variableName, linkedVariableName + ":" + sDataType);
                }
                break;
            case MODBUS_PROTOCOL:
                if (dataSize > 1) {
                    queryFields.put(variableName, linkedVariableName + ":" + sDataType + "[" + dataSize + "]");
                } else {
                    queryFields.put(variableName, linkedVariableName + ":" + sDataType);
                }
                break;
        }
        return queryFields;
    }

    /**
     * 根据变量组装请求参数
     *
     * @param dataDefinitionInfos
     * @return
     */
    private static Map<String, Object> buildReadParams(List<DataDefinitionInfo> dataDefinitionInfos) {
        String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
        Map<String, Object> queryFields = new LinkedHashMap<>();
        for (int i = 0; i < dataDefinitionInfos.size(); i++) {
            String variableName = dataDefinitionInfos.get(i).getLabel();
            String linkedVariableName = dataDefinitionInfos.get(i).getLinkVariable();//plc定义的变量名称
            String sDataType = RequestMatchUtil.parsetSecsDataType(dataDefinitionInfos.get(i).getFormat()).name();
            int dataSize = Integer.parseInt(dataDefinitionInfos.get(i).getDataSize());
            switch (protocol) {
                case EIP_PROTOCOL:
                    if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                        queryFields.put(variableName, FILED_PREFIX + linkedVariableName + "[0]:" + sDataType + ":" + dataSize);
                    } else {
                        queryFields.put(variableName, FILED_PREFIX + linkedVariableName + ":" + sDataType);
                    }
                    break;
                case S7_PROTOCOL:
                    if (dataSize > 1 && !sDataType.equals(SecsDataTypeCode.STRING.name())) {
                        queryFields.put(variableName, FILED_PREFIX + linkedVariableName + ":" + sDataType + "[" + dataSize + "]");
                    } else {
                        queryFields.put(variableName, FILED_PREFIX + linkedVariableName + ":" + sDataType);
                    }
                    break;
                case FINS_PROTOCOL:
                    if (dataSize > 1) {
                        queryFields.put(variableName, linkedVariableName + ":" + sDataType + "[" + dataSize + "]");
                    } else {
                        queryFields.put(variableName, linkedVariableName + ":" + sDataType);
                    }
                    break;
                case MODBUS_PROTOCOL:
                    if (dataSize > 1) {
                        queryFields.put(variableName, linkedVariableName + ":" + sDataType + "[" + dataSize + "]");
                    } else {
                        queryFields.put(variableName, linkedVariableName + ":" + sDataType);
                    }
                    break;
            }
        }
        return queryFields;
    }

    private static Map<String, Map<String, Object>> buildWriteParams(List<WriteParam> writeParams) {
        Map<String, Map<String, Object>> tags = new LinkedHashMap<>();
        for (int i = 0; i < writeParams.size(); i++) {
            WriteParam writeParam = writeParams.get(i);
            Map<String, Object> writeFields = new LinkedHashMap<>();
            int dataSize = writeParam.getDataSize();
            String dataType = writeParam.getType();
            String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
            switch (protocol) {
                case S7_PROTOCOL:
                    if (dataType.equals(SecsDataTypeCode.STRING.name())) {
                        writeFields.put(FILED_PREFIX + writeParam.getName() + ":" + writeParam.getType() + "(" + writeParam.getValue().toString().length() + ")", writeParam.getValue());
                    } else {
                        writeFields.put(FILED_PREFIX + writeParam.getName() + ":" + writeParam.getType(), writeParam.getValue());
                    }
                    break;
                case EIP_PROTOCOL:
                    writeFields.put(FILED_PREFIX + writeParam.getName() + ":" + writeParam.getType(), writeParam.getValue());
                    break;
                case FINS_PROTOCOL:
                    writeFields.put(writeParam.getName() + ":" + writeParam.getType(), writeParam.getValue());
                    break;
                case MODBUS_PROTOCOL:
                    if (dataType.equals(SecsDataTypeCode.STRING.name())) {
                        writeFields.put(writeParam.getName() + ":" + dataType + "[" + writeParam.getValue().toString().length() + "]", writeParam.getValue());
                    } else {
                        writeFields.put(writeParam.getName() + ":" + dataType, writeParam.getValue());
                    }
                    break;

            }
            tags.put(writeParam.getName(), writeFields);
        }
        return tags;
    }

    public static DefaultPlcRequestMessage buildWriteRequestMessage(List<WriteParam> writeParams) {
        DefaultPlcRequestMessage defaultPlcRequestMessage = new DefaultPlcRequestMessage();
        defaultPlcRequestMessage.setRequestUrl(DataHolder.getInstance().getProtocalDriverUrl());
        defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
        Map<String, Map<String, Object>> tags = buildWriteParams(writeParams);
        defaultPlcRequestMessage.setWriteFileds(tags);
        String protocol = DataHolder.getInstance().getProtocalDriverUrl().split(":")[0];
        switch (protocol) {
            case EIP_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.EIP);
                break;
            case S7_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.S7);
                break;
            case FINS_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.FINS);
                break;
            case MODBUS_PROTOCOL:
                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.MODBUS);
                break;
        }
        return defaultPlcRequestMessage;
    }


    /**
     * 数据格式校验
     *
     * @param dataType
     * @param format
     * @return
     */
    public static boolean checkDataType(SecsDataTypeCode dataType, String format) {
        boolean isFit = false;
        switch (dataType) {
            case LIST:
                if (format.equals("L")) {
                    isFit = true;
                }
                break;
            case BYTE:
                if (format.equals("B")) {
                    isFit = true;
                }
                break;
            case BOOL:
                if (format.equals("BOOLEAN")) {
                    isFit = true;
                }
                break;
            case STRING:
                if (format.equals("A")) {
                    isFit = true;
                }
                break;
            case SINT:
                if (format.equals("I1")) {
                    isFit = true;
                }
                break;
            case INT:
                if (format.equals("I2")) {
                    isFit = true;
                }
                break;
            case DINT:
                if (format.equals("I4")) {
                    isFit = true;
                }
                break;
            case LINT:
                if (format.equals("I8")) {
                    isFit = true;
                }
                break;
            case REAL:
                if (format.equals("F4")) {
                    isFit = true;
                }
                break;
            case LREAL:
                if (format.equals("F8")) {
                    isFit = true;
                }
                break;
            case USINT:
                if (format.equals("U1")) {
                    isFit = true;
                }
                break;
            case UDINT:
                if (format.equals("U4")) {
                    isFit = true;
                }
                break;
            case UINT:
                if (format.equals("U2")) {
                    isFit = true;
                }
                break;
            case ULINT:
                if (format.equals("U8")) {
                    isFit = true;
                }
                break;
        }
        return isFit;
    }

    public static SecsDataTypeCode parsetSecsDataType(String format) {
        SecsDataTypeCode secsDataTypeCode = null;
        switch (format) {
            case "L":
                secsDataTypeCode = SecsDataTypeCode.LIST;
                break;
            case "B":
                secsDataTypeCode = SecsDataTypeCode.BYTE;
                break;
            case "BOOLEAN":
                secsDataTypeCode = SecsDataTypeCode.BOOL;
                break;
            case "A":
                secsDataTypeCode = SecsDataTypeCode.STRING;
                break;
            case "I1":
                secsDataTypeCode = SecsDataTypeCode.SINT;
                break;
            case "I2":
                secsDataTypeCode = SecsDataTypeCode.INT;
                break;
            case "I4":
                secsDataTypeCode = SecsDataTypeCode.DINT;
                break;
            case "I8":
                secsDataTypeCode = SecsDataTypeCode.LINT;
                break;
            case "F4":
                secsDataTypeCode = SecsDataTypeCode.REAL;
                break;
            case "F8":
                secsDataTypeCode = SecsDataTypeCode.LREAL;
                break;
            case "U1":
                secsDataTypeCode = SecsDataTypeCode.USINT;
                break;
            case "U4":
                secsDataTypeCode = SecsDataTypeCode.UDINT;
                break;
            case "U8":
                secsDataTypeCode = SecsDataTypeCode.ULINT;
                break;
            case "U2":
                secsDataTypeCode = SecsDataTypeCode.UINT;
                break;
        }
        return secsDataTypeCode;
    }

    /**
     * 根据返回值类型转换结果
     *
     * @param resItem
     * @return
     */
    public static DataStruct buildResItemDataStruct(ResItem resItem) {
        Object value = resItem.getValue();
        DataStruct vidLen = buildItemDataStruct(value);
        return vidLen;
    }

    public static DataStruct buildResItemDataStructByType(ResItem resItem) {
        Object value = resItem.getValue();
        String valueType = resItem.getValueType();
        DataStruct vidLen = null;
        SecsDataTypeCode secsDataTypeCode = null;
        switch (valueType) {
            case "L":
                PlcList plcList = ((PlcList) value);
                List<byte[]> list = new ArrayList<>();
                int resultLength = 0;
                SecsDataTypeCode dataType = null;
                for (int i = 0; i < plcList.getLength(); i++) {
                    DataStructNormal dataStruct = (DataStructNormal) buildItemDataStruct(plcList.getIndex(i));
                    dataType = dataStruct.getDataType();
                    byte[] data = dataStruct.getData();
                    byte[] dataBytes = {(byte) dataType.getValue(), (byte) data.length};
                    byte[] mergedArray = new byte[data.length + dataBytes.length];
                    System.arraycopy(dataBytes, 0, mergedArray, 0, dataBytes.length); // 复制第一个字节数组到合并后的数组
                    System.arraycopy(data, 0, mergedArray, dataBytes.length, data.length); // 复制第二个字节数组到合并后的数组
                    list.add(mergedArray);
                    resultLength += mergedArray.length;
                }
                byte[] result = new byte[resultLength];
                int index = 0;
                for (int j = 0; j < list.size(); j++) {
                    byte[] bytes = list.get(j);
                    for (int k = 0; k < bytes.length; k++) {
                        result[index] = bytes[k];
                        index++;
                    }
                }
                int size = ((PlcList) value).getLength() * dataType.getSize();
                if (size > 255 && size <= 65535) {
                    vidLen = new DataStructMedium(LIST2, (short) ((PlcList) value).getLength(), result);
                } else if (size > 65535) {
                    vidLen = new DataStructLong(LIST3, (short) ((PlcList) value).getLength(), result);
                } else {
                    vidLen = new DataStructNormal(LIST, (short) ((PlcList) value).getLength(), result);
                }
                break;
            case "B":
                vidLen = new DataStructNormal(BYTE, BYTE.getSize(), new byte[]{((PlcBYTE) value).getByte()});
                break;
            case "BOOLEAN":
                vidLen = new DataStructNormal(BOOL, BOOL.getSize(), new byte[]{((PlcBOOL) value).getByte()});
                break;
            case "A":
                vidLen = new DataStructNormal(SecsDataTypeCode.STRING, (short) String.valueOf(value).substring(1, String.valueOf(value).length() - 1).getBytes().length, String.valueOf(value).substring(1, String.valueOf(value).length() - 1).getBytes());
                break;
            case "I1":
                vidLen = new DataStructNormal(SINT, (short) ((PlcSINT) value).getBytes().length, ((PlcSINT) value).getBytes());
                break;
            case "I2":
                vidLen = new DataStructNormal(SecsDataTypeCode.INT, (short) ((PlcINT) value).getBytes().length, ((PlcINT) value).getBytes());
                break;
            case "I4":
                vidLen = new DataStructNormal(SecsDataTypeCode.LINT, (short) ((PlcLINT) value).getBytes().length, ((PlcLINT) value).getBytes());
                break;
            case "I8":
                vidLen = new DataStructNormal(DINT, (short) ((PlcDINT) value).getBytes().length, ((PlcDINT) value).getBytes());
                break;
            case "F4":
                vidLen = new DataStructNormal(SecsDataTypeCode.REAL, (short) ((PlcREAL) value).getBytes().length, ((PlcREAL) value).getBytes());
                break;
            case "F8":
                vidLen = new DataStructNormal(SecsDataTypeCode.LREAL, (short) ((PlcLREAL) value).getBytes().length, ((PlcLREAL) value).getBytes());
                break;
            case "U1":
                vidLen = new DataStructNormal(SecsDataTypeCode.USINT, (short) ((PlcUSINT) value).getBytes().length, ((PlcUSINT) value).getBytes());
                break;
            case "U4":
                vidLen = new DataStructNormal(SecsDataTypeCode.UDINT, (short) ((PlcUDINT) value).getBytes().length, ((PlcUDINT) value).getBytes());
                break;
            case "U8":
                vidLen = new DataStructNormal(SecsDataTypeCode.ULINT, (short) ((PlcULINT) value).getBytes().length, ((PlcULINT) value).getBytes());
                break;
            case "U2":
                vidLen = new DataStructNormal(SecsDataTypeCode.UINT, (short) ((PlcUINT) value).getBytes().length, ((PlcUINT) value).getBytes());
                break;
        }
        if (vidLen == null) {
            logger.warn("Failed to get the value");
        }
        return vidLen;
    }

    private static DataStruct buildItemDataStruct(Object value) {
        DataStruct vidLen = null;
        if (value instanceof PlcBOOL) {
            vidLen = new DataStructNormal(BOOL, BOOL.getSize(), new byte[]{((PlcBOOL) value).getByte()});
        }
        if (value instanceof PlcBYTE) {
            vidLen = new DataStructNormal(BYTE, BYTE.getSize(), new byte[]{((PlcBYTE) value).getByte()});
        }
        if (value instanceof PlcSTRING) {
            vidLen = new DataStructNormal(SecsDataTypeCode.STRING, (short) String.valueOf(value).substring(1, String.valueOf(value).length() - 1).getBytes().length, String.valueOf(value).substring(1, String.valueOf(value).length() - 1).getBytes());
        }
        if (value instanceof PlcSINT) {
            vidLen = new DataStructNormal(SINT, (short) ((PlcSINT) value).getBytes().length, ((PlcSINT) value).getBytes());
        }
        if (value instanceof PlcINT) {
            vidLen = new DataStructNormal(SecsDataTypeCode.INT, (short) ((PlcINT) value).getBytes().length, ((PlcINT) value).getBytes());
        }
        if (value instanceof PlcLINT) {
            vidLen = new DataStructNormal(SecsDataTypeCode.LINT, (short) ((PlcLINT) value).getBytes().length, ((PlcLINT) value).getBytes());
//            vidLen = new DataStruct(SecsDataTypeCode.STRING, (short) (String.valueOf(value).getBytes().length), String.valueOf(value).getBytes());
        }
        if (value instanceof PlcDINT) {
            vidLen = new DataStructNormal(DINT, (short) ((PlcDINT) value).getBytes().length, ((PlcDINT) value).getBytes());
        }
        if (value instanceof PlcREAL) {
            vidLen = new DataStructNormal(SecsDataTypeCode.REAL, (short) ((PlcREAL) value).getBytes().length, ((PlcREAL) value).getBytes());
        }
        if (value instanceof PlcLREAL) {
            vidLen = new DataStructNormal(SecsDataTypeCode.LREAL, (short) ((PlcLREAL) value).getBytes().length, ((PlcLREAL) value).getBytes());
        }
        if (value instanceof PlcUSINT) {
            vidLen = new DataStructNormal(SecsDataTypeCode.USINT, (short) ((PlcUSINT) value).getBytes().length, ((PlcUSINT) value).getBytes());
        }
        if (value instanceof PlcUINT) {
            vidLen = new DataStructNormal(SecsDataTypeCode.UINT, (short) ((PlcUINT) value).getBytes().length, ((PlcUINT) value).getBytes());
        }
        if (value instanceof PlcULINT) {
            vidLen = new DataStructNormal(SecsDataTypeCode.ULINT, (short) ((PlcULINT) value).getBytes().length, ((PlcULINT) value).getBytes());
        }
        if (value instanceof PlcUDINT) {
            vidLen = new DataStructNormal(SecsDataTypeCode.UDINT, (short) ((PlcUDINT) value).getBytes().length, ((PlcUDINT) value).getBytes());
        }
        if (value instanceof PlcList) {
            PlcList plcList = ((PlcList) value);
            List<byte[]> list = new ArrayList<>();
            int resultLength = 0;
            SecsDataTypeCode dataType = null;
            for (int i = 0; i < plcList.getLength(); i++) {
                DataStructNormal dataStruct = (DataStructNormal) buildItemDataStruct(plcList.getIndex(i));
                dataType = dataStruct.getDataType();
                byte[] data = dataStruct.getData();
                byte[] dataBytes = {(byte) dataType.getValue(), (byte) data.length};
                byte[] mergedArray = new byte[data.length + dataBytes.length];
                System.arraycopy(dataBytes, 0, mergedArray, 0, dataBytes.length); // 复制第一个字节数组到合并后的数组
                System.arraycopy(data, 0, mergedArray, dataBytes.length, data.length); // 复制第二个字节数组到合并后的数组
                list.add(mergedArray);
                resultLength += mergedArray.length;
            }
            byte[] result = new byte[resultLength];
            int index = 0;
            for (int j = 0; j < list.size(); j++) {
                byte[] bytes = list.get(j);
                for (int k = 0; k < bytes.length; k++) {
                    result[index] = bytes[k];
                    index++;
                }
            }
            int size = ((PlcList) value).getLength() * dataType.getSize();
            if (size > 255 && size <= 65535) {
                vidLen = new DataStructMedium(LIST2, (short) ((PlcList) value).getLength(), result);
            } else if (size > 65535) {
                vidLen = new DataStructLong(LIST3, (short) ((PlcList) value).getLength(), result);
            } else {
                vidLen = new DataStructNormal(LIST, (short) ((PlcList) value).getLength(), result);
            }
        }
        if (vidLen == null) {
            logger.warn("Failed to get the value");
        }
        return vidLen;
    }

}
