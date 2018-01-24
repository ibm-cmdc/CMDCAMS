package com.ibm.ams.service.user;

import java.util.List;

import com.ibm.ams.entity.Page;
import com.ibm.ams.entity.system.Bo;
import com.ibm.ams.entity.system.User;
import com.ibm.ams.util.PageData;



public interface UserManager {
	
	/**登录判断
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData getUserByNameAndPwd(PageData pd)throws Exception;
	/**
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Bo> queryBoByUidUpa(PageData pd)throws Exception;
	
	/**
	 * 修改用户信息
	 * @param pd
	 * @throws Exception
	 */
	public int updateUser(PageData pd)throws Exception;
	/**
	 * 根据名称和密码查询用户和用户角色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public User getUserAndRoleByNameAndPwd(PageData pd)throws Exception;
	
	/**
	 * 查询用户所有信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<User> queryUserInfo(PageData pd)throws Exception;
	/**保存用户
	 * @param pd
	 * @throws Exception
	 */
	public int saveU(PageData pd)throws Exception;
	
	
	
	
	
	
	/**更新登录时间
	 * @param pd
	 * @throws Exception
	 */
	public void updateLastLogin(PageData pd)throws Exception;
	
	/**通过用户ID获取用户信息和角色信息
	 * @param USER_ID
	 * @return
	 * @throws Exception
	 */
	public User getUserAndRoleById(String USER_ID) throws Exception;
	
	/**通过USERNAEME获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUsername(PageData pd)throws Exception;
	
	/**列出某角色下的所有用户
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllUserByRoldId(PageData pd) throws Exception;
	
	/**保存用户IP
	 * @param pd
	 * @throws Exception
	 */
	public void saveIP(PageData pd)throws Exception;
	
	/**用户列表
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listUsers(Page page)throws Exception;
	
	/**用户列表(弹窗选择用)
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listUsersBystaff(Page page)throws Exception;
	
	/**通过邮箱获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUE(PageData pd)throws Exception;
	
	/**通过编号获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByUN(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**修改用户
	 * @param pd
	 * @throws Exception
	 */
	public void editU(PageData pd)throws Exception;
	

	/**删除用户
	 * @param pd
	 * @throws Exception
	 */
	public int deleteU(PageData pd)throws Exception;
	
	/**批量删除用户
	 * @param USER_IDS
	 * @return 
	 * @throws Exception
	 */
	public int deleteAllU(String[] USER_IDS)throws Exception;
	
	/**用户列表(全部)
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllUser(PageData pd)throws Exception;
	
	/**获取总数
	 * @param pd
	 * @throws Exception
	 */
	public PageData getUserCount(String value)throws Exception;
	
}
