package com.likai.gateway.Util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 请求校验工具类
 *
 * @author 32950745
 */
public class WeChatUtil {


    private final static Logger logger = LoggerFactory.getLogger(WeChatUtil.class);

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{WeChatContant.TOKEN, timestamp, nonce};
        logger.error("++++++++++++++++++signature:[{}]+++++++++timestamp:[{}]=======timestamp:[{}]", signature, timestamp, nonce);
        // 将token、timestamp、nonce三个参数进行字典序排序
        // Arrays.sort(arr);
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    private static void sort(String a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }


    public static String getTimeStamp() {
        long currentTimeMillis = System.currentTimeMillis();
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        Date dDate = null;
        try {
            dDate = format.parse(String.valueOf(currentTimeMillis));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format2.format(dDate);
        return time;
    }


    public synchronized static String createTransactionSuffix() {
        // 先加1再取值
        AtomicLong atomicLong = new AtomicLong(1);
        long i = atomicLong.incrementAndGet();
        System.out.println(i);
        if (i > 9999999999L) {
            // 当大于 9999999999L
            // 重新开始
            atomicLong = new AtomicLong();
            // 先加1再取值
            i = atomicLong.incrementAndGet();
        }
        return String.format(String.format("%010d", i));
    }


}