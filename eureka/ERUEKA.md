## Eureka 单机部署，双节点集群以及多节点集群

### 实验过程中遇到的问题
1. 多节点部署时注册中心DS Replicas只有localhost
主要是由于application.yml配置文件中将client属性配置在instance下面了，client属性是在eureka
下面
2. 在idea中使用一个项目部署多个服务节点
对于多个eureka服务，基本配置是没有多大的变化，一般都是在port，defaultZone这里会有一些变化，所以
这里推荐使用spring的profile属性来切换部署配置，在application.yml配置文件中使用三个杠
(**---**)隔离配置环境。
3. 启动eureka服务的方式
在启动程序应用的时候在指定不同的profile。一般启动有两种方式：
```
1.打包成jar包使用命令行启动
java -jar eureka.jar --spring.profiles.active=peer1
2.在idea的启动程序中Programma arguments添加以下配置
--spring.profiles.active=peer1
```
4. 关于profile的小坑
在application.yml配置文件中配置profiles的时候不要配置成profiles:active: peer1，而是配置成
profiles: peer1

### application.yml配置
具体的配置可以查看项目的中application.yml配置文件，单机和多机部署主要的区别在于
fetch-registry,register-with-eureka,在多机部署不需要进行配置使用默认值