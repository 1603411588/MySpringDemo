package com.liuyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/websocket")
	public String websocket() {
		return "websocket";
	}

	@RequestMapping("/console")
	public String console(String page) {
		return "console";
	}
	
	@RequestMapping("/upload")
	public String upload(String page) {
		return "upload";
	}
}
