package com.ibm.ams.dao.redis.queue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibm.ams.util.DbFH;

import redis.clients.jedis.JedisPubSub;

public class QueueThread extends Thread{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	@Override
    public void run() {
       //redis
		try{
			Properties pros = getPprVue();
			String host = pros.getProperty("redis.host");		//地址
			String pass = pros.getProperty("redis.pass");		//密码
			SubClient subClient = new SubClient(host,pass);
			JedisPubSub listener = new QueueListener();  
			subClient.sub(listener, "CMDCAMS-channel");  
		}catch(Exception e){
			logger.error("消息队列初始化错误"+e);
			System.out.println(e.getMessage());
		}
        
    }
	
	
	/**读取redis.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public Properties getPprVue(){
		InputStream inputStream = DbFH.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			//读取配置文件出错
			e.printStackTrace();
		}
		return p;
	}

}
