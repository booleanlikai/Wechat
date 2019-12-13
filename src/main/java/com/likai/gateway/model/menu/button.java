package com.likai.gateway.model.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class button {
    @JSONField(ordinal = 1)
    private String type;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(ordinal = 3)
    private String key;
    @JSONField(ordinal = 4)
    private String url;
    @JSONField(ordinal = 5)
    private String media_id;
    @JSONField(ordinal = 6)
    private String appid;
    @JSONField(ordinal = 7)
    private String pagepath;
    @JSONField(ordinal = 9)
    private List<button> sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public List<button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<button> sub_button) {
        this.sub_button = sub_button;
    }
}
