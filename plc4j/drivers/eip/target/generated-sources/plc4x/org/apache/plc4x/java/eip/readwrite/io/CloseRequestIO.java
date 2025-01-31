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
package org.apache.plc4x.java.eip.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.eip.readwrite.*;
import org.apache.plc4x.java.eip.readwrite.io.*;
import org.apache.plc4x.java.eip.readwrite.types.*;
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

public class CloseRequestIO implements MessageIO<CloseRequest, CloseRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloseRequestIO.class);

    @Override
    public CloseRequest parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        return (CloseRequest) new CipServiceIO().parse(readBuffer, args);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, CloseRequest value, Object... args) throws ParseException {
        new CipServiceIO().serialize(writeBuffer, value, args);
    }

    public static CloseRequestBuilder staticParse(ReadBuffer readBuffer) throws ParseException {
        readBuffer.pullContext("CloseRequest");
        int startPos = readBuffer.getPos();
        int curPos;

        // Const Field (request_path_size)
        short request_path_size = readBuffer.readUnsignedShort("request_path_size", 8);
        if(request_path_size != CloseRequest.REQUEST_PATH_SIZE) {
            throw new ParseException("Expected constant value " + CloseRequest.REQUEST_PATH_SIZE + " but got " + request_path_size);
        }

        // Const Field (request_path)
        long request_path = readBuffer.readUnsignedLong("request_path", 32);
        if(request_path != CloseRequest.REQUEST_PATH) {
            throw new ParseException("Expected constant value " + CloseRequest.REQUEST_PATH + " but got " + request_path);
        }

        // Const Field (ptt)
        short ptt = readBuffer.readUnsignedShort("ptt", 8);
        if(ptt != CloseRequest.PTT) {
            throw new ParseException("Expected constant value " + CloseRequest.PTT + " but got " + ptt);
        }

        // Const Field (tot)
        short tot = readBuffer.readUnsignedShort("tot", 8);
        if(tot != CloseRequest.TOT) {
            throw new ParseException("Expected constant value " + CloseRequest.TOT + " but got " + tot);
        }


        // Simple Field (connection_serial_number)
int connection_serial_number = readBuffer.readUnsignedInt("connection_serial_number", 16) ;
        // Const Field (vendor_id)
        int vendor_id = readBuffer.readUnsignedInt("vendor_id", 16);
        if(vendor_id != CloseRequest.VENDOR_ID) {
            throw new ParseException("Expected constant value " + CloseRequest.VENDOR_ID + " but got " + vendor_id);
        }


        // Simple Field (originator_serial_number)
long originator_serial_number = readBuffer.readUnsignedLong("originator_serial_number", 32) ;
        // Const Field (connection_path_size)
        short connection_path_size = readBuffer.readUnsignedShort("connection_path_size", 8);
        if(connection_path_size != CloseRequest.CONNECTION_PATH_SIZE) {
            throw new ParseException("Expected constant value " + CloseRequest.CONNECTION_PATH_SIZE + " but got " + connection_path_size);
        }

        // Reserved Field (Compartmentalized so the "reserved" variable can't leak)
        {
            short reserved = readBuffer.readUnsignedShort("reserved", 8);
            if(reserved != (short) 0x00) {
                LOGGER.info("Expected constant value " + 0x00 + " but got " + reserved + " for reserved field.");
            }
        }

        // Const Field (connection_path)
        long connection_path = readBuffer.readUnsignedLong("connection_path", 32);
        if(connection_path != CloseRequest.CONNECTION_PATH) {
            throw new ParseException("Expected constant value " + CloseRequest.CONNECTION_PATH + " but got " + connection_path);
        }

        readBuffer.closeContext("CloseRequest");
        // Create the instance
        return new CloseRequestBuilder(connection_serial_number, originator_serial_number);
    }

    public static void staticSerialize(WriteBuffer writeBuffer, CloseRequest _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("CloseRequest");

        // Const Field (request_path_size)
        writeBuffer.writeUnsignedShort("request_path_size", 8, ((Number) 0x02).shortValue());

        // Const Field (request_path)
        writeBuffer.writeUnsignedLong("request_path", 32, ((Number) 0x01240620).longValue());

        // Const Field (ptt)
        writeBuffer.writeUnsignedShort("ptt", 8, ((Number) 0x06).shortValue());

        // Const Field (tot)
        writeBuffer.writeUnsignedShort("tot", 8, ((Number) 0x9c).shortValue());

        // Simple Field (connection_serial_number)
        int connection_serial_number = (int) _value.getConnection_serial_number();
        writeBuffer.writeUnsignedInt("connection_serial_number", 16, ((Number) (connection_serial_number)).intValue());

        // Const Field (vendor_id)
        writeBuffer.writeUnsignedInt("vendor_id", 16, ((Number) 0x002f).intValue());

        // Simple Field (originator_serial_number)
        long originator_serial_number = (long) _value.getOriginator_serial_number();
        writeBuffer.writeUnsignedLong("originator_serial_number", 32, ((Number) (originator_serial_number)).longValue());

        // Const Field (connection_path_size)
        writeBuffer.writeUnsignedShort("connection_path_size", 8, ((Number) 0x02).shortValue());

        // Reserved Field (reserved)
        writeBuffer.writeUnsignedShort("reserved", 8, ((Number) (short) 0x00).shortValue());

        // Const Field (connection_path)
        writeBuffer.writeUnsignedLong("connection_path", 32, ((Number) 0x01240620).longValue());
        writeBuffer.popContext("CloseRequest");
    }

    public static class CloseRequestBuilder implements CipServiceIO.CipServiceBuilder {
        private final int connection_serial_number;
        private final long originator_serial_number;

        public CloseRequestBuilder(int connection_serial_number, long originator_serial_number) {
            this.connection_serial_number = connection_serial_number;
            this.originator_serial_number = originator_serial_number;
        }

        public CloseRequest build() {
            return new CloseRequest(connection_serial_number, originator_serial_number);
        }
    }

}
