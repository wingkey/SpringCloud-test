package com.test.dubbo.filter;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.jmnarloch.spring.cloud.ribbon.rule.MetadataAwareRule;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

/**
 * 简单的灰度发布的过滤器
 * 灰度发布：有多台服务器布置相同的服务，但业务流程不相同，比如某台服务器服务重写了，相较于其他服务器，不一样，现在该修改功能只希望在小范围内测试
 * 等测试无误后再全部上线，这个时候就需要灰度发布的功能了 实现：需要先导入jar包
 * 		ribbon-discovery-filter-spring-cloud-starter 具体见pom文件配置，对应不同版本的服务器也需要导入
 * 对应项目application.xml 加入配置 eureka.instance.metadataMap.version：xxx
 * 								eureka.instance.metadataMap.variant:xxx 
 * 	加入配置后由zuul网关决定服务分配到的服务器，详细见下面的过滤器实现
 * 
 * 
 * 问题：这个还是要配置文件的，那么动态修改无法实现，如果要实现热更新的话就需要将配置文件写成class类进行项目配置，然后对应属性值则通过远程配置方式（比如git）来获取，
 * 		但这个配置文件类如何写，就是个问题了(官网和git上并没有提供相关写法)
 * 
 * 		这个过滤类并没写完，相关其他属性配置都没写，其他的服务也并没有加入灰度发布，代码是否生效并没有测试
 * 
 * 
 * ribbon-discovery-filter-spring-cloud-starter
 * 官网有一段貌似是动态配置服务版本的代码，因为没测试所以不知道，在git的问题上有讨论到这个问题，作者给出的方案是更新的最新版本然后用下面的注入配置，
 * 但因为根本没写api文档官网只有个简单的使用示例也不知道是不是（鬼东西都不知道是不是有人维护，给的信息真的少），代码如下：
 *   @Bean
 *   @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
 *   public DiscoveryEnabledRule metadataAwareRule() {
 *       return new MetadataAwareRule(new DiscoveryEnabledPredicate() { ... });
 *   }
 * 
 * @author dell
 *
 */

public class GrayReleaseFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {

		return false;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		//这里灰度控制 是根据用户上传上来的参数来决定，这里代码假定相关参数是放在token中的
		Object token = request.getParameter("token");
		//假定信息是以json形式存放，这里先将token转换成json再获取指定参数
		JSONObject json=new JSONObject(token);
		//假定版本信息
		String version=json.getString("version");
		RibbonFilterContextHolder.clearCurrentContext();
		if (version.equals("springcloud-custom-0.0.1")) {
			RibbonFilterContextHolder.getCurrentContext().add("version", "variant");//这里两个参数指的即是对应服务器里配置文件配置的参数
		}else {
			RibbonFilterContextHolder.getCurrentContext().add("oldversion", "oldvariant");//不满足条件指向老服务器
		}
		//到这里灰度发布已经实现，剩下的就由框架处理

		return null;
	}

	@Override
	public String filterType() {

		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {

		return 0;
	}

}
