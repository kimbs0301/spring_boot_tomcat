package com.example.junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.config.WebConfig;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
@ComponentScan(basePackages = { "com.example.boot" }, excludeFilters = {
		@Filter(value = { WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE),
		@Filter(value = { RestController.class, Controller.class }, type = FilterType.ANNOTATION) })
public class JunitConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(JunitConfig.class);

	public JunitConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}
}