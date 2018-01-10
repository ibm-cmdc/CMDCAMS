package com.ibm.ams.dao.redis.queue;

import redis.clients.jedis.Jedis;

public class PubClient {
	
	private Jedis jedis;//  
    public PubClient(String host,String password){  
        jedis = new Jedis(host);  
        jedis.auth(password);
    }  
      
    public void pub(String channel,String message){  
        Long publish = jedis.publish(channel, message);  
    }  
      
    public void close(String channel){  
        jedis.publish(channel, "quit");  
        //jedis.del(channel);//  
    }  

}
