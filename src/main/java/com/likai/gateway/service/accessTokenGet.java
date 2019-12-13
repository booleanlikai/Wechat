package com.likai.gateway.service;

import com.likai.gateway.Util.WeChatContant;
import com.likai.gateway.model.token.tokenResponse;
import com.likai.gateway.service.Imp.accessTokenGetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public interface accessTokenGet {

      Logger logger = LoggerFactory.getLogger(accessTokenGet.class);

    default public  tokenResponse getaccess(){
        WebClient client = WebClient.create(WeChatContant.token_url);
        tokenResponse result = client.get()
                .uri("/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}",WeChatContant.appID,WeChatContant.appsecret)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(tokenResponse.class)
                .doOnNext(res->{
                    logger.info("---------------------------------[{}]",res.toString());
                }).block();
        return  result;
    }
}
