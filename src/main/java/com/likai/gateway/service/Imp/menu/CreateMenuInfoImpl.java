package com.likai.gateway.service.Imp.menu;


import com.alibaba.fastjson.JSONArray;
import com.likai.gateway.Util.WeChatContant;
import com.likai.gateway.model.menu.menuRequest;
import com.likai.gateway.model.menu.menuResponse;
import com.likai.gateway.schedule.TokenAccessFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.concurrent.Callable;



public class CreateMenuInfoImpl implements  Callable<menuResponse> {

    private final static Logger logger = LoggerFactory.getLogger(CreateMenuInfoImpl.class);
    private  menuRequest request=null;

    public CreateMenuInfoImpl(menuRequest request) {
        this.request = request;
    }

    @Override
    public menuResponse call() throws Exception {
        String jsonString=JSONArray.toJSONString(request);
        logger.info("+++++++jsonString++[{}]",jsonString);
        WebClient client = WebClient.create(WeChatContant.menuCreate);
        menuResponse result = client.post()
                .uri("?access_token={ACCESS_TOKEN}", TokenAccessFactory.getAccess_token())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(jsonString)
                .retrieve()
                .bodyToMono(menuResponse.class)
                .block();
        return result;
    }

}
