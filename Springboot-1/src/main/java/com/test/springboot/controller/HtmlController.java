package com.test.springboot.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

	@RequestMapping("/test")
	public String test(Model model) {
		List<String> list = new ArrayList<String>(Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee"));

		model.addAttribute("test", "传参测试");
		model.addAttribute("list", list);
		return "test";
	}
}
