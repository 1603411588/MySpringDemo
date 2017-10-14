package com.liuyi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspet {

	@After(" execution(* com.liuyi.controller.HelloController.testAop(..))")
	public void doAfter(JoinPoint joinPoint){
		System.out.println("...TestAspcet...doAfter...");
	}
	
	@After(" execution(* com.liuyi.service.HelloService.test1(..))")
	public void doBefore(JoinPoint joinPoint){
		System.out.println("...TestAspcet...doBefore...");
	}
}
