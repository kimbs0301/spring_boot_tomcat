package com.example.boot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author gimbyeongsu
 * 
 */
@Aspect
@Component
public class LoggerAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

	public LoggerAspect() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}
	
	@Around("execution(* com.example..web.*Controller.*(..)) or execution(* com.example..service.*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		String type = joinPoint.getSignature().getDeclaringTypeName();
		String name = null;
		if (type.indexOf("Controller") > -1) {
			name = "Controller : ";
		} else if (type.indexOf("Service") > -1) {
			name = "ServiceImpl : ";
		}
		LOGGER.debug("{}{}.{}()", new Object[] { name, type, joinPoint.getSignature().getName() });
		return joinPoint.proceed();
	}

}
