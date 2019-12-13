package com.likai.gateway.service;

import com.likai.gateway.model.Image.messageImageResponse;
import com.likai.gateway.model.request.messageRequest;

public interface handlerImage {
    messageImageResponse handlerImageMessage(messageRequest messageRequest);
}
