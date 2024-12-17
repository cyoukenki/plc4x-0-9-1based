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

// import com.omron.gc.omron.core.api.SentinelHandlerService;
import org.apache.plc4x.java.api.PlcDriver;
import org.apache.plc4x.java.secsgem.readwrite.EqSeparateRepuest;
import org.apache.plc4x.java.secsgem.readwrite.SecsPacket;
import org.apache.plc4x.java.secsgem.readwrite.model.HostChannelInfo;
import org.apache.plc4x.java.spi.ConversationContext;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.wiring.BundleWiring;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Hashtable;
import java.util.Map;
import java.util.ServiceLoader;

@Component(service = Activator.class, immediate = true)
public class Activator implements BundleActivator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Activator.class);
    public static BundleContext bundleContext = null;
    public static final String PROTOCOL_NAME = "org.apache.plc4x.driver.name";
    public static final String PROTOCOL_CODE = "org.apache.plc4x.driver.code";
    private ServiceRegistration<PlcDriver> reg;

    public Activator() {
    }

    public void start(BundleContext context) {
        LOGGER.info("Starting the {} bundle!", Activator.class);
        bundleContext = context;
        ServiceLoader<PlcDriver> drivers = ServiceLoader.load(PlcDriver.class, context.getBundle().adapt(BundleWiring.class).getClassLoader());
        for (PlcDriver driver : drivers) {
            Hashtable<String, String> props = new Hashtable<>();
            props.put(PROTOCOL_CODE, driver.getProtocolCode());
            props.put(PROTOCOL_NAME, driver.getProtocolName());
            reg = context.registerService(PlcDriver.class, driver, props);
        }
    }

    public void stop(BundleContext context) {
        LOGGER.info("Stopping the {} bundle!", Activator.class);
        reg.unregister();
        Map<String, HostChannelInfo> channelMap = DataHolder.getInstance().getChannelMap();
        EqSeparateRepuest eqSeparateRepuest = new EqSeparateRepuest(0, (short) 0x00, (short) 0x09, 1);
        for (Map.Entry<String, HostChannelInfo> entry : channelMap.entrySet()
        ) {
            HostChannelInfo hostChannelInfo = entry.getValue();
            ConversationContext<SecsPacket> conversationContext = hostChannelInfo.getContext();
            conversationContext.sendRequest(eqSeparateRepuest)
                .expectResponse(SecsPacket.class, Duration.ofMillis(Long.parseLong(DataHolder.getInstance().getBaseSettingInfo().getNetworkInterCharactorTimeOut()) * 1000))
                .handle(secsPacket -> {
                    LOGGER.info("send separateRepuest", secsPacket.getDeviceID());
                });
        }
    }

//    @Reference(
//        name = "com.omron.gc.omron.core.server.OmronServer",
//        service = com.omron.gc.omron.core.server.OmronServer.class,
//        cardinality = ReferenceCardinality.OPTIONAL,
//        policy = ReferencePolicy.DYNAMIC,
//        unbind = "unsetOmronServer"
//    )
//    protected void setOmronServer(com.omron.gc.omron.core.server.OmronServer omronServer) {
//        DataHolder.getInstance().setRegister(true);
//
//    }
//
//    protected void unsetOmronServer(com.omron.gc.omron.core.server.OmronServer entityService) {
//        DataHolder.getInstance().setRegister(false);
//    }

    // @Reference(
    //     name = "com.omron.gc.omron.core.api.SentinelHandlerService",
    //     service = SentinelHandlerService.class,
    //     cardinality = ReferenceCardinality.OPTIONAL,
    //     policy = ReferencePolicy.DYNAMIC,
    //     unbind = "unsetSentinelHandlerService"
    // )
    // protected void setSentinelHandlerService(SentinelHandlerService sentinelHandlerService, Map<String,String> sentinelProperties) {
    //     boolean isHasp = sentinelHandlerService.verifySentinel(Integer.parseInt(sentinelProperties.get("featureId")));
    //     LOGGER.info("Sentinel key isHasp = {}",isHasp);
    //     if(isHasp) {
    //         DataHolder.getInstance().setRegister(true);
    //     } else {
    //         DataHolder.getInstance().setRegister(false);
    //     }
    // }

    // protected void unsetSentinelHandlerService(SentinelHandlerService sentinelHandlerService, Map<String,String> sentinelProperties) {
    //     DataHolder.getInstance().setRegister(false);
    // }
}