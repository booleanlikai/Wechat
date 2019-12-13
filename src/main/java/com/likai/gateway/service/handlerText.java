package com.likai.gateway.service;

import com.likai.gateway.model.Text.messageTextResponse;
import com.likai.gateway.model.request.messageRequest;

public interface handlerText {
    messageTextResponse handlerTextMessage(messageRequest messageRequest);
}
