package com.test.springboot.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.test.springboot.vo.UserTestVo;

public class UserRealm extends AuthorizingRealm  {
	
	@Autowired
	private BaseMapper<UserTestVo> baseMapper;

	// 这里做权限控制
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	// 这里做登录控制
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
;		UsernamePasswordToken usertoken=(UsernamePasswordToken) token;
		
		

		String id=usertoken.getUsername();
		Object a=usertoken.getPassword();
		
		UserTestVo user=baseMapper.selectById(id);
		
		
		
		if (user!=null) {
			AuthenticationInfo info=new SimpleAuthenticationInfo(id, user.getAge(), "qwertyt");
			return info;
		}
		return null;
	}

	

}
