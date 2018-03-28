package com.example.paul.myapplication.ui.CustomSearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.ImageResult;
import com.squareup.picasso.Picasso;

/**
 * Created by butle on 3/28/2018.
 */

public class ImageDisplay extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        ImageView ivImage = (ImageView) findViewById(R.id.ivResult);
        TextView tvImageName = (TextView) findViewById(R.id.tvImageName);

        ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("result");
        String url = imageResult.getFullUrl();
        Picasso.with(this).load(url).into(ivImage);
        tvImageName.setText(imageResult.getTitle());
        // getActionBar().hide();
        getSupportActionBar().hide();
    }

}
