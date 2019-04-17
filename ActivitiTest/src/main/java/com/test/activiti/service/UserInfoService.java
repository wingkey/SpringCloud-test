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
	
	
	/**
	 * 根据id更新哦用户数据
	 * @param vo
	 * @return
	 */
	public int updateUser(UserInfoVo vo);
	
	/**
	 * 将传入对象转成map后再查，对象属性为null则不作为条件
	 * @param vo
	 * @return
	 */
	public List<UserInfoVo> select(UserInfoVo vo);
}
