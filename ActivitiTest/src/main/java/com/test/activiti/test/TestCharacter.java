package com.test.activiti.test;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 
 角色组：
 即写流程的时候给上角色组，当然前提是要已经建组了，没建组的话那么就建个组，再对组成员分配，
 
 注意：这里奇坑，按角色组分配和按任务人及按组分配三种分配节点任务人的方式查询方式全都不一样，所以这里奇坑
 当要查某个人目前的任务时，也就是说要按三种方式轮流查一遍
 
 */
@Slf4j
public class TestCharacter {

private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();//启动引擎

private IdentityService identityService=engine.getIdentityService();

	@Test
	//创建，设置用户及组
	public void deploy() {
		
		Deployment deployment = engine.getRepositoryService()// 流程定义部署相关service
				.createDeployment().name("to test").addClasspathResource("processes/test7.bpmn")// 传入文件
				.addClasspathResource("processes/test7.png").deploy();// 返回部署对象
		
		/**在部署流程定义和启动流程实例的中间，设置组任务的办理人，向Activity表中存放组和用户的信息*/
		
		identityService.saveGroup(new GroupEntity("leader"));//建立组
		identityService.saveGroup(new GroupEntity("worker"));//建立组
		identityService.saveUser(new UserEntity("conner"));
		identityService.saveUser(new UserEntity("neko"));
		identityService.saveUser(new UserEntity("cloud"));
		identityService.createMembership("conner", "leader");//建立组和用户关系
		identityService.createMembership("neko", "leader");//建立组和用户关系
		identityService.createMembership("cloud", "worker");//建立组和用户关系
		
		//启动
		ProcessInstance instance =engine.getRuntimeService().startProcessInstanceByKey("test7");
		log.info("to test start success ,the id is " + instance.getId() + " the process is "
				+ instance.getProcessDefinitionId());
	}
	
	@Test
	public void start() {
		//启动
				ProcessInstance instance =engine.getRuntimeService().startProcessInstanceByKey("test7");
				log.info("to test start success ,the id is " + instance.getId() + " the process is "
						+ instance.getProcessDefinitionId());
		
	}
	

	@Test
	//以组形式查询任务
	public void find() {
		List<Task>res3=engine.getTaskService().createTaskQuery()
				.taskCandidateOrAssigned("neko").list();
		log.debug(res3.toString());
		
		/*List<Task>res2=engine.getTaskService().createTaskQuery()
				.taskCandidateGroup("leader").list();
		log.debug(res2.toString());*/
		/*List<Task> list4=engine.getTaskService().createTaskQuery().list();
		log.debug(list4.toString());
		for (Task taska : list4) {
			log.debug(taska.toString());
			List<IdentityLink> list = engine.getTaskService()//
	                .getIdentityLinksForTask(taska.getId());
			
			
			
			for (IdentityLink task : list) {
				List<User>users=identityService.createUserQuery().memberOfGroup(task.getGroupId()).list();
				log.debug(task.getTaskId()+"||"+task.getUserId()+"||" );
				for (User user : users) {
					log.debug(user.getId());
				}
				
			}
		}*/
	}
	
	/**
	 * 根据用户查相关的流程
	 */
	@Test
	public void find1() {
		String user="neko";//用户
		Group group=getUserGroup(user);//获取用户所在组
		List<Task>list=engine.getTaskService().createTaskQuery().taskCandidateGroup(group.getId()).list();
		log.debug(list.toString());
	}
	
	
	/**
	 * 获取用户所在组
	 * @param userid
	 * @return
	 */
	private Group getUserGroup(String userid) {
		return identityService.createGroupQuery().groupMember(userid).singleResult();
	}
	

	@Test
	//完成
	public void  complete() {
		String taskid="215004";
		
		engine.getTaskService().complete(taskid);
		log.debug("finish");
	}
}
