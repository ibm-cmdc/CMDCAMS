package com.ibm.ams.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.ams.controller.base.BaseController;
import com.ibm.ams.entity.system.Menu;
import com.ibm.ams.entity.system.User;
import com.ibm.ams.entity.token.TokenModel;
import com.ibm.ams.init.AmsCache;
import com.ibm.ams.service.token.TokenManager;
import com.ibm.ams.service.user.UserManager;
import com.ibm.ams.util.Const;
import com.ibm.ams.util.PageData;
import com.ibm.ams.util.RightsHelper;

import net.sf.json.JSONArray;


@Controller
public class LoginController extends BaseController {
	
	@Resource(name="userService")
	private UserManager userService;
	@Autowired    
	private TokenManager manager;
	
	/**访问登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	@ResponseBody
	public String toLogin(String txtuid,String txtpwd) throws Exception{
		
		JSONObject rspJson = new JSONObject();
		
		PageData pd1 = new PageData();
		pd1.put("USERNAME", txtuid);
		pd1.put("UPASSWORD", txtpwd);
		//pd1.put("USER_ID", "2");//必须是数字
		User user = userService.getUserAndRoleByNameAndPwd(pd1);
		
		if(user==null){
			return returnMessage("E","当前用户名或密码错误，请重新尝试!");
		}
		
		String rights = user.getRole().getRIGHTS();
		String roleid= user.getRole().getROLE_ID();
		
		TokenModel token = manager.createToken(user.getUSERNAME());
		
		if(token==null || "".equals(token.getToken())){
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "获取登录Token失败!");
			return rspJson.toString();
		}
		
		rspJson.put(Const.RESULT_CODE, "S");
		rspJson.put(Const.RESULT_MSG, "登录成功!");
		rspJson.put("RIGHTS", rights);
		rspJson.put("ROLE_ID", roleid);
		rspJson.put("TOKEN", token.getToken());
		
		
		return rspJson.toString();
	}
	
	/** 获取登录用户所有菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getmenu", method = RequestMethod.GET)
	@ResponseBody
	public Object getMenu(String RIGHTS,String USER_ID,String PLATFORM)throws Exception{
		
		//缓存获取资产系统菜单 
		AmsCache amsCache = AmsCache.getInstance();
		Object map = amsCache.getMap("AMSMenuList");
		
		//获取有权限的菜单
		List<Menu> readMenu = new ArrayList<Menu>();
		if(map!=null && map instanceof List){
			List<Menu> allmenuList = (List<Menu>) map;
			readMenu = this.readMenu(allmenuList,RIGHTS);
		}
		//输出菜单为json格式
		JSONArray arr = JSONArray.fromObject(readMenu);
		String json = arr.toString();
		
		String replace = json.replace("[]", "null");
		
		
		return readMenu;
	}
	
	
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public List<Menu> readMenu(List<Menu> menuList,String roleRights){
		for(int i=0;i<menuList.size();i++){
			//set是否对该菜单有权限
			menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMENU_ID()));
			if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
				this.readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
			}
		}
		return menuList;
	}

}
