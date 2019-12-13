package com.likai.gateway.EnDeCode;


import com.likai.gateway.Util.EncodeDecode.AesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;

import java.io.IOException;



public class DecodeServerHttpRequestDecorator extends ServerHttpRequestDecorator {

    private final static Logger log = LoggerFactory.getLogger(DecodeServerHttpRequestDecorator.class);
    private Flux<DataBuffer> body;

    DecodeServerHttpRequestDecorator(ServerHttpRequest delegate) throws IOException, AesException {
        super(delegate);
//        final String path = delegate.getURI().getPath();
//        final String query = delegate.getURI().getQuery();
        final MultiValueMap<String, String> queryParams = delegate.getQueryParams();
        final String msgSignature = queryParams.get("msg_signature").get(0);
        final String timestamp = queryParams.get("timestamp").get(0);
        final String nonce = queryParams.get("nonce").get(0);
//        final String method = Optional.ofNullable(delegate.getMethod()).orElse(HttpMethod.GET).name();
        final MediaType contentType = delegate.getHeaders().getContentType();
        Flux<DataBuffer> flux = super.getBody();
        if (EncodeDecodeUtil.legalMediaTypes.contains(contentType)) {
            body = flux.buffer().map(dataBuffers -> {
                try {
                    return EncodeDecodeUtil.InterRequest(dataBuffers, msgSignature, timestamp, nonce);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            });
        } else {
            body = flux;
        }


    }

    @Override
    public Flux<DataBuffer> getBody() {
        return body;
    }

}
