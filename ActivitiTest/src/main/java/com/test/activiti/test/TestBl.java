package com.test.activiti.test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import com.test.activiti.vo.TestVo;

import lombok.extern.slf4j.Slf4j;
/**
 流程图变量 
 分两种
  二者区别
		 setVariable  全局性的 ，即在这个流程内，只要设置过，在哪个节点都能获取到这个值
		 setVariableLocal   局部的 ，只在当前节点生效，到下个节点就查询不到了
		 
		 当然，二者的值都可以放实体类 ，只是要求实体类要序列化（内部有处理）
		 
		 other:
		 
		 1）RuntimeService对象可以设置流程变量和获取流程变量
		 2）TaskService对象可以设置流程变量和获取流程变量
		 3）流程实例启动的时候可以设置流程变量
		 4）任务办理完成的时候可以设置流程变量
		 5）流程变量可以通过名称/值的形式设置单个流程变量
		 6）流程变量可以通过Map集合，同时设置多个流程变量
		 Map集合的key表示流程变量的名称
		 Map集合的value表示流程变量的值
 
 
 */

@Slf4j
public class TestBl {
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

	
	//----------------------------
	@Test
	/**
	 * 设置流程变量
	 */
	public void setBl() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		TaskService service=engine.getTaskService();
		Task task=service.createTaskQuery().taskAssignee("master").taskId("47502").singleResult();
		
		//放变量 ,两个都是覆盖式的，即根据给的name,如果之前放过同名的，则覆盖  
		
		
		/**
		 二者区别
		 setVariable  全局性的 ，即在这个流程内，只要设置过，在哪个节点都能获取到这个值
		 setVariableLocal   局部的 ，只在当前节点生效，到下个节点就查询不到了
		 
		 当然，二者的值都可以放实体类 ，只是要求实体类要序列化（内部有处理）
		 
		 other:
		 
		 1）RuntimeService对象可以设置流程变量和获取流程变量
		 2）TaskService对象可以设置流程变量和获取流程变量
		 3）流程实例启动的时候可以设置流程变量
		 4）任务办理完成的时候可以设置流程变量
		 5）流程变量可以通过名称/值的形式设置单个流程变量
		 6）流程变量可以通过Map集合，同时设置多个流程变量
		 Map集合的key表示流程变量的名称
		 Map集合的value表示流程变量的值
		 */
		
		service.setVariable(task.getId(), "请假", "请假六天");
		service.setVariable(task.getId(),"请假", "请假七天");
		service.setVariable(task.getId(),"aaa", "aaa");
		service.setVariableLocal(task.getId(),"aaa", "aaa");
		service.setVariableLocal(task.getId(),"aaa", "sss");
		service.setVariableLocal(task.getId(), "test", "test");
		
		TestVo vo=new TestVo();
		vo.setId("aaa");
		vo.setTitle("请假");
		vo.setContent("请假七天");
		service.setVariable(task.getId(), "all", vo);
	}
	
	@Test
	//获取流程变量
	public void getBl() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		TaskService service=engine.getTaskService();
		Task task=service.createTaskQuery().taskAssignee("master").taskId("47502").singleResult();
		log.debug(task.toString());
		String  val= (String) service.getVariable(task.getId(), "请假");
		String  val1=(String) service.getVariableLocal(task.getId(), "aaa");
		TestVo vo=(TestVo) service.getVariable(task.getId(), "all");
		
		log.debug(val);
		log.debug(val1);
		log.debug(vo.toString());
	}
	
	/**
	 * 查询历史流程变量
	 */
	@Test
	public void history() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		List<HistoricVariableInstance> list=engine.getHistoryService()
				.createHistoricVariableInstanceQuery().variableName("aaa").list();
		log.debug(list.toString());
		
	}
	
	//-------------------------------
	
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
		String taskid = "42504";
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
		String id = "27508";// ACT_RE_PROCDEF中EPLOYMENT_ID_
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
