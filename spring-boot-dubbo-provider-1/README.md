## dubbo-provider 说明
* 该项目即dubbo项目的服务提供者，消费者所调用的所有数据来源都是来自这里
* dubbo需要使用注册中心，这里使用的注册中心是zookeeper,这个需要自己去安装，相关安装步骤就省略到百度上了
* 项目中需要配置注册中心地址等信息，需要根据自己的注册中心进行修改，具体配置修改见application.properties
* 这里dao层使用的框架是mybatis-plus，该框架完美融合mybatis框架，可以以以配置mapper文件方式来进行项目编写，需要按springboot配置类方式配置开启读取配置mapper功能后才能使用
详细配置类在config包下
* mybatis-plus 本身具备不写mapper文件的方式直接写代码，基本方式三种 ：1.写mapper文件 2.使用注解直接写sql语句 3.使用plus自带的条件构造器直接构造查询条件，三种方法项目中都有使用，如果对这个框架感兴趣见springboot-1项目