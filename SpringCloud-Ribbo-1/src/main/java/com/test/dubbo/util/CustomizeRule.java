package com.test.dubbo.util;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;



/**
 * 自定义负载均衡机制    --轮流调，调够五次换一个
 * @author dell
 *
 */
public class CustomizeRule extends AbstractLoadBalancerRule{

	
	private int total = 0; 			// 总共被调用的次数，目前要求每台被调用5次
	private int currentIndex = 0;	// 当前提供服务的顺序
	
	
	@Override
	public Server choose(Object key) { //具体怎么处理主要看这里
		ILoadBalancer lb= getLoadBalancer();
		
		if (lb == null) {
			return null;
		}
		Server server = null;
 
		while (server == null) {
			if (Thread.interrupted()) {
				return null;
			}
			List<Server> upList = lb.getReachableServers();//获取所有课访问服务
			List<Server> allList = lb.getAllServers();//所有服务
 
			int serverCount = allList.size();
			if (serverCount == 0) {
				/*
				 * No servers. End regardless of pass, because subsequent passes only get more
				 * restrictive.
				 */
				return null;
			}
 
//			int index = rand.nextInt(serverCount);// java.util.Random().nextInt(3);
//			server = upList.get(index);
 
			
//			private int total = 0; 			// 总共被调用的次数，目前要求每台被调用5次
//			private int currentIndex = 0;	// 当前提供服务的机器号
			
			
			/**
			 * 这里所有服务用尽后会把两个参数重置为零
			 */
            if(total < 5)
            {
	            server = upList.get(currentIndex);
	            total++;
            }else {
	            total = 0;
	            currentIndex++;
	            if(currentIndex >= upList.size())
	            {
	              currentIndex = 0;
	            }
            }			
			
			
			if (server == null) {
				/*
				 * The only time this should happen is if the server list were somehow trimmed.
				 * This is a transient condition. Retry after yielding.
				 */
				Thread.yield();
				continue;
			}
 
			if (server.isAlive()) {
				return (server);
			}
 
			// Shouldn't actually happen.. but must be transient or a bug.
			server = null;
			Thread.yield();
		}
 
		return server;
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		
	}

}
