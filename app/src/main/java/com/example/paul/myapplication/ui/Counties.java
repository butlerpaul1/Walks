package com.example.paul.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.myapplication.R;

/**
 * Created by butle on 23-Feb-18.
 */

public class Counties extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_counties);



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
