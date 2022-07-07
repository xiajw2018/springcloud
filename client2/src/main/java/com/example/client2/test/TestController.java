package com.example.client2.test;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${author.name}")
    private String name;

    @Value("${author.birth}")
    private Integer birth;

    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient discoveryClient;

    @GetMapping("/1")
    public Object test(){
        return String.format("author name :%s,birth : %d",name,birth);
    }

    @GetMapping("/2")
    public Object test2(){
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("client1",false);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(instance.getHomePageUrl()+"test/1",String.class);
    }
}
