package com.ibm.ams.service.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.ams.dao.DaoSupport;
import com.ibm.ams.entity.system.Intf;
import com.ibm.ams.entity.system.Role;
import com.ibm.ams.service.role.RoleManager;
import com.ibm.ams.util.PageData;


@Service("roleService")
public class RoleService implements RoleManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/**列出此组下级角色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Role> queryRoleInfo(PageData pd) throws Exception {
		return (List<Role>) dao.findForList("RoleMapper.QueryRolesInfo", pd);
	}
    
	/**通过id查找
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findObjectById(PageData pd) throws Exception {
		return (PageData)dao.findForObject("RoleMapper.findObjectById", pd);
	}
	
	/**添加
	 * @param pd
	 * @throws Exception
	 */
	public int add(PageData pd) throws Exception {
		return (int)dao.save("RoleMapper.insert", pd);
	}
	
	/**保存修改
	 * @param pd
	 * @throws Exception
	 */
	public int updateRole(PageData pd) throws Exception {
		return (int)dao.update("RoleMapper.updateRole", pd);
	}
	
	/**删除角色
	 * @param ROLE_ID
	 * @throws Exception
	 */
	public int deleteRoleById(String ROLE_ID) throws Exception {
		return (int)dao.delete("RoleMapper.deleteRoleById", ROLE_ID);
	}
	/**
	 * 根据角色ID查询Intf表内容
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Intf> QueryIntfbyRoleID(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<Intf>) dao.findForList("RoleMapper.QueryIntfbyRoleID", pd);
	}


}
