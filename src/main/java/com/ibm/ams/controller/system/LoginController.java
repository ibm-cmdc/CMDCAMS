package com.ibm.ams.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.ams.controller.base.BaseController;
import com.ibm.ams.entity.system.Menu;
import com.ibm.ams.init.AmsCache;
import com.ibm.ams.service.user.UserManager;
import com.ibm.ams.util.PageData;
import com.ibm.ams.util.RightsHelper;

import net.sf.json.JSONArray;


@Controller
public class LoginController extends BaseController {
	
	@Resource(name="userService")
	private UserManager userService;
	
	/**访问登录页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login")
	public ModelAndView toLogin(HttpSession httpSession) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String name = pd.getString("name");
		String pwd = pd.getString("pwd");
		
		PageData pd1 = new PageData();
		pd1.put("USERNAME", "zhaol");
		pd1.put("PASSWORD", "a63802a60e54a195fc1c8181fb429be7f4d35764");
		pd1.put("USER_ID", "2");//必须是数字
		pd1 = userService.getUserByNameAndPwd(pd1);
		String rights = pd1.getString("RIGHTS");
		String username = pd1.getString("NAME");
		if(rights==null || "".equals(rights)){
			throw new Exception("用户无权限！");
		}
		httpSession.setAttribute("v_right", rights);
		httpSession.setAttribute("v_username", username);
		//缓存获取菜单
		AmsCache amsCache = AmsCache.getInstance();
		Object map = amsCache.getMap("allmenuList");
		//获取有权限的菜单
		List<Menu> readMenu = new ArrayList<Menu>();
		if(map!=null && map instanceof List){
			List<Menu> allmenuList = (List<Menu>) map;
			readMenu = this.readMenu(allmenuList, rights);
		}
		//输出菜单为json格式
		JSONArray arr = JSONArray.fromObject(readMenu);
		String json = arr.toString();
		
		System.out.println(name+"--"+pwd+"--"+pd1.getString("RIGHTS"));
		
		mv.setViewName("mdm/index");
		mv.addObject("pd",pd);
		
		return mv;
	}
	
	/** 获取登录用户所有菜单
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getmenu", method = RequestMethod.POST)
	@ResponseBody
	public String getMenu(String rights)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		AmsCache amsCache = AmsCache.getInstance();
		Object map = amsCache.getMap("allmenuList");
		
		
		List<Menu> readMenu = new ArrayList<Menu>();
		if(map!=null && map instanceof List){
			List<Menu> allmenuList = (List<Menu>) map;
			readMenu = this.readMenu(allmenuList,rights );
		}
		
		JSONArray arr = JSONArray.fromObject(readMenu);
		String json = arr.toString();
		
		return json;
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
