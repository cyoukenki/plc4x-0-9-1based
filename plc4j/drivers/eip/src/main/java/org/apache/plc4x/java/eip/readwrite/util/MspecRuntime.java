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
package org.apache.plc4x.java.eip.readwrite.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.plc4x.java.eip.readwrite.protocol.EipClass3ProtocolLogic;
import org.apache.plc4x.language.java.JavaLanguageOutput;
import org.apache.plc4x.language.java.JavaLanguageTemplateHelper;
import org.apache.plc4x.plugins.codegenerator.language.LanguageOutput;
import org.apache.plc4x.plugins.codegenerator.language.mspec.model.definitions.DefaultComplexTypeDefinition;
import org.apache.plc4x.plugins.codegenerator.language.mspec.parser.MessageFormatParser;
import org.apache.plc4x.plugins.codegenerator.types.definitions.TypeDefinition;
import org.apache.plc4x.plugins.codegenerator.types.exceptions.GenerationException;
import org.apache.plc4x.plugins.codegenerator.types.fields.Field;
import org.apache.plc4x.plugins.codegenerator.types.fields.TypedField;
import org.apache.plc4x.plugins.codegenerator.types.references.SimpleTypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MspecRuntime {
    final LanguageOutput language;
    final String mspecPath;
    private static final Logger logger = LoggerFactory.getLogger(MspecRuntime.class);

    public MspecRuntime(String mspecPath) {
        this.language = new JavaLanguageOutput();
        this.mspecPath = mspecPath;
    }

    public void execute() {
        File file = new File(mspecPath);
        InputStream schemaInputStream = null;
        try {
            schemaInputStream = new FileInputStream(file);
        } catch (Exception e) {

        }

        // InputStream schemaInputStream =
        // EipProtocol.class.getResourceAsStream(mspecPath);
        if (schemaInputStream == null) {
            // throw new GenerationException("Error loading message-format schema for eio
            // stryct '" + ""+ "'");
            System.out.println("null mspec....");
            logger.warn("Structure definition file not found.");
            return;
        }
       
        Map<String, TypeDefinition> typeDefinitionMap = new LinkedHashMap<>(
                new MessageFormatParser().parse(schemaInputStream));


        // Set<String> supportedOptions = language.supportedOptions();
        Map<String, String> optionsMap = new HashMap<>();

        //output to java file
        File outFile = new File(System.getProperty("user.dir")+"/plc4j/drivers/eip/src/main/java");
        try {
            language.generate(outFile, "java", "eip", "struct", typeDefinitionMap, optionsMap);
        } catch (Exception e) {
            System.out.println("failure");
        }
        //  getHelper(typeEntry.getValue(), protocolName, outputFlavor, types, options));
         
         for (String key : typeDefinitionMap.keySet()) {
            System.out.println("entry key ...." + key);
            System.out.println("entry value 1...." + typeDefinitionMap.get(key));
            if (typeDefinitionMap.get(key) instanceof DefaultComplexTypeDefinition) {
              
                DefaultComplexTypeDefinition ctype = (DefaultComplexTypeDefinition) typeDefinitionMap.get(key);
                System.out.println("name ...." + ctype.getName());

                JavaLanguageTemplateHelper helper = new JavaLanguageTemplateHelper(typeDefinitionMap.get(key), "eip", "struct", typeDefinitionMap, optionsMap);
                List<Field> fileds = ctype.getFields();
                for (Field field : fileds) {
                    System.out.println("field name ...." + helper.getLanguageTypeNameForField(field));
                    System.out.println(helper.getNonPrimitiveLanguageTypeNameForField((TypedField)field));
                    // System.out.println(helper.getNumBits(new SimpleTypeReference() {
                        
                    // }));
                   

                }

            } else {
                logger.warn("Unsupported structure type![" + key + "]");
                return;
            }
        }
    

    }

    // protected Map<String, Object> parseOptions(PlexusConfiguration options) {
    // Map<String, Object> optionsMap = new HashMap<>();
    // for (PlexusConfiguration child : options.getChildren()) {
    // String optionName = child.getName();
    // if(child.getChildCount() == 0) {
    // optionsMap.put(optionName, child.getValue());
    // } else {
    // optionsMap.put(optionName, parseOptions(child));
    // }
    // }
    // return optionsMap;
    // }
}
