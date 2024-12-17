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
package org.apache.plc4x.java.upperlink.readwrite.protocol;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.omron.gc.data.provider.utils.DataProviderUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.types.PlcRequestProtocolType;
import org.apache.plc4x.java.api.types.PlcRequestType;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.*;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.transport.serial.SerialChannel;
import org.apache.plc4x.java.transport.serial.SerialSocketAddress;
import org.apache.plc4x.java.upperlink.readwrite.configuration.UpperLinkConfiguration;
import org.apache.plc4x.java.upperlink.readwrite.constant.ErrorContents;
import org.apache.plc4x.java.upperlink.readwrite.constant.OperationStatus;
import org.apache.plc4x.java.upperlink.readwrite.model.*;
import org.apache.plc4x.java.upperlink.readwrite.utils.ConvertUtil;
import org.apache.plc4x.java.upperlink.readwrite.utils.FscUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.apache.plc4x.java.upperlink.readwrite.constant.UpperLinkConstants.DIRECTION_IN;
import static org.apache.plc4x.java.upperlink.readwrite.constant.UpperLinkConstants.DIRECTION_OUT;


public class UpperLinkProtocolLogic extends Plc4xProtocolBase<UpperLink> implements HasConfiguration<UpperLinkConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(UpperLinkProtocolLogic.class);
    private UpperLinkConfiguration configuration;
    private RequestTransactionManager tm;
    private DefaultPlcResponseMessage rdPcbIdResponseMessageRail1;
    private DefaultPlcResponseMessage rdPcbIdResponseMessageRail2;
    private String pushMessageContent;//推送主题内容
    private String packageData = "";
    private DefaultServerConnectorListener defaultServerConnectorListener;

    @Override
    public void setConfiguration(UpperLinkConfiguration configuration) {
        this.configuration = configuration;
        // Set the transaction manager to allow only one message at a time.
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<UpperLink> context) {
        logger.debug("Sending RegisterSession SecsPacket Package");
    }

    @Override
    public void onDisconnect(ConversationContext<UpperLink> context) {
        super.onDisconnect(context);
    }

    @Override
    public void close(ConversationContext<UpperLink> context) {
    }

    @Override
    public void onHostConnect(ConversationContext<UpperLink> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        pushMessageContent = comPort + " connected";//推送内容
    }

    @Override
    public void onHostDisconnect(ConversationContext<UpperLink> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        pushMessageContent = comPort + " disConnected";//推送内容
    }

    @Override
    protected void decode(ConversationContext<UpperLink> context, UpperLink upperLink) {
        this.context = context;
        logger.info("UpperLinkProtocolLogic--decode--:{}", upperLink.toString());
        defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        contentSuffer(upperLink.getContent(), context);
//        String content = upperLink.getContent();
//        Context reqTypeContext= upperLink.getContext();
//        int unitNo = upperLink.getUnitNo();
//        int fcs = upperLink.getFcs();
//        String unitNoStr = ConvertUtil.hexadecimalToString(Integer.toHexString(unitNo));
//        String fcsStr = ConvertUtil.hexadecimalToString(Integer.toHexString(fcs));
//        Integer headerCode = reqTypeContext.getHeaderCode();
//        String headerCodeStr = ConvertUtil.hexadecimalToString(Integer.toHexString(headerCode));
//        switch (headerCodeStr) {
//            case "RD": //DbReadReq 处理读取条码read指令
//                Map<String,Object> rdParamMap = new HashMap<>();//记录指令参数信息
//                String rdBeginingWord = ConvertUtil.hexadecimalToString(Integer.toHexString(((DbReadReq) reqTypeContext).getBeginingWord()));
//                String no_of_words = ConvertUtil.hexadecimalToString(Integer.toHexString(((DbReadReq) reqTypeContext).getNo_of_words()));
//                UpperlinkCommandInfo upperlinkCommandInfoRd = new UpperlinkCommandInfo();
//                upperlinkCommandInfoRd.setRdBeginingWord(rdBeginingWord);
//                upperlinkCommandInfoRd.setNo_of_words(no_of_words);
//                upperlinkCommandInfoRd.setRD(true);
//                rdParamMap.put("upperlinkCommandInfo",upperlinkCommandInfoRd);
//                DefaultPlcRequestMessage defaultPlcRequestMessage = new DefaultPlcRequestMessage();
//                defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
//                defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
//                defaultPlcRequestMessage.setParamMap(rdParamMap);
//                pushMessageContent = unitNoStr + headerCodeStr + rdBeginingWord + no_of_words +fcsStr;//推送内容
//                sendWebSocketMessage(pushMessageContent,DIRECTION_IN);
//                byte[] pcbIdBytes;
//                if(rdBeginingWord.equals("0070") && no_of_words.equals("0016")) {//向外围设备请求pcb id信息32位，第一次，次数根据机器上条码的位数决定，例如64位则为两次)
//                    //cxf plc4j transports 实现监听，获取消息转发至restAPI，监听扫码器消息，并通过HSC协议与mes通讯，组装结果返回
//                    rdPcbIdResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
//                    if(rdPcbIdResponseMessage !=null) {
//                        String pcbId = (String) rdPcbIdResponseMessage.getResponseItems().get(0).getValue();
////                        byte[] bytes = getBytes(value, 32);//不足32位 补00
//                        pcbIdBytes = getPcbIdBytes(pcbId);
//                    } else {//没有读到条码，返回null给设备
//                        pcbIdBytes = getPcbIdBytes("");
//                    }
//                    DbReadRspOK dbReadReq = new DbReadRspOK("00".getBytes(),pcbIdBytes);
//                    UpperLink upperLinkRsp = new UpperLink(upperLink.getUnitNo(),dbReadReq,upperLink.getFcs());
//                    context.sendToWire(upperLinkRsp);
//                    pushMessageContent = unitNoStr + headerCodeStr + "00" + new String(pcbIdBytes) + fcsStr;//推送内容
//                }
//                if(rdBeginingWord.equals("0086") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
//                    defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
//                    if(rdPcbIdResponseMessage !=null && rdPcbIdResponseMessage.getResponseItems().size() > 1) {
//                        String pcbId = (String) rdPcbIdResponseMessage.getResponseItems().get(1).getValue();
//                        pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
////                    byte[] bytes = getBytes(value, 32);//不足32位 补00
//                    } else {//没有读到条码，返回null给设备
//                        pcbIdBytes = getPcbIdBytes("");//转换ASCII码，补00
//                    }
//                    DbReadRspOK dbReadReq = new DbReadRspOK("00".getBytes(),pcbIdBytes);
//                    UpperLink upperLinkRsp = new UpperLink(upperLink.getUnitNo(),dbReadReq,upperLink.getFcs());
//                    context.sendToWire(upperLinkRsp);
//                    pushMessageContent = unitNoStr + headerCodeStr + "00" + new String(pcbIdBytes) + fcsStr;//推送内容
//                }
//                if(rdBeginingWord.equals("0102") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
//                    defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
//                    if(rdPcbIdResponseMessage !=null && rdPcbIdResponseMessage.getResponseItems().size() > 2) {
//                        String pcbId = (String) rdPcbIdResponseMessage.getResponseItems().get(2).getValue();
//                        pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
////                    byte[] bytes = getBytes(value, 32);//不足32位 补00
//                    } else {//没有读到条码，返回null给设备
//                        pcbIdBytes = getPcbIdBytes("");
//                    }
//                    DbReadRspOK dbReadReq = new DbReadRspOK("00".getBytes(),pcbIdBytes);
//                    UpperLink upperLinkRsp = new UpperLink(upperLink.getUnitNo(),dbReadReq,upperLink.getFcs());
//                    context.sendToWire(upperLinkRsp);
//                    pushMessageContent = unitNoStr + headerCodeStr + "00" + new String(pcbIdBytes) + fcsStr;//推送内容
//                }
//                if(rdBeginingWord.equals("0118") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
//                    defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
//                    if(rdPcbIdResponseMessage !=null && rdPcbIdResponseMessage.getResponseItems().size() > 3) {
//                        String pcbId = (String) rdPcbIdResponseMessage.getResponseItems().get(3).getValue();
//                        pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
////                    byte[] bytes = getBytes(value, 32);//不足32位 补00
//                    } else {//没有读到条码，返回null给设备
//                        pcbIdBytes = getPcbIdBytes("");
//                    }
//                    DbReadRspOK dbReadReq = new DbReadRspOK("00".getBytes(),pcbIdBytes);
//                    UpperLink upperLinkRsp = new UpperLink(upperLink.getUnitNo(),dbReadReq,upperLink.getFcs());
//                    context.sendToWire(upperLinkRsp);
//                    pushMessageContent = unitNoStr + headerCodeStr + "00" + new String(pcbIdBytes) + fcsStr;//推送内容
//                }
//                if(rdBeginingWord.equals("0002") && no_of_words.equals("0001")) {
////                    defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
////                    defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
////                    defaultPlcRequestMessage.setParamMap(rdParamMap);
//                    //cxf plc4j transports 实现监听，获取消息转发至restAPI，监听扫码器消息，并通过HSC协议与mes通讯，组装结果返回
//                    defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
//                    DbReadRspOK dbReadReq = new DbReadRspOK("00".getBytes(),"0000".getBytes());
////                    DbReadRspOK dbReadReq = new DbReadRspOK((short) 0,"0001".getBytes());
//                    UpperLink upperLinkRsp = new UpperLink(upperLink.getUnitNo(),dbReadReq,upperLink.getFcs());
//                    context.sendToWire(upperLinkRsp);
//                    pushMessageContent = unitNoStr + headerCodeStr +"000000" + fcsStr;//推送内容
//                }
//                sendWebSocketMessage(pushMessageContent, DIRECTION_OUT);
//                break;
//            case "WD": //DbWriteReq
//                Map<String,Object> wdParamMap = new HashMap<>();//记录指令参数信息
//                String wdBeginingWord = ConvertUtil.hexadecimalToString(Integer.toHexString(((DbWriteReq) reqTypeContext).getBeginingWord()));
////                wdParamMap.put("wdBeginingWord",wdBeginingWord);
////                if(((DbWriteReq) reqTypeContext).getStatusCode().length()>4) {
////                    DbWriteRsp dbWriteRsp = new DbWriteRsp("00".getBytes());
////                    UpperLink upperLinkRsp = new UpperLink(upperLink.getUnitNo(),dbWriteRsp,upperLink.getFcs());
////                    context.sendToWire(upperLinkRsp);
////                }
//                DefaultPlcRequestMessage wdRequestMessage = null;
//                UpperlinkCommandInfo upperlinkCommandInfoWd = new UpperlinkCommandInfo();
//                upperlinkCommandInfoWd.setWdBeginingWord(wdBeginingWord);
//                upperlinkCommandInfoWd.setWdRemainWord(((DbWriteReq) reqTypeContext).getStatusCode());
//                upperlinkCommandInfoWd.setRD(false);
//                pushMessageContent = unitNoStr + headerCodeStr + upperlinkCommandInfoWd.getWdBeginingWord() + upperlinkCommandInfoWd.getWdRemainWord() + fcsStr;//推送内容
//                sendWebSocketMessage(pushMessageContent, DIRECTION_IN);
//                DbWriteRsp dbWriteRsp = new DbWriteRsp("00".getBytes());//wd 统一返回@01WD0052*
//                UpperLink upperLinkRsp = new UpperLink(upperLink.getUnitNo(),dbWriteRsp,upperLink.getFcs());
//                switch (wdBeginingWord) {
//                    case "0006"://Operation Status
//                        String statusCode = ((DbWriteReq) reqTypeContext).getStatusCode().substring(0,4);//处理@01WD0006FFFFFFFF000054* 等0006后非状态值情况
////                        String statusCode = ConvertUtil.hexadecimalToString(Integer.toHexString(((DbWriteReq) reqTypeContext).getWrite_no_of_words()[0]));
////                        String statusCode = ConvertUtil.hexadecimalToString(Integer.toHexString(((DbWriteReq) reqTypeContext).getStatusCode()));
//                        if(OperationStatus.getStatusByCode(statusCode) != null) { //判断状态值是否存在
////                            wdParamMap.put("operationStatusCode",statusCode);
////                            wdParamMap.put("operationStatus",OperationStatus.getStatusByCode(statusCode));
////                            wdParamMap.put("statusInfo",OperationStatus.getStatusByCode(statusCode));
//                            upperlinkCommandInfoWd.setOperationStatusCode(statusCode);
//                            upperlinkCommandInfoWd.setOperationStatus(OperationStatus.getStatusByCode(statusCode));
//                            upperlinkCommandInfoWd.setStatusInfo(OperationStatus.getStatusByCode(statusCode));
//                        } else {
//                            upperlinkCommandInfoWd.setOperationStatusCode(((DbWriteReq) reqTypeContext).getStatusCode());
//                        }
//                        wdParamMap.put("upperlinkCommandInfo",upperlinkCommandInfoWd);
//                        logger.info("operating state----"+OperationStatus.getStatusByCode(statusCode));
//                        wdRequestMessage = new DefaultPlcRequestMessage();
//                        wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
//                        wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
//                        wdRequestMessage.setParamMap(wdParamMap);
//                        defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
//                        context.sendToWire(upperLinkRsp);
//                        break;
//                    case "0001"://输出轨道宽度
//                        String railWidth = ((DbWriteReq) reqTypeContext).getStatusCode();
////                        String railWidth = ConvertUtil.hexadecimalToString(Integer.toHexString(((DbWriteReq) reqTypeContext).getStatusCode()));
////                        String railWidth = ConvertUtil.hexadecimalToString(Integer.toHexString(((DbWriteReq) reqTypeContext).getWrite_no_of_words()[0]));
////                        wdParamMap.put("railWidth",railWidth);
////                        wdParamMap.put("statusInfo","output rail width data:"+railWidth);
//                        upperlinkCommandInfoWd.setRailWidth(railWidth);
//                        upperlinkCommandInfoWd.setStatusInfo("output rail width data:"+railWidth);
//                        wdParamMap.put("upperlinkCommandInfo",upperlinkCommandInfoWd);
//                        wdRequestMessage = new DefaultPlcRequestMessage();
//                        wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
//                        wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
//                        wdRequestMessage.setParamMap(wdParamMap);
//                        defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
//                        context.sendToWire(upperLinkRsp);
//                        break;
//                    case "0002"://Output Ready Flag of Lower device
//                    case "0005":
//                        wdParamMap.put("upperlinkCommandInfo",upperlinkCommandInfoWd);
//                        wdRequestMessage = new DefaultPlcRequestMessage();
//                        wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
//                        wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
//                        wdRequestMessage.setParamMap(wdParamMap);
//                        defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
//                        context.sendToWire(upperLinkRsp);
//                        break;
//                    case "0007"://Error Contents
//                        String errorContentStatusCode = ((DbWriteReq) reqTypeContext).getStatusCode().substring(0,4);//处理@01WD0006FFFFFFFF000054* 等0006后非状态值情况
//                        if(ErrorContents.getStatusByCode(errorContentStatusCode) != null) { //判断状态值是否存在
////                            wdParamMap.put("errorContentStatusCode",statusCode);
////                            wdParamMap.put("errorContentStatus",ErrorContents.getStatusByCode(statusCode));
////                            wdParamMap.put("statusInfo",ErrorContents.getStatusByCode(statusCode));
//                            upperlinkCommandInfoWd.setErrorContentStatus(ErrorContents.getStatusByCode(errorContentStatusCode));
//                            upperlinkCommandInfoWd.setErrorContentStatusCode(errorContentStatusCode);
//                            upperlinkCommandInfoWd.setStatusInfo(ErrorContents.getStatusByCode(errorContentStatusCode));
//                            logger.info("errorContents statusInfo----"+ErrorContents.getStatusByCode(errorContentStatusCode));
//                        }
//                        wdParamMap.put("upperlinkCommandInfo",upperlinkCommandInfoWd);
//                        wdRequestMessage = new DefaultPlcRequestMessage();
//                        wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
//                        wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
//                        wdRequestMessage.setParamMap(wdParamMap);
//                        defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
//                        context.sendToWire(upperLinkRsp);
//                        break;
//                    default:
//                        context.sendToWire(upperLinkRsp);
//                        break;
//                }
//                pushMessageContent = unitNoStr + headerCodeStr +"00" +fcsStr;//推送内容
//                sendWebSocketMessage(pushMessageContent,DIRECTION_OUT);
//                break;
//            default:
//                logger.info("decode ----upperlink unSupport commond");
//                break;
//        }

    }

    private void contentSuffer(String pkgContent, ConversationContext<UpperLink> context) {
//        String c = "@01WD00050";
//        String c1 = "0000000000057*\r@01WD0050";
        String content = pkgContent.replace("\r", "");
        if (StringUtils.isNoneEmpty()) {
            String[] split = content.split("\\*");
            if ((content.contains("*") && split.length >= 0)) {//处理报文分多次发送情况（特殊情况：包含*\r）
                if (split.length > 0) {
                    packageData += split[0];
                    content = content.substring(content.indexOf(split[0]) + split[0].length() + 1, content.length());
                }
                logger.info("decode-------------------------" + packageData);
                if (packageData.contains("WD")) {
                    handleWdCommond(packageData + "*");
                } else {
                    handleRdCommond(packageData + "*");
                }
                packageData = "";
                if (content.contains("*") && split.length > 0) {
                    contentSuffer(content, context);
                }
            } else {
                packageData += content;
            }
        } else {
            packageData = "";
        }

    }

    private void handleWdCommond(String packageData) {
        Map<String, Object> wdParamMap = new HashMap<>();//记录指令参数信息
        String wdBeginingWord = packageData.substring(5, 9);
        String wdRemainWord = packageData.substring(9, packageData.length() - 3);//去掉fcs+*
        DefaultPlcRequestMessage wdRequestMessage = null;
        UpperlinkCommandInfo upperlinkCommandInfoWd = new UpperlinkCommandInfo();
        upperlinkCommandInfoWd.setWdBeginingWord(wdBeginingWord);
        upperlinkCommandInfoWd.setWdRemainWord(wdRemainWord);
        upperlinkCommandInfoWd.setRD(false);
        pushMessageContent = packageData;//推送内容
        sendWebSocketMessage(pushMessageContent, DIRECTION_IN);

        String respPackegeInfo = "@01WD00";
        String fsc = FscUtil.getFsc(respPackegeInfo);
        UpperLink upperLinkRsp = new UpperLink(respPackegeInfo + fsc + "*\r");//wd 统一返回@01WD0052*
        String statusOrWidth = wdRemainWord.substring(0, 4);//处理@01WD0006FFFFFFFF000054* 等0006后非状态值情况
        switch (wdBeginingWord) {
            case "0006"://Operation Status
                if (OperationStatus.getStatusByCode(statusOrWidth) != null) { //判断状态值是否存在
                    upperlinkCommandInfoWd.setOperationStatusCode(statusOrWidth);
                    upperlinkCommandInfoWd.setOperationStatus(OperationStatus.getStatusByCode(statusOrWidth));
                    upperlinkCommandInfoWd.setStatusInfo(OperationStatus.getStatusByCode(statusOrWidth));
                } else {
                    upperlinkCommandInfoWd.setOperationStatusCode(wdRemainWord);
                }
                wdParamMap.put("upperlinkCommandInfo", upperlinkCommandInfoWd);
                logger.info("operating state----" + OperationStatus.getStatusByCode(statusOrWidth));
                wdRequestMessage = new DefaultPlcRequestMessage();
                wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
                wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
                wdRequestMessage.setParamMap(wdParamMap);
                defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
                context.sendToWire(upperLinkRsp);
                break;
            case "0001"://输出轨道宽度
                upperlinkCommandInfoWd.setRailWidth(statusOrWidth);
                upperlinkCommandInfoWd.setStatusInfo("output rail width data:" + statusOrWidth);
                wdParamMap.put("upperlinkCommandInfo", upperlinkCommandInfoWd);
                wdRequestMessage = new DefaultPlcRequestMessage();
                wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
                wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
                wdRequestMessage.setParamMap(wdParamMap);
                defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
                context.sendToWire(upperLinkRsp);
                break;
            case "0002"://Output Ready Flag of Lower device
            case "0005":
                wdParamMap.put("upperlinkCommandInfo", upperlinkCommandInfoWd);
                wdRequestMessage = new DefaultPlcRequestMessage();
                wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
                wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
                wdRequestMessage.setParamMap(wdParamMap);
                defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
                context.sendToWire(upperLinkRsp);
                break;
            case "0007"://Error Contents
                String errorContentStatusCode = wdRemainWord.substring(0, 4);//处理@01WD0006FFFFFFFF000054* 等0006后非状态值情况
                if (ErrorContents.getStatusByCode(errorContentStatusCode) != null) { //判断状态值是否存在
                    upperlinkCommandInfoWd.setErrorContentStatus(ErrorContents.getStatusByCode(errorContentStatusCode));
                    upperlinkCommandInfoWd.setErrorContentStatusCode(errorContentStatusCode);
                    upperlinkCommandInfoWd.setStatusInfo(ErrorContents.getStatusByCode(errorContentStatusCode));
                    logger.info("errorContents statusInfo----" + ErrorContents.getStatusByCode(errorContentStatusCode));
                }
                wdParamMap.put("upperlinkCommandInfo", upperlinkCommandInfoWd);
                wdRequestMessage = new DefaultPlcRequestMessage();
                wdRequestMessage.setPlcRequestType(PlcRequestType.WRITE);
                wdRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
                wdRequestMessage.setParamMap(wdParamMap);
                defaultServerConnectorListener.onRequestMessage(wdRequestMessage);//消息传递cxf restAPI
                context.sendToWire(upperLinkRsp);
                break;
            default:
                context.sendToWire(upperLinkRsp);
                break;
        }
        pushMessageContent = upperLinkRsp.getContent();//推送内容
        sendWebSocketMessage(pushMessageContent, DIRECTION_OUT);
    }

    private void handleRdCommond(String packageData) {
        Map<String, Object> rdParamMap = new HashMap<>();//记录指令参数信息
        String rdBeginingWord = packageData.substring(5, 9);
        String no_of_words = packageData.substring(9, 13);
        UpperlinkCommandInfo upperlinkCommandInfoRd = new UpperlinkCommandInfo();
        upperlinkCommandInfoRd.setRdBeginingWord(rdBeginingWord);
        upperlinkCommandInfoRd.setNo_of_words(no_of_words);
        upperlinkCommandInfoRd.setRD(true);
        rdParamMap.put("upperlinkCommandInfo", upperlinkCommandInfoRd);
        DefaultPlcRequestMessage defaultPlcRequestMessage = new DefaultPlcRequestMessage();
        defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.UPPER_LINK);
        defaultPlcRequestMessage.setParamMap(rdParamMap);
        sendWebSocketMessage(packageData, DIRECTION_IN);//推送内容
        byte[] pcbIdBytes;
        String respPackegeInfo = "@01RD00";
        if (rdBeginingWord.equals("0070") && no_of_words.equals("0016")) {//向外围设备请求pcb id信息32位，第一次，次数根据机器上条码的位数决定，例如64位则为两次)
            //cxf plc4j transports 实现监听，获取消息转发至restAPI，监听扫码器消息，并通过HSC协议与mes通讯，组装结果返回
            rdPcbIdResponseMessageRail1 = defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail1 != null && rdPcbIdResponseMessageRail1.getPlcResponseCode() == PlcResponseCode.OK) {
                String pcbId = (String) rdPcbIdResponseMessageRail1.getResponseItems().get(0).getValue();
//                        byte[] bytes = getBytes(value, 32);//不足32位 补00
                pcbIdBytes = getPcbIdBytes(pcbId);
            } else {//没有读到条码
                pcbIdBytes = getPcbIdBytes("");
            }
            String fsc = FscUtil.getFsc(respPackegeInfo + new String(pcbIdBytes));
            UpperLink upperLinkRsp = new UpperLink(respPackegeInfo + new String(pcbIdBytes).toUpperCase() + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容
        }
        if (rdBeginingWord.equals("0086") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail1 != null && rdPcbIdResponseMessageRail1.getPlcResponseCode() == PlcResponseCode.OK && rdPcbIdResponseMessageRail1.getResponseItems().size() > 1) {
                String pcbId = (String) rdPcbIdResponseMessageRail1.getResponseItems().get(1).getValue();
                pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
//                    byte[] bytes = getBytes(value, 32);//不足32位 补00
            } else {//没有读到条码，返回null给设备
                pcbIdBytes = getPcbIdBytes("");//转换ASCII码，补00
            }
            String fsc = FscUtil.getFsc(respPackegeInfo + new String(pcbIdBytes));
            UpperLink upperLinkRsp = new UpperLink(respPackegeInfo + new String(pcbIdBytes).toUpperCase() + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容
        }
        if (rdBeginingWord.equals("0102") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail1 != null && rdPcbIdResponseMessageRail1.getPlcResponseCode() == PlcResponseCode.OK && rdPcbIdResponseMessageRail1.getResponseItems().size() > 2) {
                String pcbId = (String) rdPcbIdResponseMessageRail1.getResponseItems().get(2).getValue();
                pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
//                    byte[] bytes = getBytes(value, 32);//不足32位 补00
            } else {//没有读到条码，返回null给设备
                pcbIdBytes = getPcbIdBytes("");
            }
            String content = respPackegeInfo + new String(pcbIdBytes);
            String fsc = FscUtil.getFsc(content);
            UpperLink upperLinkRsp = new UpperLink(content.toUpperCase() + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容`
        }
        if (rdBeginingWord.equals("0118") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail1 != null && rdPcbIdResponseMessageRail1.getPlcResponseCode() == PlcResponseCode.OK && rdPcbIdResponseMessageRail1.getResponseItems().size() > 3) {
                String pcbId = (String) rdPcbIdResponseMessageRail1.getResponseItems().get(3).getValue();
                pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
//                    byte[] bytes = getBytes(value, 32);//不足32位 补00
            } else {//没有读到条码，返回null给设备
                pcbIdBytes = getPcbIdBytes("");
            }
            String content = respPackegeInfo + new String(pcbIdBytes);
            String fsc = FscUtil.getFsc(content);
            UpperLink upperLinkRsp = new UpperLink(content.toUpperCase() + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容
        }

        //双轨机器 二轨条码读取

        if (rdBeginingWord.equals("0470") && no_of_words.equals("0016")) {//向外围设备请求pcb id信息32位，第一次，次数根据机器上条码的位数决定，例如64位则为两次)
            //cxf plc4j transports 实现监听，获取消息转发至restAPI，监听扫码器消息，并通过HSC协议与mes通讯，组装结果返回
            rdPcbIdResponseMessageRail2 = defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail2 != null && rdPcbIdResponseMessageRail2.getPlcResponseCode() == PlcResponseCode.OK) {
                String pcbId = (String) rdPcbIdResponseMessageRail2.getResponseItems().get(0).getValue();
//                        byte[] bytes = getBytes(value, 32);//不足32位 补00
                pcbIdBytes = getPcbIdBytes(pcbId);
                String fsc = FscUtil.getFsc(respPackegeInfo + new String(pcbIdBytes));
                UpperLink upperLinkRsp = new UpperLink(respPackegeInfo + new String(pcbIdBytes).toUpperCase() + fsc + "*\r");
                context.sendToWire(upperLinkRsp);
                pushMessageContent = upperLinkRsp.getContent();//推送内容
                //状态码！=0 不回复消息，触发机器报警
            } else {//没有读到条码，返回null给设备
                pcbIdBytes = getPcbIdBytes("");
                String fsc = FscUtil.getFsc(respPackegeInfo + new String(pcbIdBytes));
                UpperLink upperLinkRsp = new UpperLink(respPackegeInfo + new String(pcbIdBytes).toUpperCase() + fsc + "*\r");
                context.sendToWire(upperLinkRsp);
                pushMessageContent = upperLinkRsp.getContent();//推送内容
            }
        }
        if (rdBeginingWord.equals("0486") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail2 != null && rdPcbIdResponseMessageRail2.getPlcResponseCode() == PlcResponseCode.OK && rdPcbIdResponseMessageRail2.getResponseItems().size() > 1) {
                String pcbId = (String) rdPcbIdResponseMessageRail2.getResponseItems().get(1).getValue();
                pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
//                    byte[] bytes = getBytes(value, 32);//不足32位 补00
            } else {//没有读到条码，返回null给设备
                pcbIdBytes = getPcbIdBytes("");//转换ASCII码，补00
            }
            String fsc = FscUtil.getFsc(respPackegeInfo + new String(pcbIdBytes));
            UpperLink upperLinkRsp = new UpperLink(respPackegeInfo + new String(pcbIdBytes).toUpperCase() + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容
        }
        if (rdBeginingWord.equals("0502") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail2 != null && rdPcbIdResponseMessageRail2.getPlcResponseCode() == PlcResponseCode.OK && rdPcbIdResponseMessageRail2.getResponseItems().size() > 2) {
                String pcbId = (String) rdPcbIdResponseMessageRail2.getResponseItems().get(2).getValue();
                pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
//                    byte[] bytes = getBytes(value, 32);//不足32位 补00
            } else {//没有读到条码，返回null给设备
                pcbIdBytes = getPcbIdBytes("");
            }
            String content = respPackegeInfo + new String(pcbIdBytes);
            String fsc = FscUtil.getFsc(content);
            UpperLink upperLinkRsp = new UpperLink(content.toUpperCase() + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容`
        }
        if (rdBeginingWord.equals("0518") && no_of_words.equals("0016")) {//>32位 PCBID 读取剩余
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            if (rdPcbIdResponseMessageRail2 != null && rdPcbIdResponseMessageRail2.getPlcResponseCode() == PlcResponseCode.OK && rdPcbIdResponseMessageRail2.getResponseItems().size() > 3) {
                String pcbId = (String) rdPcbIdResponseMessageRail2.getResponseItems().get(3).getValue();
                pcbIdBytes = getPcbIdBytes(pcbId);//转换ASCII码，补00
//                    byte[] bytes = getBytes(value, 32);//不足32位 补00
            } else {//没有读到条码，返回null给设备
                pcbIdBytes = getPcbIdBytes("");
            }
            String content = respPackegeInfo + new String(pcbIdBytes);
            String fsc = FscUtil.getFsc(content);
            UpperLink upperLinkRsp = new UpperLink(content + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容
        }
        if (rdBeginingWord.equals("0002") && no_of_words.equals("0001")) {
            //cxf plc4j transports 实现监听，获取消息转发至restAPI，监听扫码器消息，并通过HSC协议与mes通讯，组装结果返回
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);

            String content = "@01RD000000";
            String fsc = FscUtil.getFsc(content);
            UpperLink upperLinkRsp = new UpperLink(content.toUpperCase() + fsc + "*\r");
            context.sendToWire(upperLinkRsp);
            pushMessageContent = upperLinkRsp.getContent();//推送内容
        }
        sendWebSocketMessage(pushMessageContent, DIRECTION_OUT);
    }

    @Override
    public CompletableFuture<PlcReadResponse> read(PlcReadRequest readRequest) {
        return null;
    }

    private byte[] getBytes(String s, int length) {
        int fixLength = length - s.getBytes().length;
        if (s.getBytes().length < length) {
            byte[] S_bytes = new byte[length];
            System.arraycopy(s.getBytes(), 0, S_bytes, 0, s.getBytes().length);
            for (int x = length - fixLength; x < length; x++) {
                S_bytes[x] = 0x00;
            }
            return S_bytes;
        }
        return s.getBytes();
    }

    private byte[] getPcbIdBytes(String pcbId) {
        String valueHex = ConvertUtil.stringToHexadecimal(pcbId);
        int lackNum = 64 - valueHex.length();
        if (lackNum > 0) {
            for (int i = 0; i < lackNum; i++) {
                valueHex += "0";
            }
        }
        return valueHex.getBytes();
    }


    public void sendWebSocketMessage(String pushMessgeContent, String type) {
        if (StringUtils.isNoneEmpty(configuration.getWebTopic())) {
            try {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("messgeContent", new PushMessageInfo("UPPER-LINK", type + ": " + pushMessgeContent).toString());
                DataProviderUtil.sendText(configuration.getWebTopic(), new Gson().toJson(jsonObject));
            } catch (IOException e) {
                logger.error("DataProvider sendText Message error with {}", e.getMessage(), e);
            }
        }
    }
}
