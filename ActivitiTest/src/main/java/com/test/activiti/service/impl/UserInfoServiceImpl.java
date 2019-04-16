package com.test.activiti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.activiti.dao.UserInfoDao;
import com.test.activiti.service.UserInfoService;
import com.test.activiti.vo.UserInfoVo;

public class UserInfoServiceImpl implements  UserInfoService{

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public Map<String, Object> selectByGroupidInPage(String groupid, Integer rows, Integer page) {
		EntityWrapper<UserInfoVo> wrapper=new EntityWrapper<UserInfoVo>();
		wrapper.where("where id in(select userid from  age_user_group where groupid ={0} )", groupid);
		wrapper.orderBy("id");
		
		//获取分页数据
		List<UserInfoVo>list=userInfoDao.selectPage(new Page<UserInfoVo>(page, rows), wrapper);
		
		//获取总条数
		int count=userInfoDao.selectCount(wrapper);
		
		Map<String, Object>map=new HashMap<>();
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public List<UserInfoVo> selectByGroupid(String groupid) {
		EntityWrapper<UserInfoVo> wrapper=new EntityWrapper<UserInfoVo>();
		wrapper.where(" id in(select userid from  age_user_group where groupid ={0} )", groupid);
		wrapper.orderBy("id");
		return userInfoDao.selectList(wrapper);
	}

}
