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
type NPDU struct {
	ProtocolVersionNumber     uint8
	MessageTypeFieldPresent   bool
	DestinationSpecified      bool
	SourceSpecified           bool
	ExpectingReply            bool
	NetworkPriority           uint8
	DestinationNetworkAddress *uint16
	DestinationLength         *uint8
	DestinationAddress        []uint8
	SourceNetworkAddress      *uint16
	SourceLength              *uint8
	SourceAddress             []uint8
	HopCount                  *uint8
	Nlm                       *NLM
	Apdu                      *APDU
}

// The corresponding interface
type INPDU interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

func NewNPDU(protocolVersionNumber uint8, messageTypeFieldPresent bool, destinationSpecified bool, sourceSpecified bool, expectingReply bool, networkPriority uint8, destinationNetworkAddress *uint16, destinationLength *uint8, destinationAddress []uint8, sourceNetworkAddress *uint16, sourceLength *uint8, sourceAddress []uint8, hopCount *uint8, nlm *NLM, apdu *APDU) *NPDU {
	return &NPDU{ProtocolVersionNumber: protocolVersionNumber, MessageTypeFieldPresent: messageTypeFieldPresent, DestinationSpecified: destinationSpecified, SourceSpecified: sourceSpecified, ExpectingReply: expectingReply, NetworkPriority: networkPriority, DestinationNetworkAddress: destinationNetworkAddress, DestinationLength: destinationLength, DestinationAddress: destinationAddress, SourceNetworkAddress: sourceNetworkAddress, SourceLength: sourceLength, SourceAddress: sourceAddress, HopCount: hopCount, Nlm: nlm, Apdu: apdu}
}

func CastNPDU(structType interface{}) *NPDU {
	castFunc := func(typ interface{}) *NPDU {
		if casted, ok := typ.(NPDU); ok {
			return &casted
		}
		if casted, ok := typ.(*NPDU); ok {
			return casted
		}
		return nil
	}
	return castFunc(structType)
}

func (m *NPDU) GetTypeName() string {
	return "NPDU"
}

func (m *NPDU) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *NPDU) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(0)

	// Simple field (protocolVersionNumber)
	lengthInBits += 8

	// Simple field (messageTypeFieldPresent)
	lengthInBits += 1

	// Reserved Field (reserved)
	lengthInBits += 1

	// Simple field (destinationSpecified)
	lengthInBits += 1

	// Reserved Field (reserved)
	lengthInBits += 1

	// Simple field (sourceSpecified)
	lengthInBits += 1

	// Simple field (expectingReply)
	lengthInBits += 1

	// Simple field (networkPriority)
	lengthInBits += 2

	// Optional Field (destinationNetworkAddress)
	if m.DestinationNetworkAddress != nil {
		lengthInBits += 16
	}

	// Optional Field (destinationLength)
	if m.DestinationLength != nil {
		lengthInBits += 8
	}

	// Array field
	if len(m.DestinationAddress) > 0 {
		lengthInBits += 8 * uint16(len(m.DestinationAddress))
	}

	// Optional Field (sourceNetworkAddress)
	if m.SourceNetworkAddress != nil {
		lengthInBits += 16
	}

	// Optional Field (sourceLength)
	if m.SourceLength != nil {
		lengthInBits += 8
	}

	// Array field
	if len(m.SourceAddress) > 0 {
		lengthInBits += 8 * uint16(len(m.SourceAddress))
	}

	// Optional Field (hopCount)
	if m.HopCount != nil {
		lengthInBits += 8
	}

	// Optional Field (nlm)
	if m.Nlm != nil {
		lengthInBits += (*m.Nlm).LengthInBits()
	}

	// Optional Field (apdu)
	if m.Apdu != nil {
		lengthInBits += (*m.Apdu).LengthInBits()
	}

	return lengthInBits
}

func (m *NPDU) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func NPDUParse(readBuffer utils.ReadBuffer, npduLength uint16) (*NPDU, error) {
	if pullErr := readBuffer.PullContext("NPDU"); pullErr != nil {
		return nil, pullErr
	}

	// Simple Field (protocolVersionNumber)
	protocolVersionNumber, _protocolVersionNumberErr := readBuffer.ReadUint8("protocolVersionNumber", 8)
	if _protocolVersionNumberErr != nil {
		return nil, errors.Wrap(_protocolVersionNumberErr, "Error parsing 'protocolVersionNumber' field")
	}

	// Simple Field (messageTypeFieldPresent)
	messageTypeFieldPresent, _messageTypeFieldPresentErr := readBuffer.ReadBit("messageTypeFieldPresent")
	if _messageTypeFieldPresentErr != nil {
		return nil, errors.Wrap(_messageTypeFieldPresentErr, "Error parsing 'messageTypeFieldPresent' field")
	}

	// Reserved Field (Compartmentalized so the "reserved" variable can't leak)
	{
		reserved, _err := readBuffer.ReadUint8("reserved", 1)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'reserved' field")
		}
		if reserved != uint8(0) {
			log.Info().Fields(map[string]interface{}{
				"expected value": uint8(0),
				"got value":      reserved,
			}).Msg("Got unexpected response.")
		}
	}

	// Simple Field (destinationSpecified)
	destinationSpecified, _destinationSpecifiedErr := readBuffer.ReadBit("destinationSpecified")
	if _destinationSpecifiedErr != nil {
		return nil, errors.Wrap(_destinationSpecifiedErr, "Error parsing 'destinationSpecified' field")
	}

	// Reserved Field (Compartmentalized so the "reserved" variable can't leak)
	{
		reserved, _err := readBuffer.ReadUint8("reserved", 1)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'reserved' field")
		}
		if reserved != uint8(0) {
			log.Info().Fields(map[string]interface{}{
				"expected value": uint8(0),
				"got value":      reserved,
			}).Msg("Got unexpected response.")
		}
	}

	// Simple Field (sourceSpecified)
	sourceSpecified, _sourceSpecifiedErr := readBuffer.ReadBit("sourceSpecified")
	if _sourceSpecifiedErr != nil {
		return nil, errors.Wrap(_sourceSpecifiedErr, "Error parsing 'sourceSpecified' field")
	}

	// Simple Field (expectingReply)
	expectingReply, _expectingReplyErr := readBuffer.ReadBit("expectingReply")
	if _expectingReplyErr != nil {
		return nil, errors.Wrap(_expectingReplyErr, "Error parsing 'expectingReply' field")
	}

	// Simple Field (networkPriority)
	networkPriority, _networkPriorityErr := readBuffer.ReadUint8("networkPriority", 2)
	if _networkPriorityErr != nil {
		return nil, errors.Wrap(_networkPriorityErr, "Error parsing 'networkPriority' field")
	}

	// Optional Field (destinationNetworkAddress) (Can be skipped, if a given expression evaluates to false)
	var destinationNetworkAddress *uint16 = nil
	if destinationSpecified {
		_val, _err := readBuffer.ReadUint16("destinationNetworkAddress", 16)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'destinationNetworkAddress' field")
		}
		destinationNetworkAddress = &_val
	}

	// Optional Field (destinationLength) (Can be skipped, if a given expression evaluates to false)
	var destinationLength *uint8 = nil
	if destinationSpecified {
		_val, _err := readBuffer.ReadUint8("destinationLength", 8)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'destinationLength' field")
		}
		destinationLength = &_val
	}

	// Array field (destinationAddress)
	if pullErr := readBuffer.PullContext("destinationAddress", utils.WithRenderAsList(true)); pullErr != nil {
		return nil, pullErr
	}
	// Count array
	destinationAddress := make([]uint8, utils.InlineIf(destinationSpecified, func() uint16 { return uint16((*destinationLength)) }, func() uint16 { return uint16(uint16(0)) }))
	for curItem := uint16(0); curItem < uint16(utils.InlineIf(destinationSpecified, func() uint16 { return uint16((*destinationLength)) }, func() uint16 { return uint16(uint16(0)) })); curItem++ {
		_item, _err := readBuffer.ReadUint8("", 8)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'destinationAddress' field")
		}
		destinationAddress[curItem] = _item
	}
	if closeErr := readBuffer.CloseContext("destinationAddress", utils.WithRenderAsList(true)); closeErr != nil {
		return nil, closeErr
	}

	// Optional Field (sourceNetworkAddress) (Can be skipped, if a given expression evaluates to false)
	var sourceNetworkAddress *uint16 = nil
	if sourceSpecified {
		_val, _err := readBuffer.ReadUint16("sourceNetworkAddress", 16)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'sourceNetworkAddress' field")
		}
		sourceNetworkAddress = &_val
	}

	// Optional Field (sourceLength) (Can be skipped, if a given expression evaluates to false)
	var sourceLength *uint8 = nil
	if sourceSpecified {
		_val, _err := readBuffer.ReadUint8("sourceLength", 8)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'sourceLength' field")
		}
		sourceLength = &_val
	}

	// Array field (sourceAddress)
	if pullErr := readBuffer.PullContext("sourceAddress", utils.WithRenderAsList(true)); pullErr != nil {
		return nil, pullErr
	}
	// Count array
	sourceAddress := make([]uint8, utils.InlineIf(sourceSpecified, func() uint16 { return uint16((*sourceLength)) }, func() uint16 { return uint16(uint16(0)) }))
	for curItem := uint16(0); curItem < uint16(utils.InlineIf(sourceSpecified, func() uint16 { return uint16((*sourceLength)) }, func() uint16 { return uint16(uint16(0)) })); curItem++ {
		_item, _err := readBuffer.ReadUint8("", 8)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'sourceAddress' field")
		}
		sourceAddress[curItem] = _item
	}
	if closeErr := readBuffer.CloseContext("sourceAddress", utils.WithRenderAsList(true)); closeErr != nil {
		return nil, closeErr
	}

	// Optional Field (hopCount) (Can be skipped, if a given expression evaluates to false)
	var hopCount *uint8 = nil
	if destinationSpecified {
		_val, _err := readBuffer.ReadUint8("hopCount", 8)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'hopCount' field")
		}
		hopCount = &_val
	}

	// Optional Field (nlm) (Can be skipped, if a given expression evaluates to false)
	var nlm *NLM = nil
	if messageTypeFieldPresent {
		_val, _err := NLMParse(readBuffer, uint16(npduLength)-uint16(uint16(uint16(uint16(uint16(uint16(2))+uint16(uint16(utils.InlineIf(sourceSpecified, func() uint16 { return uint16(uint16(uint16(3)) + uint16((*sourceLength))) }, func() uint16 { return uint16(uint16(0)) }))))+uint16(uint16(utils.InlineIf(destinationSpecified, func() uint16 { return uint16(uint16(uint16(3)) + uint16((*destinationLength))) }, func() uint16 { return uint16(uint16(0)) }))))+uint16(uint16(utils.InlineIf(bool(bool(destinationSpecified) || bool(sourceSpecified)), func() uint16 { return uint16(uint16(1)) }, func() uint16 { return uint16(uint16(0)) }))))))
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'nlm' field")
		}
		nlm = _val
	}

	// Optional Field (apdu) (Can be skipped, if a given expression evaluates to false)
	var apdu *APDU = nil
	if !(messageTypeFieldPresent) {
		_val, _err := APDUParse(readBuffer, uint16(npduLength)-uint16(uint16(uint16(uint16(uint16(uint16(2))+uint16(uint16(utils.InlineIf(sourceSpecified, func() uint16 { return uint16(uint16(uint16(3)) + uint16((*sourceLength))) }, func() uint16 { return uint16(uint16(0)) }))))+uint16(uint16(utils.InlineIf(destinationSpecified, func() uint16 { return uint16(uint16(uint16(3)) + uint16((*destinationLength))) }, func() uint16 { return uint16(uint16(0)) }))))+uint16(uint16(utils.InlineIf(bool(bool(destinationSpecified) || bool(sourceSpecified)), func() uint16 { return uint16(uint16(1)) }, func() uint16 { return uint16(uint16(0)) }))))))
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'apdu' field")
		}
		apdu = _val
	}

	if closeErr := readBuffer.CloseContext("NPDU"); closeErr != nil {
		return nil, closeErr
	}

	// Create the instance
	return NewNPDU(protocolVersionNumber, messageTypeFieldPresent, destinationSpecified, sourceSpecified, expectingReply, networkPriority, destinationNetworkAddress, destinationLength, destinationAddress, sourceNetworkAddress, sourceLength, sourceAddress, hopCount, nlm, apdu), nil
}

func (m *NPDU) Serialize(writeBuffer utils.WriteBuffer) error {
	if pushErr := writeBuffer.PushContext("NPDU"); pushErr != nil {
		return pushErr
	}

	// Simple Field (protocolVersionNumber)
	protocolVersionNumber := uint8(m.ProtocolVersionNumber)
	_protocolVersionNumberErr := writeBuffer.WriteUint8("protocolVersionNumber", 8, (protocolVersionNumber))
	if _protocolVersionNumberErr != nil {
		return errors.Wrap(_protocolVersionNumberErr, "Error serializing 'protocolVersionNumber' field")
	}

	// Simple Field (messageTypeFieldPresent)
	messageTypeFieldPresent := bool(m.MessageTypeFieldPresent)
	_messageTypeFieldPresentErr := writeBuffer.WriteBit("messageTypeFieldPresent", (messageTypeFieldPresent))
	if _messageTypeFieldPresentErr != nil {
		return errors.Wrap(_messageTypeFieldPresentErr, "Error serializing 'messageTypeFieldPresent' field")
	}

	// Reserved Field (reserved)
	{
		_err := writeBuffer.WriteUint8("reserved", 1, uint8(0))
		if _err != nil {
			return errors.Wrap(_err, "Error serializing 'reserved' field")
		}
	}

	// Simple Field (destinationSpecified)
	destinationSpecified := bool(m.DestinationSpecified)
	_destinationSpecifiedErr := writeBuffer.WriteBit("destinationSpecified", (destinationSpecified))
	if _destinationSpecifiedErr != nil {
		return errors.Wrap(_destinationSpecifiedErr, "Error serializing 'destinationSpecified' field")
	}

	// Reserved Field (reserved)
	{
		_err := writeBuffer.WriteUint8("reserved", 1, uint8(0))
		if _err != nil {
			return errors.Wrap(_err, "Error serializing 'reserved' field")
		}
	}

	// Simple Field (sourceSpecified)
	sourceSpecified := bool(m.SourceSpecified)
	_sourceSpecifiedErr := writeBuffer.WriteBit("sourceSpecified", (sourceSpecified))
	if _sourceSpecifiedErr != nil {
		return errors.Wrap(_sourceSpecifiedErr, "Error serializing 'sourceSpecified' field")
	}

	// Simple Field (expectingReply)
	expectingReply := bool(m.ExpectingReply)
	_expectingReplyErr := writeBuffer.WriteBit("expectingReply", (expectingReply))
	if _expectingReplyErr != nil {
		return errors.Wrap(_expectingReplyErr, "Error serializing 'expectingReply' field")
	}

	// Simple Field (networkPriority)
	networkPriority := uint8(m.NetworkPriority)
	_networkPriorityErr := writeBuffer.WriteUint8("networkPriority", 2, (networkPriority))
	if _networkPriorityErr != nil {
		return errors.Wrap(_networkPriorityErr, "Error serializing 'networkPriority' field")
	}

	// Optional Field (destinationNetworkAddress) (Can be skipped, if the value is null)
	var destinationNetworkAddress *uint16 = nil
	if m.DestinationNetworkAddress != nil {
		destinationNetworkAddress = m.DestinationNetworkAddress
		_destinationNetworkAddressErr := writeBuffer.WriteUint16("destinationNetworkAddress", 16, *(destinationNetworkAddress))
		if _destinationNetworkAddressErr != nil {
			return errors.Wrap(_destinationNetworkAddressErr, "Error serializing 'destinationNetworkAddress' field")
		}
	}

	// Optional Field (destinationLength) (Can be skipped, if the value is null)
	var destinationLength *uint8 = nil
	if m.DestinationLength != nil {
		destinationLength = m.DestinationLength
		_destinationLengthErr := writeBuffer.WriteUint8("destinationLength", 8, *(destinationLength))
		if _destinationLengthErr != nil {
			return errors.Wrap(_destinationLengthErr, "Error serializing 'destinationLength' field")
		}
	}

	// Array Field (destinationAddress)
	if m.DestinationAddress != nil {
		if pushErr := writeBuffer.PushContext("destinationAddress", utils.WithRenderAsList(true)); pushErr != nil {
			return pushErr
		}
		for _, _element := range m.DestinationAddress {
			_elementErr := writeBuffer.WriteUint8("", 8, _element)
			if _elementErr != nil {
				return errors.Wrap(_elementErr, "Error serializing 'destinationAddress' field")
			}
		}
		if popErr := writeBuffer.PopContext("destinationAddress", utils.WithRenderAsList(true)); popErr != nil {
			return popErr
		}
	}

	// Optional Field (sourceNetworkAddress) (Can be skipped, if the value is null)
	var sourceNetworkAddress *uint16 = nil
	if m.SourceNetworkAddress != nil {
		sourceNetworkAddress = m.SourceNetworkAddress
		_sourceNetworkAddressErr := writeBuffer.WriteUint16("sourceNetworkAddress", 16, *(sourceNetworkAddress))
		if _sourceNetworkAddressErr != nil {
			return errors.Wrap(_sourceNetworkAddressErr, "Error serializing 'sourceNetworkAddress' field")
		}
	}

	// Optional Field (sourceLength) (Can be skipped, if the value is null)
	var sourceLength *uint8 = nil
	if m.SourceLength != nil {
		sourceLength = m.SourceLength
		_sourceLengthErr := writeBuffer.WriteUint8("sourceLength", 8, *(sourceLength))
		if _sourceLengthErr != nil {
			return errors.Wrap(_sourceLengthErr, "Error serializing 'sourceLength' field")
		}
	}

	// Array Field (sourceAddress)
	if m.SourceAddress != nil {
		if pushErr := writeBuffer.PushContext("sourceAddress", utils.WithRenderAsList(true)); pushErr != nil {
			return pushErr
		}
		for _, _element := range m.SourceAddress {
			_elementErr := writeBuffer.WriteUint8("", 8, _element)
			if _elementErr != nil {
				return errors.Wrap(_elementErr, "Error serializing 'sourceAddress' field")
			}
		}
		if popErr := writeBuffer.PopContext("sourceAddress", utils.WithRenderAsList(true)); popErr != nil {
			return popErr
		}
	}

	// Optional Field (hopCount) (Can be skipped, if the value is null)
	var hopCount *uint8 = nil
	if m.HopCount != nil {
		hopCount = m.HopCount
		_hopCountErr := writeBuffer.WriteUint8("hopCount", 8, *(hopCount))
		if _hopCountErr != nil {
			return errors.Wrap(_hopCountErr, "Error serializing 'hopCount' field")
		}
	}

	// Optional Field (nlm) (Can be skipped, if the value is null)
	var nlm *NLM = nil
	if m.Nlm != nil {
		nlm = m.Nlm
		_nlmErr := nlm.Serialize(writeBuffer)
		if _nlmErr != nil {
			return errors.Wrap(_nlmErr, "Error serializing 'nlm' field")
		}
	}

	// Optional Field (apdu) (Can be skipped, if the value is null)
	var apdu *APDU = nil
	if m.Apdu != nil {
		apdu = m.Apdu
		_apduErr := apdu.Serialize(writeBuffer)
		if _apduErr != nil {
			return errors.Wrap(_apduErr, "Error serializing 'apdu' field")
		}
	}

	if popErr := writeBuffer.PopContext("NPDU"); popErr != nil {
		return popErr
	}
	return nil
}

func (m *NPDU) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}