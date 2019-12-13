package com.likai.gateway.service.Imp;

import com.likai.gateway.model.Text.messageTextResponse;
import com.likai.gateway.model.request.messageRequest;
import com.likai.gateway.service.handlerText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class handlerTextImpl implements handlerText {

    private final static Logger logger = LoggerFactory.getLogger(handlerTextImpl.class);

    @Override
    public messageTextResponse handlerTextMessage(messageRequest messageRequest) {
        String ToUserName = messageRequest.getToUserName();
        String FromUserName = messageRequest.getFromUserName();
        messageTextResponse messageTextResponse=new messageTextResponse();
        messageTextResponse.setFromUserName(ToUserName);
        messageTextResponse.setToUserName(FromUserName);
        messageTextResponse.setContent(messageRequest.getContent());
        messageTextResponse.setCreateTime(messageRequest.getCreateTime());
        messageTextResponse.setMsgType(messageRequest.getMsgType());
        messageTextResponse.setMsgId(messageRequest.getMsgId());
        return messageTextResponse;
    }
}
