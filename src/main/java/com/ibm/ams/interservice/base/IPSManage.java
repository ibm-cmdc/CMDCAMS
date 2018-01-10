package com.ibm.ams.interservice.base;

import java.util.ArrayList;
import java.util.List;


public class IPSManage {
	
	private IPSInterface  ipsinterface;
	private String methodName;
	
	
	public void callInterface(IPSInterface ipsinterface,String methodName) {  
        this.ipsinterface = ipsinterface;  
        this.methodName = methodName;
    }  
	
	public IPSReturn execute() { 
		ReturnMessage et = new ReturnMessage();
		IPSReturn rm = new IPSReturn();
		List list = new ArrayList();
		
		String size  = "";
		try{
			Object msg = ipsinterface.callInterface();
			size = msg.toString();
			if("获取任务清单".equals(methodName) || "获取历史清单".equals(methodName)){
				list = (List)msg;
				et.setType("S");
				et.setMessage(methodName+"执行成功-有"+list.size()+"个");
			}else{
				et.setType("S");
				et.setMessage(methodName+"执行成功!");
			}
			
		}catch(Exception e){
			et.setType("E");
			et.setMessage(methodName+"执行失败:"+e.getMessage());
		}
		
		rm.setRm(et);
		rm.setList(list);
		rm.setString(size);
		
		return rm;
		
    }  
	
}
