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

package readwrite

import (
	"github.com/apache/plc4x/plc4go/internal/plc4go/eip/readwrite/model"
	"github.com/apache/plc4x/plc4go/internal/plc4go/spi/utils"
	"github.com/pkg/errors"
	"strconv"
	"strings"
)

// Code generated by code-generation. DO NOT EDIT.

type EipXmlParserHelper struct {
}

// Temporary imports to silent compiler warnings (TODO: migrate from static to emission based imports)
func init() {
	_ = strconv.ParseUint
	_ = strconv.ParseInt
	_ = strings.Join
	_ = utils.Dump
}

func (m EipXmlParserHelper) Parse(typeName string, xmlString string, parserArguments ...string) (interface{}, error) {
	switch typeName {
	case "CipService":
		parsedUint0, err := strconv.ParseUint(parserArguments[0], 10, 16)
		if err != nil {
			return nil, err
		}
		serviceLen := uint16(parsedUint0)
		return model.CipServiceParse(utils.NewXmlReadBuffer(strings.NewReader(xmlString)), serviceLen)
	case "EipPacket":
		return model.EipPacketParse(utils.NewXmlReadBuffer(strings.NewReader(xmlString)))
	case "Services":
		parsedUint0, err := strconv.ParseUint(parserArguments[0], 10, 16)
		if err != nil {
			return nil, err
		}
		servicesLen := uint16(parsedUint0)
		return model.ServicesParse(utils.NewXmlReadBuffer(strings.NewReader(xmlString)), servicesLen)
	case "CipExchange":
		parsedUint0, err := strconv.ParseUint(parserArguments[0], 10, 16)
		if err != nil {
			return nil, err
		}
		exchangeLen := uint16(parsedUint0)
		return model.CipExchangeParse(utils.NewXmlReadBuffer(strings.NewReader(xmlString)), exchangeLen)
	}
	return nil, errors.Errorf("Unsupported type %s", typeName)
}
