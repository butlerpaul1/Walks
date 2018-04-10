package com.example.paul.myapplication.api.service;

import com.example.paul.myapplication.api.model.yahoo.Channel;

/**
 * Created by butle on 3/22/2018.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}