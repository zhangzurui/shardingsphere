/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.shardingjdbc.spring.namespace.factorybean;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Properties;
import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.spi.algorithm.keygen.KeyGenerateAlgorithmServiceLoader;
import org.apache.shardingsphere.spi.keygen.KeyGenerateAlgorithm;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * key generate algorithm FactoryBean.
 */
@Setter
@Getter
public class KeyGenerateAlgorithmFactoryBean implements FactoryBean<KeyGenerateAlgorithm>, InitializingBean {
    
    private String type;
    
    private Properties properties;
    
    @Override
    public KeyGenerateAlgorithm getObject() throws Exception {
        return new KeyGenerateAlgorithmServiceLoader().newService(type, properties);
    }
    
    @Override
    public Class<?> getObjectType() {
        return KeyGenerateAlgorithm.class;
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(type), "The type of keyGenerateAlgorithm is required.");
    }
}