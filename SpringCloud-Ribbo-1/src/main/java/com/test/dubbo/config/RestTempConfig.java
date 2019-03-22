package com.test.dubbo.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.test.dubbo.util.CustomizeRule;





/**
 * 配置负载均衡器
 * @author dell
 *
 */

@Configuration//表示配置类
public class RestTempConfig {
	

	/**
	 * 定义负载机制
	 * @return
	 */
	@Bean
	public IRule rule() {
		/**
		 * 机制用轮播   --如果有自定义算法直接参考这个的实现类，继承AbstractLoadBalancerRule后重写，最后这里给自定义算法
		 * 
		 * 其他机制：RandomRule  默认随机
		 * 		  RoundRobinRule  轮播，就是轮流来
		 * 		  RetryRule    失败了挨个重试
		 * 		  WeightedResponseTimeRule   按上次响应时间来选，用耗时最少的
		 * 		  ClientConfigEnabledRoundRobinRule  这个还是轮播
		 * 		  BestAvailableRule   找出并发请求最小的服务实例来使用
		 * 		  PredicateBasedRule   先通过内部定义的一个过滤器过滤出一部分服务实例清单，然后再采用线性轮询的方式从过滤出来的结果中选取一个服务实例。 
		 * 		  ZoneAvoidanceRule    ZoneAvoidanceRule中的过滤条件是以ZoneAvoidancePredicate为主过滤条件和以AvailabilityPredicate为次过滤条件组成的一个叫做CompositePredicate的组合过滤条件，过滤成功之后，继续采用线性轮询的方式从过滤结果中选择一个出来。 
		 * 
		 */
		//return new RoundRobinRule();
		return new CustomizeRule();   //自定义的，每个调五次后换
	}
	
	
	
	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();  //创建负载均衡器，当然这里可以创建自己定义算法的
		
	}
	
	
	
}
