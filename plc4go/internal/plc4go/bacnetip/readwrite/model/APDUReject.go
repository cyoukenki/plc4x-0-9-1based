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
	"github.com/rs/zerolog/log"
)

// Code generated by code-generation. DO NOT EDIT.

// The data-structure of this message
type APDUReject struct {
	OriginalInvokeId uint8
	RejectReason     uint8
	Parent           *APDU
}

// The corresponding interface
type IAPDUReject interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *APDUReject) ApduType() uint8 {
	return 0x6
}

func (m *APDUReject) InitializeParent(parent *APDU) {
}

func NewAPDUReject(originalInvokeId uint8, rejectReason uint8) *APDU {
	child := &APDUReject{
		OriginalInvokeId: originalInvokeId,
		RejectReason:     rejectReason,
		Parent:           NewAPDU(),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastAPDUReject(structType interface{}) *APDUReject {
	castFunc := func(typ interface{}) *APDUReject {
		if casted, ok := typ.(APDUReject); ok {
			return &casted
		}
		if casted, ok := typ.(*APDUReject); ok {
			return casted
		}
		if casted, ok := typ.(APDU); ok {
			return CastAPDUReject(casted.Child)
		}
		if casted, ok := typ.(*APDU); ok {
			return CastAPDUReject(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *APDUReject) GetTypeName() string {
	return "APDUReject"
}

func (m *APDUReject) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *APDUReject) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(m.Parent.ParentLengthInBits())

	// Reserved Field (reserved)
	lengthInBits += 4

	// Simple field (originalInvokeId)
	lengthInBits += 8

	// Simple field (rejectReason)
	lengthInBits += 8

	return lengthInBits
}

func (m *APDUReject) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func APDURejectParse(readBuffer utils.ReadBuffer) (*APDU, error) {
	if pullErr := readBuffer.PullContext("APDUReject"); pullErr != nil {
		return nil, pullErr
	}

	// Reserved Field (Compartmentalized so the "reserved" variable can't leak)
	{
		reserved, _err := readBuffer.ReadUint8("reserved", 4)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'reserved' field")
		}
		if reserved != uint8(0x00) {
			log.Info().Fields(map[string]interface{}{
				"expected value": uint8(0x00),
				"got value":      reserved,
			}).Msg("Got unexpected response.")
		}
	}

	// Simple Field (originalInvokeId)
	originalInvokeId, _originalInvokeIdErr := readBuffer.ReadUint8("originalInvokeId", 8)
	if _originalInvokeIdErr != nil {
		return nil, errors.Wrap(_originalInvokeIdErr, "Error parsing 'originalInvokeId' field")
	}

	// Simple Field (rejectReason)
	rejectReason, _rejectReasonErr := readBuffer.ReadUint8("rejectReason", 8)
	if _rejectReasonErr != nil {
		return nil, errors.Wrap(_rejectReasonErr, "Error parsing 'rejectReason' field")
	}

	if closeErr := readBuffer.CloseContext("APDUReject"); closeErr != nil {
		return nil, closeErr
	}

	// Create a partially initialized instance
	_child := &APDUReject{
		OriginalInvokeId: originalInvokeId,
		RejectReason:     rejectReason,
		Parent:           &APDU{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *APDUReject) Serialize(writeBuffer utils.WriteBuffer) error {
	ser := func() error {
		if pushErr := writeBuffer.PushContext("APDUReject"); pushErr != nil {
			return pushErr
		}

		// Reserved Field (reserved)
		{
			_err := writeBuffer.WriteUint8("reserved", 4, uint8(0x00))
			if _err != nil {
				return errors.Wrap(_err, "Error serializing 'reserved' field")
			}
		}

		// Simple Field (originalInvokeId)
		originalInvokeId := uint8(m.OriginalInvokeId)
		_originalInvokeIdErr := writeBuffer.WriteUint8("originalInvokeId", 8, (originalInvokeId))
		if _originalInvokeIdErr != nil {
			return errors.Wrap(_originalInvokeIdErr, "Error serializing 'originalInvokeId' field")
		}

		// Simple Field (rejectReason)
		rejectReason := uint8(m.RejectReason)
		_rejectReasonErr := writeBuffer.WriteUint8("rejectReason", 8, (rejectReason))
		if _rejectReasonErr != nil {
			return errors.Wrap(_rejectReasonErr, "Error serializing 'rejectReason' field")
		}

		if popErr := writeBuffer.PopContext("APDUReject"); popErr != nil {
			return popErr
		}
		return nil
	}
	return m.Parent.SerializeParent(writeBuffer, m, ser)
}

func (m *APDUReject) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}