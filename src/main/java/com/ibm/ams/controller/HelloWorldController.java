package com.ibm.ams.controller;

 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.ams.controller.base.BaseController;
import com.ibm.ams.dao.redis.RedisDao;
import com.ibm.ams.dao.redis.queue.PubClient;
import com.ibm.ams.dao.redis.queue.QueueListener;
import com.ibm.ams.dao.redis.queue.QueueThread;
import com.ibm.ams.dao.redis.queue.SubClient;
import com.ibm.ams.entity.system.Menu;
import com.ibm.ams.exception.AMSException;
import com.ibm.ams.init.AmsCache;
import com.ibm.ams.service.menu.MenuManager;
import com.ibm.ams.service.user.UserManager;
import com.ibm.ams.test.Print;
import com.ibm.ams.util.DbFH;
import com.ibm.ams.util.PageData;
import com.ibm.ams.util.RightsHelper;
import com.ibm.ams.util.Tools;

import net.sf.json.JSONArray;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

@Controller
public class HelloWorldController extends BaseController{
	
	@Resource(name="PrintService")
	private Print ps;
	@Resource(name="userService")
	private UserManager userService;
	@Resource(name="menuService")
	private MenuManager menuService;
	@Resource(name = "redisDaoImpl")
	private RedisDao redisDaoImpl;

	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public ModelAndView hello() throws AMSException{
		// TODO Auto-generated method stub
		
		PageData pd = new PageData();
//		pd = this.getPageData();
		
//		String string = pd.getString("name");
//		String key = pd.getString("key");
		
//		pd.put("USERNAME", "系统管理员");
//		pd.put("PASSWORD", "de41b7fb99201d8334c23c014db35ecd92df81bc");
//		pd.put("USER_ID", "1");
//		pd = userService.getUserByNameAndPwd(pd);
		
//		Map<String, String> jmap = new HashMap<String, String>();
//    	jmap.put("name", "fhadmin");
//    	jmap.put("age", "22");
//    	jmap.put("qq", "313596790");
//		System.out.println(redisDaoImpl.addMap("fh", jmap));				//存储Map
		
		/*  测试异常
		try{
			System.out.println("获取Map:"+redisDaoImpl.getMap("fh"));
		}catch(Exception e){
			throw new AMSException(e.getMessage());
		}
		*/
		
		
		
		
		BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray("101,102"));
		
		System.out.println(rights.toString()+"##############################");
		
		AmsCache amsCache = AmsCache.getInstance();
		Object map = amsCache.getMap("allmenuList");
		
		
		List<Menu> readMenu = new ArrayList<Menu>();
		if(map!=null && map instanceof List){
			List<Menu> allmenuList = (List<Menu>) map;
			readMenu = this.readMenu(allmenuList, rights.toString());
		}
		
		JSONArray arr = JSONArray.fromObject(readMenu);
		String json = arr.toString();
		
		ModelAndView mv = new ModelAndView();  
	       mv.addObject("message", json);  
	       mv.setViewName("hello");  
	       return mv;  
	       
	}
	
	
	@RequestMapping(value="/startRedis", method = RequestMethod.GET)
	@ResponseBody
	public String startRedis() throws Exception{
		QueueThread tt = new QueueThread();
		tt.start();
		return "S";
	}
	
	@RequestMapping(value="/sendQueue", method = RequestMethod.GET)
	@ResponseBody
	public String sendQueue() throws Exception{
		PubClient pubClient = new PubClient("127.0.0.1", "abcd1234");  
		final String channel = "CMDCAMS-channel";  
        pubClient.pub(channel, "test2");  
		return "S";
	}
	
	
	@RequestMapping(value="/ts", method = RequestMethod.GET)
    @ResponseBody
	public String transation(String id,String name){
		String print = ps.print();
		return print;
	}
	
	
	
	//--------------  工具
	
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public List<Menu> readMenu(List<Menu> menuList,String roleRights){
		for(int i=0;i<menuList.size();i++){
			menuList.get(i).setHasMenu(RightsHelper.testRights(roleRights, menuList.get(i).getMENU_ID()));
			if(menuList.get(i).isHasMenu()){		//判断是否有此菜单权限
				this.readMenu(menuList.get(i).getSubMenu(), roleRights);//是：继续排查其子菜单
			}else{
				menuList.remove(i);
			}
		}
		return menuList;
	}
	
	/**读取redis.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public Properties getPprVue(){
		InputStream inputStream = DbFH.class.getClassLoader().getResourceAsStream("redis.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			//读取配置文件出错
			e.printStackTrace();
		}
		return p;
	}

}
