package com.example.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class DelPrefixFilter extends AbstractGatewayFilterFactory<DelPrefixFilter.Config> {

    public DelPrefixFilter() {
        super(DelPrefixFilter.Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        int index = config.getIndex();
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getRawPath();
            String[] paths = path.split("/");
            String newPath = "";
            for(int i=0;i<paths.length;i++){
                if(index == i)
                    continue;
                if("".equals(paths[i]))
                    continue;
                newPath += "/"+paths[i];
            }
            if("".equals(newPath)){
                newPath = "/";
            }
            ServerHttpRequest req = request.mutate().path(newPath).build();
            return chain.filter(exchange.mutate().request(req).build());
        };
    }

    @Override
    public String name() {
        return "delPrefix";
    }

    public static class Config{
        private Integer index = 1;

        public Config() {
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }
    }
}
