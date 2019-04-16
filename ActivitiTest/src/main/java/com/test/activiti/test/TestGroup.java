package com.test.activiti.test;


import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 
 组任务：即一个任务分配给一个组的人去做，这里图省事还是用并行节点一次测试所有方法
三种方式：1 .直接指定  直接在流程图节点里给Candidate  users给赋值  格式  xx,xx,xx,xx   对应流程图节点group1
         2 使用流程变量       直接在流程图节点里给Candidate  users给变量            对应流程图节点group2
   3.使用监听器赋值任务人  直接使用监听器赋值       对应流程图节点group3
  
  注意：组任务和个人任务是不一样的（个人任务就是直接给assignee赋值）二者按任务人去查节点的方式是不同的
  */
@Slf4j
public class TestGroup {
private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

	

	@Test
	// 创建流程
	public void deploy() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		@SuppressWarnings("unused")
		Deployment deployment = engine.getRepositoryService()// 流程定义部署相关service
				.createDeployment().name("to test").addClasspathResource("processes/test6.bpmn")// 传入文件
				.addClasspathResource("processes/test6.png").deploy();// 返回部署对象
		log.info("to test   define  success!");
	}

	@Test
	// 启动
	public void startProcess() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey("test6");
		log.info("to test start success ,the id is " + instance.getId() + " the process is "
				+ instance.getProcessDefinitionId());
	}
	
	@Test
	//以组形式查询任务
	public void find() {
		
		List<Task> list4=engine.getTaskService().createTaskQuery().list();
		log.debug(list4.toString());
		for (Task taska : list4) {
			log.debug(taska.toString());
			List<IdentityLink> list = engine.getTaskService()//
	                .getIdentityLinksForTask(taska.getId());
			for (IdentityLink task : list) {
				
				log.debug(task.getTaskId()+"||"+task.getUserId());
				
			}
		}
	}
		
	
		@Test
		//以个人形式查询任务,查不到，只能以组的形式去查任务
		public void find1() {
			
			List<Task> list4=engine.getTaskService().createTaskQuery().taskAssignee("a1").list();
			for (Task task : list4) {
				log.debug(task.getAssignee());
				log.debug(task.getName());
				log.debug(task.getId());
			}
		
		
		
		}
	
	
	
	
	@Test
	//完成
	public void  complete() {
		String taskid="195025";
		
		engine.getTaskService().complete(taskid);
		log.debug("finish");
	}
	
	

}
