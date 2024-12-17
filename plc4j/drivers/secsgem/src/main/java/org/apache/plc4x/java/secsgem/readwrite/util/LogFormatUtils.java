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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.secsgem.readwrite.*;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode;
import org.apache.plc4x.java.secsgem.readwrite.types.SecsDataTypeCode2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFormatUtils {
    private static String endStr = "\r\n";
    private static final String SECS_TITLE = "SECS II ";
    private static final Logger logger = LoggerFactory.getLogger(LogFormatUtils.class);

    public static String formatSecsPacketReceiveData(SecsPacket secsPacket) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SECS_TITLE + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + " ");
        if (secsPacket instanceof HostSeparateRepuest) {
            stringBuilder.append(appendStrWithEnter("Received HostSeparate request,system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F1Request) {
            stringBuilder.append(appendStrWithEnter("Receive AreYouThere:S1F1, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F3Request) {
//                               <L,1 [SVIDCOUNT]
//                                    <I2,1 '0' [SVID]>
//                               >.
            stringBuilder.append(appendStrWithEnter("S1F3"));
            S1F3Request secsPacket1 = (S1F3Request) secsPacket;
            DataStructNormal[] dataStructs = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            stringBuilder.append(formatDataStructs(dataStructs, 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F11Request) {
            S1F11Request secsPacket1 = (S1F11Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("S1F11"));
            DataStructNormal[] dataStructs = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            stringBuilder.append(formatDataStructs(dataStructs, 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F13Request) {
            S1F13Request secsPacket1 = (S1F13Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("Receive S1F13, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F15Request) {
            S1F15Request secsPacket1 = (S1F15Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("Receive S1F15, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F17Request) {
            S1F17Request secsPacket1 = (S1F17Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("Receive S1F17, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F13Request) {
            S2F13Request secsPacket1 = (S2F13Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("S2F13"));
            DataStructNormal[] dataStructs = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            stringBuilder.append(formatDataStructs(dataStructs, 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F15Request) {
            S2F15Request secsPacket1 = (S2F15Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("S2F15"));
            ECID1DataStruct[] dataStructs = secsPacket1.getValues();
            stringBuilder.append(formatECID1DataStructs(dataStructs, 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F17Request) {
            S2F17Request secsPacket1 = (S2F17Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("Receive DateTimeRequest, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F29Request) {
            S2F29Request secsPacket1 = (S2F29Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("S2F29"));
            DataStructNormal[] dataStructs = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            stringBuilder.append(formatDataStructs(dataStructs, 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F19Request) {
            S7F19Request secsPacket1 = (S7F19Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("Receive S7F19, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F19Request) {
            S6F2Response secsPacket1 = (S6F2Response) secsPacket;
            stringBuilder.append(appendStrWithEnter("Receive S6F2, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        stringBuilder.append(appendStrWithEnter("Received expected secondary SECS message, system byte = " + secsPacket.getLengthInBytes() + ", device ID " + secsPacket.getDeviceID()));
        stringBuilder.append(appendStrWithEnter("Received SECS II message -"));
        if (secsPacket instanceof S2F31Request) {
            stringBuilder.append(appendStrWithEnter("S2F31"));
            S2F31Request secsPacket1 = (S2F31Request) secsPacket;
//            <A,12 '202202020202'>
            DataStructNormal ctime = (DataStructNormal) secsPacket1.getCtime();
            stringBuilder.append(appendStrWithEnter(formatDataStruct(ctime, 1)));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F23Request) {
            stringBuilder.append(appendStrWithEnter("S2F23"));
            S2F23Request secsPacket1 = (S2F23Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTrid(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getDsper(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTotsmp(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getRepgsz(), 2));
            DataStructNormal[] svidDatas = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            stringBuilder.append(formatDataStructs(svidDatas, 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F33Request) {

//               <L,2
//                    <U2,1 '0' [DATAID]>
//                                       <L,1 [RPTIDCOUNT]
//                    <L,2
//                    <U2,1 '0' [RPTID]>
//                                               <L,1 [VIDCOUNT]
//                    <U2,1 '0' [VID]>
//                                               >
//                                           >
//                                       >
//               >.
            stringBuilder.append(appendStrWithEnter("S2F33"));
            S2F33Request secsPacket1 = (S2F33Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getDataId(), 2));
            RPTIDListDataStruct[] reportDatas = secsPacket1.getValues();
//            ReportData[] reportDatas = secsPacket1.getReportData();
            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter("<L"), appendStrWithEnter("<L").length() + 8));
            stringBuilder.append(formatReportDatas(reportDatas, 3));
            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter(">"), appendStrWithEnter(">").length() + 8));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F35Request) {
//            <L,2
//                <U2,1 '0' [DATAID]>
//                                   <L,1 [CEIDCOUNT]
//                <L,2
//                <U2,1 '0' [CEID]>
//                                           <L,1 [RPTIDCOUNT]
//                <U2,1 '0' [RPTID]>
//                                           >
//                                       >
//                                   >
//                               >.
            stringBuilder.append(appendStrWithEnter("S2F35"));
            S2F35Request secsPacket1 = (S2F35Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getDataId(), 2));
            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter("<L"), appendStrWithEnter("<L").length() + 8));
            stringBuilder.append(formatCeidDatas(secsPacket1.getValues(), 3));
            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter(">"), appendStrWithEnter(">").length() + 8));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();

        }
        if (secsPacket instanceof S2F37Request) {
//
//                               <L,2
//                <BOOLEAN,1 '0' [CEED]>
//                                   <L,1 [CEIDCOUNT]
//                <U2,1 '0' [CEID]>
//                                   >
//                               >.
            stringBuilder.append(appendStrWithEnter("S2F37"));
            S2F37Request secsPacket1 = (S2F37Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getCEED(), 2));
            stringBuilder.append(formatDataStructs(SecsDataUtils.convertToDataStructs(secsPacket1.getValues()), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();

        }
        if (secsPacket instanceof S2F41Request) {
//            <L,2
//                <A,0 '' [RCMD]>
//                                   <L,1 [CPCOUNT]
//                <L,2
//                <A,0 '' [CPNAME]>
//                                           <A,0 '' [CPVAL]>
//                                       >
//                                   >
//                               >.
            stringBuilder.append(appendStrWithEnter("S2F41"));
            S2F41Request secsPacket1 = (S2F41Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getRcmd(), 2));
            stringBuilder.append(formatParaDatas(secsPacket1.getValues(), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();

        }
        if (secsPacket instanceof S5F5Request) {
            S5F5Request secsPacket1 = (S5F5Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("S5F5"));
            byte[] values = secsPacket1.getValues();
            short dataType = secsPacket1.getDataType();
            stringBuilder.append(formatPPBODYData(dataType, values, 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S5F7Request) {
            S5F7Request secsPacket1 = (S5F7Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("S5F7"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F1Request) {

//                               <L,2
//                <A,0 '' [PPID]>
//                                   <U2,1 '0' [LENGTH]>
//                               >.
            stringBuilder.append(appendStrWithEnter("S7F1"));
            S7F1Request secsPacket1 = (S7F1Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getPPID(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getLENGTH(), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F3Request) {
            stringBuilder.append(appendStrWithEnter("S7F3"));
            S7F3Request secsPacket1 = (S7F3Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getPpid(), 2));
            stringBuilder.append(formatPPBODYData(secsPacket1.getDataType(), secsPacket1.getData(), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F5Request) {
            stringBuilder.append(appendStrWithEnter("S7F5"));
            S7F5Request secsPacket1 = (S7F5Request) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getPPID(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F17Request) {
            stringBuilder.append(appendStrWithEnter("S7F17"));
            S7F17Request secsPacket1 = (S7F17Request) secsPacket;
            stringBuilder.append(formatDataStructs(SecsDataUtils.convertToDataStructs(secsPacket1.getValues()), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }

        if (secsPacket instanceof S5F3Request) {
            stringBuilder.append(appendStrWithEnter("S5F3"));
            S5F3Request secsPacket1 = (S5F3Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAled(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAled(), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S10F3Request) {
            stringBuilder.append(appendStrWithEnter("S10F3"));
            S10F3Request secsPacket1 = (S10F3Request) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTid(), 1));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getText(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F12Response) {
            stringBuilder.append(appendStrWithEnter("S6F12"));
            S6F12Response secsPacket1 = (S6F12Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getHcack(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F15Request) {
            stringBuilder.append(appendStrWithEnter("S6F15"));
            S6F15Request secsPacket1 = (S6F15Request) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getCeid(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F16Response) {
            stringBuilder.append(appendStrWithEnter("S6F16"));
            S6F16Response secsPacket1 = (S6F16Response) secsPacket;

            stringBuilder.append(appendStrWithEnter("<L"));
            DataStructNormal dataId = (DataStructNormal) secsPacket1.getDataId();
            DataStructNormal ceId = (DataStructNormal) secsPacket1.getCeId();
            stringBuilder.append(formatRPTIDDataStructs(secsPacket1.getValues(), 1));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F19Request) {
            stringBuilder.append(appendStrWithEnter("S6F19"));
            S6F19Request secsPacket1 = (S6F19Request) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getRptid(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S10F2Response) {
            stringBuilder.append(appendStrWithEnter("S10F2"));
            S10F2Response secsPacket1 = (S10F2Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAckc10(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S10F5Request) {
            stringBuilder.append(appendStrWithEnter("S10F5"));
            S10F5Request secsPacket1 = (S10F5Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTID(), 1));
            stringBuilder.append(formatDataStructs(SecsDataUtils.convertToDataStructs(secsPacket1.getValues()), 1));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S10F6Reply) {
            stringBuilder.append(appendStrWithEnter("S10F5"));
            S10F6Reply secsPacket1 = (S10F6Reply) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getACKC10(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F2Response) {
            stringBuilder.append(appendStrWithEnter("S1F2"));
            S1F2Response secsPacket1 = (S1F2Response) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            DataStructNormal[] values = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            for (int j = 0; j < values.length; j++) {
//                stringBuilder.append(formatDataStruct(secsPacket1.getMdln(),2));
//                stringBuilder.append(formatDataStruct(secsPacket1.getSoftrev(),2));
                stringBuilder.append(formatDataStruct(values[j], 2));
            }
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof HostLinktestResponse) {
            HostLinktestResponse secsPacket1 = (HostLinktestResponse) secsPacket;
            stringBuilder.append(appendStrWithEnter("receive control message - Linktest response,system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S5F2Response) {
            stringBuilder.append(appendStrWithEnter("S5F2"));
            S5F2Response secsPacket1 = (S5F2Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAck5(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        return null;
    }


    private static StringBuffer formatParaDatas(ParametersData[] paraDatas, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < paraDatas.length; i++) {
            ParametersData paraData = paraDatas[i];
            DataStructNormal rptId = (DataStructNormal) paraData.getCpname();
            stringBuffer.append(formatDataStruct(rptId, index + 1));
            DataStructNormal cpval = (DataStructNormal) paraData.getCpval();
            stringBuffer.append(formatDataStruct(cpval, index + 1));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatCeidDatas(CEIDListDataStruct[] ceidDatas, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < ceidDatas.length; i++) {
            CEIDListDataStruct ceidData = ceidDatas[i];
            DataStructNormal ceid = (DataStructNormal) ceidData.getCEID();
            stringBuffer.append(formatDataStruct(ceid, index + 1));
            DataStructNormal[] values = SecsDataUtils.convertToDataStructs(ceidData.getValues());
            stringBuffer.append(formatDataStructs(values, index + 1));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatReportDatas(RPTIDListDataStruct[] reportDatas, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < reportDatas.length; i++) {
            RPTIDListDataStruct reportData = reportDatas[i];
            DataStructNormal rptId = (DataStructNormal) reportData.getRptId();
            stringBuffer.append(formatDataStruct(rptId, index + 1));
            DataStructNormal[] values = SecsDataUtils.convertToDataStructs(reportData.getValues());
            stringBuffer.append(formatDataStructs(values, 4));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatDataStructs(DataStructNormal[] dataStructs, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < dataStructs.length; i++) {
            stringBuffer.append(formatDataStruct(dataStructs[i], index + 1));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatAlarmDataStructs(AlarmStruct[] dataStructs, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < dataStructs.length; i++) {
            stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index)));
            AlarmStruct dataStruct = dataStructs[i];
            DataStructNormal alcd = (DataStructNormal) dataStruct.getALCD();
            DataStructNormal alid = (DataStructNormal) dataStruct.getALID();
            DataStructNormal altx = (DataStructNormal) dataStruct.getALTX();
            stringBuffer.append(formatDataStruct(alcd, index + 2));
            stringBuffer.append(formatDataStruct(alid, index + 2));
            stringBuffer.append(formatDataStruct(altx, index + 2));
            stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index)));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatRPTIDDataStructs(RPTIDListDataStruct[] dataStructs, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < dataStructs.length; i++) {
            stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index)));
            RPTIDListDataStruct dataStruct = dataStructs[i];
            DataStructNormal rptId = (DataStructNormal) dataStruct.getRptId();
            stringBuffer.append(formatDataStruct(rptId, index + 1));
            stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index + 1)));
            DataStructNormal[] values = SecsDataUtils.convertToDataStructs(dataStruct.getValues());
            for (int j = 0; j < values.length; i++) {
                stringBuffer.append(formatDataStruct(values[j], index + 2));
            }
            stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index + 1)));
            stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index)));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatSVIDDataStructs(SVIDDataStruct[] dataStructs, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < dataStructs.length; i++) {
            stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index)));
            SVIDDataStruct dataStruct = dataStructs[i];
            DataStructNormal svid = (DataStructNormal) dataStruct.getSVID();
            DataStructNormal svname = (DataStructNormal) dataStruct.getSVNAME();
            DataStructNormal units = (DataStructNormal) dataStruct.getUNITS();
            stringBuffer.append(formatDataStruct(svid, index + 2));
            stringBuffer.append(formatDataStruct(svname, index + 2));
            stringBuffer.append(formatDataStruct(units, index + 2));
            stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index)));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatECID2DataStructs(ECID2DataStruct[] dataStructs, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < dataStructs.length; i++) {
            StringBuffer append = stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index)));
            ECID2DataStruct dataStruct = dataStructs[i];
            DataStructNormal svid = (DataStructNormal) dataStruct.getECID();
            DataStructNormal svname = (DataStructNormal) dataStruct.getECNAME();
            DataStructNormal ecmin = (DataStructNormal) dataStruct.getECMIN();
            DataStructNormal ecmax = (DataStructNormal) dataStruct.getECMAX();
            DataStructNormal ecdf = (DataStructNormal) dataStruct.getECDEF();
            DataStructNormal units = (DataStructNormal) dataStruct.getUNITS();
            stringBuffer.append(formatDataStruct(svid, index + 2));
            stringBuffer.append(formatDataStruct(svname, index + 2));
            stringBuffer.append(formatDataStruct(ecmin, index + 2));
            stringBuffer.append(formatDataStruct(ecmax, index + 2));
            stringBuffer.append(formatDataStruct(ecdf, index + 2));
            stringBuffer.append(formatDataStruct(units, index + 2));
            stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index)));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static StringBuffer formatECID1DataStructs(ECID1DataStruct[] dataStructs, int index) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index - 1)));
        for (int i = 0; i < dataStructs.length; i++) {
            StringBuffer append = stringBuffer.append(StringUtils.leftPad("<L\r\n", "<L\r\n".length() + 8 * (index)));
            ECID1DataStruct dataStruct = dataStructs[i];
            DataStructNormal svid = (DataStructNormal) dataStruct.getValues()[0];
            DataStructNormal ecv = (DataStructNormal) dataStruct.getValues()[1];
            stringBuffer.append(formatDataStruct(svid, index + 2));
            stringBuffer.append(formatDataStruct(ecv, index + 2));
            stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index)));
        }
        stringBuffer.append(StringUtils.leftPad(">\r\n", ">\r\n".length() + 8 * (index - 1)));
        return stringBuffer;
    }

    private static String formatDataStruct(DataStructNormal dataStruct, int index) {
        SecsDataTypeCode dataType = dataStruct.getDataType();
        String dtaType_p = null;
        switch (dataType) {
            case BYTE:
                dtaType_p = "B";
                break;
            case LIST:
                dtaType_p = "L";
                break;
            case BOOL:
                dtaType_p = "Boolean";
                break;
            case STRING:
                dtaType_p = "A";
                break;
            case SINT:
                dtaType_p = "I1";
                break;
            case INT:
                dtaType_p = "I2";
                break;
            case DINT:
                dtaType_p = "I4";
                break;
            case REAL:
                dtaType_p = "F4";
                break;
            case LREAL:
                dtaType_p = "F8";
                break;
            case USINT:
                dtaType_p = "U1";
                break;
            case UINT:
                dtaType_p = "U2";
                break;
            case UDINT:
                dtaType_p = "U4";
                break;
            case LINT:
                dtaType_p = "I8";
                break;
            case ULINT:
                dtaType_p = "U8";
                break;
        }
        String data;
        if (dataStruct.getData().length == 0) {
            data = "<" + dtaType_p + ">\r\n";
        } else {
            data = "<" + dtaType_p + " " + SecsDataUtils.parseDataToObject(dataType, dataStruct.getData()).toString() + ">\r\n";
        }
        return StringUtils.leftPad(data, data.length() + 8 * (index - 1));
    }

    private static String formatPPBODYData(short dataType, byte[] bytes, int index) {
        SecsDataTypeCode2 dataType2 = SecsDataTypeCode2.enumForValue(dataType);
        String dtaType_p = null;
        switch (dataType2) {
            case BYTE:
                dtaType_p = "B";
                break;
            case BOOL:
                dtaType_p = "Boolean";
                break;
            case STRING:
                dtaType_p = "A";
                break;
            case SINT:
                dtaType_p = "I1";
                break;
            case INT:
                dtaType_p = "I2";
                break;
            case DINT:
                dtaType_p = "I4";
                break;
            case REAL:
                dtaType_p = "F4";
                break;
            case LREAL:
                dtaType_p = "F8";
                break;
            case USINT:
                dtaType_p = "U1";
                break;
            case UINT:
                dtaType_p = "U2";
                break;
            case UDINT:
                dtaType_p = "U4";
                break;
            case LINT:
                dtaType_p = "I8";
                break;
            case ULINT:
                dtaType_p = "U8";
                break;
        }
        String data;
        if (bytes.length == 0) {
            data = "<" + dtaType_p + ">\r\n";
        } else {
            data = "<" + dtaType_p + " " + SecsDataUtils.parseDataToObject(SecsDataTypeCode.valueOf(dataType2.name()), bytes).toString() + ">\r\n";
        }
        return StringUtils.leftPad(data, data.length() + 8 * (index - 1));
    }

    private static String appendStrWithEnter(String content) {
        return content + "\r\n";
    }

    public static String formatSecsPacketSendData(SecsPacket secsPacket) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SECS_TITLE + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + " ");
        if (secsPacket instanceof EqRejectRepuest) {
            stringBuilder.append(appendStrWithEnter("Sending control message - RejectRepuest  sent, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F1Request) {
            stringBuilder.append(appendStrWithEnter("Send AreYouThere:S1F1, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof SelectResponse) {
            stringBuilder.append(appendStrWithEnter("Send control message - Select response 0 - select was succesful"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof HostDeselectResponse) {
            stringBuilder.append(appendStrWithEnter("Send control message - Deselect response 0 - select was succesful"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof HostLinktestResponse) {
            stringBuilder.append(appendStrWithEnter("Send control message - Linktest response,Linktest response sent, system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S1F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S2F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S3F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S3F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S4F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S4F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S5F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S5F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S6F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S7F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S8F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S8F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S9F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S9F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S10F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S10F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S11F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S11F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S12F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S12F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S13F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S13F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S14F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S14F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S15F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S15F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S16F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S16F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S17F0Response) {
            stringBuilder.append(appendStrWithEnter("Send S17F0 - system byte = " + secsPacket.getLengthInBytes()));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        stringBuilder.append(appendStrWithEnter("Send expected secondary SECS message, system byte = " + secsPacket.getLengthInBytes() == null ? "0" : secsPacket.getLengthInBytes() + ", device ID " + secsPacket.getDeviceID()));
        stringBuilder.append(appendStrWithEnter("Preparing to send SECS II message -"));
        if (secsPacket instanceof S1F2Response) {
            stringBuilder.append(appendStrWithEnter("S1F2"));
            S1F2Response secsPacket1 = (S1F2Response) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            DataStructNormal[] values = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            for (int i = 0; i < values.length; i++) {
//                stringBuilder.append(formatDataStruct(secsPacket1.getMdln(),2));
//                stringBuilder.append(formatDataStruct(secsPacket1.getSoftrev(),2));
                stringBuilder.append(formatDataStruct(values[i], 2));
            }
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F14Response) {
            stringBuilder.append(appendStrWithEnter("S1F14"));
            S1F14Response secsPacket1 = (S1F14Response) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getCommack(), 2));
            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter("<L"), appendStrWithEnter("<L").length() + 8));
            DataStructNormal[] values = SecsDataUtils.convertToDataStructs(secsPacket1.getValues());
            if (values.length == 2) {
//                stringBuilder.append(formatDataStruct(secsPacket1.getMdln(),3));
//                stringBuilder.append(formatDataStruct(secsPacket1.getSoftrev(),3));
                stringBuilder.append(formatDataStruct(values[0], 3));
                stringBuilder.append(formatDataStruct(values[1], 3));
            }

            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter(">"), appendStrWithEnter(">").length() + 8));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F4Response) {
            stringBuilder.append(appendStrWithEnter("S1F4"));
            S1F4Response secsPacket1 = (S1F4Response) secsPacket;
            stringBuilder.append(formatDataStructs(SecsDataUtils.convertToDataStructs(secsPacket1.getValues()), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F12Response) {
            stringBuilder.append(appendStrWithEnter("S1F12"));
            S1F12Response secsPacket1 = (S1F12Response) secsPacket;
            stringBuilder.append(formatSVIDDataStructs(secsPacket1.getValues(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F14Response) {
            stringBuilder.append(appendStrWithEnter("S2F14"));
            S2F14Response secsPacket1 = (S2F14Response) secsPacket;
            stringBuilder.append(formatDataStructs(SecsDataUtils.convertToDataStructs(secsPacket1.getValues()), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F16Response) {
            stringBuilder.append(appendStrWithEnter("S2F16"));
            S2F16Response secsPacket1 = (S2F16Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getEAC(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F18Response) {
            stringBuilder.append(appendStrWithEnter("S2F18"));
            S2F18Response secsPacket1 = (S2F18Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getCtime(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F30Response) {
            stringBuilder.append(appendStrWithEnter("S2F30"));
            S2F30Response secsPacket1 = (S2F30Response) secsPacket;
            stringBuilder.append(formatECID2DataStructs(secsPacket1.getValues(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F32Response) {
            stringBuilder.append(appendStrWithEnter("S2F32"));
            S2F32Response secsPacket1 = (S2F32Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTiack(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F24Response) {
            stringBuilder.append(appendStrWithEnter("S2F24"));
            S2F24Response secsPacket1 = (S2F24Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTiaack(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F34Response) {
            stringBuilder.append(appendStrWithEnter("S2F34"));
            S2F34Response secsPacket1 = (S2F34Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getDRACK(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F36Response) {
            stringBuilder.append(appendStrWithEnter("S2F36"));
            S2F36Response secsPacket1 = (S2F36Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getLRACK(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F38Reply) {
            stringBuilder.append(appendStrWithEnter("S2F38"));
            S2F38Reply secsPacket1 = (S2F38Reply) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getERACK(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S2F42Response) {
            stringBuilder.append(appendStrWithEnter("S2F42"));
            S2F42Response secsPacket1 = (S2F42Response) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getHcack(), 2));
            stringBuilder.append(formatParaDatas(secsPacket1.getValues(), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F18Response) {
            stringBuilder.append(appendStrWithEnter("S7F18"));
            S7F18Response secsPacket1 = (S7F18Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getHcack(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F20Response) {
            stringBuilder.append(appendStrWithEnter("S7F20"));
            S7F20Response secsPacket1 = (S7F20Response) secsPacket;
            stringBuilder.append(formatDataStructs(SecsDataUtils.convertToDataStructs(secsPacket1.getValues()), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F16Response) {
            stringBuilder.append(appendStrWithEnter("S1F16"));
            S1F16Response secsPacket1 = (S1F16Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getOflack(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S1F18Response) {
            stringBuilder.append(appendStrWithEnter("S1F18"));
            S1F18Response secsPacket1 = (S1F18Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getOnlack(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S5F4Response) {
            stringBuilder.append(appendStrWithEnter("S5F4"));
            S5F4Response secsPacket1 = (S5F4Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAckc5(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S5F6Response) {
            stringBuilder.append(appendStrWithEnter("S5F6"));
            S5F6Response secsPacket1 = (S5F6Response) secsPacket;
            stringBuilder.append(formatAlarmDataStructs(secsPacket1.getValues(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S5F8Response) {
            stringBuilder.append(appendStrWithEnter("S5F8"));
            S5F8Response secsPacket1 = (S5F8Response) secsPacket;
            stringBuilder.append(formatAlarmDataStructs(secsPacket1.getValues(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F2Response) {
            stringBuilder.append(appendStrWithEnter("S7F2"));
            S7F2Response secsPacket1 = (S7F2Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getPPGNT(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F16Response) {
            stringBuilder.append(appendStrWithEnter("S6F16"));
            S6F16Response secsPacket1 = (S6F16Response) secsPacket;

            stringBuilder.append(appendStrWithEnter("<L"));
            DataStructNormal dataId = (DataStructNormal) secsPacket1.getDataId();
            DataStructNormal ceId = (DataStructNormal) secsPacket1.getCeId();
            stringBuilder.append(formatRPTIDDataStructs(secsPacket1.getValues(), 1));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F4Response) {
            stringBuilder.append(appendStrWithEnter("S7F4"));
            S7F4Response secsPacket1 = (S7F4Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getACKC7(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F6Response) {
            stringBuilder.append(appendStrWithEnter("S7F6"));
            S7F6Response secsPacket1 = (S7F6Response) secsPacket;
            stringBuilder.append(formatPPBODYData(secsPacket1.getDataType(), secsPacket1.getData(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S7F6ResponseL) {
            stringBuilder.append(appendStrWithEnter("S7F6"));
            S7F6ResponseL secsPacket1 = (S7F6ResponseL) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getPPBODY(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S10F4Response) {
            stringBuilder.append(appendStrWithEnter("S10F4"));
            S10F4Response secsPacket1 = (S10F4Response) secsPacket;
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAckc10(), 1));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S9F1Response) {
            stringBuilder.append(appendStrWithEnter("S9F1"));
            S9F1Response secsPacket1 = (S9F1Response) secsPacket;
            stringBuilder.append("Send S9F1 response,system byte =" + secsPacket.getLengthInBytes());
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S9F3Response) {
            stringBuilder.append(appendStrWithEnter("S9F3"));
            S9F3Response secsPacket1 = (S9F3Response) secsPacket;
            short[] revData = secsPacket1.getRevData();
            stringBuilder.append("Send S9F3 response,system byte =" + secsPacket.getLengthInBytes());
            stringBuilder.append("<B " + revData.length + " \'" + revData.toString() + "\'>");
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S9F5Response) {
            stringBuilder.append(appendStrWithEnter("S9F5"));
            S9F5Response secsPacket1 = (S9F5Response) secsPacket;
            short[] revData = secsPacket1.getRevData();
            stringBuilder.append("Send S9F5 response,system byte =" + secsPacket.getLengthInBytes());
            stringBuilder.append("<B " + revData.length + " \'" + revData.toString() + "\'>");
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S9F7Response) {
            stringBuilder.append(appendStrWithEnter("S9F7"));
            S9F7Response secsPacket1 = (S9F7Response) secsPacket;
            short[] revData = secsPacket1.getRevData();
            stringBuilder.append("Send S9F7 response,system byte =" + secsPacket.getLengthInBytes());
            stringBuilder.append("<B " + revData.length + " \'" + revData.toString() + "\'>");
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F11Request) {
            stringBuilder.append(appendStrWithEnter("S6F11"));
            S6F11Request secsPacket1 = (S6F11Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getDataId(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getCeId(), 2));
            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter("<L"), appendStrWithEnter("<L").length() + 8));
            stringBuilder.append(formatReportDatas(secsPacket1.getValues(), 3));
            stringBuilder.append(StringUtils.leftPad(appendStrWithEnter(">"), appendStrWithEnter(">").length() + 8));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S10F1Request) {
            stringBuilder.append(appendStrWithEnter("S10F1"));
            S10F1Request secsPacket1 = (S10F1Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTid(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getText(), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof EqLinktestRequest) {
            stringBuilder.append(stringBuilder.append(appendStrWithEnter("Sending control message - Linktest response,Linktest response sent, system byte = " + secsPacket.getLengthInBytes())));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S5F1Request) {
            stringBuilder.append(appendStrWithEnter("S5F1"));
            S5F1Request secsPacket1 = (S5F1Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAlcd(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAlid(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getAltx(), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        if (secsPacket instanceof S6F1Request) {
            stringBuilder.append(appendStrWithEnter("S6F1"));
            S6F1Request secsPacket1 = (S6F1Request) secsPacket;
            stringBuilder.append(appendStrWithEnter("<L"));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getTrid(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getSmpln(), 2));
            stringBuilder.append(formatDataStruct((DataStructNormal) secsPacket1.getStime(), 2));
            stringBuilder.append(formatDataStructs(SecsDataUtils.convertToDataStructs(secsPacket1.getValues()), 2));
            stringBuilder.append(appendStrWithEnter(">"));
            logger.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
        return null;
    }
}
