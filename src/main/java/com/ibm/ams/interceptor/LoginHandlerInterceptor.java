package com.ibm.ams.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ibm.ams.entity.token.TokenModel;
import com.ibm.ams.exception.AMSException;
import com.ibm.ams.exception.AuthorizationException;
import com.ibm.ams.service.token.TokenManager;
import com.ibm.ams.util.Const;


@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired    
	private TokenManager manager;
	
	//拦截前处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		/*
		StringBuffer sb = null;
		try{
			System.out.println(sb.toString());
		}catch(Exception e){
			throw new AMSException(e.getMessage());
		}
		*/
		
		//如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {           
			return true;        
		}
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;        
		Method method = handlerMethod.getMethod();    
		
		//从header中得到token        
		String authorization = request.getHeader(Const.AUTHORIZATION);     
		
		//验证token        
		TokenModel model = manager.getToken(authorization);        
		if (manager.checkToken(model)) {            
			//如果token验证成功，将token对应的用户id存在request中，便于之后注入            
			request.setAttribute(Const.CURRENT_USER_ID, model.getUserId());            
			return true;        
		}else{
			//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new AuthorizationException("当前请求无权限!");
		}        
		
		/*
		
		String path = request.getServletPath();
		HttpSession session = request.getSession(); 
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		
		//保存菜单ID
		String parameter = request.getParameter("menuid");
		if(parameter!=null && !"".equals(parameter)){
			session.setAttribute("v_menuid", parameter);
		}
		System.out.println("进入拦截器"+"path:"+path+"---"+"URI"+requestURI+"---"+"url"+requestURL);
		return true;
		
		*/
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
