package com.ibm.ams.util.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


public class TokenSingleton {
	
	protected Logger logger = Logger.getLogger(this.getClass());
    
        public final static String weixin_jssdk_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

        private Map<String, String> map = new HashMap<String, String>();

        private TokenSingleton() {
        }

        private static TokenSingleton single = null;

        public static TokenSingleton getInstance() {
            if (single == null) {
                single = new TokenSingleton();
            }
            return single;
        }

        public Map<String, String> getMap() {
            String time = map.get("time");
            String accessToken = map.get("access_token");
            Long nowDate = new Date().getTime();
            
            if (accessToken != null && time != null && nowDate - Long.parseLong(time) < 3000 * 1000) {
            	logger.info("已经存在Token"+accessToken);
            } else {
                String access_token = JSSDKUtil.getAccess_token();
                String jsapi_token = JSSDKUtil.getTicket(access_token);
                map.put("time", nowDate + "");
                map.put("access_token", access_token);
                map.put("jsapi_token", jsapi_token);
                logger.info("没有Token重新获取"+accessToken);
            }
            
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }

        public static TokenSingleton getSingle() {
            return single;
        }

        public static void setSingle(TokenSingleton single) {
            TokenSingleton.single = single;
        }
        
}