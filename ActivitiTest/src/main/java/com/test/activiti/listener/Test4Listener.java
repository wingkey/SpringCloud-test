package com.test.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 
 * 流程监听器，当流程配置了这个监听器时，执行到此处就会执行此监听器
 *
 */
public class Test4Listener implements  TaskListener{

	
	private static final long serialVersionUID = 1L;

	/**
	 * 该监听器可修改配置流程多项参数，比如流程变量，节点任务人等等，具体方法及修改内容很多，详见类属性
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		
		delegateTask.setAssignee("listener");//指定任务分配人
	}

}
