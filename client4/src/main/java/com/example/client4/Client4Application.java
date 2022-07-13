package com.example.client4;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@RestController
@EnableHystrix
@SpringBootApplication
public class Client4Application {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${author.name}")
    private String name;

    @Value("${author.birth}")
    private Integer birth;

    @GetMapping("/")
    public String home(){
        return "welcome to consul.consul can be used insteadof eureka.bus";
    }

    @GetMapping("/config")
    public String testConfig(){
        return String.format("author name : %s \n,birth: %d",name,birth);
    }

    @GetMapping("/hystrix/{name}")
    @HystrixCommand(fallbackMethod = "myHystrix")
    public String testHystrix(@PathVariable("name")String name)throws Exception{
        if("0".equals(name)){
            throw new Exception("111");
        }else{
            return "success :"+name;
        }
    }

    public String myHystrix(String name){
        return "failed :" + name;
    }

    @GetMapping("/ribbon")
    public String testRibbon(){
        return restTemplate().getForObject("http://client4/config",String.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Client4Application.class, args);
    }

}
