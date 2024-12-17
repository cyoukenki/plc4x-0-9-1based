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
package org.apache.plc4x.java.api.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


@Component(service = Activator.class,immediate = true)
public class Activator implements BundleActivator {
    private static final Logger LOGGER = LoggerFactory.getLogger(Activator.class);
    public Activator() {
    }

    public void start(BundleContext context) {
        LOGGER.info("Starting the {} bundle!", Activator.class);

    }

    public void stop(BundleContext context) {
        LOGGER.info("Stopping the {} bundle!", Activator.class);
        }


//    @Reference(
//        name = "com.omron.gc.omron.core.server.OmronServer",
//        service = OmronServer.class,
//        cardinality = ReferenceCardinality.OPTIONAL,
//        policy = ReferencePolicy.DYNAMIC,
//        unbind = "unRegisterOmronServer"
//    )
//    protected void setOmronServer(com.omron.gc.omron.core.server.OmronServer omronServer) {
//        DataHolder.getInstance().setRegister(true);
//
//    }
//
//    protected void unRegisterOmronServer(com.omron.gc.omron.core.server.OmronServer entityService) {
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