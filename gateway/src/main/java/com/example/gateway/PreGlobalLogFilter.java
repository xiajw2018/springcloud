package com.example.gateway;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PreGlobalLogFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(PreGlobalLogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        logger.info("path:{},method:{},request uri:{}",request.getPath(),request.getMethodValue(),request.getURI().getRawPath());

        // 解决 Only one connection receive subscriber allowed.报错
        ServerHttpResponse originalResponse = exchange.getResponse();
        

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
