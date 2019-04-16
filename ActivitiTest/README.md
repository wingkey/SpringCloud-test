# Activiti 工作流说明

# 准备工作
*   配置pem文件，这里主要是activiti的包配置，dao层使用框架和数据库连接看自己的自行修改配置
*   建表：1.在resouerces下配置好自己数据库的连接信息，详见activiti.cfg.xml中的配置，根据自己的数据库自行修改。2.建表,见类createtable ，里面唯一要注意的就是前面配置的配置文件地址是否正确，执行里面的方法即可创建好所需要的表


#  默认网页工作台
*  先从官网下再activiti的包，从git或maven上下载的都不是要的，解压后里面有个war文件夹，里面的就是我们用的工作台，部署上tomcat后需要修改数据库连接，将自己使用的数据库连接包放到lib目录下，再去修改配置文件db.properties（这里面指定数据库是哪个的时候，sqlserver数据库写mssql不然不识别），这后面再重启tomcat连接的就是自己的数据库了，自己数据库要建的25张表见解压后的database文件夹，里面看自己对应的数据库选择自己用的建表语句


## 可能出现的情况  
* tomcat 启动报错   Version of activiti database (5.22.0.0) is more recent than the engine (5.13)  这个直接修改数据库表ACT_GE_PROPERTY 5.22.0.0为 5.13即可解决
* 默认登录用户   kermit   密码 kermit


#  表结构介绍
因为详细介绍表内容太多了，详见链接  https://blog.csdn.net/hj7jay/article/details/51302829
表大概分为这几类
*  ACT_GE_* : “GE”代表“General”（通用），用在各种情况下；

*  ACT_HI_* : “HI”代表“History”（历史），这些表中保存的都是历史数据，比如执行过的流程实例、变量、任务，等等。Activit默认提供了4种历史级别：
              none: 不保存任何历史记录，可以提高系统性能；
			  activity：保存所有的流程实例、任务、活动信息；
			  audit：也是Activiti的默认级别，保存所有的流程实例、任务、活动、表单属性；
			  full：最完整的历史记录，除了包含audit级别的信息之外还能保存详细，例如：流程变量。
			   对于几种级别根据对功能的要求选择，如果需要日后跟踪详细可以开启full。

*  ACT_ID_* : “ID”代表“Identity”（身份），这些表中保存的都是身份信息，如用户和组以及两者之间的关系。如果Activiti被集成在某一系统当中的话，这些表可以不用，可以直接使用现有系统中的用户或组信息；

*  ACT_RE_* : “RE”代表“Repository”（仓库），这些表中保存一些‘静态’信息，如流程定义和流程资源（如图片、规则等）；

*  ACT_RU_* : “RU”代表“Runtime”（运行时），这些表中保存一些流程实例、用户任务、变量等的运行时数据。Activiti只保存流程实例在执行过程中的运行时数据，并且当流程结束后会立即移除这些数据，这是为了保证运行时表尽量的小并运行的足够快；

##简单介绍几种常用表
######流程部署相关
*  act_re_deployement 部署对象表
*  act_ge_bytearray 资源文件表
*  act_ge_prperty  主键生成策略表（对于部署对象表的主键ID）

######流程实例相关表
*  act_ru_execution 正在执行的执行对象表（包含执行对象ID和流程实例ID，如果有多个线程可能流程实例ID不一样）
*  act_hi_procinst 流程实例历史表
*  act_hi_actinst 存放历史所有完成的任务

######Task 任务相关表
*  act_ru_task 代办任务表 （只对应节点是UserTask的）
*  act_hi_taskinst 代办任务历史表 （只对应节点是UserTask的）
*  act_hi_actinst  所有节点活动历史表 （对应流程的所有节点的活动历史，从开始节点一直到结束节点中间的所有节点的活动都会被记录）

######流程变量表
*  act_ru_variable 正在执行的流程变量表
*  act_hi_variable 流程变量历史表


#画流程图
#####eclipse插件下载地址：https://www.activiti.org/designer/archived/activiti-designer-5.18.0.zip
安装完毕后即可使用，直接new时搜activiti就能找到插件，不要使用第二个project 
*  要有start 和end
* 中间流程使用task面板，具体用里面哪个看需要
*  流程之间的连线使用connection中的第一个连




##中文api http://www.mossle.com/docs/activiti/index.html




## 常见类说明
*  ProcessEngine 流程引擎，核心类，所有其他相关类全是从他来的
	>  产生方式 	ProcessEngine engine=ProcessEngines.getDefaultProcessEngine()
	>可以产生RepositoryService :processengine.getRepositoryService()
	>可以产生RuntimeService: processengine.getRuntimeService()
	>可以产生TaskService:processengine.getTaskService()
* service说明 ：RepositoryService   管理流程定义
			  RuntimeService  执行管理，包括启动、推进、删除流程实例等操作
			TaskService：任务管理
			HistoryService:历史管理(执行完的数据的管理)
			IdentityService:组织机构管理
			FormService:一个可选服务，任务表单管理
			

### RepositoryService
* 即仓库服务类，其实就是定义两个文件，就是加载自己画的流程图文件（bpmn和对应png）
* 产生方式 ：processengine.getRepositoryService()
* 可以产生DeploymentBuilder，用来定义流程部署的相关参数
* 删除流程定义

### RuntimeService
* 流程执行服务类。可以从这个服务类中获取很多关于流程执行相关的信息。


### TaskService
* 任务服务类。可以从这个类中获取任务的信息

###  HistoryService
* 查询历史信息的类。在一个流程执行完成后，这个对象为我们提供查询历史信息。

###  ProcessDefinition
* 流程定义类。可以从这里获得资源文件等

### ProcessInstance
* 流程定义的执行实例，流程实例就表示一个流程从开始到结束的最大的流程分支，即一个流程中流程实例只有一个。

### Execution
* 描述流程执行的每一个节点。在没有并发的情况下，Execution就是同ProcessInstance。流程按照流程定义的规则执行一次的过程



### activiti 自带用户表
* 因为自带用户表，而且牵涉到分配任务关系人时牵涉到这些表，所以在不修改源码的条件下，项目要使用只能用外键进行关联来处理了
* ACT_ID_USER  用户表 主键id ，这里id即用户名自己注意用户名重复问题
* ACT_ID_GROUP 用户组   在创建流程表的时候可以按流程组分配，即该流程通知所有组人
* ACT_ID_MEMBERSHIP 用户-组关联表  即用户属于哪些组

###### 因为流程图分人的时候好像只能单人分，多人的话只好分组，那么这里就可以这样先建组再分




##----------------------------
## 流程图变量
分两种
  二者区别
*		 setVariable  全局性的 ，即在这个流程内，只要设置过，在哪个节点都能获取到这个值
*		 setVariableLocal   局部的 ，只在当前节点生效，到下个节点就查询不到了
		 
		 当然，二者的值都可以放实体类 ，只是要求实体类要序列化（内部有处理）
		 
		 other:
		 
		 1）RuntimeService对象可以设置流程变量和获取流程变量
		 2）TaskService对象可以设置流程变量和获取流程变量
		 3）流程实例启动的时候可以设置流程变量
		 4）任务办理完成的时候可以设置流程变量
		 5）流程变量可以通过名称/值的形式设置单个流程变量
		 6）流程变量可以通过Map集合，同时设置多个流程变量
		 Map集合的key表示流程变量的名称
		 Map集合的value表示流程变量的值


##  连线   getway 
即流程图里面连接各个节点的线 ，这个线可以设定执行条件，可以根据读取的流程变量决定是否走这条线，比如某个节点后面分出两条线，a线条件变量s=true，b线条件变量 s=false，这个时候就看上个节点到底给这个变量的值了


## 排他网关
1)一个排他网关对应一个以上的顺序流
  2)由排他网关流出的顺序流都有个conditionExpression元素，在内部维护返回boolean类型的决策结果。
  3)决策网关只会返回一条结果。当流程执行到排他网关时，流程引擎会自动检索网关出口，从上到下检索如果发现第一条决策结果为true或者没有设置条件的(默认为成立)，则流出。
  4)如果没有任何一个出口符合条件，则抛出异常
  5)使用流程变量，设置连线的条件，并按照连线的条件执行工作流，如果没有条件符合的条件，则以默认的连线离开。
 
 这里的汇聚跟我理解的汇聚不一样，正如我自己写的一样到第二个网关时，一边结束，另一边才第一步，这里的这个网关只是保证任务不直接结束，让另一个继续跑而已，只有两边都结束才算结束任务
 
  和不使用排他网关的区别
  1.不使用时当检索不到时并不会抛出异常
  2.可以设置默认值
  
## 并行网关
并行网关：即该网关下多个节点都会启动
 
 
 1）一个流程中流程实例只有1个，执行对象有多个
2）并行网关的功能是基于进入和外出的顺序流的：
分支(fork)： 并行后的所有外出顺序流，为每个顺序流都创建一个并发分支。
汇聚(join)： 所有到达并行网关，在此等待的进入分支， 直到所有进入顺序流的分支都到达以后， 流程就会通过汇聚网关。
3）并行网关的进入和外出都是使用相同节点标识
4）如果同一个并行网关有多个进入和多个外出顺序流， 它就同时具有分支和汇聚功能。 这时，网关会先汇聚所有进入的顺序流，然后再切分成多个并行分支。
5）并行网关不会解析条件。 即使顺序流中定义了条件，也会被忽略。
并行网关不需要是“平衡的”（比如， 对应并行网关的进入和外出节点数目不一定相等）。
 
 
##任务人Assignee
测试指定节点任务人,这里图省事直接用并行节点直接三种方式一起测试了
 三种方式：1 .直接指定       
         2 使用流程变量
   3.使用监听器赋值任务人
   
   
   监听器说明
   	1）在类中使用delegateTask.setAssignee(assignee);的方式分配个人任务的办理人，此时张无忌是下一个任务的办理人
	2）通过processEngine.getTaskService().setAssignee(taskId, userId);将个人任务从一个人分配给另一个人，此时张无忌不再是下一个任务的办理人，而换成了周芷若
	3）在开发中，可以将每一个任务的办理人规定好，例如张三的领导是李四，李四的领导是王五，这样张三提交任务，就可以查询出张三的领导是李四，通过类的方式设置下一个任务的办理人
	       
## 组任务
 组任务及三种分配方式：
    1：在taskProcess.bpmn中直接写 candidate-users=“小A,小B,小C,小D"
    2：在taskProcess.bpmn中写 candidate-users =“#{userIDs}”，变量的值要是String的。
         使用流程变量指定办理人
              Map<String, Object> variables = new HashMap<String, Object>();
              variables.put("userIDs", "大大,小小,中中");
    3，使用TaskListener接口，使用类实现该接口，在类中定义：
            //添加组任务的用户
delegateTask.addCandidateUser(userId1);
delegateTask.addCandidateUser(userId2);
组任务分配给个人任务（认领任务）：
     processEngine.getTaskService().claim(taskId, userId);
个人任务分配给组任务：
     processEngine.getTaskService(). setAssignee(taskId, null);
向组任务添加人员：
     processEngine.getTaskService().addCandidateUser(taskId, userId);
向组任务删除人员：
     processEngine.getTaskService().deleteCandidateUser(taskId, userId);
个人任务和组任务存放办理人对应的表：
act_ru_identitylink表存放任务的办理人，包括个人任务和组任务，表示正在执行的任务
act_hi_identitylink表存放任务的办理人，包括个人任务和组任务，表示历史任务
区别在于：如果是个人任务TYPE的类型表示participant（参与者）
		 如果是组任务TYPE的类型表示candidate（候选者）和participant（参与者）

##角色组
 角色组：
 即写流程的时候给上角色组，当然前提是要已经建组了，没建组的话那么就建个组，再对组成员分配，
 
 注意：这里奇坑，按角色组分配和按任务人及按组分配三种分配节点任务人的方式查询方式全都不一样，所以这里奇坑
 当要查某个人目前的任务时，也就是说要按三种方式轮流查一遍
 
详细查询方式见类   TestGroup, TestCharacter  ,TestUser


