package com.example.paul.myapplication.ui;

/**
 * Created by butle on 29-Jan-18.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;
import com.example.paul.myapplication.api.service.MlabClient;
import com.example.paul.myapplication.ui.adapter.MlabTrailAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewWalks extends AppCompatActivity {


    private ListView listView;
    String apiKey = "Sp-vJvuovvpQqzMiyuLGf7n-WG7e7RbF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trail_list);

        listView = (ListView) findViewById(R.id.pagination_list);
        final TextView selected = (TextView)findViewById(R.id.trailName);


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.mlab.com/api/1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        MlabClient client = retrofit.create(MlabClient.class);
        Call<List<Trail>> call = client.getTrails(apiKey);

        call.enqueue(new Callback<List<Trail>>() {
            @Override
            public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {

                List<Trail> repos = response.body();

                listView.setAdapter(new MlabTrailAdapter( ViewWalks.this, repos));

                Log.d("TAG", "Trails: " + repos.size());
                Log.d("TAG", "Trail Name:" + repos);

            }

            @Override
            public void onFailure(Call<List<Trail>> call, Throwable t) {

                Toast.makeText(ViewWalks.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ViewWalks.this, "Item Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
