package com.test.springboot.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
public class ExceptionAop {
	
	//@ExceptionHandler(Throwable.class)
	//@ResponseBody
	public String  exceptionText(Throwable e) {
		return "异常测试:e:"+e.getMessage();
	}
	

}
