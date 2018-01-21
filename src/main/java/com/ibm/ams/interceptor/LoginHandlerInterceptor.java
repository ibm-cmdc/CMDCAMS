package com.ibm.ams.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ibm.ams.entity.system.Intf;
import com.ibm.ams.entity.token.TokenModel;
import com.ibm.ams.service.role.RoleManager;
import com.ibm.ams.service.token.TokenManager;
import com.ibm.ams.util.Const;
import com.ibm.ams.util.PageData;


@Component
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired    
	private TokenManager manager;
	
	@Resource(name="roleService")
	private RoleManager roleService;
	
	//拦截前处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		//如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {           
			return true;        
		}
		
		//验证接口权限
		StringBuffer requestURL = request.getRequestURL();
		String requestURI = request.getRequestURI();
		System.out.println(requestURL+"--"+requestURI);
		
		String[] split = requestURI.split("/",-1);
		for(String s:split){
			System.out.println(s);
		}
		
		
		PageData pd1 = new PageData();
		pd1.put("ROLE_ID", "9999");
		List<Intf> queryIntfbyRoleID = roleService.QueryIntfbyRoleID(pd1);
		
		for(Intf list : queryIntfbyRoleID){
			System.out.println(list.getINTF_NAME());
		}
		
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;        
		Method method = handlerMethod.getMethod();  
		
		JSONObject rspJson = new JSONObject();
		response.setContentType("application/json");
		
		//从header中得到token        
		String authorization = request.getHeader(Const.AUTHORIZATION); 
		
		if(authorization==null || "".equals(authorization)){
			rspJson.put(Const.RESULT_CODE, "E");
			rspJson.put(Const.RESULT_MSG, "接口未传输Token!");
			PrintWriter out = response.getWriter();
			out.print(rspJson.toString());
	        out.flush();
	        return false;
		}
		
		//验证token        
		TokenModel model = manager.getToken(authorization);        
		if (manager.checkToken(model)) {            
			//如果token验证成功，将token对应的用户id存在request中，便于之后注入            
			//request.setAttribute(Const.CURRENT_USER_ID, model.getUserId());            
			
			
			
			
			return true;        
		}else{
			//response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			
			rspJson.put(Const.RESULT_CODE, "401");
			rspJson.put(Const.RESULT_MSG, "当前用户无权限");
			
			PrintWriter out = response.getWriter();
			out.print(rspJson.toString());
	        out.flush();
			//throw new AuthorizationException("当前请求无权限!");
			return false;
		}        
		
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
