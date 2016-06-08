package com.example.boot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.boot.member.model.Member;

/**
 * @author gimbyeongsu
 * 
 */
@Aspect
@Component
public class ParamAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParamAspect.class);

	public ParamAspect() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}

	@Before("execution(* *(.., @com.example.boot.aop.ParamAop (*), ..))")
	public void nullCheck(JoinPoint joinPoint) {
		for (MethodArgument argument : MethodArgument.of(joinPoint)) {
			if (argument.hasAnnotation(ParamAop.class)) {
				Member member = (Member) argument.getValue();
				member.setId(99);
			}
		}
	}
}
