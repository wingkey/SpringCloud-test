package com.test.springboot.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.test.springboot.service.TestUserService;
import com.test.springboot.service.UserTestService;
import com.test.springboot.service.UserinfoService;
import com.test.springboot.vo.UserTestVo;

/**
 * 
 * @author dell
 *	
 *
 *@Controller     返回界面
 *@RestController  	返回json 即数据
 *
 *@EnableAutoConfiguration(可以用来启动项目，搭配main方法  一般不用)
 */

@RestController
@EnableAutoConfiguration
public class HellowController {
	
	@Autowired
	@Qualifier("userinfoService")
	private UserinfoService userinfoService;
	
	@Autowired
	@Qualifier("userservice")
	private UserTestService userservice;
	
	@Autowired
	@Qualifier("testservice")
	private TestUserService testservice;
	
	
	@RequestMapping("/hellow")
	public String hellow () {
		return "这是测试！";
	}
	
	
	@RequestMapping("/insert")
	public String insert(String name,String code,String age) {
		UserTestVo vo=new UserTestVo();
		vo.setId(UUID.randomUUID().toString().replaceAll("-","").toLowerCase());
		vo.setAge(age);
		vo.setCode(code);
		vo.setName(name);
		return userservice.insertUser(vo)+"";
	}
	
	@RequestMapping("/delete")
	public String delete(String id, String name,String code,String age) {
		Map<String, Object> map=new HashMap<String, Object>();
		UserTestVo vo=new UserTestVo();
		vo.setCode(code);
		vo.setAge(age);
		vo.setName(name);
		vo.setId(id);
		map.put("userTestVo", vo);
		return userservice.deleteByMap(map)+"";
	}
	
	@RequestMapping("/get")
	public UserTestVo get(String id) {
		return testservice.getUserTestVoById(id);
	}
	
	/**
	 * 测试事务
	 */
	@RequestMapping("/transfor")
	public void insertBytransfor() {
		List<UserTestVo>list=new ArrayList<UserTestVo>();
		UserTestVo vo1=new UserTestVo();
		UserTestVo vo2=new UserTestVo();
		vo1.setId(UUID.randomUUID()+"".replace("-", "").toLowerCase());
		vo1.setAge("10");
		vo1.setCode("001");
		vo1.setName("saaa");
		
		vo2.setId(UUID.randomUUID()+"".replace("-", "").toLowerCase());
		vo2.setAge("11");
		vo2.setCode("100");
		//vo2.setName("fyedugaa");//因为数据库设置不能为空，所以给空会回滚
		list.add(vo1);
		list.add(vo2);
		testservice.insertByTransfor(list);
	}
	
	
	@SuppressWarnings({ "null" })
	@RequestMapping("/except")
	public String except () {
		
		@SuppressWarnings("rawtypes")
		List a=null;
		
		System.out.println(a.size());
		return "这是测试！";
	}
	
	
	@RequestMapping("/getUser")
	public String getUser(String id) {
		return userinfoService.getUserinfoById(id).toString();
	}
	
	
	@RequestMapping("/MP")
	public String testMpGet() {
		return testservice.testToMPGet()+"";
	}
	
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(HellowController.class, args);
	}

}
