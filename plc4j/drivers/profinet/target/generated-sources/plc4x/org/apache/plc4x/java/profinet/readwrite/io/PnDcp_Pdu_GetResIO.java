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
package org.apache.plc4x.java.profinet.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.profinet.readwrite.*;
import org.apache.plc4x.java.profinet.readwrite.io.*;
import org.apache.plc4x.java.profinet.readwrite.types.*;
import org.apache.plc4x.java.api.exceptions.PlcRuntimeException;
import org.apache.plc4x.java.spi.generation.*;
import org.apache.plc4x.java.api.value.PlcValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.time.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

// Code generated by code-generation. DO NOT EDIT.

public class PnDcp_Pdu_GetResIO implements MessageIO<PnDcp_Pdu_GetRes, PnDcp_Pdu_GetRes> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PnDcp_Pdu_GetResIO.class);

    @Override
    public PnDcp_Pdu_GetRes parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (PnDcp_Pdu_GetRes) new PnDcp_PduIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, PnDcp_Pdu_GetRes value, Object... args) throws ParseException {
        new PnDcp_PduIO().serialize(writeBuffer, value, args);
    }

    public static PnDcp_Pdu_GetResBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("PnDcp_Pdu_GetRes");
        int startPos = readBuffer.getPos();
        int curPos;

        readBuffer.closeContext("PnDcp_Pdu_GetRes");
        // Create the instance
        return new PnDcp_Pdu_GetResBuilder();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, PnDcp_Pdu_GetRes _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("PnDcp_Pdu_GetRes");
        writeBuffer.popContext("PnDcp_Pdu_GetRes");
    }

    public static class PnDcp_Pdu_GetResBuilder implements PnDcp_PduIO.PnDcp_PduBuilder {

        public PnDcp_Pdu_GetResBuilder() {
        }

        public PnDcp_Pdu_GetRes build(PnDcp_ServiceType serviceType, long xid, int responseDelayFactorOrPadding) {
            return new PnDcp_Pdu_GetRes(serviceType, xid, responseDelayFactorOrPadding);
        }
    }

}