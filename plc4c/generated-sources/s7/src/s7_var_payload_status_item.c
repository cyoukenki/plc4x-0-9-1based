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

#include <stdio.h>
#include <plc4c/spi/evaluation_helper.h>
#include "s7_var_payload_status_item.h"

// Code generated by code-generation. DO NOT EDIT.


// Parse function.
plc4c_return_code plc4c_s7_read_write_s7_var_payload_status_item_parse(plc4c_spi_read_buffer* readBuffer, plc4c_s7_read_write_s7_var_payload_status_item** _message) {
  uint16_t startPos = plc4c_spi_read_get_pos(readBuffer);
  uint16_t curPos;
  plc4c_return_code _res = OK;

  // Allocate enough memory to contain this data structure.
  (*_message) = malloc(sizeof(plc4c_s7_read_write_s7_var_payload_status_item));
  if(*_message == NULL) {
    return NO_MEMORY;
  }

  // Simple Field (returnCode)
  plc4c_s7_read_write_data_transport_error_code* returnCode;
  _res = plc4c_s7_read_write_data_transport_error_code_parse(readBuffer, (void*) &returnCode);
  if(_res != OK) {
    return _res;
  }
  (*_message)->return_code = *returnCode;

  return OK;
}

plc4c_return_code plc4c_s7_read_write_s7_var_payload_status_item_serialize(plc4c_spi_write_buffer* writeBuffer, plc4c_s7_read_write_s7_var_payload_status_item* _message) {
  plc4c_return_code _res = OK;

  // Simple Field (returnCode)
  _res = plc4c_s7_read_write_data_transport_error_code_serialize(writeBuffer, &_message->return_code);
  if(_res != OK) {
    return _res;
  }

  return OK;
}

uint16_t plc4c_s7_read_write_s7_var_payload_status_item_length_in_bytes(plc4c_s7_read_write_s7_var_payload_status_item* _message) {
  return plc4c_s7_read_write_s7_var_payload_status_item_length_in_bits(_message) / 8;
}

uint16_t plc4c_s7_read_write_s7_var_payload_status_item_length_in_bits(plc4c_s7_read_write_s7_var_payload_status_item* _message) {
  uint16_t lengthInBits = 0;

  // Simple field (returnCode)
  lengthInBits += plc4c_s7_read_write_data_transport_error_code_length_in_bits(&_message->return_code);

  return lengthInBits;
}

