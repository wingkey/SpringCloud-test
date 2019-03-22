package com.test.dubbo.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dubbo.service.CustomerService;
import com.test.dubbo.utils.StringUtils;

/**
 * 
 * 测试配合页面
 * @author dell
 *
 */

@Controller
@RequestMapping("/testdubbo")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/getUserByPage")
	@ResponseBody
	public String getUserByPage(Integer rows, Integer page, String name) {
		return customerService.getUserByPage(rows, page, name);
	}
	
	@RequestMapping("/selectById")
	@ResponseBody
	public String selectById(String id) {
		return customerService.selectById(id);
	}
	
	//------------------------------------
	@RequestMapping("/index")
	public String index(ModelMap map) {
		return "freemarker/table";
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(String rows, String page, String name) throws Exception {
		if (!(StringUtils.checkNull(rows)||StringUtils.checkNull(page))) {
			throw new Exception("传入参数缺失，请检查");
		}
		return customerService.getUserByPage(Integer.parseInt(rows),Integer.parseInt(page), name);
	}
	
	//验证代号是否重复
	@RequestMapping("/validateCode")
	@ResponseBody
	public String validateCode(String code) {
		Integer count=customerService.validateCode(code);
		Map<String, Object> map = new HashMap<String, Object>();
		if (count >1) {
			map.put("valid", false);
		} else {
			map.put("valid", true);
		}
		JSONObject object = new JSONObject(map);
		return object.toString();
	}
	
	
	//保存编辑
	@RequestMapping("/editUser")
	@ResponseBody
	public String editUser(String id,String name,String code,String sex,String state) {
		return customerService.editUser(id, name, code, sex, state);
	}
	
	//新增
	@RequestMapping("/insertUser")
	@ResponseBody
	public String insertUser(String name,String code,String sex,String state) {
		return customerService.insertUser(name, code, sex, state);
	}
	
	//删除
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(String id) {
		return customerService.deleteById(id);
	}
	
	//批量删除
	@RequestMapping("/bathDeleteById")
	@ResponseBody
	public String bathDeleteById(String ids) {
		return customerService.bathDeleteById(ids);
	}
	
	
}
