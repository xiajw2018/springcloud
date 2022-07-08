package com.example.client1.test;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RefreshScope //只有加了这个注解才能刷新配置
@RequestMapping("/test")
@RestController
public class TestController {

    @Value("${author.name}")
    private String name;

    @Value("${author.birth}")
    private Integer birth;

    @GetMapping("/1")
    public Object test(){
        return String.format("author name :%s,birth : %d",name,birth);
    }

    /**
     * 熔断回退
     */
    @HystrixCommand(fallbackMethod = "defaultResponse")
    @GetMapping("/2/{name}")
    public Object test2(@PathVariable("name")String name)throws Exception{
        if("0".equals(name)){
            throw new Exception("123123");
        }
        return name;
    }

    public Object defaultResponse(String name){
        return "default response test hystrix!";
    }
}
