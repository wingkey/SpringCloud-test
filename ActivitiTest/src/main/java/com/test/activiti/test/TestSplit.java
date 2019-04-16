package com.test.activiti.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
/**
 * 这里测试的是普通连线处理 ，即到一个节点后出现两条线进行判定走哪边，直接设置线上的值及流程变量
 * @author dell
 *
 */
@Slf4j
public class TestSplit {
	
	private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

	

	@Test
	// 创建流程
	public void deploy() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		Deployment deployment = engine.getRepositoryService()// 流程定义部署相关service
				.createDeployment().name("to test").addClasspathResource("processes/test2.bpmn")// 传入文件
				.addClasspathResource("processes/test2.png").deploy();// 返回部署对象
		log.info("to test   define  success!");
	}

	@Test
	// 启动
	public void startProcess() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey("test2");
		log.info("to test start success ,the id is " + instance.getId() + " the process is "
				+ instance.getProcessDefinitionId());
	}
	@Test
	//查个人任务
	public void find() {
		String useid="test";
		List<Task> list=engine.getTaskService().createTaskQuery().list();
		log.debug(list.toString());
	
	}
	
	
	
	/**
	  这里处理的是连线的判定条件，当变量中message=0时，算重要的走审核一，详细见bpmn中连线配置
	 */
	@Test
	//完成
	public void  complete() {
		String taskid="115002";
		Map<String, Object>variables=new HashMap<>();
		variables.put("message", "0");
		engine.getTaskService().complete(taskid, variables);
		log.debug("finish");
	}
	
	
	
	
	
}
