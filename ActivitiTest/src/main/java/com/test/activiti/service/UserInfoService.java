package com.test.activiti.service;

import java.util.List;
import java.util.Map;

import com.test.activiti.vo.UserInfoVo;

public interface UserInfoService {
	
	
	/**
	 * 根据组织id找用户（带分页）
	 * @param groupid
	 * @param rows
	 * @param page
	 * @return
	 */
	public Map<String, Object> selectByGroupidInPage(String groupid,Integer rows, Integer page);

	/**
	 * 根据组织id找用户（不分页）
	 * @param groupid
	 * @return
	 */
	public List<UserInfoVo> selectByGroupid(String groupid);
	
	
}
