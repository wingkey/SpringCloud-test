package com.test.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.springboot.mapper.TestUserMapper;
import com.test.springboot.mapper.UserTestMapper;
import com.test.springboot.service.TestUserService;
import com.test.springboot.vo.UserTestVo;

@Service("testservice")
public class TestUserServiceImpl implements TestUserService{

	@Autowired
	@Qualifier("testmap")
	private TestUserMapper mapper;
	
	@Autowired
	@Qualifier("usermap")
	private UserTestMapper usermapper;
	
	@Override
	public UserTestVo getUserTestVoById(String id) {
		return mapper.getUserTestVoById(id);
	}

	/**
	 * 测试事务
	 */
	@Override
	@Transactional//注解事务
	public int insertByTransfor(List<UserTestVo> list) {
		for (UserTestVo userTestVo : list) {
			usermapper.insertUser(userTestVo);
		}
		return 0;
	}

	@Override
	public int testToMPGet() {
	 	UserTestVo a=mapper.selectById("1");
		return 0;
	}

}
