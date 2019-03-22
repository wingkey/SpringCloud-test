package com.test.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@EnableEurekaClient//表示服务发现类
@RefreshScope//加了这个配置后就能自动刷新下面name值了
public class SpringCloudClientApplication {
	
	
	/**
	 * 读取远程配置文件
	 * 1.对应管理配置文件的服务，建文件的时候按规定命名格式命名文件
	 * 2.需要远程访问的配置要写在配置文件bootstrap中，不然不生效（详细配置见文件）
	 * 3.按目前的配置，当远程修改远程文件时，读取的值并不会动态更新，这个应该有办法实现，等往后看
	 * 
	 * 
	 * 4.远程配置项目见：SpringCloud-config
	 * 
	 * 
	 * 5.动态更新需要再导入包，见pem文件注释
	 * 6.如果要刷新值先要发请求   http://localhost：端口/refesh提起刷新，然后值才会变（必须是post请求，如果要使其自动刷新需要先配置git）
	 * 
	 * 7.因为springboot支持用class文件方式写配置文件，那么就可以用下面注解的方式获取配置值进行配置
	 * 
	 * @param args
	 */
	

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudClientApplication.class, args);
	}

	@Value("${name}")
	String name;
	@RequestMapping(value = "/hello")
	public String hello() {
		return name;
	}
}
