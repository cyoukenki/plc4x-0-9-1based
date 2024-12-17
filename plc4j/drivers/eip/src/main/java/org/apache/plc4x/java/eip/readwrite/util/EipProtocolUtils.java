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

import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.api.value.PlcValue;
import org.apache.plc4x.java.eip.readwrite.configuration.EIPConfiguration;
import org.apache.plc4x.java.eip.readwrite.entity.EipRequestEntity;
import org.apache.plc4x.java.eip.readwrite.field.EipField;
import org.apache.plc4x.java.eip.readwrite.types.CIPDataTypeCode;
import org.apache.plc4x.java.spi.values.PlcList;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EipProtocolUtils {
    public EipProtocolUtils() {
    }

    /**
     * 获取单个读取请求报文超长的集合
     *
     * @param readRequest
     * @param configuration
     * @return
     */
    public static List<String> getReadSingleRespOverlengthParams(PlcReadRequest readRequest, EIPConfiguration configuration) {
        List<String> overLengthParamsList = new ArrayList<>();
        List<PlcField> plcFields = readRequest.getFields();
        List<String> filedNames = Arrays.asList(readRequest.getFieldNames().toArray(new String[0]));
        for (int i = 0; i < plcFields.size(); i++) {
            PlcField plcField = plcFields.get(i);
            EipField eipField = (EipField) plcField;
            int dataByteLength = calculateSingleReadRespDataLength(eipField);//计算数据长度  用于判断单个tag报文返回结果是否超长
            if (dataByteLength > configuration.getDataPackageByteLength()) {
                overLengthParamsList.add(filedNames.get(i));
            }
        }
        return overLengthParamsList;
    }

    /**
     * 去除单个读取请求报文超长的集合结果
     *
     * @param readRequest
     * @param configuration
     * @return
     */
    public static List<List<String>> getReadNoSingleRespOverlengthParams(PlcReadRequest readRequest, EIPConfiguration configuration) {
        List<List<String>> splitRequestParams = splitReadRequestParams(readRequest);
        List<List<String>> splitRequestParamList = new ArrayList<>();
        for (int i = 0; i < splitRequestParams.size(); i++) {
            List<String> eipRequestEntitieList = new ArrayList<>();
            List<String> eipRequestEntities = splitRequestParams.get(i);
            for (int j = 0; j < eipRequestEntities.size(); j++) {
                String fieldName = eipRequestEntities.get(j);
                final EipField field = (EipField) readRequest.getField(fieldName);
                int dataByteLength = calculateSingleReadRespDataLength(field);//计算数据长度  用于判断单个tag报文返回结果是否超长
                if (dataByteLength < configuration.getDataPackageByteLength()) {
                    eipRequestEntitieList.add(fieldName);
                }
            }
            splitRequestParamList.add(eipRequestEntitieList);
        }
        return splitRequestParamList;
    }

    /**
     * 分割请求参数集合（根据请求报文长度计算）
     *
     * @param readRequest
     * @return
     */
    public static List<List<String>> splitReadRequestParams(PlcReadRequest readRequest) {
        List<PlcField> plcFields = readRequest.getFields();
        List<String> filedNames = Arrays.asList(readRequest.getFieldNames().toArray(new String[0]));
        List<List<String>> requestFiledsList = new ArrayList<>();
        List<String> eipRequestFieldList = new ArrayList<>();
        int packageSize = 0;
        int commmonHeaderSize = 0;
        if (plcFields.size() > 1) {
            //按照MultipleServiceRequest 计算报文长度
            //6f 00 2a 00 6d 01 07 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 00 00 00 00 b2 00 1a 00 52 02 20 06 24 01 05 9d
            //24 00
            //0a //MultipleServiceRequest
            //02 //RequestPathSize
            //20 02 24 01 //RequestPath
            //02 00 //serviceNb
            //06 00 12 00 //offsets读取两个变量地址 偏移量
            //4c 04 91 06 50 61 72 61 6d 32 01 00
            //4c 04 91 06 50 61 72 61 6d 31 01 00
            //01 00 01 00
            int prefixBytelength = 48;
            int dataLengthByte = 2;
            int serviceTypeByte = 1;
            int requestPathSizeByte = 1;
            int requestPathByte = 4;
            int serviceNbByte = 2;
//            int offsetsAddrByte = plcFields.size() * 2;//偏移量
            int cipReadRequestByteLength = 0;
            int offsetIndex = 0;
            commmonHeaderSize = prefixBytelength + dataLengthByte + serviceTypeByte + requestPathSizeByte + requestPathByte + serviceNbByte;
            packageSize += commmonHeaderSize;
            for (int i = 0; i < plcFields.size(); i++) {
                PlcField plcField = plcFields.get(i);
                EipField eipField = (EipField) plcField;
                String tag = eipField.getTag();//获取读取的变量名称
                //根据变量名称计算Byte长度
                int serviceTypeByte1 = 1;//服务标识
                int requestPathSizeByte1 = 1;//请求路径
                int dataTypeByte1 = 1;//扩展符号
                int dataConentByte = tag.length();//
                int dataCountByte = 0;
                if (eipField.getElementNb() > 1) {
                    //elementNb>1情况，增加长度标识位长度
                    dataCountByte = 2;
                }
                int elementNbByte = 2;
//                int offsetsAddrByte = (offsetIndex + 1) * 2;//偏移量
                int offsetsAddrByte = 0;
                offsetsAddrByte += 2;//offset list 占用长度
                cipReadRequestByteLength = serviceTypeByte1 + requestPathSizeByte1 + dataTypeByte1 + dataConentByte + elementNbByte + offsetsAddrByte + dataCountByte;
                packageSize += cipReadRequestByteLength;
                int endBytelength = 4;
                packageSize += endBytelength;
                //判断如果packageSize超出限定范围 ，拆分read参数
                if (packageSize > EIPConfiguration.UCMM_PACKAGE_LENGTH_READ) {
                    packageSize = 0;
                    offsetIndex = 0;
                    i--;
                    requestFiledsList.add(new ArrayList<>(eipRequestFieldList));
                    eipRequestFieldList.clear();
                    packageSize += commmonHeaderSize;
                } else if (i == plcFields.size() - 1) {
                    eipRequestFieldList.add(filedNames.get(i));
                    requestFiledsList.add(new ArrayList<>(eipRequestFieldList));
                } else {
                    offsetIndex++;
                    eipRequestFieldList.add(filedNames.get(i));
                }
            }
        } else {
            PlcField plcField = plcFields.get(0);
            eipRequestFieldList.add(filedNames.get(0));
            requestFiledsList.add(eipRequestFieldList);
        }
        return requestFiledsList;
    }

    public static int calculateSingleReadRespDataLength(EipField eipField) {
        //数据长度  即数据类型对应的字节长度
        int arrayIndex = 0;
        String tag = eipField.getTag();
        int packageSize = 0;
        int dataSize;
        int elementNb = eipField.getElementNb();
        boolean isArray = false;
        if (tag.contains("[")) {
            //%TEST2[0]:DINT:200
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);//数组索引
        }
        if (elementNb >= 1) {
            //数组情况
            dataSize = eipField.getType().getSize() * elementNb;
        } else {
            dataSize = eipField.getType().getSize();
        }
        packageSize += dataSize;
        return packageSize;
    }

    public static List<String> getWriteSingleRespOverlengthParams(PlcWriteRequest writeRequest, EIPConfiguration configuration) {
        List<String> overLengthParamsList = new ArrayList<>();
        List<PlcField> plcFields = writeRequest.getFields();
        List<String> filedNames = Arrays.asList(writeRequest.getFieldNames().toArray(new String[0]));
        for (int i = 0; i < plcFields.size(); i++) {
            PlcField plcField = plcFields.get(i);
            EipField eipField = (EipField) plcField;
            String tag = eipField.getTag();
            final EipField field = (EipField) writeRequest.getField(filedNames.get(i));
            final PlcValue value = writeRequest.getPlcValue(filedNames.get(i));
            int elements = 1;
            if (field.getElementNb() > 1) {
                elements = field.getElementNb();
            }
            boolean isArray = false;
            String tagIsolated = tag;
            if (tag.contains("[")) {
                isArray = true;
                tagIsolated = tag.substring(0, tag.indexOf("["));
            }
            int dataLength = (tagIsolated.length() + 2 + (tagIsolated.length() % 2) + (isArray ? 2 : 0));
            byte requestPathSize = (byte) (dataLength / 2);
            byte[] data = encodeValue(value, field.getType(), (short) elements);
            int dataByteLength = dataLength + data.length;//计算数据长度  用于判断单个tag报文写入超长
            if (dataByteLength > configuration.getDataPackageByteLength()) {
                overLengthParamsList.add(filedNames.get(i));
            }
        }
        return overLengthParamsList;


    }

    public static List<List<String>> getWriteNoSingleRespOverlengthParams(PlcWriteRequest writeRequest, EIPConfiguration configuration) {
        List<List<String>> splitRequestParams = splitWriteRequestParams(writeRequest);
        List<List<String>> splitRequestParamList = new ArrayList<>();
        for (int i = 0; i < splitRequestParams.size(); i++) {
            List<String> eipRequestEntitieList = new ArrayList<>();
            List<String> eipRequestEntities = splitRequestParams.get(i);
            for (int j = 0; j < eipRequestEntities.size(); j++) {
                final EipField field = (EipField) writeRequest.getField(eipRequestEntities.get(j));
                int dataByteLength = calculateSingleReadRespDataLength(field);//计算数据长度  用于判断单个tag报文返回结果是否超长
                if (dataByteLength < configuration.getDataPackageByteLength()) {
                    eipRequestEntitieList.add(eipRequestEntities.get(j));
                }
            }
            splitRequestParamList.add(eipRequestEntitieList);
        }
        return splitRequestParamList;
    }

    private static List<List<String>> splitWriteRequestParams(PlcWriteRequest writeRequest) {
        List<PlcField> plcFields = writeRequest.getFields();
        List<String> filedNames = Arrays.asList(writeRequest.getFieldNames().toArray(new String[0]));
        List<List<String>> requestFiledsList = new ArrayList<>();
        List<String> eipRequestFieldList = new ArrayList<>();
        int packageSize = 0;
        int commmonHeaderSize = 0;
        if (plcFields.size() > 1) {
            //按照MultipleServiceRequest 计算报文长度
//            6f 00 4a 00 6d 01 0a 00 00 00 00 00 00 00 00 00
//            00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00
//            00 00 00 00 b2 00 3a 00 52 02 20 06 24 01 05 9d
//            2c 00
//            0a 02
//            20 02 24 01
//            02 00
//            06 00 16 00 //偏移量
//            4d 04 91 06 50 61 72 61 6d 34 c3 00 01 00 17 00
//            4d 04 91 06 50 61 72 61 6d 33 c3 00 01 00 17 00 01 00
//            01 00
            int prefixBytelength = 48;
            int dataLengthByte = 2;
            int serviceTypeByte = 1;
            int requestPathSizeByte = 1;
            int requestPathByte = 4;
            int serviceNbByte = 2;
            int cipReadRequestByteLength = 0;
            commmonHeaderSize = prefixBytelength + dataLengthByte + serviceTypeByte + requestPathSizeByte + requestPathByte + serviceNbByte;
            packageSize += commmonHeaderSize;
            for (int i = 0; i < plcFields.size(); i++) {
                PlcField plcField = plcFields.get(i);
                EipField field = (EipField) plcField;
                String tag = field.getTag();//获取读取的变量名称
                final PlcValue value = writeRequest.getPlcValue(filedNames.get(i));
                int elements = 1;
                if (field.getElementNb() > 1) {
                    elements = field.getElementNb();
                }
                boolean isArray = false;
                String tagIsolated = tag;
                if (tag.contains("[")) {
                    isArray = true;
                    tagIsolated = tag.substring(0, tag.indexOf("["));
                }
                int dataLength = (tagIsolated.length() + 2 + (tagIsolated.length() % 2) + (isArray ? 2 : 0));
                byte[] data = encodeValue(value, field.getType(), (short) elements);
                int dataByteLength = dataLength + data.length;//计算数据长度
                //根据变量名称计算Byte长度
                int serviceTypeByte1 = 1;//服务标识
                int requestPathSizeByte1 = 1;//请求路径
                int dataTypeByte1 = 1;//扩展符号
//                int dataConentByte = tag.length();//
                int dataCountByte = 0;
                if (field.getElementNb() > 1) {
                    //elementNb>1情况，增加长度标识位长度
                    dataCountByte = 2;
                }
                int elementNbByte = 2;
//                int offsetsAddrByte = (offsetIndex + 1) * 2;//偏移量
                int offsetsAddrByte = 0;
                offsetsAddrByte += 2;//offset list 占用长度
                cipReadRequestByteLength = serviceTypeByte1 + requestPathSizeByte1 + dataTypeByte1 + elementNbByte + offsetsAddrByte + dataCountByte +dataByteLength;
                packageSize += cipReadRequestByteLength;
                int endBytelength = 4;
                packageSize += endBytelength;
                //判断如果packageSize超出限定范围 ，拆分write参数
                if (packageSize > EIPConfiguration.UCMM_PACKAGE_LENGTH_WRITE) {
                    packageSize = 0;
                    i--;
                    requestFiledsList.add(new ArrayList<>(eipRequestFieldList));
                    eipRequestFieldList.clear();
                    packageSize += commmonHeaderSize;
                } else if (i == plcFields.size() - 1) {
                    eipRequestFieldList.add(filedNames.get(i));
                    requestFiledsList.add(new ArrayList<>(eipRequestFieldList));
                } else {
                    eipRequestFieldList.add(filedNames.get(i));
                }
            }
        } else {
            eipRequestFieldList.add(filedNames.get(0));
            requestFiledsList.add(eipRequestFieldList);
        }
        return requestFiledsList;
    }


    private void splitRequestParams(Map<Integer, List<EipField>> eipFiledMap, List<PlcField> plcFields) {
        List<PlcField> plcFieldsCopy = new ArrayList<>(plcFields);
        int packageSize = 0;
        if (plcFields.size() > 1) {
            //按照MultipleServiceRequest 计算报文长度
            //6f 00 2a 00 6d 01 07 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 02 00 00 00 00 00 b2 00 1a 00 52 02 20 06 24 01 05 9d
            //24 00
            //0a //MultipleServiceRequest
            //02 //RequestPathSize
            //20 02 24 01 //RequestPath
            //02 00 //serviceNb
            //06 00 12 00 //offsets读取两个变量地址 偏移量
            //4c 04 91 06 50 61 72 61 6d 32 01 00
            //4c 04 91 06 50 61 72 61 6d 31 01 00
            //01 00 01 00
            int prefixBytelength = 48;
            int dataLengthByte = 2;
            int serviceTypeByte = 1;
            int requestPathSizeByte = 1;
            int requestPathByte = 4;
            int serviceNbByte = 2;
//            int offsetsAddrByte = plcFields.size() * 2;//偏移量
            int cipReadRequestByteLength = 0;
            packageSize = prefixBytelength + dataLengthByte + serviceTypeByte + requestPathSizeByte + requestPathByte + serviceNbByte;
            List<EipField> eipFieldList = new ArrayList<>();
            for (int i = 0; i < plcFields.size(); i++) {
                PlcField plcField = plcFields.get(i);
                EipField eipField = (EipField) plcField;
                String tag = eipField.getTag();//获取读取的变量名称
                //根据变量名称计算Byte长度
                int serviceTypeByte1 = 1;//服务标识
                int requestPathSizeByte1 = 1;//请求路径
                int dataTypeByte1 = 1;//扩展符号
                int dataConentByte = tag.length();//
                int dataCountByte = 0;
                if (eipField.getElementNb() > 1) {
                    //elementNb>1情况，增加长度标识位长度
                    dataCountByte = 2;
                }
                int elementNbByte = 2;
                int offsetsAddrByte = (i + 1) * 2;//偏移量
                cipReadRequestByteLength = serviceTypeByte1 + requestPathSizeByte1 + dataTypeByte1 + dataConentByte + elementNbByte + offsetsAddrByte + dataCountByte;
                packageSize += cipReadRequestByteLength;
                int endBytelength = 4;
                packageSize += endBytelength;
                //判断如果packageSize超出限定范围 ，拆分read参数
                if (packageSize > EIPConfiguration.UCMM_PACKAGE_LENGTH_READ) {
                    eipFiledMap.put(eipFiledMap.size(), eipFieldList);
                    splitRequestParams(eipFiledMap, plcFieldsCopy);
                    break;
                } else if (i == plcFields.size() - 1) {
                    eipFieldList.add(eipField);
                    eipFiledMap.put(eipFiledMap.size(), eipFieldList);
                } else {
                    eipFieldList.add(eipField);
                    plcFieldsCopy.remove(plcField);
                }
            }
        }
    }

    private int calculateReadReqPackageLength(List<PlcField> plcFields) {
        int packageSize = 0;
        int prefixBytelength = 48;
        int dataLengthByte = 2;

        if (plcFields.size() > 1) {
            int serviceTypeByte = 1;
            int requestPathSizeByte = 1;
            int requestPathByte = 4;
            int serviceNbByte = 2;
            int offsetsAddrByte = plcFields.size() * 2;
            packageSize += (serviceTypeByte + requestPathSizeByte + requestPathByte + serviceNbByte + offsetsAddrByte);
        }
        packageSize += (prefixBytelength + dataLengthByte);
        int cipReadRequestByteLength = 0;
        for (int i = 0; i < plcFields.size(); i++) {
            PlcField plcField = plcFields.get(i);
            EipField eipField = (EipField) plcField;
            String tag = eipField.getTag();//获取读取的变量名称
            //根据变量名称计算Byte长度
            int serviceTypeByte1 = 1;//服务标识
            int requestPathSizeByte1 = 1;//请求路径
            int dataTypeByte1 = 1;//扩展符号
            int datalengthByte1 = 1;//单个参数长度
            int dataConentByte = tag.length();//
            int elementNbByte = 2;
            cipReadRequestByteLength = serviceTypeByte1 + requestPathSizeByte1 + dataTypeByte1 + datalengthByte1 + dataConentByte + elementNbByte;
            packageSize += cipReadRequestByteLength;
        }
        int endBytelength = 4;
        packageSize += endBytelength;
        return packageSize;
    }

    private int calculateReadRespPackageLength(List<PlcField> plcFields) {
        int packageSize = 0;
        int prefixBytelength = 38;
        int dataLengthByte = 2;
        if (plcFields.size() > 1) { //MultipleServiceResponse
            int serviceTypeByte = 1;
            int reservedByte = 1;//reservedByte
            int statusByte = 1;//status
            int extStatusByte = 1;//extStatus
            int serviceNbByte = 2;
            int offsetsAddrByte = plcFields.size() * 2;
            packageSize += (serviceTypeByte + reservedByte + statusByte + extStatusByte + serviceNbByte + offsetsAddrByte);
        }
        packageSize += (prefixBytelength + dataLengthByte);
        int cipReadResponseByteLength = 0;
        for (int i = 0; i < plcFields.size(); i++) {
            PlcField plcField = plcFields.get(i);
            EipField eipField = (EipField) plcField;
            String tag = eipField.getTag();//获取读取的变量名称
            int serviceTypeByte1 = 1;//服务标识
            int reservedByte1 = 1;//0x00固定
            int statusByte1 = 1;//status
            int extStatusByte1 = 1;//extStatus
            int dataTypeByte = 2;//数据类型
            //数据长度  即数据类型对应的字节长度
            int dataSize;
            int elementNb = eipField.getElementNb();
            if (elementNb > 1) {
                //数组情况
                dataSize = eipField.getType().getSize() * elementNb;
            } else {
                dataSize = eipField.getType().getSize();
            }
            cipReadResponseByteLength = serviceTypeByte1 + reservedByte1 + statusByte1 + extStatusByte1 + dataTypeByte + dataSize;
            packageSize += cipReadResponseByteLength;
        }
        return packageSize;
    }

    private int calculateReadRespDataLength(List<PlcField> plcFields) {
        int packageSize = 0;
        for (int i = 0; i < plcFields.size(); i++) {
            PlcField plcField = plcFields.get(i);
            EipField eipField = (EipField) plcField;
            //数据长度  即数据类型对应的字节长度
            int dataSize;
            int elementNb = eipField.getElementNb();
            if (elementNb > 1) {
                //数组情况
                dataSize = eipField.getType().getSize() * elementNb;
            } else {
                dataSize = eipField.getType().getSize();
            }
            packageSize += dataSize;
        }
        return packageSize;
    }

    private static byte[] encodeValue(PlcValue value, CIPDataTypeCode type, short elements) {
        // ByteBuffer buffer =
        // ByteBuffer.allocate(4+type.getSize()).order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer buffer = ByteBuffer.allocate(type.getSize() * elements).order(ByteOrder.LITTLE_ENDIAN);
        if (type.getSize() == 1 && type == CIPDataTypeCode.BOOL) {
            buffer = ByteBuffer.allocate(type.getSize() * 2 * elements).order(ByteOrder.LITTLE_ENDIAN);
        }
        int byteLength = 0;
        StringBuffer stringBuffer = new StringBuffer();
        if (elements > 1) {
            PlcList plcList = (PlcList) value;
            List<PlcValue> plcValueList = plcList.getList();
            for (int i = 0; i < plcValueList.size(); i++) {
                PlcValue plcValue = plcValueList.get(i);
                switch (type) {
                    case BYTE:
                    case USINT:
                    case SINT:
                    case BOOL:
                        buffer.put(plcValue.getByte());
                        break;
                    case UINT:
                    case INT:
                        buffer.putShort(plcValue.getShort());
                        break;
                    case UDINT:
                    case DINT:
                    case DWORD:
                        buffer.putInt(plcValue.getInteger());
                        break;
                    case REAL:
                        // buffer.putDouble(value.getDouble());
                        buffer.putFloat(plcValue.getFloat());
                        break;
                    case LREAL:
                        // buffer.putDouble(value.getDouble());
                        buffer.putDouble(plcValue.getDouble());
                        break;
                    case ULINT:
                    case LINT:
                        buffer.putLong(plcValue.getLong());
                        break;
                    case STRING:
                        stringBuffer.append(plcValue.getString());
                        if (i == plcValueList.size() - 1) {
                            ByteBuffer buffer_string = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
                            if (stringBuffer.toString().getBytes().length % 2 != 0) { //判断数据byte长度是否未偶数位,奇数补位
                                buffer = ByteBuffer.allocate(type.getSize() * 2 + stringBuffer.toString().getBytes().length / 2 * 2 + 2).order(ByteOrder.LITTLE_ENDIAN);
                                buffer_string.putShort((short) (stringBuffer.toString().getBytes().length / 2 * 2 + 2));//STRING类型添加数据长度标志位
                            } else {
                                buffer = ByteBuffer.allocate(type.getSize() * 2 + stringBuffer.toString().getBytes().length).order(ByteOrder.LITTLE_ENDIAN);
                                buffer_string.putShort((short) (stringBuffer.toString().getBytes().length));//STRING类型添加数据长度标志位
                            }
                            buffer.put(buffer_string.array());//数据长度标志位
                            buffer.put(stringBuffer.toString().getBytes());//data内容
                        }
                        break;
                    default:
                        break;
                }
            }
        } else {
            switch (type) {
                case BYTE:
                case USINT:
                case SINT:
                case BOOL:
                    buffer.put(value.getByte());
                    break;
                case UINT:
                case INT:
                    buffer.putShort(value.getShort());
                    break;
                case UDINT:
                case DINT:
                case DWORD:
                    buffer.putInt(value.getInteger());
                    break;
                case REAL:
                    // buffer.putDouble(value.getDouble());
                    buffer.putFloat(value.getFloat());
                    break;
                case LREAL:
                    // buffer.putDouble(value.getDouble());
                    buffer.putDouble(value.getDouble());
                    break;
                case ULINT:
                case LINT:
                    buffer.putLong(value.getLong());
                    break;
                case STRING:
                    ByteBuffer buffer_string = ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
                    if (value.getString().getBytes().length % 2 != 0) { //判断数据byte长度是否未偶数位,奇数补位
                        buffer = ByteBuffer.allocate(type.getSize() * 2 + value.getString().getBytes().length / 2 * 2 + 2).order(ByteOrder.LITTLE_ENDIAN);
                        buffer_string.putShort((short) (value.getString().getBytes().length / 2 * 2 + 2));//STRING类型添加数据长度标志位
                    } else {
                        buffer = ByteBuffer.allocate(type.getSize() * 2 + value.getString().getBytes().length).order(ByteOrder.LITTLE_ENDIAN);
                        buffer_string.putShort((short) (value.getString().getBytes().length));//STRING类型添加数据长度标志位
                    }
                    buffer.put(buffer_string.array());//数据长度标志位
                    buffer.put(value.getString().getBytes());//data内容
                    break;
                default:
                    break;
            }
        }
        return buffer.array();

    }
}
