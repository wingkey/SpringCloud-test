package com.test.dubbo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class CustomerService {

	@Autowired
	private RestTemplate restTemplate;
	

	public String doOtherStuff(String url) {
        String results = restTemplate.getForObject("http://SPRING-CLOUD-CONSUMER/"+url, String.class);//这里的地址指的是注册中心的服务名
        return results;
    }
	
	
	
	
	
	
}
