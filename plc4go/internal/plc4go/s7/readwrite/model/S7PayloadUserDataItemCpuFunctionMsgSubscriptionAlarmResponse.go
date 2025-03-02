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

package model

import (
	"github.com/apache/plc4x/plc4go/internal/plc4go/spi/utils"
	"github.com/pkg/errors"
)

// Code generated by code-generation. DO NOT EDIT.

// The data-structure of this message
type S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse struct {
	Result     uint8
	Reserved01 uint8
	AlarmType  AlarmType
	Reserved02 uint8
	Reserved03 uint8
	Parent     *S7PayloadUserDataItem
}

// The corresponding interface
type IS7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) CpuFunctionType() uint8 {
	return 0x08
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) CpuSubfunction() uint8 {
	return 0x02
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) DataLength() uint16 {
	return 0x05
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) InitializeParent(parent *S7PayloadUserDataItem, returnCode DataTransportErrorCode, transportSize DataTransportSize) {
	m.Parent.ReturnCode = returnCode
	m.Parent.TransportSize = transportSize
}

func NewS7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse(result uint8, reserved01 uint8, alarmType AlarmType, reserved02 uint8, reserved03 uint8, returnCode DataTransportErrorCode, transportSize DataTransportSize) *S7PayloadUserDataItem {
	child := &S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse{
		Result:     result,
		Reserved01: reserved01,
		AlarmType:  alarmType,
		Reserved02: reserved02,
		Reserved03: reserved03,
		Parent:     NewS7PayloadUserDataItem(returnCode, transportSize),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastS7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse(structType interface{}) *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse {
	castFunc := func(typ interface{}) *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse {
		if casted, ok := typ.(S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse); ok {
			return &casted
		}
		if casted, ok := typ.(*S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse); ok {
			return casted
		}
		if casted, ok := typ.(S7PayloadUserDataItem); ok {
			return CastS7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse(casted.Child)
		}
		if casted, ok := typ.(*S7PayloadUserDataItem); ok {
			return CastS7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) GetTypeName() string {
	return "S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse"
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(m.Parent.ParentLengthInBits())

	// Simple field (result)
	lengthInBits += 8

	// Simple field (reserved01)
	lengthInBits += 8

	// Simple field (alarmType)
	lengthInBits += 8

	// Simple field (reserved02)
	lengthInBits += 8

	// Simple field (reserved03)
	lengthInBits += 8

	return lengthInBits
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponseParse(readBuffer utils.ReadBuffer) (*S7PayloadUserDataItem, error) {
	if pullErr := readBuffer.PullContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse"); pullErr != nil {
		return nil, pullErr
	}

	// Simple Field (result)
	result, _resultErr := readBuffer.ReadUint8("result", 8)
	if _resultErr != nil {
		return nil, errors.Wrap(_resultErr, "Error parsing 'result' field")
	}

	// Simple Field (reserved01)
	reserved01, _reserved01Err := readBuffer.ReadUint8("reserved01", 8)
	if _reserved01Err != nil {
		return nil, errors.Wrap(_reserved01Err, "Error parsing 'reserved01' field")
	}

	// Simple Field (alarmType)
	if pullErr := readBuffer.PullContext("alarmType"); pullErr != nil {
		return nil, pullErr
	}
	alarmType, _alarmTypeErr := AlarmTypeParse(readBuffer)
	if _alarmTypeErr != nil {
		return nil, errors.Wrap(_alarmTypeErr, "Error parsing 'alarmType' field")
	}
	if closeErr := readBuffer.CloseContext("alarmType"); closeErr != nil {
		return nil, closeErr
	}

	// Simple Field (reserved02)
	reserved02, _reserved02Err := readBuffer.ReadUint8("reserved02", 8)
	if _reserved02Err != nil {
		return nil, errors.Wrap(_reserved02Err, "Error parsing 'reserved02' field")
	}

	// Simple Field (reserved03)
	reserved03, _reserved03Err := readBuffer.ReadUint8("reserved03", 8)
	if _reserved03Err != nil {
		return nil, errors.Wrap(_reserved03Err, "Error parsing 'reserved03' field")
	}

	if closeErr := readBuffer.CloseContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse"); closeErr != nil {
		return nil, closeErr
	}

	// Create a partially initialized instance
	_child := &S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse{
		Result:     result,
		Reserved01: reserved01,
		AlarmType:  alarmType,
		Reserved02: reserved02,
		Reserved03: reserved03,
		Parent:     &S7PayloadUserDataItem{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) Serialize(writeBuffer utils.WriteBuffer) error {
	ser := func() error {
		if pushErr := writeBuffer.PushContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse"); pushErr != nil {
			return pushErr
		}

		// Simple Field (result)
		result := uint8(m.Result)
		_resultErr := writeBuffer.WriteUint8("result", 8, (result))
		if _resultErr != nil {
			return errors.Wrap(_resultErr, "Error serializing 'result' field")
		}

		// Simple Field (reserved01)
		reserved01 := uint8(m.Reserved01)
		_reserved01Err := writeBuffer.WriteUint8("reserved01", 8, (reserved01))
		if _reserved01Err != nil {
			return errors.Wrap(_reserved01Err, "Error serializing 'reserved01' field")
		}

		// Simple Field (alarmType)
		if pushErr := writeBuffer.PushContext("alarmType"); pushErr != nil {
			return pushErr
		}
		_alarmTypeErr := m.AlarmType.Serialize(writeBuffer)
		if popErr := writeBuffer.PopContext("alarmType"); popErr != nil {
			return popErr
		}
		if _alarmTypeErr != nil {
			return errors.Wrap(_alarmTypeErr, "Error serializing 'alarmType' field")
		}

		// Simple Field (reserved02)
		reserved02 := uint8(m.Reserved02)
		_reserved02Err := writeBuffer.WriteUint8("reserved02", 8, (reserved02))
		if _reserved02Err != nil {
			return errors.Wrap(_reserved02Err, "Error serializing 'reserved02' field")
		}

		// Simple Field (reserved03)
		reserved03 := uint8(m.Reserved03)
		_reserved03Err := writeBuffer.WriteUint8("reserved03", 8, (reserved03))
		if _reserved03Err != nil {
			return errors.Wrap(_reserved03Err, "Error serializing 'reserved03' field")
		}

		if popErr := writeBuffer.PopContext("S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse"); popErr != nil {
			return popErr
		}
		return nil
	}
	return m.Parent.SerializeParent(writeBuffer, m, ser)
}

func (m *S7PayloadUserDataItemCpuFunctionMsgSubscriptionAlarmResponse) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}
