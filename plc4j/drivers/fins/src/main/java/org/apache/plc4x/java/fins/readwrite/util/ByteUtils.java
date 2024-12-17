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
package org.apache.plc4x.java.fins.readwrite.util;

import java.util.HashMap;
import java.util.Map;

public class ByteUtils {
    private static final Map<String, String> DIGIT_MAP = new HashMap<>();
    static {
        DIGIT_MAP.put("0","0000");
        DIGIT_MAP.put("1","0001");
        DIGIT_MAP.put("2","0010");
        DIGIT_MAP.put("3","0011");
        DIGIT_MAP.put("4","0100");
        DIGIT_MAP.put("5","0101");
        DIGIT_MAP.put("6","0110");
        DIGIT_MAP.put("7","0111");
        DIGIT_MAP.put("8","1000");
        DIGIT_MAP.put("9","1001");
        DIGIT_MAP.put("a","1010");
        DIGIT_MAP.put("b","1011");
        DIGIT_MAP.put("c","1100");
        DIGIT_MAP.put("d","1101");
        DIGIT_MAP.put("e","1110");
        DIGIT_MAP.put("f","1111");
    }
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    /**
     * 字节数组转16进制
     * @param data
     * @return
     */
    public static String byteToHex(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars);
    }
    public static int byteArrayToInt(byte[] bytes){
        int res = 0;
        for (int i = 0; i < bytes.length; i++) {
            res += (bytes[i] & 0xff) << i*8;
        }
        return res;
    }
    // int转byte
    public static byte[] intToBytes(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }

    //这个函数将float转换成byte[]
    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }
        return dest;
    }

    //这个函数将byte转换成float
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }
    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }
    public static String getBit(byte by){

        StringBuffer sb = new StringBuffer();

        sb.append((by>>7)&0x1)

            .append((by>>6)&0x1)

            .append((by>>5)&0x1)

            .append((by>>4)&0x1)

            .append((by>>3)&0x1)

            .append((by>>2)&0x1)

            .append((by>>1)&0x1)

            .append((by>>0)&0x1);

        return sb.toString();

    }
    public static void main(String[] args) {
        System.out.println(getBit((byte) 65471111));
        String s = byteToHex(new byte[]{0, 2,34,34});
    }
}
