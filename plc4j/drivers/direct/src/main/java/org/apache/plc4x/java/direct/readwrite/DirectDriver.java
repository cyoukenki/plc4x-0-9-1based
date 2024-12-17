package org.apache.plc4x.java.direct.readwrite;
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

import io.netty.buffer.ByteBuf;
import org.apache.plc4x.java.api.listener.MessageExchangeListener;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.direct.readwrite.configuration.DirectConfiguration;
import org.apache.plc4x.java.direct.readwrite.field.DirectField;
import org.apache.plc4x.java.direct.readwrite.field.DirectFieldHandler;
import org.apache.plc4x.java.direct.readwrite.model.DirectPacket;
import org.apache.plc4x.java.direct.readwrite.model.io.DirectPacketIO;
import org.apache.plc4x.java.direct.readwrite.protocol.DirectProtocolLogic;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.*;
import org.apache.plc4x.java.spi.values.IEC61131ValueHandler;

import java.util.function.ToIntFunction;

/**
 * Test driver holding its state in the client process.
 * The URL schema is {@code simulated:<device_name>}.
 * Devices are created each time a connection is established and should not be reused.
 * Every device contains a random value generator accessible by address {@code random}.
 * Any value can be stored into test devices, however the state will be gone when connection is closed.
 */
public class DirectDriver extends GeneratedDriverBase<DirectPacket> {
    public static MessageExchangeListener messageExchangeListener;
    @Override
    public String getProtocolCode() {
        return "direct";
    }

    @Override
    public String getProtocolName() {
        return "direct";
    }

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return DirectConfiguration.class;
    }

    @Override
    protected PlcFieldHandler getFieldHandler() {
        return new DirectFieldHandler();
    }

    @Override
    protected PlcValueHandler getValueHandler() {
        return new IEC61131ValueHandler();
    }

    @Override
    protected boolean awaitSetupComplete() {
        return false;
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
    protected boolean canRead() {
        return true;
    }

    @Override
    protected boolean canWrite() {
        return true;
    }

    @Override
    protected String getDefaultTransport() {
        return "serial";
    }

    @Override
    protected ProtocolStackConfigurer<DirectPacket> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(DirectPacket.class, DirectPacketIO.class)
            .withProtocol(DirectProtocolLogic.class)
            .withPacketSizeEstimator(ByteLengthEstimator.class)
            .withParserArgs(true)
            .build();
    }
    public static class ByteLengthEstimator implements ToIntFunction<ByteBuf> {
        @Override
        public int applyAsInt(ByteBuf byteBuf) {
            // TODO: we might need to try multiple times because the ln might not be here in time
            for (int i = 0; i < byteBuf.readableBytes(); i++) {
                boolean hasOneMore = i + 1 < byteBuf.readableBytes();

                char currentChar = (char) byteBuf.getByte(i);

                boolean isCR = currentChar == '\r';
                boolean followUpIsLF = hasOneMore && (byteBuf.getByte(i + 1) == '\n');
                boolean followUpIsNotLF = hasOneMore && (byteBuf.getByte(i + 1) != '\n');

                if ((!hasOneMore && isCR) || (isCR && followUpIsNotLF)) {
                    return i + 1;
                }
                if (isCR && followUpIsLF) {
                    return i + 2;
                }
            }
            return -1;
        }
    }
    @Override
    public DirectField prepareField(String query){
        return DirectField.of(query);
    }

    @Override
    protected boolean canSubscribe() {
        return true;
    }

    @Override
    protected boolean awaitDiscoverComplete() {
        return super.awaitDiscoverComplete();
    }

    @Override
    protected void initializePipeline(ChannelFactory channelFactory) {
        super.initializePipeline(channelFactory);
    }


    @Override
    public void setListener(MessageExchangeListener messageExchangeListener) {
       this.messageExchangeListener = messageExchangeListener;
    }
}
