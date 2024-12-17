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
package org.apache.plc4x.java.ckd.readwrite.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.omron.gc.data.provider.utils.DataProviderUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.plc4x.java.ckd.readwrite.model.PushMessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WebSocketUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtil.class);
    public static void sendWebSocketMessage(String webTopic, String pushMessgeContent, String type){
        logger.info(pushMessgeContent);
        if(StringUtils.isNoneEmpty(webTopic)) {
            try {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("messgeContent",new PushMessageInfo("CKD",type+": "+pushMessgeContent).toString());
                DataProviderUtil.sendText(webTopic, new Gson().toJson(jsonObject));
            } catch (IOException e) {
                logger.error("DataProvider sendText Message error with {}",e.getMessage(),e);
            }
        }
    }
}
