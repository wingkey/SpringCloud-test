package com.test.dubbo.service.impl;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.dubbo.dao.UserInfoDao;

import com.test.dubbo.service.UserInfoService;
import com.test.dubbo.utils.StringUtils;
import com.test.dubbo.vo.UserinfoVo;

@Service(version="0.1.0",timeout=5000)//该注解用于暴露该服务  (超时，版本号等都可以配置)
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoDao userInfoDao;
	

	@Override
	public String getUserByPage(Integer rows, Integer page, String name) {
		EntityWrapper<UserinfoVo> wrapper=new EntityWrapper<UserinfoVo>();
		
		if (StringUtils.checkNull(name)) {
			wrapper.like("name", name);
		}
		
		Integer count=userInfoDao.selectCount(wrapper);//先查数量
		
		wrapper.orderBy("id", true);
		
		List<UserinfoVo> list= userInfoDao.selectPage(new Page<>(page, rows), wrapper);//再加排序和分页
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", count);
		JSONObject object = new JSONObject(map);
		return object.toString();
	}



	@Override
	public UserinfoVo selectById(String id) {
		
		return userInfoDao.selectById(id);
	}



	@Override
	public Integer selectCountByMap(Map<String, Object> map) {
		EntityWrapper<UserinfoVo> wrapper=new EntityWrapper<UserinfoVo>();
		
		for (Map.Entry<String, Object> m : map.entrySet()) {
			wrapper.eq(m.getKey(), m.getValue());
			
		}
		return userInfoDao.selectCount(wrapper);
	}



	@Override
	public String updateUser(UserinfoVo vo) {
		return userInfoDao.updateById(vo)+"";
	}



	@Override
	public String insertUser(UserinfoVo vo) {
		return userInfoDao.insert(vo)+"";
	}



	@Override
	public String deleteById(String id) {
		return userInfoDao.deleteById(id)+"";
	}



	@Override
	public String bathDeleteById(String ids) {
		return userInfoDao.deleteBatchIds(Arrays.asList(ids.split(",")))+"";
	}

	
}
