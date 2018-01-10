package com.ibm.ams.init;

import java.util.HashMap;
import java.util.Map;


public class AmsCache {
	
	private static AmsCache single = null;
	
	private Map<String, Object> map = new HashMap<String, Object>();

    public static AmsCache getInstance() {
        if (single == null) {
            single = new AmsCache();
        }
        return single;
    }
    
    
    public Object getMap(String key) {
        Object obj = map.get(key);
        return obj;
    }

    public void putMap(String key, Object value) {
       map.put(key, value);
    }


}
