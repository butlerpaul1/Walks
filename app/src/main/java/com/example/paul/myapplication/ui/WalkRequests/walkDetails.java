package com.example.paul.myapplication.ui.WalkRequests;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;
import com.example.paul.myapplication.api.service.ApiInterface;

import com.example.paul.myapplication.api.service.MlabApiClient;
import com.example.paul.myapplication.ui.GoogleMaps.MapActivity;
import com.example.paul.myapplication.ui.MainActivity;
import com.example.paul.myapplication.ui.Weather.WeatherActivity;
import com.example.paul.myapplication.ui.adapter.WalkDetailsAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by butle on 3/15/2018.
 */

public class walkDetails extends AppCompatActivity{

    private ListView listView;
    private final static String mlabAPi = "Sp-vJvuovvpQqzMiyuLGf7n-WG7e7RbF";
    private final static String accuApi = "tCiIuvak1ZxqDc51RFKnsSlVaK7nhfJn";

    private static final String TAG = "walkDetails";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walk_details);




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

        //convert from String to LatLng
        final double lat = Double.parseDouble(Latitude);
        double longit = Double.parseDouble(Longitude);
        LatLng location = new LatLng(lat,longit);
        String locationString = location.toString();

        if (isServiceOK()){
            init(Latitude,Longitude,TrailName, County);
        }



        String countyString = String.format("{'Trail Name':'%s'}" , TrailName);

        /*
        ----------------------Toolbar-------------------
         */

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(walkDetails.this, GetByCounty.class);
                myIntent.putExtra("County", County);
                startActivity(myIntent);            }
        });


        /*
        ----------------------------------Walk Details------------------
                */

        listView = (ListView) findViewById(R.id.list_item_details);


        if (mlabAPi.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from!", Toast.LENGTH_LONG).show();
            return;
        }


        ApiInterface apiService =
                MlabApiClient.getClient().create(ApiInterface.class);

        Call<List<Trail>> call = apiService.byTrailID(countyString,mlabAPi);
        call.enqueue(new Callback<List<Trail>>() {
            @Override
            public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                int statusCode = response.code();
                List<Trail> trails = response.body();
                listView.setAdapter(new WalkDetailsAdapter(getApplicationContext(),trails));
            }

            @Override
            public void onFailure(Call<List<Trail>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());

            }
        });

        /*
        -------------------------Firebase User-----------------
         */

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String email = user.getEmail();

        Log.d(TAG, "Email:" + email);




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
                        Intent myIntent = new Intent(walkDetails.this, WeatherActivity.class);
                        myIntent.putExtra("County", County);
                        myIntent.putExtra("TrailName", TrailName);
                        myIntent.putExtra("Latitude", Latitude);
                        myIntent.putExtra("Longitude", Longitude);                        startActivity(myIntent);
                        break;

                    case R.id.nav_details:
                        startActivity(new Intent(walkDetails.this, MainActivity.class));
                        break;

                    case R.id.nav_images:
                        startActivity(new Intent(walkDetails.this, MainActivity.class));
                        break;


                }

                return false;
            }
        });


    }
    private void init(final String Lat, final String Long, final String Trailname, final String County)
    {


        ImageView btnMap = (ImageView) findViewById(R.id.mapView);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(walkDetails.this, MapActivity.class);
                intent.putExtra("Latitude", Lat);
                intent.putExtra("Longitude", Long);
                intent.putExtra("TrailName", Trailname);
                intent.putExtra("County", County);

                startActivity(intent);
            }
        });

    }

    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(walkDetails.this);

        if (available == ConnectionResult.SUCCESS)
        {
            Log.d(TAG, "Google play services is working :)");
            return true;

        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //version issue
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(walkDetails.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(this, "you cant make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
