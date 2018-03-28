package com.example.paul.myapplication.ui.CustomSearch;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.ImageFilter;
import com.example.paul.myapplication.api.model.ImageResult;
import com.example.paul.myapplication.ui.CustomSearch.helper.EndlessScrollListener;
import com.example.paul.myapplication.ui.MainActivity;
import com.example.paul.myapplication.ui.WalkRequests.walkDetails;
import com.example.paul.myapplication.ui.Weather.WeatherActivity;
import com.example.paul.myapplication.ui.adapter.ImageResultArrayAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by butle on 3/28/2018.
 */

public class SearchActivity extends AppCompatActivity {

    private static int MAX_PAGE = 10;
    //EditText etQuery;
    GridView gvResults;
    //Button btnSearch;
    ArrayList<ImageResult> imageResults;
    ImageResultArrayAdapter imageAdapter;
    SearchClient client;
    int startPage = 1;
    String query;
    ImageFilter imageFilter = new ImageFilter();


    private static final String TAG = "SearchActivity";



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
                        Intent intentWeather = new Intent(SearchActivity.this, WeatherActivity.class);
                        intentWeather.putExtra("County", County);
                        intentWeather.putExtra("TrailName", TrailName);
                        intentWeather.putExtra("Latitude", Latitude);
                        intentWeather.putExtra("Longitude", Longitude);
                        startActivity(intentWeather);
                        break;

                    case R.id.nav_details:
                        Intent intentDetails = new Intent(SearchActivity.this, walkDetails.class);
                        intentDetails.putExtra("TrailName", TrailName);
                        intentDetails.putExtra("Latitude", Latitude);
                        intentDetails.putExtra("Longitude", Longitude);
                        intentDetails.putExtra("County", County);
                        startActivity(intentDetails);
                        break;

                    case R.id.nav_images:
                        Intent walkImage = new Intent(SearchActivity.this, SearchActivity.class);
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


        setupViews(TrailName);
    }

    public void setupViews(final String trailName){

        gvResults = (GridView) findViewById(R.id.gvResults);
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ImageDisplay.class);
                ImageResult imageResult = imageResults.get(position);
                i.putExtra("result", imageResult);
                startActivity(i);
            }
        });

        gvResults.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (page <= MAX_PAGE) {
                    onImageSearch((10*(page-1)) + 1, trailName);
                }
            }
        });

        imageResults = new ArrayList<>();
        imageAdapter = new ImageResultArrayAdapter(this, imageResults);
        gvResults.setAdapter(imageAdapter);

        onImageSearch(1,trailName);

    }


    public void onImageSearch(int start, String trailName) {

            client = new SearchClient();
            query = trailName;
            startPage = start;
            if (startPage == 1)
                imageAdapter.clear();

            if (!query.equals(""))
                client.getSearch(query, startPage, imageFilter, this, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                try {
                                    JSONArray imageJsonResults;
                                    if (response != null) {
                                        imageJsonResults = response.getJSONArray("items");
                                        imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                super.onFailure(statusCode, headers, responseString, throwable);
                                Toast.makeText(getApplicationContext(), "Service not working", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            else {
                    Log.d(TAG, "Error with walk name");
            }
    }

}
