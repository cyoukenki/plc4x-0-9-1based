package org.apache.plc4x.java.ckd.readwrite.protocol;/*
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
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.ckd.readwrite.configuration.CkdConfiguration;
import org.apache.plc4x.java.ckd.readwrite.model.PushMessageInfo;
import org.apache.plc4x.java.ckd.readwrite.model.CkdPacket;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.DefaultPlcRequestMessage;
import org.apache.plc4x.java.spi.messages.DefaultPlcResponseMessage;
import org.apache.plc4x.java.spi.messages.DefaultServerConnectorListener;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.transport.serial.SerialChannel;
import org.apache.plc4x.java.transport.serial.SerialSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import static org.apache.plc4x.java.ckd.readwrite.constant.CkdConstants.DIRECTION_IN;
import static org.apache.plc4x.java.ckd.readwrite.constant.CkdConstants.DIRECTION_OUT;

public class CkdProtocolLogic extends Plc4xProtocolBase<CkdPacket> implements HasConfiguration<CkdConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(CkdProtocolLogic.class);
    private CkdConfiguration configuration;
    private RequestTransactionManager tm;
    private ConversationContext<CkdPacket> context;
    private String pushMessageContent;//推送主题内容
    private ScheduledExecutorService scheduledExecutorService;
    private DefaultServerConnectorListener defaultServerConnectorListener;
    private int retryCount = 6;
    private int retryNo = 0;
    private String pcbId;
    private String packageData = "";

    @Override
    public void close(ConversationContext<CkdPacket> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("SerialDirectProtocolLogic---comPort:{}---close", comPort);
    }

    @Override
    public void setConfiguration(CkdConfiguration configuration) {
        this.configuration = configuration;
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<CkdPacket> context) {
        this.context = context;
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("CkdProtocolLogic---comPort:{}---onConnect", comPort);
    }

    @Override
    public void onDisconnect(ConversationContext<CkdPacket> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("CkdProtocolLogic---comPort:{}---onDisconnect", comPort);
        context.getChannel().close();
        ((SerialChannel) context.getChannel()).shutdown();
    }

    @Override
    public void onDiscover(ConversationContext<CkdPacket> context) {
        logger.debug("CkdProtocolLogic---------------------onDiscover");
    }

    @Override
    public void onHostConnect(ConversationContext<CkdPacket> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info(comPort + "---onHostConnect");
    }

    @Override
    public void onHostDisconnect(ConversationContext<CkdPacket> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info(comPort + "---onHostDisconnect");
    }

    @Override
    protected void decode(ConversationContext<CkdPacket> context, CkdPacket msg) {
        this.context = context;
        defaultServerConnectorListener = context.getDefaultServerConnectorListener();
        contentSuffer(msg.getContent(), context);
    }

    private void contentSuffer(String content, ConversationContext<CkdPacket> context) {
        String[] split = content.split("\r");
        if ((content.contains("\r") && split.length >= 0)) {//处理报文分多次发送情况（特殊情况：包含*\r）
            if (split.length > 0) {
                packageData += split[0];
                content = content.substring(content.indexOf(split[0]) + split[0].length() + 1, content.length());
            }
            logger.info("decode-------------------------" + packageData);
            retryNo = 0;
            sendWebSocketMessage(configuration.getWebTopic(), packageData, DIRECTION_IN);//推送内容
            if ("LON".equals(packageData)) {
                pcbId = null;
                readSerialNo(context);
            }
            packageData = "";
            if (content.contains("\r") && split.length > 0) {
                contentSuffer(content, context);
            }
        } else {
            packageData += content;
        }
    }

    private void readSerialNo(ConversationContext<CkdPacket> context) {
        if (retryNo > retryCount) {
            return;
        }
        Map<String, Object> rdParamMap = new HashMap<>();//记录指令参数信息
        retryNo++;
        DefaultPlcRequestMessage defaultPlcRequestMessage = new DefaultPlcRequestMessage();
        defaultPlcRequestMessage.setPlcRequestType(PlcRequestType.READ);
        defaultPlcRequestMessage.setPlcRequestProtocolType(PlcRequestProtocolType.CKD);
        defaultPlcRequestMessage.setParamMap(rdParamMap);
        DefaultPlcResponseMessage defaultPlcResponseMessage = defaultServerConnectorListener.onRequestMessage(defaultPlcRequestMessage);
        if (defaultPlcResponseMessage != null && defaultPlcResponseMessage.getPlcResponseCode() == PlcResponseCode.OK) {
            pcbId = (String) defaultPlcResponseMessage.getResponseItems().get(0).getValue();
            CkdPacket ckdPacket = new CkdPacket(pcbId + "\r");
            context.sendToWire(ckdPacket);
            pushMessageContent = ckdPacket.getContent();
            sendWebSocketMessage(configuration.getWebTopic(), ckdPacket.getContent(), DIRECTION_OUT);
            logger.info("sendSuccess--------------" + pcbId);
            retryNo = 0;
            return;
        } else {
            try {
                Thread.sleep(500);
                readSerialNo(context);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void sendWebSocketMessage(String webTopic, String pushMessgeContent, String type) {
        logger.info(pushMessgeContent);
        if (StringUtils.isNoneEmpty(webTopic)) {
            try {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("messgeContent", new PushMessageInfo("CKD", type + ": " + pushMessgeContent).toString());
                DataProviderUtil.sendText(webTopic, new Gson().toJson(jsonObject));
            } catch (IOException e) {
                logger.error("DataProvider sendText Message error with {}", e.getMessage(), e);
            }
        }
    }
}
