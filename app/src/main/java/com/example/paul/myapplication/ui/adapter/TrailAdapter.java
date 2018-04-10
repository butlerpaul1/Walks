package com.example.paul.myapplication.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.mLab.Trail;
import com.example.paul.myapplication.ui.WalkRequests.WalkDetails;

import java.util.List;

/**
 * Created by butle on 23-Feb-18.
 */

public class TrailAdapter extends RecyclerView.Adapter<TrailAdapter.TrailViewHolder> {



    private List<Trail> trails;
    private int rowLayout;
    private Context context;



    public static class TrailViewHolder extends RecyclerView.ViewHolder
            implements  View.OnClickListener, View.OnLongClickListener{
        LinearLayout trailsLayout;
        TextView trailTitle;
        TextView county;
        TextView format;
        TextView distance;

        private ItemClickListener mListener;


        public TrailViewHolder(final View v) {
            super(v);
            trailsLayout = (LinearLayout) v.findViewById(R.id.trails_layout);
            trailTitle = (TextView) v.findViewById(R.id.title);
            county = (TextView) v.findViewById(R.id.county);
            format = (TextView) v.findViewById(R.id.format);
            distance = (TextView) v.findViewById(R.id.distance);

            v.setClickable(true);
            v.setFocusableInTouchMode(true);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener listener) {
            this.mListener = listener;
        }

        @Override public void onClick(View view) {
            mListener.onClickItem(getLayoutPosition());
        }

        @Override public boolean onLongClick(View view) {
            mListener.onLongClickItem(getLayoutPosition());
            return true;
        }
    }
    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);

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
    public void onBindViewHolder(final TrailViewHolder holder, final int position) {

        final Trail trail = trails.get(position);

        holder.trailTitle.setText("Trail Name: " + trails.get(position).getTrailName());
        holder.county.setText("County: " + trails.get(position).getCounty());
        holder.format.setText("Format: " + trails.get(position).getFormat());
        holder.distance.setText("Distance(km): " + trails.get(position).getDistance());

        final String latitude = trail.getLatitute();
        final String longitude = trail.getLongitude();
        final String county = trail.getCounty();



        //get item id on click, and pass to new activity
        holder.setClickListener(new ItemClickListener() {
            @Override public void onClickItem(int pos) {
                String TrailName = trail.getTrailName();
                Intent intent = new Intent(context, WalkDetails.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("TrailName", TrailName);
                intent.putExtra("Latitude", latitude);
                intent.putExtra("Longitude", longitude);
                intent.putExtra("County", county);
                context.startActivity(intent);
            }

            @Override public void onLongClickItem(int pos) {
                //Toast.makeText(context, "Trail Name:" + trail.getTrailName(), Toast.LENGTH_SHORT).show();

                String TrailName = trail.getTrailName();
                Intent intent = new Intent(context, WalkDetails.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("TrailName", TrailName);
                intent.putExtra("Latitude", latitude);
                intent.putExtra("Longitude", longitude);
                intent.putExtra("County", county);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trails.size();
    }
}
