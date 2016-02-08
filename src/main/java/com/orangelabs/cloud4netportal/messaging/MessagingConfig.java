package com.orangelabs.cloud4netportal.messaging;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"com.orangelabs.cloud4netportal.service.api",
		"com.orangelabs.cloud4netportalservice.impl",
		"com.orangelabs.cloud4netportalservice",
		"com.orangelabs.cloud4netportalweb.rest",
		"com.orangelabs.cloud4netportal.messaging"
	})
public class MessagingConfig {
	public MessagingConfig() {
        super();
    }
}