package com.example.paul.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;
import com.example.paul.myapplication.api.service.MlabApiClient;
import com.example.paul.myapplication.api.service.ApiInterface;
import com.example.paul.myapplication.ui.Firebase.Settings;
import com.example.paul.myapplication.ui.adapter.TrailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by butle on 28-Feb-18.
 */

public class GetByTrailName extends AppCompatActivity {

    private static final String TAG = GetByTrailName.class.getSimpleName();


    private final static String Api = "Sp-vJvuovvpQqzMiyuLGf7n-WG7e7RbF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);



        // get Trail Name from previous activity
        String TrailName;
        final String County;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                TrailName= null;
                County = null;
            } else {
                TrailName= extras.getString("TrailName");
                County=extras.getString("County");
            }
        } else {
            TrailName= (String) savedInstanceState.getSerializable("TrailName");
            County=(String) savedInstanceState.getSerializable("County");
        }

        Log.d("Tag", TrailName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(GetByTrailName.this, GetByCounty.class);
                myIntent.putExtra("County", County);
                startActivity(myIntent);            }
        });


        //String countyString = String.format("{'County': '%s'}" , County);
        String countyString = String.format("{'Trail Name':'%s'}" , TrailName);


        if (Api.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trails_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                MlabApiClient.getClient().create(ApiInterface.class);

        Call<List<Trail>> call = apiService.byCounty(countyString,Api);
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

