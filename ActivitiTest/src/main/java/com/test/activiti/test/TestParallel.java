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
/*
 并行网关：即该网关下多个节点都会启动
 
 
 1）一个流程中流程实例只有1个，执行对象有多个
2）并行网关的功能是基于进入和外出的顺序流的：
分支(fork)： 并行后的所有外出顺序流，为每个顺序流都创建一个并发分支。
汇聚(join)： 所有到达并行网关，在此等待的进入分支， 直到所有进入顺序流的分支都到达以后， 流程就会通过汇聚网关。
3）并行网关的进入和外出都是使用相同节点标识
4）如果同一个并行网关有多个进入和多个外出顺序流， 它就同时具有分支和汇聚功能。 这时，网关会先汇聚所有进入的顺序流，然后再切分成多个并行分支。
5）并行网关不会解析条件。 即使顺序流中定义了条件，也会被忽略。
并行网关不需要是“平衡的”（比如， 对应并行网关的进入和外出节点数目不一定相等）。
 
 
 */
@Slf4j
public class TestParallel {
	
	private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	// 创建流程
	public void deploy() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		Deployment deployment =  ProcessEngines.getDefaultProcessEngine().getRepositoryService()// 流程定义部署相关service
				.createDeployment().addClasspathResource("processes/test3.0.bpmn")// 传入文件
				.addClasspathResource("processes/test3.0.png").deploy();// 返回部署对象
		log.info("to test   define  success!");
	}

	@Test
	// 启动
	public void startProcess() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey("test3.0");
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
	
	
	@Test
	//完成任务
	public void completeTask() {
		String taskid="147504";
		Map<String, Object>variables=new HashMap<>();
		variables.put("money", 50000);
		engine.getTaskService().complete(taskid, variables);
		log.debug("finish");
	}
	
	

}
