package com.liuyi.support;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.liuyi.util.ContextUtils;

@Component
public class ContextSupport implements ApplicationContextAware, EnvironmentAware {

	private Environment environment;
	private ApplicationContext applicationContext;

	@PostConstruct
	public void initContextUtils() {
		ContextUtils.setApplicationContext(applicationContext);
		ContextUtils.setEnvironment(environment);
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
