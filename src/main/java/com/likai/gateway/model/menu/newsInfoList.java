package com.likai.gateway.model.menu;

import java.util.List;

public class newsInfoList {
    private String type;

    private String name;

    private String key;

    private String url;

    private String media_id;

    private String appid;

    private String pagepath;

    private List<newsInfoDetail> list;

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

    public List<newsInfoDetail> getList() {
        return list;
    }

    public void setList(List<newsInfoDetail> list) {
        this.list = list;
    }
}
