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

[discriminatedType 'EipPacket'
    [discriminator uint 16 'command']
    [implicit      uint 16 'len' 'lengthInBytes - 24']
    [simple        uint 32 'sessionHandle']
    [simple        uint 32 'status']
    [array         uint 8  'senderContext' count '8']
    [simple        uint 32 'options']
    [typeSwitch 'command'
            ['0x0070' SendUnitData [uint 16 'len']
                [const uint 32 'api_handle' '0x00']
                [const uint 16 'timeout' '0x00']
                [const uint 16 'item_count' '0x0002']
                [const uint 16 'connection_address' '0x00a1']
                [const uint 16 'connection_length' '0x0004']
                [simple uint 32 'o_t_connection_id']
                [const uint 16 'connection_data_item' '0x00b1']
                [simple    CipExchange3   'exchange3' ['len-6']]   
            ]
        ]
]
[type  'CipExchange3' [uint 16 'exchangeLen']
        [implicit uint 16 'size' 'lengthInBytes - 44']
        [simple uint 16 'sequence_count']
        [simple         CipService          'service' ['exchangeLen - 10'] ]
]
[discriminatedType  'CipService' [uint 16 'serviceLen']
    [discriminator  uint    8   'service']
    [typeSwitch 'service'
       
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
    ]
]



