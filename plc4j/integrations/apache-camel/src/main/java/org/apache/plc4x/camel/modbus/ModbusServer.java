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

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.apache.camel.Consume;
import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultConsumer;
import org.apache.plc4x.camel.Plc4XProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import com.intelligt.modbus.jlibmodbus.data.DataHolder;

public class ModbusServer {
    private final Logger logger = LoggerFactory.getLogger(ModbusServer.class);
    private ModbusSlave slave;
    private DefaultConsumer consumer;

    public ModbusServer(DefaultConsumer consumer) {
        this.consumer = consumer;
        logger.info("...init modbus server");
        Modbus.log().addHandler(new Handler() {
            @Override
            public void publish(LogRecord record) {
                logger.info(record.getLevel().getName() + ": " + record.getMessage());
            }

            @Override
            public void flush() {
                // do nothing
            }

            @Override
            public void close() throws SecurityException {
                // do nothing
            }
        });
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);

        try {

            TcpParameters tcpParameters = new TcpParameters();

            tcpParameters.setHost(InetAddress.getLocalHost());
            tcpParameters.setKeepAlive(true);
            tcpParameters.setPort(Modbus.TCP_PORT);
            logger.info("...bind modbus server on port " + Modbus.TCP_PORT);
            slave = ModbusSlaveFactory.createModbusSlaveTCP(tcpParameters);
            slave.setReadTimeout(0); // if not set default timeout is 1000ms, I think this must be set to 0
                                     // (infinitive timeout)
            Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);

            DataHolder4Plc4x dh = new DataHolder4Plc4x();
            dh.addEventListener(new Plc4xListener(consumer));
            slave.setDataHolder(dh);
          
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            slave.setServerAddress(1);
            /*
             * using master-branch it should be #slave.open();
             */
            slave.listen();
            logger.info("...modbus started server on port " + Modbus.TCP_PORT);
        } catch (Exception e) {
            logger.error("startup modbus server failure!", e);
        }
    }
    public void stop(){
        try {
            slave.shutdown();
          
            logger.info("...modbus server shutdown ! ");
        } catch (Exception e) {
            logger.error("shutdown modbus server failure!", e);
        }
    }

    
}
