package com.liuyi.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public void test1() {
		System.out.println(" HelloServiceImpl.test1() 调用！");
	}
}
