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
package org.apache.plc4x.java.serial.direct.readwrite.field;
import org.apache.plc4x.java.api.exceptions.PlcInvalidFieldException;
import org.apache.plc4x.java.api.model.PlcField;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Test address for accessing values in virtual devices.
 */
public class SerialDirectField implements PlcField {

    /**
     * Examples:
     * - {@code RANDOM/foo:INTEGER}
     * - {@code STDOUT/foo:STRING}
     */
    private static final Pattern ADDRESS_PATTERN = Pattern.compile("^(?<type>\\w+)/(?<name>[a-zA-Z0-9_\\\\.]+):(?<dataType>[a-zA-Z0-9]++)(\\[(?<numElements>\\d+)])?$");

    private final String name;
    private final int numElements;

    private SerialDirectField(String name, int numElements) {
        this.name = name;
        this.numElements = numElements;
    }

    public static SerialDirectField of(String fieldString) throws PlcInvalidFieldException {
        Matcher matcher = ADDRESS_PATTERN.matcher(fieldString);
        if (matcher.matches()) {
            String name = matcher.group("name");
            int numElements = 1;
            if (matcher.group("numElements") != null) {
                numElements = Integer.parseInt(matcher.group("numElements"));
            }
            return new SerialDirectField(name, numElements);
        }
        throw new PlcInvalidFieldException("Unable to parse address: " + fieldString);
    }

    static boolean matches(String fieldString) {
        return ADDRESS_PATTERN.matcher(fieldString).matches();
    }



    public String getName() {
        return name;
    }


    public int getNumberOfElements() {
        return numElements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SerialDirectField testField = (SerialDirectField) o;
        return numElements == testField.numElements &&
            Objects.equals(name, testField.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numElements);
    }

    @Override
    public String toString() {
        return "TestField{" +
            "name='" + name + '\'' +
            ", numElements=" + numElements +
            '}';
    }

}
