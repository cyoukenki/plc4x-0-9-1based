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

import java.util.ArrayList;
import java.util.List;

import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;

public class DataHolder4Plc4x extends DataHolder {

    final List<ModbusEventListener> modbusEventListenerList = new ArrayList<ModbusEventListener>();

    public DataHolder4Plc4x() {
        // you can place the initialization code here
        /*
         * something like that:
         * setHoldingRegisters(new SimpleHoldingRegisters(10));
         * setCoils(new Coils(128));
         * ...
         * etc.
         */
        setHoldingRegisters(new ModbusHoldingRegisters(65535));
    }

    public void addEventListener(ModbusEventListener listener) {
        modbusEventListenerList.add(listener);
    }

    public boolean removeEventListener(ModbusEventListener listener) {
        return modbusEventListenerList.remove(listener);
    }

    @Override
    public void writeHoldingRegister(int offset, int value)
            throws IllegalDataAddressException, IllegalDataValueException {
        for (ModbusEventListener l : modbusEventListenerList) {
            l.onWriteToSingleHoldingRegister(offset, value);
        }
        super.writeHoldingRegister(offset, value);
    }

    @Override
    public void writeHoldingRegisterRange(int offset, int[] range)
            throws IllegalDataAddressException, IllegalDataValueException {
        for (ModbusEventListener l : modbusEventListenerList) {
            l.onWriteToMultipleHoldingRegisters(offset, range.length, range);
        }
        super.writeHoldingRegisterRange(offset, range);
    }

    @Override
    public void writeCoil(int offset, boolean value) throws IllegalDataAddressException, IllegalDataValueException {
        for (ModbusEventListener l : modbusEventListenerList) {
            l.onWriteToSingleCoil(offset, value);
        }
        super.writeCoil(offset, value);
    }

    @Override
    public void writeCoilRange(int offset, boolean[] range)
            throws IllegalDataAddressException, IllegalDataValueException {
        for (ModbusEventListener l : modbusEventListenerList) {
            l.onWriteToMultipleCoils(offset, range.length, range);
        }
        super.writeCoilRange(offset, range);
    }
    @Override
    public int readHoldingRegister(int offset) throws IllegalDataAddressException{
        for (ModbusEventListener l : modbusEventListenerList) {
            l.onReadHoldingRegister(offset);
        }
        return super.readHoldingRegister(offset);
       
    }
    @Override
    public int[] readHoldingRegisterRange(int offset, int quantity) throws IllegalDataAddressException {
        for (ModbusEventListener l : modbusEventListenerList) {
            int[] result = l.readHoldingRegisterRange(offset,quantity);
            if(result != null){
                return result;
            }          
        }
        throw new IllegalDataAddressException(offset);
    }

}
