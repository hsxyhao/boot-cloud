## ZUUL 网关

### 简单网关配置
1. 添加依赖spring-cloud-starter-zuul
2. 启动类添加注解
3. application添加配置属性：zuul:routes:blog:path:和url

### 网关配置服务化
后端服务基本上都是服务化的，所以如果这里使用url写死的情况是不符合微服务的理念的。
1. 添加eureka客户端功能
2. 将url属性修改为服务端的spring.application.name
3. zuul默认支持ribbon

### zuul支持默认配置
服务化之后的网关虽然可以应变服务的改变的，但是一旦服务变得多了那么就需要手动一个一个添加路由，还是
不方便量化，所以zuul很贴心的支持默认路由配置

测试验证：
+ 随便输入注册服务的spring.application.name作为前缀的url看能不能正确返回

### zuul高级配置以及自定义过滤器
zuul是通过一系列的过滤器来实现的

### 默认过滤器

| 类型        | 顺序    |  过滤器  |  功能  |
| --------   | -----:  | :----: | :----: |
|pre	|-3	|ServletDetectionFilter	|标记处理Servlet的类型|
|pre	|-2	|Servlet30WrapperFilter	|包装HttpServletRequest请求|
|pre	|-1	|FormBodyWrapperFilter	|包装请求体|
|route	|1	|DebugFilter	|标记调试标志|
|route	|5	|PreDecorationFilter	|处理请求上下文供后续使用|
|route	|10	|RibbonRoutingFilter	|serviceId请求转发|
|route	|100	|SimpleHostRoutingFilter	|url请求转发|
|route	|500	|SendForwardFilter	|forward请求转发|
|post	|0	|SendErrorFilter	|处理有错误的请求响应|
|post	|1000	|SendResponseFilter	|处理正常的请求响应|

### 禁用指定的过滤器
zuul:过滤器:类型:disable: true

### 自定义过滤器
zuul网关提供自定义的过滤器，继承ZuulFilter，重写ZuulFilter的方法，最后注入到容器中
+ filterType 定义filter的类型，有pre、route、post、error四种
+ filterOrder 定义filter的顺序，数字越小表示顺序越高，越先执行
+ shouldFilter 表示是否需要执行该filter，true表示执行，false表示不执行
+ run filter需要执行的具体操作

### 路由熔断  
当我们后台服务出现错误的时候，可以在网关进行拦截，并返回友好的提示信息，添加路由熔断，需要实现
ZuulFallbackProvider接口里面的方法
+ getRoute 需要拦截熔断的服务，注入到eureka服务的application.name
+ fallbackResponse 处理熔断的逻辑方法

### 路由重试
当服务不是由于出错，但是由于网络原因导致暂时性不可访问，这个时候可以使用路由重试
1. 添加依赖org.springframework.retry:spring-retry
2. 配置文件中配置zuul.retryable=true ribbon.MaxAutoRetries=2 ribbon.MaxAutoRetriesNextServer=0
> retryable 开启路由重试
> MaxAutoRetries 重试次数
> MaxAutoRetriesNextServer 切换相同Server的次数

### 注意
开启重试在某些情况下是有问题的，比如当压力过大，一个实例停止响应时，路由将流量转到另一个实例，很有可能导致最终所有的实例全被压垮。
说到底，断路器的其中一个作用就是防止故障或者压力扩散。用了retry，断路器就只有在该服务的所有实例都无法运作的情况下才能起作用。这
种时候，断路器的形式更像是提供一种友好的错误信息，或者假装服务正常运行的假象给使用者。
不用retry，仅使用负载均衡和熔断，就必须考虑到是否能够接受单个服务实例关闭和eureka刷新服务列表之间带来的短时间的熔断。
如果可以接受，就无需使用retry。
