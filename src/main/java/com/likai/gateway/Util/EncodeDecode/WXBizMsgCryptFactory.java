package com.likai.gateway.Util.EncodeDecode;

import com.likai.gateway.Util.WeChatContant;

public class WXBizMsgCryptFactory {
    private static WXBizMsgCrypt wxBizMsgCrypt;



    public static WXBizMsgCrypt getWxBizMsgCrypt() throws AesException {
        if(wxBizMsgCrypt==null){
            try {
                wxBizMsgCrypt = new WXBizMsgCrypt(WeChatContant.TOKEN,WeChatContant.encodingAesKey,WeChatContant.appID);
            } catch (AesException e) {
                e.printStackTrace();
            }
        }
        return wxBizMsgCrypt;
    }


}
