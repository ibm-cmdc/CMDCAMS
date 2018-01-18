package com.ibm.ams.service.roleintf.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.ams.dao.DaoSupport;
import com.ibm.ams.entity.system.RoleIntf;
import com.ibm.ams.service.roleintf.RoleIntfManager;
import com.ibm.ams.util.PageData;

@Service("roleIntfService")
public class RoleIntfService implements RoleIntfManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public List<RoleIntf> QueryRoleIntf(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<RoleIntf>) dao.findForList("RoleIntfMapper.QueryRoleIntf", pd);
	}

	public List<RoleIntf> QueryRoleAndIntf(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<RoleIntf>) dao.findForList("RoleIntfMapper.QueryRoleAndIntf", pd);
	}
	public void CreateRoleIntf(RoleIntf roleIntf) throws Exception {
		// TODO Auto-generated method stub
		dao.save("RoleIntfMapper.insertRoleIntf", roleIntf);
	}

	public void UpdateRoleIntf(RoleIntf roleIntf) throws Exception {
		// TODO Auto-generated method stub
		 dao.update("RoleIntfMapper.updateRoleIntf", roleIntf);
	}

	public void DeleteRoleIntf(String db_id) throws Exception {
		// TODO Auto-generated method stub
		dao.save("RoleIntfMapper.deleteRoleIntfById", db_id);
	}

}
