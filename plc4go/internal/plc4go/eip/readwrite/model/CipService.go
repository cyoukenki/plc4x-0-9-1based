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
type CipService struct {
	Child ICipServiceChild
}

// The corresponding interface
type ICipService interface {
	Service() uint8
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

type ICipServiceParent interface {
	SerializeParent(writeBuffer utils.WriteBuffer, child ICipService, serializeChildFunction func() error) error
	GetTypeName() string
}

type ICipServiceChild interface {
	Serialize(writeBuffer utils.WriteBuffer) error
	InitializeParent(parent *CipService)
	GetTypeName() string
	ICipService
}

func NewCipService() *CipService {
	return &CipService{}
}

func CastCipService(structType interface{}) *CipService {
	castFunc := func(typ interface{}) *CipService {
		if casted, ok := typ.(CipService); ok {
			return &casted
		}
		if casted, ok := typ.(*CipService); ok {
			return casted
		}
		return nil
	}
	return castFunc(structType)
}

func (m *CipService) GetTypeName() string {
	return "CipService"
}

func (m *CipService) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *CipService) LengthInBitsConditional(lastItem bool) uint16 {
	return m.Child.LengthInBits()
}

func (m *CipService) ParentLengthInBits() uint16 {
	lengthInBits := uint16(0)
	// Discriminator Field (service)
	lengthInBits += 8

	return lengthInBits
}

func (m *CipService) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func CipServiceParse(readBuffer utils.ReadBuffer, serviceLen uint16) (*CipService, error) {
	if pullErr := readBuffer.PullContext("CipService"); pullErr != nil {
		return nil, pullErr
	}

	// Discriminator Field (service) (Used as input to a switch field)
	service, _serviceErr := readBuffer.ReadUint8("service", 8)
	if _serviceErr != nil {
		return nil, errors.Wrap(_serviceErr, "Error parsing 'service' field")
	}

	// Switch Field (Depending on the discriminator values, passes the instantiation to a sub-type)
	var _parent *CipService
	var typeSwitchError error
	switch {
	case service == 0x4C: // CipReadRequest
		_parent, typeSwitchError = CipReadRequestParse(readBuffer)
	case service == 0xCC: // CipReadResponse
		_parent, typeSwitchError = CipReadResponseParse(readBuffer, serviceLen)
	case service == 0x4D: // CipWriteRequest
		_parent, typeSwitchError = CipWriteRequestParse(readBuffer)
	case service == 0xCD: // CipWriteResponse
		_parent, typeSwitchError = CipWriteResponseParse(readBuffer)
	case service == 0x0A: // MultipleServiceRequest
		_parent, typeSwitchError = MultipleServiceRequestParse(readBuffer, serviceLen)
	case service == 0x8A: // MultipleServiceResponse
		_parent, typeSwitchError = MultipleServiceResponseParse(readBuffer, serviceLen)
	case service == 0x52: // CipUnconnectedRequest
		_parent, typeSwitchError = CipUnconnectedRequestParse(readBuffer)
	default:
		// TODO: return actual type
		typeSwitchError = errors.New("Unmapped type")
	}
	if typeSwitchError != nil {
		return nil, errors.Wrap(typeSwitchError, "Error parsing sub-type for type-switch.")
	}

	if closeErr := readBuffer.CloseContext("CipService"); closeErr != nil {
		return nil, closeErr
	}

	// Finish initializing
	_parent.Child.InitializeParent(_parent)
	return _parent, nil
}

func (m *CipService) Serialize(writeBuffer utils.WriteBuffer) error {
	return m.Child.Serialize(writeBuffer)
}

func (m *CipService) SerializeParent(writeBuffer utils.WriteBuffer, child ICipService, serializeChildFunction func() error) error {
	if pushErr := writeBuffer.PushContext("CipService"); pushErr != nil {
		return pushErr
	}

	// Discriminator Field (service) (Used as input to a switch field)
	service := uint8(child.Service())
	_serviceErr := writeBuffer.WriteUint8("service", 8, (service))

	if _serviceErr != nil {
		return errors.Wrap(_serviceErr, "Error serializing 'service' field")
	}

	// Switch field (Depending on the discriminator values, passes the serialization to a sub-type)
	_typeSwitchErr := serializeChildFunction()
	if _typeSwitchErr != nil {
		return errors.Wrap(_typeSwitchErr, "Error serializing sub-type field")
	}

	if popErr := writeBuffer.PopContext("CipService"); popErr != nil {
		return popErr
	}
	return nil
}

func (m *CipService) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}