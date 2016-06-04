package com.example.boot.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
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
		// WebInitializer.onStartup(container, context);

		// context.setConfigLocations("com.example.boot.web", "com.example.boot.api");
		context.register(WebConfig.class);
		// container.addListener(new ContextLoaderListener(context));
		context.setServletContext(container);

		container.addFilter("trackingFilter", new TrackingFilter()).addMappingForUrlPatterns(null, true, "/*");
		container.addFilter("shallowEtagHeaderFilter", new ShallowEtagHeaderFilter()).addMappingForUrlPatterns(null, true, "/*");
		
		DispatcherServlet dispatcher = new DispatcherServlet(context);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcher);

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}