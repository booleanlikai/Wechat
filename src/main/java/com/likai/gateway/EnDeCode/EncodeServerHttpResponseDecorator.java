package com.likai.gateway.EnDeCode;


import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.single;

public class EncodeServerHttpResponseDecorator extends ServerHttpResponseDecorator {
    private final static Logger log = LoggerFactory.getLogger(EncodeServerHttpResponseDecorator.class);

    EncodeServerHttpResponseDecorator(ServerHttpResponse delegate) {
        super(delegate);
    }

    @Override
    public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
        return super.writeAndFlushWith(body);
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        final MediaType contentType = super.getHeaders().getContentType();
        HttpHeaders headers = super.getHeaders();

        Publisher<? extends DataBuffer>  response = null;
        if (EncodeDecodeUtil.legalMediaTypes.contains(contentType)&&contentType.equals(MediaType.TEXT_XML)) {
            if (body instanceof Mono) {
                final Mono<DataBuffer> monoBody = (Mono<DataBuffer>) body;
                response = monoBody.publishOn(single()).map(dataBuffer ->
                        {
                            try {
                                return EncodeDecodeUtil.InterResponseMon(dataBuffer,headers);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return null;
                        });
            } else if (body instanceof Flux) {
                final Flux<DataBuffer> monoBody = (Flux<DataBuffer>) body;
                response = monoBody.buffer().map(dataBuffers -> {
                    try {
                        return EncodeDecodeUtil.InterResponse(dataBuffers,headers);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                });
            }
        } else {
            response = body;
        }
        return super.writeWith(response);
    }



}