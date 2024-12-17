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

[discriminatedType 'SecsPacket'
    [implicit      uint 32 'len' 'lengthInBytes-4']
    [simple        uint 16 'deviceID']
    [discriminator uint 16 'streamFunction']
    [simple uint 8 'PType']
    [simple uint 8 'Stype']
    [simple uint 32 'systemBytes']

    [typeSwitch 'streamFunction'
        //S1
        ['0x8101' S1F1Request            //Host发送0x8101  eq将接收到的0x8101数据放到S1F1Request这个变量里面
        ]
        [ '0x0102' S1F2Response                                               //将Host反馈的0102放到S1F2Response这个变量里面
            [const  uint   16     'hostResponses1f2list1' '0x0102']          //将'0x0102'取出来放到hostResponses1f2list1这个变量中
            [simple  DataStruct   'mdln'                          ]          //将反馈回来的mdln值放到'mdln'这个变量里面
            [simple  DataStruct   'softrev'                       ]          //将反馈回来的softrev值放到'softrev'这个变量里面  
        ]
        ['0x810d' S1F13Request                                 //Eq主动发送S1F13
            [const uint 16 'S1F13list1' '0x0102' ]             //0102为固定
            [simple DataStruct 'mdln']                           //发送mdln
            [simple DataStruct 'softrev']                        //发送softrev
        ] 
        [ '0x010e' S1F14Response                             //
            [simple uint 16   's1f14list1'     ]             //将'0x0102'取出来放到s1f14list1这个变量中
            [simple DataStruct 'commack'       ]             //将反馈回来的commack'取出来放到commack这个变量中
            [simple uint 16   's1f14list2'     ]             //将'0x0102'取出来放到s1f14list1这个变量中
            [simple DataStruct 'mdln'          ]             //将反馈回来的mdln'取出来放到mdln这个变量中
            [simple DataStruct 'softrev'       ]             //将反馈回来的softrev'取出来放到softrev这个变量中
        ]
        ['0x8103' S1F3Request    //Host主动发过来S1F3           //将0x8103 存放到hostS1F3Request这个变量中
            [simple    uint 8    'hostS1F3list1'        ]      //将list的01 存放到hostS1F3list1这个变量中
            [simple    uint 8    'vidLen'               ]      //将vid的个数存放到vidlen这个变量里面
            [array  DataStruct   'vidvalues' count 'vidLen']      //将数据值放到vidvalues数组里面，长度为上面接收到的len
        ] 
        [ '0x0104' S1F4Response                                 //EQ反馈S1F4   .0x0104
            [const  uint  8      'S1F4Responselist1' '01' ]        //0x01
            [simple uint  8      'vidlen'                    ]       //vid个数
            [array  DataStruct   'vidvalues' count 'vidLen'  ]       //反馈vid数据      
        ]
        //S2
        ['0x8211' S2F17Request     //host发送给EQ
        ] 
        ['0x0212' S2F18Response         //EQ反馈给host
            [simple DataStruct 'ctime']      //time
        ]
        ['0x821F' S2F31Request             //host发送给EQ
            [simple DataStruct 'ctime']     //time
        ]
        ['0x0220' S2F32Response           //EQ反馈host
            [simple DataStruct 'tiack']   //tiack
        ]
        ['0x8229' S2F41Request          //host主动发送给EQ
            [const uint 16 'list2' '0x0102' ]   //L,2固定值
            [simple DataStruct 'rcmd']
            [const uint 8 'L' '0x01']       //L,n 中的L
            [simple uint 8 'paraLen']       //L,n 中的n
            [array  ParametersData   'paraData' count 'paraLen']
        ]
        ['0x022A' S2F42Response          //EQ反馈给host
            [const uint 16 'list2' '0x0102' ]   //L,2 固定值
            [simple DataStruct 'hcack']         //HCACK
            [const uint 8 'L' '0x01']           //L,n 中的L
            [simple uint 8 'ErrorparaLen']           //L,n 中的n
            [array  ParametersResponseData   'paraResponseData' count 'ErrorparaLen']
        ]
        //S5
        ['0x8501' S5F1Request                   //EQ发送给host
            [const uint 16 'list3' '0x0103' ]   //L,3 固定值
            [simple DataStruct 'alcd']          //ALCD
            [simple DataStruct 'alid']          //ALID
            [simple DataStruct 'altx']          //ALTX
        ]
        ['0x0502' S5F2Response               //host反馈给EQ
            [simple DataStruct 'ack5']       //ack5
        ]
        //S6
        ['0x860b' S6F11Request
            [const uint 16 'list3' '0x0103' ]
            [simple DataStruct 'dataId']
            [simple DataStruct 'secId']
            [array  ReportData   'reportData' count  '8']
        ]
        ['0x060C' S6F12Response    //HOST回复EQ的S6F11
            [simple DataStruct 'hcack']         //HCACK，报头后面的  EQ回复给HOST的状态值，
        ]
        //S7
        ['0x8711' S7F17Request   //HOST主动发出来，EQ接收
            [ const  uint 8 'L' '0x01' ]
            [ simple uint 8 'countNumber']
            [ array  DataStruct  'ppid' count 'countNumber']
        ]
        ['0x0712' S7F18Response    //EQ回复HOSTS7F17
            [simple DataStruct 'hcack']         //HCACK，报头后面的  EQ回复给HOST的状态值，
        ]
        ['0x8713' S7F19Request    //HOST主动发出，EQ接受，请求EQ上报PPID列表。只有10字节的报头，没有后面的数据。
        ]
        ['0x0714' S7F20Response    //EQ回复S7F19，上报PPID列表
            [ const  uint 8 'L' '0x01' ]    //列表list
            [ simple uint 8 'countNumber']  //PPID的数量
            [ array  DataStruct   'ppid' count 'countNumber']  //PPID的内容，  数据类型，字节数，数据内容
        ]

        //S9
        ['0x0901' S9F1Response
            [const  uint 16   'list2'     '0x210a'       ]    // eq主动发送s9f1,当判断接收到的Device  id与设备设置的Device  id不一致时需要向Host反馈S9F1
            [array  uint  8   'revData'    count     '10']    // 接收到的Device  id如果与设备设置的不一致，则返回接收指令的header 的10个byte。
        ]
        ['0x0903' S9F3Response                                //eq主动发送S9F3,当eq判断Host发送过来的指令其中S未在eq中定义，则会反馈S9F3
            [const  uint 16   'list2'     '0x210a'       ]    //  
            [array  uint  8   'revData'    count     '10']    //eq判断Host发送过来的指令其中S未在eq中定义，则返回接收指令的header 的10个byte。
        ]
        ['0x0905' S9F5Response                                 //eq主动发送S9F5,当eq判断Host发送过来的指令其中F未在eq中定义，则会反馈S9F5
            [const  uint 16   'list2'     '0x210a'       ]
            [array  uint  8   'revData'    count     '10']     //eq判断Host发送过来的指令其中F未在eq中定义，则返回接收指令的header 的10个byte。
        ] 
        ['0x0907' S9F7Response
            [const  uint 16   'list2'     '0x210a'       ]     //eq判断Host发送过来的指令数据格式是否正确。
            [array  uint  8   'revData'    count     '10']     //eq判断Host发送过来的指令数据格式不正确，则返回接收指令的header 的10个byte。
        ] 
         
    ]

]
[type 'ParametersData'
    [const uint 16 'L2' '0x0102']      //L,2固定值
    [simple DataStruct 'cpname']       //cpname
    [simple DataStruct 'cpval']        //cpval
]

[type 'ParametersResponseData'
    [const uint 16 'L2' '0x0102']      //L,2固定值
    [simple DataStruct 'cpname']       //cpname
    [simple DataStruct 'cpack']        //cpack
]

[type 'ReportData'
    [const uint 8 'L' '0x01']
    [simple uint 8 'dataLen']
    [const uint 8 'L2' '0x0102']
    [simple DataStruct 'aId']
    [array  VidData   'vidData' count  '8' ]

    [const uint 16 'list2' '0x0102' ]
    [simple DataStruct 'bId']
]
[type 'VidData'
    [const uint 8 'L11' '0x01']
    [simple uint 8 'dataLen']
    [array  DataStruct   'values' count  'dataLen' ]
]



[type 'DataStruct'
    [enum   SecsDataTypeCode 'dataType']
    [simple uint    16  'elementNb']
    [array  int 8   'data'  length  'dataType.size*elementNb']
]
[enum uint   8   'SecsDataTypeCode' [uint 8  'size']
    ['0X01'   List            ['1']]
    ['0X21'   Byte            ['1']]
    ['0X25'   Bool            ['1']]
    ['0X41'   STRING256       ['256']]
    ['0X65'   SINT            ['1']]
    ['0X69'   INT             ['2']]
    ['0X71'   DINT            ['4']]
    ['0X91'   REAL            ['4']]
    ['0X81'   LREAL           ['8']]
    ['0Xa5'   USINT           ['1']]
    ['0Xa9'   UINT            ['2']]
    ['0Xb1'   UDINT           ['4']]
]

[enum uint 16 'ErrorCode'
    ['0x0901' S9F1]
    ['0x0901' S9F3]
]
