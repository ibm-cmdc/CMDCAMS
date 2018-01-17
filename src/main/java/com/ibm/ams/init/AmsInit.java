package com.ibm.ams.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.ams.dao.redis.queue.QueueListener;
import com.ibm.ams.dao.redis.queue.SubClient;
import com.ibm.ams.entity.system.Menu;
import com.ibm.ams.service.menu.MenuManager;
import com.ibm.ams.util.DbFH;

import redis.clients.jedis.JedisPubSub;


public class AmsInit extends HttpServlet{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
	private MenuManager menuService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try{
			ServletContext application = config.getServletContext();
			WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(application);
			menuService = (MenuManager)wac.getBean("menuService");
			//获取全部菜单
			List<Menu> allmenuList = new ArrayList<Menu>();
			allmenuList = menuService.listAllMenuQx("100");	
			
			logger.info("获取菜单总数："+allmenuList.size());
			
			AmsCache amsCache = AmsCache.getInstance();
			amsCache.putMap("AMSMenuList", allmenuList);
			
			
			
		}catch(Exception e){
			logger.error("系统初始化错误"+e);
			throw new ServletException("系统初始化错误");
		}
	}
	
}
