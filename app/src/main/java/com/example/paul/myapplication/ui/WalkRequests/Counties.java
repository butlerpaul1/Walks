package com.example.paul.myapplication.ui.WalkRequests;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.ui.MainActivity;

/**
 * Created by butle on 23-Feb-18.
 */

public class Counties extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counties);

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
                //pass county to activity
                Intent myIntent = new Intent(Counties.this, GetByCounty.class);
                myIntent.putExtra("County", county);
                startActivity(myIntent);
            }
        });
    }

}

