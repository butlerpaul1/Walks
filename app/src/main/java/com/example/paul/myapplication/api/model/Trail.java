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
    @SerializedName("Format")
    private String format;
    @SerializedName("Length (Km)")
    private Double distance;


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


    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


    public Double getDistance() {
        return distance;
    }





}
