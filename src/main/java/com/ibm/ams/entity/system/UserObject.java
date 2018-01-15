package com.ibm.ams.entity.system;

/**
 * 用户业务对象关联表
 */
public class UserObject {
	private String DB_ID;//	表ID
	private String USER_ID;//	用户ID
	private String BO_ID;//	业务对象ID
	public String getDB_ID() {
		return DB_ID;
	}
	public void setDB_ID(String dB_ID) {
		DB_ID = dB_ID;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getBO_ID() {
		return BO_ID;
	}
	public void setBO_ID(String bO_ID) {
		BO_ID = bO_ID;
	}
}
