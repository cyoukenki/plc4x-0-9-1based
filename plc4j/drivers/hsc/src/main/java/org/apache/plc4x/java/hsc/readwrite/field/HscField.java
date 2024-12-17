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
package org.apache.plc4x.java.hsc.readwrite.field;

import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.api.model.PlcField;
import org.apache.plc4x.java.spi.generation.ParseException;
import org.apache.plc4x.java.spi.generation.WriteBuffer;
import org.apache.plc4x.java.spi.utils.Serializable;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HscField implements PlcField, Serializable {

    private static final Pattern ADDRESS_PATTERN =
        Pattern.compile("^(?<workFlow>[a-zA-Z_.0-9]*):(?<reqContent>[\\s\\S]*)");
    private static final String WORKFLOW = "workFlow";
    private static final String REQ_CONTENT = "reqContent";
    private String workFlow;
    private String reqContent;


    public String getWorkFlow() {
        return workFlow;
    }

    public void setWorkFlow(String workFlow) {
        this.workFlow = workFlow;
    }

    public String getReqContent() {
        return reqContent;
    }

    public void setReqContent(String reqContent) {
        this.reqContent = reqContent;
    }

    public HscField(String workFlow) {
        this.workFlow = workFlow;
    }

    public HscField(String workFlow, String reqContent) {
        this.workFlow = workFlow;
        this.reqContent = reqContent;
    }


    public static boolean matches(String fieldQuery) {
        return ADDRESS_PATTERN.matcher(fieldQuery).matches();
    }

    public static HscField of(String fieldString) {
        Matcher matcher = ADDRESS_PATTERN.matcher(fieldString);
        if (matcher.matches()) {
            String workFlow = matcher.group(WORKFLOW);
            String reqContent =null;
            if (!matcher.group(REQ_CONTENT).isEmpty()) {
                reqContent = matcher.group(REQ_CONTENT);
            }
            if (StringUtils.isNoneEmpty(reqContent)) {
                return new HscField(workFlow, reqContent);
            } else {
                return new HscField(workFlow);
            }
        }
        return null;
    }



    @Override
    public void serialize(WriteBuffer writeBuffer) throws ParseException {
        writeBuffer.pushContext(getClass().getSimpleName());
        writeBuffer.writeString("workFlow", workFlow.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), workFlow);
        writeBuffer.writeString("reqContent", reqContent.getBytes(StandardCharsets.UTF_8).length * 8, StandardCharsets.UTF_8.name(), reqContent);
        // TODO: remove this (not language agnostic)
        writeBuffer.popContext(getClass().getSimpleName());
    }

}
