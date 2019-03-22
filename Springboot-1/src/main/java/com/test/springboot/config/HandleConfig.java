package com.test.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.test.springboot.interceptor.TestHandle;

/**
 * 拦截器配置类，用于在初始化时加载拦截器的
 * 
 * 说明：
 * 1.定义拦截器实现HandlerInterceptor
 * 2.写配置类即当前这个类，实现WebMvcConfigurerAdapter 将对应拦截器引入，这这样项目启动时就能扫描到这个拦截器了
 * 
 * 
 * 
 * 3.这种写法的拦截器在后来的版本中给废除了，所以见另一种写法的
 * @author dell
 *
 */


@SpringBootConfiguration
public class HandleConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private TestHandle handle; //自定义拦截器
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(handle).addPathPatterns("/**");//定义拦截规则
		//super.addInterceptors(registry);
	}
	
}
