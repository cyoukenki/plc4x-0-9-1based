package org.apache.plc4x.java.serial.direct.readwrite.protocol;/*
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.omron.gc.data.provider.utils.DataProviderUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.types.PlcRequestProtocolType;
import org.apache.plc4x.java.api.types.PlcRequestType;
import org.apache.plc4x.java.serial.direct.readwrite.model.SerialDirectCommandInfo;
import org.apache.plc4x.java.serial.direct.readwrite.configuration.SerialDirectConfiguration;
import org.apache.plc4x.java.serial.direct.readwrite.model.PushMessageInfo;
import org.apache.plc4x.java.serial.direct.readwrite.model.SerialDirect;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.DefaultPlcRequestMessage;
import org.apache.plc4x.java.spi.messages.DefaultServerConnectorListener;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.transport.serial.SerialChannel;
import org.apache.plc4x.java.transport.serial.SerialSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.plc4x.java.serial.direct.readwrite.constant.SerialDirectConstants.DIRECTION_IN;

public class SerialDirectProtocolLogic extends Plc4xProtocolBase<SerialDirect> implements HasConfiguration<SerialDirectConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(SerialDirectProtocolLogic.class);
    private SerialDirectConfiguration configuration;
    private RequestTransactionManager tm;
    private ConversationContext<SerialDirect> context;
    private String pushMessageContent;//推送主题内容
    private String packageData = "";
    private DefaultServerConnectorListener defaultServerConnectorListener;
    @Override
    public void close(ConversationContext<SerialDirect> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("SerialDirectProtocolLogic---comPort:{}---close",comPort);
    }

    @Override
    public void setConfiguration(SerialDirectConfiguration configuration) {
        this.configuration = configuration;
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<SerialDirect> context) {
        this.context = context;
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("SerialDirectProtocolLogic---comPort:{}---onConnect",comPort);
    }

    @Override
    public void onDisconnect(ConversationContext<SerialDirect> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("SerialDirectProtocolLogic---comPort:{}---onDisconnect",comPort);
    }

    @Override
    public void onDiscover(ConversationContext<SerialDirect> context) {
        logger.debug("SerialDirectProtocolLogic---------------------onDiscover");
    }

    @Override
    public void onHostConnect(ConversationContext<SerialDirect> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info(comPort +"---onHostConnect");
    }

    @Override
    public void onHostDisconnect(ConversationContext<SerialDirect> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info(comPort +"---onHostDisconnect");
    }

    @Override
    protected void decode(ConversationContext<SerialDirect> context, SerialDirect msg){
        this.context = context;
        defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        contentSuffer(msg.getPcb(),context);
    }

    private void contentSuffer(String content, ConversationContext<SerialDirect> context) {
        String[] split = content.split("\r");
        if((content.contains("\r") && split.length >= 0)) {//处理报文分多次发送情况（包含\r）
            if(split.length > 0) {
                packageData += split[0];
                content = content.substring(content.indexOf(split[0])+split[0].length()+1,content.length());
            }
            logger.info("decode-------------------------"+packageData);

            String comPort = ((SerialSocketAddress) ((SerialChannel) this.context.getChannel()).getComPort().getAddress()).getIdentifier();
            sendWebSocketMessage(packageData,comPort,DIRECTION_IN);
            Map<String,Object> rdParamMap = new HashMap<>();//记录指令参数信息
            SerialDirectCommandInfo serialDirectCommandInfo = new SerialDirectCommandInfo(packageData,comPort,packageData,configuration.getRailNo());
            rdParamMap.put("serialDirectCommandInfo",serialDirectCommandInfo);
            DefaultPlcRequestMessage defaultPlcRequestMessage = new DefaultPlcRequestMessage();
            defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.SUB);
            defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.SERIAL_DIRECT);
            defaultPlcRequestMessage.setParamMap(rdParamMap);
            //cxf plc4j transports 实现监听，获取消息转发至restAPI，监听扫码器消息，并通过HSC协议与mes通讯，组装结果返回
            defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
            packageData = "";
            if(content.contains("\r") && split.length > 0) {
                contentSuffer(content, context);
            }
        } else {
            packageData += content;
        }
    }

    public void sendWebSocketMessage(String pushMessgeContent, String comInfo, String type){
        logger.info(pushMessgeContent);
        if(StringUtils.isNoneEmpty(configuration.getWebTopic())) {
            try {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("messgeContent",new PushMessageInfo("SCANNER:"+comInfo,type+": "+pushMessgeContent).toString());
                DataProviderUtil.sendText(configuration.getWebTopic(), new Gson().toJson(jsonObject));
            } catch (IOException e) {
                logger.error("DataProvider sendText Message error with {}",e.getMessage(),e);
            }
        }
    }
}
