package com.test.springcloud.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 自身作为消费者远程调用生产者的方法实例
 * @author dell
 *
 */

@FeignClient(name= "spring-boot-demo")
public interface TestRemote {
	
	@GetMapping("/freemarker/login")
	public String testRemote();

}
