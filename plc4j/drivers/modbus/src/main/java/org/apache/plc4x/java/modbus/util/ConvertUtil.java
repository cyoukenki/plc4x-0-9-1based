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
package org.apache.plc4x.java.modbus.util;

public class ConvertUtil {

    public static String stringToHexadecimal(String str) {
        StringBuffer sb = new StringBuffer();
        //将字符串转换为字符数组
        char ch[] = str.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();
        return result;
    }
    public static String hexadecimalToString(String str) {
        String result = new String();
        char[] charArray = str.toCharArray();
        for(int i = 0; i < charArray.length; i=i+2) {
            String st = ""+charArray[i]+""+charArray[i+1];
            char ch = (char)Integer.parseInt(st, 16);
            result = result + ch;
        }
       return result;
    }
    public static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
    public static String swap(String str , int i , int j ) {

        StringBuilder strB = new StringBuilder(str);

        char l = strB.charAt(i) , r = strB.charAt(j);

        strB.setCharAt(i,r);

        strB.setCharAt(j,l);

        return strB.toString();

    }
    public static void main(String[] args) {
    }
}
