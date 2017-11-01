package com.liuyi.controller;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletResponse;

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
	public String eval(HttpServletResponse response,String scriptContent) throws IOException {
		try {
			Object result = consoleManager.resolveScript(scriptContent);
			return result == null ? "null" : result.toString();
		} catch (Throwable e) {
			e.printStackTrace(new PrintStream(response.getOutputStream()));
			return null;
		}
	}
}
