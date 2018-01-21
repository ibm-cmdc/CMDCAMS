package com.ibm.ams.service.intf;

import java.util.List;

import com.ibm.ams.entity.system.Intf;
import com.ibm.ams.entity.system.Role;
import com.ibm.ams.util.PageData;

public interface IntfManager {

	/**
	 * 查询数据对象
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Intf>  QueryIntf(PageData pd)throws Exception;
	
	public List<Role> QueryRolebyIntfID(PageData pd) throws Exception;
	/**
	 * 创建数据对象
	 * @param intf
	 * @throws Exception
	 */
	public void CreateIntf(Intf intf)throws Exception;
	/**
	 * 修改数据对象
	 * @param intf
	 * @throws Exception
	 */
	public void UpdateIntf(Intf intf)throws Exception;
	/**
	 * 删除数据对象
	 * @param intfId
	 * @throws Exception
	 */
	public void DeleteIntf(String intfId)throws Exception;
}
