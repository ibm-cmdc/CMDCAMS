package com.ibm.ams.service.bo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.ams.dao.DaoSupport;
import com.ibm.ams.entity.system.Bo;
import com.ibm.ams.service.bo.BoManager;
import com.ibm.ams.util.PageData;

@Service("boService")
public class BoService implements BoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<Bo> QueryBo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<Bo>) dao.findForList("BoMapper.QueryBo", pd);
	}

	public void CreateBo(Bo bo) throws Exception {
		// TODO Auto-generated method stub
		dao.save("BoMapper.insertBo", bo);
	}

	public void UpdateBo(Bo bo) throws Exception {
		// TODO Auto-generated method stub
		 dao.update("BoMapper.updateBo", bo);
	}

	public void DeleteBo(String db_id) throws Exception {
		// TODO Auto-generated method stub
		dao.save("BoMapper.deleteBoById", db_id);
	}

}
