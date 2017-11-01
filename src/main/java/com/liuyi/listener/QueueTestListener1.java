package com.liuyi.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liuyi.entity.Person;

@Component("queueTestListener1")
public class QueueTestListener1 implements MessageListener{

	@Autowired
	private MessageConverter jsonMessageConverter;
	
	@Override
	public void onMessage(Message message) {
		Person p = (Person)jsonMessageConverter.fromMessage(message);
		System.out.println(p);
	}

}
