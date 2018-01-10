package com.ibm.ams.dao.redis.queue;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SubClient {
	
	 private Jedis jedis;//  
     
	    public SubClient(String host,String password){  
	        jedis = new Jedis(host);  
	        jedis.auth(password);
	    }  
	      
	    public void sub(JedisPubSub listener,String channel){  
	        jedis.subscribe(listener, channel);  
	    }  

}
