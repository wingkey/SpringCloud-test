package com.test.dubbo.filter;




import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.ServletInputStream;


import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;


/**
 * zuul 过滤器，   看具体情况决定是否使用，具体怎么用可以尝试模仿源码
 *  Servlet30WrapperFilter
 * 	DebugFilter
 *  PreDecorationFilter
 *  RibbonRoutingFilter
 *  
 *  zuul自身本身就定义了很多过滤器，有需要按要求改写即可
 *  
 * @author dell
 *
 */
public class TestFilter extends ZuulFilter{

	
	/**
	 * 判断是否过滤
	 */
	@Override
	public boolean shouldFilter() {
		 RequestContext context = RequestContext.getCurrentContext();//获取请求，然后看具体请求内容决定是否使用过滤器
		return false;
	}

	
	/**
	 * 具体逻辑,比如动态修改请求参数等，这里就是给请求budy里随便加了一段，其他的看需求
	 */
	@Override
	public Object run() {
		 try {
		        RequestContext context = RequestContext.getCurrentContext();
		        InputStream in = (InputStream) context.get("requestEntity");
		        if (in == null) {
		            in = context.getRequest().getInputStream();
		        }
		        String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
		        body = "动态增加一段内容到body中: " + body;
		        byte[] bytes = body.getBytes("UTF-8");
		        context.setRequest(new HttpServletRequestWrapper(RequestContext.getCurrentContext().getRequest()) {
		            @Override
		            public ServletInputStream getInputStream() throws IOException {
		                return new ServletInputStreamWrapper(bytes);
		            }
		 
		            @Override
		            public int getContentLength() {
		                return bytes.length;
		            }
		 
		            @Override
		            public long getContentLengthLong() {
		                return bytes.length;
		            }
		        });
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return null;
	}

	/**
	 *  什么时候过滤   具体输出参数就是下面四个      具体可见常量类      FilterConstants
	 * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
	 *  pre：路由之前
	 *	routing：路由之时
	 *	post： 路由之后
	 *	error：发送错误调用
	 * 
	 */
	@Override
	public String filterType() {
		
		
		
		return null;
	}

	/**
	 * filterOrder：过滤的顺序  数值从小到大
	 */
	@Override
	public int filterOrder() {
		
		return 0;
	}

}
