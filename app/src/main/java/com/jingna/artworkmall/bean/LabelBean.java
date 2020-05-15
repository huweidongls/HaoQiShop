package com.jingna.artworkmall.bean;

/**
 * Created by Administrator on 2020/1/7.
 */

public class LabelBean {

    private int bg;
    private String color;

    public LabelBean(int bg, String color) {
        this.bg = bg;
        this.color = color;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
