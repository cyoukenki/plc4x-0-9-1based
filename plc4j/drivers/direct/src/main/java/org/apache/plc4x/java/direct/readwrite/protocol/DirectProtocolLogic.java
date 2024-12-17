package org.apache.plc4x.java.direct.readwrite.protocol;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.exceptions.PlcRuntimeException;
import org.apache.plc4x.java.api.listener.MessageExchangeListener;
import org.apache.plc4x.java.api.messages.*;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.api.value.PlcValue;
import org.apache.plc4x.java.direct.readwrite.DirectDriver;
import org.apache.plc4x.java.direct.readwrite.configuration.DirectConfiguration;
import org.apache.plc4x.java.direct.readwrite.field.DirectField;
import org.apache.plc4x.java.direct.readwrite.model.DirectPacket;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.*;
import org.apache.plc4x.java.spi.messages.utils.ResponseItem;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.spi.values.PlcSTRING;
import org.apache.plc4x.java.transport.serial.SerialChannel;
import org.apache.plc4x.java.transport.serial.SerialSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;


public class DirectProtocolLogic extends Plc4xProtocolBase<DirectPacket> implements HasConfiguration<DirectConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DirectProtocolLogic.class);
    private DirectConfiguration configuration;
    private RequestTransactionManager tm;
    private ConversationContext<DirectPacket> context;
    public MessageExchangeListener messageExchangeListener;
    public static final Duration REQUEST_TIMEOUT = Duration.ofMillis(5000);

    @Override
    public void close(ConversationContext<DirectPacket> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("SerialDirectProtocolLogic---comPort:{}---close", comPort);
    }

    @Override
    public void setConfiguration(DirectConfiguration configuration) {
        this.configuration = configuration;
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<DirectPacket> context) {
        this.messageExchangeListener = DirectDriver.messageExchangeListener;
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("SerialDirectProtocolLogic---comPort:{}---onConnect", comPort);
    }

    @Override
    public void onDisconnect(ConversationContext<DirectPacket> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info("SerialDirectProtocolLogic---comPort:{}---onDisconnect", comPort);
    }

    @Override
    public void onDiscover(ConversationContext<DirectPacket> context) {
        logger.debug("SerialDirectProtocolLogic-------onDiscover");
    }

    @Override
    public void onHostConnect(ConversationContext<DirectPacket> context) {
        this.messageExchangeListener = DirectDriver.messageExchangeListener;
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info(comPort + "---onHostConnect");
    }

    @Override
    public void onHostDisconnect(ConversationContext<DirectPacket> context) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        logger.info(comPort + "---onHostDisconnect");
    }


    @Override
    protected void decode(ConversationContext<DirectPacket> context, DirectPacket msg) {
        String comPort = ((SerialSocketAddress) ((SerialChannel) context.getChannel()).getComPort().getAddress()).getIdentifier();
        if(this.messageExchangeListener != null) {
            this.messageExchangeListener.received(msg.getContent());
        }
        logger.info("SerialDirectProtocolLogic:receive comPort {} message is {}",comPort,msg.getContent());
    }

    @Override
    public CompletableFuture<PlcReadResponse> read(PlcReadRequest readRequest) {
        CompletableFuture<PlcReadResponse> future = new CompletableFuture<>();
        DefaultPlcReadRequest request = (DefaultPlcReadRequest) readRequest;
        if (request.getFieldNames().size() == 1) {
            String fieldName = request.getFieldNames().iterator().next();
            DirectField directField = (DirectField) request.getField(fieldName);
            String content = directField.getContent()+"\r";
            RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
            DirectPacket directPacket = new DirectPacket(content);
            transaction.submit(() -> context.sendRequest(directPacket)
                .expectResponse(DirectPacket.class, REQUEST_TIMEOUT)
                .onTimeout(future::completeExceptionally)
                .onError((p, e) -> future.completeExceptionally(e))
                .handle(p -> {
                    PlcResponseCode code = null;
                    PlcValue value = null;
                    if (p instanceof DirectPacket) {
                        DirectPacket resp = (DirectPacket) p;
                        if (StringUtils.isNoneEmpty(resp.getContent())) {
                            value = new PlcSTRING(resp.getContent());
                            code = PlcResponseCode.OK;
                        } else {
                            code = PlcResponseCode.INTERNAL_ERROR;
                        }
                    }
                    PlcReadResponse response = new DefaultPlcReadResponse(request,
                        Collections.singletonMap(fieldName, new ResponseItem<>(code, value)));
                    future.complete(response);
                    // Finish the request-transaction.
                    transaction.endRequest();
                }));
        } else {
            future.completeExceptionally(new PlcRuntimeException("Direct only supports single filed requests"));
        }
        return future;
    }

    @Override
    public CompletableFuture<PlcWriteResponse> write(PlcWriteRequest writeRequest) {
        CompletableFuture<PlcWriteResponse> future = new CompletableFuture<>();
        DefaultPlcWriteRequest request = (DefaultPlcWriteRequest) writeRequest;
        List<DirectPacket> items = new ArrayList<>(writeRequest.getNumberOfFields());
        for (String fieldName : request.getFieldNames()) {
            final DirectField finsField = (DirectField) request.getField(fieldName);
            String content = finsField.getContent();
            DirectPacket directPacket = new DirectPacket(content);
            items.add(directPacket);
        }
        if (items.size() == 1) {
            RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
            transaction.submit(() -> context.sendRequest(items.get(0))
                .expectResponse(DirectPacket.class, REQUEST_TIMEOUT)
                .onTimeout((e) -> {
                        future.completeExceptionally(e);
                        transaction.endRequest();
                    }
                )
                .onError((p, e) -> {
                    future.completeExceptionally(e);
                    transaction.endRequest();
                })
                .check(p -> p instanceof DirectPacket).unwrap(p -> (DirectPacket) p)
                .handle(p -> {
                    String fieldName = writeRequest.getFieldNames().iterator().next();
                    Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
                    // only 1 field
                    Map<String, PlcResponseCode> responses = new HashMap<>();
                    DirectPacket resp = (DirectPacket) p;
                    String content = resp.getContent();
                    if (!StringUtils.isEmpty(content)) {
                        responses.put(fieldName, PlcResponseCode.OK);
                    } else {
                        responses.put(fieldName, PlcResponseCode.INTERNAL_ERROR);
                    }
                    future.complete(new DefaultPlcWriteResponse(writeRequest, responses));
                    transaction.endRequest();
                }));
        } else {
            future.completeExceptionally(new PlcRuntimeException("Direct only supports single filed requests"));
        }
        return future;
    }

    @Override
    public void setContext(ConversationContext<DirectPacket> context) {
        this.context = context;
    }
}
