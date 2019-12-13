package com.likai.gateway.service;

import com.likai.gateway.model.baseMessage;
import com.likai.gateway.model.request.messageRequest;

public interface serviceDisType {
    <T extends baseMessage> T handlerMessage(messageRequest messageRequest);
}
