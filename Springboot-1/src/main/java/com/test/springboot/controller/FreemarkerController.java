package com.test.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.test.springboot.config.FilterTestConfig;
import com.test.springboot.config.PropertiesConfig;
import com.test.springboot.mapper.UserTestMapper;
import com.test.springboot.vo.UserTestVo;
import com.test.springboot.vo.UserinfoVo;

/**
 * mybatis-plus 说明
 * 1.至目前为止其自带的构造器依然没有实现多表联查功能，但其支持注解和mapper文件方式获取查询结果，
 * *  测试可以利用构造器某种程度上实现多表联查，具体见下面test1方法
 * 2.目前貌似不支持存储过程，还是只能用常规写法操作 
 * 3.原本以为的可以不写三层直接查数据库并不是这样的，dao层extends
 * BaseMapper<xxxclass>必须写，不然最后还是无法实例化对象
 * 
 * @author dell
 *
 */

@Controller
@RequestMapping("/freemarker")
public class FreemarkerController {

	@Autowired
	private BaseMapper<UserTestVo> baseMapper;

	@Autowired
	private BaseMapper<UserinfoVo> baseMapper1;
	
	@Autowired
	@Qualifier("usermap")
	private UserTestMapper mapper;
	

	@Autowired
	private PropertiesConfig propert;

	@Autowired
	private FilterTestConfig filterconfig;
	
	@RequestMapping("/index")
	public String index(ModelMap map) {
		map.addAttribute("propert", propert);

		return "freemarker/table";
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(String rows, String page, String name) throws Exception {
		if (!(checkNull(rows) || checkNull(page))) {
			throw new Exception("传入参数缺失，请检查");
		}

		EntityWrapper<UserTestVo> entity = new EntityWrapper<UserTestVo>();
		
		entity.orderBy("convert(int,id)", true);

		if (checkNull(name)) {
			entity.like("name", name);
		}

		List<UserTestVo> rows1 = baseMapper
				.selectPage(new Page<UserTestVo>(Integer.parseInt(page), Integer.parseInt(rows)), entity);
		// 获取数据
		Map<String, Object> map = new HashMap<String, Object>();

		// 获取总条数
		int count = baseMapper.selectCount(new EntityWrapper<UserTestVo>());

		map.put("rows", rows1);
		map.put("total", count);

		JSONObject object = new JSONObject(map);
		return object.toString();
	}

	/**
	 * 新增用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertUser")
	@ResponseBody
	public String insertUser(String name, String age, String code) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!checkNull(name) || !checkNull(age) || !checkNull(code)) {
			throw new Exception("传入参数缺失，请检查");
		}

		// 先判断是否有同样code数据
		EntityWrapper<UserTestVo> entityWrapper = new EntityWrapper<UserTestVo>();
		entityWrapper.eq("code", code);
		Integer count = baseMapper.selectCount(entityWrapper);
		if (count >= 1) {
			map.put("success", 1);
			map.put("msg", "代号重复，请修改");
		} else {// 不重复开始保存
				// 先获取数据库最大的id值
			entityWrapper = new EntityWrapper<UserTestVo>();
			entityWrapper.setSqlSelect("MAX(convert(int,id)) id");
			Integer maxid = Integer.parseInt(baseMapper.selectList(entityWrapper).get(0).getId());

			UserTestVo vo = new UserTestVo();
			vo.setId((maxid + 1) + "");
			vo.setAge(age);
			vo.setCode(code);
			vo.setName(name);

			// 保存数据
			Integer countsucc = baseMapper.insert(vo);
			map.put("success", 0);
			map.put("msg", "成功保存数据，id为：" + (maxid + 1));
		}
		JSONObject object = new JSONObject(map);
		return object.toString();
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public String deleteUser(String id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!checkNull(id)) {
			throw new Exception("传入参数缺失，请检查");
		}

		EntityWrapper<UserTestVo> entityWrapper = new EntityWrapper<UserTestVo>();
		entityWrapper.eq("id", id);

		Integer count = 0;
		try {
			count = baseMapper.delete(entityWrapper);
			map.put("success", 0);
			map.put("msg", "删除成功！共删除" + count + "条数据.");
		} catch (Exception e) {
			map.put("success", 1);
			map.put("msg", "删除失败，e:" + e.getMessage());
		}

		JSONObject object = new JSONObject(map);
		return object.toString();
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bathDeleteUser")
	@ResponseBody
	public String bathDeleteUser(String ids) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!checkNull(ids)) {
			throw new Exception("传入参数缺失，请检查");
		}

		EntityWrapper<UserTestVo> entityWrapper = new EntityWrapper<UserTestVo>();
		entityWrapper.in("id", ids.split(","));

		Integer count = 0;
		try {
			count = baseMapper.delete(entityWrapper);
			map.put("success", 0);
			map.put("msg", "删除成功！共删除" + count + "条数据.");
		} catch (Exception e) {
			map.put("success", 1);
			map.put("msg", "删除失败，e:" + e.getMessage());
		}

		JSONObject object = new JSONObject(map);
		return object.toString();
	}

	/**
	 * 根据id改数据
	 * 
	 * @param id
	 * @param name
	 * @param code
	 * @param age
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editUser")
	@ResponseBody
	public String editUser(String id, String name, String code, String age) throws Exception {
		if (!(checkNull(id) || checkNull(name))) {
			throw new Exception("传入参数缺失，请检查");
		}
		EntityWrapper<UserTestVo> entity = new EntityWrapper<UserTestVo>();
		entity.eq("id", id);
		UserTestVo vo = new UserTestVo();
		vo.setId(id);
		vo.setAge(age);
		vo.setCode(code);
		vo.setName(name);

		Map<String, Object> map = new HashMap<String, Object>();

		Integer count = 0;
		try {
			
			count = baseMapper.update(vo, entity);
		} catch (Exception e) {
			map.put("success", 1);
			map.put("msg", "更新失败，e:" + e.getMessage());
		}

		map.put("success", 0);
		map.put("msg", "成功更新" + count + "条数据。");

		JSONObject object = new JSONObject(map);
		return object.toString();
	}

	/**
	 * 验证代号是否重复 （传回json格式固定，否则不生效）
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/validateName")
	@ResponseBody
	public String validateName(String code, String id) {

		EntityWrapper<UserTestVo> entity = new EntityWrapper<UserTestVo>();
		entity.eq("code", code);

		if (checkNull(id)) {
			entity.ne("id", id);
		}

		Integer count = baseMapper.selectCount(entity);
		System.out.println(count);
		Map<String, Object> map = new HashMap<String, Object>();
		if (count >= 1) {
			map.put("valid", false);
		} else {
			map.put("valid", true);
		}
		JSONObject object = new JSONObject(map);
		return object.toString();
	}

	public boolean checkNull(String check) {
		if (check == null || "null".equals(check) || "".equals(check)) {
			return false;
		}
		return true;
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		EntityWrapper<UserTestVo> entityWrapper = new EntityWrapper<UserTestVo>();
		entityWrapper = new EntityWrapper<UserTestVo>();
		entityWrapper.setSqlSelect("MAX(convert(int,id)) id");// 这里可以写表列名
		Integer maxid = Integer.parseInt(baseMapper.selectList(entityWrapper).get(0).getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", maxid);
		JSONObject object = new JSONObject(map);
		return object.toString();
	}

	/**
	 * 测试根据另一张表查询结果来查指定表,(就是测试构造器的where条件能否实现这种情况下的多表查询)
	 * 
	 * @return
	 */
	@RequestMapping("/test1")
	@ResponseBody
	public String test1() {

		EntityWrapper<UserinfoVo> entityWrapper = new EntityWrapper<UserinfoVo>();
		entityWrapper.where("organid=(select id from sys_organ where organcode='280001')");
		List<UserinfoVo> list = baseMapper1.selectList(entityWrapper);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", list.size());
		for (int i = 0; i < list.size(); i++) {
			map.put("msg" + i, list.get(i));
		}
		JSONObject object = new JSONObject(map);
		return object.toString();
	}
	
	
	
	/**
	 * 测试springboot直接跑sql语句
	 * @return
	 */
	@RequestMapping("/testSql")
	@ResponseBody
	public String testSql() {
		UserinfoVo vo=mapper.get("309BFEE4E5284A818E3AEB08DA761E45");
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", vo.toString());
		JSONObject object = new JSONObject(map);
		
		return object.toString();
	}
	
	/**
	 *   使用注解执行存储过程
	 * @return
	 */
	@RequestMapping("/testCaller")
	@ResponseBody
	public String testCaller() {
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", mapper.testCall());
		JSONObject object = new JSONObject(map);
		
		return object.toString();
	}
	
	
	
	@RequestMapping("/testProperties")
	@ResponseBody
	public String testProperties() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg",filterconfig.getFilters());
		JSONObject object = new JSONObject(map);
		
		return object.toString();
	}
	
	
	@RequestMapping("/login")
	@ResponseBody
	public String login() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg","这是登录页面");
		JSONObject object = new JSONObject(map);
		return object.toString();	
	}
	
	@RequestMapping("/S404")
	@ResponseBody
	public String S404() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg","这是404页面");
		JSONObject object = new JSONObject(map);
		return object.toString();	
	}
	
	
	@Value("")
	String test;
	@RequestMapping("/toTestProperties")
	public String toTestProperties() {
		return test;
	}
	
	
	
}
