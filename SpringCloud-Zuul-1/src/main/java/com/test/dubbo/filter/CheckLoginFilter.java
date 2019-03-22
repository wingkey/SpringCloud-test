package com.test.dubbo.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 这里是一个简单的认证用户是否登录的过滤器，假设用户登录后将信息放在token中，这时判断token是否为空
 * @author dell
 *
 */
public class CheckLoginFilter extends ZuulFilter{

	private static Logger logger = LoggerFactory.getLogger(CheckLoginFilter.class);
	
	//在pre（路由之前）状态过滤
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }
 
    @Override
    public int filterOrder() {
        return 0;
    }
 
    @Override
    public boolean shouldFilter() {
        return true;
    }
 
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
 
        Object token = request.getParameter("token");
 
        //校验token
        if (token == null) {
            logger.info("token为空，禁止访问!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        } else {
            //TODO 根据token获取相应的登录信息，进行校验（略）
        }
 
        //添加Basic Auth认证信息
        ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "*****"));
 
        return null;
    }
 
    private String getBase64Credentials(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }
}
