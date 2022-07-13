# consul config
###maven repository

        <dependency>
             <groupId>org.springframework.cloud</groupId>
             <artifactId>spring-cloud-starter-consul-config</artifactId>
         </dependency>
使用consul的分布式配置，默认属性源为：
* config/${spring.application.name},${spring.profiles.active}/ 
* config/${spring.application.name}/ 
* config/application,${spring.profiles.active}/
* config/application/

根据spring.cloud.consul.config.format的值来决定如何读取
* key_value: 默认属性源文件夹下的key/value值
* files: \
    属性源：
    * config/${spring.application.name}-${spring.profiles.active}.yml
    * config/${spring.application.name}.yml
    * config/application.yml
* yaml: 默认属性源文件夹下的data（.yml格式）
* properties: 默认属性源文件夹下的data（.properties格式）

##consul-config配置解析：
`
    spring.cloud.consul.config.prefix=config 配置文件前缀
    spring.cloud.consul.config.defaultContext=application 全局默认配置文件夹
    spring.cloud.consul.config.profileSeparator=, profile分隔符
    spring.cloud.consul.config.dataKey=data yml,properties格式下，读取属性源下的key值
`
