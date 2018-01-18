package com.ibm.ams.service.intf.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.ams.dao.DaoSupport;
import com.ibm.ams.entity.system.Intf;
import com.ibm.ams.service.intf.IntfManager;
import com.ibm.ams.util.PageData;

@Service("intfService")
public class IntfService implements IntfManager{
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<Intf> QueryIntf(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<Intf>) dao.findForList("IntfMapper.QueryIntf", pd);
	}

	public void CreateIntf(Intf intf) throws Exception {
		// TODO Auto-generated method stub
		dao.save("IntfMapper.insertIntf", intf);
	}

	public void UpdateIntf(Intf intf) throws Exception {
		// TODO Auto-generated method stub
		 dao.update("IntfMapper.updateIntf", intf);
	}

	public void DeleteIntf(String intfId) throws Exception {
		// TODO Auto-generated method stub
		dao.save("IntfMapper.deleteIntfById", intfId);
	}

}
