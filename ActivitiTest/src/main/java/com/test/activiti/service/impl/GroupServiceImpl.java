package com.test.activiti.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.activiti.dao.GroupDao;
import com.test.activiti.service.GroupService;
import com.test.activiti.utils.Vo2MapUtils;
import com.test.activiti.vo.GroupVo;

public class GroupServiceImpl implements GroupService{
	
	@Autowired
	private GroupDao groupDao; 
	

	@Override
	public List<GroupVo> selectByGroupid(String userid) {
		EntityWrapper<GroupVo>wrapper=new EntityWrapper<GroupVo>();
		wrapper.where(" id in (select groupid from age_user_group where userid ={0} ) ", userid);
		return groupDao.selectList(wrapper);
	}


	@Override
	public int insertGroup(GroupVo groupVo) {
		return groupDao.insert(groupVo);
	}


	@Override
	public int updateGroup(GroupVo groupVo) {
		return groupDao.updateById(groupVo);
	}


	@Override
	public List<GroupVo> select(GroupVo groupVo) {
		return groupDao.selectByMap(Vo2MapUtils.vo2Map(groupVo, true));
	}

}
