package com.test.activiti.service;

import java.util.List;

import com.test.activiti.vo.GroupVo;



public interface GroupService {
	
	/**
	 * 根据用户id找组织
	 * @param userid
	 * @return
	 */
	public List<GroupVo> selectByGroupid(String userid);

	
	
	/**
	 * 增加组织
	 * @param groupVo
	 * @return
	 */
	public int insertGroup(GroupVo groupVo);
	
	/**
	 * 更新
	 * @param groupVo
	 * @return
	 */
	public int updateGroup(GroupVo groupVo);
	
	/**
	 * 查询：根据传入的对象属性看是否为空进行判定查询条件
	 * @param groupVo
	 * @return
	 */
	public List<GroupVo> select(GroupVo groupVo);
	
}
