package com.test.springboot.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.test.springboot.vo.UserinfoVo;

@Mapper
public interface UserinfoMapper extends BaseMapper<UserinfoVo>{
	
	@Select("select * from sys_userInfo where id=#{id}")
	public UserinfoVo getUserinfoById(@Param("id") String id);
	
	
	
	
}
