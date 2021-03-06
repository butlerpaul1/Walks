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
import com.example.paul.myapplication.api.model.mLab.User;
import com.example.paul.myapplication.api.service.ApiInterface;
import com.example.paul.myapplication.api.service.MlabApiClient;
import com.example.paul.myapplication.ui.MainActivity;
import com.example.paul.myapplication.ui.adapter.CompletedWalksAdapter;
import com.example.paul.myapplication.ui.adapter.UserWalksAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by butle on 4/10/2018.
 */

public class ViewCompletedWalks extends AppCompatActivity {

    private static final String TAG = "ViewFavWalks";
    private static final String array = "{'favWalks':1}";
    private final static String Api = "Sp-vJvuovvpQqzMiyuLGf7n-WG7e7RbF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.completed_walks));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewCompletedWalks.this, MainActivity.class));
            }
        });

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();

        String query = String.format("{'email':'%s'}",email);
        Log.d(TAG, query);


        if (Api.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY", Toast.LENGTH_LONG).show();
            return;
        }


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trails_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                MlabApiClient.getClient().create(ApiInterface.class);

        Call<List<User>> call = apiService.completedWalks(query,Api);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.w(TAG,new GsonBuilder().setPrettyPrinting().create().toJson(response));

                List<User> userList = response.body();
                recyclerView.setAdapter(new CompletedWalksAdapter(userList,R.layout.list_item_fav,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }
}
