package com.likai.gateway.service.Imp;



import com.likai.gateway.Util.WeChatContant;
import com.likai.gateway.model.token.tokenResponse;
import com.likai.gateway.service.accessTokenGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class accessTokenGetImpl implements accessTokenGet {

    private final static Logger logger = LoggerFactory.getLogger(accessTokenGetImpl.class);
    private static com.likai.gateway.model.token.tokenResponse tokenResponse = new tokenResponse();
    private  static int i=10;


    public void init() throws InterruptedException {
        tokenResponse.setAccess_token("1111111111111111111111111");
        tokenResponse.setExpires_in("sadsajdas");
        tokenResponse.setErrcode(null);
        tokenResponse.setErrmsg(null);
    }

//    public synchronized tokenResponse getaccess() {
////        WebClient client = WebClient.create(WeChatContant.token_url);
////        tokenResponse result = client.get()
////                .uri("/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}",WeChatContant.appID,WeChatContant.appsecret)
////                .accept(MediaType.APPLICATION_JSON)
////                .retrieve()
////                .bodyToMono(tokenResponse.class)
////                .doOnNext(res->{
////                    logger.info("---------------------------------[{}]",res.toString());
////                }).block();
//
//        tokenResponse.setAccess_token("1111111111111111111111111");
//        tokenResponse.setExpires_in("sadsajdas");
//        tokenResponse.setErrcode("sbadkjs");
//        i--;
//        if(i<=0)
//        {
//            try {
//                init();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        logger.info("---------------------------------[{}]", tokenResponse.toString());
//        return tokenResponse;
//    }
}
