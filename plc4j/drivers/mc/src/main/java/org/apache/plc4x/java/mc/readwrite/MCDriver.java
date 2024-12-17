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
package org.apache.plc4x.java.mc.readwrite;

import io.netty.buffer.ByteBuf;
import org.apache.plc4x.java.mc.readwrite.configuration.MCConfiguration;
import org.apache.plc4x.java.mc.readwrite.field.McField;
import org.apache.plc4x.java.mc.readwrite.field.McFieldHandler;
import org.apache.plc4x.java.mc.readwrite.io.McPacketIO;
import org.apache.plc4x.java.mc.readwrite.protocol.McProtocolLogic;
import org.apache.plc4x.java.mc.readwrite.util.ConvertUtils;
import org.apache.plc4x.java.spi.connection.GeneratedDriverBase;
import org.apache.plc4x.java.spi.connection.ProtocolStackConfigurer;
import org.apache.plc4x.java.spi.optimizer.BaseOptimizer;
import org.apache.plc4x.java.spi.optimizer.SingleFieldOptimizer;
import org.apache.plc4x.java.spi.values.IEC61131ValueHandler;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.SingleProtocolStackConfigurer;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

import static org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.charToHex;

public class MCDriver extends GeneratedDriverBase<McPacket> {

    @Override
    public String getProtocolCode() {
        return "mc";
    }

    @Override
    public String getProtocolName() {
        return "mc";
    }

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return MCConfiguration.class;
    }

    @Override
    protected McFieldHandler getFieldHandler() {
        return new McFieldHandler();
    }

    @Override
    protected PlcValueHandler getValueHandler() {
        return new IEC61131ValueHandler();
    }

    /**
     * This protocol doesn't have a disconnect procedure, so there is no need to wait for a login to finish.
     *
     * @return false
     */
    @Override
    protected boolean awaitDisconnectComplete() {
        return false;
    }

    @Override
    protected String getDefaultTransport() {
        return "tcp";
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
    protected boolean awaitSetupComplete() {
        return false;
    }

    @Override
    protected ProtocolStackConfigurer<McPacket> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(McPacket.class, McPacketIO.class)
            .withProtocol(McProtocolLogic.class)
            .withPacketSizeEstimator(ByteLengthEstimator.class)
//            .littleEndian()
            .build();
    }

    /**
     * Estimate the Length of a Packet
     */
    public static class ByteLengthEstimator implements ToIntFunction<ByteBuf> {
        @Override
        public int applyAsInt(ByteBuf byteBuf) {
            if (byteBuf.readableBytes() >= 18) {
                //Second byte for the size and then add the header size 24
//                int size = byteBuf.getInt(byteBuf.readerIndex() + 14) + 18;
                int size = Integer.parseInt(ConvertUtils.hexToChar(Integer.toHexString(byteBuf.getInt(byteBuf.readerIndex() + 14))), 16) + 18;
                return size;
            }
            return -1;
        }
    }

    /**
     * Consumes all Bytes till another Magic Byte is found
     */
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
    public McField prepareField(String query) {
//        return McField.of(query);
        return null;
    }

}
