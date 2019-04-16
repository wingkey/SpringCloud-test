package com.test.activiti.listener;

import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;



public class Test6StartListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		Map<String, Object>map=new HashMap<>();
		map.put("group", "b1,b2,b3");
		execution.setVariables(map);
	}

}
