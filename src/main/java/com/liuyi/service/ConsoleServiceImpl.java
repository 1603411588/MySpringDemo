package com.liuyi.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuyi.util.MyBinding;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

@Service
@Transactional
public class ConsoleServiceImpl implements ConsoleService, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public Object resolveScript(String scriptContent) {
		StringBuilder body = new StringBuilder();
		body.append(this.getScriptImportCode());
		body.append("\n");
		body.append(scriptContent);
		Binding binding = new MyBinding(applicationContext);
		GroovyShell shell = new GroovyShell(binding);
		Object message = shell.evaluate(body.toString());
		return message;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	private String getScriptImportCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("import java.util.*;\n");
		sb.append("import java.math.*;\n");
		sb.append("import com.liuyi.util.JsonUtils;\n");
		return sb.toString();
	}
}
