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
package org.apache.plc4x.camel.modbus;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

public class Plc4xListener implements ModbusEventListener {
    private final Logger logger = LoggerFactory.getLogger(Plc4xListener.class);
    private final DefaultConsumer consumer;

    public Plc4xListener(DefaultConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void onWriteToSingleCoil(int address, boolean value) {
        logger.info("onWriteToSingleCoil: address " + address + ", value " + value);
    }

    @Override
    public void onWriteToMultipleCoils(int address, int quantity, boolean[] values) {
        logger.info("onWriteToMultipleCoils: address " + address + ", quantity " + quantity);
    }

    @Override
    public void onWriteToSingleHoldingRegister(int address, int value) {
        logger.info("onWriteToSingleHoldingRegister: address " + address + ", value " + value);
    }

    @Override
    public void onWriteToMultipleHoldingRegisters(int address, int quantity, int[] values) {
        logger.info("onWriteToMultipleHoldingRegisters: address " + address + ", quantity " + quantity);
    }

    @Override
    public int onReadHoldingRegister(int offset) {
        logger.info("onReadHoldingRegister: address " + offset);
    
        try {

            Exchange exchange = consumer.getEndpoint().createExchange();
            exchange.getIn().setBody(offset);
            consumer.getProcessor().process(exchange);
            logger.info("received message :" + exchange.getIn().getBody().toString());
        } catch (Exception e) {

        }
        return -1;

    }

    @Override
    public int[] readHoldingRegisterRange(int offset, int quantity) {
        logger.info("readHoldingRegisterRange: address " + offset + ", quantity " + quantity);

        try {

            Exchange exchange = consumer.getEndpoint().createExchange();
            Map<String,Integer> body =  new HashMap<>();
            body.put("startAddress", offset);
            body.put("quantity", quantity);
            exchange.getIn().setBody(body);
            consumer.getProcessor().process(exchange);
            logger.info("received message :" + exchange.getIn().getBody().toString());
        } catch (Exception e) {

        }
        return new int[]{0};
    }

}
