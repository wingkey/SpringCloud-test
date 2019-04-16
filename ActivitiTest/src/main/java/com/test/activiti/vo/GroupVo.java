package com.test.activiti.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

@Data
@TableName("ags_group")
public class GroupVo {

	@TableId(type = IdType.UUID)
	private String id;
	
	private String origanname;
	
	private String area;
	
	private String fid;
	
	private String state;
}
