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
[discriminatedType 'UpperLink'
    [const  uint 8  'header'  '0x40']                 //1 items
  // [const          sring       1      'UTF-8'    'header'       '@']                 //1 items
    [simple uint 16  'unitNo']
    [simple Context 'context']
    [simple uint 16  'fcs']
   // [simple uint 32  'lengthInBytes']

    [const  uint 16  'terminator' '0x2A0D']
    [discriminatedType  'Context'
        [discriminator  uint 8  'headerCode']
        [typeSwitch 'headerCode'
            ['0x5244'   dbReadReq//headercode写入‘RD’
                [simple int 32  'beginingWord']
                [simple int 32  'no_of_words' ]
            ]
            ['0x5244'   dbReadRsp//headercode返回‘RD’
                [simple uint    8  'responseCode']
                [array  uint    8   'data'  count   'lengthInBytes-11']//'@'+'Unit No.'+'responseCode'+'fcs'+'*'+'CR'
            ]
            ['0x5744' dbWriteReq//headercode写入‘WD’
                [simple int 32  'beginingWord']
                [Array  int 32  'write_no_of_words']
            ]
            ['0x5744'   dbWriteRsp//headercode返回‘WD’
                [simple int 16  'responseCode']
            ]
            ['0x6363'   dbErrorRsp//headercode返回‘？？’
                [simple sring '255' 'UTF-8' 'meaning']
                [simple sring '255' 'UTF-8' 'causing']
                [simple ErrorCode  'errorCode']//定义errorcode类，case判断功能码
            ]
        ]
    ]
    [discriminatedType 'ErrorCode'//报警代码，根据不同的报警代码，对meaning和causing变量赋值，展示报错原因
        [discriminator uint 8 'functionCode']
            [typeSwitch 'functionCode'
            ['0x00'
                [meaning =   'Normal end']
                [causing =   '...']
            ]
            ['0x01'
                [meaning =   'Not executable due to RUNmode']
                [causing =   'A command not executable in the RUN mode was sent.']
            ]
            ['0x02'
                [meaning =   'Not executable due to MONITOR mode']
                [causing =   'A command not executable in the MONITOR mode was sent.']
            ]
            ['0x03'
                [meaning =   'Not executable due to PROM']
                [causing =   'The program is not transferable due to the PROMspecification of the user program.']
            ]
            ['0x04'
                [meaning =   'Over-address']
                [causing =   'The user program exceeds the maximum values of various areas.']
            ]
            ['0x0B'
                [meaning =   'Not executable due to RUNmode']
                [causing =   'A command not executable in the RUN mode was sent.']
            ]
            ['0x0C'
                [meaning =   'Not executable due to DEBUG mode']
                [causing =   'A command not executable in the DEBUG mode was sent.']
            ]
            ['0x0D'
                [meaning =   'Not executable due to LOCAL mode']
                [causing =   'A write-related command was sent when the key switch was in the LOCAL position.']
            ]
            ['0x10'
                [meaning =   'Parity error']
                [causing =   'The upper-link detected a parity error when it was receiving the command.']
            ]
            ['0x11'
                [meaning =   'Framing error']
                [causing =   'The upper-link failed to detect the stop bit when it was receiving the command.']
            ]
            ['0x12'
                [meaning =   'Overrun error']
                [causing =   'The upper-link received the next command when it was receiving and processing the command.']
            ]
            ['0x13'
                [meaning =   'FCS error']
                [causing =   'The FCS is wrong.']
            ]
            ['0x14'
                [meaning =   'Format error']
                [causing =   'The command format is wrong.']
            ]
            ['0x15'
                [meaning =   'Numeric data error']
                [causing =   'The read/write enable area was specified wrongly.']
            ]
            ['0x16'
                [meaning =   'No relevant command']
                [causing =   'There is no relevant command in the relevant step.']
            ]
            ['0x18'
                [meaning =   'Frame length over-max. error']
                [causing =   'The frame length exceeds the maximum allowable length.']
            ]
            ['0x19'
                [meaning =   'Not executable']
                [causing =   'Error reset is disabled due to memory error.EEPROM write is disabled, and the registration I/O is yet to be executed.']
            ]
            ['0x20'
                [meaning =   'I/O table create disabled']
                [causing =   'The remote operation is yet to be confirmed, and the channel is over or used redundantly.']
            ]
            ['0x21'
                [meaning =   'Not executable due to an error caused to the CPU in the PC main unit']
                [causing =   'Execution is disabled due to an error caused to the CPU in the PC main unit.']
            ]
            ['0x22'
                [meaning =   'No relevant memory']
                [causing =   'There is no relevant memory unit.']
            ]
            ['0x23'
                [meaning =   'Relevant memory is protected.']
                [causing =   'The PROTECT switch of the relevant memory unit is in the ON position.']
            ]
            ['0xA0'
                [meaning =   'Aborted due to a parity error caused to the transmission data during processing']
                [causing =   'Each error occurred when a command covering plural write-related frames was being executed.']
            ]
            ['0xA1'
                [meaning =   'Aborted due to a framing error caused to the transmission data during processing']
                [causing =   'Each error occurred when a command covering plural write-related frames was being executed.']
            ]
            ['0xA2'
                [meaning =   'Aborted due to an overrun error caused to the transmission data during processing']
                [causing =   'Each error occurred when a command covering plural write-related frames was being executed.']
            ]
            ['0xA3'
                [meaning =   'Aborted due to an FCS error caused to the transmission data during processing']
                [causing =   'Each error occurred when a command covering plural write-related frames was being executed.']
            ]
            ['0xA4'
                [meaning =   'Aborted due to a format error caused to the transmission data during processing']
                [causing =   'Each error occurred when a command covering plural write-related frames was being executed.']
            ]
            ['0xA5'
                [meaning =   'Aborted due to a numeric data error caused to the transmission data during processing']
                [causing =   'Each error occurred when a command covering plural write-related frames was being executed.']
            ]
            ['0xA8'
                [meaning =   'Aborted due to a frame length over-max. error caused to the transmission data during processing']
                [causing =   'Each error occurred when a command covering plural write-related frames was being executed.']
            ]
            ['0xB0'
                [meaning =   'Not executable due to the size of the program area other than 16KB']
                [causing =   'The program area has only 8KB in the C500F.']
            ]
        ]
    ]
]