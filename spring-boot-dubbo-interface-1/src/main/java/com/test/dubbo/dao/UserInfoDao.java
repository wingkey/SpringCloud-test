package com.test.dubbo.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.dubbo.vo.UserinfoVo;

@Mapper
public interface UserInfoDao extends BaseMapper<UserinfoVo>{
	
	public List<UserinfoVo> getUserByPage(Integer rows, Integer page, String name);
	
	public Integer countUser(String name);
	
}
