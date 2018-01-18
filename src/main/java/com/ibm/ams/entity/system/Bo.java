package com.ibm.ams.entity.system;

import com.ibm.ams.entity.Page;

/**
 * 数据对象主表
 */
public class Bo { 
	private String DB_ID; // 表ID
	private String BO_NAME; // 数据对象名称
	private String BO_ID; // 数据对象ID
	private String BO_KEY; // 数据对象KEY
	private String BO_VALUE; // 数据对象VALUE 
	private String ADD1; // 附加字段1
	private String ADD2; // 附加字段2 
	private String ADD3; // 附加字段3
	private String ADD4; // 附加字段4
	private String ADD5; // 附加字段5
	private String BPLATFORM; // 系统平台

	
	private Page page; // 分页对象

	public Page getPage() {
		if (page == null)
			page = new Page();
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getDB_ID() {
		return DB_ID;
	}

	public void setDB_ID(String dB_ID) {
		DB_ID = dB_ID;
	}

	public String getBO_NAME() {
		return BO_NAME;
	}

	public void setBO_NAME(String bO_NAME) {
		BO_NAME = bO_NAME;
	}

	public String getBO_ID() {
		return BO_ID;
	}

	public void setBO_ID(String bO_ID) {
		BO_ID = bO_ID;
	}

	public String getBO_KEY() {
		return BO_KEY;
	}

	public void setBO_KEY(String bO_KEY) {
		BO_KEY = bO_KEY;
	}

	public String getBO_VALUE() {
		return BO_VALUE;
	}

	public void setBO_VALUE(String bO_VALUE) {
		BO_VALUE = bO_VALUE;
	}

	public String getADD1() {
		return ADD1;
	}

	public void setADD1(String aDD1) {
		ADD1 = aDD1;
	}

	public String getADD2() {
		return ADD2;
	}

	public void setADD2(String aDD2) {
		ADD2 = aDD2;
	}

	public String getADD3() {
		return ADD3;
	}

	public void setADD3(String aDD3) {
		ADD3 = aDD3;
	}

	public String getADD4() {
		return ADD4;
	}

	public void setADD4(String aDD4) {
		ADD4 = aDD4;
	}

	public String getADD5() {
		return ADD5;
	}

	public void setADD5(String aDD5) {
		ADD5 = aDD5;
	}

	public String getBPLATFORM() {
		return BPLATFORM;
	}

	public void setBPLATFORM(String bPLATFORM) {
		this.BPLATFORM = bPLATFORM;
	}

}
