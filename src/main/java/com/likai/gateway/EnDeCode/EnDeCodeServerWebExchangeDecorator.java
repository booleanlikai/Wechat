package com.likai.gateway.EnDeCode;

import com.likai.gateway.Util.EncodeDecode.AesException;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

import java.io.IOException;


public class EnDeCodeServerWebExchangeDecorator extends ServerWebExchangeDecorator {

    private DecodeServerHttpRequestDecorator requestDecorator;

    private EncodeServerHttpResponseDecorator responseDecorator;

    public EnDeCodeServerWebExchangeDecorator(ServerWebExchange delegate) {
        super(delegate);
        try {
            requestDecorator = new DecodeServerHttpRequestDecorator(delegate.getRequest());
            responseDecorator = new EncodeServerHttpResponseDecorator(delegate.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServerHttpRequest getRequest() {
        return requestDecorator;
    }

    @Override
    public ServerHttpResponse getResponse() {
        return responseDecorator;
    }
}