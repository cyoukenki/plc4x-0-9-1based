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
package org.apache.plc4x.java.fins.readwrite;

import io.netty.buffer.ByteBuf;
import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.fins.readwrite.configuration.FinsConfiguration;
import org.apache.plc4x.java.fins.readwrite.field.FinsField;
import org.apache.plc4x.java.fins.readwrite.field.FinsFieldHandler;
import org.apache.plc4x.java.fins.readwrite.io.FinsUdpPacketIO;
import org.apache.plc4x.java.fins.readwrite.protocol.FinsUdpProtocolLogic;
import org.apache.plc4x.java.spi.optimizer.BaseOptimizer;
import org.apache.plc4x.java.spi.optimizer.SingleFieldOptimizer;
import org.apache.plc4x.java.spi.values.IEC61131ValueHandler;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.GeneratedDriverBase;
import org.apache.plc4x.java.spi.connection.PlcFieldHandler;
import org.apache.plc4x.java.spi.connection.ProtocolStackConfigurer;
import org.apache.plc4x.java.spi.connection.SingleProtocolStackConfigurer;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class FinsUdpDriver extends GeneratedDriverBase<FinsUdpPacket> {
    public static final int PORT = 9600;
    public static boolean isConnect = false;
    @Override
    public String getProtocolCode() {
        return "fins-udp";
    }

    @Override
    public String getProtocolName() {
        return "Omron Fins Udp";
    }

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return FinsConfiguration.class;
    }

    @Override
    protected PlcFieldHandler getFieldHandler() {
        return new FinsFieldHandler();
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
        return "udp";
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
    protected ProtocolStackConfigurer<FinsUdpPacket> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(FinsUdpPacket.class, FinsUdpPacketIO.class)
            .withProtocol(FinsUdpProtocolLogic.class)
            .withPacketSizeEstimator(ByteLengthEstimator.class)
//            .littleEndian()
            .build();
    }

    /** Estimate the Length of a Packet */
    public static class ByteLengthEstimator implements ToIntFunction<ByteBuf> {
        @Override
        public int applyAsInt(ByteBuf byteBuf) {
            if (byteBuf.readableBytes() >= 8) {
                int size = byteBuf.readableBytes();
                return size;
            }
            return -1;
        }
    }

     /**Consumes all Bytes till another Magic Byte is found */
    public static class CorruptPackageCleaner implements Consumer<ByteBuf> {
        @Override
        public void accept(ByteBuf byteBuf) {
            while (byteBuf.getUnsignedByte(0) != 0x00) {
                // Just consume the bytes till the next possible start position.
                byteBuf.readByte();
            }
        }
    }
    @Override
    protected BaseOptimizer getOptimizer() {
        return new SingleFieldOptimizer();
    }
    @Override
    public PlcField prepareField(String query){
        return FinsField.of(query);
    }

}
