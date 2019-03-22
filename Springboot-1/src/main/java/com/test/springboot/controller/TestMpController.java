package com.test.springboot.controller;


import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.test.springboot.mapper.TestUserMapper;
import com.test.springboot.vo.UserTestVo;

/**
 * 使用mybatis-plus查询数据
 * 1.自带的方法基本能满足需求
 * 2.查询分页见下面的例子
 * 3.EntityWrapper作用类似于一个sql语句编辑器，里面所有对应的sql语句连接符（比如 or and ）真的就只是连接符
 * 里面传什么参数都没效，如果要传参，这么传（.or().eq("id","1")对应sql:or id="1"）
 * 方法里面所有数学运算方法（eq,like,lt,gt等）全部对应对应的数学方法（比如le("age","15") 就是 age<=15）
 * @author dell
 *
 */
@Controller
@RequestMapping("/totest")
public class TestMpController {
	
	@Autowired
	@Qualifier("testmap")
	private TestUserMapper mapper;
	
	@Autowired
	private BaseMapper<UserTestVo> baseMapper;
	

	@RequestMapping("/page")
	@ResponseBody
	public List<UserTestVo> testByPage(){
		List<UserTestVo> list=mapper.selectPage(
				new Page<UserTestVo>(1,5),
				new EntityWrapper<UserTestVo>().eq("id", "1"));
		return list;
	}
	
	@RequestMapping("/page1")
	@ResponseBody
	public List<UserTestVo> testByPage1(){
		List<UserTestVo> list=mapper.selectPage(
				new Page<UserTestVo>(1,5),
				new EntityWrapper<UserTestVo>().eq("id", "1").or().eq("id", "2")
				.or().le("id", "5"));
		return list;
	}
	
	
	@RequestMapping("/page2")
	@ResponseBody
	public List<Map<String, Object>> testByPage3(){
		return mapper.selectMapsPage(new Page<UserTestVo>(1,5),
				new EntityWrapper<UserTestVo>().eq("id", "1").or().eq("id", "2")
				.or().le("id", "5"));
	}
	
	/**
	 * 尝试可不可以不写dao层就查数据库，没想到居然真的可以
	 * @return
	 */
	@RequestMapping("/page3")
	@ResponseBody
	public List<Map<String, Object>> testByPage4(){
		return baseMapper.selectMapsPage(new Page<UserTestVo>(1,5),
				new EntityWrapper<UserTestVo>().eq("id", "1").or().eq("id", "2")
				.or().le("id", "5"));
	}
	
	@RequestMapping("/page4")
	@ResponseBody
	public String testByPage5(String id){
		return baseMapper.deleteById(id)+"";
	}
	
	
	/**
	 * 	自己注意，配置了shiro后去配置类ShiroConfiguration中修改拦截配置，不然全是404
	 * @return
	 */
	@RequestMapping("/testShiro")
	@ResponseBody
	public String testShiro() {
		return "test";
	}
	
	
	@RequestMapping("/testShirolog")
	@ResponseBody
	public String testShirolog(String id) {
		UsernamePasswordToken token=new  UsernamePasswordToken(id, "瞎测试的");
		Subject sccurity = SecurityUtils.getSubject();
		try {
			sccurity.login(token);
		} catch (AuthenticationException e) {
			return "this is a err page";
		}
		
		return "this is a page";
	}
	
	@RequestMapping("/testShirologout")
	@ResponseBody
	public String testShirologout(String id) {
		UsernamePasswordToken token=new  UsernamePasswordToken(id, "瞎测试的");
		Subject sccurity = SecurityUtils.getSubject();
		try {
			sccurity.logout();
		} catch (AuthenticationException e) {
			return "退出失败";
		}
		
		return testShiro();
	}
	
	
	
}
