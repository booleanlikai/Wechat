package com.likai.gateway.collectrol;


import com.likai.gateway.Util.WeChatUtil;
import com.likai.gateway.model.menu.button;
import com.likai.gateway.model.menu.menuRequest;
import com.likai.gateway.model.menu.menuResponse;
import com.likai.gateway.model.request.messageRequest;
import com.likai.gateway.service.Imp.menu.CreateMenuInfoImpl;
import com.likai.gateway.service.serviceDisType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * DemoConsumerController
 * 消费者控制层
 *
 * @author xiaoze
 * @date 2018/6/7
 */
@RestController
public class DemoConsumerController {

    private final static Logger logger = LoggerFactory.getLogger(DemoConsumerController.class);
//
//    @Autowired
//    private WeChatServiceImpl weChatService;

    @Autowired
    @Qualifier("serviceDisTypeImpl")
    private serviceDisType serviceDisType;


    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     * <p>
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping(value = "wechat")
    public String validate(@RequestParam(value = "signature") String signature,
                           @RequestParam(value = "timestamp") String timestamp,
                           @RequestParam(value = "nonce") String nonce,
                           @RequestParam(value = "echostr") String echostr) {

        String ss = WeChatUtil.checkSignature(signature, timestamp, nonce) ? echostr : null;
        logger.error("+++++++++++++++++++++++++++++[{}]++++++++++++++++++++", ss);
        return ss;

    }


    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(value = "wechat",
            consumes = {MediaType.TEXT_XML_VALUE},
            produces = {MediaType.TEXT_XML_VALUE}
    )
    @ResponseBody
    public Object processMsg(ServerHttpRequest request, @RequestBody messageRequest messageRequest) {
        // 调用核心服务类接收处理请求
        request.getQueryParams();//获取请求URL后面拼接的参数
        //获取请求体
        logger.info("==============================从微信接收到的数据【{}】", messageRequest.toString());
        Object response = serviceDisType.handlerMessage(messageRequest);
        logger.info("==============================返回的数据【{}】", response.toString());
        return response;
    }


    @GetMapping(value = "createMenu", produces = {MediaType.APPLICATION_JSON_VALUE})
    public menuRequest createMenu() throws InterruptedException, ExecutionException {
        menuRequest menuRequest = new menuRequest();
        button button1 = new button();
        button1.setType("click");
        button1.setKey("10001");
        button1.setName("测试");
        button button2 = new button();
//        button2.setType("click");
//        button2.setKey("10002");
        button2.setName("测试2");
        button button3 = new button();
        button3.setType("click");
        button3.setKey("10003");
        button3.setName("测试3");
        button button4 = new button();
        button4.setType("click");
        button4.setKey("10004");
        button4.setName("测试4");
        button button5 = new button();
        button5.setType("click");
        button5.setKey("10005");
        button5.setName("测试5");
        button button6 = new button();
        button6.setType("click");
        button6.setKey("10006");
        button6.setName("测试6");
        button button7 = new button();
        button7.setType("click");
        button7.setKey("10007");
        button7.setName("测试7");
        List<button> list1 = new ArrayList<>();
        list1.add(button1);
        list1.add(button2);
        list1.add(button3);
//        list1.add(button7);
        List<button> list2 = new ArrayList<>();
        list2.add(button4);
        list2.add(button5);
        list2.add(button6);
        button2.setSub_button(list2);
        menuRequest.setButton(list1);
        CreateMenuInfoImpl pushMenuInfo = new CreateMenuInfoImpl(menuRequest);
        FutureTask<menuResponse> task = new FutureTask<menuResponse>(pushMenuInfo);
        Thread th = new Thread(task);
        th.start();
        th.join();
        menuResponse menuResponse = task.get();
//        pushMenuInfo.pushMenu(menuRequest);
        return menuRequest;
    }


}

