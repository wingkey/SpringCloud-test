package com.test.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration//表示加载配置文件的类
//指定配置的一些属性,其中的prefix表示前缀
@ConfigurationProperties(prefix = "com.test")
//指定所读取的配置文件的路径
@PropertySource(value = "classpath:test/testconfig.properties",encoding="UTF-8")
public class PropertiesConfig {

	private String name;
	
	private String sex;
	
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	


}
