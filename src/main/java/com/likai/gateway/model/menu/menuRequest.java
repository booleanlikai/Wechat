package com.likai.gateway.model.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class menuRequest {

    @JSONField(name = "button")
    private List<button> button = new ArrayList<>();


    public List<button> getButton() {
        return button;
    }

    public void setButton(List<button> button) {
        this.button = button;
    }


}
