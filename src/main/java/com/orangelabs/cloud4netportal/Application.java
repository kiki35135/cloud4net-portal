/*
 * Copyright 2016 Orange Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.orangelabs.cloud4netportal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * @author Ch. LE TOQUIN, 2016
 */
@SpringBootApplication
//@EnableWebSocketMessageBroker
@PropertySource("classpath:build.properties")
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(Application.class);
    }

    public static void main(String... args) {	
	SpringApplication.run(Application.class, args);
    }
    
     @Bean(destroyMethod="shutdown")
     public Executor taskScheduler(final @Value("${cloud4netportal.scheduled-thread-pool-size:10}") int scheduledThreadPoolSize) {
         return Executors.newScheduledThreadPool(scheduledThreadPoolSize);
     }
}
