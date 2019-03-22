package com.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * -zuul：理由网关--简单说也是用来统一处理请求的，可以将所有请求全部配置到这里，然后在这个项目里统一配置请求去哪个项目里
 * 
 * @author dell
 *
 */

@EnableZuulProxy  //开启zuul配置  （基于过滤器的负载均衡配置工具） 类似中间件，用来维系ruibbo和其他注册项目的，具体需要配置的参数见配置文件
@EnableEurekaClient
@SpringBootApplication
public class SpringCloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulApplication.class, args);
	}

}
