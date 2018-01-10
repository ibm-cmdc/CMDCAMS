package com.ibm.ams.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	//拦截前处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		HttpSession session = request.getSession();  
		
		//保存菜单ID
		String parameter = request.getParameter("menuid");
		if(parameter!=null && !"".equals(parameter)){
			session.setAttribute("v_menuid", parameter);
		}
		System.out.println("进入拦截器");
		return true;
	};
	
	//拦截后处理
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	};
	
	//全部完成后处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	};
}
