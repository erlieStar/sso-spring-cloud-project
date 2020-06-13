## 单点登陆组件

[博客介绍](https://blog.csdn.net/zzti_erlie/article/details/105991909) 

### 项目准备

依赖了lz写的工具类库，但是暂时还没有发布到maven仓库，可以手动install到本地仓库


```shell
git clone git@github.com:erlieStar/common-tool.git
cd common-tool
git checkout v2.0
mvn clean install -DskipTests=true
```

在sso-server模块下的resources目录下，有初始的表和数据，因为没有写注册的接口，所以初始化了一个用户，用户名和密码都是root

项目中配置的数据库名，用户名，密码都是test，按需修改

### 模块介绍

1. eureka-server（eureka注册中心）

2. sso-sample（单点登陆的client端）

3. sso-server（单点登陆的server端）

4. sso-spring-boot-starter（单点登陆的starter）

### 使用介绍

注意：单点登陆的server端需要和client端需要注册到一个eureka集群上

测试的时候先启动eureka，再启动server端和client端

加入单点登陆服务只需要加入如下starter

```xml
<dependency>
    <groupId>com.javashitang</groupId>
    <artifactId>sso-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

在配置类上加上@EnableSso注解，即可启动单点登陆的功能

可以配置的选项如下
```yaml
javashitang:
  sso:
    enable: true # 是否启用sso，加上@EnableSso注解，但是enable为false也不会启用
    includePattern: # 要拦截的路径，多个用英文逗号分隔
    excludePattern: # 要排除的路径，多个用英文逗号分隔
```
