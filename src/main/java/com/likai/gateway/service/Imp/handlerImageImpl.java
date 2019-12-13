package com.likai.gateway.service.Imp;

import com.likai.gateway.model.Image.messageImageResponse;
import com.likai.gateway.model.request.messageRequest;
import com.likai.gateway.service.handlerImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class handlerImageImpl implements handlerImage {

    private final static Logger logger = LoggerFactory.getLogger(handlerImageImpl.class);

    @Override
    public messageImageResponse handlerImageMessage(messageRequest messageRequest) {
        messageImageResponse messageImageResponse = new messageImageResponse();
        messageImageResponse.setToUserName(messageRequest.getFromUserName());
        messageImageResponse.setFromUserName(messageRequest.getToUserName());
        messageImageResponse.setMsgType(messageRequest.getMsgType());
        messageImageResponse.setCreateTime(messageRequest.getCreateTime());
        List<String> list = new ArrayList<>();
        list.add(messageRequest.getMediaId());
        messageImageResponse.setMediaIdList(list);
        return messageImageResponse;
    }
}
