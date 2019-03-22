package com.test.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.springboot.vo.UserTestVo;


/**
 * dao层使用配置文件的方式
 * @author dell
 *
 */
@Mapper
@Repository("testmap")
public interface TestUserMapper extends BaseMapper<UserTestVo> {
	
	public UserTestVo getUserTestVoById(String id);

	public int testCaller();
	
}	

