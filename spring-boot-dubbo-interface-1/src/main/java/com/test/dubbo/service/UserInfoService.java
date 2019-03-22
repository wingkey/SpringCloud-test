package com.test.dubbo.service;

import java.util.Map;

import com.test.dubbo.vo.UserinfoVo;

public interface UserInfoService {

	public String getUserByPage(Integer rows,Integer page,String name);
	
	public UserinfoVo selectById(String id);
	
	public Integer selectCountByMap(Map<String, Object>map);
	
	public String updateUser(UserinfoVo vo);
	
	public String insertUser(UserinfoVo vo);
	
	public String deleteById(String id);
	
	public String bathDeleteById(String ids);
}
