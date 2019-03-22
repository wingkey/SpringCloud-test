package com.test.springboot.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import com.test.springboot.vo.UserTestVo;
/**
 * dao层只使用注解方式不使用配置文件的方式
 *  相当于直接执行sql语句，针对一些
 * @author dell
 *
 */
import com.test.springboot.vo.UserinfoVo;
@Mapper
@Repository("usermap")
public interface UserTestMapper {

	@Insert("insert into test_user (id,name,code,age) values (#{id,jdbcType=VARCHAR},#{name,jdbcType=VARCHAR},#{code,jdbcType=VARCHAR},#{age,jdbcType=VARCHAR}) ")
	public int insertUser(UserTestVo vo);
	
	@DeleteProvider(type=BatchUserTest.class,method="deleteByMap")
	public int deleteByMap(@SuppressWarnings("rawtypes") Map map);
	
	@Select("select * from sys_userInfo where id=#{id}")
	public UserinfoVo get(@Param("id")String id);
	
	//测试执行存储过程   注解方式
	@Select("{call data_test()}")
	@Results(id = "sendMessageInfos",
    value={ @Result(column = "id", property = "id"),
            @Result(column = "organid", property = "organid"),
            @Result(column = "usercode", property = "usercode"),
            @Result(column = "username", property = "username"),
            @Result(column = "userpass", property = "userpass") } )
	@Options(statementType= StatementType.CALLABLE )
	public UserinfoVo testCall();

}
