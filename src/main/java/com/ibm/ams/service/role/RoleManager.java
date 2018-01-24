package com.ibm.ams.service.role;

import java.util.List;

import com.ibm.ams.entity.system.Intf;
import com.ibm.ams.entity.system.Role;
import com.ibm.ams.util.PageData;


public interface RoleManager {
	
	/**列出此组下级角色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Role> queryRoleInfo(PageData pd) throws Exception;
	
	
	/**
	 * 根据角色ID查询Intf表内容
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<Intf> QueryIntfbyRoleID(PageData pd) throws Exception;
	
	/**通过id查找
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findObjectById(PageData pd) throws Exception;
	
	/**添加
	 * @param pd
	 * @throws Exception
	 */
	public int add(PageData pd) throws Exception;
	
	/**保存修改
	 * @param pd
	 * @throws Exception
	 */
	public int updateRole(PageData pd) throws Exception;
	
	/**删除角色
	 * @param ROLE_ID
	 * @throws Exception
	 */
	public int deleteRoleById(String ROLE_ID) throws Exception;
	
	

}
