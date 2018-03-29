package com.example.paul.myapplication.api.model;

import org.json.JSONObject;

/**
 * Created by butle on 3/22/2018.
 */

public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}