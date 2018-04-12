package com.example.paul.myapplication.api.service;

import com.example.paul.myapplication.api.model.mLab.Trail;
import com.example.paul.myapplication.api.model.mLab.Update;
import com.example.paul.myapplication.api.model.mLab.UpdateRequest;
import com.example.paul.myapplication.api.model.mLab.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface ApiInterface {
    //sort in order
    @GET("databases/walks/collections/walks?s={'Trail Name':1}")
    Call<List<Trail>> getTrails (@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/walks?s={'Trail Name':1}")
    Call<List<Trail>> byCounty (@Query("q") String county,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/walks?s={'Trail Name':1}")
    Call<List<Trail>> likeWalk (@Query("q") String county,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/walks")
    Call<List<Trail>> byTrailID (@Query("q") String TrailName,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/user")
    Call<List<User>> favWalks ( @Query("q") String q,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/user")
    Call<List<User>> completedWalks ( @Query("q") String q,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/user")
    Call<List<User>> user (@Query("apiKey") String apiKey);

    @POST("databases/walks/collections/user")
    Call<User> createAccount (@Query("apiKey") String apiKey, @Body User user);

    @PUT("databases/walks/collections/user")
    Call<Update> updateWalks(@Query("apiKey") String apiKey, @Query("q") String Email, @Body UpdateRequest Query);

}
