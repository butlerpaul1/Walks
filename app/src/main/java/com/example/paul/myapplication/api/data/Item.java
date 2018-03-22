package com.example.paul.myapplication.api.data;

import org.json.JSONObject;

/**
 * Created by butle on 3/22/2018.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}