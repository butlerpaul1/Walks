package com.example.paul.myapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;

import java.util.List;

/**
 * Created by butle on 23-Feb-18.
 */

public class TrailAdapter extends RecyclerView.Adapter<TrailAdapter.TrailViewHolder> {



    private List<Trail> trails;
    private int rowLayout;
    private Context context;



    public static class TrailViewHolder extends RecyclerView.ViewHolder {
        LinearLayout trailsLayout;
        TextView trailTitle;
        TextView county;
        TextView format;
        TextView distance;


        public TrailViewHolder(final View v) {
            super(v);
            trailsLayout = (LinearLayout) v.findViewById(R.id.trails_layout);
            trailTitle = (TextView) v.findViewById(R.id.title);
            county = (TextView) v.findViewById(R.id.county);
            format = (TextView) v.findViewById(R.id.format);
            distance = (TextView) v.findViewById(R.id.distance);

            v.setClickable(true);
            v.setFocusableInTouchMode(true);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(v.getContext(), "Position" + Integer.toString(getAdapterPosition()), Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public TrailAdapter(List<Trail> trails, int rowLayout, Context context) {
        this.trails = trails;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public TrailAdapter.TrailViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new TrailViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TrailViewHolder holder, final int position) {
        holder.trailTitle.setText(trails.get(position).getTrailName());
        holder.county.setText(trails.get(position).getCounty());
        holder.format.setText(trails.get(position).getFormat());
        holder.distance.setText(trails.get(position).getDistance().toString());
    }

    @Override
    public int getItemCount() {
        return trails.size();
    }
}