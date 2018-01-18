package com.ibm.ams.service.userbo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.ams.dao.DaoSupport;
import com.ibm.ams.entity.system.UserBo;
import com.ibm.ams.service.userbo.UserBoManager;
import com.ibm.ams.util.PageData;
@Service("userBoService")
public class UserBoService implements UserBoManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	public List<UserBo> QueryUserBo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<UserBo>) dao.findForList("UserBoMapper.QueryUserBo", pd);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserBo> QueryUserAndBo(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<UserBo>) dao.findForList("UserBoMapper.QueryUserAndBo", pd);
	}
	public void CreateUserBo(UserBo userbo) throws Exception {
		// TODO Auto-generated method stub
		dao.save("UserBoMapper.insertUserBo", userbo);
	}

	public void UpdateUserBo(UserBo userbo) throws Exception {
		// TODO Auto-generated method stub
		dao.update("UserBoMapper.updateUserBo", userbo);
	}

	public void DeleteUserBo(String db_id) throws Exception {
		// TODO Auto-generated method stub
		dao.save("UserBoMapper.deleteUserBoById", db_id);
	}

	
	
	
}
