package com.test.activiti.service;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Task;

public interface ActivityDemoService {

	/**
	 * 启动流程
	 * @param bizId 业务id
	 */
	public void startProcesses(String bizId);
	
	/**
	 * 
	* <p>描述: 根据用户id查询待办任务列表</p>  
	* @author 范相如  
	* @date 2018年2月25日
	 */
	public List<Task> findTasksByUserId(String userId);
	
	
	/**
	 * 
	 * <p>描述:任务审批 	（通过/拒接） </p>  
	 * @author 范相如  
	 * @date 2018年2月25日  
	 * @param taskId 任务id
	 * @param userId 用户id
	 * @param result false OR true
	 */
	public void completeTask(String taskId,String userId,String result);


	/**
	 * 更改业务流程状态#{ActivityDemoServiceImpl.updateBizStatus(execution,"tj")}
	 * @param execution
	 * @param status
	 */
	public void updateBizStatus(DelegateExecution execution,String status);

	
	//流程节点权限用户列表${ActivityDemoServiceImpl.findUsers(execution,sign)}
	public List<String> findUsersForSL(DelegateExecution execution);
	
	//流程节点权限用户列表${ActivityDemoServiceImpl.findUsers(execution,sign)}
	public List<String> findUsersForSP(DelegateExecution execution);
	
	/**
	 * 
	* <p>描述:  生成流程图
	 * 首先启动流程，获取processInstanceId，替换即可生成</p>  
	* @author 范相如  
	* @date 2018年2月25日  
	* @param processInstanceId
	* @throws Exception
	 */
	public void queryProImg(String processInstanceId) throws Exception;

	/**
	 * 流程图高亮显示
	 * 首先启动流程，获取processInstanceId，替换即可生成
	 * @throws Exception
	 */
    public void queryProHighLighted(String processInstanceId) throws Exception;
    
    
	
}
