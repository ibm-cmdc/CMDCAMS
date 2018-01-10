package com.ibm.ams.test;

import org.springframework.beans.factory.annotation.Autowired;

public class HelloWorld {
	
	 private String message;
	 
	    public void setMessage(String message){
	        this.message  = message;
	    }
	 
	    public String getMessage(){
	        return this.message;
	    }
	 

}
