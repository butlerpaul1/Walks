package com.example.paul.myapplication.ui.CustomSearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.WalkImageResult;
import com.squareup.picasso.Picasso;

/**
 * Created by butle on 3/28/2018.
 */

public class WalkImageDisplay extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        ImageView walkImage = (ImageView) findViewById(R.id.walkImage);

        //saved instance state wont work as walkImageResult is not a string
        WalkImageResult walkImageResult;
        walkImageResult = (WalkImageResult) getIntent().getSerializableExtra("result");
        String url = walkImageResult.getFullUrl();
        Picasso.with(this).load(url).into(walkImage);
        getSupportActionBar().hide();
    }

}
