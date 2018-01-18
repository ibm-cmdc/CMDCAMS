package com.ibm.ams.service.userbo;

import java.util.List;

import com.ibm.ams.entity.system.UserBo;
import com.ibm.ams.util.PageData;

public interface UserBoManager {
	/**
	 * 查询数据对象
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<UserBo>  QueryUserBo(PageData pd)throws Exception;
	
	/**
	 * 查询User和bo信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<UserBo>  QueryUserAndBo(PageData pd)throws Exception;
	/**
	 * 创建数据对象
	 * @param userbo
	 * @throws Exception
	 */
	public void CreateUserBo(UserBo userbo)throws Exception;
	/**
	 * 修改数据对象
	 * @param userbo
	 * @throws Exception
	 */
	public void UpdateUserBo(UserBo userbo)throws Exception;
	/**
	 * 删除数据对象
	 * @param db_id
	 * @throws Exception
	 */
	public void DeleteUserBo(String db_id)throws Exception;
}
