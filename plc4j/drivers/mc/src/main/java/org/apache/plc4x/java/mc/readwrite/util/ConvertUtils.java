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
package org.apache.plc4x.java.mc.readwrite.util;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {
    public static void main(String[] args) {
        String aaaa = charToHex("aaaa");
        System.out.println(aaaa);
    }

    public static String hexToChar(String hexStr) {
        int length = 2;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hexStr.length(); i += length) {
            String hexSplit;
            if ((i + length) <= hexStr.length()) {
                hexSplit = hexStr.substring(i, i + length);
            } else {
                hexSplit = hexStr.substring(i);
            }
            int iValue = Integer.parseInt(hexSplit, 16);// 将十六进制数转换为十进制数
            char character = (char) iValue; // 将十进制数转换为字符
            stringBuilder.append(character);
        }
        return stringBuilder.toString();
    }

    public static String charToHex(String hexStr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hexStr.length(); i++) {
            char c = hexStr.charAt(i); // 获取当前位置上的字符
            int asciiCode = (int) c; // 将字符转换为对应的ASCII码值
            stringBuilder.append(Integer.toHexString(asciiCode));
        }
        return stringBuilder.toString();
    }

    public static long calculateRequestDateLength(int length) {
        String hexValue = charToHex(String.format("%04x", Integer.valueOf(length - 18)));
        return Long.parseLong(hexValue, 16);
    }

    public static long calculateResponseDateLength(long length) {
        return Long.parseLong(hexToChar(Long.toHexString(length)), 16) - 4;
    }

    public static long calculateWriteDateLength(long length) {
        return Long.parseLong(hexToChar(Long.toHexString(length)), 16);
    }

    public static List<String> splitString(String str, int len) {
        List<String> result = new ArrayList<>();
        int start = 0;
        while (start < str.length()) {
            int end = Math.min(start + len, str.length());
            String subStr = str.substring(start, end);
            result.add(subStr);
            start += len;
        }
        return result;
    }


    public static String reverseResult(String data, int num) {
        String result = data;
        if (num == 4) {
            int length = data.length();
            if (length >= 8) {
                String frontFour = data.substring(0, 4); // 提取前四位
                String backFour = data.substring(length - 4); // 提取后四位
                result = backFour + frontFour;
            }
        }
        if (num == 8) {
            int length = data.length();
            if (length >= 16) {
                String frontFour = data.substring(0, 4); // 提取前四位
                String secFour = data.substring(4, 8); // 提取后四位
                String trdFour = data.substring(8, 12); // 提取后四位
                String backFoure = data.substring(12, 16); // 提取后四位
                result = backFoure + trdFour + secFour + frontFour;
            }
        }
        return result;
    }

    public static String hexToAscii(String hexStr) {
        String result;
        byte[] bytes = new BigInteger(hexStr, 16).toByteArray();
        StringBuilder asciiBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length && bytes[i] != 0x00; ++i) {
            char c = (char) bytes[i];
            asciiBuilder.append(c);
        }
        result = asciiBuilder.toString();
        return result;
    }

    public static String swapCharacters(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < str.length() - 1; i += 2) {
            // 交换第i个和第i+1个字符
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(i + 1));
            sb.setCharAt(i + 1, temp);
        }

        return sb.toString();
    }
}
