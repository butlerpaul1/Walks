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

    @SerializedName("Quality")
    private String quality;

    @SerializedName("Estimated time to complete (mins)")
    private String time;

    @SerializedName("Climb (m)")
    private String climb;

    @SerializedName("Grade")
    private String grade;

    @SerializedName("Nearest Town to Start")
    private String startTown;

    @SerializedName("Nearest Town to Finish")
    private String finishTown;


    public String getQuality() {
        return quality;
    }

    public String getTime() {
        return time;
    }

    public String getClimb() {
        return climb;
    }

    public String getGrade() {
        return grade;
    }

    public String getStartTown() {
        return startTown;
    }

    public String getFinishTown() {
        return finishTown;
    }


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
