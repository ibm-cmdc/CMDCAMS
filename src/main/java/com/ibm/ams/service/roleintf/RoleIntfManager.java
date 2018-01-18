package com.ibm.ams.service.roleintf;

import java.util.List;

import com.ibm.ams.entity.system.RoleIntf;
import com.ibm.ams.util.PageData;

public interface RoleIntfManager {
	/**
	 * 查询数据对象
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<RoleIntf>  QueryRoleIntf(PageData pd)throws Exception;
	public List<RoleIntf> QueryRoleAndIntf(PageData pd) throws Exception;
	/**
	 * 创建数据对象
	 * @param roleIntf
	 * @throws Exception
	 */
	public void CreateRoleIntf(RoleIntf roleIntf)throws Exception;
	/**
	 * 修改数据对象
	 * @param roleIntf
	 * @throws Exception
	 */
	public void UpdateRoleIntf(RoleIntf roleIntf)throws Exception;
	/**
	 * 删除数据对象
	 * @param db_id
	 * @throws Exception
	 */
	public void DeleteRoleIntf(String db_id)throws Exception;
}
