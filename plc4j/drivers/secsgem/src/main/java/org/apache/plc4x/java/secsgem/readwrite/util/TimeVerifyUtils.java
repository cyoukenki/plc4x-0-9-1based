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
package org.apache.plc4x.java.secsgem.readwrite.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeVerifyUtils {
    public static String pattern16 = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8])))([0-1]?[0-9]|2[0-3])([0-5][0-9])([0-5][0-9])([0-9][0-9])";
    public static String pattern12 = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-8])))([0-1]?[0-9]|2[0-3])([0-5][0-9])";
    public static boolean verifyTime(String time) {
        Pattern r;
        if(time.length()==16){
            r = Pattern.compile(pattern16);
            Matcher m = r.matcher(time);
            return m.matches();
        } else if(time.length()==12) {
            r = Pattern.compile(pattern12);
            Matcher m = r.matcher(time);
            return m.matches();
        }
        return false;
    }

    public static void main(String[] args) {
        boolean b = verifyTime("2024010100000000");

    }
}
