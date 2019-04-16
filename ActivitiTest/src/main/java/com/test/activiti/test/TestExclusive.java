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
  1)一个排他网关对应一个以上的顺序流
  2)由排他网关流出的顺序流都有个conditionExpression元素，在内部维护返回boolean类型的决策结果。
  3)决策网关只会返回一条结果。当流程执行到排他网关时，流程引擎会自动检索网关出口，从上到下检索如果发现第一条决策结果为true或者没有设置条件的(默认为成立)，则流出。
  4)如果没有任何一个出口符合条件，则抛出异常
  5)使用流程变量，设置连线的条件，并按照连线的条件执行工作流，如果没有条件符合的条件，则以默认的连线离开。
 
 这里的汇聚跟我理解的汇聚不一样，正如我自己写的一样到第二个网关时，一边结束，另一边才第一步，这里的这个网关只是保证任务不直接结束，让另一个继续跑而已，只有两边都结束才算结束任务
 
  和不使用排他网关的区别
  1.不使用时当检索不到时并不会抛出异常
  2.可以设置默认值
 */

@Slf4j
public class TestExclusive {
	
	private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	@Test
	// 创建流程
	public void deploy() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		Deployment deployment =  ProcessEngines.getDefaultProcessEngine().getRepositoryService()// 流程定义部署相关service
				.createDeployment().addClasspathResource("processes/test4.bpmn")// 传入文件
				.addClasspathResource("processes/test4.png").deploy();// 返回部署对象
		log.info("to test   define  success!");
	}

	@Test
	// 启动
	public void startProcess() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey("test4");
		log.info("to test start success ,the id is " + instance.getId() + " the process is "
				+ instance.getProcessDefinitionId());
	}
	
	@Test
	//查个人任务
	public void find() {
		
		List<Task> list=engine.getTaskService().createTaskQuery().list();
		log.debug(list.toString());
	}
	
	
	@Test
	//完成任务
	public void completeTask() {
		String taskid="162502";
		Map<String, Object>variables=new HashMap<>();
		variables.put("money", 50000);
		engine.getTaskService().complete(taskid, variables);
		log.debug("finish");
	}
	
	
	

}
