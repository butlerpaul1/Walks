package com.example.paul.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.ui.Firebase.LoginActivity;
import com.example.paul.myapplication.ui.Firebase.Settings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by butle on 23-Feb-18.
 */

public class Counties extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_counties);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Counties.this, MainActivity.class));
            }
        });



        final String[] counties = getResources().getStringArray(R.array.counties);
        ListView countiesList = (ListView)findViewById(R.id.county_list);


        countiesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String county = counties[i];

                //String selected = "County:" +counties[i];
                //Toast.makeText(Counties.this, selected,Toast.LENGTH_SHORT).show();

                //pass county to activity

                Intent myIntent = new Intent(Counties.this, GetByCounty.class);
                myIntent.putExtra("County", county);
                startActivity(myIntent);
            }
        });

    }

}

