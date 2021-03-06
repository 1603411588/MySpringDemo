package com.liuyi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@RequestMapping("/websocket/testHello")
	@ResponseBody
	public String test1() {
		simpMessagingTemplate.convertAndSend("/app/testHello", " hello websocket!!!");
		return "SUCCESS";
	}
	
	@RequestMapping("/websocket/sendToUser")
	@ResponseBody
	public String sendToUser() {
		Map<String, Object> msg = new HashMap<>();
		msg.put("key", "你好");
		simpMessagingTemplate.convertAndSendToUser("TestWesocketUser00001", "/userMessage", msg);;
		return "SUCCESS";
	}

	@MessageMapping("/testHello")
	@SendTo("/topic/testHello")
	public String test2(@Payload String payload, @Headers Map<String, Object> headers) {
		System.out.println("headers:" + headers);
		System.out.println("payload:" + payload);
		return payload;
	}
	
	@MessageMapping("/testHello2")
	@SendToUser()
	public String test3(@Payload String payload, @Headers Map<String, Object> headers) {
		System.out.println("headers:" + headers);
		System.out.println("payload:" + payload);
		return payload;
	}
}
