package com.ibm.ams.interservice.test;


public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "您好："+name;
	}

}