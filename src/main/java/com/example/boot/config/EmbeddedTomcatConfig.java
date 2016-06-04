package com.example.boot.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.AccessLogValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
public class EmbeddedTomcatConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedTomcatConfig.class);

	@Autowired
	private Environment environment;

	// @Autowired
	// private EmbeddedTomcatWebInitalizer embeddedTomcatWebInitalizer;

	public EmbeddedTomcatConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		// EmbeddedServletContainerAutoConfiguration
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory(
				environment.getRequiredProperty("context.path"), 8080);
		factory.setProtocol("org.apache.coyote.http11.Http11NioProtocol");
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("html", "text/html;charset=UTF-8");
		mappings.add("file", "multipart/form-data");
		mappings.add("json", "application/json;charset=UTF-8");
		mappings.add("xml", "application/xml;charset=UTF-8");
		factory.setMimeMappings(mappings);
		factory.setSessionTimeout(50, TimeUnit.MINUTES);
		factory.setUriEncoding(Charset.forName("UTF-8"));

		AccessLogValve accessLogValve = new AccessLogValve();
		accessLogValve.setDirectory("/temp");
		// '%h %l %u %t "%r" %s %b %D'
		accessLogValve.setPattern("%{yyyy-MM-dd HH:mm:ss}t\t%a\t%r\t%{Referer}i\t%{User-Agent}i\t%D\t%I");
		accessLogValve.setSuffix(".log");
		factory.addContextValves(accessLogValve);

		// Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		// connector.setPort(8081);
		// factory.addAdditionalTomcatConnectors(connector);

		// ApplicationContext parent = new AnnotationConfigApplicationContext(InitConfig.class);

		factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

			@Override
			public void customize(Connector connector) {
				connector.setProperty("useComet", "false");
				connector.setProperty("connectionTimeout", "3000");
				connector.setProperty("compression", "on");
				connector.setProperty("compressionMinSize", "2048");
				connector.setProperty("compressableMimeType", "text/html,text/xml,text/plain,application/json");
				connector.setEnableLookups(false);
				connector.setURIEncoding("UTF-8");
				// connector.setXpoweredBy(true);
			}
		});

		List<ServletContextInitializer> servletContextInitializers = new ArrayList<>();
		servletContextInitializers.add(new WebInitalizer());
		factory.setInitializers(servletContextInitializers);

		return factory;
	}
}
