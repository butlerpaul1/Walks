package com.example.paul.myapplication.api.service;

import java.util.List;

import com.example.paul.myapplication.api.model.Trail;
import com.example.paul.myapplication.api.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by norman on 12/26/16.
 */

public interface MlabClient {




    @GET("databases/walks/collections/walks")
    Call<List<Trail>> getTrails (@Query("apiKey") String apiKey);


    @GET("databases/walks/collections/walks?q={'Trail id': 7}")
    Call<List<Trail>> byId(@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/walks")
    Call<List<Trail>> byCounty (@Query("q") String county,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/walks")
    Call<List<Trail>> test (@Query("q") String county,@Query("apiKey") String apiKey);







}
