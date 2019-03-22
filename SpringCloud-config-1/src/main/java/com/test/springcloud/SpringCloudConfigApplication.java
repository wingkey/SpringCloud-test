package com.test.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * springcloud 统一配置项目配置文件 1.示例给的是以git为条件的配置
 * 2.需要配置的东西很少，主要是包导入+启动类@EnableConfigServer注释
 * 3.这个项目的配置文件还是没办法也放在git上的，具体配置见application.properties
 * 4.访问的话，注意访问地址直接给git的文件路径（不知道为什么git会给我的目标文件又加了两层文件夹，于是访问地址变成http://127.0.0.1:8093/SpringCloud-Config/blob/master/texttoget.properties）
 * 
 * 
 * 找到原因了，文件的命名格式要按官网说明命名，不然文件无法识别   	 测试用例：{application} - {profile}.properties
 * /{application}/{profile}[/{label}]
 * /{application}-{profile}.yml
 * /{label}/{application}-{profile}.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 * 
 * 
 * 5.读取项目实例见：SpringCloud-client
 * 
 * @author dell
 *
 */

/**
 * 和RabbitMQ的整合 -- 消息总线Spring Cloud Netflix Bus
 * 当项目使用远程配置配置文件时（比如使用git）多个项目配置同样的配置时，需要挨个更新，使用这个可以一次通知同样配置的一起更新
 * 
 */

/**
 * 使用rabbitMQ进行动态更新
 * 1.引入jar包spring-cloud-starter-bus-amqp 具体见pem 文件
 * 2.安装rabbitMQ  启动服务，具体安装步骤见百度
 * 3.配置文件：spring.rabbitmq.host=10.10.10.101   //rabbitMQ服务所在服务器
 *			spring.rabbitmq.port=5672			//默认登录端口
 *			spring.rabbitmq.username=fyc		//用户名
 *	 		spring.rabbitmq.password=fyc123		//密码
 *	
 *			这里登录用户需要授权，不授权会报错（授权permission，这个可以在登录页面上配）
 * 	
 * 4.因为rabbitMQ只是个通信工具，告知每个相关项目配置的变化，所以对应项目（本项目里就指的是springcloud-client）也要引入同样的jar包和配置文件
 * 5.当git上的文件修改后，向本服务发送post请求    bus/refresh  即可刷新配置，如果post请求报错  则是被拦截了，新增配置
 * 				management.security.enabled=false
 *
 */


@EnableEurekaClient//表示服务发现类
@EnableEurekaServer
@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigApplication.class, args);
	}

}
