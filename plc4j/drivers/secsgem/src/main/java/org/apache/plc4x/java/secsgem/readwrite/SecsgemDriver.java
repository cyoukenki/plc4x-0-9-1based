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
package org.apache.plc4x.java.secsgem.readwrite;

import io.netty.buffer.ByteBuf;
import org.apache.plc4x.java.secsgem.readwrite.configuration.SecsgemConfiguration;
import org.apache.plc4x.java.secsgem.readwrite.field.SecsgemField;
import org.apache.plc4x.java.secsgem.readwrite.field.SecsgemFieldHandler;
import org.apache.plc4x.java.secsgem.readwrite.io.SecsPacketIO;
import org.apache.plc4x.java.secsgem.readwrite.protocol.SecsgemProtocolLogic;
import org.apache.plc4x.java.spi.values.IEC61131ValueHandler;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.GeneratedDriverBase;
import org.apache.plc4x.java.spi.connection.PlcFieldHandler;
import org.apache.plc4x.java.spi.connection.ProtocolStackConfigurer;
import org.apache.plc4x.java.spi.connection.SingleProtocolStackConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;


public class SecsgemDriver extends GeneratedDriverBase<SecsPacket> {
    public static final int PORT = 44818;
    private static final Logger logger = LoggerFactory.getLogger(SecsgemDriver.class);

    @Override
    public String getProtocolCode() {
        return "secsgem";
    }

    @Override
    public String getProtocolName() {
        return "secsgem";
    }

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return SecsgemConfiguration.class;
    }

    @Override
    protected PlcFieldHandler getFieldHandler() {
        return new SecsgemFieldHandler();
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
    protected ProtocolStackConfigurer<SecsPacket> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(SecsPacket.class, SecsPacketIO.class)
            .withProtocol(SecsgemProtocolLogic.class)
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
            if (byteBuf.readableBytes() >= 4) {
                //Second byte for the size and then add the header size 24
                int size = (int) byteBuf.getUnsignedInt(byteBuf.readerIndex()) + 4;
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
    public SecsgemField prepareField(String query) {
        return SecsgemField.of(query);
    }

    @Override
    protected boolean awaitSetupComplete() {
        return super.awaitSetupComplete();
    }

    public SecsgemDriver() {
    }


}
