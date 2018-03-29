package com.example.paul.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.WalkImageResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by butle on 3/28/2018.
 */

public class WalkImageResultArrayAdapter extends ArrayAdapter<WalkImageResult>{


    public WalkImageResultArrayAdapter(Context context, List<WalkImageResult> images){
        super(context, android.R.layout.simple_list_item_1, images);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WalkImageResult walkImageResult = getItem(position);
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_image_result, parent, false);
        }
        ImageView walkImage = (ImageView) convertView.findViewById(R.id.ivImage);
        walkImage.setImageResource(0);
        Picasso.with(getContext()).load(walkImageResult.getThumbUrl()).into(walkImage);
        return convertView;
    }

}
