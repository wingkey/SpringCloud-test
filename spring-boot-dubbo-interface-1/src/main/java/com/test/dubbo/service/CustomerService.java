package com.test.dubbo.service;

public interface CustomerService {

	public String getUserByPage(Integer rows,Integer page,String name);
	
	public String selectById(String id);
	
	public Integer validateCode(String code);
	
	public String editUser(String id,String name,String code,String sex,String state);

	public String insertUser(String name,String code,String sex,String state);

	public String deleteById(String id);
	
	public String bathDeleteById(String ids);
	
}
