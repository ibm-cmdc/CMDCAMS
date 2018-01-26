package com.ibm.ams.controller.system;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.ams.controller.base.BaseController;
import com.ibm.ams.entity.system.Menu;
import com.ibm.ams.entity.system.Role;
import com.ibm.ams.entity.system.User;
import com.ibm.ams.service.menu.MenuManager;
import com.ibm.ams.service.role.RoleManager;
import com.ibm.ams.service.user.UserManager;
import com.ibm.ams.util.Const;
import com.ibm.ams.util.DateUtil;
import com.ibm.ams.util.PageData;
import com.ibm.ams.util.RightsHelper;
import com.ibm.ams.util.Tools;

import net.sf.json.JSONArray;

@Controller
public class UmeController extends BaseController{
	@Resource
	private UserManager userService ;
	@Resource
	private RoleManager roleService ;
	@Resource
	private MenuManager menuService;
	/**
	 * 查询用户信息
	 * @param limit 当前页
	 * @param offset 每页条数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryUser", method = RequestMethod.GET)
	@ResponseBody
	public String queryUser() throws Exception{
		PageData pd = this.getPageData();
		int limit = Integer.valueOf((String)pd.get("limit"));
		int offset = Integer.valueOf((String)pd.get("offset"));
		JSONObject rspJson = new JSONObject();
		int rowno=(limit-1)*offset+1;
		int rownum=limit*offset;
		 //                        1    2       3
		//起始条(页码-1)*每页长度+1   1 4  5  8    9  12 
		//结束条limit*offset
		pd.put("rowno", rowno);
		pd.put("rownum", rownum);
		List<User> listUser = userService.queryUserInfo(pd);
		PageData userCount = userService.getUserCount("");
		BigDecimal totalResult=(BigDecimal)userCount.get("userCount");
		if (null==listUser||"".equals(listUser)||null==userCount||"".equals(userCount)) {
			JSONArray arr = JSONArray.fromObject(listUser);
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询用户信息失败!");
			rspJson.put("ALLUSER", arr);
			rspJson.put("TOTALRESULT", totalResult);
			return rspJson.toString();
		}
		
		JSONArray arr = JSONArray.fromObject(listUser);
		rspJson.put(Const.RESULT_CODE, "S");
		rspJson.put(Const.RESULT_MSG, "查询用户信息成功!");
		rspJson.put("ALLUSER", arr);
		rspJson.put("TOTALRESULT", totalResult);
		return rspJson.toString();
	}
	
	/**
	 * 查询角色信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/queryRole", method = RequestMethod.GET)
	@ResponseBody
	public String queryRole() throws Exception{
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
        List<Role> listAllRolesByRIdPa = roleService.queryRoleInfo(pd);
        JSONArray arr = JSONArray.fromObject(listAllRolesByRIdPa);
        if (null!=listAllRolesByRIdPa||"".equals(listAllRolesByRIdPa)) {
        	int size = listAllRolesByRIdPa.size();
        	if (0<size) {
        		rspJson.put(Const.RESULT_CODE, "S");
        		rspJson.put("LISTROLEINFO", arr);
    			rspJson.put(Const.RESULT_MSG, "查询角色信息成功,共"+size+"!");
			}else{
				rspJson.put(Const.RESULT_CODE, "W");
				rspJson.put("LISTROLEINFO", "");
				rspJson.put(Const.RESULT_MSG, "查询角色信息成功,共"+size+"!");
			}
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询角色信息失败!");
		}
		return rspJson.toString();
	}
	@RequestMapping(value="/queryMenu", method = RequestMethod.GET)
	@ResponseBody
	public String queryMenu() throws Exception{
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
        List<Menu> queryMenu = menuService.queryMenu(pd);
        JSONArray arr = JSONArray.fromObject(queryMenu);
        if (null!=queryMenu||"".equals(queryMenu)) {
        	int size = queryMenu.size();
        	if (0<size) {
        		rspJson.put(Const.RESULT_CODE, "S");
        		rspJson.put("LISTROLEINFO", arr);
    			rspJson.put(Const.RESULT_MSG, "查询菜单信息成功,共"+size+"!");
			}else{
				rspJson.put(Const.RESULT_CODE, "W");
				rspJson.put("LISTROLEINFO", "");
				rspJson.put(Const.RESULT_MSG, "查询菜单信息成功,共"+size+"!");
			}
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "查询菜单信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 创建菜单信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveMenu", method = RequestMethod.GET)
	@ResponseBody
	public String saveMenu() throws Exception{
		PageData pd = this.getPageData();
		JSONObject rspJson = new JSONObject();
        int saveMenu = menuService.saveMenu(pd);
        String menu_name = (String) pd.get("MENU_NAME");
        if (0<saveMenu) {
        	
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加菜单名称【"+menu_name+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加菜单名称【"+menu_name+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 保存角色信息
	 * @param pd
	 * @param rights
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveRole", method = RequestMethod.GET)
	@ResponseBody
	public String saveRole() throws Exception{
		PageData pd = this.getPageData();
		String rights = (String)pd.get("rights");
		JSONObject rspJson = new JSONObject();
		BigInteger rights_JM = RightsHelper.sumRights(Tools.str2StrArray(rights));
        pd.put("RIGHTS", rights_JM);
        int add = roleService.add(pd);
        String role_name = (String) pd.get("ROLE_NAME");
        if (0<add) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加角色【"+role_name+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加角色【"+role_name+"】信息失败!");
		}
		return rspJson.toString();
	}
	
	/**
	 * 保存角色信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveUser", method = RequestMethod.GET)
	@ResponseBody
	public String saveUser() throws Exception{
		PageData pd=this.getPageData();
		pd.put("UPASSWORD", "abcd1234");//初始化默认密码
		pd.put("CREATETIME", DateUtil.getStringCurrentDate("yyyy/MM/dd"));//创建时间
		pd.put("LAST_IP", "");//登录IP
		pd.put("SECPOLICY", "");//密码策略
		JSONObject rspJson = new JSONObject();
		String username = (String) pd.get("USERNAME");
		int saveU = userService.saveU(pd);
		if (0<saveU) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "添加用户【"+username+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "添加用户【"+username+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 更新菜单
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateMenu", method = RequestMethod.GET)
	@ResponseBody
	public String updateMenu() throws Exception{
		PageData pd=this.getPageData();
		JSONObject rspJson = new JSONObject();
		String menu_id = (String) pd.get("MENU_ID");
		int updateMenu = menuService.updateMenu(pd);
		if (0<updateMenu) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新菜单ID【"+menu_id+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新菜单ID【"+menu_id+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 更新角色
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateRole", method = RequestMethod.GET)
	@ResponseBody
	public String updateRole() throws Exception{
		PageData pd=this.getPageData();
		JSONObject rspJson = new JSONObject();
		String role_id = (String) pd.get("ROLE_ID");
		int updateRole = roleService.updateRole(pd);
		if (0<updateRole) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新角色ID【"+role_id+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新角色ID【"+role_id+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 更新用户
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateUser", method = RequestMethod.GET)
	@ResponseBody
	public String updateUser() throws Exception{
		PageData pd=this.getPageData();
		JSONObject rspJson = new JSONObject();
		String user_id = (String) pd.get("USER_ID");
		int updateUser = userService.updateUser(pd);
		if (0<updateUser) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "更新用户ID【"+user_id+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "更新用户ID【"+user_id+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 删除菜单
	 * @param menu_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteMenuByID", method = RequestMethod.GET)
	@ResponseBody
	public String deleteMenuByID(String menu_id) throws Exception{
		JSONObject rspJson = new JSONObject();
		int deleteMenuById = menuService.deleteMenuById(menu_id);
		if (0<deleteMenuById) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【"+menu_id+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【"+menu_id+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 删除菜单
	 * @param menu_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deletePLMenuById", method = RequestMethod.GET)
	@ResponseBody
	public String deletePLMenuById(String[] MENU_ID) throws Exception{
		JSONObject rspJson = new JSONObject();
		int deleteMenuById = menuService.deletePLMenuById(MENU_ID);
		if (0<deleteMenuById) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【"+MENU_ID+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除菜单ID【"+MENU_ID+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 根据角色ID删除角色信息
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteRoleByID", method = RequestMethod.GET)
	@ResponseBody
	public String deleteRoleByID() throws Exception{
		JSONObject rspJson = new JSONObject();
		PageData pd=this.getPageData();
		String role_id=(String)pd.get("ROLE_ID");
		int deleteRoleById = roleService.deleteRoleById(role_id);
		if (0<deleteRoleById) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除角色ID【"+role_id+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除角色ID【"+role_id+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 根据用户id删除用户
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteUserByID", method = RequestMethod.GET)
	@ResponseBody
	public String deleteUserByID() throws Exception{
		JSONObject rspJson = new JSONObject();
		PageData pd=this.getPageData();
		String user_id=(String)pd.get("USER_ID");
		int deleteU = userService.deleteU(pd);
		if (0<deleteU) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除用户ID【"+user_id+"】信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除用户ID【"+user_id+"】信息失败!");
		}
		return rspJson.toString();
	}
	/**
	 * 批量删除用户，根据用户ID
	 * @param user_ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deletePLUserByID", method = RequestMethod.GET)
	@ResponseBody
	public String deletePLUserByID(String[] USER_ID) throws Exception{
		JSONObject rspJson = new JSONObject();
		int deleteAllU = userService.deleteAllU(USER_ID);
		if (0<deleteAllU) {
			rspJson.put(Const.RESULT_CODE, "S");
			rspJson.put(Const.RESULT_MSG, "删除用户【"+deleteAllU+"】条信息成功!");
		}else{
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "删除用户【"+deleteAllU+"】条信息失败!");
		}
		return rspJson.toString();
	}
}
