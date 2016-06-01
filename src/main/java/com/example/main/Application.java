package com.example.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.config.JunitConfig;
import com.example.boot.config.WebConfig;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.example.boot" }, excludeFilters = {
		@Filter(value = { JunitConfig.class, WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE),
		@Filter(value = {RestController.class, Controller.class}, type = FilterType.ANNOTATION) })
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOGGER.debug("start");

		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class);
		SpringApplication springApplication = springApplicationBuilder.profiles("local").build();
		// springApplication.setAddCommandLineProperties(true);
		// String[] command = { "--spring.config.location=classpath:/common.properties" };
		// springApplication.run(command);
		springApplication.run();
	}
}