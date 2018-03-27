package com.example.paul.myapplication.ui.WalkRequests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;
import com.example.paul.myapplication.api.service.MlabApiClient;
import com.example.paul.myapplication.api.service.ApiInterface;
import com.example.paul.myapplication.ui.MainActivity;
import com.example.paul.myapplication.ui.adapter.TrailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by butle on 28-Feb-18.
 */

public class GetAllTrails extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    // TODO - insert your themoviedb.org API KEY here
    private final static String Api = "Sp-vJvuovvpQqzMiyuLGf7n-WG7e7RbF";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        if (Api.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trails_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                MlabApiClient.getClient().create(ApiInterface.class);

        Call<List<Trail>> call = apiService.getTrails(Api);
        call.enqueue(new Callback<List<Trail>>() {
            @Override
            public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                int statusCode = response.code();
                List<Trail> trails = response.body();
                recyclerView.setAdapter(new TrailAdapter(trails, R.layout.list_item_trail, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Trail>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });

    }
}
