
package com.ibm.ams.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * @author zhl
 * 
 */
public class AmsUtil {

	private static Logger logger = Logger.getLogger(AmsUtil.class);
	private static Properties prop = new Properties();     

	
	private AmsUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean propInstance(){
		InputStream in=null;
		try{
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			in = new BufferedInputStream (new FileInputStream(path+"/ams.properties"));
			prop.load(in);   
		}catch(Exception e){
			logger.error("getvalue异常:"+e.getMessage());
			return false;
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("getvalue异常:"+e.getMessage());
					return  false;
				}
			}
		}
		
		return  true;
	}
	
	
	public static String getvalue(String key){
		String value = prop.getProperty(key);
		return value;
	}

	
	
	public static void main(String[] arg) {
		boolean propInstance = AmsUtil.propInstance();
		if(propInstance){
			String getvalue = AmsUtil.getvalue("test");
			System.out.print(getvalue);
			
		}
		
	}

}
