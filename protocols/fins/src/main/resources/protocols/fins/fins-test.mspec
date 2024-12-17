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
// Remark: The different fields are encoded in Big-endian.

[discriminatedType 'FinsPacket'
    [const  uint    32   'finsHeader'   '0x46494E53']
    [implicit      uint 32 'len' 'lengthInBytes - 8']
    [discriminator uint 32 'command'] //0x00000001
    [simple uint 32 'errorCode']
    [simple uint 8 'ICF']
    [simple uint 8 'RSV']
    [simple uint 8 'VCT']
    [simple uint 8 'DNA']
    //DA1
    [typeSwitch 'command'
        ['0x00000000' handshakeReq     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
        ]
        ['0x00000001' handshakeRes     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                [simple uint 8 'DA1']
                [simple uint 8 'DA2']
                [simple uint 8 'SNA']
                [simple uint 8 'SA1']
        ]

        ['0x00000002' communicationMessage     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                [simple uint 8 'DA1']
                [simple uint 8 'DA2']
                [simple uint 8 'SNA']
                [simple uint 8 'SA1']
                [simple uint 8 'SA2']
                [simple uint 8 'SID']
                [discriminator uint 16 'finsCommand']

                [typeSwitch 'finsCommand'
                    ['0x0101' readBitsReq                                     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                        [enum   FinsDataTypeCode 'dataType']
                        [simple uint 16 'wordStart']
                        [simple uint 8  'bitsStart']
                        [simple uint 16 'lenght']
                    ]

                    ['0x0101' readBitsRsp  [uint 16 'len']    // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                        [simple uint 16 'endCode']
                        [array  uint 8  'data' count  'len - 2']
                    ]

                    ['0x0102' WriteBitsReq     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                        [enum   FinsDataTypeCode 'dataType']
                        [simple uint 16 'wordStart']
                        [simple uint 8  'bitsStart']
                        [simple uint 16 'lenght']
                        [array  uint 8 'data'   count  'lenght']
                    ]

                    ['0x0102' WriteBitsRsp     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                        [simple uint 16 'endCode']
                    ]
                ]
        ]

    ]
]   // 包含type switch
[enum  uint 8 'FinsDataTypeCode'  [uint 8  'size']
    ['0XB0'   CIO            ['1']]
    ['0XB1'   W              ['1']]
    ['0XB2'   H              ['1']]
    ['0XB3'   A              ['1']]
    ['0X82'   D              ['1']]
]