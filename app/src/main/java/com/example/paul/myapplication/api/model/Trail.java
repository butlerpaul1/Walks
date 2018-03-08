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
    //must be string
    private String distance;
    @SerializedName("Trail ID")
    private String id;

    public String getDistance() {
        return distance;
    }

    public String getTrailName() {
        return trailName;
    }
    public String getCounty() {
        return county;
    }
    public String getFormat() {
        return format;
    }
    public String getId(){return id;}







}
