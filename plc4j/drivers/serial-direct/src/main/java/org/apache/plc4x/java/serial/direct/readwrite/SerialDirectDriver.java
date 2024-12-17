package org.apache.plc4x.java.serial.direct.readwrite;/*
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
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.authentication.PlcAuthentication;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcDiscoveryRequest;
import org.apache.plc4x.java.api.metadata.PlcDriverMetadata;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.serial.direct.readwrite.configuration.SerialDirectConfiguration;
import org.apache.plc4x.java.serial.direct.readwrite.field.SerialDirectField;
import org.apache.plc4x.java.serial.direct.readwrite.field.SerialDirectFieldHandler;
import org.apache.plc4x.java.serial.direct.readwrite.model.SerialDirect;
import org.apache.plc4x.java.serial.direct.readwrite.model.io.SerialDirectIO;
import org.apache.plc4x.java.serial.direct.readwrite.protocol.SerialDirectProtocolLogic;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.*;
import org.apache.plc4x.java.spi.optimizer.BaseOptimizer;
import org.apache.plc4x.java.spi.transport.Transport;
import org.apache.plc4x.java.spi.values.IEC61131ValueHandler;

import java.util.function.ToIntFunction;

/**
 * Test driver holding its state in the client process.
 * The URL schema is {@code simulated:<device_name>}.
 * Devices are created each time a connection is established and should not be reused.
 * Every device contains a random value generator accessible by address {@code random}.
 * Any value can be stored into test devices, however the state will be gone when connection is closed.
 */
public class SerialDirectDriver extends GeneratedDriverBase<SerialDirect> {

    @Override
    public String getProtocolCode() {
        return "serial-direct";
    }

    @Override
    public String getProtocolName() {
        return "serial-direct";
    }

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return SerialDirectConfiguration.class;
    }

    @Override
    protected PlcFieldHandler getFieldHandler() {
        return new SerialDirectFieldHandler();
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

//    @Override
//    protected boolean canSubscribe() {
//        return true;
//    }

    @Override
    protected String getDefaultTransport() {
        return "serial";
    }

    @Override
    protected ProtocolStackConfigurer<SerialDirect> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(SerialDirect.class, SerialDirectIO.class)
            .withProtocol(SerialDirectProtocolLogic.class)
//            .withPacketSizeEstimator(ByteLengthEstimator.class)
            .withParserArgs(true)
            .build();
    }
    public static class ByteLengthEstimator implements ToIntFunction<ByteBuf> {
        @Override
        public int applyAsInt(ByteBuf byteBuf) {
            if (byteBuf.readableBytes() >= 4) {
                //Second byte for the size and then add the header size 24
                int size = byteBuf.getUnsignedShort(byteBuf.readerIndex()+1)+24;
                return size;
            }
            return -1;
        }
    }
    @Override
    public SerialDirectField prepareField(String query){
        return SerialDirectField.of(query);
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
    protected BaseOptimizer getOptimizer() {
        return super.getOptimizer();
    }

    @Override
    protected ProtocolStackConfigurer<SerialDirect> getStackConfigurer(Transport transport) {
        return super.getStackConfigurer(transport);
    }

    @Override
    protected void initializePipeline(ChannelFactory channelFactory) {
        super.initializePipeline(channelFactory);
    }

    @Override
    public PlcConnection getConnection(String connectionString) throws PlcConnectionException {
        return super.getConnection(connectionString);
    }

    @Override
    public PlcConnection getConnection(String url, PlcAuthentication authentication) throws PlcConnectionException {
        return super.getConnection(url, authentication);
    }

    @Override
    public PlcDriverMetadata getMetadata() {
        return super.getMetadata();
    }

    @Override
    public PlcDiscoveryRequest.Builder discoveryRequestBuilder() {
        return super.discoveryRequestBuilder();
    }

}
