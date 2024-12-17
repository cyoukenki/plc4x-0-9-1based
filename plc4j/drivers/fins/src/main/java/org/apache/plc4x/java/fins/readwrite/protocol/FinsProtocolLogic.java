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
package org.apache.plc4x.java.fins.readwrite.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.plc4x.java.api.exceptions.PlcRuntimeException;
import org.apache.plc4x.java.api.messages.*;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.api.value.PlcValue;
import org.apache.plc4x.java.fins.readwrite.*;
import org.apache.plc4x.java.fins.readwrite.configuration.FinsConfiguration;
import org.apache.plc4x.java.fins.readwrite.field.FinsField;
import org.apache.plc4x.java.fins.readwrite.types.FinsDataType;
import org.apache.plc4x.java.fins.readwrite.types.FinsDataTypeCode;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.DefaultPlcReadRequest;
import org.apache.plc4x.java.spi.messages.DefaultPlcReadResponse;
import org.apache.plc4x.java.spi.messages.DefaultPlcWriteRequest;
import org.apache.plc4x.java.spi.messages.DefaultPlcWriteResponse;
import org.apache.plc4x.java.spi.messages.utils.ResponseItem;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.spi.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.apache.plc4x.java.fins.readwrite.types.FinsDataType.STRING;

public class FinsProtocolLogic extends Plc4xProtocolBase<FinsPacket> implements HasConfiguration<FinsConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(FinsProtocolLogic.class);
    public static final Duration REQUEST_TIMEOUT = Duration.ofMillis(20000);

    //    private static final short[] emptySenderContext = new short[]{(short) 0x00, (short) 0x00, (short) 0x00,
//        (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00};
    private short[] senderContext;
    private FinsConfiguration configuration;
    private Duration requestTimeout;
    private short unitIdentifier;
    private RequestTransactionManager tm;
    private short remoteSA1;
    private short hostSA1;

    @Override
    public void setConfiguration(FinsConfiguration configuration) {
        this.requestTimeout = Duration.ofMillis(configuration.getRequestTimeout());
        this.unitIdentifier = (short) configuration.getUnitIdentifier();
        this.configuration = configuration;
        // Set the transaction manager to allow only one message at a time.
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<FinsPacket> context) {
        hostSA1 = Short.decode(String.valueOf(Integer.valueOf(context.getChannel().localAddress().toString().split(":")[0].split("\\.")[3])));
        logger.debug("Sending RegisterSession Fins Package");
        HandshakeReq connectionRequest = new HandshakeReq(0L, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00);
        context.sendRequest(connectionRequest)
            .expectResponse(FinsPacket.class, requestTimeout)
            .unwrap(p -> p)
            .check(p -> p instanceof HandshakeRes)
            .handle(p -> {
                if (p.getErrorCode() == 0L) {
                    remoteSA1 = ((HandshakeRes) p).getSA1();
                    context.fireConnected();
                    FinsDriver.isConnect = true;
                    logger.info("onConnect", "error code is 0");
                } else {
                    logger.warn("Got status errorCode [{}]", p.getErrorCode());
                }

            });
    }

    @Override
    public CompletableFuture<PlcReadResponse> read(PlcReadRequest readRequest) {
        //errorCode  ICF  RSV  VCT  DNA  DA1  DA2  SNA  SA1  SA2  SID  dataType  wordStart  bitsStart  lenght
        //需动态传入 FinsDataTypeCode,wordStart,lenght
//        ReadBitsReq readBitsReq = new ReadBitsReq(0L,(short)0x80,(short)0x00,(short)0x02,(short)0x00,remoteSA1,(short)0x00,(short)0x00,hostSA1,(short)0x00,(short)0x00, FinsDataTypeCode.D,100,(short)0x00,(short)0x00);
        CompletableFuture<PlcReadResponse> future = new CompletableFuture<>();
        DefaultPlcReadRequest request = (DefaultPlcReadRequest) readRequest;
        Map<String, ResponseItem<PlcValue>> map = new HashMap<>();
        if (request.getFieldNames().size() == 1) {
            String fieldName = request.getFieldNames().iterator().next();
            FinsField finsField = (FinsField) request.getField(fieldName);
            String wordStart = finsField.getWordStart();
            FinsDataTypeCode areaType = finsField.getAreaType();
            FinsDataType dataType = finsField.getDataType();
            int quantity = calculateQuantity(dataType, finsField.getQuantity());//根据读取数量计算plc中数据范围
            String[] split = wordStart.split("\\.");
            int wordStartInt = Integer.parseInt(split[0]);
            short bitsStart = 0;
            if (split.length == 2) {
                bitsStart = Short.parseShort(split[1]);
            }
            CommandDataStruct commandDataStruct = new ReadBitsReq(areaType, wordStartInt, (short) bitsStart, (short) quantity);
            CommunicationMessage communicationMessage = new CommunicationMessage(0L, (short) 0x80, (short) 0x00, (short) 0x02, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, commandDataStruct);
//            requests.add(readBitsReq);
            RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
            transaction.submit(() -> context.sendRequest(communicationMessage)
                .expectResponse(FinsPacket.class, REQUEST_TIMEOUT)
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
                    if (p instanceof CommunicationMessage) {
                        CommunicationMessage resp = (CommunicationMessage) p;
                        long errorCode = resp.getErrorCode();
                        if (errorCode == 0L) {
                            CommandDataStruct commandDataStruct1 = resp.getCommandDataStruct();
                            ReadBitsRes readBitsRes;
                            if (commandDataStruct1 instanceof ReadBitsRes) {
                                readBitsRes = (ReadBitsRes) commandDataStruct1;
                                byte[] data = readBitsRes.getData();
                                if (finsField.getDataType() == FinsDataType.REAL
                                    || finsField.getDataType() == FinsDataType.LREAL
                                    || finsField.getDataType() == FinsDataType.DINT
                                    || finsField.getDataType() == FinsDataType.UDINT
                                    || finsField.getDataType() == FinsDataType.UDINT_BCD
                                    || finsField.getDataType() == FinsDataType.DWORD
                                    || finsField.getDataType() == FinsDataType.LINT
                                    || finsField.getDataType() == FinsDataType.ULINT
                                    || finsField.getDataType() == FinsDataType.ULINT_BCD
                                    || finsField.getDataType() == FinsDataType.LWORD
                                ) {
                                    data = swapH2L(data);
                                }
                                ByteBuf byteBuf = Unpooled.wrappedBuffer(data);

                                plcValue = parsePlcValue(finsField, byteBuf, dataType, quantity);
                                code = PlcResponseCode.OK;
                            }

                        } else {
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
            future.completeExceptionally(new PlcRuntimeException("Fins only supports single filed requests"));
        }
        return future;
    }


    public float swap(float value) {
        int bytes = Float.floatToIntBits(value);
        int b1 = (bytes >> 0) & 0xff;
        int b2 = (bytes >> 8) & 0xff;
        int b3 = (bytes >> 16) & 0xff;
        int b4 = (bytes >> 24) & 0xff;
        return Float.intBitsToFloat(b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0);
    }

    public static byte[] swapH2L(byte[] data) {
        byte temp;
        if (data.length % 2 == 0) {
            for (int i = 0; i < data.length; i = i + 2) {
                temp = data[i];
                data[i] = data[i + 1];
                data[i + 1] = temp;
            }
        }
        return data;
    }

    public static Long bcdDataTransfer(PlcValue plcValue, FinsDataType dataType) {
        String binaryString = null;
        StringBuffer stringBuffer = new StringBuffer();
        switch (dataType) {
            case UDINT_BCD:
                binaryString = Integer.toBinaryString(plcValue.getInt());
                break;
            case UINT_BCD:
                String uintBinaryString = Integer.toBinaryString(plcValue.getShort());
                binaryString = uintBinaryString.length() > 16 ? uintBinaryString.substring(uintBinaryString.length() - 16) : uintBinaryString;
                break;
            case ULINT_BCD:
                binaryString = Long.toBinaryString(plcValue.getLong());
                break;
        }
        while (binaryString.length() % 4 != 0) {
            binaryString = "0" + binaryString;
        }
        for (int i = 0; i < binaryString.length(); i += 4) {
            String fourBits = binaryString.substring(i, Math.min(i + 4, binaryString.length()));
            // 将四位二进制数转换为十进制数
            int decimalValue = Integer.parseInt(fourBits, 2);
            stringBuffer.append(decimalValue);
        }
        return Long.parseLong(stringBuffer.toString());
    }

    public static PlcValue parseBcdData(long data, FinsDataType dataType) {
        String binaryString = Long.toBinaryString(data);
        while (binaryString.length() % 4 != 0) {
            binaryString = "0" + binaryString;
        }
        PlcValue plcValue = null;
        switch (dataType) {
            case UINT_BCD:
                plcValue = new PlcUINT(Short.parseShort(binaryString, 2));
                break;
            case UDINT_BCD:
                plcValue = new PlcUDINT(Integer.parseInt(binaryString, 2));
                break;
            case ULINT_BCD:
                plcValue = new PlcULINT(Long.parseLong(binaryString, 2));
                break;
        }
        return plcValue;
    }

    @Override
    public CompletableFuture<PlcWriteResponse> write(PlcWriteRequest writeRequest) {
        // D:20:ULINT[1]
        // WB:0.01:BOOL[1]
        CompletableFuture<PlcWriteResponse> future = new CompletableFuture<>();
        DefaultPlcWriteRequest request = (DefaultPlcWriteRequest) writeRequest;
        List<CommunicationMessage> items = new ArrayList<>(writeRequest.getNumberOfFields());
        for (String fieldName : request.getFieldNames()) {
            logger.info("---------------------" + fieldName);
            final FinsField finsField = (FinsField) request.getField(fieldName);
            final PlcValue value = request.getPlcValue(fieldName);
            String wordStart = finsField.getWordStart();
            FinsDataTypeCode areaType = finsField.getAreaType();
            FinsDataType dataType = finsField.getDataType();
            int quantity = calculateQuantity(dataType, finsField.getQuantity());//根据读取数量计算plc中数据范围
            String[] split = wordStart.split("\\.");
            int wordStartInt = Integer.parseInt(split[0]);
            short bitsStart = 0;
            if (split.length == 2) {
                bitsStart = Short.parseShort(split[1]);
            }
            byte[] data = encodeValue(value, dataType, finsField.getQuantity());

            if (finsField.getDataType() == FinsDataType.REAL
                || finsField.getDataType() == FinsDataType.LREAL
                || finsField.getDataType() == FinsDataType.DINT
                || finsField.getDataType() == FinsDataType.UDINT
                || finsField.getDataType() == FinsDataType.UDINT_BCD
                || finsField.getDataType() == FinsDataType.DWORD
                || finsField.getDataType() == FinsDataType.LINT
                || finsField.getDataType() == FinsDataType.ULINT
                || finsField.getDataType() == FinsDataType.ULINT_BCD
                || finsField.getDataType() == FinsDataType.LWORD
                || finsField.getDataType() == FinsDataType.UINT_BCD
            ) {
                data = swapH2L(data);
            }
            if (finsField.getDataType() == STRING) {
                quantity = data.length / 2;
            }
            CommandDataStruct commandDataStruct = new WriteBitsReq(areaType, wordStartInt, (short) bitsStart, (short) quantity, data);
            CommunicationMessage communicationMessage = new CommunicationMessage(0L, (short) 0x80, (short) 0x00, (short) 0x02, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, (short) 0x00, commandDataStruct);
            items.add(communicationMessage);
        }
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        if (items.size() == 1) {
            transaction.submit(() -> context.sendRequest(items.get(0))
                .expectResponse(FinsPacket.class, REQUEST_TIMEOUT)
                .onTimeout((e) -> {
                        future.completeExceptionally(e);
                        transaction.endRequest();
                    }
                )
                .onError((p, e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .check(p -> p instanceof FinsPacket).unwrap(p -> (FinsPacket) p)
                .handle(p -> {
                    String fieldName = writeRequest.getFieldNames().iterator().next();
                    Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
                    // only 1 field
                    PlcResponseCode code = null;
                    PlcValue plcValue = null;
                    Map<String, PlcResponseCode> responses = new HashMap<>();
                    if (p instanceof CommunicationMessage) {
                        CommunicationMessage resp = (CommunicationMessage) p;
                        long errorCode = resp.getErrorCode();
                        if (errorCode == 0L) {
                            CommandDataStruct commandDataStruct1 = resp.getCommandDataStruct();
                            WriteBitsRes writeBitsRes;
                            if (commandDataStruct1 instanceof WriteBitsRes) {
                                writeBitsRes = (WriteBitsRes) commandDataStruct1;

                                FinsField field = (FinsField) writeRequest.getField(fieldName);
                                int endCode = writeBitsRes.getEndCode();
                                if (endCode == 0) {
                                    responses.put(fieldName, PlcResponseCode.OK);
                                } else {
                                    responses.put(fieldName, PlcResponseCode.INTERNAL_ERROR);
                                }
                            }
                        } else {
                            responses.put(fieldName, PlcResponseCode.INTERNAL_ERROR);
                        }
                    }
                    future.complete(new DefaultPlcWriteResponse(writeRequest, responses));
                    transaction.endRequest();
                }));
        }
        return future;
    }

    public static byte[] toByteArray(short[] src) {

        int count = src.length;
        byte[] dest = new byte[count << 1];
        for (int i = 0; i < count; i++) {
            dest[i * 2] = (byte) (src[i] >> 8);
            dest[i * 2 + 1] = (byte) (src[i] >> 0);
        }

        return dest;
    }

    private PlcValue parsePlcValue(FinsField field, ByteBuf data, FinsDataType dataType, int quantity) {
        int index = 0;
        int dataSize = 0;
        if (dataType == FinsDataType.BOOL || dataType == FinsDataType.SINT || dataType == FinsDataType.USINT || dataType == FinsDataType.BYTE) {
            dataSize = quantity / (dataType.getDataTypeSize());
        } else if (dataType == STRING) {
            dataSize = 1;
        } else {
            dataSize = quantity / (dataType.getDataTypeSize() / 2);
        }
        List<PlcValue> list = new ArrayList<>();
        if (dataSize == 1) {
            PlcValue plcValue = null;
            switch (dataType) {
                case BYTE:
                    return new PlcBYTE(data.getByte(index));
                case DINT:
                    return new PlcDINT(data.getIntLE(index));
                case UDINT:
                    return new PlcUDINT(data.getUnsignedIntLE(index));
                case UDINT_BCD:
                    try {
                        plcValue = new PlcLINT(bcdDataTransfer(new PlcUDINT(data.getUnsignedIntLE(index)), FinsDataType.UDINT_BCD));
                    } catch (Exception e) {
                        logger.error("transfer UDINT_BCD data error", e.getMessage());
                    }
                    return plcValue;
                case INT:
                    return new PlcINT(data.getShort(index));
                case UINT:
                    return new PlcUINT(data.getUnsignedShort(index));
                case UINT_BCD:
                    try {
                        plcValue = new PlcUINT(bcdDataTransfer(new PlcUINT(data.getUnsignedShort(index)), FinsDataType.UINT_BCD));
                    } catch (Exception e) {
                        logger.error("transfer UINT_BCD data error", e.getMessage());
                    }
                    return plcValue;
                case LINT:
                    return new PlcLINT(data.getLongLE(index));
                case ULINT:
                    try {
                        plcValue = new PlcULINT(data.getLongLE(index));
                    } catch (Exception e) {
                        logger.error("value is out of range 0 - 18446744073709551615 for a PlcULINT Value invalid {}", e.getMessage());
                    }
                    return plcValue;
                case ULINT_BCD:
                    try {
                        plcValue = new PlcLINT(bcdDataTransfer(new PlcLINT(data.getLongLE(index)), FinsDataType.ULINT_BCD));
                    } catch (Exception e) {
                        logger.error("transfer ULINT_BCD data error", e.getMessage());
                    }
                    return plcValue;
                case SINT:
                    return new PlcSINT(data.getByte(index));
                case USINT:
                    return new PlcUSINT(data.getByte(index));
                case REAL:
                    return new PlcREAL(data.getFloatLE(index));
                case LREAL:
                    return new PlcLREAL(data.getDoubleLE(index));
                case BOOL:
                    return new PlcBOOL(data.getByte(index));
                case WORD:
                    return new PlcWORD(data.getUnsignedShort(index));
                case DWORD:
                    return new PlcDWORD(data.getUnsignedIntLE(index));
                case LWORD:
                    return new PlcLWORD(data.getLongLE(index));
                case STRING:
                    return new PlcSTRING(new String(data.array()).trim());
                default:
                    return null;
            }
        } else {
            for (int i = 0; i < dataSize; i++) {
                switch (dataType) {
                    case BYTE:
                        list.add(new PlcBYTE(data.getByte(index)));
                        index += (dataType.getDataTypeSize());
                        break;
                    case DINT:
                        list.add(new PlcDINT(data.getIntLE(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case UDINT:
                        list.add(new PlcUDINT(data.getUnsignedIntLE(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case UDINT_BCD:
                        try {
                            list.add(new PlcLINT(bcdDataTransfer(new PlcUDINT(data.getUnsignedIntLE(index)), FinsDataType.UDINT_BCD)));
                        } catch (Exception e) {
                            logger.error("transfer UDINT_BCD data error", e.getMessage());
                        }
                        index += dataType.getDataTypeSize();
                        break;
                    case INT:
                        list.add(new PlcINT(data.getShort(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case UINT:
                        list.add(new PlcUINT(data.getUnsignedShort(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case UINT_BCD:
                        try {
                            list.add(new PlcUINT(bcdDataTransfer(new PlcUINT(data.getUnsignedShort(index)), FinsDataType.UINT_BCD)));
                        } catch (Exception e) {
                            logger.error("transfer UINT_BCD data error", e.getMessage());
                        }
                        index += dataType.getDataTypeSize();
                        break;
                    case LINT:
                        list.add(new PlcLINT(data.getLongLE(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case ULINT:
                        try {
                            list.add(new PlcULINT(data.getLongLE(index)));
                        } catch (Exception e) {
                            logger.error("value is out of range 0 - 18446744073709551615 for a PlcULINT Value invalid {}", e.getMessage());
                        }
                        index += dataType.getDataTypeSize();
                        break;
                    case ULINT_BCD:
                        try {
                            list.add(new PlcLINT(bcdDataTransfer(new PlcLINT(data.getLongLE(index)), FinsDataType.ULINT_BCD)));
                        } catch (Exception e) {
                            logger.error("value is out of range 0 - 18446744073709551615 for a PlcULINT Value invalid {}", e.getMessage());
                        }
                        index += dataType.getDataTypeSize();
                        break;
                    case SINT:
                        list.add(new PlcSINT(data.getByte(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case USINT:
                        list.add(new PlcUSINT(data.getByte(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case REAL:
                        list.add(new PlcREAL(data.getFloatLE(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case LREAL:
                        list.add(new PlcLREAL(data.getDoubleLE(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case BOOL:
                        list.add(new PlcBOOL(data.getByte(index)));
                        index += (dataType.getDataTypeSize());
                        break;
                    case WORD:
                        list.add(new PlcWORD(data.getUnsignedShort(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case DWORD:
                        list.add(new PlcDWORD(data.getUnsignedIntLE(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case LWORD:
                        list.add(new PlcLWORD(data.getLongLE(index)));
                        index += dataType.getDataTypeSize();
                        break;
                    case STRING:
                        list.add(new PlcSTRING(new String(data.array()).trim()));
                        index += dataType.getDataTypeSize();
                        break;
                    default:
                        return null;
                }
            }
            return new PlcList(list);
        }
    }

    private int calculateQuantity(FinsDataType dataType, int quantity) {
        int quantityRes = 0;
        switch (dataType) {
            case SINT:
            case USINT:
            case BYTE:
            case BOOL:
            case INT:
            case UINT:
            case UINT_BCD:
            case WORD:
            case STRING:
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
            case ULINT_BCD:
            case LREAL:
            case LWORD:
                quantityRes = quantity * 4;
                break;
        }
        return quantityRes;
    }

    private byte[] encodeValue(PlcValue value, FinsDataType type, int elements) {
        ByteBuffer buffer = ByteBuffer.allocate(type.getDataTypeSize() * elements).order(ByteOrder.LITTLE_ENDIAN);
        StringBuffer stringBuffer = new StringBuffer();
        String[] asciiValues = new String[0];
        if (type == STRING) {
            String asciiValue;
            if (value instanceof PlcList) {
                PlcList plcList = (PlcList) value;
                List<PlcValue> plcValueList = plcList.getList();
                for (int i = 0; i < plcValueList.size(); i++) {
                    stringBuffer.append(plcValueList.get(i).getString().trim());
                }
                asciiValue = stringBuffer.toString();
            } else {
                asciiValue = value.getString().trim();
            }
            asciiValues = splitString(asciiValue, 2).toArray(new String[]{});//将字符串分割成两个字符一组的数据
            buffer = ByteBuffer.allocate(type.getDataTypeSize() * 2 * asciiValues.length).order(ByteOrder.LITTLE_ENDIAN);
            for (int i = 0; i < asciiValues.length; i++) {
                if (elements > 1 && i >= elements) {
                    break;
                }
                buffer.put(asciiValues[i].getBytes());//data内容
            }
            return buffer.array();
        }
        if (elements > 1) {
            PlcList plcList = (PlcList) value;
            List<PlcValue> plcValueList = plcList.getList();
            for (int i = 0; i < elements; i++) {
                PlcValue plcValue = plcValueList.get(i);
                switch (type) {
                    case BYTE:
                        buffer.put(new PlcBYTE(plcValue.getString().trim()).getByte());
                        break;
                    case SINT:
                        buffer.put(new PlcSINT(plcValue.getString().trim()).getByte());
                        break;
                    case USINT:
                        buffer.put(new PlcUSINT(plcValue.getString().trim()).getByte());
                        break;
                    case INT:
                    case WORD:
                        buffer.putShort(Short.reverseBytes(plcValue.getShort()));
                        break;
                    case UINT:
                        buffer.putShort(Short.reverseBytes(new PlcUINT(plcValue.getString().trim()).getShort()));
                        break;
                    case DINT:
                    case DWORD:
                        buffer.putInt(plcValue.getInteger());
                        break;
                    case UDINT:
                        buffer.putInt(new PlcUDINT(plcValue.getString()).getInteger());
                        break;
                    case REAL:
                        buffer.putFloat(plcValue.getFloat());
                        break;
                    case LREAL:
                        buffer.putDouble(plcValue.getDouble());
                        break;
                    case LINT:
                    case LWORD:
                        buffer.putLong(plcValue.getLong());
                        break;
                    case ULINT:
                        buffer.putLong(new PlcULINT(plcValue.getString().trim()).getLong());
                        break;
                    case BOOL:
                        buffer.put(new PlcBOOL(plcValue.getString().trim()).getByte());
                        break;
                    case UINT_BCD:
                        buffer.putShort((short) Integer.parseInt(String.valueOf(parseBcdData(plcValue.getShort(), FinsDataType.UINT_BCD).getShort()), 16));
                        break;
                    case UDINT_BCD:
                        buffer.putInt((int) Long.parseLong(String.valueOf(parseBcdData(plcValue.getInt(), FinsDataType.UDINT_BCD).getLong()), 16));
                        break;
                    case ULINT_BCD:
                        buffer.putLong(Long.parseUnsignedLong(String.valueOf(parseBcdData(plcValue.getLong(), FinsDataType.ULINT_BCD).getLong()), 16));
                        break;
                    default:
                        break;
                }
            }
        } else {
            switch (type) {
                case BYTE:
                    buffer.put(new PlcBYTE(value.getString().trim()).getByte());
                    break;
                case SINT:
                    buffer.put(new PlcSTRING(value.getString().trim()).getByte());
                    break;
                case USINT:
                    buffer.put(new PlcUSINT(value.getString().trim()).getByte());
                    break;
                case INT:
                case WORD:
                    buffer.putShort(Short.reverseBytes(value.getShort()));
                    break;
                case UINT:
                    buffer.putShort(Short.reverseBytes(new PlcUINT(value.getString().trim()).getShort()));
                    break;
                case DINT:
                case DWORD:
                    buffer.putInt(new PlcSTRING(value.getString().trim()).getInteger());
                    break;
                case UDINT:
                    buffer.putInt(new PlcUDINT(value.getString().trim()).getInteger());
                    break;
                case REAL:
                    buffer.putFloat(new PlcSTRING(value.getString().trim()).getFloat());
                    break;
                case LREAL:
                    buffer.putDouble(new PlcSTRING(value.getString().trim()).getDouble());
                    break;
                case LINT:
                case LWORD:
                    buffer.putLong(new PlcSTRING(value.getString().trim()).getLong());
                    break;
                case ULINT:
                    buffer.putLong(new PlcULINT(value.getString().trim()).getLong());
                    break;
                case BOOL:
                    buffer.put(new PlcBOOL(value.getString().trim()).getByte());
                    break;
                case UINT_BCD:
                    buffer.putShort((short) Integer.parseInt(String.valueOf(parseBcdData(value.getShort(), FinsDataType.UINT_BCD).getShort()), 16));
                    break;
                case UDINT_BCD:
                    buffer.putInt((int) Long.parseLong(String.valueOf(parseBcdData(value.getInt(), FinsDataType.UDINT_BCD).getInt()), 16));
                    break;
                case ULINT_BCD:
                    buffer.putLong(Long.parseUnsignedLong(String.valueOf(parseBcdData(value.getLong(), FinsDataType.ULINT_BCD).getLong()), 16));
                    break;
                default:
                    break;
            }
        }
        return buffer.array();

    }

    @Override
    public void close(ConversationContext<FinsPacket> context) {
        context.fireDisconnected();
        logger.info("close------------------------------");
    }

    @Override
    public void onDisconnect(ConversationContext<FinsPacket> context) {
        logger.info("onDisconnect------------------------------");
        context.fireDisconnected();
        super.onDisconnect(context);
    }


    public List<String> splitString(String value, int num) {
        char[] chars = value.toCharArray(); // 将字符串转换成char数组

        int length = chars.length; // 获取字符数组的长度

        List<String> resultList = new ArrayList<>(); // 存放结果的列表

        for (int i = 0; i < length; i += 2) { // 从第二个字符开始，每次跨越两个字符
            StringBuilder sb = new StringBuilder(); // 创建StringBuilder对象，用于构造子字符串

            if ((i + 1) >= length) { // 当最后一个字符无法与之配对时，直接添加到结果列表
                sb.append(chars[i]);
                resultList.add(sb.toString());
                break;
            } else {
                sb.append(chars[i]).append(chars[i + 1]); // 连接前后两个字符并添加到StringBuilder对象

                resultList.add(sb.toString()); // 将StringBuilder对象转换成字符串并添加到结果列表
            }
        }
        return resultList;
    }
}
