package com.example.paul.myapplication.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by butle on 3/30/2018.
 */

public class UpdateRequest {
    @SerializedName("$addToSet")
    public
    Map<String, String> addToSet = new HashMap();
}
