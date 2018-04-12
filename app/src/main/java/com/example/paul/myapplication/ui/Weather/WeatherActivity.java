package com.example.paul.myapplication.ui.Weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.yahoo.Channel;
import com.example.paul.myapplication.api.model.yahoo.Item;
import com.example.paul.myapplication.api.service.WeatherServiceCallback;
import com.example.paul.myapplication.api.service.YahooWeatherService;
import com.example.paul.myapplication.ui.CustomSearch.WalkImageSearch;
import com.example.paul.myapplication.ui.WalkRequests.GetByCounty;
import com.example.paul.myapplication.ui.WalkRequests.WalkDetails;

/**
 * Created by butle on 3/22/2018.
 */

public class WeatherActivity extends Activity implements WeatherServiceCallback {

        private ImageView weatherIconImageView;
        private TextView temperatureTextView;
        private TextView conditionTextView;
        private TextView locationTextView;

        private YahooWeatherService service;
        private ProgressDialog dialog;

    private static final String TAG = "WeatherActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);



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

        


            weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
            temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
            conditionTextView = (TextView) findViewById(R.id.conditionTextView);
            locationTextView = (TextView) findViewById(R.id.locationTextView);

            service = new YahooWeatherService(this);
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...");
            dialog.show();

            final String Country = ",Ireland";
            String weatherLocation = County + Country;
            service.refreshWeather(weatherLocation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("WalkPal");
        /*toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(WeatherActivity.this, GetByCounty.class);
                myIntent.putExtra("County", County);
                startActivity(myIntent);
            }
        }); */



        /*
            ----------------------Bottom Nav-------------------
         */

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav_View);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){

                    case R.id.nav_weather:
                        Intent intentWeather = new Intent(WeatherActivity.this, WeatherActivity.class);
                        intentWeather.putExtra("County", County);
                        intentWeather.putExtra("Latitude", Latitude);
                        intentWeather.putExtra("Longitude", Longitude);
                        intentWeather.putExtra("TrailName", TrailName);
                        startActivity(intentWeather);
                        break;

                    case R.id.nav_details:
                        Intent intentDetails = new Intent(WeatherActivity.this, WalkDetails.class);
                        intentDetails.putExtra("TrailName", TrailName);
                        intentDetails.putExtra("Latitude", Latitude);
                        intentDetails.putExtra("Longitude", Longitude);
                        intentDetails.putExtra("County", County);
                        startActivity(intentDetails);
                        break;

                    case R.id.nav_images:
                        Intent walkImage = new Intent(WeatherActivity.this, WalkImageSearch.class);
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
        }

        @Override
        public void serviceSuccess(Channel channel) {
            dialog.hide();

            Item item = channel.getItem();

            int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

            @SuppressWarnings("deprecation")
            Drawable weatherIconDrawble = getResources().getDrawable(resourceId);

            weatherIconImageView.setImageDrawable(weatherIconDrawble);

            temperatureTextView.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
            conditionTextView.setText(item.getCondition().getDescription());
            locationTextView.setText(service.getLocation());
        }

        @Override
        public void serviceFailure(Exception exception) {
            dialog.hide();
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        }

}
