package com.example.paul.myapplication.Lists;

/**
 * Created by butle on 06-Feb-18.
 */

public class AllWalks {

    String walkName, county;

    public AllWalks () {

    }

    public AllWalks(String walkName, String county) {
        this.walkName = walkName;
        this.county = county;
    }

    public String getWalkName() {
        return walkName;
    }

    public void setWalkName(String walkName) {
        this.walkName = walkName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
