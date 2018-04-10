package com.example.paul.myapplication.api.model.mLab;

import com.google.gson.annotations.SerializedName;

/**
 * Created by butle on 3/30/2018.
 */

public class Update {

    @SerializedName("n")
    private String n;

    public String getN() {
        return n;
    }

    public Update(String n) {
        this.n = n;
    }
}
