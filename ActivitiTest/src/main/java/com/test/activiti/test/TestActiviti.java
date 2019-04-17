package com.test.activiti.test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestActiviti {

	@Test
	// 创建流程
	public void deploy() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

		Deployment deployment = engine.getRepositoryService()// 流程定义部署相关service
				.createDeployment().name("to test").addClasspathResource("processes/test1.bpmn")// 传入文件
				.addClasspathResource("processes/test1.png").deploy();// 返回部署对象
		log.info("to test   define  success!");
	}

	@Test
	// 启动
	public void startProcess() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey("myProcess1");
		log.info("to test start success ,the id is " + instance.getId() + " the process is "
				+ instance.getProcessDefinitionId());
	}

	@Test
	// 执行 这个其实就是个查询，丫的在上一步已经启动了
	public void queryTask() {
		String initiator = "master";// 指定发起者，这里即写在流程图里面节点的assignee 是谁
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		List<Task> list = engine.getTaskService().createTaskQuery()// .taskAssignee(initiator)
				.list();// 这里已经把所有和这个人有关的流程启动了
		log.info(list.toString());

	}

	@Test
	// 完成
	public void finishTask() {
		String taskid = "35002";
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 结束
		engine.getTaskService().complete(taskid);
		log.info("end ");
	}

	@Test
	// 删除流程定义
	/**
	 * 这里的删除方式是 带级联的删除 流程是否启动都能删掉
	 * 
	 */
	public void deleteTask1() {
		String id = "172503";
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		String deid=engine.getTaskService().createTaskQuery().taskId(id).singleResult().getProcessDefinitionId();//流程定义id
		 
		//下面查部署id
		ProcessDefinition processDefinition=engine.getRepositoryService().createProcessDefinitionQuery()
				 		.processDefinitionId(deid)
 						 .singleResult(); 
		 String deploymentId =  processDefinition.getDeploymentId();  //部署id
		
		engine.getRepositoryService().deleteDeployment(deploymentId, true);// 使用部署id,给上true彻底删除
		log.info("删除" + id);
	}

	@Test
	/**
	 * 根据用户id查询待办任务列表 这个是支持分页的啊
	 * 
	 */
	public void findTaskByUser() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		List<Task> res = engine.getTaskService().createTaskQuery().taskAssignee("test").orderByProcessDefinitionId()
				.asc().list();
		log.debug(res.toString());
	}
	
	
	/**
	 * 获取历史记录
	 */
	@Test
	public void  getHistory() {
		ProcessEngine engine=ProcessEngines.getDefaultProcessEngine();
		List<HistoricActivityInstance>list=engine.getHistoryService().createHistoricActivityInstanceQuery().list();
		for (HistoricActivityInstance instance : list) {
			log.debug("name:"+instance.getActivityName()+" start:"+instance.getStartTime() +" end:"+instance.getEndTime());
		}
	}
	

}
