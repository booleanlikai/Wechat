package com.likai.gateway.EnDeCode;

import com.likai.gateway.Util.DateUtil;
import com.likai.gateway.Util.EncodeDecode.AesException;
import com.likai.gateway.Util.EncodeDecode.WXBizMsgCryptFactory;
import com.likai.gateway.Util.WeChatUtil;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class EncodeDecodeUtil {
    private final static Logger logger = LoggerFactory.getLogger(EncodeDecodeUtil.class);

    public static final List<MediaType> legalMediaTypes = Arrays.asList(MediaType.TEXT_XML,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_JSON_UTF8,
            MediaType.TEXT_PLAIN,
            MediaType.TEXT_XML);

    @SuppressWarnings("unchecked")
    public static <T extends DataBuffer> DataBuffer InterRequest(List<DataBuffer> dataBuffers, String msgSignature, String timestamp, String nonce) throws IOException, AesException {
        //解决返回体分段传输
        List<String> list = new ArrayList<>();
        dataBuffers.forEach(dataBuffer -> {
            byte[] content = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(content);
            DataBufferUtils.release(dataBuffer);
            try {
                list.add(new String(content, "utf-8"));
            } catch (Exception e) {
                logger.error("--list.add--error", e);
            }
        });
        String resquestString = StringUtils.collectionToDelimitedString(list, "");
        logger.info("解密后密文: " + resquestString);
        String result2 = null;
        try {
            result2 = WXBizMsgCryptFactory.getWxBizMsgCrypt().decryptMsg(msgSignature, timestamp, nonce, resquestString);
        } catch (AesException e) {
            e.printStackTrace();
        }
        logger.info("解密后密文: " + result2);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
        return nettyDataBufferFactory.wrap(result2.getBytes());
    }

    public static <T extends DataBuffer> T InterResponse(List<DataBuffer> dataBuffers, HttpHeaders headers) throws IOException, AesException {
        List<String> list = new ArrayList<>();
        String timestamp = DateUtil.getNowTimeBySecond();
        String nonce = WeChatUtil.createTransactionSuffix();
        dataBuffers.forEach(dataBuffer -> {
            byte[] content = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(content);
            DataBufferUtils.release(dataBuffer);
            try {
                list.add(new String(content, "utf-8"));
            } catch (Exception e) {
                logger.error("--list.add--error", e);
            }
        });
        String responseString = StringUtils.collectionToDelimitedString(list, "");
        logger.info("加密前密文: " + responseString);
        String result2 = WXBizMsgCryptFactory.getWxBizMsgCrypt().encryptMsg(responseString, timestamp, nonce);
        logger.info("加密后密文: " + result2);
        headers.setContentLength(result2.length());
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
        return (T) nettyDataBufferFactory.wrap(result2.getBytes());
    }

    public static <T extends DataBuffer> T InterResponseMon(T buffer, HttpHeaders headers) throws IOException, AesException {
        String timestamp = DateUtil.getNowTimeBySecond();
        String nonce = WeChatUtil.createTransactionSuffix();
        try {
            InputStream dataBuffer = buffer.asInputStream();
            byte[] bytes = IOUtils.toByteArray(dataBuffer);
            NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
            String replyMsg = new String(bytes);
            String result2 = WXBizMsgCryptFactory.getWxBizMsgCrypt().encryptMsg(replyMsg, timestamp, nonce);
            logger.info("加密后密文: " + result2);
            headers.setContentLength(result2.length());
            DataBufferUtils.release(buffer);
            return (T) nettyDataBufferFactory.wrap(result2.getBytes());
        } catch (IOException | AesException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        }
    }



}
