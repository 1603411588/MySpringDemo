package com.liuyi.task;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {

	@Scheduled(cron = "0 5 * * * ?")
	public void task1() {
		System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()) + " task1 -  task1 - ");
	}
}
