package com.ibm.ams.entity.system;

import com.ibm.ams.entity.Page;

/**
 * 系统角色接口关联表
 */
public class RoleIntf {
	private String DB_ID;// 表ID
	private String ROLE_ID;// 角色ID
	private String INTF_ID;// 业务对象ID

	private Role role;
	private Intf intf;
	
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

	public String getROLE_ID() {
		return ROLE_ID;
	}

	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}

	public String getINTF_ID() {
		return INTF_ID;
	}

	public void setINTF_ID(String iNTF_ID) {
		INTF_ID = iNTF_ID;
	}
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Intf getIntf() {
		return intf;
	}

	public void setIntf(Intf intf) {
		this.intf = intf;
	}
}
