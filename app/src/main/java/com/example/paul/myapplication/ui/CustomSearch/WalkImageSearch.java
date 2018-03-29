package com.example.paul.myapplication.ui.CustomSearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.WalkImageFilter;
import com.example.paul.myapplication.api.model.WalkImageResult;
import com.example.paul.myapplication.api.service.WalkImageService;
import com.example.paul.myapplication.ui.CustomSearch.helper.EndlessScrollListener;
import com.example.paul.myapplication.ui.WalkRequests.WalkDetails;
import com.example.paul.myapplication.ui.Weather.WeatherActivity;
import com.example.paul.myapplication.ui.adapter.WalkImageResultArrayAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

/**
 * Created by butle on 3/28/2018.
 */

public class WalkImageSearch extends AppCompatActivity {

    private static int MAX_PAGE = 5;
    GridView gvWalkImage;
    ArrayList<WalkImageResult> walkWalkImageResults;
    WalkImageResultArrayAdapter WalkImageAdapter;
    WalkImageService client;
    int startPage = 1;
    String TrailName;
    WalkImageFilter walkImageFilter = new WalkImageFilter();


    private static final String TAG = "WalkImageSearch";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        // get Trail Name from previous activity
        final String TrailName;
        final String Latitude;
        final String County;
        final String Longitude;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                TrailName= null;
                Latitude = null;
                Longitude = null;
                County = null;
            } else {
                TrailName= extras.getString("TrailName");
                Latitude=extras.getString("Latitude");
                Longitude=extras.getString("Longitude");
                County=extras.getString("County");
            }
        } else {
            TrailName= (String) savedInstanceState.getSerializable("TrailName");
            Latitude= (String) savedInstanceState.getSerializable("Latitude");
            Longitude= (String) savedInstanceState.getSerializable("Longitude");
            County = (String) savedInstanceState.getSerializable("County");


        }

        Log.d(TAG, TrailName);
        Log.d(TAG, Longitude);
        Log.d(TAG, Latitude);
        Log.d(TAG,County);

        /*
            ----------------------Bottom Nav-------------------
        */

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_View);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.nav_weather:
                        Intent intentWeather = new Intent(WalkImageSearch.this, WeatherActivity.class);
                        intentWeather.putExtra("County", County);
                        intentWeather.putExtra("TrailName", TrailName);
                        intentWeather.putExtra("Latitude", Latitude);
                        intentWeather.putExtra("Longitude", Longitude);
                        startActivity(intentWeather);
                        break;

                    case R.id.nav_details:
                        Intent intentDetails = new Intent(WalkImageSearch.this, WalkDetails.class);
                        intentDetails.putExtra("TrailName", TrailName);
                        intentDetails.putExtra("Latitude", Latitude);
                        intentDetails.putExtra("Longitude", Longitude);
                        intentDetails.putExtra("County", County);
                        startActivity(intentDetails);
                        break;

                    case R.id.nav_images:
                        Intent walkImage = new Intent(WalkImageSearch.this, WalkImageSearch.class);
                        walkImage.putExtra("TrailName", TrailName);
                        walkImage.putExtra("Latitude", Latitude);
                        walkImage.putExtra("Longitude", Longitude);
                        walkImage.putExtra("County", County);
                        startActivity(walkImage);
                        break;


                }

                return false;
            }
        });


        displayImages(TrailName);
    }

    public void displayImages(final String trailName){

        gvWalkImage = (GridView) findViewById(R.id.gvWalkImage);
        gvWalkImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), WalkImageDisplay.class);
                WalkImageResult walkImageResult = walkWalkImageResults.get(position);
                i.putExtra("result", walkImageResult);
                startActivity(i);
            }
        });

        gvWalkImage.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (page <= MAX_PAGE) {
                    onImageSearch((10*(page-1)) + 1, trailName);
                }
            }
        });

        walkWalkImageResults = new ArrayList<>();
        WalkImageAdapter = new WalkImageResultArrayAdapter(this, walkWalkImageResults);
        gvWalkImage.setAdapter(WalkImageAdapter);

        onImageSearch(1,trailName);

    }


    public void onImageSearch(int start, String trailName) {

            client = new WalkImageService();
        TrailName = trailName;
            startPage = start;
            if (startPage == 1)
                WalkImageAdapter.clear();

            client.getSearch(TrailName, startPage, walkImageFilter, new JsonHttpResponseHandler()
                    {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                JSONArray imageJsonResults;
                                if (response != null) {
                                    imageJsonResults = response.getJSONArray("items");
                                    WalkImageAdapter.addAll(WalkImageResult.fromJSONArray(imageJsonResults));
                                }
                            } catch (JSONException e) {
                                Log.e(TAG, e.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Toast.makeText(getApplicationContext(), "Service not working", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
    }

}
