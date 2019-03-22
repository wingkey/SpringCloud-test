package com.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;




/**
 * 目前仪表盘功能有bug，在springboot 1.4.x,1.5.x上，可视化界面并不能统计对应请求信息，2.0以上及1.3.X下面的版本都有对应解决方案，但目前我用的1.5.7没有，
 * 网上的方法试过都无法生效，就目前而言，hystrix断路器功能正常工作，就是对应仪表盘功能无法使用（因为这个鬼项目已经不更新了，估计这个bug是不会解决了，后面试下其他的断路器测试下）
 * @author dell
 *
 */
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class SpringCloudHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHystrixDashboardApplication.class, args);
	}
	
	
	
   

}
