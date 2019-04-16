package com.test.activiti.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test1")
public class FirstTestController {

	@Test
	// 定义流程

	/**
	 * 这里对应数据库里面的表即是以re_*开头的（ACT_RE_MODEL，ACT_RE_PROCDEF，ACT_RE_DEPLOYMENT）
	 * 
	 * ACT_RE_DEPLOYMENT：部署对象表
	 * 
	 * ACT_RE_PROCDEF：流程定义表，关键对象ProcessDefinitionId，命名格式：bpmn中的id+版本+随机生成的数字
	 * 
	 * 
	 */
	public void deploy() {
		/**
		 * 获取流程引擎 这里所加载的引擎对象即是自己定义的activiti.cfg.xml 如果自己定义了多个引擎对象时可以用下面的方式指定定义的文件
		 * 
		 * //载入资源 ProcessEngineConfiguration processEngineConfiguration =
		 * ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		 * //创建引擎 ProcessEngine processEngine =
		 * processEngineConfiguration.buildProcessEngine();
		 * 
		 */
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		/**
		 * 加载自己写的流程图文件
		 * 
		 * 注意：这里有个比较坑的地方，当和springboot结合时，使用的包是springboot专用的，这个在项目启动时会默认检查流程文件是否在resource/processes文件夹下
		 * 不然就报错，如果自己的文件不是放在这个文件夹下，需要新增配置 在application中加
		 * spring.activiti.check-process-definitions=false即可跳过检查processes文件夹
		 * ，然后这个文件想放哪就放哪
		 * 
		 */
		Deployment deployment = engine.getRepositoryService()// 流程定义部署相关service
				.createDeployment()// 创建对象
				.name("test")// 设置流程名
				.addClasspathResource("processes/test.bpmn")// 传入文件
				.addClasspathResource("processes/test.png").deploy();// 返回部署对象

		// 以zip格式加载流程图，zip里放的就是bpmn和png文件，下面加载方式的流的相对路径就是resource下的
		/*
		 * Deployment deployment1 = engine.getRepositoryService()//流程定义部署相关service
		 * .createDeployment()//创建对象 .name("test")//设置流程名 .addZipInputStream(new
		 * ZipInputStream(this.getClass().getClassLoader().getResourceAsStream(
		 * "processes/test.zip"))) .deploy();//返回部署对象
		 */
		log.info("流程定义完毕完毕");
	}

	@Test
	// 启动流程
	public void startProcess() {
		// 获取引擎
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		/**
		 * 启动流程
		 * 
		 * ProcessInstance 即整个流程过程对象，getRuntimeService获取正在运行的服务实例，即上面方法定义的流程对象
		 * 这里开启的"myProcess"即是上面那个流程图文件以xml形式打开后的id，注意自己写的流程图文件id不能重复写完自己改
		 * 
		 */
		ProcessInstance pi = engine.getRuntimeService().startProcessInstanceByKey("myProcess");// 这里用key的优势是默认用最新版本的，即你写两个，用最新的
		log.info("流程创建成功，当前流程实例ID：" + pi.getId()); // 对应ACT_RU_EXECUTION中的id
		log.info("流程创建成功，当前流程定义ID：" + pi.getProcessDefinitionId()); // 对应ACT_RE_PROCDEF的id
	}

	@Test
	// 执行流程
	public void queryTask() {
		String initiator = "test";// 指定发起者,即在写流程图时指定的assignee
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		List<Task> tasks = engine.getTaskService().createTaskQuery() // 创建流程 这里直接理解为查询数据库就行了。下面所有方法全是查数据库
				.taskAssignee(initiator)// 指定流程办理人
				.list();
		log.info("---流程开始执行---");
		if (tasks != null) {
			for (Task task : tasks) {
				log.info("taskid:" + task.getId() + "  taskname:" + task.getName());
			}
		}
		log.info("---流程执行结束---");
	}

	
	@Test
	// 完成流程
	public void finishTask() {
		String taskId = "5104";// 即上面查出来的任务id
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 结束指定任务
		engine.getTaskService().complete(taskId);
		log.info("id:" + taskId + "  任务结束");
	}
	@Test
	// 删除流程定义
	/**
	 * 这里的删除方式是 不带级联的删除 即该流程没有启动，流程启动就抛异常
	 */
	public void deleteTask() {
		String id = "2601";// ACT_RE_PROCDEF中EPLOYMENT_ID_
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		engine.getRepositoryService().deleteDeployment(id);// 使用部署id
		log.info("删除" + id);
	}

	@Test
	// 删除流程定义
	/**
	 * 这里的删除方式是 带级联的删除 流程是否启动都能删掉
	 * 
	 */
	public void deleteTask1() {
		String id = "1";// ACT_RE_PROCDEF中EPLOYMENT_ID_
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		engine.getRepositoryService().deleteDeployment(id, true);// 使用部署id,给上true彻底删除
		log.info("删除" + id);
	}

	/**
	 * 查看流程图
	 */
	@Test
	public void getViewPic() {
		String id = "10101";// ACT_GE_BYTEARRAY中EPLOYMENT_ID_
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		// 将生成的文件图片放到文件夹下

		// 获取流程文件名,这里拿到的即是流程图文件，因为我们的就两个 （bpmn和png)直接找下解决
		List<String> name = engine.getRepositoryService().getDeploymentResourceNames(id);
		String picname = "";// 图片名称
		if (name != null) {
			for (String na : name) {
				if (na.contains(".png")) {
					picname = na;
				}
			}
		}
		// 获取图片输入流
		InputStream in = engine.getRepositoryService().getResourceAsStream(id, picname);
		// 将图片生成指定目录
		File file = new File("D:/a/" + picname);
		try {
			FileUtils.copyInputStreamToFile(in, file);// 将流复制到指定目录下
		} catch (IOException e) {
			log.error("err:" + e.getMessage());
		}

	}

	/**
	 * 获取所有最新版本流程
	 */
	@Test
	public void getNewestVer() {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		List<ProcessDefinition> list=engine.getRepositoryService()//获取所有流程定义
				.createProcessDefinitionQuery()
				.orderByProcessDefinitionVersion().asc().list();
		Map<String, Object>map=new HashMap<>();
		if (list!=null) {
			for (ProcessDefinition processDefinition : list) {
				map.put(processDefinition.getKey(), processDefinition);
			}
		}
		List<Object>pros=new ArrayList<Object>(map.values());
		for (Object obj : pros) {
			ProcessDefinition definition=(ProcessDefinition) obj;
			log.info("id: "+definition.getId());
			log.info("key:"+definition.getKey());
			log.info("name: "+definition.getName());
			log.info("resourceName: "+definition.getResourceName());
		}
	}
	
	/**
	 * 不使用id使用名字删流程定义
	 */
	public void deleteByKey() {
		String key="test";
		
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		List<ProcessDefinition> list=engine.getRepositoryService().createProcessDefinitionQuery()
			.processDefinitionKey(key).list();
		for (ProcessDefinition processDefinition : list) {//循环获取getDeploymentId删除所有同名的流程
			engine.getRepositoryService().deleteDeployment(processDefinition.getDeploymentId(), true);// 使用部署id,给上true彻底删除
		}
		
	}
	
	
	/**
	 * 根据用户id查询待办任务列表    这个是支持分页的啊
	 * 
	 */
    public void findTaskByUser() {
    	ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    	List<Task> res=engine.getTaskService().createTaskQuery().processDefinitionId("check").taskCandidateOrAssigned("id").orderByProcessDefinitionId().list();
    	log.debug(res.toString());
    }
	

}
