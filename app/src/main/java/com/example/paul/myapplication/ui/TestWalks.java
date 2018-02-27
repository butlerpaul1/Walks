package com.example.paul.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;
import com.example.paul.myapplication.api.service.MlabClient;
import com.example.paul.myapplication.ui.adapter.TrailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by butle on 23-Feb-18.
 */

public class TestWalks extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testwalks);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trails_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.mlab.com/api/1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        MlabClient client1 = retrofit.create(MlabClient.class);
        Call<List<Trail>> call1 = client1.getTrails("ss");

        call1.enqueue(new Callback<List<Trail>>() {
            @Override
            public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {

                List<Trail> repos = response.body();

                recyclerView.setAdapter(new TrailAdapter(repos, R.layout.list_trails,getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Trail>> call, Throwable t) {

                Toast.makeText(TestWalks.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
