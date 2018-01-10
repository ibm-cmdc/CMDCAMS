package com.ibm.ams.util.wechat;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.json.JSONObject;


public class JSSDKUtil {
	protected static Logger logger = Logger.getLogger(JSSDKUtil.class.getClass());
	
	public final static String appId = "wxb436b0064c498355";   //  wxabd2966c59f7d687     
	public final static String appSecret = "3acc170cab45b95e8ddd8b6fe9912ee4";  // 023536d66c00e12b4e690143eaebdcb0   
	public String accessToken = "";
//	public static Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.cmcc", 8080));
	
	public static String getAccess_token(){ 

		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId + "&secret=" +appSecret;

		String accessToken = null;
		try {
		URL urlGet = new URL(url);
		
		HttpURLConnection http = (HttpURLConnection) urlGet.openConnection(); 

		http.setRequestMethod("GET"); 
		http.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
		http.setDoOutput(true); 
		http.setDoInput(true);
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 

		http.connect();

		InputStream is =http.getInputStream();
		int size =is.available();
		byte[] jsonBytes =new byte[size];
		is.read(jsonBytes);
		String message=new String(jsonBytes,"UTF-8");

		JSONObject demoJson = new JSONObject(message);
		accessToken = demoJson.getString("access_token");
		http.disconnect();
		} catch (Exception e) {
			logger.error("getAccess_token异常"+e);
		}
		return accessToken;
	}
	
	public static String getOAuth2Token(String code){ 

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";

		String openid = "";
		try {
		URL urlGet = new URL(url);
		
		HttpURLConnection http = (HttpURLConnection) urlGet.openConnection(); 

		http.setRequestMethod("GET"); 
		http.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
		http.setDoOutput(true); 
		http.setDoInput(true);
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 

		http.connect();

		InputStream is =http.getInputStream();
		int size =is.available();
		byte[] jsonBytes =new byte[size];
		is.read(jsonBytes);
		String message=new String(jsonBytes,"UTF-8");
		JSONObject demoJson = new JSONObject(message);
		openid = demoJson.getString("openid");
		logger.info("用户openid"+message);
		http.disconnect();
		} catch (Exception e) {
			logger.error("getOpenId异常"+e);
			
		}
		return openid;
	}
	
	
	public static String getTicket(String ACCESS_TOKEN) {
        String ticket = null;
        String access_token =ACCESS_TOKEN; 
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";
         
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); 
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = new JSONObject(message);
            ticket = demoJson.getString("ticket");
            is.close();
            http.disconnect();
        } catch (Exception e) {
        	logger.error("getTicket异常"+e);
        	
        }
         
        return ticket;
    }
	
	
	
	public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
	}
	
	
	public static SignBean getSignBean(String url){
		SignBean signbean = new SignBean();
		TokenSingleton instance = TokenSingleton.getInstance();
		Map<String, String> map = instance.getMap();
		String timestamp = Long.toString(System.currentTimeMillis());
		String nonceStr = UUID.randomUUID().toString(); 
		String sign = "jsapi_ticket=" + map.get("jsapi_token") + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + url;
		try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(sign.getBytes("UTF-8"));
            String signature = JSSDKUtil.byteToHex(crypt.digest());
            signbean.setAppId(appId);
            signbean.setJsapi_ticket(map.get("jsapi_token"));
            signbean.setNonceStr(nonceStr);
            signbean.setSignature(signature);
            signbean.setTimestamp(timestamp);
            return signbean;
		  	} catch (Exception e) {
		  		logger.error("getSignBean异常jsapi_ticket:"+map.get("jsapi_token")+"---url:"+url);
		  		logger.error(e);
		  		return null;
	        }
	}

}
