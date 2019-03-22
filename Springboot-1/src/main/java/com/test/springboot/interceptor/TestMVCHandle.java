package com.test.springboot.interceptor;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TestMVCHandle implements WebMvcConfigurer{

	//添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ActionHandle()).addPathPatterns("/**").excludePathPatterns("/error","/111.action","/freemarker/login.action");
		registry.addInterceptor(new TestHandle()).excludePathPatterns("/error","/111.action","/freemarker/login.action","/**/*.*");
		
	}
	
	
	//匹配路由请求规则
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(true);//别用错方法了 
		//setUseTrailingSlashMatch 自动后缀路径模式匹配 效果即   /user=/user/
        //configurer.setUseTrailingSlashMatch(true);
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		
	}

	
	//注册自定义的Formatter和Convert
	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub
		
	}


	//静态资源处理
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		//registry.addResourceHandler("/**/*.js").addResourceLocations("classpath:/static/**/*.js");
		//registry.addResourceHandler("/**/*.css").addResourceLocations("classpath:/static/**/*.css");
		
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	//自定义视图控制器(简单说就是修改指定请求，比如地址，状态等)
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		  /*//super.addViewControllers(registry);
		    // 对 "/hello" 的 请求 redirect 到 "/home"   这个是累加的，就是放一个生效一个，后面同理
		    registry.addRedirectViewController("/hello", "/freemarker/S404");
		    // 对 "/admin/**" 的请求 返回 404 的 http 状态
		    registry.addStatusController("/admin/**", HttpStatus.NOT_FOUND);
		    // 将 "/home" 的 请求响应为返回 "home" 的视图 
		    registry.addViewController("/home").setViewName("home");*/
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO Auto-generated method stub
		return null;
	}

}
