# spring cloud gateway

spring boot 2.1.3.release和spring cloud Greenwich.SR6不兼容，启动会报错。换成2.1.5.release版本解决，所以这个工程的boot版本跟其他工程的不一样。

注意事项：
* 每个路由必须配置断言
* 路由匹配之后，前缀还是会带到转发的服务，如果被路由的服务没有前缀，要加spring.cloud.gateway.routes[0].filters.StripPrefix来忽略
* 路由地址，如果要用服务发现，要用loadbalancer筛选器lb://service来配置，不要用http://host:port 来配置

## 全局过滤器
实现GlobalFilter接口来自定义全局过滤器，实现Ordered接口来定义多个全局过滤器的优先级

## 局部过滤器
参考com.example.gateway.DelPrefixFilter\
继承AbstractGatewayFilterFactory类来实现，通过重写name方法自定义过滤器名称{filterName}，以在路由中配置spring.cloud.gateway.routes[0].filters.{filterName}。

## 默认过滤器
用于所有路由

    spring:
      cloud:
        gateway:
          default-filters:
          - AddResponseHeader=X-Response-Default-Foo, Default-Bar
          - PrefixPath=/httpbin
## 限流器Redis RateLimiter
RedisRateLimiter算法是令牌桶算法
* 引入依赖

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
        </dependency>
        
* 配置

        filters:
         - name: RequestRateLimiter
           args:
             redis-rate-limiter.replenishRate: 10
             redis-rate-limiter.burstCapacity: 20

redis-rate-limiter.replenishRate: 令牌填充速率/s\
redis-rate-limiter.burstCapacity：最大请求数/s。也是令牌桶中的令牌数量。

## 网关端点
org.springframework.cloud.gateway.actuate.GatewayControllerEndpoint

    globalfilters	GET	    展示所有全局过滤器
    routefilters	GET	    展示所有路由过滤器
    refresh	        POST	    Clears the routes cache.
    routes	        GET	    展示所有的路由
    routes/{id}	GET	    获取路由信息
    routes/{id}	POST	    添加新路由。Json格式参考org.springframework.cloud.gateway.route.RouteDefinition
    routes/{id}	DELETE	    删除路由

