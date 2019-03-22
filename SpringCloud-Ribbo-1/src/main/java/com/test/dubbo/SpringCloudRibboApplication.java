package com.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


/**
 * 配置负载均衡器   ribbon     
 * 1.多台机器配置同个服务并注册到注册中心，此时访问服务使用哪个机器的
 * 2.系统默认配置顺序为随机
 * 3.支持自定义配置顺序算法
 * @author dell
 *
 */

/**
 * 配置断路器 ---hystrix
 * @author dell
 * 1.当系统远程调用服务提供者请求时可能因网路等其他原因导致调用失败从而出现程序错误，断路器则是用于处理出现这种情况的工具
 * 2.需要启用类声明注释@EnableCircuitBreaker 表示项目启用断路器
 * 3.在远程调用服务的方法上使用注解@HystrixCommand(fallbackMethod = "回调方法名") 表示该方法使用hystrix进行处理，fallbackMethod表示当出现异常时回调的方法
 * 
 * 
 */



//@EnableDiscoveryClient//注册到注册中心
@EnableEurekaClient  //按道理用上面的，但这个注解本身就引入了上面的，所以无所谓
@SpringBootApplication

@EnableHystrix
@EnableCircuitBreaker //声明使用断路器
public class SpringCloudRibboApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudRibboApplication.class, args);
	}
	
	
}
