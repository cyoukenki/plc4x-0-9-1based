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
type S7MessageResponse struct {
	ErrorClass uint8
	ErrorCode  uint8
	Parent     *S7Message
}

// The corresponding interface
type IS7MessageResponse interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *S7MessageResponse) MessageType() uint8 {
	return 0x02
}

func (m *S7MessageResponse) InitializeParent(parent *S7Message, tpduReference uint16, parameter *S7Parameter, payload *S7Payload) {
	m.Parent.TpduReference = tpduReference
	m.Parent.Parameter = parameter
	m.Parent.Payload = payload
}

func NewS7MessageResponse(errorClass uint8, errorCode uint8, tpduReference uint16, parameter *S7Parameter, payload *S7Payload) *S7Message {
	child := &S7MessageResponse{
		ErrorClass: errorClass,
		ErrorCode:  errorCode,
		Parent:     NewS7Message(tpduReference, parameter, payload),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastS7MessageResponse(structType interface{}) *S7MessageResponse {
	castFunc := func(typ interface{}) *S7MessageResponse {
		if casted, ok := typ.(S7MessageResponse); ok {
			return &casted
		}
		if casted, ok := typ.(*S7MessageResponse); ok {
			return casted
		}
		if casted, ok := typ.(S7Message); ok {
			return CastS7MessageResponse(casted.Child)
		}
		if casted, ok := typ.(*S7Message); ok {
			return CastS7MessageResponse(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *S7MessageResponse) GetTypeName() string {
	return "S7MessageResponse"
}

func (m *S7MessageResponse) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *S7MessageResponse) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(m.Parent.ParentLengthInBits())

	// Simple field (errorClass)
	lengthInBits += 8

	// Simple field (errorCode)
	lengthInBits += 8

	return lengthInBits
}

func (m *S7MessageResponse) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func S7MessageResponseParse(readBuffer utils.ReadBuffer) (*S7Message, error) {
	if pullErr := readBuffer.PullContext("S7MessageResponse"); pullErr != nil {
		return nil, pullErr
	}

	// Simple Field (errorClass)
	errorClass, _errorClassErr := readBuffer.ReadUint8("errorClass", 8)
	if _errorClassErr != nil {
		return nil, errors.Wrap(_errorClassErr, "Error parsing 'errorClass' field")
	}

	// Simple Field (errorCode)
	errorCode, _errorCodeErr := readBuffer.ReadUint8("errorCode", 8)
	if _errorCodeErr != nil {
		return nil, errors.Wrap(_errorCodeErr, "Error parsing 'errorCode' field")
	}

	if closeErr := readBuffer.CloseContext("S7MessageResponse"); closeErr != nil {
		return nil, closeErr
	}

	// Create a partially initialized instance
	_child := &S7MessageResponse{
		ErrorClass: errorClass,
		ErrorCode:  errorCode,
		Parent:     &S7Message{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *S7MessageResponse) Serialize(writeBuffer utils.WriteBuffer) error {
	ser := func() error {
		if pushErr := writeBuffer.PushContext("S7MessageResponse"); pushErr != nil {
			return pushErr
		}

		// Simple Field (errorClass)
		errorClass := uint8(m.ErrorClass)
		_errorClassErr := writeBuffer.WriteUint8("errorClass", 8, (errorClass))
		if _errorClassErr != nil {
			return errors.Wrap(_errorClassErr, "Error serializing 'errorClass' field")
		}

		// Simple Field (errorCode)
		errorCode := uint8(m.ErrorCode)
		_errorCodeErr := writeBuffer.WriteUint8("errorCode", 8, (errorCode))
		if _errorCodeErr != nil {
			return errors.Wrap(_errorCodeErr, "Error serializing 'errorCode' field")
		}

		if popErr := writeBuffer.PopContext("S7MessageResponse"); popErr != nil {
			return popErr
		}
		return nil
	}
	return m.Parent.SerializeParent(writeBuffer, m, ser)
}

func (m *S7MessageResponse) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}