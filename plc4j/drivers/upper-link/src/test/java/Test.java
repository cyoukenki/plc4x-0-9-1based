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

import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcServerConnector;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.junit.Before;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Test {
    PlcServerConnector serverConnector;
    @Before
    public void setUp(){

        try {
            serverConnector = new PlcDriverManager().getServerConnector("secsgem:tcp://127.0.0.1:44818");
            Thread.sleep(10000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.Test
    public void testHostDeselect() {
        PlcReadRequest.Builder builder = serverConnector.readRequestBuilder();
        builder.addItem("item1", "DESELECT:STYPE:3");
        PlcReadRequest readRequest = builder.build();
        try {
            PlcReadResponse syncResponse = readRequest.execute().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @org.junit.Test
    public void testSeparate() {
        PlcReadRequest.Builder builder = serverConnector.readRequestBuilder();
        builder.addItem("item1", "SEPARATE:STYPE:9");
        PlcReadRequest readRequest = builder.build();
        try {
            PlcReadResponse syncResponse = readRequest.execute().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @org.junit.Test
    public void testLinkTest() {
        PlcReadRequest.Builder builder = serverConnector.readRequestBuilder();
        builder.addItem("item1", "LINKTEST:STYPE:5");
        PlcReadRequest readRequest = builder.build();
        try {
            PlcReadResponse syncResponse = readRequest.execute().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    @org.junit.Test
    public void testS1F13() {
        PlcReadRequest.Builder builder = serverConnector.readRequestBuilder();
        builder.addItem("item1", "S1F13:MDLN:MDLN1");
        builder.addItem("item2", "S1F13:REV:REV1");
        PlcReadRequest readRequest = builder.build();
        try {
            PlcReadResponse syncResponse = readRequest.execute().get(10000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @org.junit.Test
    public void testS1F1() {
        PlcReadRequest.Builder builder = serverConnector.readRequestBuilder();
        builder.addItem("item1", "S1F1::");
        PlcReadRequest readRequest = builder.build();
        try {
            PlcReadResponse syncResponse = readRequest.execute().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
