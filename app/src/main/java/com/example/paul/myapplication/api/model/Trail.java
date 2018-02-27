package com.example.paul.myapplication.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by butle on 18-Feb-18.
 */

public class Trail {




    @SerializedName("Trail Name")
    private String trailName;
    @SerializedName("County")
    private String county;
    @SerializedName("Length(KM)")
    private int distance;
    @SerializedName("Format")
    private String format;



    public String getTrailName() {
        return trailName;
    }

    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }





}
