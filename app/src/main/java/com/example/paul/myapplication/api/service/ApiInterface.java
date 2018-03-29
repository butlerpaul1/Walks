package com.example.paul.myapplication.api.service;

import com.example.paul.myapplication.api.model.Trail;
import com.example.paul.myapplication.api.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiInterface {
    //sort in order
    @GET("databases/walks/collections/walks?s={'Trail ID':1}")
    Call<List<Trail>> getTrails (@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/walks")
    Call<List<Trail>> byCounty (@Query("q") String county,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/walks")
    Call<List<Trail>> likeWalk (@Query("q") String county,@Query("apiKey") String apiKey);


    @GET("databases/walks/collections/walks")
    Call<List<Trail>> byTrailID (@Query("q") String TrailName,@Query("apiKey") String apiKey);

    @GET("databases/walks/collections/user")
    Call<List<User>> favWalks (@Query("q") String Email,@Query("apiKey") String apiKey);

    @POST("databases/walks/collections/user?apiKey=Sp-vJvuovvpQqzMiyuLGf7n-WG7e7RbF")
    Call<User> createAccount (@Body User user);

    


}
