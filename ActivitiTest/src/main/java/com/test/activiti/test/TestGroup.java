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
 
 组任务及三种分配方式：
    1：在taskProcess.bpmn中直接写 candidate-users=“小A,小B,小C,小D"
    2：在taskProcess.bpmn中写 candidate-users =“#{userIDs}”，变量的值要是String的。
         使用流程变量指定办理人
              Map<String, Object> variables = new HashMap<String, Object>();
              variables.put("userIDs", "大大,小小,中中");
    3，使用TaskListener接口，使用类实现该接口，在类中定义：
            //添加组任务的用户
delegateTask.addCandidateUser(userId1);
delegateTask.addCandidateUser(userId2);
组任务分配给个人任务（认领任务）：
     processEngine.getTaskService().claim(taskId, userId);
个人任务分配给组任务：
     processEngine.getTaskService(). setAssignee(taskId, null);
向组任务添加人员：
     processEngine.getTaskService().addCandidateUser(taskId, userId);
向组任务删除人员：
     processEngine.getTaskService().deleteCandidateUser(taskId, userId);
个人任务和组任务存放办理人对应的表：
act_ru_identitylink表存放任务的办理人，包括个人任务和组任务，表示正在执行的任务
act_hi_identitylink表存放任务的办理人，包括个人任务和组任务，表示历史任务
区别在于：如果是个人任务TYPE的类型表示participant（参与者）
		 如果是组任务TYPE的类型表示candidate（候选者）和participant（参与者）
  
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
