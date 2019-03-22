package com.test.dubbo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.test.dubbo.service.CustomerService;
import com.test.dubbo.service.UserInfoService;
import com.test.dubbo.utils.StringUtils;
import com.test.dubbo.vo.UserinfoVo;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Reference(version = "*")
	private UserInfoService userInfoService;
	
	@Override
	public String getUserByPage(Integer rows, Integer page, String name) {
		return userInfoService.getUserByPage(rows, page, name);
	}

	@Override
	public String selectById(String id) {
		
		return userInfoService.selectById(id).toString();
	}

	@Override
	public Integer validateCode(String code) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("usercode", code);
		return userInfoService.selectCountByMap(map);
	}

	@Override
	public String editUser(String id, String name, String code, String sex,String state) {
		UserinfoVo vo=new UserinfoVo();
		vo.setId(id);
		vo.setUsername(name);
		vo.setUsercode(code);
		vo.setUserstate(state);
		vo.setUsersex(sex);
		
		String count="0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			count=userInfoService.updateUser(vo);
		} catch (Exception e) {
			map.put("success", 1);
			map.put("msg", "更新失败，e:" + e.getMessage());
			return new JSONObject(map).toString();
		}
		
		map.put("success", 0);
		map.put("msg", "成功更新" + count + "条数据。");
		return new JSONObject(map).toString();
	}

	@Override
	public String insertUser(String name, String code, String sex, String state) {
		
		UserinfoVo vo=new UserinfoVo();
		vo.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		vo.setUsername(name);
		vo.setUsercode(code);
		vo.setUserstate(state);
		vo.setUsersex(sex);

		String count="0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			count=userInfoService.insertUser(vo);
		} catch (Exception e) {
			map.put("success", 1);
			map.put("msg", "新增失败，e:" + e.getMessage());
			return new JSONObject(map).toString();
		}
		map.put("success", 0);
		map.put("msg", "成功新增" + count + "条数据。");
		return new JSONObject(map).toString();
	}

	@Override
	public String deleteById(String id) {
		String count="0";
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.checkNull(id)) {
			map.put("success", 1);
			map.put("msg", "传入参数缺失，请检查");
			return new JSONObject(map).toString();
		}
		
		try {
			count=userInfoService.deleteById(id);
		} catch (Exception e) {
			map.put("success", 1);
			map.put("msg", "新增失败，e:" + e.getMessage());
			return new JSONObject(map).toString();
		}
		
		map.put("success", 0);
		map.put("msg", "成功删除" + count + "条数据。");
		return new JSONObject(map).toString();
	}

	@Override
	public String bathDeleteById(String ids) {
		String count="0";
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.checkNull(ids)) {
			map.put("success", 1);
			map.put("msg", "传入参数缺失，请检查");
			return new JSONObject(map).toString();
		}
		
		try {
			count=userInfoService.bathDeleteById(ids);
		} catch (Exception e) {
			map.put("success", 1);
			map.put("msg", "批量删除失败，e:" + e.getMessage());
			return new JSONObject(map).toString();
		}
		map.put("success", 0);
		map.put("msg", "成功删除" + count + "条数据。");
		return new JSONObject(map).toString();
	}

}
