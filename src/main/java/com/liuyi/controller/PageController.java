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
	public String console() {
		return "console";
	}
	
	@RequestMapping("/upload")
	public String upload() {
		return "upload";
	}
	
	@RequestMapping("/stomp")
	public String stomp() {
		return "stomp";
	}
}
