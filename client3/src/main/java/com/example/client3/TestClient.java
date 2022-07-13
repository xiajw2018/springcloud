package com.example.client3;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * feign声明式编程。
 * 不同于RestTemplate对于微服务的调用，通过对接口的声明，绑定到eureka中的服务。
 * 后续直接调用此处的接口。
 */
@FeignClient("client1") // 服务名称，对应eureka中的名称
public interface TestClient {

    @RequestMapping(method = RequestMethod.GET,value = "/test/1")
    String test1();

    @RequestMapping(method = RequestMethod.GET,value = "/test/2")
    String test2();
}
