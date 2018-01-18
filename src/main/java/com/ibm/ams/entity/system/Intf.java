package com.ibm.ams.entity.system;

import com.ibm.ams.entity.Page;

/**
 * 系统接口表
 */
public class Intf {
	private String INTF_ID;//接口ID
	private String INTF_NAME;//接口名称
	private String INTF_URL;//接口地址
	private String IPLATFORM;//系统平台
	private Page page;			//分页对象
	
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getINTF_ID() {
		return INTF_ID;
	}
	public void setINTF_ID(String iNTF_ID) {
		INTF_ID = iNTF_ID;
	}
	public String getINTF_NAME() {
		return INTF_NAME;
	}
	public void setINTF_NAME(String iNTF_NAME) {
		INTF_NAME = iNTF_NAME;
	}
	public String getINTF_URL() {
		return INTF_URL;
	}
	public void setINTF_URL(String iNTF_URL) {
		INTF_URL = iNTF_URL;
	}
	public String getIPLATFORM() {
		return IPLATFORM;
	}
	public void setIPLATFORM(String iPLATFORM) {
		IPLATFORM = iPLATFORM;
	}
}
