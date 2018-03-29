package com.example.paul.myapplication.ui.WalkRequests;

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
import com.example.paul.myapplication.ui.MainActivity;
import com.example.paul.myapplication.ui.adapter.TrailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by butle on 28-Feb-18.
 */

public class GetLikeTrailName extends AppCompatActivity {

    private static final String TAG = "GetLikeTrailName";


    private final static String Api = "Sp-vJvuovvpQqzMiyuLGf7n-WG7e7RbF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GetLikeTrailName.this, MainActivity.class));
            }
        });
        */

        // get Trail Name from previous activity
        String TrailName;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                TrailName= null;
            } else {
                TrailName= extras.getString("TrailName");
            }
        } else {
            TrailName= (String) savedInstanceState.getSerializable("TrailName");
        }

        Log.d("Tag", TrailName);


        String walkString = String.format("{'Trail Name': {$regex : '%s'}}" , TrailName);




        if (Api.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trails_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                MlabApiClient.getClient().create(ApiInterface.class);

        Call<List<Trail>> call = apiService.likeWalk(walkString,Api);
        call.enqueue(new Callback<List<Trail>>() {
            @Override
            public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                int statusCode = response.code();
                List<Trail> trails = response.body();
                if (trails.size() > 1) {
                    recyclerView.setAdapter(new TrailAdapter(trails, R.layout.list_item_trail, getApplicationContext()));

                }else{
                    Toast.makeText(getApplicationContext(),"WalkName/Keyword does not match any records", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Trail>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });

    }
}

