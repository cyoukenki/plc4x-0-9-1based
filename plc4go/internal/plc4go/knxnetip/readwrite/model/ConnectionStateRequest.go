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
type ConnectionStateRequest struct {
	CommunicationChannelId uint8
	HpaiControlEndpoint    *HPAIControlEndpoint
	Parent                 *KnxNetIpMessage
}

// The corresponding interface
type IConnectionStateRequest interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *ConnectionStateRequest) MsgType() uint16 {
	return 0x0207
}

func (m *ConnectionStateRequest) InitializeParent(parent *KnxNetIpMessage) {
}

func NewConnectionStateRequest(communicationChannelId uint8, hpaiControlEndpoint *HPAIControlEndpoint) *KnxNetIpMessage {
	child := &ConnectionStateRequest{
		CommunicationChannelId: communicationChannelId,
		HpaiControlEndpoint:    hpaiControlEndpoint,
		Parent:                 NewKnxNetIpMessage(),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastConnectionStateRequest(structType interface{}) *ConnectionStateRequest {
	castFunc := func(typ interface{}) *ConnectionStateRequest {
		if casted, ok := typ.(ConnectionStateRequest); ok {
			return &casted
		}
		if casted, ok := typ.(*ConnectionStateRequest); ok {
			return casted
		}
		if casted, ok := typ.(KnxNetIpMessage); ok {
			return CastConnectionStateRequest(casted.Child)
		}
		if casted, ok := typ.(*KnxNetIpMessage); ok {
			return CastConnectionStateRequest(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *ConnectionStateRequest) GetTypeName() string {
	return "ConnectionStateRequest"
}

func (m *ConnectionStateRequest) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *ConnectionStateRequest) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(m.Parent.ParentLengthInBits())

	// Simple field (communicationChannelId)
	lengthInBits += 8

	// Reserved Field (reserved)
	lengthInBits += 8

	// Simple field (hpaiControlEndpoint)
	lengthInBits += m.HpaiControlEndpoint.LengthInBits()

	return lengthInBits
}

func (m *ConnectionStateRequest) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func ConnectionStateRequestParse(readBuffer utils.ReadBuffer) (*KnxNetIpMessage, error) {
	if pullErr := readBuffer.PullContext("ConnectionStateRequest"); pullErr != nil {
		return nil, pullErr
	}

	// Simple Field (communicationChannelId)
	communicationChannelId, _communicationChannelIdErr := readBuffer.ReadUint8("communicationChannelId", 8)
	if _communicationChannelIdErr != nil {
		return nil, errors.Wrap(_communicationChannelIdErr, "Error parsing 'communicationChannelId' field")
	}

	// Reserved Field (Compartmentalized so the "reserved" variable can't leak)
	{
		reserved, _err := readBuffer.ReadUint8("reserved", 8)
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

	// Simple Field (hpaiControlEndpoint)
	if pullErr := readBuffer.PullContext("hpaiControlEndpoint"); pullErr != nil {
		return nil, pullErr
	}
	hpaiControlEndpoint, _hpaiControlEndpointErr := HPAIControlEndpointParse(readBuffer)
	if _hpaiControlEndpointErr != nil {
		return nil, errors.Wrap(_hpaiControlEndpointErr, "Error parsing 'hpaiControlEndpoint' field")
	}
	if closeErr := readBuffer.CloseContext("hpaiControlEndpoint"); closeErr != nil {
		return nil, closeErr
	}

	if closeErr := readBuffer.CloseContext("ConnectionStateRequest"); closeErr != nil {
		return nil, closeErr
	}

	// Create a partially initialized instance
	_child := &ConnectionStateRequest{
		CommunicationChannelId: communicationChannelId,
		HpaiControlEndpoint:    hpaiControlEndpoint,
		Parent:                 &KnxNetIpMessage{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *ConnectionStateRequest) Serialize(writeBuffer utils.WriteBuffer) error {
	ser := func() error {
		if pushErr := writeBuffer.PushContext("ConnectionStateRequest"); pushErr != nil {
			return pushErr
		}

		// Simple Field (communicationChannelId)
		communicationChannelId := uint8(m.CommunicationChannelId)
		_communicationChannelIdErr := writeBuffer.WriteUint8("communicationChannelId", 8, (communicationChannelId))
		if _communicationChannelIdErr != nil {
			return errors.Wrap(_communicationChannelIdErr, "Error serializing 'communicationChannelId' field")
		}

		// Reserved Field (reserved)
		{
			_err := writeBuffer.WriteUint8("reserved", 8, uint8(0x00))
			if _err != nil {
				return errors.Wrap(_err, "Error serializing 'reserved' field")
			}
		}

		// Simple Field (hpaiControlEndpoint)
		if pushErr := writeBuffer.PushContext("hpaiControlEndpoint"); pushErr != nil {
			return pushErr
		}
		_hpaiControlEndpointErr := m.HpaiControlEndpoint.Serialize(writeBuffer)
		if popErr := writeBuffer.PopContext("hpaiControlEndpoint"); popErr != nil {
			return popErr
		}
		if _hpaiControlEndpointErr != nil {
			return errors.Wrap(_hpaiControlEndpointErr, "Error serializing 'hpaiControlEndpoint' field")
		}

		if popErr := writeBuffer.PopContext("ConnectionStateRequest"); popErr != nil {
			return popErr
		}
		return nil
	}
	return m.Parent.SerializeParent(writeBuffer, m, ser)
}

func (m *ConnectionStateRequest) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}