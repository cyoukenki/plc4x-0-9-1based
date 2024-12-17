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
package org.apache.plc4x.java.secsgem.readwrite.protocol;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.messages.PlcWriteResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.configuration.SecsgemConfiguration;
import org.apache.plc4x.java.secsgem.readwrite.constant.ConnectionStatus;
import org.apache.plc4x.java.secsgem.readwrite.internal.DataHolder;
import org.apache.plc4x.java.secsgem.readwrite.model.*;
import org.apache.plc4x.java.secsgem.readwrite.task.*;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode2;
import org.apache.plc4x.java.secsgem.readwrite.util.*;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.*;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.spi.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

import static org.apache.plc4x.java.secsgem.readwrite.constant.ServerConstants.*;
import static org.apache.plc4x.java.secsgem.readwrite.util.SecsDataUtils.*;

public class SecsgemProtocolLogic extends Plc4xProtocolBase<SecsPacket> implements HasConfiguration<SecsgemConfiguration>, Consumer<Map<String, Object>> {

    private static final Logger logger = LoggerFactory.getLogger(SecsgemProtocolLogic.class);
    private short[] senderContext;
    private SecsgemConfiguration configuration;

    private RequestTransactionManager tm;
    private static ScheduledExecutorService scheduler;
    private static ScheduledExecutorService traceScheduler;
    private final int poolSizeScheduler = 10;
    private static final int scheduleDelayTime = 1000;
    private Map<String, Boolean> remoteMap = new HashMap<>();
    private DefaultServerConnectorListener defaultServerConnectorListener;
    public int accept_status = ACCEPT_YES;
    public int alarmStatusReadInterval = 2000;//告警状态读取周期
    private BaseSettingInfo baseSettingInfo;
    private Map<String, ScheduledFuture> scheduledFutureMap = new HashMap<>();
    private Map<Object, ScheduledExecutorService> scheduledExecutorServiceHashMap = new HashMap<>();
    private Map<String, Boolean> ppidValidMap = new HashMap<>();
    public static int eventDataId = 1;
    private SecsPacket secsPacket;
    public static ObjectMapper mapper = new ObjectMapper();
    public static Gson gson = new Gson();

    public SecsgemProtocolLogic() {
        this.scheduler = Executors.newScheduledThreadPool(poolSizeScheduler,
            new BasicThreadFactory.Builder()
                .namingPattern("secsgem-scheduling-thread-%d")
                .daemon(false)
                .build()
        );
        this.traceScheduler = Executors.newScheduledThreadPool(poolSizeScheduler,
            new BasicThreadFactory.Builder()
                .namingPattern("secsgem-trace-scheduling-thread-%d")
                .daemon(false)
                .build()
        );
    }

    @Override
    public void setConfiguration(SecsgemConfiguration configuration) {
        this.configuration = configuration;
        // Set the transaction manager to allow only one message at a time.
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<SecsPacket> context) {
        logger.info("SecsgemProtocolLogic-------onConnect()");
    }

    @Override
    public void onDisconnect(ConversationContext<SecsPacket> context) {
        logger.info("SecsgemProtocolLogic-------onDisconnect()");
        super.onDisconnect(context);
    }

    @Override
    public void close(ConversationContext<SecsPacket> context) {
        logger.info("SecsgemProtocolLogic-------close()");
    }

    @Override
    public CompletableFuture<PlcReadResponse> read(PlcReadRequest readRequest) {
        Map<String, HostChannelInfo> channelMap = DataHolder.getInstance().getChannelMap();
        return super.read(readRequest);
    }

    @Override
    public CompletableFuture<PlcWriteResponse> write(PlcWriteRequest writeRequest) {
        return super.write(writeRequest);
    }

    @Override
    protected void decode(ConversationContext<SecsPacket> context, SecsPacket secsPacket) {
        this.secsPacket = secsPacket;
        defaultServerConnectorListener = context.getDefaultServerConnectorListener();//获取当前通道的监听器
        int streamFunction = secsPacket.getStreamFunction(); //请求类型值
        short stype = secsPacket.getStype();//stype
        int deviceID = secsPacket.getDeviceID();//当前请求header携带得deviceID
        short pType = secsPacket.getPType();//pType
        int lengthInBytes = secsPacket.getLengthInBytes();
        logger.info("request header info==streamFunction:[" + streamFunction + "]stype:[" + stype + "]deviceID:[" + deviceID + "]pType:[" + pType + "]lengthInBytes:[" + lengthInBytes + "]");
        SocketAddress socketAddress = context.getChannel().remoteAddress();
        String hostAddress = ((InetSocketAddress) socketAddress).getAddress().getHostAddress(); //当前连接host 的地址信息
        Map<String, HostChannelInfo> channelMap = DataHolder.getInstance().getChannelMap();//获取当前连接通道集合
        String deviceId = DataHolder.getInstance().getBaseSettingInfo().getDeviceId();//获取基础设置配置的deviceId
        if (StringUtils.isEmpty(deviceId)) {
            logger.error("deviceId is empty, please check your instance settting");
            return;
        }
        int deviceIdSet = Integer.parseInt(deviceId);//控制台device id 设置
        if (channelMap.get(hostAddress) != null) { //通道集合包含当前请求host地址
            HostChannelInfo hostChannelInfo = channelMap.get(hostAddress);//获取通道信息
            ConnectionStatus connectionStatus = hostChannelInfo.getConnectionStatus();//获取状态
            if (accept_status == ACCEPT_YES) { //状态判断暂未使用
                if (connectionStatus == ConnectionStatus.CONNECTED_NOT_SELECTED) { //判断状态 已连接未select
                    if (streamFunction != 0 || stype != 1) {//判断streamFunction 和 stype 不满足条件拒绝连接
                        //1:send reject req 2:socket close
                        EqRejectRepuest eqRejectRepuest = new EqRejectRepuest(deviceID, pType, (short) 0x07, 1);
                        context.sendRequest(eqRejectRepuest);
                        sendWebSocketMessage(eqRejectRepuest);
                        //WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, "Sending control message - RejectRepuest  sent, system byte = " + lengthInBytes);
                        changeStatus(hostAddress, ConnectionStatus.NOT_CONNECTED);
                        logger.info("hostAddress:" + ConnectionStatus.NOT_CONNECTED + " cause：streamFunction or stype is not correct");
                    }
                    if (lengthInBytes != 14) {  // 校验长度!=14，如有错误执行 socket close
                        changeStatus(hostAddress, ConnectionStatus.NOT_CONNECTED);
                        logger.info("hostAddress:" + ConnectionStatus.NOT_CONNECTED + " cause：lengthInBytes is not correct");
                    }
                }
                if (!(secsPacket instanceof S7F3Request) && !(secsPacket instanceof S7F23Request)) {
                    //清除S7F1指令记录历史
                    ppidValidMap.clear();
                }

                if (secsPacket instanceof SelectRequest) {  //匹配指令请求
                    if (streamFunction == 0) {
                        switch (stype) {//根据stype 判断控制指令类型
                            case 1://SelectRequest 响应select请求
                                //请求报文示例：00 00 00 0A FF FF 00 00 00 01 00 00 00 49
                                //响应报文示例：00 00 00 0A FF FF 00 00 00 02 00 00 00 49
                                //WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, "Received select request,system byte =" + lengthInBytes);
                                SelectResponse selectResponse = new SelectResponse(secsPacket.getDeviceID(), secsPacket.getPType(), (short) 0x02, secsPacket.getSystemBytes());//selectResponse  stype标记位 = 2
                                context.sendToWire(selectResponse);
                                sendWebSocketMessage(selectResponse);
                                DataHolder.getInstance().getDeviceMap().put(hostAddress, deviceID);//select后保存设备信息
                                DataHolder.getInstance().getChannelMap().get(hostAddress).setContext(context);//保存通道信息
                                changeStatus(hostAddress, ConnectionStatus.SELECTED);//改变eq对应状态位SELECT
                                logger.info("hostAddress:" + ConnectionStatus.SELECTED);
                                break;
                            case 3://HostDeselectRequest 响应deslect请求   hostDeselectResponse  stype标记位 = 4
                                //请求报文示例：00 00 00 0A FF FF 00 00 00 03 00 00 00 59
                                //响应报文示例：00 00 00 0A FF FF 00 00 00 04 00 00 00 59
                                //WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, "Received deslect request,system byte =" + lengthInBytes);
                                HostDeselectResponse hostDeselectResponse = new HostDeselectResponse(secsPacket.getDeviceID(), secsPacket.getPType(), (short) 0x04, secsPacket.getSystemBytes());
                                context.sendToWire(hostDeselectResponse);
                                sendWebSocketMessage(hostDeselectResponse);
                                changeStatus(hostAddress, ConnectionStatus.NOT_CONNECTED);//改变eq状态位NOT_CONNECTED
                                logger.info("hostAddress:" + ConnectionStatus.NOT_CONNECTED + " cause：deslect");
                                break;
                            case 5://HostLinktestRequest 响应linktest请求   hostLinktestResponse  stype标记位 = 6
                                //请求报文示例：00 00 00 0A FF FF 00 00 00 05 00 00 00 59
                                //响应报文示例：00 00 00 0A FF FF 00 00 00 06 00 00 00 59
                                WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, "Received linktest request,system byte =" + lengthInBytes);
                                HostLinktestResponse hostLinktestResponse = new HostLinktestResponse(secsPacket.getDeviceID(), secsPacket.getPType(), (short) 0x06, secsPacket.getSystemBytes());
                                context.sendToWire(hostLinktestResponse);
                                sendWebSocketMessage(hostLinktestResponse);
                                break;
                            case 9://HostSeparateRepuest 响应HostSeparateRepuest分离请求  无响应直接断开
                                //请求报文示例：00 00 00 0A FF FF 00 00 00 09 00 00 00 59
                                String secsPackReceiveLog = LogFormatUtils.formatSecsPacketReceiveData(secsPacket);
                                WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, secsPackReceiveLog);
                                changeStatus(hostAddress, ConnectionStatus.NOT_CONNECTED);
                                logger.info("hostAddress:" + ConnectionStatus.NOT_CONNECTED + " cause：HostSeparateRepuest");
                                break;
//                            default: //其它情况 断开连接
//                                changeStatus(hostAddress, ConnectionStatus.NOT_CONNECTED);
//                                logger.info("hostAddress:" + ConnectionStatus.NOT_CONNECTED + " cause：Other");
//                                break;
                        }

                    }
                }
                if (secsPacket instanceof SelectRequest == false) {
                    String secsPackReceiveLog = LogFormatUtils.formatSecsPacketReceiveData(secsPacket);
                    WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, secsPackReceiveLog);
                    if (deviceID != deviceIdSet) {//deviceID与连接的设备不匹配
                        sendS9F1Commond(secsPacket);
                        return;
                    }
                    if (secsPacket instanceof S1F13Request == false && secsPacket instanceof S1F17Request == false) {
                        //获取Control state信息
                        JsonObject jsonObject = LogicRequestUtils.initModelSettings(context, REQUEST_FUNCTION_TYPE_CONTROL_STATE);
                        String eqStatus = null;
                        if (jsonObject != null) {
                            eqStatus = jsonObject.get("defaultControlState").getAsString();
                        }
                        if (StringUtils.isNoneEmpty(eqStatus) && EQUIPMENT_STATUS_OFF_LINE.equals(eqStatus)) {
                            String streamFunctionType = ByteUtils.byteToHex(ByteUtils.intToBytes(streamFunction)).substring(4, 6);
                            switch (streamFunctionType) {
                                case STREAM_FUNCTION_S1:
                                    S1F0Response s1F0Response = new S1F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s1F0Response);
                                    sendWebSocketMessage(s1F0Response);
                                    break;
                                case STREAM_FUNCTION_S2:
                                    S2F0Response s2F0Response = new S2F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s2F0Response);
                                    sendWebSocketMessage(s2F0Response);
                                    break;
                                case STREAM_FUNCTION_S3:
                                    S3F0Response s3F0Response = new S3F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s3F0Response);
                                    sendWebSocketMessage(s3F0Response);
                                    break;
                                case STREAM_FUNCTION_S4:
                                    S4F0Response s4F0Response = new S4F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s4F0Response);
                                    sendWebSocketMessage(s4F0Response);
                                    break;
                                case STREAM_FUNCTION_S5:
                                    S5F0Response s5F0Response = new S5F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s5F0Response);
                                    sendWebSocketMessage(s5F0Response);
                                    break;
                                case STREAM_FUNCTION_S6:
                                    S6F0Response s6F0Response = new S6F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s6F0Response);
                                    sendWebSocketMessage(s6F0Response);
                                    break;
                                case STREAM_FUNCTION_S7:
                                    S7F0Response s7F0Response = new S7F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s7F0Response);
                                    sendWebSocketMessage(s7F0Response);
                                    break;
                                case STREAM_FUNCTION_S8:
                                    S8F0Response s8F0Response = new S8F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s8F0Response);
                                    sendWebSocketMessage(s8F0Response);
                                    break;
                                case STREAM_FUNCTION_S9:
                                    S9F0Response s9F0Response = new S9F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s9F0Response);
                                    sendWebSocketMessage(s9F0Response);
                                    break;
                                case STREAM_FUNCTION_S10:
                                    S10F0Response s10F0Response = new S10F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s10F0Response);
                                    sendWebSocketMessage(s10F0Response);
                                    break;
                                case STREAM_FUNCTION_S11:
                                    S11F0Response s11F0Response = new S11F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s11F0Response);
                                    sendWebSocketMessage(s11F0Response);
                                    break;
                                case STREAM_FUNCTION_S12:
                                    S12F0Response s12F0Response = new S12F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s12F0Response);
                                    sendWebSocketMessage(s12F0Response);
                                    break;
                                case STREAM_FUNCTION_S13:
                                    S13F0Response s13F0Response = new S13F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s13F0Response);
                                    sendWebSocketMessage(s13F0Response);
                                    break;
                                case STREAM_FUNCTION_S14:
                                    S14F0Response s14F0Response = new S14F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s14F0Response);
                                    sendWebSocketMessage(s14F0Response);
                                    break;
                                case STREAM_FUNCTION_S15:
                                    S15F0Response s15F0Response = new S15F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s15F0Response);
                                    sendWebSocketMessage(s15F0Response);
                                    break;
                                case STREAM_FUNCTION_S16:
                                    S16F0Response s16F0Response = new S16F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s16F0Response);
                                    sendWebSocketMessage(s16F0Response);
                                    break;
                                case STREAM_FUNCTION_S17:
                                    S17F0Response s17F0Response = new S17F0Response(0, secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                                    context.sendToWire(s17F0Response);
                                    sendWebSocketMessage(s17F0Response);
                                    break;
                            }
                            return;
                        }
                    }

                }

                if (connectionStatus == ConnectionStatus.SELECTED) {
                    try {
                        Method getUnDefinedFunction = secsPacket.getClass().getMethod("getUnDefinedFunction");
                        if (getUnDefinedFunction != null) {
                            sendS9F5Commond(secsPacket);
                        }
                    } catch (NoSuchMethodException e) {
                        logger.info("check commond UnDefinedFunction");
                    }
                    try {
                        Method getUnDefinedStream = secsPacket.getClass().getMethod("getUnDefinedStream");
                        if (getUnDefinedStream != null) {
                            sendS9F3Commond(secsPacket);
                        }
                    } catch (NoSuchMethodException e) {
                        logger.info("check commond UnDefinedStream");
                    }

                    if (secsPacket instanceof S1F1Request) {
                        if (deviceID != deviceIdSet) {//deviceID与连接的设备不匹配
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            //S1F1Request  are you there request  获取设备MDLN和SOFTREV信息
                            //请求报文示例：00 00 00 0A 00 00 81 01 00 00 00 00 00 4A
                            //响应报文示例：00 00 00 17 00 00 01 02 00 00 00 00 00 4A 01 02 41 04 4D 44 4C 4E 41 03 52 45 56
                            JsonObject jsonObject = LogicRequestUtils.initModelSettings(context, REQUEST_FUNCTION_TYPE_COMMUNICATION_STATE);
                            if (jsonObject != null) {
                                String softRev = jsonObject.get("softRev").getAsString();
                                String mdln = jsonObject.get("MDLN").getAsString();
                                DataStructNormal mdlnDS = new DataStructNormal(SecsDataTypeCode.STRING, (short) mdln.getBytes().length, mdln.getBytes());
                                DataStructNormal softrevDS = new DataStructNormal(SecsDataTypeCode.STRING, (short) softRev.getBytes().length, softRev.getBytes());
                                DataStructNormal[] dataStructs = new DataStructNormal[]{mdlnDS, softrevDS};
                                S1F2Response s1F2Response = new S1F2Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), (short) dataStructs.length, dataStructs);
                                context.sendToWire(s1F2Response);
                                sendWebSocketMessage(s1F2Response);
                                return;
                            } else {
                                logger.error("model setting info query failed!");
                            }

                        }
                    } else if (secsPacket instanceof S1F13Request) {
                        //S1F13Request 连接请求    commack 0:accept 1:denied (try again)
                        //请求报文示例：00 00 00 0A 00 00 01 0D 00 00 00 00 00 **
                        //响应报文示例：00 00 00 **  00 00 01 0E  00 00 00 00 00 ** 01 02 21 01 00 01 02 41 04 4D 44 4C 4E 41 07 53 47 46 54 52 45 56
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            JsonObject jsonObject = LogicRequestUtils.initModelSettings(context, REQUEST_FUNCTION_TYPE_COMMUNICATION_STATE);
                            if (jsonObject != null) {
                                String softRev = jsonObject.get("softRev").getAsString();
                                String mdln = jsonObject.get("MDLN").getAsString();
                                DataStructNormal mdlnDS = new DataStructNormal(SecsDataTypeCode.STRING, (short) mdln.getBytes().length, mdln.getBytes());
                                DataStructNormal softrevDS = new DataStructNormal(SecsDataTypeCode.STRING, (short) softRev.getBytes().length, softRev.getBytes());
                                DataStructNormal commack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{0x00}); //组装commack数据
                                DataStructNormal[] dataStructs = new DataStructNormal[]{mdlnDS, softrevDS};
                                S1F14Response s1F14Response = new S1F14Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), 258, commack, (short) dataStructs.length, dataStructs);
                                context.sendToWire(s1F14Response);
                                sendWebSocketMessage(s1F14Response);
                                return;
                            } else {
                                logger.error("model setting info query failed!");
                            }
                        }
                    } else if (secsPacket instanceof S1F3Request) {
                        //S1F3request 读取特定id的值  例如：读取id为10001的值，模拟返回234
                        //请求报文示例：00 00 00 10 00 00 81 03 00 00 00 00 00 ** 01 01 a9 02 27 11
                        //响应报文示例：00 00 00 11 00 00 01 04 00 00 00 00 00 ** 01 01 41 03 32 33 34
                        int signTag = 0;
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            //SECSGEM层数据定义标准校验
                            DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S1F3Request) secsPacket).getValues());//请求数据
                            for (int i = 0; i < vidvalues.length; i++) {//遍历S1F11Request请求 需要查询的变量
                                DataStructNormal svidDataStruct = vidvalues[i];
                                SecsDataTypeCode svidDataType = svidDataStruct.getDataType();
                                if (svidDataType != SecsDataTypeCode.STRING
                                    && svidDataType != SecsDataTypeCode.DINT && svidDataType != SecsDataTypeCode.INT
                                    && svidDataType != SecsDataTypeCode.LINT && svidDataType != SecsDataTypeCode.SINT
                                    && svidDataType != SecsDataTypeCode.UDINT && svidDataType != SecsDataTypeCode.UINT
                                    && svidDataType != SecsDataTypeCode.ULINT && svidDataType != SecsDataTypeCode.USINT) {
                                    //svidDataType数据类型不匹配  回复S9F7
                                    signTag = -1;
                                    break;
                                }
                            }
                            if (signTag == -1) {//sendS9F7
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS1F3Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                                List<DataStruct> dataStructList = new ArrayList<>();
                                int ackCode = defaultResponseMessage.getAckCode();
                                if (ackCode == -1) {//UI 数据类型校验不通过
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                                if (ackCode == 1) {// 全部SVID未定义
                                    DataStruct2 dataStruct2 = null;
                                    for (int i = 0; i < vidvalues.length; i++) {
                                        DataStruct vidLen = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{(byte) 0});
                                        dataStructList.add(vidLen);
                                    }
                                    DataStruct[] dataStructs = dataStructList.toArray(new DataStruct[0]);
                                    dataStruct2 = calculateDataLength(dataStructs);
                                    S1F4Response s1F4Response = new S1F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                    context.sendToWire(s1F4Response);
                                    sendWebSocketMessage(s1F4Response);
                                    return;
                                }
                                List<ResItem> responseItems = defaultResponseMessage.getResponseItems();
                                for (ResItem resItem : responseItems
                                ) {
                                    DataStruct vidLen;
                                    if (resItem.getPlcResponseCode() == PlcResponseCode.INTERNAL_ERROR) {
                                        vidLen = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{(byte) 0});
                                    } else {
                                        vidLen = RequestMatchUtil.buildResItemDataStruct(resItem);
                                    }
                                    dataStructList.add(vidLen);
                                }
                                DataStruct[] dataStructs = dataStructList.toArray(new DataStruct[0]);
                                DataStruct2 dataStruct2 = calculateDataLength(dataStructs);
                                S1F4Response s1F4Response = new S1F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                context.sendToWire(s1F4Response);
                                sendWebSocketMessage(s1F4Response);
                                return;
                            } else {
                                DataStructNormal vidLen = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{(byte) 0});
                                DataStructNormal[] dataStructs = new DataStructNormal[]{vidLen};
                                DataStruct2 dataStruct2 = new DataShort((short) 1, (short) 1);
                                S1F4Response s1F4Response = new S1F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                context.sendToWire(s1F4Response);
                                sendWebSocketMessage(s1F4Response);
                                return;
                            }
                        }
                    } else if (secsPacket instanceof S1F11Request) {
                        //请求报文示例：00 00 00 0A 00 00 01 0D 00 00 00 00 00 **
                        //响应报文示例：00 00 00 **  00 00 01 0E  00 00 00 00 00 ** 01 02 21 01 00 01 02 41 04 4D 44 4C 4E 41 07 53 47 46 54 52 45 56
                        int signTag = 0;
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S1F11Request) secsPacket).getValues());//请求数据
                            for (int i = 0; i < vidvalues.length; i++) {//遍历 需要查询的变量
                                DataStructNormal svidDataStruct = vidvalues[i];
                                SecsDataTypeCode svidDataType = svidDataStruct.getDataType();
                                if (svidDataType != SecsDataTypeCode.STRING
                                    && svidDataType != SecsDataTypeCode.DINT && svidDataType != SecsDataTypeCode.INT
                                    && svidDataType != SecsDataTypeCode.LINT && svidDataType != SecsDataTypeCode.SINT
                                    && svidDataType != SecsDataTypeCode.UDINT && svidDataType != SecsDataTypeCode.UINT
                                    && svidDataType != SecsDataTypeCode.ULINT && svidDataType != SecsDataTypeCode.USINT) {
                                    //svidDataType数据类型不匹配  回复S9F7
                                    signTag = -1;//sendS9F7
                                    break;
                                }
                            }
                            if (signTag == -1) {//sendS9F7
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS1F11Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                                if (defaultResponseMessage.getAckCode() == -1) {
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                                List<SVIDDataStruct> svidDataStructs = new ArrayList<>();
                                JsonObject data = (JsonObject) defaultResponseMessage.getData();
                                JsonArray records = data.get("records").getAsJsonArray();
                                for (int i = 0; i < records.size(); i++) {
                                    JsonObject asJsonObject = records.get(i).getAsJsonObject();
                                    String dataIdFormat;
                                    String units;
                                    String label;
                                    Integer dataId;
                                    DataStructNormal vidDataStruct = null;
                                    DataStructNormal unitsDataStruct = null;
                                    DataStructNormal svnameDataStruct = null;
                                    if (asJsonObject.get("units") != null) {
                                        units = asJsonObject.get("units").getAsString();
                                        unitsDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) units.getBytes().length, units.getBytes());
                                    } else {
                                        unitsDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                    }
                                    if (asJsonObject.get("label") != null) {
                                        label = asJsonObject.get("label").getAsString();
                                        svnameDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) label.getBytes().length, label.getBytes());
                                    } else {
                                        svnameDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                    }
                                    if (asJsonObject.get("dataIdFormat") != null && asJsonObject.get("dataId") != null) {
                                        dataIdFormat = asJsonObject.get("dataIdFormat").getAsString();
                                        SecsDataTypeCode secsDataTypeCode = SecsDataUtils.parsetSecsDataType(dataIdFormat);
                                        dataId = asJsonObject.get("dataId").getAsInt();
                                        vidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, dataId);
                                    } else {
                                        DataStructNormal dataStruct = vidvalues[i];
                                        int svidValue = Integer.parseInt(parseDataToObject(dataStruct.getDataType(), dataStruct.getData()).toString());
                                        vidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(dataStruct.getDataType(), svidValue);
                                    }
                                    SVIDDataStruct svidDataStruct = new SVIDDataStruct(vidDataStruct, svnameDataStruct, unitsDataStruct);
                                    svidDataStructs.add(svidDataStruct);
                                }
                                SVIDDataStruct[] dataStructs = svidDataStructs.toArray(new SVIDDataStruct[0]);
                                DataStruct2 dataStruct2 = calculateDataLength(dataStructs);
                                S1F12Response s1F12Response = new S1F12Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                context.sendToWire(s1F12Response);
                                sendWebSocketMessage(s1F12Response);
                                return;
                            }
                        }
                    } else if (secsPacket instanceof S2F13Request) {
                        int signTag = 0;
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            //SECSGEM层数据定义标准校验
                            DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S2F13Request) secsPacket).getValues());//请求数据
                            for (int i = 0; i < vidvalues.length; i++) {//遍历S1F11Request请求 需要查询的变量
                                DataStructNormal svidDataStruct = vidvalues[i];
                                SecsDataTypeCode svidDataType = svidDataStruct.getDataType();
                                if (svidDataType != SecsDataTypeCode.STRING
                                    && svidDataType != SecsDataTypeCode.DINT && svidDataType != SecsDataTypeCode.INT
                                    && svidDataType != SecsDataTypeCode.LINT && svidDataType != SecsDataTypeCode.SINT
                                    && svidDataType != SecsDataTypeCode.UDINT && svidDataType != SecsDataTypeCode.UINT
                                    && svidDataType != SecsDataTypeCode.ULINT && svidDataType != SecsDataTypeCode.USINT) {
                                    //svidDataType数据类型不匹配  回复S9F7
                                    signTag = -1;
                                    break;
                                }
                            }
                            if (signTag == -1) {//sendS9F7
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F13Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                                List<DataStruct> dataStructList = new ArrayList<>();
                                int ackCode = defaultResponseMessage.getAckCode();
                                if (ackCode == -1) {//UI 数据类型校验不通过
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                                if (ackCode == 1) {// 全部VID未定义
                                    DataStruct2 dataStruct2 = null;
                                    for (int i = 0; i < vidvalues.length; i++) {
                                        DataStruct vidLen = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{(byte) 0});
                                        dataStructList.add(vidLen);
                                    }
                                    DataStruct[] dataStructs = dataStructList.toArray(new DataStruct[0]);
                                    dataStruct2 = calculateDataLength(dataStructs);
                                    S2F14Response s2F14Response = new S2F14Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                    context.sendToWire(s2F14Response);
                                    sendWebSocketMessage(s2F14Response);
                                    return;
                                }
                                List<ResItem> responseItems = defaultResponseMessage.getResponseItems();
                                for (ResItem resItem : responseItems
                                ) {
                                    DataStruct vidLen;
                                    if (resItem.getPlcResponseCode() == PlcResponseCode.INTERNAL_ERROR) {
                                        vidLen = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{(byte) 0});
                                    } else {
                                        vidLen = RequestMatchUtil.buildResItemDataStruct(resItem);
                                    }
                                    dataStructList.add(vidLen);
                                }
                                DataStruct[] dataStructs = dataStructList.toArray(new DataStruct[0]);
                                DataStruct2 dataStruct2 = calculateDataLength(dataStructs);
                                S2F14Response s2F14Response = new S2F14Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                context.sendToWire(s2F14Response);
                                sendWebSocketMessage(s2F14Response);
                                return;
                            } else {
                                DataStructNormal vidLen = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{(byte) 0});
                                DataStructNormal[] dataStructs = new DataStructNormal[]{vidLen};
                                DataStruct2 dataStruct2 = new DataShort((short) 1, (short) 1);
                                S2F14Response s2F14Response = new S2F14Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                context.sendToWire(s2F14Response);
                                sendWebSocketMessage(s2F14Response);
                                return;
                            }
                        }
                    } else if (secsPacket instanceof S2F15Request) {
                        int signTag = 0;
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            ECID1DataStruct[] values = ((S2F15Request) secsPacket).getValues();//请求数据
                            for (int i = 0; i < values.length; i++) {//遍历S1F11Request请求 需要查询的变量
                                ECID1DataStruct ecid1DataStruct = values[i];
                                DataStructNormal[] dataStructValues = SecsDataUtils.convertToDataStructs(ecid1DataStruct.getValues());
                                if (dataStructValues.length != 2) {
                                    signTag = -1;
                                    break;
                                }
                                //校验数据格式
                                SecsDataTypeCode ecidDataType = dataStructValues[0].getDataType();
                                SecsDataTypeCode ecvDataType = dataStructValues[1].getDataType();
                                //3() 5() 20
                                if (ecidDataType != SecsDataTypeCode.STRING
                                    && ecidDataType != SecsDataTypeCode.DINT && ecidDataType != SecsDataTypeCode.INT
                                    && ecidDataType != SecsDataTypeCode.LINT && ecidDataType != SecsDataTypeCode.SINT
                                    && ecidDataType != SecsDataTypeCode.UDINT && ecidDataType != SecsDataTypeCode.UINT
                                    && ecidDataType != SecsDataTypeCode.ULINT && ecidDataType != SecsDataTypeCode.USINT) {
                                    //svidDataType数据类型不匹配  回复S9F7
                                    signTag = -1;
                                    break;
                                }
                                //10 11 20 21 3() 4() 5()
                                if (ecvDataType != SecsDataTypeCode.STRING
                                    && ecvDataType != SecsDataTypeCode.BYTE && ecvDataType != SecsDataTypeCode.BOOL
                                    && ecvDataType != SecsDataTypeCode.REAL && ecvDataType != SecsDataTypeCode.LREAL
                                    && ecvDataType != SecsDataTypeCode.DINT && ecvDataType != SecsDataTypeCode.INT
                                    && ecvDataType != SecsDataTypeCode.LINT && ecvDataType != SecsDataTypeCode.SINT
                                    && ecvDataType != SecsDataTypeCode.UDINT && ecvDataType != SecsDataTypeCode.UINT
                                    && ecvDataType != SecsDataTypeCode.ULINT && ecvDataType != SecsDataTypeCode.USINT) {
                                    //svidDataType数据类型不匹配  回复S9F7
                                    signTag = -1;
                                    break;
                                }
                            }
                            if (signTag == -1) {//sendS9F7
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            //校验通过
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F15Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                                int ackCode = defaultResponseMessage.getAckCode();
                                if (ackCode == -1) {//UI 数据类型校验不通过
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                                DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) ackCode});
                                S2F16Response s2F16Response = new S2F16Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                                context.sendToWire(s2F16Response);
                                sendWebSocketMessage(s2F16Response);
                                return;
                            } else {//写入失败  other error
                                int ackCode = defaultResponseMessage.getAckCode();
                                DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) ackCode});
                                S2F16Response s2F16Response = new S2F16Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                                context.sendToWire(s2F16Response);
                                sendWebSocketMessage(s2F16Response);
                                return;
                            }
                        }
                    } else if (secsPacket instanceof S2F17Request) {
                        //S2F17Request 发送获取时间和日期请求
                        //请求报文示例：00 00 82 11 00 00 00 00 00 01
                        //响应报文示例：00 00 02 12 00 00 00 00 00 01 41 17 32 30 32 32 2D 31 32 2D 30 38 20 31 35 3A 32 35 3A 30 34 2E 36 38 38
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F17Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                                int ackCode = defaultResponseMessage.getAckCode();
                                if (ackCode == -1) {// 预设变量不存在
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                                DataStructNormal ctime = (DataStructNormal) RequestMatchUtil.buildResItemDataStruct(defaultResponseMessage.getResponseItems().get(0));
                                S2F18Response s2F18Response = new S2F18Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ctime);
                                context.sendToWire(s2F18Response);
                                sendWebSocketMessage(s2F18Response);
                                return;
                            } else {
                                logger.error("S2F17Request fail to get response!");
                            }
                        }
                    } else if (secsPacket instanceof S2F29Request) {
                        //请求报文示例：00 00 00 0A 00 00 01 0D 00 00 00 00 00 **
                        //响应报文示例：00 00 00 **  00 00 01 0E  00 00 00 00 00 ** 01 02 21 01 00 01 02 41 04 4D 44 4C 4E 41 07 53 47 46 54 52 45 56
                        int signTag = 0;
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DataStructNormal[] vidvalues = SecsDataUtils.convertToDataStructs(((S2F29Request) secsPacket).getValues());//请求数据
                            for (int i = 0; i < vidvalues.length; i++) {//遍历 需要查询的变量
                                DataStructNormal svidDataStruct = vidvalues[i];
                                SecsDataTypeCode svidDataType = svidDataStruct.getDataType();
                                if (svidDataType != SecsDataTypeCode.STRING
                                    && svidDataType != SecsDataTypeCode.DINT && svidDataType != SecsDataTypeCode.INT
                                    && svidDataType != SecsDataTypeCode.LINT && svidDataType != SecsDataTypeCode.SINT
                                    && svidDataType != SecsDataTypeCode.UDINT && svidDataType != SecsDataTypeCode.UINT
                                    && svidDataType != SecsDataTypeCode.ULINT && svidDataType != SecsDataTypeCode.USINT) {
                                    //svidDataType数据类型不匹配  回复S9F7
                                    signTag = -1;//sendS9F7
                                    break;
                                }
                            }
                            if (signTag == -1) {//sendS9F7
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F29Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                                if (defaultResponseMessage.getAckCode() == -1) {
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                                List<SVIDDataStruct> svidDataStructs = new ArrayList<>();
                                JsonObject data = (JsonObject) defaultResponseMessage.getData();
                                JsonArray records = data.get("records").getAsJsonArray();
                                List<ECID2DataStruct> ecid2DataStructArrayList = new ArrayList<>();
                                for (int i = 0; i < records.size(); i++) {
                                    JsonObject asJsonObject = records.get(i).getAsJsonObject();
                                    SecsDataTypeCode linkedDataTypeCode = null;
                                    DataStructNormal ecnameDataStruct;
                                    DataStructNormal ecMinDataStruct;
                                    DataStructNormal ecDefDataStruct;
                                    DataStructNormal unitsDataStruct;
                                    DataStructNormal ecMaxDataStruct;
                                    DataStructNormal ecidDataStruct;
                                    if (asJsonObject.get("format") != null) {
                                        linkedDataTypeCode = SecsDataUtils.parsetSecsDataType(asJsonObject.get("format").getAsString());
                                    }
                                    if (asJsonObject.get("label") != null) {
                                        String label = asJsonObject.get("label").getAsString();
                                        ecnameDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) label.getBytes().length, label.getBytes());
                                    } else {
                                        ecnameDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                    }
                                    if (asJsonObject.get("ecMin") != null) {
                                        String ecMin = asJsonObject.get("ecMin").getAsString();
                                        ecMinDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(linkedDataTypeCode, ecMin);
                                    } else {
                                        ecMinDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                    }
                                    if (asJsonObject.get("ecMax") != null) {
                                        String ecMax = asJsonObject.get("ecMax").getAsString();
                                        ecMaxDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(linkedDataTypeCode, ecMax);
                                    } else {
                                        ecMaxDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                    }
                                    if (asJsonObject.get("ecDef") != null) {
                                        String ecDef = asJsonObject.get("ecDef").getAsString();
                                        ecDefDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(linkedDataTypeCode, ecDef);
                                    } else {
                                        ecDefDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                    }
                                    if (asJsonObject.get("units") != null) {
                                        String units = asJsonObject.get("units").getAsString();
                                        unitsDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) units.getBytes().length, units.getBytes());
                                    } else {
                                        unitsDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                    }
                                    if (asJsonObject.get("dataId") != null & asJsonObject.get("dataIdFormat") != null & asJsonObject.get("format") != null) {
                                        Integer dataId = asJsonObject.get("dataId").getAsInt();
                                        SecsDataTypeCode secsDataTypeCode = SecsDataUtils.parsetSecsDataType(asJsonObject.get("dataIdFormat").getAsString());
                                        linkedDataTypeCode = SecsDataUtils.parsetSecsDataType(asJsonObject.get("format").getAsString());
                                        ecidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, dataId);
                                    } else {
                                        ecidDataStruct = vidvalues[i];
                                    }
                                    ECID2DataStruct ecid2DataStruct = new ECID2DataStruct(ecidDataStruct, ecnameDataStruct, ecMinDataStruct, ecMaxDataStruct, ecDefDataStruct, unitsDataStruct);
                                    ecid2DataStructArrayList.add(ecid2DataStruct);
                                }
                                ECID2DataStruct[] ecid2DataStructs = ecid2DataStructArrayList.toArray(new ECID2DataStruct[0]);
                                DataStruct2 dataStruct2 = calculateDataLength(ecid2DataStructs);
                                S2F30Response s2F30Response = new S2F30Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, ecid2DataStructs);
                                context.sendToWire(s2F30Response);
                                sendWebSocketMessage(s2F30Response);
                                return;
                            }
                        }
                    } else if (secsPacket instanceof S2F31Request) {
                        //S2F31Request host发送设置时间和日期请求
                        //请求报文示例：00 00 82 1F 00 00 00 00 00 01 41 10 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20
                        //响应报文示例：00 00 02 20 00 00 00 00 00 01 21 01 00
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F31Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                                if (defaultResponseMessage.getAckCode() == -1) {
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                                int ackCode = defaultResponseMessage.getAckCode();
                                DataStructNormal tiack = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) ackCode});
                                S2F32Response s2F32Response = new S2F32Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), tiack);
                                context.sendToWire(s2F32Response);
                                sendWebSocketMessage(s2F32Response);
                                return;
                            } else {
                                DataStructNormal tiack = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{0x01});
                                S2F32Response s2F32Response = new S2F32Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), tiack);
                                context.sendToWire(s2F32Response);
                                sendWebSocketMessage(s2F32Response);
                                return;
                            }
                        }
                    } else if (secsPacket instanceof S2F23Request) {
                        //S2F17Request 发送获取时间和日期请求
                        //请求报文示例：00 00 82 11 00 00 00 00 00 01
                        //响应报文示例：00 00 02 12 00 00 00 00 00 01 41 17 32 30 32 32 2D 31 32 2D 30 38 20 31 35 3A 32 35 3A 30 34 2E 36 38 38
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        S2F23Request s2F23Request = (S2F23Request) secsPacket;
                        DataStructNormal trid = (DataStructNormal) s2F23Request.getTrid();//trid ID
                        DataStructNormal dsper = (DataStructNormal) s2F23Request.getDsper();//时间间隔
                        DataStructNormal totsmp = (DataStructNormal) s2F23Request.getTotsmp();//总样本
                        DataStructNormal repgsz = (DataStructNormal) s2F23Request.getRepgsz();//数据组大小
                        SecsDataTypeCode tridDataType = trid.getDataType();
                        SecsDataTypeCode dsperDataType = dsper.getDataType();
                        SecsDataTypeCode totsmpDataType = totsmp.getDataType();
                        SecsDataTypeCode repgszDataType = repgsz.getDataType();
                        if (tridDataType != SecsDataTypeCode.STRING
                            && tridDataType != SecsDataTypeCode.DINT && tridDataType != SecsDataTypeCode.INT
                            && tridDataType != SecsDataTypeCode.LINT && tridDataType != SecsDataTypeCode.SINT
                            && tridDataType != SecsDataTypeCode.UDINT && tridDataType != SecsDataTypeCode.UINT
                            && tridDataType != SecsDataTypeCode.ULINT && tridDataType != SecsDataTypeCode.USINT) {
                            //tridDataType数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        if (dsperDataType != SecsDataTypeCode.STRING) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        if (totsmpDataType != SecsDataTypeCode.STRING
                            && totsmpDataType != SecsDataTypeCode.DINT && totsmpDataType != SecsDataTypeCode.INT
                            && totsmpDataType != SecsDataTypeCode.LINT && totsmpDataType != SecsDataTypeCode.SINT
                            && totsmpDataType != SecsDataTypeCode.UDINT && totsmpDataType != SecsDataTypeCode.UINT
                            && totsmpDataType != SecsDataTypeCode.ULINT && totsmpDataType != SecsDataTypeCode.USINT) {
                            //totsmpDataType数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }

                        if (repgszDataType != SecsDataTypeCode.STRING
                            && repgszDataType != SecsDataTypeCode.DINT && repgszDataType != SecsDataTypeCode.INT
                            && repgszDataType != SecsDataTypeCode.LINT && repgszDataType != SecsDataTypeCode.SINT
                            && repgszDataType != SecsDataTypeCode.UDINT && repgszDataType != SecsDataTypeCode.UINT
                            && repgszDataType != SecsDataTypeCode.ULINT && repgszDataType != SecsDataTypeCode.USINT) {
                            //repgszDataType数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }

                        int lengthInBits = dsper.getElementNb();
                        String dsperValue = parseDataToObject(SecsDataTypeCode.STRING, dsper.getData()).toString();//时间hhmmss/hhmmsscc
                        //计算间隔时间
                        if (lengthInBits != 6 && lengthInBits != 8) {
                            //时间格式错误
                            DataStructNormal tiaack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) 3});
                            S2F24Response s2F24Response = new S2F24Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), tiaack);
                            context.sendToWire(s2F24Response);
                            sendWebSocketMessage(s2F24Response);
                            return;
                        } else {
                            int hour = Integer.parseInt(dsperValue.substring(0, 2));
                            int minute = Integer.parseInt(dsperValue.substring(2, 4));
                            int secend = Integer.parseInt(dsperValue.substring(4, 6));
                            if (hour > 24 || minute > 60 || secend > 60) {
                                //时间格式错误
                                DataStructNormal tiaack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) 3});
                                S2F24Response s2F24Response = new S2F24Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), tiaack);
                                context.sendToWire(s2F24Response);
                                sendWebSocketMessage(s2F24Response);
                                return;
                            }
                        }
                        int repgszValue = Integer.parseInt(parseDataToObject(repgsz.getDataType(), repgsz.getData()).toString());
                        int totsmpValue = Integer.parseInt(parseDataToObject(totsmp.getDataType(), totsmp.getData()).toString());
                        if (repgszValue > totsmpValue) {
                            //REPGSZ数据大于TOTSMP的数值时，错误代码报5
                            DataStructNormal tiaack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) 5});
                            S2F24Response s2F24Response = new S2F24Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), tiaack);
                            context.sendToWire(s2F24Response);
                            sendWebSocketMessage(s2F24Response);
                            return;
                        }

                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F23Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            DataStructNormal tiaack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                            S2F24Response s2F24Response = new S2F24Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), tiaack);
                            context.sendToWire(s2F24Response);
                            sendWebSocketMessage(s2F24Response);
                            return;
                        }
                    } else if (secsPacket instanceof S2F33Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        S2F33Request s2F33Request = (S2F33Request) secsPacket;
                        DataStructNormal dataIdDataStruct = (DataStructNormal) s2F33Request.getDataId();
                        SecsDataTypeCode dataIdDataType = dataIdDataStruct.getDataType();
                        //判断dataType数据类型
                        if (dataIdDataType != SecsDataTypeCode.STRING
                            && dataIdDataType != SecsDataTypeCode.DINT && dataIdDataType != SecsDataTypeCode.INT
                            && dataIdDataType != SecsDataTypeCode.LINT && dataIdDataType != SecsDataTypeCode.SINT
                            && dataIdDataType != SecsDataTypeCode.UDINT && dataIdDataType != SecsDataTypeCode.UINT
                            && dataIdDataType != SecsDataTypeCode.ULINT && dataIdDataType != SecsDataTypeCode.USINT) {
                            //svidDataType数据类型不匹配
                            logger.warn("S2F33Request :: dataType {} is not matched , must 3()||5()", dataIdDataType);
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        RPTIDListDataStruct[] rptidListDataStructs = s2F33Request.getValues();
                        for (int i = 0; i < rptidListDataStructs.length; i++) {
                            RPTIDListDataStruct rptidListDataStruct = rptidListDataStructs[i];
                            DataStructNormal rptId = (DataStructNormal) rptidListDataStruct.getRptId();
                            SecsDataTypeCode rptIdDataType = rptId.getDataType();
                            //判断rptID数据类型
                            if (rptIdDataType != SecsDataTypeCode.STRING
                                && rptIdDataType != SecsDataTypeCode.DINT && rptIdDataType != SecsDataTypeCode.INT
                                && rptIdDataType != SecsDataTypeCode.LINT && rptIdDataType != SecsDataTypeCode.SINT
                                && rptIdDataType != SecsDataTypeCode.UDINT && rptIdDataType != SecsDataTypeCode.UINT
                                && rptIdDataType != SecsDataTypeCode.ULINT && rptIdDataType != SecsDataTypeCode.USINT) {
                                //svidDataType数据类型不匹配
                                logger.warn("S2F33Request :: dataType {} is not matched , must 3()||5()", rptIdDataType);
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            DataStructNormal[] rpDataStructs = SecsDataUtils.convertToDataStructs(rptidListDataStruct.getValues());
                            for (int j = 0; j < rpDataStructs.length; j++) {
                                DataStructNormal rpDataStruct = rpDataStructs[j];
                                SecsDataTypeCode rpDataType = rpDataStruct.getDataType();
                                if (rpDataType != SecsDataTypeCode.STRING
                                    && rpDataType != SecsDataTypeCode.DINT && rpDataType != SecsDataTypeCode.INT
                                    && rpDataType != SecsDataTypeCode.LINT && rpDataType != SecsDataTypeCode.SINT
                                    && rpDataType != SecsDataTypeCode.UDINT && rpDataType != SecsDataTypeCode.UINT
                                    && rpDataType != SecsDataTypeCode.ULINT && rpDataType != SecsDataTypeCode.USINT) {
                                    //svidDataType数据类型不匹配
                                    logger.warn("S2F33Request :: dataType {} is not matched , must 3()||5()", rpDataType);
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                            }
                        }
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F33Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            sendS2F34Commond(secsPacket, (byte) ackCode);
                            return;
                        }
                    } else if (secsPacket instanceof S2F35Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        S2F35Request s2F35Request = (S2F35Request) secsPacket;
                        DataStructNormal dataIdDataStruct = (DataStructNormal) s2F35Request.getDataId();
                        SecsDataTypeCode dataIdDataType = dataIdDataStruct.getDataType();
                        if (dataIdDataType != SecsDataTypeCode.STRING
                            && dataIdDataType != SecsDataTypeCode.DINT && dataIdDataType != SecsDataTypeCode.INT
                            && dataIdDataType != SecsDataTypeCode.LINT && dataIdDataType != SecsDataTypeCode.SINT
                            && dataIdDataType != SecsDataTypeCode.UDINT && dataIdDataType != SecsDataTypeCode.UINT
                            && dataIdDataType != SecsDataTypeCode.ULINT && dataIdDataType != SecsDataTypeCode.USINT) {
                            //dataIdDataType数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        CEIDListDataStruct[] ceidListDataStructs = s2F35Request.getValues();
                        for (int i = 0; i < ceidListDataStructs.length; i++) {
                            CEIDListDataStruct ceidListDataStruct = ceidListDataStructs[i];
                            DataStructNormal ceidDatumDataStruct = (DataStructNormal) ceidListDataStruct.getCEID();//report id
                            SecsDataTypeCode ceidDataType = ceidDatumDataStruct.getDataType();
                            //判断rptID数据类型
                            if (ceidDataType != SecsDataTypeCode.DINT && ceidDataType != SecsDataTypeCode.INT
                                && ceidDataType != SecsDataTypeCode.LINT && ceidDataType != SecsDataTypeCode.SINT
                                && ceidDataType != SecsDataTypeCode.UDINT && ceidDataType != SecsDataTypeCode.UINT
                                && ceidDataType != SecsDataTypeCode.ULINT && ceidDataType != SecsDataTypeCode.USINT) {
                                //svidDataType数据类型不匹配
                                logger.warn("S2F35Request :: ceid dataType {} is not matched , must 3()||5()", ceidDataType);
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            //循环校验vid参数
                            DataStructNormal[] rptStructs = SecsDataUtils.convertToDataStructs(ceidListDataStruct.getValues());
                            for (int j = 0; j < rptStructs.length; j++) {
                                DataStructNormal rptStruct = rptStructs[j];
                                SecsDataTypeCode rptIdType = rptStruct.getDataType();
                                byte[] data = rptStruct.getData();
                                if (rptIdType != SecsDataTypeCode.DINT && rptIdType != SecsDataTypeCode.INT
                                    && rptIdType != SecsDataTypeCode.LINT && rptIdType != SecsDataTypeCode.SINT
                                    && rptIdType != SecsDataTypeCode.UDINT && rptIdType != SecsDataTypeCode.UINT
                                    && rptIdType != SecsDataTypeCode.ULINT && rptIdType != SecsDataTypeCode.USINT) {
                                    //rptIdType数据类型不匹配
                                    logger.warn("S2F35Request :: reportId dataType {} is not matched , must 3()||5()", rptIdType);
                                    sendS9F7Commond(secsPacket);
                                    return;
                                }
                            }
                        }
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F35Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            sendS2F36Commond(secsPacket, (byte) ackCode);
                            return;
                        }
                    } else if (secsPacket instanceof S2F37Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        S2F37Request s2F37Request = (S2F37Request) secsPacket;
                        DataStructNormal[] ceidDataStructs = SecsDataUtils.convertToDataStructs(s2F37Request.getValues());
                        DataStructNormal ceedDataStruct = (DataStructNormal) s2F37Request.getCEED();
                        SecsDataTypeCode ceedDataStructDataType = ceedDataStruct.getDataType();
                        if (ceedDataStructDataType != SecsDataTypeCode.BOOL) {
                            //ceedDataStructDataType数据类型不匹配
                            logger.warn("S2F37Request :: ceed dataType {} is not matched , must 3()||5()", ceedDataStruct);
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        for (int i = 0; i < ceidDataStructs.length; i++) {
                            DataStructNormal ceidDataStruct = ceidDataStructs[i];
                            SecsDataTypeCode ceidDataStructDataType = ceidDataStruct.getDataType();
                            //判断数据类型
                            if (ceidDataStructDataType != SecsDataTypeCode.DINT && ceidDataStructDataType != SecsDataTypeCode.INT
                                && ceidDataStructDataType != SecsDataTypeCode.LINT && ceidDataStructDataType != SecsDataTypeCode.SINT
                                && ceidDataStructDataType != SecsDataTypeCode.UDINT && ceidDataStructDataType != SecsDataTypeCode.UINT
                                && ceidDataStructDataType != SecsDataTypeCode.ULINT && ceidDataStructDataType != SecsDataTypeCode.USINT) {
                                //ceidDataStructDataType数据类型不匹配
                                logger.warn("S2F33Request :: ceid dataType {} is not matched , must 3()||5()", ceidDataStructDataType);
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                        }
                        //校验完成  更新状态
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F37Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            DataStructNormal erack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                            S2F38Reply s2F38Response = new S2F38Reply(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), erack);
                            context.sendToWire(s2F38Response);
                            sendWebSocketMessage(s2F38Response);
                            return;
                        }
                    } else if (secsPacket instanceof S2F41Request) {
                        //S2F41Request host请求Eq使用相关参数执行指定的远程命令 Eq返回S2F42指令中，包含HCACK变量，该变量值为0时，表示Eq执行完成，该变量值≥1时，表示指令错误
                        //请求报文示例：00 00 82 29 00 00 00 00 00 02 01 02 41 00 01 01 01 02 41 00 41 00
                        //响应报文示例：00 00 02 2A 00 00 00 00 00 02 01 02 21 01 00 01 01 01 02 41 00 41 00
                        //HCACK
                        // 0 - ok, completed
                        //1 - invalid command
                        //2 - cannot do now
                        //3 - parameter error
                        //4 - initiated for asynchronous completion
                        //5 - rejected, already in desired condition
                        //6 - invalid object
                        //CPACK
                        // 1 - unknown CPNAME
                        // 2 - illegal value for CPVAL
                        // 3 - illegal format for CPVAL
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS2F41Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() != 0) {
                                JsonObject data = (JsonObject) defaultResponseMessage.getData();
                                int hcack = defaultResponseMessage.getAckCode();
                                List<ParametersData> parametersDataList = new ArrayList<>();
                                if (data != null) {
                                    Map<String, Double> checkCpNameMap = new Gson().fromJson(data.toString(), Map.class);
                                    for (Map.Entry<String, Double> entry : checkCpNameMap.entrySet()) {
                                        if (entry.getValue() != 0) {//只返回错误项
                                            DataStructNormal cpNameDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) entry.getKey().getBytes().length, entry.getKey().getBytes());
                                            DataStructNormal cpval = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{entry.getValue().byteValue()});
                                            ParametersData parametersData = new ParametersData(cpNameDataStruct, cpval);
                                            parametersDataList.add(parametersData);
                                        }
                                    }
                                    DataStructNormal hcackDataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) hcack});
                                    ParametersData[] parametersData = parametersDataList.toArray(new ParametersData[0]);
                                    DataStruct2 symbolTypeDS2 = calculateDataLength(parametersData);
                                    S2F42Response s2F42Response = new S2F42Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), hcackDataStruct, symbolTypeDS2, parametersData);
                                    context.sendToWire(s2F42Response);
                                    sendWebSocketMessage(s2F42Response);
                                    return;
                                } else {
                                    DataStructNormal hcackDataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) hcack});
                                    ParametersData[] parametersData = parametersDataList.toArray(new ParametersData[0]);
                                    DataStruct2 symbolTypeDS2 = calculateDataLength(parametersData);
                                    S2F42Response s2F42Response = new S2F42Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), hcackDataStruct, symbolTypeDS2, parametersData);
                                    context.sendToWire(s2F42Response);
                                    sendWebSocketMessage(s2F42Response);
                                    return;
                                }
                            }
                        }
                    } else if (secsPacket instanceof S6F15Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            eventDataId++;
                            if (eventDataId == 65535) {
                                eventDataId = 0;
                            }
                            DataStructNormal ceidDataStruct = (DataStructNormal) ((S6F15Request) secsPacket).getCeid();
                            SecsDataTypeCode ceidDataStructDataType = ceidDataStruct.getDataType();
                            DataStructNormal dataIdDataStruct = new DataStructNormal(SecsDataTypeCode.UINT, SecsDataTypeCode.UINT.getSize(), new PlcUINT(eventDataId).getBytes());
                            //判断数据类型
                            if (ceidDataStructDataType != SecsDataTypeCode.STRING
                                && ceidDataStructDataType != SecsDataTypeCode.DINT && ceidDataStructDataType != SecsDataTypeCode.INT
                                && ceidDataStructDataType != SecsDataTypeCode.LINT && ceidDataStructDataType != SecsDataTypeCode.SINT
                                && ceidDataStructDataType != SecsDataTypeCode.UDINT && ceidDataStructDataType != SecsDataTypeCode.UINT
                                && ceidDataStructDataType != SecsDataTypeCode.ULINT && ceidDataStructDataType != SecsDataTypeCode.USINT) {
                                //ceidDataStructDataType数据类型不匹配
                                logger.warn("S6F15Request :: ceid dataType {} is not matched , must 3()||5()", ceidDataStructDataType);
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS6F15Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -2) {
                                //ceid不存在
                                RPTIDListDataStruct[] reportDataArray = new RPTIDListDataStruct[]{};
                                DataStruct2 dataStruct2 = calculateDataLength(reportDataArray);
                                S6F16Response s6F16Response = new S6F16Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataIdDataStruct, ceidDataStruct, dataStruct2, reportDataArray);
                                context.sendToWire(s6F16Response);
                                sendWebSocketMessage(s6F16Response);
                                return;
                            }
                            Map<Integer, List<ResItem>> resultMap = (Map<Integer, List<ResItem>>) defaultResponseMessage.getData();
                            Set<Map.Entry<Integer, List<ResItem>>> entries = resultMap.entrySet();
                            List<RPTIDListDataStruct> rptidListDataStructList = new ArrayList<>();
                            for (Map.Entry<Integer, List<ResItem>> entry : entries
                            ) {
                                Integer key = entry.getKey();
                                List<ResItem> values = entry.getValue();
                                List<DataStructNormal> dataStructList = new ArrayList<>();
                                DataStructNormal[] dataStructs = new DataStructNormal[values.size()];
                                for (int k = 0; k < values.size(); k++) {
                                    //读取report中变量值
                                    dataStructs[k] = (DataStructNormal) RequestMatchUtil.buildResItemDataStruct(values.get(k));
                                }
                                DataStructNormal rptidDataStruct = new DataStructNormal(SecsDataTypeCode.UINT, SecsDataTypeCode.UINT.getSize(), new PlcUINT(key).getBytes());
                                ReportData reportData = new ReportData(rptidDataStruct, Integer.valueOf(dataStructs.length).byteValue(), dataStructs);
                                DataStruct2 dataStruct2 = calculateDataLength(dataStructs);
                                RPTIDListDataStruct rptidListDataStruct = new RPTIDListDataStruct(rptidDataStruct, dataStruct2, dataStructs);
                                rptidListDataStructList.add(rptidListDataStruct);
                            }
                            RPTIDListDataStruct[] reportDataArray = rptidListDataStructList.toArray(new RPTIDListDataStruct[0]);
                            DataStruct2 dataStruct2 = calculateDataLength(reportDataArray);
                            S6F16Response s6F16Response = new S6F16Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataIdDataStruct, ceidDataStruct, dataStruct2, reportDataArray);
                            context.sendToWire(s6F16Response);
                            sendWebSocketMessage(s6F16Response);
                        }
                    } else if (secsPacket instanceof S6F19Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            eventDataId++;
                            if (eventDataId == 65535) {
                                eventDataId = 0;
                            }
                            DataStructNormal rptidDataStruct = (DataStructNormal) ((S6F19Request) secsPacket).getRptid();
                            SecsDataTypeCode rptIdDataType = rptidDataStruct.getDataType();
                            int rpdIdValue = Integer.parseInt(SecsDataUtils.getDataStructValueByFormat(rptidDataStruct));
                            //判断rptID数据类型
                            if (rptIdDataType != SecsDataTypeCode.STRING
                                && rptIdDataType != SecsDataTypeCode.DINT && rptIdDataType != SecsDataTypeCode.INT
                                && rptIdDataType != SecsDataTypeCode.LINT && rptIdDataType != SecsDataTypeCode.SINT
                                && rptIdDataType != SecsDataTypeCode.UDINT && rptIdDataType != SecsDataTypeCode.UINT
                                && rptIdDataType != SecsDataTypeCode.ULINT && rptIdDataType != SecsDataTypeCode.USINT) {
                                //svidDataType数据类型不匹配
                                logger.warn("S6F19Request :: dataType {} is not matched , must 3()||5()", rptIdDataType);
                                sendS9F7Commond(secsPacket);
                                return;
                            }

                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS6F19Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -2) {
                                DataStructNormal[] dataStructs = new DataStructNormal[]{};
                                DataStruct2 dataStruct2 = calculateDataLength(dataStructs);
                                S6F20Response s6F20Response = new S6F20Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                                context.sendToWire(s6F20Response);
                                sendWebSocketMessage(s6F20Response);
                                return;
                            }
                            List<ResItem> resItems = defaultResponseMessage.getResponseItems();
                            DataStructNormal[] dataStructs = new DataStructNormal[resItems.size()];
                            for (int j = 0; j < resItems.size(); j++) {
                                dataStructs[j] = (DataStructNormal) RequestMatchUtil.buildResItemDataStruct(resItems.get(j));
                            }
                            DataStruct2 dataStruct2 = calculateDataLength(dataStructs);
                            S6F20Response s6F20Response = new S6F20Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, dataStructs);
                            context.sendToWire(s6F20Response);
                            sendWebSocketMessage(s6F20Response);
                            return;
                        }
                    } else if (secsPacket instanceof S7F17Request) {
                        //S7F17Request HOST发送S7F17,发送内容包括需要删除的PPID列表
                        //请求报文示例：00 00 87 11 00 00 00 00 00 03 01 01 41 00
                        //响应报文示例：00 00 07 12 00 00 00 00 00 03 21 01 00
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS7F17Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            DataStructNormal hcack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                            S7F18Response s2F18Response = new S7F18Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), hcack);
                            context.sendToWire(s2F18Response);
                            sendWebSocketMessage(s2F18Response);
                            return;
                        }
                    } else if (secsPacket instanceof S7F19Request) {
                        //HOST请求Eq的PPID列表
                        //查询ppid列表
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            logger.info("SecsgemProtocalLogic decode receive S7F19Request");
                            List<DataStructNormal> dataStructList = new ArrayList<>();
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS7F19Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            JsonObject data = (JsonObject) defaultResponseMessage.getData();
                            if (data != null && data.get("records") != null) {
                                JsonArray records = data.get("records").getAsJsonArray();
                                for (int i = 0; i < records.size(); i++) {
                                    JsonObject asJsonObject = records.get(i).getAsJsonObject();
                                    DataStructNormal ppidDataStuct = null;
                                    String description = asJsonObject.get("ppidType").getAsString();
                                    if (description.equals(SecsDataTypeCode.STRING.name())) {
                                        ppidDataStuct = new DataStructNormal(SecsDataTypeCode.STRING, (short) asJsonObject.get("ppid").getAsString().getBytes().length, asJsonObject.get("ppid").getAsString().getBytes());
                                    } else {
                                        ppidDataStuct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) asJsonObject.get("ppid").getAsInt()});
                                    }
                                    dataStructList.add(ppidDataStuct);
                                }
                            }
                            DataStruct2 dataStruct2 = calculateDataLength(dataStructList.toArray());
                            DataStructNormal[] vidvalues = dataStructList.toArray(new DataStructNormal[0]);
                            S7F20Response s2F20Response = new S7F20Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, vidvalues);
                            context.sendToWire(s2F20Response);
                            sendWebSocketMessage(s2F20Response);
                            return;
                        }
                    } else if (secsPacket instanceof S7F23Request) {
                        boolean isDataTypeValid = true;
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DataStructNormal ppidDataStruct = (DataStructNormal) ((S7F23Request) secsPacket).getPpid();
                            DataStructNormal mdlnDataStruct = (DataStructNormal) ((S7F23Request) secsPacket).getMDLN();
                            DataStructNormal softRevDataStruct = (DataStructNormal) ((S7F23Request) secsPacket).getSOFTREV();
                            //校验MDLN和SOFTREV数据格式，不正确返回S9F7
                            if (mdlnDataStruct.getDataType() != SecsDataTypeCode.STRING || softRevDataStruct.getDataType() != SecsDataTypeCode.STRING) {
                                sendS9F7Commond(secsPacket);
                                ppidValidMap.clear();
                                return;
                            }
                            SecsDataTypeCode ppidDataType = ppidDataStruct.getDataType();
                            if (ppidDataType != SecsDataTypeCode.STRING && ppidDataType != SecsDataTypeCode.BYTE) {
                                //ppid数据类型不匹配
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            String ppidValue = parseDataToObject(ppidDataStruct.getDataType(), ppidDataStruct.getData()).toString();
//                            if (ppidValidMap.get(ppidValue) == null) { //发送前未发送S7F1
//                                DataStructNormal ack7 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) 1});
//                                S7F24Response s7F24Response = new S7F24Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ack7);
//                                context.sendToWire(s7F24Response);
//                                sendWebSocketMessage(s7F24Response);
//                                ppidValidMap.clear();
//                                return;
//                            }
                            ProcessDataStruct[] processDataStructs = ((S7F23Request) secsPacket).getValues();
                            Map<DataStructNormal, DataStructNormal[]> recipeParamsMap = new HashMap<>();//所有参数
                            for (int i = 0; i < processDataStructs.length; i++) {//
                                ProcessDataStruct processDataStruct = processDataStructs[i];
                                DataStructNormal ccode = (DataStructNormal) processDataStruct.getCCODE();
                                //判断ccode数据类型
                                if (ccode.getDataType() != SecsDataTypeCode.STRING
                                    && ccode.getDataType() != SecsDataTypeCode.DINT && ccode.getDataType() != SecsDataTypeCode.INT
                                    && ccode.getDataType() != SecsDataTypeCode.UDINT && ccode.getDataType() != SecsDataTypeCode.UINT) {
                                    //svidDataType数据类型不匹配
                                    isDataTypeValid = false;
                                    break;
                                }
                                if (!isDataTypeValid) {
                                    break;
                                }
                            }
                            if (!isDataTypeValid) { //参数数据类型格式不匹配
                                sendS9F7Commond(secsPacket);
                                ppidValidMap.clear();
                                return;
                            }
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS7F23Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                ppidValidMap.clear();
                                return;
                            }
                            //校验通过 - 下载配方
                            DataStructNormal ack7 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                            S7F24Response s7F24Response = new S7F24Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ack7);
                            context.sendToWire(s7F24Response);
                            sendWebSocketMessage(s7F24Response);
//                            ppidValidMap.clear();
                            return;
                        }
                    } else if (secsPacket instanceof S7F25Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        DataStructNormal ppid = (DataStructNormal) ((S7F25Request) secsPacket).getPPID();
                        SecsDataTypeCode ppidDataType = ppid.getDataType();
                        if (ppidDataType != SecsDataTypeCode.STRING && ppidDataType != SecsDataTypeCode.BYTE) {
                            //ppid数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        String ppidValue = parseDataToObject(ppid.getDataType(), ppid.getData()).toString();

                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS7F25Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        int ackCode = defaultResponseMessage.getAckCode();
                        if (defaultResponseMessage.getAckCode() == -1) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -2) {
                            //没有对应的PPID名称，则S7F26回复<L,0>
                            DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{});
                            S7F26Response0 s7F26Response0 = new S7F26Response0(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                            context.sendToWire(s7F26Response0);
                            sendWebSocketMessage(s7F26Response0);
                            return;
                        }
                    } else if (secsPacket instanceof S1F15Request) {
                        //host发送设备转换到离线状态请求
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS1F15Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            DataStructNormal oflack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                            S1F16Response s1F16Response = new S1F16Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), oflack);
                            context.sendToWire(s1F16Response);
                            sendWebSocketMessage(s1F16Response);
                            return;

                        }
                    } else if (secsPacket instanceof S1F17Request) {
                        //host发送设备转换到在线状态请求
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            logger.info("SecsgemProtocalLogic decode receive S1F17Request");
                            //设置网关状态为ON-LINE
                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS1F17Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            DataStructNormal oflack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                            S1F18Response s1F18Response = new S1F18Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), oflack);
                            context.sendToWire(s1F18Response);
                            sendWebSocketMessage(s1F18Response);
                            return;
                        }
                    } else if (secsPacket instanceof S5F3Request) {
                        //host发送设备转换到离线状态请求
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        DataStructNormal aled = (DataStructNormal) ((S5F3Request) secsPacket).getAled();
                        DataStructNormal alid = (DataStructNormal) ((S5F3Request) secsPacket).getAlid();
                        SecsDataTypeCode aledDataType = aled.getDataType();
                        if (aledDataType != SecsDataTypeCode.BYTE) {
                            //aled数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        SecsDataTypeCode alidDataType = alid.getDataType();
                        if (alidDataType != SecsDataTypeCode.DINT && alidDataType != SecsDataTypeCode.INT
                            && alidDataType != SecsDataTypeCode.LINT && alidDataType != SecsDataTypeCode.SINT
                            && alidDataType != SecsDataTypeCode.UDINT && alidDataType != SecsDataTypeCode.UINT
                            && alidDataType != SecsDataTypeCode.ULINT && alidDataType != SecsDataTypeCode.USINT) {
                            //alid数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS5F3Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        int ackCode = defaultResponseMessage.getAckCode();
                        DataStructNormal ackc5 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                        S5F4Response s5F4Response = new S5F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ackc5);
                        context.sendToWire(s5F4Response);
                        sendWebSocketMessage(s5F4Response);
                        return;
                    } else if (secsPacket instanceof S5F5Request) {
                        int signTag = 0;
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        byte[] body = ((S5F5Request) secsPacket).getValues();
                        short dataType = ((S5F5Request) secsPacket).getDataType();
                        SecsDataTypeCode2 alidDataType = SecsDataTypeCode2.enumForValue(dataType);
                        if (alidDataType != SecsDataTypeCode2.DINT && alidDataType != SecsDataTypeCode2.INT
                            && alidDataType != SecsDataTypeCode2.LINT && alidDataType != SecsDataTypeCode2.SINT
                            && alidDataType != SecsDataTypeCode2.UDINT && alidDataType != SecsDataTypeCode2.UINT
                            && alidDataType != SecsDataTypeCode2.ULINT && alidDataType != SecsDataTypeCode2.USINT) {
                            //alid数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        int dataLength = 0;
                        switch (alidDataType) {
                            case USINT:
                            case SINT:
                                dataLength = body.length;
                                break;
                            case UINT:
                            case INT:
                                dataLength = body.length / 2;
                                break;
                            case UDINT:
                            case DINT:
                                dataLength = body.length / 4;
                                break;
                            case REAL:
                                dataLength = body.length / 4;
                                break;
                            case LREAL:
                                dataLength = body.length / 8;
                                break;
                            case ULINT:
                            case LINT:
                                dataLength = body.length / 8;
                                break;
                            default:
                                break;
                        }
                        ByteBuf byteBuffer = Unpooled.wrappedBuffer(body);
                        List<Object> dataList = new ArrayList<>();
                        int index = 0;
                        for (int i = 0; i < dataLength; i++) {
                            Object data = null;
                            switch (alidDataType) {
                                case USINT:
                                case SINT:
                                    data = Byte.toUnsignedInt(byteBuffer.getByte(index));
                                    index += 1;
                                    break;
                                case UINT:
                                case INT:
                                    data = byteBuffer.getShort(index);
                                    index += 2;
                                    break;
                                case UDINT:
                                case DINT:
                                    data = byteBuffer.getInt(index);
                                    index += 4;
                                    break;
                                case REAL:
                                    data = byteBuffer.getFloat(index);
                                    index += 4;
                                    break;
                                case LREAL:
                                    data = byteBuffer.getDouble(index);
                                    index += 8;
                                    break;
                                case ULINT:
                                case LINT:
                                    data = byteBuffer.getLong(index);
                                    index += 8;
                                    break;
                                default:
                                    break;
                            }
                            dataList.add(data);
                        }

                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS5F5Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -1) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        if (dataList.size() == 0) {
                            //返回所有告警
                            JsonObject data = (JsonObject) defaultResponseMessage.getData();
                            List<AlarmStruct> alarmStructList = new ArrayList<>();
                            if (data != null && data.get("records") != null) {
                                JsonArray records = data.get("records").getAsJsonArray();
                                for (int i = 0; i < records.size(); i++) {
                                    JsonObject asJsonObject = records.get(i).getAsJsonObject();
                                    int alarmCdValue = asJsonObject.get("alarmCd").getAsInt();
                                    DataStructNormal alcdDataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) alarmCdValue});
                                    String alarmIdFormat = asJsonObject.get("alarmIdFormat").getAsString();
                                    String alarmIdValue = asJsonObject.get("alarmId").getAsString();
                                    SecsDataTypeCode secsDataTypeCode = RequestMatchUtil.parsetSecsDataType(alarmIdFormat);
                                    DataStructNormal alidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, alarmIdValue);
                                    String alarmText = asJsonObject.get("alarmText").getAsString();
                                    DataStructNormal altxDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) alarmText.getBytes().length, alarmText.getBytes());
                                    AlarmStruct alarmStruct = new AlarmStruct(alcdDataStruct, alidDataStruct, altxDataStruct);
                                    alarmStructList.add(alarmStruct);
                                }
                            }
                            DataStruct2 dataStruct2 = calculateDataLength(alarmStructList.toArray());
                            AlarmStruct[] alarmStructs = alarmStructList.toArray(new AlarmStruct[0]);
                            S5F6Response s5F6Response = new S5F6Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, alarmStructs);
                            context.sendToWire(s5F6Response);
                            sendWebSocketMessage(s5F6Response);
                            return;
                        } else {
                            List<AlarmStruct> alarmStructList = new ArrayList<>();
                            for (int i = 0; i < dataList.size(); i++) {
                                JsonObject data = (JsonObject) defaultResponseMessage.getData();
                                if (data != null && data.get("records") != null) {
                                    JsonArray records = data.get("records").getAsJsonArray();
                                    List<Integer> alarmIdList = new ArrayList<>();
                                    for (int j = 0; j < records.size(); j++) {
                                        JsonObject asJsonObject = records.get(j).getAsJsonObject();
                                        String alarmIdValue = asJsonObject.get("alarmId").getAsString();
                                        alarmIdList.add(Integer.parseInt(alarmIdValue));
                                    }
                                    if (alarmIdList.contains(Integer.parseInt(dataList.get(i).toString()))) {
                                        for (int j = 0; j < records.size(); j++) {
                                            JsonObject asJsonObject = records.get(j).getAsJsonObject();
                                            int alarmCdValue = asJsonObject.get("alarmCd").getAsInt();
                                            String alarmIdFormat = asJsonObject.get("alarmIdFormat").getAsString();
                                            String alarmIdValue = asJsonObject.get("alarmId").getAsString();
                                            String alarmText = asJsonObject.get("alarmText").getAsString();
                                            alarmIdList.add(Integer.parseInt(alarmIdValue));
                                            if (Integer.parseInt(alarmIdValue) == Integer.parseInt(dataList.get(i).toString())) {
                                                DataStructNormal alcdDataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) alarmCdValue});
                                                SecsDataTypeCode secsDataTypeCode = RequestMatchUtil.parsetSecsDataType(alarmIdFormat);
                                                DataStructNormal alidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, alarmIdValue);
                                                DataStructNormal altxDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) alarmText.getBytes().length, alarmText.getBytes());
                                                AlarmStruct alarmStruct = new AlarmStruct(alcdDataStruct, alidDataStruct, altxDataStruct);
                                                alarmStructList.add(alarmStruct);
                                                break;
                                            }
                                        }
                                    } else {
                                        DataStructNormal alcdDataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 0, new byte[]{});
                                        SecsDataTypeCode secsDataTypeCode = RequestMatchUtil.parsetSecsDataType("I2");
                                        DataStructNormal alidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, dataList.get(i));
                                        DataStructNormal altxDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) 0, new byte[]{});
                                        AlarmStruct alarmStruct = new AlarmStruct(alcdDataStruct, alidDataStruct, altxDataStruct);
                                        alarmStructList.add(alarmStruct);
                                    }

                                }
                            }
                            DataStruct2 dataStruct2 = calculateDataLength(alarmStructList.toArray());
                            AlarmStruct[] alarmStructs = alarmStructList.toArray(new AlarmStruct[0]);
                            S5F6Response s5F6Response = new S5F6Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, alarmStructs);
                            context.sendToWire(s5F6Response);
                            sendWebSocketMessage(s5F6Response);
                            return;
                        }
                    } else if (secsPacket instanceof S5F7Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS5F7Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -1) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        JsonObject data = (JsonObject) defaultResponseMessage.getData();
                        List<AlarmStruct> alarmStructList = new ArrayList<>();
                        if (data != null && data.get("records") != null) {
                            JsonArray records = data.get("records").getAsJsonArray();
                            for (int i = 0; i < records.size(); i++) {
                                JsonObject asJsonObject = records.get(i).getAsJsonObject();
                                int alarmCdValue = asJsonObject.get("alarmCd").getAsInt();
                                DataStructNormal alcdDataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) alarmCdValue});
                                String alarmIdFormat = asJsonObject.get("alarmIdFormat").getAsString();
                                String alarmIdValue = asJsonObject.get("alarmId").getAsString();
                                SecsDataTypeCode secsDataTypeCode = RequestMatchUtil.parsetSecsDataType(alarmIdFormat);
                                DataStructNormal alidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, alarmIdValue);
                                String alarmText = asJsonObject.get("alarmText").getAsString();
                                DataStructNormal altxDataStruct = new DataStructNormal(SecsDataTypeCode.STRING, (short) alarmText.getBytes().length, alarmText.getBytes());
                                AlarmStruct alarmStruct = new AlarmStruct(alcdDataStruct, alidDataStruct, altxDataStruct);
                                alarmStructList.add(alarmStruct);
                            }
                        }
                        DataStruct2 dataStruct2 = calculateDataLength(alarmStructList.toArray());
                        AlarmStruct[] alarmStructs = alarmStructList.toArray(new AlarmStruct[0]);
                        S5F8Response s5F8Response = new S5F8Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct2, alarmStructs);
                        context.sendToWire(s5F8Response);
                        sendWebSocketMessage(s5F8Response);
                        return;
                    } else if (secsPacket instanceof S7F1Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        } else {
                            DataStructNormal ppid = (DataStructNormal) ((S7F1Request) secsPacket).getPPID();
                            DataStructNormal length = (DataStructNormal) ((S7F1Request) secsPacket).getLENGTH();
                            SecsDataTypeCode ppidDataType = ppid.getDataType();
                            SecsDataTypeCode lengthDataType = length.getDataType();
                            String ppidValue = parseDataToObject(ppid.getDataType(), ppid.getData()).toString();
                            ppidValidMap.put(ppidValue, false);
                            if (ppidDataType != SecsDataTypeCode.STRING && ppidDataType != SecsDataTypeCode.BYTE) {
                                //ppid数据类型不匹配
                                sendS9F7Commond(secsPacket);
                                return;
                            }

                            if (lengthDataType != SecsDataTypeCode.DINT && lengthDataType != SecsDataTypeCode.INT
                                && lengthDataType != SecsDataTypeCode.LINT && lengthDataType != SecsDataTypeCode.SINT
                                && lengthDataType != SecsDataTypeCode.UDINT && lengthDataType != SecsDataTypeCode.UINT
                                && lengthDataType != SecsDataTypeCode.ULINT && lengthDataType != SecsDataTypeCode.USINT) {
                                //length数据类型不匹配
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int lengthValue = Integer.parseInt(parseDataToObject(length.getDataType(), length.getData()).toString());//host发送指令的length
                            if (StringUtils.isEmpty(ppidValue) || lengthValue == 0) { //ppid为空  或length=0
                                logger.warn("S7F1Request :: ppid is {} and length is {}", ppidValue, lengthValue);
                                DataStructNormal ppgnt = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) 3});
                                S7F2Response s7F2Response = new S7F2Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ppgnt);
                                context.sendToWire(s7F2Response);
                                sendWebSocketMessage(s7F2Response);
                                return;
                            }

                            DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS7F1Request(context, secsPacket);
                            if (defaultResponseMessage == null) {
                                return;
                            }
                            if (defaultResponseMessage.getAckCode() == -1) {
                                sendS9F7Commond(secsPacket);
                                return;
                            }
                            int ackCode = defaultResponseMessage.getAckCode();
                            DataStructNormal ppgnt = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                            S7F2Response s7F2Response = new S7F2Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ppgnt);
                            context.sendToWire(s7F2Response);
                            sendWebSocketMessage(s7F2Response);
                            ppidValidMap.put(ppidValue, true);
                            return;
                        }
                    } else if (secsPacket instanceof S7F3Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        DataStructNormal ppid = (DataStructNormal) ((S7F3Request) secsPacket).getPpid();
                        SecsDataTypeCode ppidDataType = ppid.getDataType();
//                        String ppidType = "";
//                        if (ppidDataType == SecsDataTypeCode.STRING) {
//                            ppidType = "ASCII";
//                        } else {
//                            ppidType = ppidDataType.name();
//                        }

                        String ppidValue = parseDataToObject(ppid.getDataType(), ppid.getData()).toString();
//                        if (ppidValidMap.get(ppidValue) == null) { //发送前未发送S7F1
//                            DataStructNormal ack7 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) 1});
//                            S7F4Response s7F4Response = new S7F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ack7);
//                            context.sendToWire(s7F4Response);
//                            sendWebSocketMessage(s7F4Response);
//                            return;
//                        }
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS7F3Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -1) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        int ackCode = defaultResponseMessage.getAckCode();
                        DataStructNormal ack7 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                        S7F4Response s7F4Response = new S7F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ack7);
                        context.sendToWire(s7F4Response);
                        sendWebSocketMessage(s7F4Response);
//                        ppidValidMap.clear();
                        return;
                    } else if (secsPacket instanceof S7F5Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        DataStructNormal ppid = (DataStructNormal) ((S7F5Request) secsPacket).getPPID();
                        SecsDataTypeCode ppidDataType = ppid.getDataType();
                        if (ppidDataType != SecsDataTypeCode.STRING && ppidDataType != SecsDataTypeCode.BYTE) {
                            //ppid数据类型不匹配
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS7F5Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -1) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -2) {
                            //没有对应的PPID名称，则S7F6回复<L,0>
                            DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{});
                            S7F6ResponseL s7F6ResponseL = new S7F6ResponseL(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                            context.sendToWire(s7F6ResponseL);
                            sendWebSocketMessage(s7F6ResponseL);
                            return;
                        }
                    } else if (secsPacket instanceof S10F2Response) {
                        S10F2Response s10F2Response = (S10F2Response) secsPacket;
                        DataStructNormal ackc101 = (DataStructNormal) s10F2Response.getAckc10();
                        ackc101.getData();
                        int ack = Integer.parseInt(parseDataToObject(SecsDataTypeCode.BYTE, ackc101.getData()).toString());
                    } else if (secsPacket instanceof S10F3Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        S10F3Request s10F3Request = (S10F3Request) secsPacket;
                        DataStructNormal textDataStruct = (DataStructNormal) s10F3Request.getText();
                        SecsDataTypeCode textDataType = textDataStruct.getDataType();
                        DataStructNormal tidDataStruct = (DataStructNormal) s10F3Request.getTid();
                        SecsDataTypeCode tidDataType = tidDataStruct.getDataType();
                        if (textDataType != SecsDataTypeCode.BYTE &&
                            textDataType != SecsDataTypeCode.DINT && textDataType != SecsDataTypeCode.INT
                            && textDataType != SecsDataTypeCode.LINT && textDataType != SecsDataTypeCode.SINT
                            && textDataType != SecsDataTypeCode.UDINT && textDataType != SecsDataTypeCode.UINT
                            && textDataType != SecsDataTypeCode.ULINT && textDataType != SecsDataTypeCode.USINT
                            && textDataType != SecsDataTypeCode.STRING) {
                            //数据类型不匹配
                            logger.warn("S10F3Request :: dataType {} is not matched", textDataType);
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        if (tidDataType != SecsDataTypeCode.BYTE) {
                            //数据类型不匹配
                            logger.warn("S10F3Request :: dataType {} is not matched", tidDataType);
                            sendS9F7Commond(secsPacket);
                            return;
                        }

                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS10F3Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -1) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        int ackCode = defaultResponseMessage.getAckCode();
                        DataStructNormal ackc10 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                        String textValue = parseDataToObject(textDataStruct.getDataType(), textDataStruct.getData()).toString();
                        logger.info("S10F3Request :: text content:{}", textValue);
                        S10F4Response s10F4Response = new S10F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ackc10);
                        context.sendToWire(s10F4Response);
                        sendWebSocketMessage(s10F4Response);
                        return;
                    } else if (secsPacket instanceof S10F5Request) {
                        if (deviceID != deviceIdSet) {
                            sendS9F1Commond(secsPacket);
                            return;
                        }
                        S10F5Request s10F5Request = (S10F5Request) secsPacket;
                        DataStructNormal[] values = SecsDataUtils.convertToDataStructs(s10F5Request.getValues());
                        DataStructNormal tidDataStruct = (DataStructNormal) s10F5Request.getTID();
                        SecsDataTypeCode tidDataType = tidDataStruct.getDataType();
                        if (tidDataType != SecsDataTypeCode.BYTE) {
                            //数据类型不匹配
                            logger.info("S10F5Request :: dataType {} is not matched", tidDataType);
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        DefaultResponseMessage defaultResponseMessage = LogicRequestUtils.sendS10F5Request(context, secsPacket);
                        if (defaultResponseMessage == null) {
                            return;
                        }
                        if (defaultResponseMessage.getAckCode() == -1) {
                            sendS9F7Commond(secsPacket);
                            return;
                        }
                        int ackCode = defaultResponseMessage.getAckCode();
                        DataStructNormal ackc10 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) ackCode});
                        S10F6Reply s10F6Reply = new S10F6Reply(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ackc10);
                        context.sendToWire(s10F6Reply);
                        sendWebSocketMessage(s10F6Reply);
                        return;
                    }
                }
            }
        } else {
            changeStatus(hostAddress, ConnectionStatus.NOT_CONNECTED);
        }

    }

    private void sendS2F34Commond(SecsPacket secsPacket, byte DRACK) {
        DataStructNormal drack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{DRACK});
        S2F34Response s2F34Response = new S2F34Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), drack);
        context.sendToWire(s2F34Response);
        sendWebSocketMessage(s2F34Response);
    }

    private void sendS2F36Commond(SecsPacket secsPacket, byte LRACK) {
        DataStructNormal drack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{LRACK});
        S2F36Response s2F36Response = new S2F36Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), drack);
        context.sendToWire(s2F36Response);
        sendWebSocketMessage(s2F36Response);
    }

    private void sendS9F3Commond(SecsPacket secsPacket) {
        logger.warn("The equipment does not recognize the stram type int the message block header");
        byte[] deviceIDbytes = ByteUtils.intToBytes(secsPacket.getDeviceID());
        short stype = secsPacket.getStype();
        short pType = secsPacket.getPType();
        byte[] systemBytes = ByteUtils.intToBytes((int) secsPacket.getSystemBytes());
        byte[] bytes1 = ByteUtils.intToBytes((int) secsPacket.getStreamFunction());
        short[] s = new short[]{deviceIDbytes[2], deviceIDbytes[3], bytes1[2], bytes1[3], stype, pType, systemBytes[0], systemBytes[1], systemBytes[2], systemBytes[3]};
        S9F3Response s9F3Response = new S9F3Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), s);
        context.sendToWire(s9F3Response);
        sendWebSocketMessage(s9F3Response);
    }

    private void sendS9F5Commond(SecsPacket secsPacket) {
        logger.warn("The equipment does not recognize the stram type int the message block header");
        byte[] deviceIDbytes = ByteUtils.intToBytes(secsPacket.getDeviceID());
        short stype = secsPacket.getStype();
        short pType = secsPacket.getPType();
        byte[] systemBytes = ByteUtils.intToBytes((int) secsPacket.getSystemBytes());
        byte[] bytes1 = ByteUtils.intToBytes((int) secsPacket.getStreamFunction());
        short[] s = new short[]{deviceIDbytes[2], deviceIDbytes[3], bytes1[2], bytes1[3], stype, pType, systemBytes[0], systemBytes[1], systemBytes[2], systemBytes[3]};
        S9F5Response s9F5Response = new S9F5Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), s);
        context.sendToWire(s9F5Response);
        sendWebSocketMessage(s9F5Response);
    }

    private void sendS9F1Commond(SecsPacket secsPacket) {
        logger.warn("The equipment does not recognize the stram type int the message block header");
        byte[] deviceIDbytes = ByteUtils.intToBytes(secsPacket.getDeviceID());
        short stype = secsPacket.getStype();
        short pType = secsPacket.getPType();
        byte[] systemBytes = ByteUtils.intToBytes((int) secsPacket.getSystemBytes());
        byte[] bytes1 = ByteUtils.intToBytes((int) secsPacket.getStreamFunction());
        short[] s = new short[]{deviceIDbytes[2], deviceIDbytes[3], bytes1[2], bytes1[3], stype, pType, systemBytes[0], systemBytes[1], systemBytes[2], systemBytes[3]};
        S9F1Response s9F1Response = new S9F1Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), s);
        context.sendToWire(s9F1Response);
        sendWebSocketMessage(s9F1Response);
    }

    /**
     * 数据格式错误返回指令S9F7
     *
     * @param secsPacket
     */
    private void sendS9F7Commond(SecsPacket secsPacket) {
        byte[] deviceIDbytes = ByteUtils.intToBytes(secsPacket.getDeviceID());
        short stype = secsPacket.getStype();
        short pType = secsPacket.getPType();
        byte[] systemBytes = ByteUtils.intToBytes((int) secsPacket.getSystemBytes());
        byte[] bytes1 = ByteUtils.intToBytes((int) secsPacket.getStreamFunction());
        short[] s = new short[]{deviceIDbytes[2], deviceIDbytes[3], bytes1[2], bytes1[3], stype, pType, systemBytes[0], systemBytes[1], systemBytes[2], systemBytes[3]};
        S9F7Response s9F7Response = new S9F7Response(secsPacket.getDeviceID(), secsPacket.getPType(), (short) 0x00, secsPacket.getSystemBytes(), s);
        context.sendToWire(s9F7Response);
        sendWebSocketMessage(s9F7Response);
    }

    private void sendS1F13Commond(ConversationContext<SecsPacket> context) {
        int deviceId = Integer.parseInt(DataHolder.getInstance().getBaseSettingInfo().getDeviceId());
        JsonObject jsonObject = LogicRequestUtils.initModelSettings(context, REQUEST_FUNCTION_TYPE_COMMUNICATION_STATE);
        if (jsonObject != null) {
            String softRev = jsonObject.get("softRev").getAsString();
            String mdln = jsonObject.get("MDLN").getAsString();
            DataStructNormal mdlnDS = new DataStructNormal(SecsDataTypeCode.STRING, (short) mdln.getBytes().length, mdln.getBytes());
            DataStructNormal softrevDS = new DataStructNormal(SecsDataTypeCode.STRING, (short) softRev.getBytes().length, softRev.getBytes());
            DataStructNormal[] dataStructs = new DataStructNormal[]{mdlnDS, softrevDS};
            S1F13Request s1F13Request = new S1F13Request(deviceId, (short) 0, (short) 0, 1, (short) dataStructs.length, dataStructs);
            this.context.sendToWire(s1F13Request);
            sendWebSocketMessage(s1F13Request);
            return;
        }


    }


    /**
     * 状态变更
     *
     * @param hostAddress
     * @param connectionStatus
     */
    private void changeStatus(String hostAddress, ConnectionStatus connectionStatus) {
        WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, "HSMS " + connectionStatus.name());
        Map<String, HostChannelInfo> channelMap = DataHolder.getInstance().getChannelMap();
        channelMap.get(hostAddress).setConnectionStatus(connectionStatus);
        ConversationContext context = channelMap.get(hostAddress).getContext();
        if (connectionStatus == ConnectionStatus.SELECTED) {
            initActiveCommand(context, hostAddress);//开启eq->host 定时任务
        }
        if (connectionStatus == ConnectionStatus.NOT_CONNECTED) {
            if (context.getChannel().isActive()) {
                context.getChannel().close();
            }
        }
        logger.info("host:" + hostAddress + "---status::" + connectionStatus);
    }

    /**
     * 启动主动上报指令
     *
     * @param context
     * @param hostAddress
     */
    private void initActiveCommand(ConversationContext context, String hostAddress) {
        this.context = context;
        startBaseSettingParamTask();
        startMonitorTask();
    }

    private void startMonitorTask() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                startLinkTestReqTask(context);//linktest
            }
        }, 2000, 2000, TimeUnit.MILLISECONDS);
        scheduledExecutorServiceHashMap.put("monitorTask", scheduledExecutorService);
    }

    private void startBaseSettingParamTask() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                LogicRequestUtils.initBaseSetting(context);
            }
        }, 2000, 2000, TimeUnit.MILLISECONDS);
        scheduledFutureMap.put("baseSetting", scheduledFuture);
        scheduledExecutorServiceHashMap.put("baseSetting", scheduledExecutorService);
    }


    private void startLinkTestReqTask(ConversationContext context) {
        if (!scheduledExecutorServiceHashMap.containsKey("linkTest") ||
            (scheduledExecutorServiceHashMap.containsKey("linkTest")
                && (scheduledExecutorServiceHashMap.get("linkTest").isTerminated()
                || scheduledExecutorServiceHashMap.get("linkTest").isShutdown()))) {
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            LinkTestReqTask linkTestReqTask = new LinkTestReqTask(context);
            ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(linkTestReqTask, 1000, 1000, TimeUnit.MILLISECONDS);
            scheduledFutureMap.put("linkTest", scheduledFuture);
            scheduledExecutorServiceHashMap.put("linkTest", scheduledExecutorService);
        }
    }

    @Override
    public void onHostConnect(ConversationContext<SecsPacket> context) {
        defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        defaultServerConnectorListener.sendConsumer(this);
        this.context = context;
        if (DataHolder.getInstance().isRegister()) {
            logger.info("onHostConnect----" + context.getChannel().remoteAddress());
            WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, "Accepted from " + context.getChannel().remoteAddress().toString());
            SocketAddress socketAddress = context.getChannel().remoteAddress();
            String hostAddress = ((InetSocketAddress) socketAddress).getAddress().getHostAddress();//获取远程连接地址
            HostChannelInfo hostChannelInfo = new HostChannelInfo(hostAddress, context, ConnectionStatus.CONNECTED_NOT_SELECTED);
            DataHolder.getInstance().getChannelMap().put(hostAddress, hostChannelInfo);//保存当前连接地址和通道信息
            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            SelectReqCommandtask secsgemConectionTimer = new SelectReqCommandtask(hostAddress, scheduledExecutorService);
            boolean isSuccess = LogicRequestUtils.initBaseSetting(context);
            LogicRequestUtils.sendConnectCommand(defaultServerConnectorListener, REQUEST_FUNCTION_TYPE_CONNECT);
            if (isSuccess) {//获取基础配置成功
                baseSettingInfo = DataHolder.getInstance().getBaseSettingInfo();
                logger.debug("request baseSetting success and baseSetting is {}", baseSettingInfo);
                scheduledExecutorService.schedule(secsgemConectionTimer, Long.parseLong(baseSettingInfo.getNotSelectedTimeOut()) * 1000, TimeUnit.MILLISECONDS);
            } else {
                scheduledExecutorService.schedule(secsgemConectionTimer, 60 * 1000, TimeUnit.MILLISECONDS);
                logger.warn("request baseSetting failed!");
            }
            sendS1F13Commond(context);
        } else {
            onHostDisconnect(context);
            logger.error("Sentinel key not found. Please insert Sentinel key!");
        }
    }


    @Override
    public void onHostDisconnect(ConversationContext<SecsPacket> context) {
        logger.info("onHostDisconnect----" + context.getChannel().remoteAddress());
        WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, "DisConenct from " + context.getChannel().remoteAddress().toString());
        SocketAddress socketAddress = context.getChannel().remoteAddress();
        String hostAddress = ((InetSocketAddress) socketAddress).getAddress().getHostAddress();
        Map<String, HostChannelInfo> channelMap = DataHolder.getInstance().getChannelMap();
        if (channelMap.get(hostAddress) != null && channelMap.get(hostAddress).getConnectionStatus() == ConnectionStatus.SELECTED) {
            DataHolder.getInstance().getChannelMap().remove(hostAddress);
        }
        if (scheduledFutureMap.size() > 0) {
            for (ScheduledExecutorService scheduledExecutorService : scheduledExecutorServiceHashMap.values()
            ) {
                scheduledExecutorService.shutdownNow();
            }
        }
        LogicRequestUtils.sendConnectCommand(defaultServerConnectorListener, REQUEST_FUNCTION_TYPE_DISCONNECT);
        context.fireDisconnected();
        context.getChannel().close();
    }

    private void sendWebSocketMessage(SecsPacket secsPacket) {
        String secsPackSendLog = LogFormatUtils.formatSecsPacketSendData(secsPacket);
        WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, secsPackSendLog);
    }

    @Override
    public void accept(Map<String, Object> map) {
        String command = null;//指令名称
        try {
            if (map.get("command") != null) {
                command = String.valueOf(map.get("command"));
                DefaultResponseMessage defaultResponseMessage;
                switch (command) {
                    case COMMAND_S2F17:
                        S2F17Request s2F17Request = new S2F17Request(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes());
                        context.sendToWire(s2F17Request);
                        break;
                    case COMMAND_S2F31:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S2F31);
                        int s2F32AckCode = defaultResponseMessage.getAckCode();
                        DataStructNormal tiack = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) s2F32AckCode});
                        S2F32Response s2F32Response = new S2F32Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), tiack);
                        context.sendToWire(s2F32Response);
                        sendWebSocketMessage(s2F32Response);
                        break;
                    case COMMAND_S2F41:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S2F41);
                        List<ParametersData> parametersDataList = new ArrayList<>();
                        int s2F42Hcack = defaultResponseMessage.getAckCode();
                        DataStructNormal hcackDataStruct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) s2F42Hcack});
                        ParametersData[] parametersData = parametersDataList.toArray(new ParametersData[0]);
                        DataStruct2 symbolTypeDS2 = calculateDataLength(parametersData);
                        S2F42Response s2F42Response = new S2F42Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), hcackDataStruct, symbolTypeDS2, parametersData);
                        context.sendToWire(s2F42Response);
                        sendWebSocketMessage(s2F42Response);
                        break;
                    case COMMAND_S6F1:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S6F1);
                        List<ResItem> responseItems = defaultResponseMessage.getResponseItems();
                        int tridValue = (int) map.get("trid");
                        int requestCount = (int) map.get("requestCount");
                        DataStructNormal trid = new DataStructNormal(SecsDataTypeCode.UINT, SecsDataTypeCode.UINT.getSize(), ByteUtils.shortToBytes((short) tridValue));//trid ID
                        DataStructNormal smpln = new DataStructNormal(SecsDataTypeCode.UINT, SecsDataTypeCode.UINT.getSize(), ByteUtils.shortToBytes((short) requestCount));
                        int milliSeconds = Integer.parseInt(new SimpleDateFormat("SS").format(new Date())) * 60 / 1000;//cc位置毫秒（秒的百分比）
                        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + (milliSeconds < 10 ? "0" + milliSeconds : milliSeconds);
                        DataStructNormal stime = new DataStructNormal(SecsDataTypeCode.STRING, (short) 16, time.getBytes());
                        List<DataStructNormal> dataStructList = new ArrayList<>();
                        for (ResItem resItem : responseItems
                        ) {
                            DataStructNormal vidLen = (DataStructNormal) RequestMatchUtil.buildResItemDataStruct(resItem);
                            if (vidLen == null) {//含有空值
                                return;
                            }
                            dataStructList.add(vidLen);
                        }
                        DataStructNormal[] vidvalues = dataStructList.toArray(new DataStructNormal[0]);
                        DataStruct2 dataStruct2 = calculateDataLength(vidvalues);//计算数据长度类型和长度占位
                        S6F1Request s6F1Request = new S6F1Request(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), requestCount, trid, smpln, stime, dataStruct2, vidvalues);
                        this.context.sendToWire(s6F1Request);
                        sendWebSocketMessage(s6F1Request);
                        break;
                    case COMMAND_S1F15:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S1F15);
                        int s1F16AckCode = defaultResponseMessage.getAckCode();
                        DataStructNormal oflack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) s1F16AckCode});
                        S1F16Response s1F16Response = new S1F16Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), oflack);
                        context.sendToWire(s1F16Response);
                        sendWebSocketMessage(s1F16Response);
                        break;
                    case COMMAND_S1F17:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S1F17);
                        int s1F18AckCode = defaultResponseMessage.getAckCode();
                        DataStructNormal onlack = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) s1F18AckCode});
                        S1F18Response s1F18Response = new S1F18Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), onlack);
                        context.sendToWire(s1F18Response);
                        sendWebSocketMessage(s1F18Response);
                        break;
                    case COMMAND_S6F11:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S6F11);
                        Map<String, Object> objectMap = (Map<String, Object>) defaultResponseMessage.getData();
                        if (objectMap != null && objectMap.size() > 0 && objectMap.get("eventInfo") != null) {
                            EventInfo eventInfo = gson.fromJson(gson.toJson(objectMap.get("eventInfo")), EventInfo.class);
                            Integer ceid = eventInfo.getEventId();
                            List<ReportInfo> reportInfoList = eventInfo.getReportInfoList();
                            List<ReportData> reportDataList = new ArrayList<>();
                            Map<Integer, List<ResItem>> resultMap = new HashMap<>();
                            if (objectMap.get("records") != null) {
                                resultMap = (Map<Integer, List<ResItem>>) objectMap.get("records");
                            }
                            RPTIDListDataStruct[] reportDataArray = new RPTIDListDataStruct[reportInfoList.size()];
                            for (int k = 0; k < reportInfoList.size(); k++
                            ) {
                                int reportId = reportInfoList.get(k).getEventId();
                                List<ResItem> resItems = resultMap.get(reportId);
                                if (resItems != null && resItems.size() > 0) {
                                    dataStructList = new ArrayList<>();
                                    for (ResItem resItem1 : resItems
                                    ) {
                                        dataStructList.add((DataStructNormal) RequestMatchUtil.buildResItemDataStruct(resItem1));
                                    }
                                    DataStructNormal[] dataStructs = dataStructList.toArray(new DataStructNormal[0]);
                                    String reportIdFormat = reportInfoList.get(k).getEventIdFormat();
                                    SecsDataTypeCode reportIdTypeCode = RequestMatchUtil.parsetSecsDataType(reportIdFormat);
                                    DataStructNormal rptidDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(reportIdTypeCode, reportId);
                                    ReportData reportData = new ReportData(rptidDataStruct, Integer.valueOf(dataStructs.length).byteValue(), dataStructs);
                                    reportDataList.add(reportData);

                                    DataStruct2 reportDataStruct2 = calculateDataLength(dataStructs);
                                    RPTIDListDataStruct rptidListDataStruct = new RPTIDListDataStruct(rptidDataStruct, reportDataStruct2, dataStructs);
                                    reportDataArray[k] = rptidListDataStruct;
                                }
                            }
                            DataStruct2 reportDataArrayDataStruct2 = calculateDataLength(reportDataArray);
                            DataStructNormal dataIdDataStruct = new DataStructNormal(SecsDataTypeCode.UINT, SecsDataTypeCode.UINT.getSize(), new PlcINT(1).getBytes());

                            String eventIdFormat = eventInfo.getEventIdFormat();
                            SecsDataTypeCode secsDataTypeCode = RequestMatchUtil.parsetSecsDataType(eventIdFormat);
                            DataStructNormal secIdDataStruct = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, ceid);
                            S6F11Request s6F11Request = new S6F11Request(secsPacket.getDeviceID(), (short) 0x00, (short) 0x00, secsPacket.getSystemBytes(), dataIdDataStruct, secIdDataStruct, reportDataArrayDataStruct2, reportDataArray);
                            WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, LogFormatUtils.formatSecsPacketSendData(s6F11Request));
                            context.sendToWire(s6F11Request);
                        }
                        break;
                    case COMMAND_S1F1:
                        S1F1Request s1F1Request = new S1F1Request(secsPacket.getDeviceID(), (short) 0, (short) 0, secsPacket.getSystemBytes());
                        WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, LogFormatUtils.formatSecsPacketSendData(s1F1Request));
                        context.sendToWire(s1F1Request);
                        break;
                    case COMMAND_S1F13:
                        DataStructNormal[] dataStructs = new DataStructNormal[]{};
                        S1F13Request s1F13Request = new S1F13Request(secsPacket.getDeviceID(), (short) 0, (short) 0, secsPacket.getSystemBytes(), (short) dataStructs.length, dataStructs);
                        context.sendToWire(s1F13Request);
                        break;
                    case COMMAND_S10F1:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S10F1);
                        int s10F2AckCode = defaultResponseMessage.getAckCode();
                        JsonObject jsonObject = (JsonObject) defaultResponseMessage.getData();
                        byte tidValue = 0;
                        String textValue;
                        if (jsonObject != null && jsonObject.get("tid") != null && jsonObject.get("text") != null) {
                            tidValue = jsonObject.get("tid").getAsByte();
                            textValue = jsonObject.get("text").getAsString();
//                    tidValue = ((Short) parseDataToObject(SecsDataTypeCode.UINT, new byte[]{tidValue})).byteValue();
                            DataStructNormal tid_byte = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{tidValue});
                            DataStructNormal text = new DataStructNormal(SecsDataTypeCode.STRING, (short) textValue.getBytes().length, textValue.getBytes());
                            S10F1Request s10F1Request = new S10F1Request(secsPacket.getDeviceID(), (short) 0, (short) 0, secsPacket.getSystemBytes(), tid_byte, text);
                            context.sendToWire(s10F1Request);
                            WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, LogFormatUtils.formatSecsPacketSendData(s10F1Request));
                        }
                        break;
                    case COMMAND_S5F1:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S5F1);
                        JsonObject jsonObject1 = (JsonObject) defaultResponseMessage.getData();
                        if (jsonObject1 != null && jsonObject1.get("alarmInfo") != null && jsonObject1.get("alarmCode") != null) {
                            JsonObject eventInfoJsonObject = jsonObject1.get("alarmInfo").getAsJsonObject();
                            int alarmCode = jsonObject1.get("alarmCode").getAsInt();
                            DataStructNormal alcd = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{(byte) alarmCode});
                            String alarmIdFormat = eventInfoJsonObject.get("alarmIdFormat").getAsString();
                            String alarmId = eventInfoJsonObject.get("alarmId").getAsString();
                            String alarmText = eventInfoJsonObject.get("alarmText").getAsString();
                            SecsDataTypeCode secsDataTypeCode = RequestMatchUtil.parsetSecsDataType(alarmIdFormat);
                            DataStructNormal alid = (DataStructNormal) SecsDataUtils.buildDataStruct(secsDataTypeCode, alarmId);
                            DataStructNormal altx = new DataStructNormal(SecsDataTypeCode.STRING, (short) alarmText.getBytes().length, alarmText.getBytes());
                            //拼装上报信息请求
                            S5F1Request s5F1Request = new S5F1Request(secsPacket.getDeviceID(), (short) 0x00, (short) 0x00, secsPacket.getSystemBytes(), alcd, alid, altx);
                            WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, LogFormatUtils.formatSecsPacketSendData(s5F1Request));
                            context.sendToWire(s5F1Request);
                        }
                        break;
                    case COMMAND_S7F17:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S7F17);
                        int s7F18HcackCode = defaultResponseMessage.getAckCode();
                        DataStructNormal hcackDS = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) s7F18HcackCode});
                        S7F18Response s2F18Response = new S7F18Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), hcackDS);
                        context.sendToWire(s2F18Response);
                        sendWebSocketMessage(s2F18Response);
                        break;
                    case COMMAND_S7F1:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S7F1);
                        int s7F2AckCode = defaultResponseMessage.getAckCode();
                        DataStructNormal ppgnt = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) s7F2AckCode});
                        S7F2Response s7F2Response = new S7F2Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ppgnt);
                        context.sendToWire(s7F2Response);
                        sendWebSocketMessage(s7F2Response);
                        break;
                    case COMMAND_S7F3:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S7F3);
                        int s7F4AckCode = defaultResponseMessage.getAckCode();
                        DataStructNormal ack7 = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) s7F4AckCode});
                        S7F4Response s7F4Response = new S7F4Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ack7);
                        context.sendToWire(s7F4Response);
//                        sendWebSocketMessage(s7F4Response);
                        break;
                    case COMMAND_S7F5:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S7F5);
                        jsonObject = (JsonObject) defaultResponseMessage.getData();
                        if (jsonObject != null && jsonObject.get("ppidType") != null && jsonObject.get("ppbodyType") != null) {
                            String ppidType = jsonObject.get("ppidType").getAsString();
                            String ppidValue = jsonObject.get("ppidValue").getAsString();
                            String ppbodyType = jsonObject.get("ppbodyType").getAsString();
                            DataStructNormal ppidDataStuct = null;
                            if (ppidType.equals("A")) {//STRING
                                ppidDataStuct = new DataStructNormal(SecsDataTypeCode.STRING, (short) ppidValue.getBytes().length, ppidValue.getBytes());
                            } else {//BYTE
                                ppidDataStuct = new DataStructNormal(SecsDataTypeCode.BYTE, SecsDataTypeCode.BYTE.getSize(), new byte[]{Byte.valueOf(ppidValue)});
                            }
                            if (defaultResponseMessage.getResponseItems() != null && defaultResponseMessage.getResponseItems().size() > 0) {
                                ResItem resItem = defaultResponseMessage.getResponseItems().get(0);
                                byte[] dataArray = (byte[]) resItem.getValue();
                                DataStruct3 dataStruct3 = SecsDataUtils.calculateArrayDataLength(dataArray, SecsDataTypeCode2.valueOf(ppbodyType));
                                S7F6Response s7F6Response = new S7F6Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ppidDataStuct, dataStruct3, dataArray);
                                context.sendToWire(s7F6Response);
//                                sendWebSocketMessage(s7F6Response);
                            } else {
                                DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{});
                                S7F6ResponseL s7F6ResponseL = new S7F6ResponseL(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                                context.sendToWire(s7F6ResponseL);
//                                sendWebSocketMessage(s7F6ResponseL);
                            }
                        } else {
                            DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{});
                            S7F6ResponseL s7F6ResponseL = new S7F6ResponseL(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                            context.sendToWire(s7F6ResponseL);
//                            sendWebSocketMessage(s7F6ResponseL);
                        }
                        break;
                    case COMMAND_S7F23:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S7F23);
                        int s7F24AckCode = defaultResponseMessage.getAckCode();
                        DataStructNormal ackDS = new DataStructNormal(SecsDataTypeCode.BYTE, (short) 1, new byte[]{(byte) s7F24AckCode});
                        S7F24Response s7F24Response = new S7F24Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), ackDS);
                        context.sendToWire(s7F24Response);
                        sendWebSocketMessage(s7F24Response);
                        break;
                    case COMMAND_S7F25:
                        defaultResponseMessage = (DefaultResponseMessage) map.get(COMMAND_S7F25);
                        List<Map<String, List<ResItem>>> list = null;
                        try {
                            list = (List<Map<String, List<ResItem>>>) defaultResponseMessage.getData();
                            if (list != null && list.size() > 0) {
                                List<ResItem> resItems1 = list.get(0).get("mdln");
                                List<ResItem> resItems2 = list.get(0).get("softRev");
                                List<ResItem> resItems3 = list.get(0).get("ppid");
                                String mdln = String.valueOf(resItems1.get(0).getValue());
                                String softRev = String.valueOf(resItems2.get(0).getValue());
                                String ppid = String.valueOf(resItems3.get(0).getValue());
                                DataStructNormal ppidDataStuct = new DataStructNormal(SecsDataTypeCode.STRING, (short) ppid.getBytes().length, ppid.getBytes());
                                DataStructNormal mdlnDataStuct = new DataStructNormal(SecsDataTypeCode.STRING, (short) mdln.getBytes().length, mdln.getBytes());
                                DataStructNormal softrevDataStuct = new DataStructNormal(SecsDataTypeCode.STRING, (short) softRev.getBytes().length, softRev.getBytes());
                                List<ProcessDataStruct> processDataStructList = new ArrayList<>();
                                for (int i = 0; i < list.size(); i++) {
                                    Map<String, List<ResItem>> resItemMap = list.get(i);
                                    List<ResItem> cCodeResItemList = (List<ResItem>) resItemMap.get("ccode");
                                    DataStructNormal ccode = (DataStructNormal) RequestMatchUtil.buildResItemDataStruct(cCodeResItemList.get(0));
                                    List<ResItem> recipeParamResItems = (List<ResItem>) resItemMap.get("recipeParam");
                                    List<DataStructNormal> dataStructNormalList = new ArrayList<>();
                                    for (int j = 0; j < recipeParamResItems.size(); j++) {
                                        DataStructNormal dataStructNormal = (DataStructNormal) RequestMatchUtil.buildResItemDataStruct(recipeParamResItems.get(j));
                                        dataStructNormalList.add(dataStructNormal);
                                    }
                                    DataStructNormal[] dataStructNormalStructs = dataStructNormalList.toArray(new DataStructNormal[0]);
                                    DataStruct2 struct2 = calculateDataLength(dataStructNormalStructs);
                                    ProcessDataStruct processDataStruct = new ProcessDataStruct(ccode, struct2, dataStructNormalStructs);
                                    processDataStructList.add(processDataStruct);
                                }
                                ProcessDataStruct[] processDataStructs = processDataStructList.toArray(new ProcessDataStruct[0]);//暂按一个返回
                                DataStruct2 dataStruct21 = calculateDataLength(processDataStructs);
                                S7F26Response s7F26Response = new S7F26Response(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(),
                                    secsPacket.getSystemBytes(), ppidDataStuct, mdlnDataStuct, softrevDataStuct, dataStruct21, processDataStructs);
                                context.sendToWire(s7F26Response);
                                sendWebSocketMessage(s7F26Response);
                            } else {
                                DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{});
                                S7F26Response0 s7F26Response0 = new S7F26Response0(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                                context.sendToWire(s7F26Response0);
                                sendWebSocketMessage(s7F26Response0);
                            }
                        } catch (Exception e) {
                            logger.error("S7F25Request get recipe data error");
                            DataStructNormal dataStruct = new DataStructNormal(SecsDataTypeCode.LIST, (short) 0, new byte[]{});
                            S7F26Response0 s7F26Response0 = new S7F26Response0(secsPacket.getDeviceID(), secsPacket.getPType(), secsPacket.getStype(), secsPacket.getSystemBytes(), dataStruct);
                            context.sendToWire(s7F26Response0);
                            sendWebSocketMessage(s7F26Response0);
                        }
                        break;
                }
            } else {
                sendS9F7Commond(secsPacket);
            }
        } catch (Exception e) {
            logger.error("SecsgemProtocolLogic accept commond with error", e);
        }
    }
}
