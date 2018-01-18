package com.ibm.ams.service.bo;

import java.util.List;

import com.ibm.ams.entity.system.Bo;
import com.ibm.ams.util.PageData;

public interface BoManager {

	/**
	 * 查询数据对象
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Bo>  QueryBo(PageData pd)throws Exception;
	/**
	 * 创建数据对象
	 * @param bo
	 * @throws Exception
	 */
	public void CreateBo(Bo bo)throws Exception;
	/**
	 * 修改数据对象
	 * @param bo
	 * @throws Exception
	 */
	public void UpdateBo(Bo bo)throws Exception;
	/**
	 * 删除数据对象
	 * @param db_id
	 * @throws Exception
	 */
	public void DeleteBo(String db_id)throws Exception;
}
