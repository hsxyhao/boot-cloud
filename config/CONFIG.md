## Cloud Config 
**摘要**：
随着线上项目变的日益庞大，每个项目都散落着各种配置文件，如果采用分布式的开发模式，需要的配置文件随着服务增加而不断增多。
某一个基础服务信息变更，都会引起一系列的更新和重启，运维苦不堪言也容易出错。配置中心便是解决此类问题的灵丹妙药。
市面上开源的配置中心有很多，BAT每家都出过，360的QConf、淘宝的diamond、百度的disconf都是解决这类问题。
国外也有很多开源的配置中心Apache Commons Configuration、owner、cfg4j等等。这些开源的软件以及解决方案都很优秀，
但是我最钟爱的却是Spring Cloud Config，因为它功能全面强大，可以无缝的和spring体系相结合，够方便够简单颜值高我喜欢。
### 配置从本地读取配置文件
参考了网上的一些博客，大部分都是配置github远程仓库的，对于一些特殊用户（没有账户，懒，特殊需求等用户）来说
没有参考范文，虽然也发现了几篇带有本地文件配置的文章，但是说的不清不白的，对于初学者来说还是很混乱的，
这边具体介绍一下以本地磁盘为仓库的配置。具体步骤如下：

> 1. 添加spring-cloud-config-server依赖
> 2. 启动类上添加EnableConfigServer注解
> 3. 配置application.yml文件中配置spring.profiles.active=**native**
> 4. spring.cloud.config.server.native.search-locations=**file:///** ${path}

注意：在配置search-locations时windows路径需要使用file://，当前系统class路径需要使用classpath:/。
其默认值为classpath:/, classpath:/config,file:./, file:./config，这些效果是一样的。

### 配置github远程仓库作为配置仓库

### 配置客户端
在配置客户端之前需要了解下**配置文件会被转换成web接口规则**以及客户端的一些**配置属性**
#### 配置文件会被转换成web接口
+ /{application}/{profile}[/{label}]
+ /{application}-{profile}.yml
+ /{label}/{application}-{profile}.yml
+ /{application}-{profile}.properties
+ /{label}/{application}-{profile}.properties

这里需要对配置文件的命名了解一下，在服务端或者客户端访问配置文件的时候，会对配置文件的名称进行解析将其转换成
web接口，所以这边需要对配置文件的命名进行一个规范，所有的配置文件名称都需要使用application，profile，
label来表示，然后程序才能正确转换成web接口，具体访问路径参考转换规则。
#### 客户端配置属性
在配置客户端的时候，需要将这些配置属性添加到bootstrap引导上下文中，引导上下引导整个程序的启动，引导上下文的配置
属性优先级是最高，不会被覆盖，所以在这里需要在application同文件夹下创建一个bootstrap.yml文件，这样解释可能太
清楚，如果参考官方的对于初学者则更佳模糊，更不用说翻译版的了。
+ spring.cloud.config.name：对应配置文件的{application}部分
+ spring.cloud.config.profile：对应对应配置文件的{profile}部分
+ spring.cloud.config.label：对应git的分支，如果配置中心使用的是本地存储，则该参数无用
+ spring.cloud.config.uri：配置could-config-server服务器的地址
+ spring.cloud.config.discovery.service-id：指定配置中心的service-id，便于扩展为高可用配置集群。

### 客户端的坑
客户端看似简单，只需要配置一下引导上下文以及添加包依赖就行了，但是在实验的过程中有可能会出现的一个现象:客户端没有报错
正常退出，并没有出现常规的一直等待现象，因为一般的web程序都会一直监听用户的请求怎么会自动退出呢?这是由于pom没有添加
spring-boot的web启动依赖，客户端需要单独添加，服务端不需要，解决问题网站:[Idea Process finished with exit code 0 when spring-boot run
](https://stackoverflow.com/questions/32758996/intellij-process-finished-with-exit-code-0-when-spring-boot-run)

> 1. 添加依赖spring-cloud-starter-config，和服务端不一样
> 2. application.yml中配置management:security:enabled: false
> 3. 配置启动引导上下文

**遗留问题**：虽然目前客户端在启动是加载到了远程的配置信息，但是对于配置信息的更改等操作无法及时响应。

**验证流程**：1. 修改客户端应用的远程配置文件 2. 服务端检查是否更新 3. 服务端更新成功后再从客户端访问 4. 结果显示客户端并没有更新
### 客户端主动更新配置文件
> 至此为止客户端已经可以在**启动**的时候拉取远程配置，但是对于远程的配置还不能实现实时的更新。

> 此时需要对客户端进行改造，添加spring-boot监控依赖[actuator],添加依赖之后需要在配置bean上添加RefreshScope注解，
本实验添加在HelloConfigController上面。

> 当修改远程的配置文件之后，可以访问客户端POST:/refresh接口更新配置。
### 使用git hook优化客户端刷新refresh
虽然客户端可以访问refresh接口刷新配置，但是不能每次修改都需要人工的去访问更新接口，
这里使用Git hooks-post-commit进行优化，每次修改提交之后，git自动访问。
> 配置git hooks，[参考链接地址](https://stackoverflow.com/questions/5697210/msysgit-error-with-hooks-git-error-cannot-spawn-git-hooks-post-commit-no-su)
> 1. 进入.git隐藏目录，创建post-commit文件，没有后缀名
> 2. 添加post-commit执行脚本，已上传至cloud config项目资源文件夹下的git_hooks
### 高可用config服务
以上步骤在功能上已经基本完成，但是很显然称不上高可用，不符合微服务的理念，所以还需要对项目进行改造，将
服务端服务化，这样服务端和客户端耦合就不会这么高，服务端ip更改之后也不用再在客户端进行修改。简而言之就
是将config的客户端和服务端注册到eureka服务中心，以便扩展和维护。

新建一个eureka服务中心项目

config-server服务端改造
1. 添加eureka服务依赖，并添加eureka配置，启动类加上注解

config-client客户端改造
1. 去除spring.cloud.config.uri属性
2. 添加eureka服务依赖，启动类加上注解
3. 在引导上下文配置文件中配置添加eureka配置以及配置spring.cloud.discovery下的enabled和serviceId属性  

### spring cloud bus:消息总线(client)
config虽然服务化之后，但是还是可以针对于refresh进行优化。如果需要客户端获取到最新的配置信息需要执行refresh，
我们可以利用git hooks的机制每次提交代码发送请求来刷新客户端，当客户端越来越多的时候，需要每个客户端都执行一遍，这种方案就不太适合了。
1. 安装RabbitMQ环境
2. 客户端添加依赖spring-cloud-starter-bus-amqp
3. 客户端**application**配置文件添加spring.rabbitmq.host和port配置
4. 修改git hook或者webhook post地址为/bus/refresh不是原来的/refresh
