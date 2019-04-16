package com.test.activiti.vo;

import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;

@Data
@TableName("age_user_group")
public class UserGroupVo {

	private String userid;
	
	private String groupid;
}
