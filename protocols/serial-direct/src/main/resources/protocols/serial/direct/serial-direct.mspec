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

[type   'SerialDirect'
    [simple string '255*10' 'UTF-8' 'pcb']
]





[enum uint   16   'SecsDataTypeCode' [uint 8  'size']
  ['0X000L'   List            ['01']]
    ['0X000B'   Byte            ['21']]
    ['0X0025'   Bool            ['25']]
    ['0X000A'   STRING256       ['41']]
    ['0X0000'   SINT            ['65']]
    ['0X0000'   INT             ['69']]
    ['0X0000'   DINT            ['71']]
    ['0X0000'   REAL            ['91']]
    ['0X0000'   LREAL           ['81']]
    ['0X0000'   USINT           ['0xa5']]
    ['0X0000'   UINT            ['9']]
    ['0X0000'   UDINT           ['1']]
]
