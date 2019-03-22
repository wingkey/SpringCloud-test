package com.test.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.dubbo.service.CustomerService;

@RestController
@RequestMapping("/testRibbo")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@HystrixCommand(fallbackMethod = "serviceFailure")   //这里有问题，加上断路器配置后不会去调服务端代码，直接报错进回调方法里了
	@RequestMapping("/test")
	@ResponseBody
	public String  testRibbo(String url) {
		return customerService.doOtherStuff(url);
	}
	
	/**
	 * 断路器触发后调用的回调方法
	 * @return
	 */
	public String serviceFailure(String url) {
		return "test hystrixcommand";
	}	
	
}
