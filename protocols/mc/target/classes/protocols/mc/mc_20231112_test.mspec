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

[discriminatedType 'MCPacket'
    [typeSwitch 'Requestorrespone'
		['0x35303030'		//请求副帧头（Subheader） "5000",占用4字节
			[simple        uint 16 'RequestdestinationnetworkNo']		//请求目标网络No.，占用2字节
			[simple        uint 16 'RequestdestinationstationNo']		//请求目标站号No.，占用2字节
			[simple        uint 32 'RequestdestinationmoduleIONo']		//请求目标模块I/O编号No.，占用4字节
			[simple        uint 16 'RequestdestinationmultidropstationNo']		//请求目标多点站号No.，占用2字节
			[implicit      uint 32 'Requestdatalength' 'lengthInBytes-9']		//请求数据长（监视定时器），占用4字节
			[simple        uint 32 'Monitoringtimer']		//监视定时器，占用4字节
			[discriminator uint 32 'Command']		//请求数据-指令，占用4字节
			[typeSwitch 'Command'		//请求数据-指令，占用4字节
				['0x30343031' Read    	//读取连续软元件值 "0401"
					[discriminator uint 32 'Subcommand']		//请求数据-子指令，占用4字节
                    [typeSwitch 'Subcommand'
                        ['0x30303031' Readbybit   	 //读取软元件值 "0001" Reads value from the bit devices (consecutive device No.) in 1-point units
                            [simple    uint 48    'headdeviceno' ]		//软元件起始地址，占用6字节
                            [enum   Devicecode 'devicecode'       ]		//软元件代码，占用2字节
                            [simple    uint 32    'devicepointno' ]		//软元件长度，占用4字节
                        ]
                        ['0x30303030' Readbyword    //读取软元件值 "0000" Reads value from the bit devices (consecutive device No.) in 16-point units.Reads value from the word devices (consecutive device No.) in one-word units.
                            [simple    uint 48    'headdeviceno' ]      //软元件起始地址，占用6字节
                            [enum   Devicecode 'devicecode'       ]      //软元件代码，占用2字节
                            [simple    uint 32    'devicepointno' ]      //软元件长度，占用4字节
                        ]
                    ]
				]
				['0x31343031' Write		//写入连续软元件值 "1401"
					[discriminator uint 32 'Subcommand']		//请求数据-子指令，占用4字节
                    [typeSwitch 'Subcommand'
                        ['0x30303031' Writebybit		//软元件值写入 "0001" Writes value to the bit devices (consecutive device No.) in 1-point units.
                            [simple    uint 48    'headdeviceno' ]		//软元件起始地址，占用6字节
                            [enum   Devicecode 'devicecode'       ]		//软元件代码，占用2字节
                            [simple    uint 32    'devicepointno' ]		//软元件长度，占用4字节
                            [array  uint 8   'data'  length  'devicepointno']		//写入的数据值，按BYTE长度写入
                        ]
                        ['0x30303030' Writebyword		//软元件值写入 "0000" Specifies the device No. and reads the device value. This can be specified with inconsecutive device No.Reads value from the word devices in one-word units or two-word units
                            [simple    uint 48    'headdeviceno' ]		//软元件起始地址，占用6字节
                            [enum   Devicecode 'devicecode'       ]		//软元件代码，占用2字节
                            [simple    uint 32    'devicepointno' ]		//软元件长度，占用4字节
                            [array  uint 16   'data'  length  'devicepointno']		//写入的数据值，按WORD长度写入
                        ]
                    ]
				]

				['0x30343033' Readrandom		//读取离散软元件值 "0403"
					[const    uint 32    'subcommand' '0x30303030' ]		//子指令 "0000" ，占用4字节 Specifies the device No. and reads the device value. This can be specified with inconsecutive device No.Reads value from the word devices in one-word units or two-word units.
					[simple    uint 16    'Wordaccesspoints' ]		//字访问点数，占用2字节
					[simple    uint 16    'Doublewordaccesspoints' ]		//双字访问点数，占用2字节
					[array  uint 64   'DataStruct'  length  'Wordaccesspoints']		//单字软元件地址
					[array  uint 64   'DataStruct'  length  'Doublewordaccesspoints']		//双字软元件地址
				]

				['0x31343032' Writerandom    //写入离散软元件值 "1402"
					[discriminator uint 32 'Subcommand']//请求数据-子指令，占用4字节
                    [typeSwitch 'Subcommand'
                        ['0x30303031' Writebybitrandom		//子指令 "0001" Specifies the device No. to bit device in 1-point units and writes value. This can be specified with inconsecutive device No.
                            [simple    uint 16    'Numberofbitaccesspoints' ]      //位访问点数
                            [array  uint 80   'DataStruct14020001'  length  'Numberofbitaccesspoints']
                        ]
                        ['0x30303030' Writebybitrandom		//子指令 "0000" Specifies the device No. to bit device in 16-point units and writes value. This can be specified with inconsecutive device No.Specifies the device No. to word device in oneword units or two-word units and writes value.This can be specified with inconsecutive device No.
                            [simple    uint 16    'Wordaccesspoints' ]      //字访问点数
                            [simple    uint 16    'Doublewordaccesspoints' ]      //双字访问点数
                            [array  uint 96   'DataStruct14020000word'  length  'Wordaccesspoints']		//单字软元件地址及写入值
                            [array  uint 112   'DataStruct14020000duoubleword'  length  'Doublewordaccesspoints']		//双字软元件地址及写入值
                        ]
                    ]

				]
				['0x30363139' Selftest    //回送测试 "0619" Tests whether the communication with external devices is normally executed or not.
					[const    uint 32    'subcommand' '0x30303030' ] 	//子指令 "0000" 
					[simple    uint 32    'numberoflookbackdata' ]     	 //回送数据字节数
					[array  uint 8   'data'  length  'numberoflookbackdata']		//回送数据
				]
			]
		]
	
		['0x44303030'		//响应副帧头（Subheader） "D000",占用4字节
			[simple        uint 16 'RequestdestinationnetworkNo']		//响应目标网络No.，占用2字节
			[simple        uint 16 'RequestdestinationstationNo']		//响应目标站号No.，占用2字节
			[simple        uint 32 'RequestdestinationmoduleIONo']		//响应目标模块I/O编号No.，占用4字节
			[simple        uint 16 'RequestdestinationmultidropstationNo']		//响应目标多点站号No.，占用2字节
			[simple      uint 32 'Responsedatalength' ]		//响应数据长
			[discriminator uint 32 'Endcode']		//正常结束or异常结束
				[typeSwitch 'Endcode'
					['0x30303030' Normalendcode		//结束代码为"0000"。	
						[array  uint 8   'data'  length  'Responsedatalength-4'] 
					]
					[Default
						[simple    uint 16    'ResponsenetworkNo' ]	//出错响应站信息-网络号
						[simple    uint 16    'ResponsestationNo' ]	//出错响应站信息-站号
						[simple    uint 32    'ResponsemoduleioNo' ]	//出错响应站信息-请求目标模块IO编号
						[simple    uint 16    'ResponsemultidropstationNo' ]	//出错响应站信息多点站号
						[simple    uint 32    'Reponsecommand' ]	//指令
						[simple    uint 32    'Responsesubcommand' ]	//子指令
					]
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
[enum uint   16   'Devicecode' [uint 8  'size']//软元件类型------------
    ['0X534D'   SM		['2']]
    ['0X5344'   SD      ['2']]
    ['0X582A'   X       ['2']]
    ['0X592A'   Y       ['2']]
    ['0X4D2A'   M       ['2']]
    ['0X4C2A'   L       ['2']]
    ['0X462A'   F       ['2']]
    ['0X562A'   V       ['2']]
    ['0X422A'   B       ['2']]
    ['0X442A'   D       ['2']]
    ['0X572A'   W       ['2']]
    ['0X5453'   TS      ['2']]
    ['0X5443'   TC      ['2']]
    ['0X544E'   TN      ['2']]
    ['0X5353'   STS     ['2']]
    ['0X5343'   STC     ['2']]
    ['0X5344'   STN     ['2']]
    ['0X4353'   CS      ['2']]
    ['0X4343'   CC      ['2']]
    ['0X434E'   CN      ['2']]
    ['0X5342'   SB      ['2']]
    ['0X5357'   SW      ['2']]
    ['0X4458'   DX      ['2']]
    ['0X4459'   DY      ['2']]
    ['0X5A2A'   Z       ['2']]
    ['0X522A'   R       ['2']]
    ['0X5A52'   ZR      ['2']] 
]
