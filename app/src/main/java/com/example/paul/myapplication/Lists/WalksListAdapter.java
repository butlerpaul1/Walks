package com.example.paul.myapplication.Lists;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paul.myapplication.R;

import java.util.List;

/**
 * Created by butle on 06-Feb-18.
 */

public class WalksListAdapter extends RecyclerView.Adapter<WalksListAdapter.ViewHolder> {

    public List<AllWalks> allWalksList;

    public WalksListAdapter(List<AllWalks> allWalksList){

        this.allWalksList = allWalksList;


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.nameText.setText(allWalksList.get(position).getWalkName());
        holder.countyText.setText(allWalksList.get(position).getCounty());

    }

    @Override
    public int getItemCount() {
        return allWalksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public TextView nameText;
        public TextView countyText;



        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nameText = (TextView) mView.findViewById(R.id.walk_name);
            countyText = (TextView) mView.findViewById(R.id.county_name);
        }
    }

}
