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
package org.apache.plc4x.java.spi.connection;

import com.omron.gc.transport.tcp.api.ServerConnector;
import com.omron.gc.transport.tcp.config.ListenerConfiguration;
import com.omron.gc.transport.tcp.config.ServerBootstrapConfiguration;
import com.omron.gc.transport.tcp.impl.DefaultTcpConnectorFactory;
import com.omron.gc.transport.tcp.impl.DefaultTcpConnectorFuture;
import io.netty.channel.*;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timer;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.listener.ServerConnectorListener;
import org.apache.plc4x.java.api.value.PlcValueHandler;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.messages.DefaultServerConnectorListener;
import org.apache.plc4x.java.spi.optimizer.BaseOptimizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultNettyPlcTcpServerConnector extends AbstractPlcServerConnector  implements ChannelExposingConnection {

    /**
     * a {@link HashedWheelTimer} shall be only instantiated once.
     */
    // TODO: maybe find a way to make this configurable per jvm
    protected final static Timer timer = new HashedWheelTimer();
    private static final Logger logger = LoggerFactory.getLogger(DefaultNettyPlcTcpServerConnector.class);

    protected final Configuration configuration;
    protected final ProtocolStackConfigurer stackConfigurer;

    protected Channel channel;
    protected boolean started;
    protected boolean conencted;
    protected String transportConfig;
    protected ServerConnector serverConnector;

    private DefaultServerConnectorListener connectorListener;

    public DefaultNettyPlcTcpServerConnector(boolean canRead, boolean canWrite, boolean canSubscribe, String transportConfig,
                                             PlcFieldHandler fieldHandler, PlcValueHandler valueHandler, Configuration configuration,
                                             ProtocolStackConfigurer stackConfigurer, BaseOptimizer optimizer) {
        super(canRead, canWrite, canSubscribe, fieldHandler, valueHandler,optimizer);
        this.transportConfig = transportConfig;
        this.configuration = configuration;
        this.stackConfigurer = stackConfigurer;
        this.started = false;
        this.conencted = false;
    }

    @Override
    public void start() throws PlcConnectionException {
        logger.debug("tcp server is Starting...");
        ChannelHandler channelHandler = getChannelHandler();
        DefaultTcpConnectorFactory dcf = new DefaultTcpConnectorFactory();
        ListenerConfiguration listenerConfiguration = new ListenerConfiguration();
        String[] transportConfigs = this.transportConfig.split(":");
        listenerConfiguration.setHost(transportConfigs[0]);
        listenerConfiguration.setPort(Integer.parseInt(transportConfigs[1]));
        ServerBootstrapConfiguration sc = new ServerBootstrapConfiguration(null,channelHandler);
        serverConnector = dcf.createServerConnector(sc, listenerConfiguration);
        DefaultTcpConnectorFuture tcpConnectorFuture = (DefaultTcpConnectorFuture) serverConnector.start();
        ChannelFuture nettyBindFuture = tcpConnectorFuture.getNettyBindFuture();
        channel = nettyBindFuture.channel();
        started = true;
        logger.debug("tcp server is Started...");
    }

    @Override
    public boolean isStart() {
        return started && channel.isActive();
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public void setServerConnectorListener(ServerConnectorListener connectorListener) {
        this.connectorListener = (DefaultServerConnectorListener) connectorListener;
    }


    @Override
    public String getConnectorID() {
        return null;
    }

    @Override
    public void close() throws Exception {
        logger.debug("trying to close channel...");
        channel.close();
        logger.debug("channel is closed...");
        started = false;
    }

    public ChannelHandler getChannelHandler() {
        if (stackConfigurer == null) {
            throw new IllegalStateException("No Protocol Stack Configurer is given!");
        }
        /*if (factory == null) {
            throw new IllegalStateException("No Instance Factory is Present!");
        }*/
        return new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) {
                // Build the protocol stack for communicating with the s7 protocol.
                ChannelPipeline pipeline = channel.pipeline();
                // Initialize via Transport Layer
//                channelFactory.initializePipeline(pipeline);
                // Initialize Protocol Layer
                setProtocol(stackConfigurer.configureServerPipeline(configuration, pipeline, true,connectorListener));
            }
        };
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

}
