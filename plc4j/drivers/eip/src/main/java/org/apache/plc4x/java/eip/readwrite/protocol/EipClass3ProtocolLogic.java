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
package org.apache.plc4x.java.eip.readwrite.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.plc4x.java.api.exceptions.PlcRuntimeException;
import org.apache.plc4x.java.api.messages.*;
import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.eip.readwrite.*;
import org.apache.plc4x.java.eip.readwrite.configuration.EIPConfiguration;
import org.apache.plc4x.java.eip.readwrite.field.EipField;
import org.apache.plc4x.java.eip.readwrite.field.EipStruct;
import org.apache.plc4x.java.eip.readwrite.field.EipStructHandler;
import org.apache.plc4x.java.eip.readwrite.io.CipServiceIO;
import org.apache.plc4x.java.eip.readwrite.types.CIPDataTypeCode;
import org.apache.plc4x.java.eip.readwrite.util.EipProtocolUtils;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.ReadBuffer;
import org.apache.plc4x.java.spi.generation.ReadBufferByteBased;
import org.apache.plc4x.java.spi.messages.*;
import org.apache.plc4x.java.spi.messages.utils.ResponseItem;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.spi.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Console;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class EipClass3ProtocolLogic extends Plc4xProtocolBase<EipPacket> implements HasConfiguration<EIPConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(EipClass3ProtocolLogic.class);
    public static final Duration REQUEST_TIMEOUT = Duration.ofMillis(2000);
    public static final Duration RESPONSE_TIMEOUT = Duration.ofMillis(3000);

    private static final short[] emptySenderContext = new short[] { (short) 0x00, (short) 0x00, (short) 0x00,
            (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00 };
    private short[] senderContext;
    private EIPConfiguration configuration;

    private final AtomicInteger transactionCounterGenerator = new AtomicInteger(10);
    private RequestTransactionManager tm;

    private long sessionHandle;
    private long otConnectionId;
    private long toId;
    private int sequenceCount = 1;

    private int dataPackageByteLength = 1990;
    private LinkedHashMap<String,LinkedHashMap<String,Object>> structs;
    private Map<String,Short> crcs = new HashMap<>();

    @Override
    public void setConfiguration(EIPConfiguration configuration) {
        this.configuration = configuration;
        this.dataPackageByteLength = configuration.getDataPackageByteLength();
        // Set the transaction manager to allow only one message at a time.
        this.tm = new RequestTransactionManager(1);
        if(configuration.getStructs() != null){
            
            this.structs = configuration.getStructInstance();
            logger.info("================"+structs.toString());
        }
        
        // for (String s : configuration.getStructPath().keySet()) {
        //     logger.info("================"+s);
        // }
    }

    @Override
    public void onConnect(ConversationContext<EipPacket> context) {
        logger.debug("Sending RegisterSession EIP Package");
        EipConnectionRequest connectionRequest = new EipConnectionRequest(0L, 0L, emptySenderContext, 0L);
        context.sendRequest(connectionRequest)
                .expectResponse(EipPacket.class, REQUEST_TIMEOUT).unwrap(p -> p)
                .check(p -> p instanceof EipConnectionRequest)
                .handle(p -> {
                    if (p.getStatus() == 0L) {
                        sessionHandle = p.getSessionHandle();
                        senderContext = p.getSenderContext();
                        logger.debug("Got assigned with Session {}", sessionHandle);
                        // Send an event that connection setup is complete.

                        // logger.info("send open request!");
                        OpenRequest(context);
                        
                    } else {
                        logger.warn("Got status code [{}]", p.getStatus());
                    }

                });
    }

    private void OpenRequest(ConversationContext<EipPacket> context) {

        this.toId = new Random().nextLong();
        LargeForwardOpenRequest req = new LargeForwardOpenRequest(0l, this.toId, (int)0x0001, this.toId);
        // logger.info("large forward open request:" + req.toString());
        CipExchange exchange = new CipExchange(req);
        // logger.info("exchange:" + exchange.toString());
        CipRRData rrdata = new CipRRData(sessionHandle, 0L, senderContext, 0L, exchange);
        // logger.info("rrdata:" + rrdata.toString());
        context.sendRequest(rrdata)
                .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                .check(p -> p instanceof CipRRData).unwrap(p -> (CipRRData) p)
                .check(p -> p.getExchange().getService() instanceof LargeForwardOpenResponse)
                .unwrap(p -> (LargeForwardOpenResponse) p.getExchange().getService())
                .handle(p -> {
                    // logger.info("handling open response");
                    otConnectionId = p.getO_t_connection_id();
                    context.fireConnected();

                });
                // logger.info("open request finished...");
    }
    private void ReadStructs(ConversationContext<EipPacket> context){

    }

    private void CloseRequest(ConversationContext<EipPacket> context) {

        CloseRequest req = new CloseRequest(0x0001, this.toId);
        CipRRData rrdata = new CipRRData(sessionHandle, 0L, senderContext, 0L, new CipExchange(req));
        context.sendRequest(rrdata)
                .expectResponse(EipPacket.class, REQUEST_TIMEOUT);

    }

    @Override
    public CompletableFuture<PlcReadResponse> read(PlcReadRequest readRequest) {
        CompletableFuture<PlcReadResponse> completableFuture = new CompletableFuture<>();
        Map<String, ResponseItem<PlcValue>> responseItemMap = new LinkedHashMap<>();
        try {
            List<PlcField> fields = readRequest.getFields();
            DefaultPlcReadRequest request = (DefaultPlcReadRequest) readRequest;
            // 单个请求返回报文超长的集合
            List<String> singleRespOverlengthParams = EipProtocolUtils.getReadSingleRespOverlengthParams(readRequest,
                    configuration);
            // 多个请求报文超长分割，并去除单个请求报文返回超长的集合
            List<List<String>> noSingleRespOverlengthParams = EipProtocolUtils
                    .getReadNoSingleRespOverlengthParams(readRequest, configuration);
            logger.debug("Number of read message groups is " + noSingleRespOverlengthParams.size());
            for (int i = 0; i < singleRespOverlengthParams.size(); i++) {
                // 单个数据超长 分段请求 合并结果
                String fieldName = singleRespOverlengthParams.get(i);
                final EipField field = (EipField) request.getField(fieldName);
                int dataByteLength = EipProtocolUtils.calculateSingleReadRespDataLength(field);
                Map<String, ResponseItem<PlcValue>> mergeSegmentResponse = mergeSegmentPlcReadResponse(fieldName, field,
                        dataByteLength);
                responseItemMap.put(fieldName, mergeSegmentResponse.get(fieldName));
            }
            for (int i = 0; i < noSingleRespOverlengthParams.size(); i++) {
                List<String> eipRequestEntities = noSingleRespOverlengthParams.get(i);
                List<CipReadRequest> requests = new ArrayList<>();
                for (int j = 0; j < eipRequestEntities.size(); j++) {
                    String fieldName = eipRequestEntities.get(j);
                    EipField plcField = (EipField) request.getField(fieldName);
                    String tag = plcField.getTag();
                    int elements = 1;
                    if (plcField.getElementNb() > 1) {
                        elements = plcField.getElementNb();
                    }
                    CipReadRequest req = new CipReadRequest(getRequestSize(tag), toAnsi(tag), elements);
                    requests.add(req);
                }
                if (requests.size() > 0) {
                    Map<String, ResponseItem<PlcValue>> responseMap = readPlcResponse(eipRequestEntities, request,
                            requests);
                    if (responseMap != null && responseMap.size() > 0) {
                        responseItemMap.putAll(responseMap);
                    } else {
                        responseItemMap.clear();
                        break;
                    }
                }
            }
            completableFuture.complete(new DefaultPlcReadResponse(readRequest, responseItemMap));
        } catch (Exception e) {
            logger.error("read error::", e);
            // throw new RuntimeException(e);
            completableFuture.complete(new DefaultPlcReadResponse(readRequest, responseItemMap));
        }
        return completableFuture;

    }

    private Map<String, ResponseItem<PlcValue>> readPlcResponse(List<String> eipRequestEntities,
            PlcReadRequest readRequest, List<CipReadRequest> requests) {
        CompletableFuture<Map<String, ResponseItem<PlcValue>>> mapCompletableFuture = getPlcReadResponse(
                eipRequestEntities, readRequest, readInternal(requests));
        Map<String, ResponseItem<PlcValue>> responseItemMap = new HashMap<>();
        try {
            responseItemMap = mapCompletableFuture.get(RESPONSE_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.warn("readPlcResponse with error", e);
            mapCompletableFuture.completeExceptionally(e);
        }
        return responseItemMap;
    }

    private CompletableFuture<Map<String, ResponseItem<PlcValue>>> getPlcReadResponse(List<String> eipRequestEntities,
            PlcReadRequest readRequest, CompletableFuture<CipService> response) {
        return response
                .thenApply(p -> {
                    Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
                    // only 1 field
                    logger.info("get plc read response .....");
                    if (p instanceof CipReadResponse) {
                        CipReadResponse resp = (CipReadResponse) p;
                        PlcResponseCode code = decodeResponseCode(resp.getReadResponseContent().getStatus());
                        PlcValue plcValue = null;
                        ReadResponseContent readResponseContent = resp.getReadResponseContent();
                        Short status = readResponseContent.getStatus();
                        EipField plcField = (EipField) readRequest.getField(eipRequestEntities.get(0));
                        if (status == 0) {
                            ResponseOk responseOk = (ResponseOk) readResponseContent;
                            CIPDataTypeCode type = responseOk.getDataType();
                            ByteBuf data = Unpooled.wrappedBuffer(responseOk.getData());
                            logger.info("pares plc value .....");
                            plcValue = parsePlcValue(plcField, data, type);
                            ResponseItem<PlcValue> result = new ResponseItem<>(code, plcValue);
                            values.put(eipRequestEntities.get(0), result);
                            logger.info("read plc response values ....."+values.toString());
                            logger.info("read plc response results ....."+result.toString());
                        } else {
                            values.put(eipRequestEntities.get(0),
                                    new ResponseItem<>(PlcResponseCode.INTERNAL_ERROR, plcValue));
                        }

                    } else if (p instanceof MultipleServiceResponse) {
                        MultipleServiceResponse responses = (MultipleServiceResponse) p;
                        int nb = responses.getServiceNb();
                        CipService[] arr = new CipService[nb];
                        ReadBufferByteBased read = new ReadBufferByteBased(responses.getServicesData(), true);
                        int total = (int) read.getTotalBytes();
                        for (int i = 0; i < nb; i++) {
                            int length = 0;
                            int offset = responses.getOffsets()[i] - responses.getOffsets()[0]; // Substract first
                                                                                                // offset as we only
                            // have the service in the buffer
                            // (not servicesNb and offsets)
                            if (i == nb - 1) {
                                length = total - offset; // Get the rest if last
                            } else {
                                length = responses.getOffsets()[i + 1] - offset - responses.getOffsets()[0]; // Calculate
                                                                                                             // length
                                // with offsets
                                // (substracting first
                                // offset)
                            }
                            ReadBuffer serviceBuf = new ReadBufferByteBased(read.getBytes(offset, offset + length),
                                    true);
                            CipService service = null;
                            try {
                                service = CipServiceIO.staticParse(read, length);
                                arr[i] = service;
                            } catch (ParseException e) {
                                throw new PlcRuntimeException(e);
                            }
                        }
                        Services services = new Services(nb, responses.getOffsets(), arr);
                        for (int i = 0; i < nb; i++) {
                            String fieldName = eipRequestEntities.get(i);
                            EipField plcField = (EipField) readRequest.getField(fieldName);
                            PlcValue plcValue = null;
                            if (services.getServices()[i] instanceof CipReadResponse) {
                                CipReadResponse readResponse = (CipReadResponse) services.getServices()[i];
                                PlcResponseCode code = null;
                                ReadResponseContent readResponseContent = readResponse.getReadResponseContent();
                                if (readResponseContent.getStatus() == 0) {
                                    code = PlcResponseCode.OK;
                                    ResponseOk responseOk = (ResponseOk) readResponseContent;
                                    CIPDataTypeCode type = responseOk.getDataType();
                                    ByteBuf data = Unpooled.wrappedBuffer(responseOk.getData());
                                    plcValue = parsePlcValue(plcField, data, type);
                                    ResponseItem<PlcValue> result = new ResponseItem<>(code, plcValue);
                                    values.put(fieldName, result);
                                } else {
                                    code = PlcResponseCode.INTERNAL_ERROR;
                                    values.put(fieldName, new ResponseItem<>(code, null));
                                }
                            }
                        }
                    }
                    return values;
                });
    }

    private Map<String, ResponseItem<PlcValue>> mergeSegmentPlcReadResponse(String fieldName, EipField plcField,
            int dataByteLength) {
        int arrayIndex = 0;// 用于记录数组请求的起始索引
        List<CipReadRequest> requests = new ArrayList<>();
        String tag = plcField.getTag();
        int initialOffset = 1;// 用于记录初始偏移量//%TEST2[0]:DINT:200
        int elements = 1;
        if (plcField.getElementNb() > 1) {
            elements = plcField.getElementNb();
            initialOffset = plcField.getElementNb();
        }
        boolean isArray = false;
        String tagIsolated = tag;
        if (tag.contains("[")) {
            // %TEST2[0]:DINT:200
            isArray = true;
            tagIsolated = tag.substring(0, tag.indexOf("["));// 标签名称
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);// 数组索引，暂未使用
        }
        if (isArray) {
            elements = 1;// 固定设置1，用于避免报文增加长度标识，导致segement-path返回错误
        }
        int segementNum = dataByteLength / dataPackageByteLength;// 分几段发送
        int leftPackegeByte = dataByteLength % dataPackageByteLength;// 分段后剩余数据包长度
        List<Integer> dataNumList = new ArrayList<>();// 用于记录分段请求返回的数据个数
        int offsetStart = 0;
        int offset = 0;
        // %TEST2[0]:DINT:100 %TEST2[2]:DINT:100 2 10 12 10
        offsetStart = arrayIndex * plcField.getType().getSize();
        offset = dataPackageByteLength;
        if (plcField.getType() == CIPDataTypeCode.BOOL) {
            // BOOL数组特殊处理 按8bit 一个字节
            offsetStart = (arrayIndex * plcField.getType().getSize()) / 8;// 计算起始索引
            offset = dataPackageByteLength;// 计算偏移量
            dataByteLength = dataByteLength / 8 + (dataByteLength % 8 == 0 ? 0 : 1); // 计算Boolean返回的字节数
            segementNum = dataByteLength / dataPackageByteLength; // 分几段发送
            leftPackegeByte = dataByteLength % dataPackageByteLength;// 分段后剩余数据包长度
        }

        for (int i = 0; i < segementNum; i++) {
            int offsetStartByte1 = Integer.parseInt(String.format("%04X", offsetStart + (i * offset)).substring(0, 2),
                    16);
            int offsetStartByte2 = Integer.parseInt(String.format("%04X", offsetStart + (i * offset)).substring(2, 4),
                    16);
            int offsetByte1 = Integer.parseInt(String.format("%04X", dataPackageByteLength).substring(0, 2), 16);
            int offsetByte2 = Integer.parseInt(String.format("%04X", dataPackageByteLength).substring(2, 4), 16);
            byte[] segementBytes = new byte[] { (byte) 0x80, (byte) 0x03, (byte) offsetStartByte2,
                    (byte) offsetStartByte1, (byte) 0x00, (byte) 0x00, (byte) offsetByte2, (byte) offsetByte1 };
            CipReadRequest req;
            if (isArray) {
                req = new CipReadRequest(getSegmentRequestSize(tagIsolated), toAnsiForArray(tagIsolated, segementBytes),
                        elements);
            } else {
                req = new CipReadRequest(getSegmentRequestSize(tagIsolated), toAnsi(tagIsolated, segementBytes),
                        elements);
            }
            dataNumList.add(dataPackageByteLength / plcField.getType().getSize());
            requests.add(req);
        }
        if (leftPackegeByte > 0) {
            // elements = leftPackegeByte/8;
            int offsetStart1 = Integer.parseInt(
                    String.format("%04X", offsetStart + segementNum * dataPackageByteLength).substring(0, 2), 16);
            int offsetStart2 = Integer.parseInt(
                    String.format("%04X", offsetStart + segementNum * dataPackageByteLength).substring(2, 4), 16);
            int offset1 = Integer.parseInt(String.format("%04X", leftPackegeByte).substring(0, 2), 16);
            int offset2 = Integer.parseInt(String.format("%04X", leftPackegeByte).substring(2, 4), 16);
            byte[] segementBytes = new byte[] { (byte) 0x80, (byte) 0x03, (byte) offsetStart2, (byte) offsetStart1,
                    (byte) 0x00, (byte) 0x00, (byte) offset2, (byte) offset1 };
            CipReadRequest req;
            if (isArray) {
                req = new CipReadRequest(getSegmentRequestSize(tagIsolated), toAnsiForArray(tagIsolated, segementBytes),
                        elements);
            } else {
                req = new CipReadRequest(getSegmentRequestSize(tagIsolated), toAnsi(tagIsolated, segementBytes),
                        elements);
            }
            dataNumList.add(leftPackegeByte / plcField.getType().getSize());
            requests.add(req);
        }
        Map<String, ResponseItem<PlcValue>> responseItemMap = new LinkedHashMap<>();
        PlcList plcList = new PlcList();
        for (int i = 0; i < requests.size(); i++) {
            // 计算每次请求返回的数据数量，用于解析返回数据
            int dataNum = dataNumList.get(i);// 需要解析的返回数据数量
            plcField.setElementNb(dataNum);
            CompletableFuture<Map<String, ResponseItem<PlcValue>>> mapCompletableFuture = toPlcReadSegmentResponse(
                    fieldName, plcField, initialOffset, readSingleInternal(requests.get(i)));
            try {
                Map<String, ResponseItem<PlcValue>> responseSingleItemMap = mapCompletableFuture
                        .get(REQUEST_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);
                ResponseItem<PlcValue> plcValueResponseItem = responseSingleItemMap.get(fieldName);
                if (responseSingleItemMap.size() > 0) {
                    PlcValue plcValueResponseItemValue = plcValueResponseItem.getValue();
                    if (plcValueResponseItemValue instanceof PlcList) {
                        PlcList plcListResponseItemValue = (PlcList) plcValueResponseItemValue;
                        for (int j = 0; j < plcListResponseItemValue.getList().size(); j++) {
                            plcList.add(plcValueResponseItemValue.getIndex(j));
                        }
                    } else {
                        plcList.add(plcValueResponseItemValue);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        ResponseItem<PlcValue> responseItem = new ResponseItem<>(PlcResponseCode.OK, plcList);
        responseItemMap.put(fieldName, responseItem);
        return responseItemMap;
    }

    private CompletableFuture<Map<String, ResponseItem<PlcValue>>> toPlcReadSegmentResponse(String fieldName,
            EipField eipField, int initialOffset, CompletableFuture<CipService> response) {
        return response
                .thenApply(p -> {
                    Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
                    // only 1 field
                    if (p instanceof CipReadResponse) {
                        CipReadResponse resp = (CipReadResponse) p;
                        PlcResponseCode code = null;
                        ReadResponseContent readResponseContent = resp.getReadResponseContent();
                        if (readResponseContent.getStatus() == 0) {
                            ResponseOk responseOk = (ResponseOk) readResponseContent;
                            CIPDataTypeCode type = responseOk.getDataType();
                            ByteBuf data = Unpooled.wrappedBuffer(responseOk.getData());
                            PlcValue plcValue = null;
                            code = PlcResponseCode.OK;
                            // 特殊处理BOOL
                            if (type == CIPDataTypeCode.BOOL) {
                                // 分段请求bool 按位解析组装结果
                                plcValue = parsePlcBoolValue(eipField, data, initialOffset);
                            } else {
                                plcValue = parsePlcValue(eipField, data, type);
                            }
                            ResponseItem<PlcValue> result = new ResponseItem<>(code, plcValue);
                            values.put(fieldName, result);
                        } else {
                            code = PlcResponseCode.INTERNAL_ERROR;
                            ResponseItem<PlcValue> result = new ResponseItem<>(code, null);
                            values.put(fieldName, result);
                        }
                    }
                    return values;
                });
    }

    private byte getRequestSize(String tag) {
        // We need the size of the request in words (0x91, tagLength, ... tag + possible
        // pad)
        // Taking half to get word size
        int arrayIndex = 0;
        boolean isArray = false;
        boolean isStruct = false;
        String tagIsolated = tag;
        if (tag.contains("[")) {
            isArray = true;
            tagIsolated = tag.substring(0, tag.indexOf("["));
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);
        }
        if (tag.contains(".")) {
            isStruct = true;
            tagIsolated = tagIsolated.replace(".", "");
        }
        int dataLength = (tagIsolated.length() + 2)
                + (tagIsolated.length() % 2)
                + (isArray ? 2 : 0)
                + (isStruct ? 2 : 0);
        byte requestPathSize = (byte) (dataLength / 2);
        if (isArray) {
            if (arrayIndex > 255) {
                requestPathSize = (byte) (dataLength / 2 + 1);
            }
        }
        return requestPathSize;
    }

    private byte getSegmentRequestSize(String tag) {
        // We need the size of the request in words (0x91, tagLength, ... tag + possible
        // pad)
        // Taking half to get word size
        boolean isArray = false;
        boolean isStruct = false;
        String tagIsolated = tag;
        if (tag.contains("[")) {
            isArray = true;
            tagIsolated = tag.substring(0, tag.indexOf("["));
        }

        if (tag.contains(".")) {
            isStruct = true;
            tagIsolated = tagIsolated.replace(".", "");
        }
        int dataLength = (tagIsolated.length() + 2)
                + (tagIsolated.length() % 2)
                + (isArray ? 2 : 0)
                + (isStruct ? 2 : 0);
        byte requestPathSize = (byte) ((dataLength / 2) + 4);// 增加segement-path部分报文长度
        return requestPathSize;
    }

    private byte[] toAnsi(String tag) {
        int arrayIndex = 0;
        boolean isArray = false;
        boolean isStruct = false;
        String tagFinal = tag;
        if (tag.contains(".")) {
            tagFinal = tag.substring(0, tag.indexOf("."));
            isStruct = true;
        }
        if (tagFinal.contains("[")) {
            isArray = true;
            String index = tagFinal.substring(tagFinal.indexOf("[") + 1, tagFinal.indexOf("]"));
            arrayIndex = Integer.parseInt(index);
            tagFinal = tagFinal.substring(0, tag.indexOf("["));
        }
      
        boolean isPadded = tagFinal.length() % 2 != 0;
        int dataSegLength = 2 + tagFinal.length()
                + (isPadded ? 1 : 0)
                + (isArray ? 2 : 0);

        if (isStruct) {
            for (String subStr : tag.substring(tag.indexOf(".") + 1).split("\\.", -1)) {
                dataSegLength += 2 + subStr.length() + subStr.length() % 2;
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(dataSegLength).order(ByteOrder.LITTLE_ENDIAN);
        if (isArray) {
            if (arrayIndex > 255) {
                buffer = ByteBuffer.allocate(dataSegLength + 2).order(ByteOrder.LITTLE_ENDIAN);
            }
        }
        buffer.put((byte) 0x91);
        buffer.put((byte) tagFinal.length());
        byte[] tagBytes = null;
        tagBytes = tagFinal.getBytes(StandardCharsets.US_ASCII);

        buffer.put(tagBytes);
        buffer.position(2 + tagBytes.length);

        if (isPadded) {
            buffer.put((byte) 0x00);
        }

       
        if (isStruct) {
            buffer.put(toAnsi(tag.substring(tag.indexOf(".") + 1, tag.length())));
        }
        if (isArray) {
            if (arrayIndex > 255) {
                buffer.put((byte) 0x29);
                buffer.put((byte) 0x00);
                buffer.putShort((short) arrayIndex);
            } else {
                buffer.put((byte) 0x28);
                buffer.put((byte) arrayIndex);
            }
        }
        return buffer.array();
    }
    private byte[] toAnsiSubName(String tag) {
        int arrayIndex = 0;
        boolean isArray = false;
        boolean isStruct = false;
        String tagFinal = tag;
        if (tag.contains("[")) {
            isArray = true;
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);
            tagFinal = tag.substring(0, tag.indexOf("["));
        }
        if (tag.contains(".")) {
            tagFinal = tag.substring(0, tag.indexOf("."));
            isStruct = true;
        }
        boolean isPadded = tagFinal.length() % 2 != 0;
        int dataSegLength = 2 + tagFinal.length()
                + (isPadded ? 1 : 0)
                + (isArray ? 2 : 0);

        if (isStruct) {
            for (String subStr : tag.substring(tag.indexOf(".") + 1).split("\\.", -1)) {
                dataSegLength += 2 + subStr.length() + subStr.length() % 2;
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(dataSegLength).order(ByteOrder.LITTLE_ENDIAN);
        if (isArray) {
            if (arrayIndex > 255) {
                buffer = ByteBuffer.allocate(dataSegLength + 2).order(ByteOrder.LITTLE_ENDIAN);
            }
        }
        buffer.put((byte) 0x91);
        buffer.put((byte) tagFinal.length());
        byte[] tagBytes = null;
        tagBytes = tagFinal.getBytes(StandardCharsets.US_ASCII);

        buffer.put(tagBytes);
        buffer.position(2 + tagBytes.length);

        if (isPadded) {
            buffer.put((byte) 0x00);
        }

       
        if (isStruct) {
            buffer.put(toAnsi(tag.substring(tag.indexOf(".") + 1, tag.length())));
        }
        if (isArray) {
            if (arrayIndex > 255) {
                buffer.put((byte) 0x29);
                buffer.put((byte) 0x00);
                buffer.putShort((short) arrayIndex);
            } else {
                buffer.put((byte) 0x28);
                buffer.put((byte) arrayIndex);
            }
        }
        return buffer.array();
    }

    private byte[] toAnsi(String tag, byte[] segementBytes) {
        int arrayIndex = 0;
        boolean isArray = false;
        boolean isStruct = false;
        String tagFinal = tag;
        if (tag.contains("[")) {
            isArray = true;
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);
            tagFinal = tag.substring(0, tag.indexOf("["));
        }
        if (tag.contains(".")) {
            tagFinal = tag.substring(0, tag.indexOf("."));
            isStruct = true;
        }
        boolean isPadded = tagFinal.length() % 2 != 0;
        int dataSegLength = 2 + tagFinal.length()
                + (isPadded ? 1 : 0)
                + (isArray ? 2 : 0);

        if (isStruct) {
            for (String subStr : tag.substring(tag.indexOf(".") + 1).split("\\.", -1)) {
                dataSegLength += 2 + subStr.length() + subStr.length() % 2;
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(dataSegLength + segementBytes.length).order(ByteOrder.LITTLE_ENDIAN);
        if (isArray) {
            if (arrayIndex > 255) {
                buffer = ByteBuffer.allocate(dataSegLength + 2).order(ByteOrder.LITTLE_ENDIAN);
            }
        }
        buffer.put((byte) 0x91);
        buffer.put((byte) tagFinal.length());
        byte[] tagBytes = null;
        tagBytes = tagFinal.getBytes(StandardCharsets.US_ASCII);

        buffer.put(tagBytes);
        buffer.position(2 + tagBytes.length);

        if (isPadded) {
            buffer.put((byte) 0x00);
        }

        if (isArray) {
            if (arrayIndex > 255) {
                buffer.put((byte) 0x29);
                buffer.put((byte) 0x00);
                buffer.putShort((short) arrayIndex);
            } else {
                buffer.put((byte) 0x28);
                buffer.put((byte) arrayIndex);
            }
        }
        if (isStruct) {
            buffer.put(toAnsi(tag.substring(tag.indexOf(".") + 1, tag.length())));
        }
        if (segementBytes != null && segementBytes.length > 0) {
            buffer.put(segementBytes);
        }
        return buffer.array();
    }

    private byte[] toAnsiForArray(String tag, byte[] segementBytes) {
        int arrayIndex = 0;
        boolean isStruct = false;
        String tagFinal = tag;
        if (tag.contains("[")) {
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);
            tagFinal = tag.substring(0, tag.indexOf("["));
        }
        if (tag.contains(".")) {
            tagFinal = tag.substring(0, tag.indexOf("."));
            isStruct = true;
        }
        boolean isPadded = tagFinal.length() % 2 != 0;
        int dataSegLength = 2 + tagFinal.length()
                + (isPadded ? 1 : 0);

        if (isStruct) {
            for (String subStr : tag.substring(tag.indexOf(".") + 1).split("\\.", -1)) {
                dataSegLength += 2 + subStr.length() + subStr.length() % 2;
            }
        }
        ByteBuffer buffer = ByteBuffer.allocate(dataSegLength + segementBytes.length).order(ByteOrder.LITTLE_ENDIAN);

        buffer.put((byte) 0x91);
        buffer.put((byte) tagFinal.length());
        byte[] tagBytes = null;
        tagBytes = tagFinal.getBytes(StandardCharsets.US_ASCII);

        buffer.put(tagBytes);
        buffer.position(2 + tagBytes.length);

        if (isPadded) {
            buffer.put((byte) 0x00);
        }

        if (isStruct) {
            buffer.put(toAnsi(tag.substring(tag.indexOf(".") + 1, tag.length())));
        }
        if (segementBytes != null && segementBytes.length > 0) {
            buffer.put(segementBytes);
        }
        return buffer.array();
    }

    private CompletableFuture<PlcReadResponse> toPlcReadResponse(PlcReadRequest readRequest,
            CompletableFuture<CipService> response) {
                logger.info("de code read response  .....");
        return response
                .thenApply(p -> {
                    return ((PlcReadResponse) decodeReadResponse(p, readRequest));
                });
    }

    private CompletableFuture<CipService> readInternal(List<CipReadRequest> request) {
        CompletableFuture<CipService> future = new CompletableFuture<>();
        this.tm = new RequestTransactionManager(1);
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        this.sequenceCount = this.sequenceCount % 2 + 1;
        if (request.size() > 1) {

            short nb = (short) request.size();
            int[] offsets = new int[nb];
            int offset = 2 + nb * 2;
            for (int i = 0; i < nb; i++) {
                offsets[i] = offset;
                offset += request.get(i).getLengthInBytes();
            }

            CipService[] serviceArr = new CipService[nb];
            for (int i = 0; i < nb; i++) {
                serviceArr[i] = request.get(i);
            }
            Services data = new Services(nb, offsets, serviceArr);
            // Encapsulate the data

            SendUnitData pkt = new SendUnitData(
                    sessionHandle,
                    0l,
                    senderContext,
                    0l,
                    otConnectionId,
                    new CipExchange3(
                            this.sequenceCount,
                            new MultipleServiceRequest(data)));

            transaction.submit(() -> context.sendRequest(pkt)
                    .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                    .onTimeout(future::completeExceptionally)
                    .onError((p, e) -> future.completeExceptionally(e))
                    .check(p -> p instanceof SendUnitData)
                    .check(p -> p.getSessionHandle() == sessionHandle)
                    // .check(p -> p.getSenderContext() == senderContext)
                    .unwrap(p -> (SendUnitData) p)
                    .unwrap(p -> p.getExchange3().getService()).check(p -> p instanceof MultipleServiceResponse)
                    .unwrap(p -> (MultipleServiceResponse) p)
                    .check(p -> p.getServiceNb() == nb)
                    .handle(p -> {
                        future.complete(p);
                        // Finish the request-transaction.
                        transaction.endRequest();
                    }));
        } else if (request.size() == 1) {
            CipExchange3 exchange = new CipExchange3(
                    this.sequenceCount,
                    request.get(0));
            SendUnitData pkt = new SendUnitData(sessionHandle,
                    0l,
                    senderContext,
                    0l,
                    otConnectionId, exchange);
            transaction.submit(() -> context.sendRequest(pkt)
                    .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                    .onTimeout(future::completeExceptionally)
                    .onError((p, e) -> future.completeExceptionally(e))
                    .check(p -> p instanceof SendUnitData)
                    .check(p -> p.getSessionHandle() == sessionHandle)
                    // .check(p -> p.getSenderContext() == senderContext)
                    .unwrap(p -> (SendUnitData) p)
                    .unwrap(p -> p.getExchange3().getService()).check(p -> p instanceof CipReadResponse)
                    .unwrap(p -> (CipReadResponse) p)
                    .handle(p -> {
                        future.complete(p);
                        // Finish the request-transaction.
                        transaction.endRequest();
                    }));
        }
        return future;
    }

    private CompletableFuture<CipService> readSingleInternal(CipReadRequest request) {
        CompletableFuture<CipService> future = new CompletableFuture<>();
        this.tm = new RequestTransactionManager(1);
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        this.sequenceCount = this.sequenceCount % 2 + 1;
        CipExchange3 exchange = new CipExchange3(
                this.sequenceCount,
                request);
        SendUnitData pkt = new SendUnitData(sessionHandle,
                0l,
                senderContext,
                0l,
                otConnectionId, exchange);

        transaction.submit(() -> context.sendRequest(pkt)
                .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                .onTimeout(future::completeExceptionally)
                .onError((p, e) -> future.completeExceptionally(e))
                .check(p -> p instanceof SendUnitData)
                .check(p -> p.getSessionHandle() == sessionHandle)
                // .check(p -> p.getSenderContext() == senderContext)
                .unwrap(p -> (SendUnitData) p)
                .unwrap(p -> p.getExchange3().getService()).check(p -> p instanceof CipReadResponse)
                .unwrap(p -> (CipReadResponse) p)
                .handle(p -> {
                    future.complete(p);
                    // Finish the request-transaction.
                    transaction.endRequest();
                }));
        return future;
    }

    private PlcResponse decodeReadResponse(CipService p, PlcReadRequest readRequest) {
        Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
        // only 1 field
        logger.info("decode read response .....");
        if (p instanceof CipReadResponse) {
            logger.info("decode read response .1....");
            CipReadResponse resp = (CipReadResponse) p;
            String fieldName = readRequest.getFieldNames().iterator().next();
            EipField field = (EipField) readRequest.getField(fieldName);
            PlcValue plcValue = null;
            PlcResponseCode code = null;
            ReadResponseContent readResponseContent = resp.getReadResponseContent();
            logger.info("decode read response 2.....");
            if (readResponseContent.getStatus() == 0) {
                code = PlcResponseCode.OK;
                ResponseOk responseOk = (ResponseOk) readResponseContent;
                CIPDataTypeCode type = responseOk.getDataType();
                ByteBuf data = Unpooled.wrappedBuffer(responseOk.getData());
                logger.info("parse plc value .....");
                plcValue = parsePlcValue(field, data, type);
                ResponseItem<PlcValue> result = new ResponseItem<>(code, plcValue);
                values.put(fieldName, result);
                logger.info("values:"+values.toString());
            } else {
                logger.info("status code != 0 .....");
                code = PlcResponseCode.INTERNAL_ERROR;
                ResponseItem<PlcValue> result = new ResponseItem<>(code, plcValue);
                values.put(fieldName, result);
            }
        }
        // Multiple response
        else if (p instanceof MultipleServiceResponse) {
            logger.info("decode read response mutilple.....");
            MultipleServiceResponse responses = (MultipleServiceResponse) p;
            int nb = responses.getServiceNb();
            CipService[] arr = new CipService[nb];
            ReadBufferByteBased read = new ReadBufferByteBased(responses.getServicesData(), true);
            int total = (int) read.getTotalBytes();
            for (int i = 0; i < nb; i++) {
                int length = 0;
                int offset = responses.getOffsets()[i] - responses.getOffsets()[0]; // Substract first offset as we only
                // have the service in the buffer
                // (not servicesNb and offsets)
                if (i == nb - 1) {
                    length = total - offset; // Get the rest if last
                } else {
                    length = responses.getOffsets()[i + 1] - offset - responses.getOffsets()[0]; // Calculate length
                    // with offsets
                    // (substracting first
                    // offset)
                }
                ReadBuffer serviceBuf = new ReadBufferByteBased(read.getBytes(offset, offset + length), true);
                CipService service = null;
                try {
                    service = CipServiceIO.staticParse(read, length);
                    arr[i] = service;
                } catch (ParseException e) {
                    throw new PlcRuntimeException(e);
                }
            }
            Services services = new Services(nb, responses.getOffsets(), arr);
            Iterator<String> it = readRequest.getFieldNames().iterator();
            for (int i = 0; i < nb && it.hasNext(); i++) {
                String fieldName = it.next();
                EipField field = (EipField) readRequest.getField(fieldName);
                if (services.getServices()[i] instanceof CipReadResponse) {
                    CipReadResponse readResponse = (CipReadResponse) services.getServices()[i];
                    PlcResponseCode code = null;
                    PlcValue plcValue = null;
                    ReadResponseContent readResponseContent = readResponse.getReadResponseContent();
                    if (readResponseContent.getStatus() == 0) {
                        code = PlcResponseCode.OK;
                        ResponseOk responseOk = (ResponseOk) readResponseContent;
                        CIPDataTypeCode type = responseOk.getDataType();
                        ByteBuf data = Unpooled.wrappedBuffer(responseOk.getData());
                        plcValue = parsePlcValue(field, data, type);
                        ResponseItem<PlcValue> result = new ResponseItem<>(code, plcValue);
                        values.put(fieldName, result);
                    } else {
                        code = PlcResponseCode.INTERNAL_ERROR;
                        ResponseItem<PlcValue> result = new ResponseItem<>(code, plcValue);
                        values.put(fieldName, result);
                    }
                }
            }
        }
        return new DefaultPlcReadResponse(readRequest, values);
    }

    private PlcValue parsePlcValue(EipField field, ByteBuf data, CIPDataTypeCode type) {
        int nb = field.getElementNb();
        logger.info("res len:"+data.readableBytes()+";"+type);
        if (nb > 1) {
            int index = 0;
            List<PlcValue> list = new ArrayList<>();
            for (int i = 0; i < nb; i++) {
                switch (type) {
                    case BYTE:
                        list.add(new PlcBYTE(data.getUnsignedByte(index)));
                        index += type.getSize();
                        break;
                    case UDINT:
                        list.add(new PlcUDINT(data.getUnsignedIntLE(index)));
                        index += type.getSize();
                        break;
                    case DINT:
                        list.add(new PlcDINT(data.getIntLE(index)));
                        index += type.getSize();
                        break;
                    case UINT:
                        list.add(new PlcUINT(data.getUnsignedShortLE(index)));
                        index += type.getSize();
                        break;
                    case INT:
                        list.add(new PlcINT(data.getShortLE(index)));
                        index += type.getSize();
                        break;
                    case WORD:
                        list.add(new PlcWORD(data.getUnsignedShortLE(index)));
                        index += type.getSize();
                        break;
                    case ULINT:
                        list.add(new PlcULINT(data.getLongLE(index)));
                        index += type.getSize();
                        break;
                    case LINT:
                        list.add(new PlcLINT(data.getLongLE(index)));
                        index += type.getSize();
                        break;
                    case USINT:
                        list.add(new PlcUSINT(data.getUnsignedByte(index)));
                        index += type.getSize();
                        break;
                    case SINT:
                        list.add(new PlcSINT(data.getByte(index)));
                        index += type.getSize();
                        break;
                    case REAL:
                        list.add(new PlcREAL(data.getFloatLE(index)));
                        index += type.getSize();
                        break;
                    case LREAL:
                        list.add(new PlcLREAL(data.getDoubleLE(index)));
                        index += type.getSize();
                        break;
                    case BOOL:
                        list.add(new PlcBOOL(data.getBoolean(index)));
                        index += type.getSize();
                        break;
                    case DWORD:
                        list.add(new PlcDWORD(data.getIntLE(index)));
                        index += type.getSize();
                        break;
                    case LWORD:
                        list.add(new PlcDWORD(data.getLongLE(index)));
                        index += type.getSize();
                        break;
                    case STRING:
                        short aShort = data.getShort(index);
                        list.add(new PlcSTRING(StandardCharsets.UTF_8.decode(data.nioBuffer(2, aShort)).toString()));
                        index += aShort + 2;
                        break;
                    default:
                        return null;
                }
            }
            return new PlcList(list);
        } else {
            switch (type) {
                case BYTE:
                    return new PlcBYTE(data.getUnsignedByte(0));
                case USINT:
                    return new PlcUSINT(data.getUnsignedByte(0));
                case SINT:
                    return new PlcSINT(data.getByte(0));
                case UINT:
                    return new PlcUINT(data.getUnsignedShortLE(0));
                case INT:
                    return new PlcINT(data.getShortLE(0));
                case WORD:
                    return new PlcWORD(data.getUnsignedShortLE(0));
                case UDINT:
                    return new PlcUDINT(data.getUnsignedIntLE(0));
                case DINT:
                    return new PlcDINT(data.getIntLE(0));
                case ULINT:
                    return new PlcULINT(data.getLongLE(0));
                case LINT:
                    return new PlcLINT(data.getLongLE(0));
                case REAL:
                    return new PlcREAL(data.getFloatLE(0));
                case LREAL:
                    return new PlcLREAL(data.getDoubleLE(0));
                case BOOL:
                    return new PlcBOOL(data.getBoolean(0));
                case STRING:
                    short aShort = data.getShortLE(0);
                    return new PlcSTRING(StandardCharsets.UTF_8.decode(data.nioBuffer(2, aShort)).toString());
                case DWORD:
                    return new PlcWORD(data.getIntLE(0));
                case LWORD:
                    return new PlcWORD(data.getLongLE(0));
                case STRUCTURED:
                    short crc = data.getShortLE(0);
                    this.crcs.put(field.getTag(), crc);
                    logger.info("struct crc:"+this.crcs.toString());
                    PlcValue value = new EipStructHandler().initIndex(2).parse(this.structs.get(field.getTag()), data);
                    logger.info("struct value:"+value.getStruct().toString());
                    return value;
                default:
                    return null;
            }
        }
    }

    private PlcValue parsePlcBoolValue(EipField eipField, ByteBuf data, int initialOffset) {
        int nb = eipField.getElementNb();
        int arrayIndex = 0;// 用于记录数组请求的起始索引
        String tag = eipField.getTag();
        int elements = eipField.getElementNb();
        boolean isArray = false;
        String tagIsolated = tag;
        if (tag.contains("[")) {
            // %TEST2[0]:BOOL:200
            tagIsolated = tag.substring(0, tag.indexOf("["));// 标签名称
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);
        }
        List<PlcValue> plcValueList = new ArrayList<>();
        List<PlcValue> list = new ArrayList<>();
        if (nb > 1) {
            int index = 0;
            for (int i = 0; i < nb; i++) {
                String binaryString = Integer.toBinaryString(data.getByte(index));
                while (binaryString.length() % 8 != 0) {// 不足8位补位
                    binaryString = "0" + binaryString;
                }
                for (int j = binaryString.length() - 1; j >= 0; j--) {
                    if (binaryString.charAt(j) == '1') {
                        list.add(new PlcBOOL(1));
                    } else {
                        list.add(new PlcBOOL(0));
                    }
                }
                index += 1;
            }
            plcValueList = list.subList(arrayIndex, initialOffset);
        }

        return new PlcList(plcValueList);
    }

    public float swap(float value) {
        int bytes = Float.floatToIntBits(value);
        int b1 = (bytes >> 0) & 0xff;
        int b2 = (bytes >> 8) & 0xff;
        int b3 = (bytes >> 16) & 0xff;
        int b4 = (bytes >> 24) & 0xff;
        return Float.intBitsToFloat(b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0);
    }

    @Override
    public CompletableFuture<PlcWriteResponse> write(PlcWriteRequest writeRequest) {
        Map<String, PlcResponseCode> responseItemMap = new HashMap<>();
        CompletableFuture<PlcWriteResponse> future = new CompletableFuture<>();
        DefaultPlcWriteRequest request = (DefaultPlcWriteRequest) writeRequest;
        List<String> singleRespOverlengthParams = EipProtocolUtils.getWriteSingleRespOverlengthParams(writeRequest,
                configuration);
        // 多个请求报文超长分割，并去除单个请求报文超长的集合
        List<List<String>> noSingleRespOverlengthParams = EipProtocolUtils
                .getWriteNoSingleRespOverlengthParams(writeRequest, configuration);
        // logger.info("Number of write message groups is " + noSingleRespOverlengthParams.size());
        for (int i = 0; i < singleRespOverlengthParams.size(); i++) {
            String fieldName = singleRespOverlengthParams.get(i);
            // 单个数据超长 分段请求 合并结果
            final EipField field = (EipField) request.getField(fieldName);
            final PlcValue value = request.getPlcValue(fieldName);
            // 计算请求数据的最大允许长度（502减公共部分）
            int allowPackageLength = calculateWriteReqAllowPackageLength(fieldName);
            int elements = 1;
            if (field.getElementNb() > 1) {
                elements = field.getElementNb();
            }
            byte[] data = encodeValue(value, field.getType(), (short) elements);
            Map<String, PlcResponseCode> plcResponseCodeMap = mergeSegmentPlcWriteResponse(fieldName, field, data,
                    allowPackageLength);
            responseItemMap.put(fieldName, plcResponseCodeMap.get(fieldName));
        }
        for (int i = 0; i < noSingleRespOverlengthParams.size(); i++) {
            List<CipWriteRequest> items = new ArrayList<>(writeRequest.getNumberOfFields());
            List<String> params = noSingleRespOverlengthParams.get(i);
            for (int j = 0; j < params.size(); j++) {
                final EipField field = (EipField) request.getField(params.get(j));
                final PlcValue value = request.getPlcValue(params.get(j));
                String tag = field.getTag();
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
                CipWriteRequest writeReq = new CipWriteRequest(requestPathSize, toAnsi(tag), field.getType(), elements,
                        data);
                items.add(writeReq);
            }
            Map<String, PlcResponseCode> plcResponseCodeMap = mergeMutiplePlcWriteResponse(params, writeRequest, items);
            responseItemMap.putAll(plcResponseCodeMap);
        }
        future.complete(new DefaultPlcWriteResponse(writeRequest, responseItemMap));
        return future;

    }

    private Map<String, PlcResponseCode> mergeMutiplePlcWriteResponse(List<String> params, PlcWriteRequest writeRequest,
            List<CipWriteRequest> items) {
        RequestTransactionManager tm = new RequestTransactionManager(1);
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        CompletableFuture<Map<String, PlcResponseCode>> future = new CompletableFuture<>();
        Map<String, PlcResponseCode> plcResponseCodeMap = new HashMap<>();
        this.sequenceCount = this.sequenceCount % 2 + 1;
        if (items.size() == 1) {

            CipExchange3 exchange = new CipExchange3(
                    this.sequenceCount,
                    items.get(0));
            SendUnitData rrdata = new SendUnitData(sessionHandle,
                    0l,
                    senderContext,
                    0l,
                    otConnectionId, exchange);

            transaction.submit(() -> context.sendRequest(rrdata)
                    .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                    .onTimeout((e) -> {
                        future.completeExceptionally(e);
                        transaction.endRequest();
                    })
                    .onError((p, e) -> {
                        future.completeExceptionally(e);
                        transaction.endRequest();
                    })
                    .check(p -> p instanceof SendUnitData).unwrap(p -> (SendUnitData) p)
                    .check(p -> p.getSessionHandle() == sessionHandle)
                    .check(p -> p.getExchange3().getService() instanceof CipWriteResponse)
                    .unwrap(p -> (CipWriteResponse) p.getExchange3().getService())
                    .handle(p -> {
                        future.complete(decodeWriteResponse(p, writeRequest, params));
                        transaction.endRequest();
                    }));
        } else {
            short nb = (short) items.size();
            int[] offsets = new int[nb];
            int offset = 2 + nb * 2;
            for (int k = 0; k < nb; k++) {
                offsets[k] = offset;
                offset += items.get(k).getLengthInBytes();
            }

            CipService[] serviceArr = new CipService[nb];
            for (int k = 0; k < nb; k++) {
                serviceArr[k] = items.get(k);
            }
            Services data = new Services(nb, offsets, serviceArr);

            SendUnitData pkt = new SendUnitData(
                    sessionHandle,
                    0l,
                    senderContext,
                    0l,
                    otConnectionId,
                    new CipExchange3(
                            this.sequenceCount,
                            new MultipleServiceRequest(data)));

            transaction.submit(() -> context.sendRequest(pkt)
                    .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                    .onTimeout((e) -> {
                        future.completeExceptionally(e);
                        transaction.endRequest();
                    })
                    .onError((p, e) -> {
                        future.completeExceptionally(e);
                        transaction.endRequest();
                    })
                    .check(p -> p instanceof SendUnitData)
                    .check(p -> p.getSessionHandle() == sessionHandle)
                    // .check(p -> p.getSenderContext() == senderContext)
                    .unwrap(p -> (SendUnitData) p)
                    .unwrap(p -> p.getExchange3().getService()).check(p -> p instanceof MultipleServiceResponse)
                    .unwrap(p -> (MultipleServiceResponse) p)
                    .check(p -> p.getServiceNb() == nb)
                    .handle(p -> {
                        future.complete(decodeWriteResponse(p, writeRequest, params));
                        // Finish the request-transaction.
                        transaction.endRequest();
                    }));
        }
        try {
            plcResponseCodeMap = future.get(RESPONSE_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return plcResponseCodeMap;
    }

    private Map<String, PlcResponseCode> singlePlcWriteResponse(PlcWriteRequest request, String fieldName) {
        RequestTransactionManager tm = new RequestTransactionManager(1);
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        CompletableFuture<Map<String, PlcResponseCode>> future = new CompletableFuture<>();
        this.sequenceCount = this.sequenceCount % 2 + 1;
        final EipField field = (EipField) request.getField(fieldName);
        final PlcValue value = request.getPlcValue(fieldName);
        String tag = field.getTag();
        int elements = 1;
        if (field.getElementNb() > 1) {
            elements = field.getElementNb();
        }
        // We need the size of the request in words (0x91, tagLength, ... tag + possible
        // pad)
        // Taking half to get word size
        boolean isArray = false;
        String tagIsolated = tag;
        if (tag.contains("[")) {
            isArray = true;
            tagIsolated = tag.substring(0, tag.indexOf("["));
        }
        int dataLength = (tagIsolated.length() + 2 + (tagIsolated.length() % 2) + (isArray ? 2 : 0));
        byte requestPathSize = (byte) (dataLength / 2);
        if (isArray) {
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            int arrayIndex = Integer.parseInt(index);
            if (arrayIndex > 255) {
                requestPathSize += 1;
            }
        }
        byte[] data = encodeValue(value, field.getType(), (short) elements);
        CipWriteRequest writeReq = new CipWriteRequest(requestPathSize, toAnsi(tag), field.getType(), elements,
                data);
        CipExchange3 exchange = new CipExchange3(
                this.sequenceCount,
                writeReq);
        SendUnitData rrdata = new SendUnitData(sessionHandle,
                0l,
                senderContext,
                0l,
                otConnectionId, exchange);
        transaction.submit(() -> context.sendRequest(rrdata)
                .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                .onTimeout((e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .onError((p, e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .check(p -> p instanceof SendUnitData).unwrap(p -> (SendUnitData) p)
                .check(p -> p.getSessionHandle() == sessionHandle)
                // .check(p -> p.getSenderContext() == senderContext)
                .check(p -> p.getExchange3().getService() instanceof CipWriteResponse)
                .unwrap(p -> (CipWriteResponse) p.getExchange3().getService())
                .handle(p -> {
                    future.complete(decodeWriteResponse(p, fieldName, 0));
                    transaction.endRequest();
                }));
        Map<String, PlcResponseCode> plcResponseCodeMap = new HashMap<>();
        try {
            plcResponseCodeMap = future.get(REQUEST_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return plcResponseCodeMap;
    }

    private Map<String, PlcResponseCode> mergeSegmentPlcWriteResponse(String fieldName, EipField plcField, byte[] data,
            int allowPackageLength) {
        Map<String, PlcResponseCode> plcResponseCodeMap = new HashMap<>();
        RequestTransactionManager tm = new RequestTransactionManager(1);
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        this.sequenceCount = this.sequenceCount % 2 + 1;
        int arrayIndex = 0;// 用于记录数组请求的起始索引
        List<CipWriteRequest> requests = new ArrayList<>();
        String tag = plcField.getTag();
        int elements = 1;
        if (plcField.getElementNb() > 1) {
            elements = plcField.getElementNb();
        }
        boolean isArray = false;
        String tagIsolated = tag;
        if (tag.contains("[")) {
            // %TEST2[0]:DINT:200
            isArray = true;
            tagIsolated = tag.substring(0, tag.indexOf("["));// 标签名称
            String index = tag.substring(tag.indexOf("[") + 1, tag.indexOf("]"));
            arrayIndex = Integer.parseInt(index);// 数组索引
        }
        if (isArray) {
            elements = 1;// 固定设置1，用于避免报文增加长度标识，导致segement-path返回错误
        }
        int segementNum = data.length / allowPackageLength;// 分几段发送
        int leftPackegeByte = data.length % allowPackageLength;// 分段后剩余数据包长度
        int segementPartNum = segementNum + (leftPackegeByte == 0 ? 0 : 1);
        int offsetStart = 0;
        int offset = 0;
        if (arrayIndex > 0) {
            // %TEST2[0]:DINT:100
            offsetStart = arrayIndex * plcField.getType().getSize();
            offset = allowPackageLength;
        }
        for (int i = 0; i < segementNum; i++) {
            int offsetStartByte1 = Integer.parseInt(String.format("%04X", offsetStart + (i * offset)).substring(0, 2),
                    16);
            int offsetStartByte2 = Integer.parseInt(String.format("%04X", offsetStart + (i * offset)).substring(2, 4),
                    16);
            int offsetByte1 = Integer.parseInt(String.format("%04X", offset + (i * allowPackageLength)).substring(0, 2),
                    16);
            int offsetByte2 = Integer.parseInt(String.format("%04X", offset + (i * allowPackageLength)).substring(2, 4),
                    16);
            byte[] segementBytes = new byte[] { (byte) 0x80, (byte) 0x03, (byte) offsetStartByte2,
                    (byte) offsetStartByte1, (byte) 0x00, (byte) 0x00, (byte) offsetByte2, (byte) offsetByte1 };
            CipWriteRequest writeReq = new CipWriteRequest(getSegmentRequestSize(tagIsolated),
                    toAnsiForArray(tagIsolated, segementBytes), plcField.getType(), elements,
                    data);
            requests.add(writeReq);
        }
        if (leftPackegeByte > 0) {
            int offsetStartByte1 = Integer
                    .parseInt(String.format("%04X", offsetStart + (segementNum * offset)).substring(0, 2), 16);
            int offsetStartByte2 = Integer
                    .parseInt(String.format("%04X", offsetStart + (segementNum * offset)).substring(2, 4), 16);
            int offsetByte1 = Integer.parseInt(String.format("%04X", leftPackegeByte).substring(0, 2), 16);
            int offsetByte2 = Integer.parseInt(String.format("%04X", leftPackegeByte).substring(2, 4), 16);
            byte[] segementBytes = new byte[] { (byte) 0x80, (byte) 0x03, (byte) offsetStartByte2,
                    (byte) offsetStartByte1, (byte) 0x00, (byte) 0x00, (byte) offsetByte2, (byte) offsetByte1 };
            CipWriteRequest writeReq = new CipWriteRequest(getSegmentRequestSize(tagIsolated),
                    toAnsiForArray(tagIsolated, segementBytes), plcField.getType(), elements,
                    data);
            requests.add(writeReq);
        }
        CompletableFuture<Map<String, PlcResponseCode>> future = new CompletableFuture<>();
        short nb = (short) requests.size();
        int[] offsets = new int[nb];
        int offset1 = 2 + nb * 2;
        for (int i = 0; i < nb; i++) {
            offsets[i] = offset1;
            offset += requests.get(i).getLengthInBytes();
        }
        CipService[] serviceArr = new CipService[nb];
        for (int i = 0; i < nb; i++) {
            serviceArr[i] = requests.get(i);
        }
        Services servicesData = new Services(nb, offsets, serviceArr);
        // Encapsulate the data

        SendUnitData pkt = new SendUnitData(
                sessionHandle,
                0l,
                senderContext,
                0l,
                otConnectionId,
                new CipExchange3(
                        this.sequenceCount,
                        new MultipleServiceRequest(servicesData)));

        transaction.submit(() -> context.sendRequest(pkt)
                .expectResponse(EipPacket.class, REQUEST_TIMEOUT)
                .onTimeout((e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .onError((p, e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .check(p -> p instanceof SendUnitData)
                .check(p -> p.getSessionHandle() == sessionHandle)
                // .check(p -> p.getSenderContext() == senderContext)
                .unwrap(p -> (SendUnitData) p)
                .unwrap(p -> p.getExchange3().getService()).check(p -> p instanceof MultipleServiceResponse)
                .unwrap(p -> (MultipleServiceResponse) p)
                .check(p -> p.getServiceNb() == nb)
                .handle(p -> {
                    future.complete(decodeWriteResponse(p, fieldName, segementPartNum));
                    // Finish the request-transaction.
                    transaction.endRequest();
                }));

        try {
            plcResponseCodeMap = future.get(REQUEST_TIMEOUT.toMillis(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return plcResponseCodeMap;
    }

    private int calculateWriteReqAllowPackageLength(String fieldName) {
        int allowDataBytes = 0;
        int prefixBytelength = 48;
        int dataLengthBytes = 2;
        int paramDataTypeBytes = 2;// 数据类型
        int paramLengthBytes = 2;// 请求参数长度
        int paramBytes = fieldName.getBytes().length;// 请求参数长度
        int dataTypeBytes = 2;// 写入数据类型 2byte
        int reversedBytes = 2;// 固定 0100 2byte
        int endBytes = 2;// 固定0000 //2byte
        allowDataBytes = EIPConfiguration.UCMM_PACKAGE_LENGTH - (prefixBytelength + dataLengthBytes + paramDataTypeBytes
                + paramLengthBytes + paramBytes + dataTypeBytes + reversedBytes + endBytes);
        if (allowDataBytes % 8 != 0) {
            allowDataBytes = allowDataBytes - (allowDataBytes % 8); // 向下取整
        }
        return allowDataBytes;
    }

    private int calculateWriteReqPackageLength(List<PlcField> plcFields) {
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
            String tag = eipField.getTag();// 获取读取的变量名称
            // 根据变量名称计算Byte长度
            int serviceTypeByte1 = 1;// 服务标识
            int requestPathSizeByte1 = 1;// 请求路径
            int dataTypeByte1 = 1;// 扩展符号
            int datalengthByte1 = 1;// 单个参数长度
            int dataConentByte = tag.length();//
            int elementNbByte = 2;
            cipReadRequestByteLength = serviceTypeByte1 + requestPathSizeByte1 + dataTypeByte1 + datalengthByte1
                    + dataConentByte + elementNbByte;
            packageSize += cipReadRequestByteLength;
        }
        int endBytelength = 4;
        packageSize += endBytelength;
        return packageSize;
    }

    private Map<String, PlcResponseCode> decodeWriteResponse(CipService p, PlcWriteRequest writeRequest,
            List<String> params) {
        Map<String, PlcResponseCode> responses = new HashMap<>();
        if (p instanceof CipWriteResponse) {
            CipWriteResponse resp = (CipWriteResponse) p;
            String fieldName = writeRequest.getFieldNames().iterator().next();
            EipField field = (EipField) writeRequest.getField(fieldName);
            responses.put(fieldName, decodeResponseCode(resp.getStatus()));
        } else if (p instanceof MultipleServiceResponse) {
            MultipleServiceResponse resp = (MultipleServiceResponse) p;
            int nb = resp.getServiceNb();
            CipService[] arr = new CipService[nb];
            ReadBufferByteBased read = new ReadBufferByteBased(resp.getServicesData());
            int total = (int) read.getTotalBytes();
            for (int i = 0; i < nb; i++) {
                int length = 0;
                int offset = resp.getOffsets()[i] - resp.getOffsets()[0];
                if (i == nb - 1) {
                    length = total - offset; // Get the rest if last
                } else {
                    length = resp.getOffsets()[i + 1] - offset - resp.getOffsets()[0]; // Calculate length with offsets
                }
                ReadBuffer serviceBuf = new ReadBufferByteBased(read.getBytes(offset, offset + length), true);
                CipService service = null;
                try {
                    service = CipServiceIO.staticParse(read, length);
                    arr[i] = service;
                } catch (ParseException e) {
                    throw new PlcRuntimeException(e);
                }
            }
            Services services = new Services(nb, resp.getOffsets(), arr);
            Iterator<String> it = writeRequest.getFieldNames().iterator();
            for (int i = 0; i < params.size(); i++) {
                String fieldName = params.get(i);
                EipField field = (EipField) writeRequest.getField(fieldName);
                PlcValue plcValue = null;
                if (services.getServices()[i] instanceof CipWriteResponse) {
                    CipWriteResponse writeResponse = (CipWriteResponse) services.getServices()[i];
                    PlcResponseCode code = decodeResponseCode(writeResponse.getStatus());
                    responses.put(fieldName, code);
                }
            }
        }
        return responses;
    }

    private Map<String, PlcResponseCode> decodeWriteResponse(CipService p, String fieldName, int segementNum) {
        Map<String, PlcResponseCode> responses = new HashMap<>();

        if (p instanceof CipWriteResponse) {
            CipWriteResponse resp = (CipWriteResponse) p;
            responses.put(fieldName, decodeResponseCode(resp.getStatus()));
            return responses;
        } else if (p instanceof MultipleServiceResponse) {
            MultipleServiceResponse resp = (MultipleServiceResponse) p;
            int nb = resp.getServiceNb();
            CipService[] arr = new CipService[nb];
            ReadBufferByteBased read = new ReadBufferByteBased(resp.getServicesData());
            int total = (int) read.getTotalBytes();
            for (int i = 0; i < nb; i++) {
                int length = 0;
                int offset = resp.getOffsets()[i] - resp.getOffsets()[0];
                if (i == nb - 1) {
                    length = total - offset; // Get the rest if last
                } else {
                    length = resp.getOffsets()[i + 1] - offset - resp.getOffsets()[0]; // Calculate length with offsets
                }
                ReadBuffer serviceBuf = new ReadBufferByteBased(read.getBytes(offset, offset + length), true);
                CipService service = null;
                try {
                    service = CipServiceIO.staticParse(read, length);
                    arr[i] = service;
                } catch (ParseException e) {
                    throw new PlcRuntimeException(e);
                }
            }
            Services services = new Services(nb, resp.getOffsets(), arr);
            for (int i = 0; i < segementNum; i++) {
                if (services.getServices()[i] instanceof CipWriteResponse) {
                    CipWriteResponse writeResponse = (CipWriteResponse) services.getServices()[i];
                    PlcResponseCode code = decodeResponseCode(writeResponse.getStatus());
                    if (code == PlcResponseCode.INTERNAL_ERROR) {
                        responses.put(fieldName, code);
                        break;
                    }
                    responses.put(fieldName, code);
                }
            }
            return responses;
        }
        return null;
    }

    private byte[] encodeValue(PlcValue value, CIPDataTypeCode type, short elements) {
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
                            if (stringBuffer.toString().getBytes().length % 2 != 0) { // 判断数据byte长度是否未偶数位,奇数补位
                                buffer = ByteBuffer.allocate(
                                        type.getSize() * 2 + stringBuffer.toString().getBytes().length / 2 * 2 + 2)
                                        .order(ByteOrder.LITTLE_ENDIAN);
                                buffer_string.putShort((short) (stringBuffer.toString().getBytes().length / 2 * 2 + 2));// STRING类型添加数据长度标志位
                            } else {
                                buffer = ByteBuffer
                                        .allocate(type.getSize() * 2 + stringBuffer.toString().getBytes().length)
                                        .order(ByteOrder.LITTLE_ENDIAN);
                                buffer_string.putShort((short) (stringBuffer.toString().getBytes().length));// STRING类型添加数据长度标志位
                            }
                            buffer.put(buffer_string.array());// 数据长度标志位
                            buffer.put(stringBuffer.toString().getBytes());// data内容
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
                    if (value.getString().getBytes().length % 2 != 0) { // 判断数据byte长度是否未偶数位,奇数补位
                        buffer = ByteBuffer
                                .allocate(type.getSize() * 2 + value.getString().getBytes().length / 2 * 2 + 2)
                                .order(ByteOrder.LITTLE_ENDIAN);
                        buffer_string.putShort((short) (value.getString().getBytes().length / 2 * 2 + 2));// STRING类型添加数据长度标志位
                    } else {
                        buffer = ByteBuffer.allocate(type.getSize() * 2 + value.getString().getBytes().length)
                                .order(ByteOrder.LITTLE_ENDIAN);
                        buffer_string.putShort((short) (value.getString().getBytes().length));// STRING类型添加数据长度标志位
                    }
                    buffer.put(buffer_string.array());// 数据长度标志位
                    buffer.put(value.getString().getBytes());// data内容
                    break;
                default:
                    break;
            }
        }
        return buffer.array();

    }

    private PlcResponseCode decodeResponseCode(int status) {
        // TODO other status
        switch (status) {
            case 0:
                return PlcResponseCode.OK;
            default:
                return PlcResponseCode.INTERNAL_ERROR;
        }
    }

    @Override
    public void close(ConversationContext<EipPacket> context) {
        logger.debug("Sending UnregisterSession EIP Pakcet");
        context.sendRequest(new EipDisconnectRequest(sessionHandle, 0L, emptySenderContext, 0L)); // Unregister gets no
        this.CloseRequest(context);
        // response
        logger.debug("Unregistred Session {}", sessionHandle);
    }
}
