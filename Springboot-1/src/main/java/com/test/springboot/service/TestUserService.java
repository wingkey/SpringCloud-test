package com.test.springboot.service;

import java.util.List;

import com.test.springboot.vo.UserTestVo;

public interface TestUserService {

	public UserTestVo getUserTestVoById(String id);

	
	public int insertByTransfor(List<UserTestVo> list) ;
	
	/**
	 * 测试mybatis-plus是否配置成功
	 * @return
	 */
	public int testToMPGet();
}
