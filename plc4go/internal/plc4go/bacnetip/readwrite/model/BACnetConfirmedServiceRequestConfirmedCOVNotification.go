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
	"fmt"
	"github.com/apache/plc4x/plc4go/internal/plc4go/spi/utils"
	"github.com/pkg/errors"
)

// Code generated by code-generation. DO NOT EDIT.

// Constant values.
const BACnetConfirmedServiceRequestConfirmedCOVNotification_SUBSCRIBERPROCESSIDENTIFIERHEADER uint8 = 0x09
const BACnetConfirmedServiceRequestConfirmedCOVNotification_MONITOREDOBJECTIDENTIFIERHEADER uint8 = 0x1C
const BACnetConfirmedServiceRequestConfirmedCOVNotification_ISSUECONFIRMEDNOTIFICATIONSHEADER uint8 = 0x2C
const BACnetConfirmedServiceRequestConfirmedCOVNotification_LIFETIMEHEADER uint8 = 0x07
const BACnetConfirmedServiceRequestConfirmedCOVNotification_LISTOFVALUESOPENINGTAG uint8 = 0x4E
const BACnetConfirmedServiceRequestConfirmedCOVNotification_LISTOFVALUESCLOSINGTAG uint8 = 0x4F

// The data-structure of this message
type BACnetConfirmedServiceRequestConfirmedCOVNotification struct {
	SubscriberProcessIdentifier               uint8
	MonitoredObjectType                       uint16
	MonitoredObjectInstanceNumber             uint32
	IssueConfirmedNotificationsType           uint16
	IssueConfirmedNotificationsInstanceNumber uint32
	LifetimeLength                            uint8
	LifetimeSeconds                           []int8
	Notifications                             []*BACnetTagWithContent
	Parent                                    *BACnetConfirmedServiceRequest
}

// The corresponding interface
type IBACnetConfirmedServiceRequestConfirmedCOVNotification interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(writeBuffer utils.WriteBuffer) error
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) ServiceChoice() uint8 {
	return 0x01
}

func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) InitializeParent(parent *BACnetConfirmedServiceRequest) {
}

func NewBACnetConfirmedServiceRequestConfirmedCOVNotification(subscriberProcessIdentifier uint8, monitoredObjectType uint16, monitoredObjectInstanceNumber uint32, issueConfirmedNotificationsType uint16, issueConfirmedNotificationsInstanceNumber uint32, lifetimeLength uint8, lifetimeSeconds []int8, notifications []*BACnetTagWithContent) *BACnetConfirmedServiceRequest {
	child := &BACnetConfirmedServiceRequestConfirmedCOVNotification{
		SubscriberProcessIdentifier:               subscriberProcessIdentifier,
		MonitoredObjectType:                       monitoredObjectType,
		MonitoredObjectInstanceNumber:             monitoredObjectInstanceNumber,
		IssueConfirmedNotificationsType:           issueConfirmedNotificationsType,
		IssueConfirmedNotificationsInstanceNumber: issueConfirmedNotificationsInstanceNumber,
		LifetimeLength:                            lifetimeLength,
		LifetimeSeconds:                           lifetimeSeconds,
		Notifications:                             notifications,
		Parent:                                    NewBACnetConfirmedServiceRequest(),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastBACnetConfirmedServiceRequestConfirmedCOVNotification(structType interface{}) *BACnetConfirmedServiceRequestConfirmedCOVNotification {
	castFunc := func(typ interface{}) *BACnetConfirmedServiceRequestConfirmedCOVNotification {
		if casted, ok := typ.(BACnetConfirmedServiceRequestConfirmedCOVNotification); ok {
			return &casted
		}
		if casted, ok := typ.(*BACnetConfirmedServiceRequestConfirmedCOVNotification); ok {
			return casted
		}
		if casted, ok := typ.(BACnetConfirmedServiceRequest); ok {
			return CastBACnetConfirmedServiceRequestConfirmedCOVNotification(casted.Child)
		}
		if casted, ok := typ.(*BACnetConfirmedServiceRequest); ok {
			return CastBACnetConfirmedServiceRequestConfirmedCOVNotification(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) GetTypeName() string {
	return "BACnetConfirmedServiceRequestConfirmedCOVNotification"
}

func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) LengthInBits() uint16 {
	return m.LengthInBitsConditional(false)
}

func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) LengthInBitsConditional(lastItem bool) uint16 {
	lengthInBits := uint16(m.Parent.ParentLengthInBits())

	// Const Field (subscriberProcessIdentifierHeader)
	lengthInBits += 8

	// Simple field (subscriberProcessIdentifier)
	lengthInBits += 8

	// Const Field (monitoredObjectIdentifierHeader)
	lengthInBits += 8

	// Simple field (monitoredObjectType)
	lengthInBits += 10

	// Simple field (monitoredObjectInstanceNumber)
	lengthInBits += 22

	// Const Field (issueConfirmedNotificationsHeader)
	lengthInBits += 8

	// Simple field (issueConfirmedNotificationsType)
	lengthInBits += 10

	// Simple field (issueConfirmedNotificationsInstanceNumber)
	lengthInBits += 22

	// Const Field (lifetimeHeader)
	lengthInBits += 5

	// Simple field (lifetimeLength)
	lengthInBits += 3

	// Array field
	if len(m.LifetimeSeconds) > 0 {
		lengthInBits += 8 * uint16(len(m.LifetimeSeconds))
	}

	// Const Field (listOfValuesOpeningTag)
	lengthInBits += 8

	// Array field
	if len(m.Notifications) > 0 {
		for _, element := range m.Notifications {
			lengthInBits += element.LengthInBits()
		}
	}

	// Const Field (listOfValuesClosingTag)
	lengthInBits += 8

	return lengthInBits
}

func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func BACnetConfirmedServiceRequestConfirmedCOVNotificationParse(readBuffer utils.ReadBuffer, len uint16) (*BACnetConfirmedServiceRequest, error) {
	if pullErr := readBuffer.PullContext("BACnetConfirmedServiceRequestConfirmedCOVNotification"); pullErr != nil {
		return nil, pullErr
	}

	// Const Field (subscriberProcessIdentifierHeader)
	subscriberProcessIdentifierHeader, _subscriberProcessIdentifierHeaderErr := readBuffer.ReadUint8("subscriberProcessIdentifierHeader", 8)
	if _subscriberProcessIdentifierHeaderErr != nil {
		return nil, errors.Wrap(_subscriberProcessIdentifierHeaderErr, "Error parsing 'subscriberProcessIdentifierHeader' field")
	}
	if subscriberProcessIdentifierHeader != BACnetConfirmedServiceRequestConfirmedCOVNotification_SUBSCRIBERPROCESSIDENTIFIERHEADER {
		return nil, errors.New("Expected constant value " + fmt.Sprintf("%d", BACnetConfirmedServiceRequestConfirmedCOVNotification_SUBSCRIBERPROCESSIDENTIFIERHEADER) + " but got " + fmt.Sprintf("%d", subscriberProcessIdentifierHeader))
	}

	// Simple Field (subscriberProcessIdentifier)
	subscriberProcessIdentifier, _subscriberProcessIdentifierErr := readBuffer.ReadUint8("subscriberProcessIdentifier", 8)
	if _subscriberProcessIdentifierErr != nil {
		return nil, errors.Wrap(_subscriberProcessIdentifierErr, "Error parsing 'subscriberProcessIdentifier' field")
	}

	// Const Field (monitoredObjectIdentifierHeader)
	monitoredObjectIdentifierHeader, _monitoredObjectIdentifierHeaderErr := readBuffer.ReadUint8("monitoredObjectIdentifierHeader", 8)
	if _monitoredObjectIdentifierHeaderErr != nil {
		return nil, errors.Wrap(_monitoredObjectIdentifierHeaderErr, "Error parsing 'monitoredObjectIdentifierHeader' field")
	}
	if monitoredObjectIdentifierHeader != BACnetConfirmedServiceRequestConfirmedCOVNotification_MONITOREDOBJECTIDENTIFIERHEADER {
		return nil, errors.New("Expected constant value " + fmt.Sprintf("%d", BACnetConfirmedServiceRequestConfirmedCOVNotification_MONITOREDOBJECTIDENTIFIERHEADER) + " but got " + fmt.Sprintf("%d", monitoredObjectIdentifierHeader))
	}

	// Simple Field (monitoredObjectType)
	monitoredObjectType, _monitoredObjectTypeErr := readBuffer.ReadUint16("monitoredObjectType", 10)
	if _monitoredObjectTypeErr != nil {
		return nil, errors.Wrap(_monitoredObjectTypeErr, "Error parsing 'monitoredObjectType' field")
	}

	// Simple Field (monitoredObjectInstanceNumber)
	monitoredObjectInstanceNumber, _monitoredObjectInstanceNumberErr := readBuffer.ReadUint32("monitoredObjectInstanceNumber", 22)
	if _monitoredObjectInstanceNumberErr != nil {
		return nil, errors.Wrap(_monitoredObjectInstanceNumberErr, "Error parsing 'monitoredObjectInstanceNumber' field")
	}

	// Const Field (issueConfirmedNotificationsHeader)
	issueConfirmedNotificationsHeader, _issueConfirmedNotificationsHeaderErr := readBuffer.ReadUint8("issueConfirmedNotificationsHeader", 8)
	if _issueConfirmedNotificationsHeaderErr != nil {
		return nil, errors.Wrap(_issueConfirmedNotificationsHeaderErr, "Error parsing 'issueConfirmedNotificationsHeader' field")
	}
	if issueConfirmedNotificationsHeader != BACnetConfirmedServiceRequestConfirmedCOVNotification_ISSUECONFIRMEDNOTIFICATIONSHEADER {
		return nil, errors.New("Expected constant value " + fmt.Sprintf("%d", BACnetConfirmedServiceRequestConfirmedCOVNotification_ISSUECONFIRMEDNOTIFICATIONSHEADER) + " but got " + fmt.Sprintf("%d", issueConfirmedNotificationsHeader))
	}

	// Simple Field (issueConfirmedNotificationsType)
	issueConfirmedNotificationsType, _issueConfirmedNotificationsTypeErr := readBuffer.ReadUint16("issueConfirmedNotificationsType", 10)
	if _issueConfirmedNotificationsTypeErr != nil {
		return nil, errors.Wrap(_issueConfirmedNotificationsTypeErr, "Error parsing 'issueConfirmedNotificationsType' field")
	}

	// Simple Field (issueConfirmedNotificationsInstanceNumber)
	issueConfirmedNotificationsInstanceNumber, _issueConfirmedNotificationsInstanceNumberErr := readBuffer.ReadUint32("issueConfirmedNotificationsInstanceNumber", 22)
	if _issueConfirmedNotificationsInstanceNumberErr != nil {
		return nil, errors.Wrap(_issueConfirmedNotificationsInstanceNumberErr, "Error parsing 'issueConfirmedNotificationsInstanceNumber' field")
	}

	// Const Field (lifetimeHeader)
	lifetimeHeader, _lifetimeHeaderErr := readBuffer.ReadUint8("lifetimeHeader", 5)
	if _lifetimeHeaderErr != nil {
		return nil, errors.Wrap(_lifetimeHeaderErr, "Error parsing 'lifetimeHeader' field")
	}
	if lifetimeHeader != BACnetConfirmedServiceRequestConfirmedCOVNotification_LIFETIMEHEADER {
		return nil, errors.New("Expected constant value " + fmt.Sprintf("%d", BACnetConfirmedServiceRequestConfirmedCOVNotification_LIFETIMEHEADER) + " but got " + fmt.Sprintf("%d", lifetimeHeader))
	}

	// Simple Field (lifetimeLength)
	lifetimeLength, _lifetimeLengthErr := readBuffer.ReadUint8("lifetimeLength", 3)
	if _lifetimeLengthErr != nil {
		return nil, errors.Wrap(_lifetimeLengthErr, "Error parsing 'lifetimeLength' field")
	}

	// Array field (lifetimeSeconds)
	if pullErr := readBuffer.PullContext("lifetimeSeconds", utils.WithRenderAsList(true)); pullErr != nil {
		return nil, pullErr
	}
	// Count array
	lifetimeSeconds := make([]int8, lifetimeLength)
	for curItem := uint16(0); curItem < uint16(lifetimeLength); curItem++ {
		_item, _err := readBuffer.ReadInt8("", 8)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'lifetimeSeconds' field")
		}
		lifetimeSeconds[curItem] = _item
	}
	if closeErr := readBuffer.CloseContext("lifetimeSeconds", utils.WithRenderAsList(true)); closeErr != nil {
		return nil, closeErr
	}

	// Const Field (listOfValuesOpeningTag)
	listOfValuesOpeningTag, _listOfValuesOpeningTagErr := readBuffer.ReadUint8("listOfValuesOpeningTag", 8)
	if _listOfValuesOpeningTagErr != nil {
		return nil, errors.Wrap(_listOfValuesOpeningTagErr, "Error parsing 'listOfValuesOpeningTag' field")
	}
	if listOfValuesOpeningTag != BACnetConfirmedServiceRequestConfirmedCOVNotification_LISTOFVALUESOPENINGTAG {
		return nil, errors.New("Expected constant value " + fmt.Sprintf("%d", BACnetConfirmedServiceRequestConfirmedCOVNotification_LISTOFVALUESOPENINGTAG) + " but got " + fmt.Sprintf("%d", listOfValuesOpeningTag))
	}

	// Array field (notifications)
	if pullErr := readBuffer.PullContext("notifications", utils.WithRenderAsList(true)); pullErr != nil {
		return nil, pullErr
	}
	// Length array
	notifications := make([]*BACnetTagWithContent, 0)
	_notificationsLength := uint16(len) - uint16(uint16(18))
	_notificationsEndPos := readBuffer.GetPos() + uint16(_notificationsLength)
	for readBuffer.GetPos() < _notificationsEndPos {
		_item, _err := BACnetTagWithContentParse(readBuffer)
		if _err != nil {
			return nil, errors.Wrap(_err, "Error parsing 'notifications' field")
		}
		notifications = append(notifications, _item)
	}
	if closeErr := readBuffer.CloseContext("notifications", utils.WithRenderAsList(true)); closeErr != nil {
		return nil, closeErr
	}

	// Const Field (listOfValuesClosingTag)
	listOfValuesClosingTag, _listOfValuesClosingTagErr := readBuffer.ReadUint8("listOfValuesClosingTag", 8)
	if _listOfValuesClosingTagErr != nil {
		return nil, errors.Wrap(_listOfValuesClosingTagErr, "Error parsing 'listOfValuesClosingTag' field")
	}
	if listOfValuesClosingTag != BACnetConfirmedServiceRequestConfirmedCOVNotification_LISTOFVALUESCLOSINGTAG {
		return nil, errors.New("Expected constant value " + fmt.Sprintf("%d", BACnetConfirmedServiceRequestConfirmedCOVNotification_LISTOFVALUESCLOSINGTAG) + " but got " + fmt.Sprintf("%d", listOfValuesClosingTag))
	}

	if closeErr := readBuffer.CloseContext("BACnetConfirmedServiceRequestConfirmedCOVNotification"); closeErr != nil {
		return nil, closeErr
	}

	// Create a partially initialized instance
	_child := &BACnetConfirmedServiceRequestConfirmedCOVNotification{
		SubscriberProcessIdentifier:               subscriberProcessIdentifier,
		MonitoredObjectType:                       monitoredObjectType,
		MonitoredObjectInstanceNumber:             monitoredObjectInstanceNumber,
		IssueConfirmedNotificationsType:           issueConfirmedNotificationsType,
		IssueConfirmedNotificationsInstanceNumber: issueConfirmedNotificationsInstanceNumber,
		LifetimeLength:                            lifetimeLength,
		LifetimeSeconds:                           lifetimeSeconds,
		Notifications:                             notifications,
		Parent:                                    &BACnetConfirmedServiceRequest{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) Serialize(writeBuffer utils.WriteBuffer) error {
	ser := func() error {
		if pushErr := writeBuffer.PushContext("BACnetConfirmedServiceRequestConfirmedCOVNotification"); pushErr != nil {
			return pushErr
		}

		// Const Field (subscriberProcessIdentifierHeader)
		_subscriberProcessIdentifierHeaderErr := writeBuffer.WriteUint8("subscriberProcessIdentifierHeader", 8, 0x09)
		if _subscriberProcessIdentifierHeaderErr != nil {
			return errors.Wrap(_subscriberProcessIdentifierHeaderErr, "Error serializing 'subscriberProcessIdentifierHeader' field")
		}

		// Simple Field (subscriberProcessIdentifier)
		subscriberProcessIdentifier := uint8(m.SubscriberProcessIdentifier)
		_subscriberProcessIdentifierErr := writeBuffer.WriteUint8("subscriberProcessIdentifier", 8, (subscriberProcessIdentifier))
		if _subscriberProcessIdentifierErr != nil {
			return errors.Wrap(_subscriberProcessIdentifierErr, "Error serializing 'subscriberProcessIdentifier' field")
		}

		// Const Field (monitoredObjectIdentifierHeader)
		_monitoredObjectIdentifierHeaderErr := writeBuffer.WriteUint8("monitoredObjectIdentifierHeader", 8, 0x1C)
		if _monitoredObjectIdentifierHeaderErr != nil {
			return errors.Wrap(_monitoredObjectIdentifierHeaderErr, "Error serializing 'monitoredObjectIdentifierHeader' field")
		}

		// Simple Field (monitoredObjectType)
		monitoredObjectType := uint16(m.MonitoredObjectType)
		_monitoredObjectTypeErr := writeBuffer.WriteUint16("monitoredObjectType", 10, (monitoredObjectType))
		if _monitoredObjectTypeErr != nil {
			return errors.Wrap(_monitoredObjectTypeErr, "Error serializing 'monitoredObjectType' field")
		}

		// Simple Field (monitoredObjectInstanceNumber)
		monitoredObjectInstanceNumber := uint32(m.MonitoredObjectInstanceNumber)
		_monitoredObjectInstanceNumberErr := writeBuffer.WriteUint32("monitoredObjectInstanceNumber", 22, (monitoredObjectInstanceNumber))
		if _monitoredObjectInstanceNumberErr != nil {
			return errors.Wrap(_monitoredObjectInstanceNumberErr, "Error serializing 'monitoredObjectInstanceNumber' field")
		}

		// Const Field (issueConfirmedNotificationsHeader)
		_issueConfirmedNotificationsHeaderErr := writeBuffer.WriteUint8("issueConfirmedNotificationsHeader", 8, 0x2C)
		if _issueConfirmedNotificationsHeaderErr != nil {
			return errors.Wrap(_issueConfirmedNotificationsHeaderErr, "Error serializing 'issueConfirmedNotificationsHeader' field")
		}

		// Simple Field (issueConfirmedNotificationsType)
		issueConfirmedNotificationsType := uint16(m.IssueConfirmedNotificationsType)
		_issueConfirmedNotificationsTypeErr := writeBuffer.WriteUint16("issueConfirmedNotificationsType", 10, (issueConfirmedNotificationsType))
		if _issueConfirmedNotificationsTypeErr != nil {
			return errors.Wrap(_issueConfirmedNotificationsTypeErr, "Error serializing 'issueConfirmedNotificationsType' field")
		}

		// Simple Field (issueConfirmedNotificationsInstanceNumber)
		issueConfirmedNotificationsInstanceNumber := uint32(m.IssueConfirmedNotificationsInstanceNumber)
		_issueConfirmedNotificationsInstanceNumberErr := writeBuffer.WriteUint32("issueConfirmedNotificationsInstanceNumber", 22, (issueConfirmedNotificationsInstanceNumber))
		if _issueConfirmedNotificationsInstanceNumberErr != nil {
			return errors.Wrap(_issueConfirmedNotificationsInstanceNumberErr, "Error serializing 'issueConfirmedNotificationsInstanceNumber' field")
		}

		// Const Field (lifetimeHeader)
		_lifetimeHeaderErr := writeBuffer.WriteUint8("lifetimeHeader", 5, 0x07)
		if _lifetimeHeaderErr != nil {
			return errors.Wrap(_lifetimeHeaderErr, "Error serializing 'lifetimeHeader' field")
		}

		// Simple Field (lifetimeLength)
		lifetimeLength := uint8(m.LifetimeLength)
		_lifetimeLengthErr := writeBuffer.WriteUint8("lifetimeLength", 3, (lifetimeLength))
		if _lifetimeLengthErr != nil {
			return errors.Wrap(_lifetimeLengthErr, "Error serializing 'lifetimeLength' field")
		}

		// Array Field (lifetimeSeconds)
		if m.LifetimeSeconds != nil {
			if pushErr := writeBuffer.PushContext("lifetimeSeconds", utils.WithRenderAsList(true)); pushErr != nil {
				return pushErr
			}
			for _, _element := range m.LifetimeSeconds {
				_elementErr := writeBuffer.WriteInt8("", 8, _element)
				if _elementErr != nil {
					return errors.Wrap(_elementErr, "Error serializing 'lifetimeSeconds' field")
				}
			}
			if popErr := writeBuffer.PopContext("lifetimeSeconds", utils.WithRenderAsList(true)); popErr != nil {
				return popErr
			}
		}

		// Const Field (listOfValuesOpeningTag)
		_listOfValuesOpeningTagErr := writeBuffer.WriteUint8("listOfValuesOpeningTag", 8, 0x4E)
		if _listOfValuesOpeningTagErr != nil {
			return errors.Wrap(_listOfValuesOpeningTagErr, "Error serializing 'listOfValuesOpeningTag' field")
		}

		// Array Field (notifications)
		if m.Notifications != nil {
			if pushErr := writeBuffer.PushContext("notifications", utils.WithRenderAsList(true)); pushErr != nil {
				return pushErr
			}
			for _, _element := range m.Notifications {
				_elementErr := _element.Serialize(writeBuffer)
				if _elementErr != nil {
					return errors.Wrap(_elementErr, "Error serializing 'notifications' field")
				}
			}
			if popErr := writeBuffer.PopContext("notifications", utils.WithRenderAsList(true)); popErr != nil {
				return popErr
			}
		}

		// Const Field (listOfValuesClosingTag)
		_listOfValuesClosingTagErr := writeBuffer.WriteUint8("listOfValuesClosingTag", 8, 0x4F)
		if _listOfValuesClosingTagErr != nil {
			return errors.Wrap(_listOfValuesClosingTagErr, "Error serializing 'listOfValuesClosingTag' field")
		}

		if popErr := writeBuffer.PopContext("BACnetConfirmedServiceRequestConfirmedCOVNotification"); popErr != nil {
			return popErr
		}
		return nil
	}
	return m.Parent.SerializeParent(writeBuffer, m, ser)
}

func (m *BACnetConfirmedServiceRequestConfirmedCOVNotification) String() string {
	if m == nil {
		return "<nil>"
	}
	buffer := utils.NewBoxedWriteBufferWithOptions(true, true)
	m.Serialize(buffer)
	return buffer.GetBox().String()
}