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
package org.apache.plc4x.java.modbus.readwrite.io;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.modbus.readwrite.*;
import org.apache.plc4x.java.modbus.readwrite.io.*;
import org.apache.plc4x.java.modbus.readwrite.types.*;
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

public class ModbusPDUIO implements MessageIO<ModbusPDU, ModbusPDU> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModbusPDUIO.class);

    @Override
    public ModbusPDU parse(ReadBuffer readBuffer, Object... args) throws ParseException {
        if((args == null) || (args.length != 1)) {
            throw new PlcRuntimeException("Wrong number of arguments, expected 1, but got " + args.length);
        }
        Boolean response;
        if(args[0] instanceof Boolean) {
            response = (Boolean) args[0];
        } else if (args[0] instanceof String) {
            response = Boolean.valueOf((String) args[0]);
        } else {
            throw new PlcRuntimeException("Argument 0 expected to be of type Boolean or a string which is parseable but was " + args[0].getClass().getName());
        }
        return ModbusPDUIO.staticParse(readBuffer, response);
    }

    @Override
    public void serialize(WriteBuffer writeBuffer, ModbusPDU value, Object... args) throws ParseException {
        ModbusPDUIO.staticSerialize(writeBuffer, value);
    }

    public static ModbusPDU staticParse(ReadBuffer readBuffer, Boolean response) throws ParseException {
        readBuffer.pullContext("ModbusPDU");
        int startPos = readBuffer.getPos();
        int curPos;

        // Discriminator Field (errorFlag) (Used as input to a switch field)
        boolean errorFlag = readBuffer.readBit("errorFlag");


        // Discriminator Field (functionFlag) (Used as input to a switch field)
        short functionFlag = readBuffer.readUnsignedShort("functionFlag", 7);


        // Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
        ModbusPDUBuilder builder = null;
                if(EvaluationHelper.equals(errorFlag, true)) {
            builder = ModbusPDUErrorIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x02) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadDiscreteInputsRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x02) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadDiscreteInputsResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x01) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadCoilsRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x01) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadCoilsResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x05) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUWriteSingleCoilRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x05) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUWriteSingleCoilResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x0F) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUWriteMultipleCoilsRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x0F) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUWriteMultipleCoilsResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x04) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadInputRegistersRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x04) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadInputRegistersResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x03) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadHoldingRegistersRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x03) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadHoldingRegistersResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x06) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUWriteSingleRegisterRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x06) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUWriteSingleRegisterResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x10) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUWriteMultipleHoldingRegistersRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x10) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUWriteMultipleHoldingRegistersResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x17) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadWriteMultipleHoldingRegistersRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x17) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadWriteMultipleHoldingRegistersResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x16) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUMaskWriteHoldingRegisterRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x16) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUMaskWriteHoldingRegisterResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x18) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadFifoQueueRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x18) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadFifoQueueResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x14) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadFileRecordRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x14) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadFileRecordResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x15) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUWriteFileRecordRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x15) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUWriteFileRecordResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x07) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadExceptionStatusRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x07) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadExceptionStatusResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x08) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUDiagnosticRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x08) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUDiagnosticResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x0B) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUGetComEventCounterRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x0B) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUGetComEventCounterResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x0C) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUGetComEventLogRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x0C) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUGetComEventLogResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x11) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReportServerIdRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x11) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReportServerIdResponseIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x2B) && EvaluationHelper.equals(response, false)) {
            builder = ModbusPDUReadDeviceIdentificationRequestIO.staticParse(readBuffer);
        } else 
                if(EvaluationHelper.equals(errorFlag, false) && EvaluationHelper.equals(functionFlag, 0x2B) && EvaluationHelper.equals(response, true)) {
            builder = ModbusPDUReadDeviceIdentificationResponseIO.staticParse(readBuffer);
        }
        if (builder == null) {
            throw new ParseException("Unsupported case for discriminated type");
        }

        readBuffer.closeContext("ModbusPDU");
        // Create the instance
        return builder.build();
    }

    public static void staticSerialize(WriteBuffer writeBuffer, ModbusPDU _value) throws ParseException {
        int startPos = writeBuffer.getPos();
        writeBuffer.pushContext("ModbusPDU");

        // Discriminator Field (errorFlag) (Used as input to a switch field)
        boolean errorFlag = (boolean) _value.getErrorFlag();
            writeBuffer.writeBit("errorFlag", (boolean) (errorFlag));

        // Discriminator Field (functionFlag) (Used as input to a switch field)
        short functionFlag = (short) _value.getFunctionFlag();
            writeBuffer.writeUnsignedShort("functionFlag", 7, ((Number) (functionFlag)).shortValue());

        // Switch field (Depending on the discriminator values, passes the instantiation to a sub-type)
        if(_value instanceof ModbusPDUError) {
            ModbusPDUErrorIO.staticSerialize(writeBuffer, (ModbusPDUError) _value);
        } else 
        if(_value instanceof ModbusPDUReadDiscreteInputsRequest) {
            ModbusPDUReadDiscreteInputsRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadDiscreteInputsRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadDiscreteInputsResponse) {
            ModbusPDUReadDiscreteInputsResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadDiscreteInputsResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadCoilsRequest) {
            ModbusPDUReadCoilsRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadCoilsRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadCoilsResponse) {
            ModbusPDUReadCoilsResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadCoilsResponse) _value);
        } else 
        if(_value instanceof ModbusPDUWriteSingleCoilRequest) {
            ModbusPDUWriteSingleCoilRequestIO.staticSerialize(writeBuffer, (ModbusPDUWriteSingleCoilRequest) _value);
        } else 
        if(_value instanceof ModbusPDUWriteSingleCoilResponse) {
            ModbusPDUWriteSingleCoilResponseIO.staticSerialize(writeBuffer, (ModbusPDUWriteSingleCoilResponse) _value);
        } else 
        if(_value instanceof ModbusPDUWriteMultipleCoilsRequest) {
            ModbusPDUWriteMultipleCoilsRequestIO.staticSerialize(writeBuffer, (ModbusPDUWriteMultipleCoilsRequest) _value);
        } else 
        if(_value instanceof ModbusPDUWriteMultipleCoilsResponse) {
            ModbusPDUWriteMultipleCoilsResponseIO.staticSerialize(writeBuffer, (ModbusPDUWriteMultipleCoilsResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadInputRegistersRequest) {
            ModbusPDUReadInputRegistersRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadInputRegistersRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadInputRegistersResponse) {
            ModbusPDUReadInputRegistersResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadInputRegistersResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadHoldingRegistersRequest) {
            ModbusPDUReadHoldingRegistersRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadHoldingRegistersRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadHoldingRegistersResponse) {
            ModbusPDUReadHoldingRegistersResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadHoldingRegistersResponse) _value);
        } else 
        if(_value instanceof ModbusPDUWriteSingleRegisterRequest) {
            ModbusPDUWriteSingleRegisterRequestIO.staticSerialize(writeBuffer, (ModbusPDUWriteSingleRegisterRequest) _value);
        } else 
        if(_value instanceof ModbusPDUWriteSingleRegisterResponse) {
            ModbusPDUWriteSingleRegisterResponseIO.staticSerialize(writeBuffer, (ModbusPDUWriteSingleRegisterResponse) _value);
        } else 
        if(_value instanceof ModbusPDUWriteMultipleHoldingRegistersRequest) {
            ModbusPDUWriteMultipleHoldingRegistersRequestIO.staticSerialize(writeBuffer, (ModbusPDUWriteMultipleHoldingRegistersRequest) _value);
        } else 
        if(_value instanceof ModbusPDUWriteMultipleHoldingRegistersResponse) {
            ModbusPDUWriteMultipleHoldingRegistersResponseIO.staticSerialize(writeBuffer, (ModbusPDUWriteMultipleHoldingRegistersResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadWriteMultipleHoldingRegistersRequest) {
            ModbusPDUReadWriteMultipleHoldingRegistersRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadWriteMultipleHoldingRegistersRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadWriteMultipleHoldingRegistersResponse) {
            ModbusPDUReadWriteMultipleHoldingRegistersResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadWriteMultipleHoldingRegistersResponse) _value);
        } else 
        if(_value instanceof ModbusPDUMaskWriteHoldingRegisterRequest) {
            ModbusPDUMaskWriteHoldingRegisterRequestIO.staticSerialize(writeBuffer, (ModbusPDUMaskWriteHoldingRegisterRequest) _value);
        } else 
        if(_value instanceof ModbusPDUMaskWriteHoldingRegisterResponse) {
            ModbusPDUMaskWriteHoldingRegisterResponseIO.staticSerialize(writeBuffer, (ModbusPDUMaskWriteHoldingRegisterResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadFifoQueueRequest) {
            ModbusPDUReadFifoQueueRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadFifoQueueRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadFifoQueueResponse) {
            ModbusPDUReadFifoQueueResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadFifoQueueResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadFileRecordRequest) {
            ModbusPDUReadFileRecordRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadFileRecordRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadFileRecordResponse) {
            ModbusPDUReadFileRecordResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadFileRecordResponse) _value);
        } else 
        if(_value instanceof ModbusPDUWriteFileRecordRequest) {
            ModbusPDUWriteFileRecordRequestIO.staticSerialize(writeBuffer, (ModbusPDUWriteFileRecordRequest) _value);
        } else 
        if(_value instanceof ModbusPDUWriteFileRecordResponse) {
            ModbusPDUWriteFileRecordResponseIO.staticSerialize(writeBuffer, (ModbusPDUWriteFileRecordResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadExceptionStatusRequest) {
            ModbusPDUReadExceptionStatusRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadExceptionStatusRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadExceptionStatusResponse) {
            ModbusPDUReadExceptionStatusResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadExceptionStatusResponse) _value);
        } else 
        if(_value instanceof ModbusPDUDiagnosticRequest) {
            ModbusPDUDiagnosticRequestIO.staticSerialize(writeBuffer, (ModbusPDUDiagnosticRequest) _value);
        } else 
        if(_value instanceof ModbusPDUDiagnosticResponse) {
            ModbusPDUDiagnosticResponseIO.staticSerialize(writeBuffer, (ModbusPDUDiagnosticResponse) _value);
        } else 
        if(_value instanceof ModbusPDUGetComEventCounterRequest) {
            ModbusPDUGetComEventCounterRequestIO.staticSerialize(writeBuffer, (ModbusPDUGetComEventCounterRequest) _value);
        } else 
        if(_value instanceof ModbusPDUGetComEventCounterResponse) {
            ModbusPDUGetComEventCounterResponseIO.staticSerialize(writeBuffer, (ModbusPDUGetComEventCounterResponse) _value);
        } else 
        if(_value instanceof ModbusPDUGetComEventLogRequest) {
            ModbusPDUGetComEventLogRequestIO.staticSerialize(writeBuffer, (ModbusPDUGetComEventLogRequest) _value);
        } else 
        if(_value instanceof ModbusPDUGetComEventLogResponse) {
            ModbusPDUGetComEventLogResponseIO.staticSerialize(writeBuffer, (ModbusPDUGetComEventLogResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReportServerIdRequest) {
            ModbusPDUReportServerIdRequestIO.staticSerialize(writeBuffer, (ModbusPDUReportServerIdRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReportServerIdResponse) {
            ModbusPDUReportServerIdResponseIO.staticSerialize(writeBuffer, (ModbusPDUReportServerIdResponse) _value);
        } else 
        if(_value instanceof ModbusPDUReadDeviceIdentificationRequest) {
            ModbusPDUReadDeviceIdentificationRequestIO.staticSerialize(writeBuffer, (ModbusPDUReadDeviceIdentificationRequest) _value);
        } else 
        if(_value instanceof ModbusPDUReadDeviceIdentificationResponse) {
            ModbusPDUReadDeviceIdentificationResponseIO.staticSerialize(writeBuffer, (ModbusPDUReadDeviceIdentificationResponse) _value);
        }
        writeBuffer.popContext("ModbusPDU");
    }

    public static interface ModbusPDUBuilder {
        ModbusPDU build();
    }

}
