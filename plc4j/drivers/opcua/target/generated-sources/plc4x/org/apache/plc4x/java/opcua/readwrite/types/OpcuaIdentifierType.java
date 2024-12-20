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
package org.apache.plc4x.java.opcua.readwrite.types;

import org.apache.plc4x.java.spi.generation.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Code generated by code-generation. DO NOT EDIT.

public enum OpcuaIdentifierType {

    STRING_IDENTIFIER((String) "s"),
    NUMBER_IDENTIFIER((String) "i"),
    GUID_IDENTIFIER((String) "g"),
    BINARY_IDENTIFIER((String) "b");

    private static final Logger logger = LoggerFactory.getLogger(OpcuaIdentifierType.class);

    private static final Map<String, OpcuaIdentifierType> map;
    static {
        map = new HashMap<>();
        for (OpcuaIdentifierType value : OpcuaIdentifierType.values()) {
            map.put((String) value.getValue(), value);
        }
    }

    private String value;

    OpcuaIdentifierType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OpcuaIdentifierType enumForValue(String value) {
        if (!map.containsKey(value)) {
            logger.error("No OpcuaIdentifierType for value {}", value);
        }
        return map.get(value);
    }

    public static Boolean isDefined(String value) {
        return map.containsKey(value);
    }

}
