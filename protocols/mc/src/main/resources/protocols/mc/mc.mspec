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

[discriminatedType 'McPacket'
    [discriminator        uint 32 'subheader']//请求副帧头//0x5000
    [const        uint 16 'RequestdestinationnetworkNo' '0x3030']//请求目标网络No.//0x00
    [const        uint 16 'RequestdestinationstationNo' '0x4646']//请求目标站号No.//0xFF
    [const        uint 32 'RequestdestinationmoduleIONo' '0x30334646']//请求目标模块I/O编号No  //0xFF03
    [const        uint 16 'RequestdestinationmultidropstationNo' '0x3030']//请求目标多点站号No.
    [typeSwitch 'subheader'
        ['0x35303030' McRequest		//请求副帧头（Subheader） "5000",占用4字节
            //5000 00 FF 03FF 00 0018 0010 0401 0000 D* 000100 0002   //读D100  1
            //35303030 3030 4646 30334646 3030 30303138 30303130 30343031 30303030 442a 303030313030 30303032
            [implicit      uint 32 'requestdatalength' 'STATIC_CALL("org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.calculateRequestDateLength", _value.lengthInBytes())]		//请求数据长（监视定时器），占用4字节
            //[implicit      uint 32 'requestdatalength' 'lengthInBytes-18']
            [const        uint 32 'monitoringtimer' '0x30303130']		//监视定时器，占用4字节//0x0000
            [simple        CommandType  'command']
        ]
        ['0x44303030'	McResponse	//响应副帧头（Subheader） "D000",占用4字节
            [simple        uint 32 'responsedatalength' ]		//响应数据长
            [simple        EndcodeType  'endcode'  ['responsedatalength']]
        ]
    ]
]
[type 'CommandType'
    [discriminator uint 32 'Command']		//请求数据-指令，占用4字节
    [typeSwitch 'Command'		//请求数据-指令，占用4字节
        ['0x30343031' Read    	//读取连续软元件值 "0401"
            [simple        ReadSubcommandType  'Subcommand']
        ]
        ['0x31343031' Write		//写入连续软元件值 "1401"
            [simple        WriteSubcommandType  'Subcommand']
        ]
        ['0x30343033' Readrandom		//读取离散软元件值 "0403"
            [const    uint 32    'subcommand' '0x30303030' ]		//子指令 "0000" ，占用4字节 Specifies the device No. and reads the device value. This can be specified with inconsecutive device No.Reads value from the word devices in one-word units or two-word units.
            [simple    uint 16    'wordaccesspoints' ]		//字访问点数，占用2字节
            [simple    uint 16    'doublewordaccesspoints' ]		//双字访问点数，占用2字节
            [array    DataStruct04030000 'wordaccesspointsAddr' count   'wordaccesspoints']		//单字软元件地址
            [array    DataStruct04030000 'doublewordaccesspointsAddr' count   'doublewordaccesspoints']		//双字软元件地址
        ]

        ['0x31343032' Writerandom    //写入离散软元件值 "1402"
            [simple       WriterandomSubcommandType  'Subcommand']
        ]
        ['0x30363139' Selftest    //回送测试 "0619" Tests whether the communication with external devices is normally executed or not.
            [const    uint 32    'subcommand' '0x30303030' ] 	//子指令 "0000"
            [simple    uint 32    'numberoflookbackdata' ]     	 //回送数据字节数
            [array  uint 8   'data'  count  'numberoflookbackdata']		//回送数据
        ]
    ]
]
[type 'ReadSubcommandType'
    [discriminator uint 32 'Subcommand']		//请求数据-子指令，占用4字节
    [typeSwitch 'Subcommand'
        ['0x30303031' Readbybit   	 //读取软元件值 "0001" Reads value from the bit devices (consecutive device No.) in 1-point units
            [enum   Devicecode 'devicecode'       ]		//软元件代码，占用2字节
            [simple    uint 48    'headdeviceno' ]		//软元件起始地址，占用6字节
            [simple    uint 32    'devicepointno' ]		//软元件长度，占用4字节
        ]
        ['0x30303030' Readbyword    //读取软元件值 "0000" Reads value from the bit devices (consecutive device No.) in 16-point units.Reads value from the word devices (consecutive device No.) in one-word units.
            [enum   Devicecode 'devicecode'       ]      //软元件代码，占用2字节
            [simple    uint 48    'headdeviceno' ]      //软元件起始地址，占用6字节
            [simple    uint 32    'devicepointno' ]      //软元件长度，占用4字节
        ]
    ]
]

[type 'WriteSubcommandType'
    [discriminator uint 32 'Subcommand']		//请求数据-子指令，占用4字节
    [typeSwitch 'Subcommand'
    //500000FF03FF00001C001014010000D* 000101 0001 000B
        ['0x30303031' Writebybit		//软元件值写入 "0001" Writes value to the bit devices (consecutive device No.) in 1-point units.
            [enum   Devicecode 'devicecode'       ]		//软元件代码，占用2字节
            [simple    uint 48    'headdeviceno' ]		//软元件起始地址，占用6字节
            [simple    uint 32    'devicepointno' ]		//软元件长度，占用4字节
            [array  int 8   'data'  count  'devicepointno']		//写入的数据值，按BYTE长度写入
        ]
        ['0x30303030' Writebyword		//软元件值写入 "0000" Specifies the device No. and reads the device value. This can be specified with inconsecutive device No.Reads value from the word devices in one-word units or two-word units
            [enum   Devicecode 'devicecode'       ]		//软元件代码，占用2字节
            [simple    uint 48    'headdeviceno' ]		//软元件起始地址，占用6字节
            [simple    uint 32    'devicepointno' ]		//软元件长度，占用4字节
            [array   int 8   'data'  count  'STATIC_CALL("org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.calculateWriteDateLength", devicepointno)*4]		//写入的数据值，按WORD长度写入
        ]
    ]
]

[type 'WriterandomSubcommandType'
    [discriminator uint 32 'Subcommand']//请求数据-子指令，占用4字节
    [typeSwitch 'Subcommand'
        ['0x30303031' Writebybitrandom		//子指令 "0001" Specifies the device No. to bit device in 1-point units and writes value. This can be specified with inconsecutive device No.
            [simple    uint 16    'Numberofbitaccesspoints' ]      //位访问点数
            [array  DataStruct14020001  'dataStruct14020001' count  'Numberofbitaccesspoints']
        ]
        ['0x30303030' Writebybitrandom		//子指令 "0000" Specifies the device No. to bit device in 16-point units and writes value. This can be specified with inconsecutive device No.Specifies the device No. to word device in oneword units or two-word units and writes value.This can be specified with inconsecutive device No.
            [simple    uint 16    'Wordaccesspoints' ]      //字访问点数
            [simple    uint 16    'Doublewordaccesspoints' ]      //双字访问点数
            [array   DataStruct14020000word  'dataStruct14020000word' count  'Wordaccesspoints']		//单字软元件地址及写入值
            [array   DataStruct14020000doubleword  'dataStruct14020000doubleword' count  'Doublewordaccesspoints']		//双字软元件地址及写入值
        ]
    ]
]

[type 'EndcodeType' [int  32  'responsedatalength']
    [discriminator uint 32 'Endcode']		//正常结束or异常结束
    [typeSwitch 'Endcode'
        ['0x30303030' Normalendcode	 [int  32  'responsedatalength']	//结束代码为"0000"。

            //[array  uint 8   'data'  count  'responsedatalength-4']
            [array  int 8   'data'  count  'STATIC_CALL("org.apache.plc4x.java.mc.readwrite.util.ConvertUtils.calculateResponseDateLength", responsedatalength)]
        ]
        [DefaultUnKnownError]
        //[default
          //  [simple    uint 16    'ResponsenetworkNo' ]	//出错响应站信息-网络号
          //  [simple    uint 16    'ResponsestationNo' ]	//出错响应站信息-站号
          //  [simple    uint 32    'ResponsemoduleioNo' ]	//出错响应站信息-请求目标模块IO编号
          //  [simple    uint 16    'ResponsemultidropstationNo' ]	//出错响应站信息多点站号
          //  [simple    uint 32    'Reponsecommand' ]	//指令
          //  [simple    uint 32    'Responsesubcommand' ]	//子指令
        //]
    ]
]
[type 'DataStruct04030000'
    [enum   Devicecode 'devicecoderadom']		//软元件类型，占用2字节
    [simple uint 48  'deviceNoradom']			//软元件地址，占用6字节
]
[type 'DataStruct14020001'
	[enum   Devicecode 'devicecoderadom']		//软元件类型，占用2字节
    [simple uint 48  'deviceNoradom']			//软元件地址，占用6字节
    [enum   Setreset 'setreset14020001']		//置位复位状态，占用1字节
]
[type 'DataStruct14020000word'
	[enum   Devicecode 'devicecoderadom']		//软元件类型，占用2字节
    [simple uint 48  'deviceNoradom']			//软元件地址，占用6字节
    [simple   uint 16 'wordvalue14020001']		//写入单字的值，占用2字节
]
[type 'DataStruct14020000doubleword'
	[enum   Devicecode 'devicecoderadom']		//软元件类型，占用2字节
    [simple uint 48  'deviceNoradom']			//软元件地址，占用6字节
    [simple   uint 32 'doublewordvalue14020001']		//写入双字的值，占用4字节
]
[enum uint   16   'Setreset' [uint 8  'size']//置位复位操作----------------
    ['0X3030'   Reset          ['1']]
    ['0X3031'   Set            ['1']]
]
[enum  int  16   'Devicecode'
    ['0X534D'   SM   ]
    ['0X5344'   SD   ]
    ['0X582A'   X    ]
    ['0X592A'   Y    ]
    ['0X4D2A'   M    ]
    ['0X4C2A'   L    ]
    ['0X462A'   F    ]
    ['0X562A'   V    ]
    ['0X422A'   B    ]
    ['0X442A'   D    ]
    ['0X572A'   W    ]
    ['0X5453'   TS   ]
    ['0X5443'   TC   ]
    ['0X544E'   TN   ]
    ['0X5353'   STS  ]
    ['0X5343'   STC  ]
    ['0X5344'   STN  ]
    ['0X4353'   CS   ]
    ['0X4343'   CC   ]
    ['0X434E'   CN   ]
    ['0X5342'   SB   ]
    ['0X5357'   SW   ]
    ['0X4458'   DX   ]
    ['0X4459'   DY   ]
    ['0X5A2A'   Z    ]
    ['0X522A'   R    ]
    ['0X5A52'   ZR   ]
]
[enum uint 8 'McDataType' [uint 8 'dataTypeSize']
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