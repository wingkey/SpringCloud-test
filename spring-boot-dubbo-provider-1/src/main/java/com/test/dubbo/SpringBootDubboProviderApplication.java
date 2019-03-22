package com.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@EnableDubbo  //开启基于注解的dubbo
@SpringBootApplication
public class SpringBootDubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboProviderApplication.class, args);
	}

}
