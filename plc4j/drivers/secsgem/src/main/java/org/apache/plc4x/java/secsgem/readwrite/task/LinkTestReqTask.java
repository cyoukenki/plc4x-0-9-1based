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
package org.apache.plc4x.java.secsgem.readwrite.task;

import org.apache.plc4x.java.secsgem.readwrite.EqLinktestRequest;
import org.apache.plc4x.java.secsgem.readwrite.SecsPacket;
import org.apache.plc4x.java.secsgem.readwrite.internal.DataHolder;
import org.apache.plc4x.java.secsgem.readwrite.util.LogFormatUtils;
import org.apache.plc4x.java.secsgem.readwrite.util.WebSocketUtil;
import org.apache.plc4x.java.spi.ConversationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.apache.plc4x.java.secsgem.readwrite.constant.ServerConstants.WEBSOCKET_TOPIC;

public class LinkTestReqTask implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(LinkTestReqTask.class);
    private ConversationContext<SecsPacket> context;
    public LinkTestReqTask(ConversationContext<SecsPacket> context) {
        this.context = context;
    }

    @Override
    public void run() {
        while (DataHolder.getInstance().getBaseSettingInfo().getLoggingLinkTestMessage().equals("enabled")) {
            EqLinktestRequest eqLinktestRequest = new EqLinktestRequest(0,(short) 0x00,(short) 0x05, 1);
            WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, LogFormatUtils.formatSecsPacketSendData(eqLinktestRequest));
            context.sendRequest(eqLinktestRequest)
                .expectResponse(SecsPacket.class, Duration.ofMillis(Long.parseLong(DataHolder.getInstance().getBaseSettingInfo().getNetworkInterCharactorTimeOut())*1000)
                ).handle(p -> {
                    short stype = p.getStype();
                    logger.info("link test",p.getDeviceID());
                    WebSocketUtil.sendWebSocketMessage(WEBSOCKET_TOPIC, LogFormatUtils.formatSecsPacketReceiveData(p));
                });
            try {
                Thread.sleep(Long.parseLong(DataHolder.getInstance().getBaseSettingInfo().getLinkTestReqSendInterval())*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
