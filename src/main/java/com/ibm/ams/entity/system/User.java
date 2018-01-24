package com.ibm.ams.entity.system;

import com.ibm.ams.entity.Page;

/**
 * 用户主表
 */
public class User {
	private String USER_ID; // 员工号
	private String USERNAME; // 登录用户名
	private String UPASSWORD; // 登录密码
	private String UNAME; // 登录用户描述
	private String ROLE_ID; // 角色ID
	private String LAST_LOGIN; // 最后登录时间
	private String LAST_IP; // 最后IP
	private String STATUS; // 状态
	private String EMAIL; // 电子邮箱
	private String PHONE; // 手机号码
	private String UGROUP; // 集团
	private String UPLATFORM; // 系统平台
	private String UCOMPANY; // 公司
	private String UCOSTCENTER; // 成本中心
	private String UORGANIZATION;// 组织单元
	private String UPOSITION; // 职位
	private String CREATETIME; // 创建时间
	private String INTERSTART; // 有效期开始
	private String INTERDURA; // 有效期间
	private String LAST_CHANGE; // 最后修改日期
	private String OPENID; // 微信公众号ID
	private String SECPOLICY; // 密码策略
	private String TOKEN; //

	private Role role; // 角色对象
	private Page page; // 分页对象
	// private String SKIN; //皮肤

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		this.USER_ID = uSER_ID;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		this.USERNAME = uSERNAME;
	}

	public String getUPASSWORD() {
		return UPASSWORD;
	}

	public void setUPASSWORD(String uPASSWORD) {
		this.UPASSWORD = uPASSWORD;
	}

	public String getUNAME() {
		return UNAME;
	}

	public void setUNAME(String uNAME) {
		this.UNAME = uNAME;
	}

	public String getUGROUP() {
		return UGROUP;
	}

	public void setUGROUP(String uGROUP) {
		this.UGROUP = uGROUP;
	}

	public String getUPLATFORM() {
		return UPLATFORM;
	}

	public void setUPLATFORM(String uPLATFORM) {
		this.UPLATFORM = uPLATFORM;
	}

	public String getUCOMPANY() {
		return UCOMPANY;
	}

	public void setUCOMPANY(String uCOMPANY) {
		this.UCOMPANY = uCOMPANY;
	}

	public String getUCOSTCENTER() {
		return UCOSTCENTER;
	}

	public void setUCOSTCENTER(String uCOSTCENTER) {
		this.UCOSTCENTER = uCOSTCENTER;
	}

	public String getUORGANIZATION() {
		return UORGANIZATION;
	}

	public void setUORGANIZATION(String uORGANIZATION) {
		this.UORGANIZATION = uORGANIZATION;
	}

	public String getROLE_ID() {
		return ROLE_ID;
	}

	public void setROLE_ID(String rOLE_ID) {
		this.ROLE_ID = rOLE_ID;
	}

	public String getLAST_LOGIN() {
		return LAST_LOGIN;
	}

	public void setLAST_LOGIN(String lAST_LOGIN) {
		this.LAST_LOGIN = lAST_LOGIN;
	}

	public String getLAST_IP() {
		return LAST_IP;
	}

	public void setLAST_IP(String lAST_IP) {
		this.LAST_IP = lAST_IP;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		this.STATUS = sTATUS;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		this.EMAIL = eMAIL;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String pHONE) {
		this.PHONE = pHONE;
	}

	public String getUPOSITION() {
		return UPOSITION;
	}

	public void setUPOSITION(String uPOSITION) {
		UPOSITION = uPOSITION;
	}


	public String getCREATETIME() {
		return CREATETIME;
	}

	public void setCREATETIME(String cREATETIME) {
		this.CREATETIME = cREATETIME;
	}

	public String getINTERSTART() {
		return INTERSTART;
	}

	public void setINTERSTART(String iNTERSTART) {
		this.INTERSTART = iNTERSTART;
	}

	public String getINTERDURA() {
		return INTERDURA;
	}

	public void setINTERDURA(String iNTERDURA) {
		this.INTERDURA = iNTERDURA;
	}

	public String getLAST_CHANGE() {
		return LAST_CHANGE;
	}

	public void setLAST_CHANGE(String lAST_CHANGE) {
		this.LAST_CHANGE = lAST_CHANGE;
	}

	public String getOPENID() {
		return OPENID;
	}

	public void setOPENID(String oPENID) {
		this.OPENID = oPENID;
	}

	public String getSECPOLICY() {
		return SECPOLICY;
	}

	public void setSECPOLICY(String sECPOLICY) {
		this.SECPOLICY = sECPOLICY;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Page getPage() {
		if (page == null)
			page = new Page();
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getTOKEN() {
		return TOKEN;
	}

	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}
}
