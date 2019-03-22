package com.test.springboot.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 需要多线程定时任务必须加两个注解 @EnableAsync @Async
 * @author dell
 *
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling	//定时任务开启注解
@EnableAsync        // 2.开启多线程
public class TestTask {
	
	@Async
	@Scheduled(cron = "0/5 * * * * *")//一般用cron表达式就够了，下面两个是设置多少毫秒执行一次，知道就行
	public void scheduled() {
	
		System.out.println("=====>>>>>使用cron  {}"+ System.currentTimeMillis()+"  thread :"+Thread.currentThread().getName());

	}
	
	@Async
	@Scheduled(fixedRate = 5000)
	public void scheduled1() {
		System.out.println("=====>>>>>使用fixedRate{}"+ System.currentTimeMillis()+"  thread :"+Thread.currentThread().getName());
		
	}
	
	@Async
	@Scheduled(fixedDelay = 5000)
	public void scheduled2() {
		System.out.println("=====>>>>>fixedDelay{}"+ System.currentTimeMillis()+"  thread :"+Thread.currentThread().getName());
		
	}

}
