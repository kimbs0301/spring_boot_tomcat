package com.example.boot.aop;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author gimbyeongsu
 * 
 */
public class MethodArgument {
	private final int index;
	private final String name;
	private final List<Annotation> annotations;
	private final Object value;

	private MethodArgument(int index, String name, List<Annotation> annotations, Object value) {
		this.index = index;
		this.name = name;
		this.annotations = Collections.unmodifiableList(annotations);
		this.value = value;
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public boolean hasAnnotation(Class<? extends Annotation> type) {
		for (Annotation annotation : annotations)
			if (annotation.annotationType().equals(type))
				return true;
		return false;
	}

	public Object getValue() {
		return value;
	}

	public static List<MethodArgument> of(JoinPoint joinPoint) {
		List<MethodArgument> arguments = new ArrayList<>();
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		String[] names = codeSignature.getParameterNames();
		MethodSignature methodSignature = (MethodSignature) joinPoint.getStaticPart().getSignature();
		Annotation[][] annotations = methodSignature.getMethod().getParameterAnnotations();
		Object[] values = joinPoint.getArgs();
		for (int i = 0; i < values.length; i++)
			arguments.add(new MethodArgument(i, names[i], Arrays.asList(annotations[i]), values[i]));
		return Collections.unmodifiableList(arguments);
	}
}