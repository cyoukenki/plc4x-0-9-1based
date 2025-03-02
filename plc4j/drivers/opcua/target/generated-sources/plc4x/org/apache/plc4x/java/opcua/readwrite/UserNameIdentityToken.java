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
package org.apache.plc4x.java.opcua.readwrite;

import static org.apache.plc4x.java.spi.generation.StaticHelper.*;

import org.apache.plc4x.java.opcua.readwrite.io.*;
import org.apache.plc4x.java.opcua.readwrite.types.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.plc4x.java.api.value.*;
import org.apache.plc4x.java.spi.generation.Message;
import org.apache.plc4x.java.spi.generation.MessageIO;

import java.time.*;
import java.util.*;
import java.math.BigInteger;

// Code generated by code-generation. DO NOT EDIT.

public class UserNameIdentityToken extends UserIdentityTokenDefinition implements Message {

    // Accessors for discriminator values.
    public String getIdentifier() {
        return "username";
    }

    // Properties.
    private final PascalString userName;
    private final PascalByteString password;
    private final PascalString encryptionAlgorithm;

    public UserNameIdentityToken(PascalString userName, PascalByteString password, PascalString encryptionAlgorithm) {
        this.userName = userName;
        this.password = password;
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public PascalString getUserName() {
        return userName;
    }

    public PascalByteString getPassword() {
        return password;
    }

    public PascalString getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    @Override
    public int getLengthInBytes() {
        return getLengthInBits() / 8;
    }

    @Override
    public int getLengthInBits() {
        return getLengthInBitsConditional(false);
    }

    public int getLengthInBitsConditional(boolean lastItem) {
        int lengthInBits = super.getLengthInBitsConditional(lastItem);
        UserNameIdentityToken _value  = this;

        // Simple field (userName)
        lengthInBits += userName.getLengthInBits();

        // Simple field (password)
        lengthInBits += password.getLengthInBits();

        // Simple field (encryptionAlgorithm)
        lengthInBits += encryptionAlgorithm.getLengthInBits();

        return lengthInBits;
    }

    @Override
    public MessageIO<UserIdentityTokenDefinition, UserIdentityTokenDefinition> getMessageIO() {
        return new UserIdentityTokenDefinitionIO();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserNameIdentityToken)) {
            return false;
        }
        UserNameIdentityToken that = (UserNameIdentityToken) o;
        return
            (getUserName() == that.getUserName()) &&
            (getPassword() == that.getPassword()) &&
            (getEncryptionAlgorithm() == that.getEncryptionAlgorithm()) &&
            super.equals(that) &&
            true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            super.hashCode(),
            getUserName(),
            getPassword(),
            getEncryptionAlgorithm()
        );
    }

    @Override
    public String toString() {
        return toString(ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toString(ToStringStyle style) {
        return new ToStringBuilder(this, style)
            .appendSuper(super.toString(style))
            .append("userName", getUserName())
            .append("password", getPassword())
            .append("encryptionAlgorithm", getEncryptionAlgorithm())
            .toString();
    }

}
