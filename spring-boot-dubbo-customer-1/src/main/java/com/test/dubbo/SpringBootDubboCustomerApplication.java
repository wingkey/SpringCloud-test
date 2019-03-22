package com.test.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@EnableDubbo
@SpringBootApplication
public class SpringBootDubboCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboCustomerApplication.class, args);
	}

}
