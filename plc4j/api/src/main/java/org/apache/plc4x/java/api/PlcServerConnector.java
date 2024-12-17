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
package org.apache.plc4x.java.api;


import org.apache.plc4x.java.api.exceptions.*;
import org.apache.plc4x.java.api.listener.ServerConnectorListener;
import org.apache.plc4x.java.api.messages.*;
import org.apache.plc4x.java.api.metadata.PlcConnectionMetadata;
import org.apache.plc4x.java.api.model.PlcField;

import java.util.concurrent.CompletableFuture;

public interface PlcServerConnector extends AutoCloseable{

    /**
     * Start the server connector which actually opens the port.
     * 
     * @return events related to the server connector.
     */
    void start() throws PlcConnectionException;

    boolean isStart();
    /**
     * Stop the server connector which actually closes the port.
     * 
     * @return state of action.
     */
    boolean stop();
    default void setServerConnectorListener(ServerConnectorListener connectorListener){
        throw new PlcNotImplementedException("Not implemented for this listener");
    }

    /**
     * Returns the unique ID of the server-connector.
     * 
     * @return the id.
     */
    String getConnectorID();

    @Override
    void close() throws Exception;

    /**
     * Parse a fieldQuery for the given connection type.
     *
     * @throws PlcRuntimeException If the string cannot be parsed
     */
    @Deprecated
    default PlcField prepareField(String fieldQuery) throws PlcInvalidFieldException {
        throw new PlcRuntimeException("Parse method is not implemented for this connection / driver");
    }

    /**
     * Provides connection metadata.
     */
    PlcConnectionMetadata getMetadata();

    /**
     * Execute a ping query against a remote device to check the availability of the connection.
     *
     * @return CompletableFuture that is completed successfully (Void) or unsuccessfully with an PlcException.
     */
    CompletableFuture<Void> ping();

    /**
     * @return read request builder.
     * @throws PlcUnsupportedOperationException if the connection does not support reading
     */
    PlcReadRequest.Builder readRequestBuilder();

    /**
     * @return write request builder.
     * @throws PlcUnsupportedOperationException if the connection does not support writing
     */
    PlcWriteRequest.Builder writeRequestBuilder();

    /**
     * @return subscription request builder.
     * @throws PlcUnsupportedOperationException if the connection does not support subscription
     */
    PlcSubscriptionRequest.Builder subscriptionRequestBuilder();

    /**
     * @return unsubscription request builder.
     * @throws PlcUnsupportedOperationException if the connection does not support subscription
     */
    PlcUnsubscriptionRequest.Builder unsubscriptionRequestBuilder();

    /**
     * @return browse request builder.
     * @throws PlcUnsupportedOperationException if the connection does not support browsing
     */
    default PlcBrowseRequest.Builder browseRequestBuilder() {
        throw new PlcNotImplementedException("Not implemented for this connection / driver");
    }
}
