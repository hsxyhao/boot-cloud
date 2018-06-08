## ZUUL 网关

### 简单网关配置
1. 添加依赖spring-cloud-starter-zuul
2. 启动类添加注解
3. application添加配置属性：zuul:routes:blog:path:和url

### 网关配置服务化
后端服务基本上都是服务化的，所以如果这里使用url写死的情况那么是不符合微服务的理念的。
