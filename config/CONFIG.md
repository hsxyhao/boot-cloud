## Cloud Config 
### 配置从本地读取配置文件
参考了网上的一些博客，大部分都是配置github远程仓库的，对于一些特殊用户（没有账户，懒，特殊需求）来说
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
spring-boot的web启动依赖，客户端需要单独添加，服务端不需要，解决问题网站:[IntelliJ Process finished with exit code 0 when spring-boot run
](https://stackoverflow.com/questions/32758996/intellij-process-finished-with-exit-code-0-when-spring-boot-run)

> 1. 添加依赖spring-cloud-starter-config，和服务端不一样
> 2. 配置启动引导上下文

**遗留问题**：虽然目前客户端在启动是加载到了远程的配置信息，但是对于配置信息的更改等操作无法及时响应。

**验证流程**：1. 修改客户端应用的远程配置文件 2. 服务端检查是否更新 3. 服务端更新成功后再从客户端访问 4. 结果显示客户端并没有更新

### 高可用config服务