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
package org.apache.plc4x.java.upperlink.readwrite;

import io.netty.buffer.ByteBuf;
import org.apache.plc4x.java.spi.connection.GeneratedDriverBase;
import org.apache.plc4x.java.spi.connection.ProtocolStackConfigurer;
import org.apache.plc4x.java.spi.values.IEC61131ValueHandler;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.PlcFieldHandler;
import org.apache.plc4x.java.spi.connection.SingleProtocolStackConfigurer;
import org.apache.plc4x.java.upperlink.readwrite.configuration.UpperLinkConfiguration;
import org.apache.plc4x.java.upperlink.readwrite.field.UpperLinkField;
import org.apache.plc4x.java.upperlink.readwrite.field.UpperLinkFieldHandler;
import org.apache.plc4x.java.upperlink.readwrite.model.UpperLink;
import org.apache.plc4x.java.upperlink.readwrite.model.io.UpperLinkIO;
import org.apache.plc4x.java.upperlink.readwrite.protocol.UpperLinkProtocolLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class UpperLinkDriver extends GeneratedDriverBase<UpperLink> {
    private static final Logger logger = LoggerFactory.getLogger(UpperLinkDriver.class);
    @Override
    public String getProtocolCode() {
        return "upperlink";
    }

    @Override
    public String getProtocolName() {
        return "upperlink";
    }

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return UpperLinkConfiguration.class;
    }

    @Override
    protected PlcFieldHandler getFieldHandler() {
        return new UpperLinkFieldHandler();
    }

    @Override
    protected PlcValueHandler getValueHandler() {
        return new IEC61131ValueHandler();
    }

    /**
     * This protocol doesn't have a disconnect procedure, so there is no need to wait for a login to finish.
     * @return false
     */
    @Override
    protected boolean awaitDisconnectComplete() {
        return false;
    }

    @Override
    protected String getDefaultTransport() {
        return "serial";
    }

    @Override
    protected boolean canRead() {
        return true;
    }

    @Override
    protected boolean canWrite() {
        return true;
    }

    @Override
    protected ProtocolStackConfigurer<UpperLink> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(UpperLink.class, UpperLinkIO.class)
            .withProtocol(UpperLinkProtocolLogic.class)
            .build();
    }


    @Override
    public UpperLinkField prepareField(String query){
//        return UpperLinkField.of(query);

        return null;
    }

    @Override
    protected boolean awaitSetupComplete() {
        return super.awaitSetupComplete();
    }

}
