package com.likai.gateway.config;

import com.likai.gateway.EnDeCode.EnDeCodeServerWebExchangeDecorator;
import com.likai.gateway.logHttp.PayloadServerWebExchangeDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class WebConfig implements WebFluxConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(WebConfig.class);

    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        List<HttpMessageReader<?>> readers = new ArrayList<HttpMessageReader<?>>();

    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE) //过滤器顺序
    public WebFilter webFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            final String path = request.getURI().getPath();
            HttpMethod httpMethod = Optional.ofNullable(request.getMethod()).orElse(HttpMethod.GET);
            if(path.contains("wechat")&&httpMethod.equals(HttpMethod.POST)){
                return chain.filter(new EnDeCodeServerWebExchangeDecorator(exchange));
            }
            return  chain.filter(exchange);
        };
    }


    @Bean
    @Order(1) //过滤器顺序
    public WebFilter webFilterLog() {
        return (exchange, chain) ->
                chain.filter(new PayloadServerWebExchangeDecorator(exchange));
    }

}