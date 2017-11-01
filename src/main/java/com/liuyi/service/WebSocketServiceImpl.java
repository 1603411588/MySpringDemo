package com.liuyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService{

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Override
	public void sayHello() {
		simpMessagingTemplate.convertAndSend("/topic/hello", " hello websocket stomp...");
	}

}
