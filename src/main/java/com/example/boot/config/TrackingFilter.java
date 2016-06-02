package com.example.boot.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gimbyeongsu
 * 
 */
public class TrackingFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(TrackingFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOGGER.debug("init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		LOGGER.debug("tracking filter");
		
		Enumeration<String> attributeNames = request.getAttributeNames();
		while(attributeNames.hasMoreElements()) {
			LOGGER.debug("{}", attributeNames.nextElement());
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}