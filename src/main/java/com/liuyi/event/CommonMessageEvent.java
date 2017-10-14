package com.liuyi.event;

import org.springframework.context.ApplicationEvent;

public class CommonMessageEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4328237370643188569L;

	public CommonMessageEvent(Object source) {
		super(source);
	}

}
