package com.ibm.ams.test;

import org.springframework.stereotype.Service;


@Service("PrintService")
public class Person implements Print{

	public String print() {
		return "9999999999";
	}
	

}
