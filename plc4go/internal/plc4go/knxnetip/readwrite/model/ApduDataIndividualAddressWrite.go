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
)

// Code generated by code-generation. DO NOT EDIT.

// The data-structure of this message
type ApduDataIndividualAddressWrite struct {
	Parent *ApduData
}

// The corresponding interface
type IApduDataIndividualAddressWrite interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *ApduDataIndividualAddressWrite) ApciType() uint8 {
	return 0x3
}

func (m *ApduDataIndividualAddressWrite) InitializeParent(parent *ApduData) {
}

func NewApduDataIndividualAddressWrite() *ApduData {
	child := &ApduDataIndividualAddressWrite{
		Parent: NewApduData(),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastApduDataIndividualAddressWrite(structType interface{}) *ApduDataIndividualAddressWrite {
	castFunc := func(typ interface{}) *ApduDataIndividualAddressWrite {
		if casted, ok := typ.(ApduDataIndividualAddressWrite); ok {
			return &casted
		}
		if casted, ok := typ.(*ApduDataIndividualAddressWrite); ok {
			return casted
		}
		if casted, ok := typ.(ApduData); ok {
			return CastApduDataIndividualAddressWrite(casted.Child)
		}
		if casted, ok := typ.(*ApduData); ok {
			return CastApduDataIndividualAddressWrite(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *ApduDataIndividualAddressWrite) GetTypeName() string {
	return "ApduDataIndividualAddressWrite"
}

func (m *ApduDataIndividualAddressWrite) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *ApduDataIndividualAddressWrite) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(m.Parent.ParentLengthInBits())

	return lengthInBits
}

func (m *ApduDataIndividualAddressWrite) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func ApduDataIndividualAddressWriteParse(readBuffer utils.ReadBuffer) (*ApduData, error) {
	if pullErr := readBuffer.PullContext("ApduDataIndividualAddressWrite"); pullErr != nil {
		return nil, pullErr
	}

	if closeErr := readBuffer.CloseContext("ApduDataIndividualAddressWrite"); closeErr != nil {
		return nil, closeErr
	}

	// Create a partially initialized instance
	_child := &ApduDataIndividualAddressWrite{
		Parent: &ApduData{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *ApduDataIndividualAddressWrite) Serialize(writeBuffer utils.WriteBuffer) error {
	ser := func() error {
		if pushErr := writeBuffer.PushContext("ApduDataIndividualAddressWrite"); pushErr != nil {
			return pushErr
		}

		if popErr := writeBuffer.PopContext("ApduDataIndividualAddressWrite"); popErr != nil {
			return popErr
		}
		return nil
	}
	return m.Parent.SerializeParent(writeBuffer, m, ser)
}

func (m *ApduDataIndividualAddressWrite) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}