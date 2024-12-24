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
    [implicit uint 32 'len' 'lengthInBytes-8']
    [discriminator uint 32 'command'] //0x00000001
    [simple uint 32 'errorCode']
    [simple uint 8 'ICF']
    [simple uint 8 'RSV']
    [simple uint 8 'GCT']
    [simple uint 8 'DNA']

    //DA1

    [typeSwitch 'command'
        ['0x00000000' HandshakeReq     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
        ]

        ['0x00000001' HandshakeRes     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                [simple uint 8 'DA1']
                [simple uint 8 'DA2']
                [simple uint 8 'SNA']
                [simple uint 8 'SA1']
        ]

        ['0x00000002' CommunicationMessage [uint  32  'len'] // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
            [simple uint 8 'DA1']
            [simple uint 8 'DA2']
            [simple uint 8 'SNA']
            [simple uint 8 'SA1']
            [simple uint 8 'SA2']
            [simple uint 8 'SID']
            [simple  CommandDataStruct 'commandDataStruct' ['len-18']]
        ]
    ]
]


[type 'CommandDataStruct' [int  32  'commandDataStructLen']
        [discriminator uint 16 'finsCommand']
        [typeSwitch 'finsCommand'

            ['0x0101' ReadBitsRes [int  32  'commandDataStructLen']
                [simple uint 16 'endCode']
                [array  int  8  'data'  length 'commandDataStructLen - 4']
            ]
            ['0x0101' ReadBitsReq
                [enum   FinsDataTypeCode 'dataType']
                [simple uint 16 'wordStart']
                [simple uint 8  'bitsStart']
                [simple uint 16 'lenght']
            ]
            ['0x0102' WriteBitsRes     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
                [simple uint 16 'endCode']
            ]
            ['0x0102' WriteBitsReq
                [enum   FinsDataTypeCode 'dataType']
                [simple uint 16 'wordStart']
                [simple uint 8  'bitsStart']
                [simple uint 16 'lenght']
                [array  int 8 'data'   count  'lenght']
            ]

        ]
]
[discriminatedType 'FinsUdpPacket'
    [simple uint 8 'ICF']
    [const uint 8 'RSV' '0x00']
    [const uint 8 'GCT' '0x02']
    [const uint 8 'DNA' '0x00']
    [simple uint 8 'DA1'] //The address node of PLc(last of ip address)
    [const uint 8 'DA2' '0x00']
    [const uint 8 'SNA' '0x00']
    [simple uint 8 'SA1']//The address node of local pc(last of ip address)
    [const uint 8 'SA2' '0x00']
    [simple uint 8 'SID']
    [discriminator uint 16 'finsCommand']
    [typeSwitch 'finsCommand'

        ['0x0101' UdpReadBitsRes
            [simple uint 16 'endCode']
            [array  int  8  'data'  length 'readBuffer.TotalBytes() - 14']
        ]
        ['0x0101' UdpReadBitsReq
            [enum   FinsDataTypeCode 'dataType']
            [simple uint 16 'wordStart']
            [simple uint 8  'bitsStart']
            [simple uint 16 'lenght']
        ]
        ['0x0102' UdpWriteBitsRes     // 1 byte = 8 bits   res mini 1 byte  我要读3个bit
            [simple uint 16 'endCode']
        ]
        ['0x0102' UdpWriteBitsReq
            [enum   FinsDataTypeCode 'dataType']
            [simple uint 16 'wordStart']
            [simple uint 8  'bitsStart']
            [simple uint 16 'lenght']
            [array  int 8 'data'   count  'lenght']
        ]

    ]
]


[enum  uint 8 'FinsDataTypeCode'  [uint 8  'size']
    ['0X02'   DB            ['1']]                       //DMArea  Bit
    ['0X30'   CB            ['1']]                       //CIOArea  Bit
    ['0X31'   WB           ['1']]                       //WorkArea Bit
    ['0X32'   HB            ['1']]                       // HoldingBit Area Bit
    ['0X33'   AB            ['1']]                       //AuxiliaryBit Area
    ['0XB0'   C              ['1']]                       //CIOArea  Word
    ['0XB1'   W             ['1']]                       //WorkArea  Word
    ['0XB2'   H              ['1']]                       //Holding bit Area Word
    ['0XB3'   A              ['1']]                        //AuxiliaryBit Area Word
    ['0X82'   D              ['1']]                        //DMArea  Wrod
    ['0X20'   E0B              ['1']]                      //EM0Area  Bit
    ['0X21'   E1B             ['1']]                      //EM1Area  Bit
    ['0X22'   E2B              ['1']]                      //EM2Area  Bit
    ['0X23'   E3B              ['1']]                      //EM3Area  Bit
    ['0X24'   E4B              ['1']]                      //EM4Area  Bit
    ['0X25'   E5B             ['1']]                      //EM5Area  Bit
    ['0X26'   E6B              ['1']]                      //EM6Area  Bit
    ['0X27'   E7B              ['1']]                      //EM7Area  Bit
    ['0X28'   E8B              ['1']]                     //EM8rea  Bit
    ['0X29'   E9B             ['1']]                      //EM9rea  Bit
    ['0X2A'   E0AB            ['1']]                      //EMA Area  Bit
    ['0X2B'   E0BB            ['1']]                      //EMB Area  Bit
    ['0X2C'   E0CB            ['1']]                      //EMCArea  Bit
    ['0X2D'   E0DB            ['1']]                      //EMDArea  Bit
    ['0X2E'    E0EB            ['1']]                      //EMEArea  Bit
    ['0X2F'    E0FB            ['1']]                      //EMFArea  Bit
    ['0XE0'   E10B              ['1']]                      //EM10Area  Bit
    ['0XE1'   E11B              ['1']]                      //EM11Area  Bit
    ['0XE2'   E12B             ['1']]                      //EM12Area  Bit
    ['0XE3'   E13B             ['1']]                      //EM13Area  Bit
    ['0XE4'   E14B              ['1']]                      //EM14Area  Bit
    ['0XE5'   E15B             ['1']]                      //EM15Area  Bit
    ['0XE6'   E16B              ['1']]                      //EM16Area  Bit
    ['0XE7'   E17B             ['1']]                      //EM17Area  Bit
    ['0XE8'   E18B              ['1']]                      //EM18Area  Bit
    ['0XA0'   E0              ['1']]                      //EM0Area  Word
    ['0XA1'   E1            ['1']]                      //EM1Area  Word
    ['0XA2'   E2              ['1']]                      //EM2Area  Word
    ['0XA3'   E3             ['1']]                      //EM3Area  Word
    ['0XA4'   E4              ['1']]                      //EM4Area  Word
    ['0XA5'   E5             ['1']]                      //EM5Area  Word
    ['0XA6'   E6              ['1']]                      //EM6Area  Word
    ['0XA7'   E7              ['1']]                      //EM7Area  Word
    ['0XA8'   E8              ['1']]                     //EM8Area  Word
    ['0XA9'   E9             ['1']]                      //EM9Area  Word
    ['0XAA'   E0A            ['1']]                      //EMAArea  Word
    //['0XAB'   E0B            ['1']]                      //EMBArea  Word
    ['0XAC'   E0C            ['1']]                      //EMCArea  Word
    ['0XAD'   E0D            ['1']]                      //EMDArea  Word
    ['0XAE'    E0E            ['1']]                      //EMEArea  Word
    ['0XAF'    E0F            ['1']]                      //EMFArea  Word
    ['0X60'   E10              ['1']]                      //EM10Area  Word
    ['0X61'   E11              ['1']]                      //EM11Area  Word
    ['0X62'   E12             ['1']]                      //EM12Area  Word
    ['0X63'   E13             ['1']]                      //EM13Area  Word
    ['0X64'   E14              ['1']]                      //EM14Area  Word
    ['0X65'   E15             ['1']]                      //EM15Area  Word
    ['0X66'   E16              ['1']]                      //EM16Area  Word
    ['0X67'   E17             ['1']]                      //EM17Area  Word
    ['0X68'   E18              ['1']]                      //EM18Area  Word
]
[enum uint 8 'FinsDataType' [uint 8 'dataTypeSize']
    ['1' BOOL ['1']]
    ['2' BYTE ['1']]
    ['3' WORD ['2']]
    ['4' DWORD ['4']]
    ['5' LWORD ['8']]
    ['6' SINT ['1']]
    ['7' INT ['2']]
    ['8' DINT ['4']]
    ['9' LINT ['8']]
    ['10' USINT ['1']]
    ['11' UINT ['2']]
    ['12' UDINT ['4']]
    ['13' ULINT ['8']]
    ['14' REAL ['4']]
    ['15' LREAL ['8']]
    ['16' TIME ['8']]
    ['17' LTIME ['8']]
    ['18' DATE ['8']]
    ['19' LDATE ['8']]
    ['20' TIME_OF_DAY ['8']]
    ['21' LTIME_OF_DAY ['8']]
    ['22' DATE_AND_TIME ['8']]
    ['23' LDATE_AND_TIME ['8']]
    ['24' CHAR ['1']]
    ['25' WCHAR ['2']]
    ['26' STRING ['1']]
    ['27' WSTRING ['2']]
    ['28' BIT ['1']]
    ['29' UINT_BCD ['2']]
    ['30' UDINT_BCD ['4']]
    ['31' ULINT_BCD ['8']]
]