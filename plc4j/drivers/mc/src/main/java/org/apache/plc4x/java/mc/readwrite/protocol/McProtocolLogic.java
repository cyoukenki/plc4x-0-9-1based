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
package org.apache.plc4x.java.mc.readwrite.protocol;

import org.apache.plc4x.java.api.exceptions.PlcRuntimeException;
import org.apache.plc4x.java.api.messages.*;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.mc.readwrite.*;
import org.apache.plc4x.java.mc.readwrite.configuration.MCConfiguration;
import org.apache.plc4x.java.mc.readwrite.field.McField;
import org.apache.plc4x.java.mc.readwrite.types.Devicecode;
import org.apache.plc4x.java.mc.readwrite.types.McDataType;
import org.apache.plc4x.java.mc.readwrite.util.ConvertUtils;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.*;
import org.apache.plc4x.java.spi.messages.utils.ResponseItem;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.spi.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.*;

public class McProtocolLogic extends Plc4xProtocolBase<McPacket> implements HasConfiguration<MCConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(McProtocolLogic.class);
    public static final Duration REQUEST_TIMEOUT = Duration.ofMillis(10000);

    private MCConfiguration configuration;

    private final AtomicInteger transactionCounterGenerator = new AtomicInteger(10);
    private RequestTransactionManager tm;

    @Override
    public void setConfiguration(MCConfiguration configuration) {
        this.configuration = configuration;
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<McPacket> context) {

    }

    @Override
    public CompletableFuture<PlcReadResponse> read(PlcReadRequest readRequest) {
        CompletableFuture<PlcReadResponse> future = new CompletableFuture<>();
        DefaultPlcReadRequest request = (DefaultPlcReadRequest) readRequest;
        Map<String, ResponseItem<PlcValue>> map = new HashMap<>();
        if (request.getFieldNames().size() == 1) {
            String fieldName = request.getFieldNames().iterator().next();
            McField mcField = (McField) request.getField(fieldName);
            BigInteger wordStart = BigInteger.valueOf(Long.parseLong(charToHex(String.format("%06d", Integer.valueOf(mcField.getWordStart()))), 16));
            Devicecode deviceCode = mcField.getDeviceCode();
            McDataType dataType = mcField.getDataType();
            int quantity = mcField.getQuantity();
            long quantityRes = calculateQuantity(dataType, mcField.getQuantity());//根据读取数量计算plc中数据范围
            ReadSubcommandType readSubcommandType;
            if (dataType == McDataType.BOOL) {
                readSubcommandType = new Readbybit(deviceCode, wordStart, quantityRes);
            } else {
                readSubcommandType = new Readbyword(deviceCode, wordStart, quantityRes);
            }
            CommandType command = new Read(readSubcommandType);
            McPacket mcRequest = new McRequest(command);
            RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
            transaction.submit(() -> context.sendRequest(mcRequest)
                .expectResponse(McPacket.class, REQUEST_TIMEOUT)
                .onTimeout((e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .onError((p, e) ->
                {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .handle(p -> {
                    Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
                    // only 1 field
                    PlcResponseCode code = null;
                    PlcValue plcValue = null;
                    if (p instanceof McResponse) {
                        EndcodeType endcodeStruct = ((McResponse) p).getEndcode();
                        String endCodeStr = hexToChar(Long.toHexString(endcodeStruct.getEndcode()));
                        if (endCodeStr.equals("0000")) {
                            //正确码
                            byte[] data = ((Normalendcode) endcodeStruct).getData();//数据
//                            ByteBuf byteBuf = Unpooled.wrappedBuffer(data);
                            plcValue = parsePlcValue(mcField, data, dataType, quantity);
                            code = PlcResponseCode.OK;
                        } else {
                            //返回错误
                            code = PlcResponseCode.INTERNAL_ERROR;
                        }
                    }
                    // Prepare the response.
                    PlcReadResponse response = new DefaultPlcReadResponse(request,
                        Collections.singletonMap(fieldName, new ResponseItem<>(code, plcValue)));
                    future.complete(response);
                    // Finish the request-transaction.
                    transaction.endRequest();
                }));
        } else {
            future.completeExceptionally(new PlcRuntimeException("Mc only supports single filed requests"));
        }
        return future;
    }


    @Override
    public CompletableFuture<PlcWriteResponse> write(PlcWriteRequest writeRequest) {
        // D:20:ULINT[1]
        // WB:0.01:BOOL[1]
        CompletableFuture<PlcWriteResponse> future = new CompletableFuture<>();
        DefaultPlcWriteRequest request = (DefaultPlcWriteRequest) writeRequest;
        List<McPacket> items = new ArrayList<>(writeRequest.getNumberOfFields());
        for (String fieldName : request.getFieldNames()) {
            logger.info("---------------------" + fieldName);
            McField mcField = (McField) request.getField(fieldName);
            BigInteger wordStart = BigInteger.valueOf(Long.parseLong(charToHex(String.format("%06d", Integer.valueOf(mcField.getWordStart()))), 16));
            Devicecode deviceCode = mcField.getDeviceCode();
            McDataType dataType = mcField.getDataType();
            int quantity = mcField.getQuantity();
            final PlcValue value = request.getPlcValue(fieldName);
            if (dataType == McDataType.STRING) {
                if (value instanceof PlcSTRING) {
                    int length = ((PlcSTRING) value).getLength();
                    if (length % 2 != 0) {
                        quantity = length / 2 + 1; // 如果原始长度是奇数，增加1
                    } else {
                        quantity = length / 2;
                    }
                } else {
                    quantity = mcField.getQuantity();
                }
            }
            long quantityRes = calculateQuantity(dataType, quantity);//根据读取数量计算plc中数据范围
            byte[] data = encodeValue(value, dataType, mcField.getQuantity());

            WriteSubcommandType readSubcommandType = null;
            if (dataType == McDataType.BOOL) {
                readSubcommandType = new Writebybit(deviceCode, wordStart, quantityRes, data);
            } else {
                readSubcommandType = new Writebyword(deviceCode, wordStart, quantityRes, data);
            }


            CommandType command = new Write(readSubcommandType);
            McPacket mcRequest = new McRequest(command);
            RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
            items.add(mcRequest);
        }
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        if (items.size() == 1) {
            transaction.submit(() -> context.sendRequest(items.get(0))
                .expectResponse(McPacket.class, REQUEST_TIMEOUT)
                .onTimeout((e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .onError((p, e) ->
                {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .handle(p -> {
                    Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
                    // only 1 field
                    PlcResponseCode code = null;
                    PlcValue plcValue = null;
                    Map<String, PlcResponseCode> responses = new HashMap<>();
                    String fieldName = writeRequest.getFieldNames().iterator().next();
                    if (p instanceof McResponse) {
                        EndcodeType endcodeStruct = ((McResponse) p).getEndcode();
                        String endCodeStr = hexToChar(Long.toHexString(endcodeStruct.getEndcode()));
                        if (endCodeStr.equals("0000")) {
                            responses.put(fieldName, PlcResponseCode.OK);
                        } else {
                            //返回错误
                            responses.put(fieldName, PlcResponseCode.OK);
                        }
                    } else {
                        responses.put(fieldName, PlcResponseCode.INTERNAL_ERROR);
                    }
                    future.complete(new DefaultPlcWriteResponse(writeRequest, responses));
                    transaction.endRequest();
                }));
        }
        return future;
    }

    private byte[] encodeValue(PlcValue value, McDataType type, int elements) {
        byte[] data;
        ByteBuffer buffer;
        String contentValue = "";
        if (type == McDataType.STRING) {
            if (value instanceof PlcList) {
                buffer = ByteBuffer.allocate(((PlcList) value).getLength() * 4).order(ByteOrder.BIG_ENDIAN);
            } else {
                contentValue = value.getString();
                if (value.getString().length() % 2 != 0) {
                    contentValue += " ";
                }
                buffer = ByteBuffer.allocate(charToHex(contentValue).length()).order(ByteOrder.BIG_ENDIAN);
            }
        } else if (type == McDataType.BOOL) {
            buffer = ByteBuffer.allocate(type.getDataTypeSize() * elements).order(ByteOrder.BIG_ENDIAN);
        } else {
            buffer = ByteBuffer.allocate(type.getDataTypeSize() * elements * 2).order(ByteOrder.BIG_ENDIAN);
        }
        if (elements > 1) {
            PlcList plcList = (PlcList) value;
            List<PlcValue> plcValueList = plcList.getList();
            for (int i = 0; i < elements; i++) {
                PlcValue plcValue = plcValueList.get(i);
                switch (type) {
                    case INT:
                    case WORD:
                        buffer.put(String.format("%04x", Short.valueOf(plcValue.getString())).getBytes());
                        break;
                    case UINT:
                        buffer.put(String.format("%04x", Integer.valueOf(plcValue.getString())).getBytes());
                        break;
                    case DINT:
                    case DWORD:
                        buffer.put(reverseResult(String.format("%08x", Integer.valueOf(plcValue.getString())), 4).getBytes());
                        break;
                    case UDINT:
                        buffer.put(reverseResult(String.format("%08x", Long.valueOf(plcValue.getString())), 4).getBytes());
                        break;
                    case REAL:
                        buffer.put(reverseResult(String.format("%08x", Float.floatToRawIntBits(plcValue.getFloat())), 4).getBytes());
                        break;
                    case LREAL:
                        buffer.put(reverseResult(String.format("%016x", Double.doubleToRawLongBits(plcValue.getFloat())), 8).getBytes());
                        break;
                    case LINT:
                    case LWORD:
                        buffer.put(reverseResult(String.format("%016x", Long.valueOf(plcValue.getString())), 8).getBytes());
                        break;
                    case ULINT:
                        buffer.put(reverseResult(String.format("%016x", Long.valueOf(plcValue.getString())), 8).getBytes());
                        break;
                    case STRING:
                        buffer.put(charToHex(swapCharacters(plcValue.getString())).getBytes());
                        break;
                    case BOOL:
                        buffer.put(plcValue.getString().getBytes());
                        break;
                    default:
                        break;
                }
            }
        } else {
            switch (type) {
                case INT:
                case WORD:
                    buffer.put(String.format("%04x", Short.valueOf(value.getString())).getBytes());
                    break;
                case UINT:
                    buffer.put(String.format("%04x", Integer.valueOf(value.getString())).getBytes());
                    break;
                case DINT:
                case DWORD:
                    buffer.put(reverseResult(String.format("%08x", Integer.valueOf(value.getString())), 4).getBytes());
                    break;
                case UDINT:
                    buffer.put(reverseResult(String.format("%08x", Long.valueOf(value.getString())), 4).getBytes());
                    break;
                case REAL:
                    buffer.put(reverseResult(String.format("%08x", Float.floatToRawIntBits(value.getFloat())), 4).getBytes());
                    break;
                case LREAL:
                    buffer.put(reverseResult(String.format("%08x", Double.doubleToRawLongBits(value.getFloat())), 8).getBytes());
                    break;
                case LINT:
                case LWORD:
                    buffer.put(reverseResult(String.format("%016x", Long.valueOf(value.getString())), 8).getBytes());
                    break;
                case ULINT:
                    buffer.put(reverseResult(String.format("%016x", Long.valueOf(value.getString())), 8).getBytes());
                    break;
                case STRING:
                    buffer.put(charToHex(swapCharacters(contentValue)).getBytes());
                    break;
                case BOOL:
                    buffer.put(value.getString().getBytes());
                    break;
                default:
                    break;
            }
        }
        return buffer.array();

    }

    private PlcValue parsePlcValue(McField field, byte[] data, McDataType dataType, int quantity) {
        String result = new String(data);
        int index = 0;
        int dataSize = quantity;
        List<PlcValue> list = new ArrayList<>();
        if (dataSize == 1) {
            PlcValue plcValue = null;
            switch (dataType) {
                case UINT:
                    return new PlcUINT(Integer.parseInt(result, 16));
                case INT:
//                    return new PlcINT(Short.parseShort(result, 16));
                    return new PlcINT((short) Integer.parseInt(result, 16));
                case DINT:
                    return new PlcDINT((int) Long.parseLong(reverseResult(result, 4), 16));
                case UDINT:
//                    return new PlcDINT(Integer.parseInt(reverseResult(result, 4), 16));
                    return new PlcUDINT(Long.parseLong(reverseResult(result, 4), 16));
                case LINT:
                    return new PlcLINT(Long.parseLong(reverseResult(result, 8), 16));
                case ULINT:
                    return new PlcULINT(Long.parseLong(reverseResult(result, 8), 16));
                case REAL:
//                    return new PlcREAL(Float.intBitsToFloat(Integer.parseInt(reverseResult(result, 4), 16)));
                    return new PlcREAL(Float.intBitsToFloat((int) Long.parseLong(reverseResult(result, 4), 16)));
                case LREAL:
                    return new PlcLREAL(Double.longBitsToDouble(Long.parseLong(reverseResult(result, 8), 16)));
                case WORD:
                    return new PlcWORD(Short.parseShort(result, 16));
                case DWORD:
                    return new PlcDWORD(Integer.parseInt(reverseResult(result, 4), 16));
                case LWORD:
                    return new PlcLWORD(Long.parseLong(reverseResult(result, 8), 16));
                case STRING:
                    return new PlcSTRING(swapCharacters(hexToAscii(result)));
                case BOOL:
                    return new PlcBOOL(Byte.parseByte(result, 16));
                default:
                    return null;
            }
        } else {
            List<String> resultList = null;
            if (dataType == McDataType.BOOL) {
                resultList = ConvertUtils.splitString(result, dataType.getDataTypeSize());
            } else if (dataType == McDataType.STRING) {
                resultList = ConvertUtils.splitString(result, dataType.getDataTypeSize() * 4);
            } else {
                resultList = ConvertUtils.splitString(result, dataType.getDataTypeSize() * 2);
            }
            StringBuilder asciiData = new StringBuilder();
            for (int i = 0; i < resultList.size(); i++) {
                String resultData = resultList.get(i);
                switch (dataType) {
                    case INT:
                        list.add(new PlcINT((short) Integer.parseInt(resultData, 16)));
                        break;
                    case UINT:
                        list.add(new PlcUINT(Integer.parseInt(resultData, 16)));
                        break;
                    case DINT:
                        list.add(new PlcDINT((int) Long.parseLong(reverseResult(resultData, 4), 16)));
                        break;
                    case UDINT:
                        list.add(new PlcUDINT(Long.parseLong(reverseResult(resultData, 4), 16)));
                        break;
                    case LINT:
                        list.add(new PlcLINT(Long.parseLong(reverseResult(resultData, 8), 16)));
                        break;
                    case ULINT:
                        list.add(new PlcULINT(Long.parseLong(reverseResult(resultData, 8), 16)));
                        break;
                    case REAL:
                        list.add(new PlcREAL(Float.intBitsToFloat((int) Long.parseLong(reverseResult(resultData, 4), 16))));
                        break;
                    case LREAL:
                        list.add(new PlcLREAL(Double.longBitsToDouble(Long.parseLong(reverseResult(resultData, 8), 16))));
                        break;
                    case WORD:
                        list.add(new PlcWORD(Short.parseShort(resultData, 16)));
                        break;
                    case DWORD:
                        list.add(new PlcDWORD(Integer.parseInt(reverseResult(resultData, 4), 16)));
                        break;
                    case LWORD:
                        list.add(new PlcLWORD(Long.parseLong(reverseResult(resultData, 8), 16)));
                        break;
                    case STRING:
                        asciiData.append(swapCharacters(hexToAscii(resultData)));
                        if (i == resultList.size() - 1) {
                            list.add(new PlcSTRING(asciiData.toString()));
                        }
                        break;
                    case BOOL:
                        list.add(new PlcBOOL(Byte.parseByte(resultData, 16)));
                        break;
                    default:
                        return null;
                }
            }
            return new PlcList(list);
        }
    }


    private long calculateQuantity(McDataType dataType, int quantity) {
        int quantityRes = 0;
        switch (dataType) {
//            case SINT:
//            case USINT:
//            case BYTE:
            case BOOL:
            case INT:
            case UINT:
            case WORD:
                quantityRes = quantity;
                break;
            case DINT:
            case UDINT:
            case UDINT_BCD:
            case REAL:
            case DWORD:
                quantityRes = quantity * 2;
                break;
            case LINT:
            case ULINT:
            case LREAL:
            case LWORD:
                quantityRes = quantity * 4;
                break;
            case STRING:
                quantityRes = quantity;
                break;
        }
        return Long.parseLong(charToHex(String.format("%04x", quantityRes)), 16);
    }

    @Override
    public void close(ConversationContext<McPacket> context) {
        logger.info("close------------------------------");
    }

    @Override
    public void onDisconnect(ConversationContext<McPacket> context) {
        logger.info("onDisconnect------------------------------");
        super.onDisconnect(context);
    }
}
