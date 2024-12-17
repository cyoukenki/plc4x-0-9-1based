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
package org.apache.plc4x.java.secsgem.readwrite.internal;


import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.plc4x.java.secsgem.readwrite.configuration.SecsgemParam;
import org.apache.plc4x.java.secsgem.readwrite.model.BaseSettingInfo;
import org.apache.plc4x.java.secsgem.readwrite.model.HostChannelInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * DataHolder to hold org.wso2.carbon.kernel.CarbonRuntime instance referenced
 * through org.wso2.carbon.helloworld.internal.ServiceComponent.
 *
 * @since 1.0.0
 */
public class DataHolder {
    Logger logger = Logger.getLogger(DataHolder.class.getName());
    private static DataHolder instance = new DataHolder();
    private final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private Map<String, HostChannelInfo> channelMap = new HashMap<>();
    private Map<String, Integer> deviceMap = new HashMap<>();
    private SecsgemParam secsgemParam;//secsgem参数
    private BaseSettingInfo baseSettingInfo;
    private String protocalDriverUrl;
    private Map<String, Boolean> ppidValidMap = new HashMap<>();
    private boolean isRegister = false;

    private DataHolder() {
    }

    public static DataHolder getInstance() {
        return instance;
    }

    public void addChannel(Channel channel) {
        channelGroup.add(channel);
    }

    public Channel findChannel(ChannelId channelId) {
        Channel channel = channelGroup.find(channelId);
        return channel;
    }

    public boolean removeChannel(Channel channel) {
        return channelGroup.remove(channel);
    }


    public Map<String, HostChannelInfo> getChannelMap() {
        return channelMap;
    }

    public void setChannelMap(Map<String, HostChannelInfo> channelMap) {
        this.channelMap = channelMap;
    }

    public Map<String, Integer> getDeviceMap() {
        return deviceMap;
    }

    public void setDeviceMap(Map<String, Integer> deviceMap) {
        this.deviceMap = deviceMap;
    }

    public void setSecsgemParam(SecsgemParam secsgemParam) {
        this.secsgemParam = secsgemParam;
    }

    public SecsgemParam getSecsgemParam() {
        return secsgemParam;
    }

    public void setBaseSettingInfo(BaseSettingInfo baseSettingInfo) {
        this.baseSettingInfo = baseSettingInfo;
    }

    public BaseSettingInfo getBaseSettingInfo() {
        return baseSettingInfo;
    }

    public void setProtocalDriverUrl(String protocalDriverUrl) {
        this.protocalDriverUrl = protocalDriverUrl;
    }

    public String getProtocalDriverUrl() {
        return protocalDriverUrl;
    }

    public Map<String, Boolean> getPpidValidMap() {
        return ppidValidMap;
    }

    public void setPpidValidMap(Map<String, Boolean> ppidValidMap) {
        this.ppidValidMap = ppidValidMap;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }
}
