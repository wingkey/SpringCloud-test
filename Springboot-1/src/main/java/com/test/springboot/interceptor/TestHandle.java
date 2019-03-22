package com.test.springboot.interceptor;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.springboot.config.FilterTestConfig;

/**
 * 测试拦截器
 * @author dell
 *
 */

@Component
public class TestHandle implements HandlerInterceptor {
	
  
	@Autowired
	private FilterTestConfig filterconfig;
	

	@Override//请求处理前
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().substring(request.getContextPath().length()); //请求的url
		System.out.println(request.getRequestURI());
		
        System.out.println(url);
        
        
        //这里假装要验证登录（没写所以肯定会false）
        HttpSession session = request.getSession(true);
        //	        得到对象
        Object admin = session.getAttribute("admin");
		
		/*if (admin==null) {
			response.sendRedirect(request.getContextPath()+"/freemarker/login.action");
			return false;
		}*/
		
		return true;
       
	}

	@Override//请求处理
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("xxxx");
		
	}

	@Override//请求结束
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("yuyyy");
		
	}

	
	
	
	/**
	 * 判断是否在不要过滤的类中
	 * @param url
	 * @return
	 */
	public boolean isInclude(String url) {
		for (String pattern : new  ArrayList<String>(Arrays.asList( filterconfig .getFilters()))) {
            
            if (pattern.equals(url)) {
                return true;
            }
        }
		return false;
	}
	
	
	
	
}
