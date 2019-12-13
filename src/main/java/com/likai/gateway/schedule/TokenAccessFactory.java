package com.likai.gateway.schedule;

public class TokenAccessFactory {
    private static String access_token;
    private static boolean flag = false;
    private static boolean status = false;
    public synchronized static String getAccess_token() {
        return access_token;
    }

    public synchronized static void setAccess_token(String access_token) {
        TokenAccessFactory.access_token = access_token;
    }

    public synchronized static boolean isFlag() {
        return flag;
    }

    public synchronized static void setFlag(boolean flag) {
        TokenAccessFactory.flag = flag;
    }

    public synchronized static boolean isStatus() {
        return status;
    }

    public synchronized static void setStatus(boolean status) {
        TokenAccessFactory.status = status;
    }
}
