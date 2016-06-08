package com.example.boot.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author gimbyeongsu
 * 
 */
public class WebInitalizer implements ServletContextInitializer {

	public WebInitalizer() {
	}

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

		context.setConfigLocations("com.example.boot");
		// context.register(WebConfig.class);
		// container.addListener(new ContextLoaderListener(context));
		context.setServletContext(container);

		FilterRegistration.Dynamic characterEncodingFilter = container.addFilter("characterEncodingFilter",
				new CharacterEncodingFilter("UTF-8", true));
		characterEncodingFilter.addMappingForUrlPatterns(null, true, "/*");

		FilterRegistration.Dynamic trackingFilter = container.addFilter("trackingFilter", new TrackingFilter());
		trackingFilter.addMappingForUrlPatterns(null, true, "/*");

		// FilterRegistration.Dynamic shallowEtagHeaderFilter = container.addFilter("shallowEtagHeaderFilter",
		// new ShallowEtagHeaderFilter());
		// shallowEtagHeaderFilter.addMappingForUrlPatterns(null, true, "/*");

		DispatcherServlet dispatcher = new DispatcherServlet(context);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcher);

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}