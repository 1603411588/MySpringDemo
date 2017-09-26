package com.liuyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuyi.service.ConsoleService;

@Controller
public class ConsoleController {

	@Autowired
	private ConsoleService consoleManager;

	@RequestMapping("/console/eval")
	@ResponseBody
	public String eval(String scriptContent) {
		Object result = consoleManager.resolveScript(scriptContent);
		return result == null ? "null" : result.toString();
	}
}
