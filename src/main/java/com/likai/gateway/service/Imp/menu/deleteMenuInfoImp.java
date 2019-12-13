package com.likai.gateway.service.Imp.menu;

import com.likai.gateway.Util.WeChatContant;
import com.likai.gateway.model.menu.menuResponse;
import com.likai.gateway.schedule.TokenAccessFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.Callable;

public class deleteMenuInfoImp implements Callable<menuResponse> {
    @Override
    public menuResponse call() throws Exception {
        WebClient client = WebClient.create(WeChatContant.menuDelete);
        menuResponse result = client.get()
                .uri("?access_token={ACCESS_TOKEN}", TokenAccessFactory.getAccess_token())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(menuResponse.class)
                .block();
        return result;
    }
}
