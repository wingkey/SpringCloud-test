package com.test.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.springboot.mapper.UserinfoMapper;
import com.test.springboot.service.UserinfoService;
import com.test.springboot.vo.UserinfoVo;


@Service("userinfoService")
public class UserinfoServiceImpl implements UserinfoService {

	@Autowired
	private UserinfoMapper  userinfoMapper;
	
	
	@Override
	public UserinfoVo getUserinfoById(String id) {
		return userinfoMapper.getUserinfoById(id);
	}

}
