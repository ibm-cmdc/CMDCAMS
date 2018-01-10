package com.ibm.ams.interservice.test;  

import javax.jws.WebService;  

@WebService  
public interface HelloService {  
  
    public String sayHello(String name);
    
}  
