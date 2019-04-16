package com.test.activiti.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
@Data
@TableName("ags_user")
public class UserInfoVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	private String id ;
	
	private String name;
	
	private String origin;
	
	private String address;
	
	private String birth;
	
	private String age;

	private String state;
}
