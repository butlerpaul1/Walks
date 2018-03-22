package com.example.paul.myapplication.api.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MlabApiClient {

    public static final String Mlab = "https://api.mlab.com/api/1/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Mlab)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
