package com.test.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration//表示加载配置文件的类
//指定配置的一些属性,其中的prefix表示前缀
@ConfigurationProperties(prefix = "com.test")
//指定所读取的配置文件的路径
@PropertySource(value = "classpath:test/filter.properties",encoding="UTF-8")
public class FilterTestConfig {
	
	private String filter;
	
	private String[] filters;

	

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String[] getFilters() {
		setFilters();
		return filters;
	}

	public void setFilters() {
		this.filters = filter.split(",");
	}
	
}
