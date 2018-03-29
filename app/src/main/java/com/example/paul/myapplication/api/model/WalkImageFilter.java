package com.example.paul.myapplication.api.model;

import java.io.Serializable;

/**
 * Created by butle on 3/28/2018.
 */

public class WalkImageFilter implements Serializable{

    private String size;
    private String color;
    private String type;
    private String site;

    public WalkImageFilter(){
        this.size = "Any";
        this.color = "Any";
        this.type = "Any";
        this.site = "";
    }

    public WalkImageFilter(String size, String color, String type, String site) {
        this.size = size;
        this.color = color;
        this.type = type;
        this.site = site;
    }

    public boolean noSelections(){
        if (size.equals("Any") && color.equals("Any") && type.equals("Any") && site.equals("")) {
            return true;
        }
        return false;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getSite() {
        return site;
    }
}
