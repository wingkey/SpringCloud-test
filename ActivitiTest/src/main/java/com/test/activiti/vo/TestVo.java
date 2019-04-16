package com.test.activiti.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestVo  implements  Serializable{

	
	private static final long serialVersionUID = 1L;

	
	private String id;
	
	private String title;
	
	private String content;
	
	private String result;
	
	
}
