package com.test.springcloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	
	@RequestMapping("/login")
	@ResponseBody
	public String login() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg","这是登录页面");
		JSONObject object = new JSONObject(map);
		return object.toString();	
	}
	
}
