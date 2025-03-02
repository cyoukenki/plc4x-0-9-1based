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
package org.apache.plc4x.java.profinet.readwrite.types;

import org.apache.plc4x.java.spi.generation.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Code generated by code-generation. DO NOT EDIT.

public enum DceRpc_PacketType {

    REQUEST((short) 0x00),
    PING((short) 0x01),
    RESPONSE((short) 0x02),
    FAULT((short) 0x03),
    WORKING((short) 0x04),
    NO_CALL((short) 0x05),
    REJECT((short) 0x06),
    ACKNOWLEDGE((short) 0x07),
    CONNECTIONLESS_CANCEL((short) 0x08),
    FRAGMENT_ACKNOWLEDGE((short) 0x09),
    CANCEL_ACKNOWLEDGE((short) 0x0A);

    private static final Logger logger = LoggerFactory.getLogger(DceRpc_PacketType.class);

    private static final Map<Short, DceRpc_PacketType> map;
    static {
        map = new HashMap<>();
        for (DceRpc_PacketType value : DceRpc_PacketType.values()) {
            map.put((short) value.getValue(), value);
        }
    }

    private short value;

    DceRpc_PacketType(short value) {
        this.value = value;
    }

    public short getValue() {
        return value;
    }

    public static DceRpc_PacketType enumForValue(short value) {
        if (!map.containsKey(value)) {
            logger.error("No DceRpc_PacketType for value {}", value);
        }
        return map.get(value);
    }

    public static Boolean isDefined(short value) {
        return map.containsKey(value);
    }

}
