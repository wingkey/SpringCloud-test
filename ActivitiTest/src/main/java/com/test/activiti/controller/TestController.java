package com.test.activiti.controller;



import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * -- 注意：流程创建非常坑，这里下面指定bpmn流程文件时，系统自己有个默认文件夹processes,会默认扫描这个文件夹下面的文件，如果没有项目启动报错
 * 			如果在下面指定流程文件位置时使用的是classpath指定时，不加上这个目录访问项目报错，可以通过增加配置的方式把一开始的流程扫描去掉
 * 
 * --在application中加    spring.activiti.check-process-definitions=false即可跳过检查processes文件夹 ，然后这个文件想放哪就放哪
 * 
 * @author dell
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@RequestMapping("/test")
	@ResponseBody
	public String firstTest() {
		

		// 读取流程文件
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/test.bpmn").deploy();
		// 获取流程定义
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();
		// 启动流程定义，返回流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
		String processId = pi.getId();
		System.out.println("流程创建成功，当前流程实例ID：" + processId);

		Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第一次执行前，任务名称：" + task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("第二次执行前，任务名称：" + task.getName());
		taskService.complete(task.getId());

		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		System.out.println("task为null，任务执行完毕：" + task);

		return "just test";
	}

}
