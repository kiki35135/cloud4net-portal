package com.orangelabs.cloud4netportal.messaging;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableScheduling
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = "com.orangelabs.cloud4netportal.messaging")
@PropertySource({
	"classpath:application-messaging.properties"
})
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private Environment env;
	
	Boolean useInMemoryBroker;
	
	@PostConstruct
	public void init() {
		LOG.info(this.getClass().getCanonicalName() + " init");
		
		useInMemoryBroker = Boolean.valueOf(env
				.getRequiredProperty("messaging.broker.stomp.in-memory"));
	}
	
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// websocket root endpoint
		registry.addEndpoint("/ws").withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		
		if (useInMemoryBroker) {
			// in-memory pub/sub message broker
			config.enableSimpleBroker("/queue/", "/topic/");
		} else {
			// use dedicated RabbitMQ server (with STOMP plugin enabled)
			StompBrokerRelayRegistration stompBrokerRelayRegistration = config.enableStompBrokerRelay("/queue/", "/topic/");
			stompBrokerRelayRegistration.setClientLogin(env.getRequiredProperty("messaging.broker.stomp.client.username"));
			stompBrokerRelayRegistration.setClientPasscode(env.getRequiredProperty("messaging.broker.stomp.client.password"));
			stompBrokerRelayRegistration.setAutoStartup(true);
			stompBrokerRelayRegistration.setRelayHost(env.getRequiredProperty("messaging.broker.stomp.host"));
			stompBrokerRelayRegistration.setRelayPort(Integer.valueOf(env.getRequiredProperty("messaging.broker.stomp.port")));
			
		}
				
		// prefix for messages that are bound for @MessageMapping-annotated methods.
		config.setApplicationDestinationPrefixes("/app");
	}
}