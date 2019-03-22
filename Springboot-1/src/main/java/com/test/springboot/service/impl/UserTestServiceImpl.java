package com.test.springboot.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.test.springboot.mapper.UserTestMapper;
import com.test.springboot.service.UserTestService;
import com.test.springboot.vo.UserTestVo;


@Service("userservice")
public class UserTestServiceImpl implements UserTestService{

	@Autowired
	@Qualifier("usermap")
	private UserTestMapper usermap;
	
	
	@Override
	public int insertUser(UserTestVo vo) {
		return usermap.insertUser(vo);
	}

	@Override
	public int deleteByMap(@SuppressWarnings("rawtypes") Map map) {
		return usermap.deleteByMap(map);
	}

}
