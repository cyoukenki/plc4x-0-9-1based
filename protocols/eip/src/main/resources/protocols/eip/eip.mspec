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

//////////////////////////////////////////////////////////////////
///EthernetIP Header of size 24
/////////////////////////////////////////////////////////////////
//6f 00 17 00 6d 01 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 000000000000020000000000b2000700d2000101040201
[discriminatedType 'EipPacket'
    [discriminator uint 16 'command']
    [implicit      uint 16 'len' 'lengthInBytes - 24']
    [simple        uint 32 'sessionHandle']
    [simple        uint 32 'status']
    [array         uint 8  'senderContext' count '8']
    [simple        uint 32 'options']
    [typeSwitch 'command'
            ['0x0065' EipConnectionRequest
                [const  uint    16   'protocolVersion'   '0x01']
                [const  uint    16   'flags'             '0x00']
            ]
            ['0x0066' EipDisconnectRequest
            ]
            ['0x006F' CipRRData [uint  16  'len']
                [reserved  uint    32    '0x00000000']
                [reserved  uint    16    '0x0000']
                [simple    CipExchange   'exchange' ['len-6']]
            ]
            ['0x0070' SendUnitData [uint 16 'len']
                [const uint 32 'api_handle' '0x00']
                [const uint 16 'timeout' '0x00']
                [const uint 16 'item_count' '0x0002']
                [const uint 16 'connection_address' '0x00a1']
                [const uint 16 'connection_length' '0x0004']
                [simple uint 32 'o_t_connection_id']
                [const uint 16 'connection_data_item' '0x00b1']
                [simple    CipExchange3   'exchange3' ['len-12']]   
            ]
        ]
]
[type  'CipExchange3' [uint 16 'exchangeLen']
        [implicit uint 16 'size' 'lengthInBytes - 2']
        [simple uint 16 'sequence_count']
        [simple         CipService          'service' ['exchangeLen - 10'] ]
]
[type  'CipExchange' [uint 16 'exchangeLen']  //We pass then length down to evey sub-type to be able to provide the remaining data size
    [const          uint        16      'itemCount'           '0x02']                 //2 items
    [const          uint        32      'nullPtr'             '0x0']                    //NullPointerAddress
    [const          uint        16      'UnconnectedData'     '0x00B2']                 //Connection Manager
    [implicit       uint        16      'size'                'lengthInBytes - 8 - 2']  //remove fields above and routing
    [simple         CipService          'service' ['exchangeLen - 10'] ]
]
[type 'ReadResponseContent' [uint   16   'len']
    [discriminator     uint   8   'status']
    [typeSwitch 'status'
        ['0x00' ResponseOk [uint   16   'len']
            [simple uint 8 'extStatus']
            [enum       CIPDataTypeCode     'dataType']
            [array      int   8   'data'  count  'len-6']
        ]
        ['0x04' ResponseError
             [reserved   uint     8   '0x00']
        ]
    ]
]
[discriminatedType  'CipService' [uint 16 'serviceLen']
    [discriminator  uint    8   'service']
    [typeSwitch 'service'
        ['0x4C' CipReadRequest
            [simple     int     8   'RequestPathSize']
            [array      int     8   'tag'   length  '(RequestPathSize*2)']
            [simple     uint    16  'elementNb']
        ]
        ['0xCC' CipReadResponse [uint 16 'serviceLen']
              [reserved   uint            8   '0x00']
              [simple     ReadResponseContent  'readResponseContent' ['serviceLen']]
        ]
        //6f0014006d01010000000000000000000000000000000000000000000000020000000000b2000400cc 00 04 00
        ['0x4D' CipWriteRequest
            [simple     int     8           'RequestPathSize']
            [array      int     8           'tag'   length  '(RequestPathSize*2)']
            [enum       CIPDataTypeCode     'dataType']
            [simple     uint    16          'elementNb']
            [array      int    8            'data'  length  'dataType.size*elementNb']
        ]
        ['0xCD' CipWriteResponse
            [reserved   uint        8   '0x00']
            [simple     uint        8   'status']
            [simple     uint        8   'extStatus']
        ]
        ['0x0A' MultipleServiceRequest [uint 16 'serviceLen']
               [const  int     8   'RequestPathSize'   '0x02']
               [const  uint    32  'RequestPath'       '0x01240220']   //Logical Segment: Class(0x20) 0x02, Instance(0x24) 01 (Message Router)
               [simple Services  'data'         ['serviceLen - 6 '] ]
        ]
        ['0x8A' MultipleServiceResponse [uint 16 'serviceLen']
               [reserved   uint    8   '0x0']
               [simple     uint    8   'status']
               [simple     uint    8   'extStatus']
               [simple     uint    16  'serviceNb']
               [array      uint    16  'offsets'       count  'serviceNb']
               [array      int     8   'servicesData' count 'serviceLen-6-(2*serviceNb)']
        ]
        ['0x52'  CipUnconnectedRequest
               [reserved   uint    8   '0x02']
               [reserved   uint    8   '0x20']   // setRequestPathLogicalClassSegment
               [reserved   uint    8   '0x06']   // set request class path
               [reserved   uint    8   '0x24']   // setRequestPathLogicalInstanceSegment
               [reserved   uint    8   '0x01']   // setRequestPathInstance
               [reserved   uint    16  '0x9D06']   //  5(32ms)   6(64ms)   7(128ms)
               [implicit   uint    16  'messageSize'   'lengthInBytes - 10 - 4']   //subtract above and routing
               [simple     CipService  'unconnectedService' ['messageSize'] ]
               [const      uint    16  'route' '0x0001']
               [simple     int     8   'backPlane']
               [simple     int     8   'slot']
        ]
        //class 3
         ['0x5b' LargeForwardOpenRequest
                [const uint 8 'request_path_size' '0x02']
                [const uint 32 'request_path' '0x01240620']
                [const uint 8 'ptt' '0x06']
                [const uint 8 'tot' '0x9c'] //actual timeout = 2^6 * 9c = 9984ms
                [simple uint 32 'o_t_id']
                [simple uint 32 't_o_id']
                [simple uint 16 'connection_serial_number']
                [const uint 16 'vendor_id' '0x002f']
                [simple uint 32 'originator_serial_number']
                [const uint 8 'trt' '0x00']
                [reserved uint 16 '0x00']
                [reserved uint 8 '0x00']
               // [const uint 32 'otrpi' '0x00002710'] //10ms
                 [const uint 32 'otrpi' '0x00989680'] //10000ms = 0x989680 / 1000 ms
                [const uint 32 'otnp' '0x420007cc']
                // [const uint 32 'torpi' '0x00002710']
                 [const uint 32 'torpi' '0x00989680']
                [const uint 32 'tonp' '0x420007cc']
                [const uint 8 'transport_type' '0xa3']
                [const uint 8 'connection_path_size' '0x02']
                [const uint 32 'connection_path' '0x01240220']
        ]
         ['0xdb' LargeForwardOpenResponse
                [reserved uint 8 '0x00']
                [simple uint 16 'open_status']
                [simple uint 32 'o_t_connection_id']
                [simple uint 32 't_o_id']
                [simple uint 16 'connection_serial_number']
                [simple uint 16 'vendor_id']
                [simple uint 32 'originator_serial_number']
                [simple uint 32 'o_t_rpi']
                [simple uint 32 't_o_rpi']
                [reserved uint 16 '0x00']
        ]
        ['0x4e' CloseRequest
                [const uint 8 'request_path_size' '0x02']
                [const uint 32 'request_path' '0x01240620']
                [const uint 8 'ptt' '0x06']
                [const uint 8 'tot' '0x9c']
                [simple uint 16 'connection_serial_number']
                [const uint 16 'vendor_id' '0x002f']
                [simple uint 32 'originator_serial_number']
                [const uint 8 'connection_path_size' '0x02']
                [reserved uint 8 '0x00']
                [const uint 32 'connection_path' '0x01240620']
        ]
        [DefaultUnKnownStreamFunction]
    ]
]

[type   'Services'  [uint   16   'servicesLen']
    [simple uint        16  'serviceNb']
    [array  uint        16  'offsets'       count  'serviceNb']
    [array  CipService   'services'      count  'serviceNb' ['servicesLen/serviceNb'] ]
]

[enum uint   16   'CIPDataTypeCode' [uint 8  'size']
    ['0X00C1'   BOOL            ['1']]
    ['0X00C2'   SINT            ['1']]
    ['0X00C3'   INT             ['2']]
    ['0X00C4'   DINT            ['4']]
    ['0X00C5'   LINT            ['8']]
    ['0X00C6'   USINT           ['1']]
    ['0X00C7'   UINT            ['2']]
    ['0X00C8'   UDINT           ['4']] 
    ['0X00C9'   ULINT           ['8']] 

    ['0X00CA'   REAL            ['4']]
    ['0X00CB'   LREAL           ['8']] 
   // ['0X00CC'   STIME           ['XX']]
   // ['0X00CD'   DATE            ['XX']]
  //  ['0X00CE'   TIME_OF_DAY     ['XX']]
   // ['0X00CF'   DATE_AND_TIME   ['XX']]
   // ['0X000A'   DATE_AND_TIME   ['20']]
    ['0X00D0'   STRING          ['1']]
    ['0X00D1'   BYTE            ['1']]
    ['0X00D2'   WORD            ['2']]
    ['0X00D3'   DWORD           ['4']]
    ['0X00D4'   LWORD           ['8']]
    ['0X00D5'   STRING2         ['2']]
   // ['0X00D6'   FTIME           ['xx']]
   // ['0X00D7'   LTIME           ['xx']]
   // ['0X00D8'   ITIME           ['xx']]
    ['0X00D9'   STRINGN         ['N']]
    ['0X00DA'   SHORT_STRING    ['1']]
   // ['0X00DB'   TIME            ['xx']]
    //['0X00DC'   EPATH           ['xx']]
    //['0X00DD'   ENGUNIT         ['xx']]

   ['0X02A0'   STRUCTURED      ['88']]
   // ['0X02A0'   STRING          ['88']]
   // ['0X02A0'   STRING36        ['40']]
    //TODO: -1 is not a valid value for uint
    //['-1'       UNKNOWN         ['-1']]
   
]

[enum   uint    16  'EiPCommand'
    ['0x0065'   RegisterSession ]
    ['0x0066'   UnregisterSession ]
    ['0x006F'   SendRRData ]
]