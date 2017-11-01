package com.liuyi.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.liuyi.event.CommonMessageEvent;

@Component
public class CommonMessageListener implements ApplicationListener<CommonMessageEvent> {

	@Async
	@Override
	public void onApplicationEvent(CommonMessageEvent event) {
		System.out.println(event.getSource());
		System.out.println(event.getTimestamp());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CommonMessageListener :" + System.currentTimeMillis());
	}

}
