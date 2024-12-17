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
package org.apache.plc4x.java.hsc.readwrite.protocol;

import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.messages.*;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.hsc.readwrite.configuration.HscConfiguration;
import org.apache.plc4x.java.hsc.readwrite.constant.ServerConstants;
import org.apache.plc4x.java.hsc.readwrite.field.HscField;
import org.apache.plc4x.java.hsc.readwrite.model.HscPacket;
import org.apache.plc4x.java.spi.ConversationContext;
import org.apache.plc4x.java.spi.Plc4xProtocolBase;
import org.apache.plc4x.java.spi.configuration.HasConfiguration;
import org.apache.plc4x.java.spi.messages.*;
import org.apache.plc4x.java.spi.messages.utils.ResponseItem;
import org.apache.plc4x.java.spi.transaction.RequestTransactionManager;
import org.apache.plc4x.java.spi.values.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class HscProtocolLogic extends Plc4xProtocolBase<HscPacket> implements HasConfiguration<HscConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(HscProtocolLogic.class);
    public static final Duration REQUEST_TIMEOUT = Duration.ofMillis(10000);

    private HscConfiguration configuration;

    private final AtomicInteger transactionCounterGenerator = new AtomicInteger(10);
    private RequestTransactionManager tm;
    private long sessionHandle;

    @Override
    public void setConfiguration(HscConfiguration configuration) {
        this.configuration = configuration;
        // Set the transaction manager to allow only one message at a time.
        this.tm = new RequestTransactionManager(1);
    }

    @Override
    public void onConnect(ConversationContext<HscPacket> context) {
        logger.info("hsc client onConnect!");
        context.fireConnected();
    }

    @Override
    public CompletableFuture<PlcReadResponse> read(PlcReadRequest readRequest) {
        CompletableFuture<PlcReadResponse> future = new CompletableFuture<>();
        HscField hscField = (HscField) readRequest.getFields().get(0);
        String workFlow = hscField.getWorkFlow();
        String reqContent = hscField.getReqContent();
        RequestTransactionManager.RequestTransaction transaction = tm.startRequest();
        try {
            switch (workFlow) {
                case ServerConstants.WORKFLOW_INTERLOK: //Interlock process step
                    HscPacket hscPacket = new HscPacket(reqContent);
                    transaction.submit(() -> context.sendRequest(hscPacket)
                        .expectResponse(HscPacket.class, REQUEST_TIMEOUT)
                        .onTimeout((e) -> {
                            future.completeExceptionally(e);
                            transaction.endRequest();
                        })
                        .onError((p, e) ->
                        {
                            future.completeExceptionally(e);
                            transaction.endRequest();
                        })
                        .unwrap(p -> (HscPacket) p)
                        .check(p -> p instanceof HscPacket)
                        .handle(p -> {
                            Map<String, ResponseItem<PlcValue>> values = new HashMap<>();
                            if (p.getContent() == null || StringUtils.isEmpty(p.getContent())) {
                                ResponseItem<PlcValue> result = new ResponseItem<>(PlcResponseCode.INTERNAL_ERROR, new PlcLSTRING(p.getContent()));
                                values.put(workFlow, result);
                            } else {
                                ResponseItem<PlcValue> result = new ResponseItem<>(PlcResponseCode.OK, new PlcLSTRING(p.getContent()));
                                values.put(workFlow, result);
                            }
                            DefaultPlcReadResponse defaultPlcReadResponse = new DefaultPlcReadResponse(readRequest, values);
                            future.complete(defaultPlcReadResponse);
                            transaction.endRequest();
                        }));
                    break;
                default:
                    future.completeExceptionally(new Exception("Unsupported workflow: " + workFlow));
            }
        } catch (Exception e) {
            future.completeExceptionally(e);
            transaction.endRequest();
        }
        return future;
    }

    @Override
    public CompletableFuture<PlcWriteResponse> write(PlcWriteRequest writeRequest) {
        return null;
    }


    @Override
    public void onDisconnect(ConversationContext<HscPacket> context) {
        super.onDisconnect(context);
        logger.info("hsc client onDisconnect!");
    }

    @Override
    public void close(ConversationContext<HscPacket> context) {
    }
}
