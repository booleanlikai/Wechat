package com.likai.gateway.service.Imp;

import com.likai.gateway.Util.WeChatContant;
import com.likai.gateway.model.baseMessage;
import com.likai.gateway.model.request.messageRequest;
import com.likai.gateway.service.handlerImage;
import com.likai.gateway.service.handlerText;
import com.likai.gateway.service.serviceDisType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class serviceDisTypeImpl implements serviceDisType {

    private final static Logger logger = LoggerFactory.getLogger(serviceDisTypeImpl.class);

    @Autowired
    @Qualifier("handlerImageImpl")
    private handlerImage handlerImage;


    @Autowired
    @Qualifier("handlerTextImpl")
    private handlerText handlerText;

    @Override
    public <T extends baseMessage> T handlerMessage(messageRequest messageRequest) {
        switch (messageRequest.getMsgType()) {
            case WeChatContant.REQ_MESSAGE_TYPE_IMAGE:
                return (T) handlerImage.handlerImageMessage(messageRequest);
            case WeChatContant.REQ_MESSAGE_TYPE_TEXT:
                return (T) handlerText.handlerTextMessage(messageRequest);
        }
        return null;
    }
}
