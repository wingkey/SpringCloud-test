package com.test.springboot.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.springboot.config.FilterTestConfig;


public class TestFilter implements Filter{
	
	@Autowired
	public   FilterTestConfig config;  

	
	
	@Override//请求前
	public void init(FilterConfig filterConfig) throws ServletException {
		
		
	}

	@Override//处理请求
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()); //请求的url
        System.out.println(url);
        
        if (!url.endsWith(".action")) {
        	httpResponse.sendRedirect(httpRequest.getContextPath()+"/freemarker/S404");
        	return;
        }
        
        if (isInclude(url)) {
        	chain.doFilter(httpRequest, httpResponse); // 发到controller方法时候，参数居然是有的。
		}else {
			
			//这里假装要验证登录（没写所以肯定会false）
	        HttpSession session = httpRequest.getSession(true);
	        //	        得到对象
	        Object admin = session.getAttribute("admin");
			
			if (admin==null) {
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/freemarker/testlogin");
			}else {
				chain.doFilter(httpRequest, httpResponse);
			}
			
			//httpResponse.sendRedirect(httpRequest.getContextPath()+"/login/adminLogin");
			//chain.doFilter(httpRequest, httpResponse); // 发到controller方法时候，参数居然是有的。
		}
        
        
		
	}

	/**
	 * 判断是否在不要过滤的类中
	 * @param url
	 * @return
	 */
	public boolean isInclude(String url) {
		for (String pattern : new  ArrayList<String>(Arrays.asList(config.getFilters()))) {
            
            if (pattern.equals(url)) {
                return true;
            }
        }
		return false;
	}
	
	
	@Override//请求销毁
	public void destroy() {
		
		
	}

}
