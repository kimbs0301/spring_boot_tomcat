package com.example.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.example.boot.config.WebConfig;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
@ComponentScan( basePackageClasses = { EmbeddedTomcatConfig.class },
		basePackages = { "com.example.xxx" }, excludeFilters = {
		@Filter(value = { AopAutoConfiguration.class, //
				EmbeddedServletContainerAutoConfiguration.class, //
				PropertyPlaceholderAutoConfiguration.class, //
				MessageSourceAutoConfiguration.class, //
				CacheAutoConfiguration.class, //
				ConfigurationPropertiesAutoConfiguration.class, //
				WebSocketAutoConfiguration.class, //
				WebSocketMessagingAutoConfiguration.class, //
				WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE),
		@Filter(value = { /** RestController.class, Controller.class **/ }, type = FilterType.ANNOTATION) })
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	public Application() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}

	public static void main(String[] args) throws Exception {
		LOGGER.debug("start");
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class);
		SpringApplication springApplication = springApplicationBuilder.profiles("local").build();
		// springApplication.setAddCommandLineProperties(true);
		// String[] command = { "--spring.config.location=classpath:/common.properties" };
		// springApplication.run(command);
		springApplication.run();
	}
}