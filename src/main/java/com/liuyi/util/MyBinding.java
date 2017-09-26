package com.liuyi.util;

import org.springframework.context.ApplicationContext;

import groovy.lang.Binding;

public class MyBinding extends Binding {

	private ApplicationContext applicationContext;

	public MyBinding(ApplicationContext applicationContext) {
		super();
		this.applicationContext = applicationContext;
		this.setVariable("applicationContext", applicationContext);
	}

	@Override
	public Object getVariable(String name) {
		if (super.hasVariable(name)) {
			return super.getVariable(name);
		}

		if (applicationContext.containsBean(name)) {
			return applicationContext.getBean(name);
		}

		return null;
	}

}
