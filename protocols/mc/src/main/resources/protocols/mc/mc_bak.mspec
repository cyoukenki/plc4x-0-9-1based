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
    [simple        uint 32 'Subheader']//请求副帧头
    [simple        uint 16 'RequestdestinationstationNo']//请求目标网络No.
    [simple        uint 16 'RequestdestinationstationNo']//请求目标站号No.
    [simple        uint 32 'RequestdestinationmoduleIONo']//请求目标模块I/O编号No.
    [simple        uint 16 'RequestdestinationmultidropstationNo']//请求目标多点站号No.
    [discriminator uint 32 'Requestorrespone']//请求or响应
    [typeSwitch 'Requestorrespone'
		[ '0x00000001' Requestorrespone1
			[implicit      uint 32 'Requestdatalength' 'lengthInBytes-9']//请求数据长（监视定时器）
			[simple        uint 32 'Monitoringtimer']//监视定时器
			[simple  DataStruct1 'Command1']

		]
		['0x00000002' Requestorrespone2
			[simple      uint 32 'Responsedatalength' ]//响应数据长
			[simple  DataStruct2 'Endcode1']

		]
	]
]


[type 'DataStruct1'
   [discriminator uint 32 'Command']//请求数据-指令
   			[typeSwitch 'Command'
   				['0x30343031' Read    //读取连续软元件值 0401
   				[simple  DataStruct12 'Command12']

   				]

   				['0x31343031' Write    //软元件值连续写入 1401
   				[simple  DataStruct22 'Command22']

   				]
   				['0x30343033' Readrandom    //读取离散软元件值 0403
   					[const    uint 48    'subcommand' '0x30303030' ] 	//子指令0000
   					[simple    uint 16    'Wordaccesspoints' ]     	 //字访问点数
   					[simple    uint 16    'Doublewordaccesspoints' ]      //双字访问点数
   					[array  uint 64   'DataStruct'  length  'Wordaccesspoints']
   					[array  uint 64   'DataStruct'  length  'Doublewordaccesspoints']
   				]

   				['0x31343032' Writerandom    //读取离散软元件值 1402
   				[simple  DataStruct33 'Command33']

   				]


   				['0x30363139' Selftest    //回送测试 0619
   					[const    uint 32    'subcommand' '0x30303030' ] 	//子指令0000
   					[simple    uint 32    'numberoflookbackdata' ]     	 //回送数据数
   					[array  uint 8   'data'  length  'numberoflookbackdata']		//回送数据
   				]
   		    ]
]
[type 'DataStruct12'
	[discriminator uint 32 'Subcommand']//请求数据-子指令
   					[typeSwitch 'Subcommand'
   						['0x30303031' Readbybit    //读取软元件值 0001 Reads value from the bit devices (consecutive device No.) in 1-point units
   							[simple    uint 48    'headdeviceno' ]      //软元件起始地址
   							[enum   Devicecode 'devicecode'       ]      //软元件代码
   							[simple    uint 32    'devicepointno' ]      //软元件长度
   						]
   						['0x30303030' Readbyword    //读取软元件值 0000 Reads value from the bit devices (consecutive device No.) in 16-point units.Reads value from the word devices (consecutive device No.) in one-word units.
   							[simple    uint 48    'headdeviceno' ]      //软元件起始地址
   							[enum   Devicecode 'devicecode'       ]      //软元件代码
   							[simple    uint 32    'devicepointno' ]      //软元件长度
   						]
   					]
]
[type 'DataStruct2'
        [discriminator uint 32 'Endcode']//正常结束or异常结束
			[typeSwitch 'Endcode'
				['0x30303030' Normalendcode//
					[array  uint 8   'data'  length  'Responsedatalength-4']
				]

				['0x30303031'  Errorendcode   //错误代码，0000以外的代码------------------------------------------------------------------------------------
					[simple    uint 16    'ResponsenetworkNo' ]	//出错响应站信息-网络号
					[simple    uint 16    'ResponsestationNo' ]	//出错响应站信息-站号
					[simple    uint 32    'ResponsemoduleioNo' ]	//出错响应站信息-请求目标模块IO编号
					[simple    uint 16    'ResponsemultidropstationNo' ]	//出错响应站信息多点站号
					[simple    uint 32    'Reponsecommand' ]	//指令
					[simple    uint 32    'Responsesubcommand' ]	//子指令
				]
			]
]
[type 'DataStruct22'
	[discriminator uint 32 'Subcommand']//请求数据-子指令
   					[typeSwitch 'Subcommand'
   						['0x30303031' Writebybit//软元件值写入 0001 Writes value to the bit devices (consecutive device No.) in 1-point units.
   							[simple    uint 48    'headdeviceno' ]      //软元件起始地址
   							[enum   Devicecode 'devicecode'       ]      //软元件代码
   							[simple    uint 32    'devicepointno' ]      //软元件长度
   							[array  uint 8   'data'  length  'devicepointno']//写入的数据值
   						]
   						['0x30303030' Writebyword//软元件值写入 0000 Specifies the device No. and reads the device value. This can be specified with inconsecutive device No.Reads value from the word devices in one-word units or two-word units
   							[simple    uint 48    'headdeviceno' ]      //软元件起始地址
   							[enum   Devicecode 'devicecode'       ]      //软元件代码
   							[simple    uint 32    'devicepointno' ]      //软元件长度
   							[array  uint 16   'data'  length  'devicepointno']//写入的数据值
   						]
   					]
]
[type 'DataStruct33'
[discriminator uint 32 'Subcommand']//请求数据-子指令
   					[typeSwitch 'Subcommand'
   						['0x30303031' Writebybitrandom		//子指令0001
   							[simple    uint 16    'Numberofbitaccesspoints' ]      //位访问点数
   							[array  uint 80   'DataStruct14020001'  length  'Numberofbitaccesspoints']
   						]
   						['0x30303030' Writebybitrandom		//子指令0000
   							[simple    uint 16    'Wordaccesspoints' ]      //字访问点数
   							[simple    uint 16    'Doublewordaccesspoints' ]      //双字访问点数
   							[array  uint 96   'DataStruct14020000word'  length  'Wordaccesspoints']
   							[array  uint 112   'DataStruct14020000duoubleword'  length  'Doublewordaccesspoints']
   						]
   					]
]
[type 'DataStruct'
    [enum   Devicecode 'devicecoderadom']
    [simple uint 48  'deviceNoradom']
]

[type 'DataStruct14020001'
    [simple   DataStruct 'bitdevicecoderadom']
    [enum   Setreset 'setreset14020001']
]
[type 'DataStruct14020000word'
    [simple   DataStruct 'worddevicecoderadom']
    [simple   uint 16 'wordvalue14020001']
]
[type 'DataStruct14020000doubleword'
    [simple   DataStruct 'doubleworddevicecoderadom']
    [simple   uint 32 'doublewordvalue14020001']
]
[enum uint   16   'Setreset' [uint 8  'size']//置位复位操作----------------
    ['0X3030'   Reset             ['1']]
    ['0X3031'   Set             ['1']]
]
[enum uint   16   'Devicecode' [uint 8  'size']//软元件类型------------
    ['0X534D'   SM             ['1']]
    ['0X5344'   SD             ['1']]
    ['0X582A'   X              ['1']]
    ['0X592A'   Y              ['1']]
    ['0X4D2A'   M              ['1']]
    ['0X4C2A'   L              ['1']]
    ['0X462A'   F              ['1']]
    ['0X562A'   V              ['1']]
    ['0X422A'   B              ['1']]
    ['0X442A'   D              ['1']]
    ['0X572A'   W              ['1']]
    ['0X5453'   TS             ['1']]
    ['0X5443'   TC             ['1']]
    ['0X544E'   TN             ['1']]
    ['0X5353'   STS            ['1']]
    ['0X5343'   STC            ['1']]
    ['0X5344'   STN            ['1']]
    ['0X4353'   CS             ['1']]
    ['0X4343'   CC             ['1']]
    ['0X434E'   CN             ['1']]
    ['0X5342'   SB             ['1']]
    ['0X5357'   SW             ['1']]
    ['0X4458'   DX             ['1']]
    ['0X4459'   DY             ['1']]
    ['0X5A2A'   Z              ['1']]
    ['0X522A'   R              ['1']]
    ['0X5A52'   ZR             ['1']] 
]
