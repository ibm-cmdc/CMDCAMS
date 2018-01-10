package com.ibm.ams.dao.redis.queue;

import redis.clients.jedis.JedisPubSub;

public class QueueListener extends JedisPubSub {

	@Override
	public void onMessage(String channel, String message) {
		System.out.println("message receive:" + message + ",channel:" + channel );  
        
        //此处我们可以取消订阅  
        if(message.equalsIgnoreCase("quit")){  
            this.unsubscribe(channel);  
        }  

	}

	@Override
	public void onPMessage(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}
