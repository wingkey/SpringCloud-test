package com.test.activiti.test.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;

import lombok.extern.slf4j.Slf4j;

/**
 * 封装流程图相关方法
 * 
 * @author dell
 *
 */
@Slf4j
public class ActivitiModel {

	private ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

	private IdentityService identityService = engine.getIdentityService();

	/**
	 * 创建流程 （即导入流程图文件）
	 * 
	 * @param name 自己定的流程名
	 * @param bpmn 画的流程图文件
	 * @param pic  对应生成的流程图图片
	 */
	public void deploy(String name, String bpmn, String pic) {

		@SuppressWarnings("unused")
		Deployment deployment = engine.getRepositoryService()// 流程定义部署相关service
				.createDeployment().name(name).addClasspathResource(bpmn)// 传入文件
				.addClasspathResource(pic).deploy();// 返回部署对象
		log.info(name + " deploy success! ");
	}

	/**
	 * 启动指定流程图
	 * 
	 * @param key 即对应的流程图文件的id
	 */
	public void startProcess(String key) {
		ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey(key);
		log.info(key + " start success ,the id is " + instance.getId() + " the process is "
				+ instance.getProcessDefinitionId());
	}

	/**
	 * 完成指定taskid的任务
	 * 
	 * @param taskid
	 */
	public void finishTask(String taskid) {

		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 结束
		engine.getTaskService().complete(taskid);
		log.info("taskid:" + taskid + " is end ");
	}

	/**
	 * 根据taskid删除流程 （不带级联，即如果流程启动则无法删除）
	 * 
	 * @param taskid
	 */
	public boolean deleteProcesByTask(String taskid) {
		String deid = engine.getTaskService().createTaskQuery().taskId(taskid).singleResult().getProcessDefinitionId();// 流程定义id

		// 下面查部署id
		ProcessDefinition processDefinition = engine.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(deid).singleResult();
		String deploymentId = processDefinition.getDeploymentId(); // 部署id

		try {
			engine.getRepositoryService().deleteDeployment(deploymentId);// 使用部署id
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 删除流程定义,带级联（即不管启动没启动，都删）
	 * 
	 * @param taskid
	 * @return
	 */
	public boolean deleteProcesByTaskInJIlian(String taskid) {
		try {
			String deid = engine.getTaskService().createTaskQuery().taskId(taskid).singleResult()
					.getProcessDefinitionId();// 流程定义id

			// 下面查部署id
			ProcessDefinition processDefinition = engine.getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(deid).singleResult();
			String deploymentId = processDefinition.getDeploymentId(); // 部署id

			engine.getRepositoryService().deleteDeployment(deploymentId, true);// 使用部署id,给上true彻底删除
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * 根据用户id查待办任务 (原本以为要分三种情况写，结果检查方法发现有方法一次查所有,但是根本没有一次传参多个进行查询的方法，也就是要进行多参数查询时，只能牺牲效率或者自己写sql)
	 * 
	 * @param userid
	 * @param sort   排序
	 * @return
	 */
	public List<Task> findTaskByUserid(String userid) {
		List<Task>res=engine.getTaskService()
				.createTaskQuery().taskCandidateOrAssigned(userid)
				.orderByTaskAssignee().list();

		return res;
	}
	
	/**
	 * 根据待执行节点id查执行任务人 （没找到相关方法，只找到分三种情况获取的方法）
	 * @param taskid
	 */
	public List<String> findUserByTaskId(String taskid) {
		
		List<String>userids=new ArrayList<>();
		
		//1.个人任务
		Task res=engine.getTaskService().createTaskQuery().taskId(taskid).singleResult();
		if (res!=null) {
			userids.add(res.getAssignee());
		}
		
		//2.以组任务
		List<IdentityLink> list = engine.getTaskService()//
                .getIdentityLinksForTask(taskid);
		for (IdentityLink task : list) {
			userids.add(task.getUserId());
		}
		
		//3.以角色组任务
		
		List<IdentityLink> links = engine.getTaskService()
                .getIdentityLinksForTask(taskid);
		
		List<User> users=identityService.createUserQuery().memberOfGroup(links.get(0).getGroupId()).list();
		for (User user : users) {
			userids.add(user.getId());
		}
		
		return userids;
	}

}
