package com.liuyi;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Autowired
	private Environment environment;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setHost(environment.getProperty("mail.host"));
		mailSender.setPort(environment.getProperty("mail.port", Integer.class, 25));
		mailSender.setUsername(environment.getProperty("mail.username"));
		mailSender.setPassword(environment.getProperty("mail.password"));
		Properties properties = new Properties();
		properties.put("mail.debug", environment.getProperty("mail.debug", boolean.class, false));
		properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
//		properties.put("mail.smtp.socketFactory.class", environment.getProperty("mail.smtp.socketFactory.class"));
//		properties.put("mail.smtp.socketFactory.fallback", environment.getProperty("mail.smtp.socketFactory.fallback"));
//		properties.put("mail.smtp.port", environment.getProperty("mail.smtp.port"));
//		properties.put("mail.smtp.socketFactory.port", environment.getProperty("mail.smtp.socketFactory.port"));
//		properties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable", boolean.class, false));
		mailSender.setJavaMailProperties(properties);
		return mailSender;
	}

}
