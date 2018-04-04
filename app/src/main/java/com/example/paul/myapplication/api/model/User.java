package com.example.paul.myapplication.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by butle on 3/29/2018.
 */

public class User {
    @SerializedName("email")
    private String email;
    @SerializedName("completedWalks")
    private String[] completedWalks;
    @SerializedName("favWalks")
    private String[] favWalks;

    public String getEmail() {
        return email;
    }
    public String[] getCompletedWalks() {
        return completedWalks;
    }

    public String[] getFavWalks() {
        return favWalks;
    }

    public User(String email, String[] completedWalks, String[] favWalks) {
        this.email = email;
        this.completedWalks = completedWalks;
        this.favWalks = favWalks;
    }
}
