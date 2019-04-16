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
 测试指定节点任务人,这里图省事直接用并行节点直接三种方式一起测试了
 三种方式：1 .直接指定       
         2 使用流程变量
   3.使用监听器赋值任务人
   
   
   监听器说明
   	1）在类中使用delegateTask.setAssignee(assignee);的方式分配个人任务的办理人，此时张无忌是下一个任务的办理人
	2）通过processEngine.getTaskService().setAssignee(taskId, userId);将个人任务从一个人分配给另一个人，此时张无忌不再是下一个任务的办理人，而换成了周芷若
	3）在开发中，可以将每一个任务的办理人规定好，例如张三的领导是李四，李四的领导是王五，这样张三提交任务，就可以查询出张三的领导是李四，通过类的方式设置下一个任务的办理人
	       
 
 
 */

@Slf4j
public class TestUser {

private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

	

	@Test
	// 创建流程
	public void deploy() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		Deployment deployment = engine.getRepositoryService()// 流程定义部署相关service
				.createDeployment().name("to test").addClasspathResource("processes/test5.bpmn")// 传入文件
				.addClasspathResource("processes/test5.png").deploy();// 返回部署对象
		log.info("to test   define  success!");
	}

	@Test
	// 启动
	public void startProcess() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey("test5");
		log.info("to test start success ,the id is " + instance.getId() + " the process is "
				+ instance.getProcessDefinitionId());
	}
	@Test
	//查个人任务
	public void find() {
		List<Task> list=engine.getTaskService().createTaskQuery().list();
		//log.debug(list.toString());
		for (Task task : list) {
			log.debug("task[id:"+task.getId()+",name:"+task.getName()+",assignee:"+task.getAssignee()+"]");
		}
	}
	
	
	
	
	@Test
	//完成
	public void  complete() {
		String taskid="180013";
		Map<String, Object>variables=new HashMap<>();
		variables.put("user", "tasker");//因为任务二使用的是变量方式赋值，所以在这里先把值给了
		engine.getTaskService().complete(taskid, variables);
		log.debug("finish");
	}
	
	//任务人转移
	@Test
	public void changeAssagnee() {
		engine.getTaskService().setAssignee("", "username");//参数任务id，任务人名
	}
	
	
}
