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
type COTPParameterDisconnectAdditionalInformation struct {
	Data   []byte
	Parent *COTPParameter
}

// The corresponding interface
type ICOTPParameterDisconnectAdditionalInformation interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *COTPParameterDisconnectAdditionalInformation) ParameterType() uint8 {
	return 0xE0
}

func (m *COTPParameterDisconnectAdditionalInformation) InitializeParent(parent *COTPParameter) {
}

func NewCOTPParameterDisconnectAdditionalInformation(data []byte) *COTPParameter {
	child := &COTPParameterDisconnectAdditionalInformation{
		Data:   data,
		Parent: NewCOTPParameter(),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastCOTPParameterDisconnectAdditionalInformation(structType interface{}) *COTPParameterDisconnectAdditionalInformation {
	castFunc := func(typ interface{}) *COTPParameterDisconnectAdditionalInformation {
		if casted, ok := typ.(COTPParameterDisconnectAdditionalInformation); ok {
			return &casted
		}
		if casted, ok := typ.(*COTPParameterDisconnectAdditionalInformation); ok {
			return casted
		}
		if casted, ok := typ.(COTPParameter); ok {
			return CastCOTPParameterDisconnectAdditionalInformation(casted.Child)
		}
		if casted, ok := typ.(*COTPParameter); ok {
			return CastCOTPParameterDisconnectAdditionalInformation(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *COTPParameterDisconnectAdditionalInformation) GetTypeName() string {
	return "COTPParameterDisconnectAdditionalInformation"
}

func (m *COTPParameterDisconnectAdditionalInformation) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *COTPParameterDisconnectAdditionalInformation) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(m.Parent.ParentLengthInBits())

	// Array field
	if len(m.Data) > 0 {
		lengthInBits += 8 * uint16(len(m.Data))
	}

	return lengthInBits
}

func (m *COTPParameterDisconnectAdditionalInformation) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func COTPParameterDisconnectAdditionalInformationParse(readBuffer utils.ReadBuffer, rest uint8) (*COTPParameter, error) {
	if pullErr := readBuffer.PullContext("COTPParameterDisconnectAdditionalInformation"); pullErr != nil {
		return nil, pullErr
	}
	// Byte Array field (data)
	numberOfBytes := int(rest)
	data, _readArrayErr := readBuffer.ReadByteArray("data", numberOfBytes)
	if _readArrayErr != nil {
		return nil, errors.Wrap(_readArrayErr, "Error parsing 'data' field")
	}

	if closeErr := readBuffer.CloseContext("COTPParameterDisconnectAdditionalInformation"); closeErr != nil {
		return nil, closeErr
	}

	// Create a partially initialized instance
	_child := &COTPParameterDisconnectAdditionalInformation{
		Data:   data,
		Parent: &COTPParameter{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *COTPParameterDisconnectAdditionalInformation) Serialize(writeBuffer utils.WriteBuffer) error {
	ser := func() error {
		if pushErr := writeBuffer.PushContext("COTPParameterDisconnectAdditionalInformation"); pushErr != nil {
			return pushErr
		}

		// Array Field (data)
		if m.Data != nil {
			// Byte Array field (data)
			_writeArrayErr := writeBuffer.WriteByteArray("data", m.Data)
			if _writeArrayErr != nil {
				return errors.Wrap(_writeArrayErr, "Error serializing 'data' field")
			}
		}

		if popErr := writeBuffer.PopContext("COTPParameterDisconnectAdditionalInformation"); popErr != nil {
			return popErr
		}
		return nil
	}
	return m.Parent.SerializeParent(writeBuffer, m, ser)
}

func (m *COTPParameterDisconnectAdditionalInformation) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}