package com.test.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class Test6Listener implements TaskListener{


	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		
		delegateTask.addCandidateUser("c1");
		delegateTask.addCandidateUser("c2");
		delegateTask.addCandidateUser("c3");
	}

}
