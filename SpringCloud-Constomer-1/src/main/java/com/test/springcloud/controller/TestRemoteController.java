package com.test.springcloud.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.springcloud.remote.TestRemote;

@RestController
public class TestRemoteController {

	@Autowired
	private TestRemote remote;
	
	@GetMapping("/test/login")
	public String getTestRemote() {
		System.out.println("----test   8092-----");
		return remote.testRemote();
	}
	
	
	@RequestMapping("/login")
	@ResponseBody
	public String login() {
		return "test to springcloud";	
	}
	
}
