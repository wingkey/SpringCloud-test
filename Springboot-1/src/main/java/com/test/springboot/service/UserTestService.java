package com.test.springboot.service;

import java.util.Map;

import com.test.springboot.vo.UserTestVo;

public interface UserTestService {
	
	public int insertUser(UserTestVo vo);
	
	public int deleteByMap(@SuppressWarnings("rawtypes") Map map);

}
