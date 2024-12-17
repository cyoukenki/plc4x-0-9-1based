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
package org.apache.plc4x.java.hsc.readwrite;
import io.netty.buffer.ByteBuf;
import org.apache.plc4x.java.hsc.readwrite.configuration.HscConfiguration;
import org.apache.plc4x.java.hsc.readwrite.field.HscField;
import org.apache.plc4x.java.hsc.readwrite.field.HscFieldHandler;
import org.apache.plc4x.java.hsc.readwrite.model.HscPacket;
import org.apache.plc4x.java.hsc.readwrite.model.io.HscPacketIO;
import org.apache.plc4x.java.hsc.readwrite.protocol.HscProtocolLogic;
import org.apache.plc4x.java.spi.values.IEC61131ValueHandler;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.GeneratedDriverBase;
import org.apache.plc4x.java.spi.connection.PlcFieldHandler;
import org.apache.plc4x.java.spi.connection.ProtocolStackConfigurer;
import org.apache.plc4x.java.spi.connection.SingleProtocolStackConfigurer;

import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class HscDriver extends GeneratedDriverBase<HscPacket> {
    public static final int PORT = 44818;
    @Override
    public String getProtocolCode() {
        return "hsc";
    }

    @Override
    public String getProtocolName() {
        return "hsc";
    }

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return HscConfiguration.class;
    }

    @Override
    protected PlcFieldHandler getFieldHandler() {
        return new HscFieldHandler();
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
    protected ProtocolStackConfigurer<HscPacket> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(HscPacket.class, HscPacketIO.class)
            .withProtocol(HscProtocolLogic.class)
            .withPacketSizeEstimator(ByteLengthEstimator.class)
            .littleEndian()
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
    public HscField prepareField(String query){
        return HscField.of(query);
    }

}
