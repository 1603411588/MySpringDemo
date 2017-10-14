package com.liuyi.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.env.Environment;

public class ContextUtils {

	private static ApplicationContext applicationContext;
	private static Environment environment;

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
	public static void publishEvent(ApplicationEvent event){
		applicationContext.publishEvent(event);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static <T> T getBean(Class<T> requiredType) {
		return applicationContext.getBean(requiredType);
	}

	public static String getProperty(String key) {
		return environment.getProperty(key);
	}

	public static <T> T getProperty(String key, Class<T> targetType) {
		return environment.getProperty(key, targetType);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		ContextUtils.applicationContext = applicationContext;
	}

	public static Environment getEnvironment() {
		return environment;
	}

	public static void setEnvironment(Environment environment) {
		ContextUtils.environment = environment;
	}

}
