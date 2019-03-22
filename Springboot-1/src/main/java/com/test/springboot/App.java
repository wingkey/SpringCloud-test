package com.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 
 * @author dell @SpringBootApplication(一般放到项目同级目录下，以保证能扫描到所有包)
 */

@EnableEurekaClient//表示服务发现类
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * 设置匹配.action后缀的请求
	 * 
	 * @param dispatcherServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
		//开启访问权限(有很大的问题，这个对所有请求全部生效，根本不管你是不是静态文件，加了静态文件又会不知道哪个是请求，就是你的请求用静态文件结尾照样访问)
		/*bean.addUrlMappings("*.action");
		//下面全是开放静态资源访问权限
		bean.addUrlMappings("*.html");
		bean.addUrlMappings("*.css");
		bean.addUrlMappings("*.js");
		bean.addUrlMappings("*.png");
		bean.addUrlMappings("*.gif");
		bean.addUrlMappings("*.ico");
		bean.addUrlMappings("*.jpeg");
		bean.addUrlMappings("*.jpg");*/
		return bean;
	}

}
