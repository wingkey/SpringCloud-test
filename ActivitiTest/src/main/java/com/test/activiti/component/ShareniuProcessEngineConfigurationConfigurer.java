package com.test.activiti.component;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 解决乱码问题（实际字符编码集不设置错没这问题）
 * @author dell
 *
 */

@Component
public class ShareniuProcessEngineConfigurationConfigurer implements ProcessEngineConfigurationConfigurer {
	@Override
	public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
		processEngineConfiguration.setActivityFontName("宋体");
		processEngineConfiguration.setLabelFontName("宋体");
		processEngineConfiguration.setAnnotationFontName("宋体");
		System.out.println("ShareniuProcessEngineConfigurationConfigurer#############");
		System.out.println(processEngineConfiguration.getActivityFontName());
	}


}
