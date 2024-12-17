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

import org.apache.plc4x.java.secsgem.readwrite.constant.Alarm;
import org.apache.plc4x.java.secsgem.readwrite.constant.ConnectionStatus;
import org.apache.plc4x.java.secsgem.readwrite.internal.DataHolder;
import org.apache.plc4x.java.secsgem.readwrite.model.HostChannelInfo;
import org.apache.plc4x.java.spi.ConversationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;


public class SelectReqCommandtask implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(SelectReqCommandtask.class);
    private String hostAddress;
    private ScheduledExecutorService scheduledExecutorService;

    public SelectReqCommandtask(String hostAddress, ScheduledExecutorService scheduledExecutorService) {
        this.hostAddress = hostAddress;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public void run() {
        Map<String, HostChannelInfo> channelMap = DataHolder.getInstance().getChannelMap();
        if(channelMap.get(hostAddress) != null) {
            HostChannelInfo hostChannelInfo = channelMap.get(hostAddress);
            ConversationContext context = hostChannelInfo.getContext();
            if(channelMap.get(hostAddress).getConnectionStatus() == ConnectionStatus.SELECTED) {
                scheduledExecutorService.shutdownNow();
            } else {
                context.getChannel().close();
                logger.warn(Alarm.T7.getMsg());
            }
        }
    }
}
