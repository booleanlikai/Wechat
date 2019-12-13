package com.likai.gateway.model.menu;

import java.util.List;

public class menuQueryResponse {
    private String is_menu_open;
    private String selfmenu_info;
    private List<subButton> button;

    public String getIs_menu_open() {
        return is_menu_open;
    }

    public void setIs_menu_open(String is_menu_open) {
        this.is_menu_open = is_menu_open;
    }

    public String getSelfmenu_info() {
        return selfmenu_info;
    }

    public void setSelfmenu_info(String selfmenu_info) {
        this.selfmenu_info = selfmenu_info;
    }

    public List<subButton> getButton() {
        return button;
    }

    public void setButton(List<subButton> button) {
        this.button = button;
    }
}
