package com.example.paul.myapplication.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.mLab.User;

import java.util.List;

/**
 * Created by butle on 4/10/2018.
 */

public class CompletedWalksAdapter extends RecyclerView.Adapter<CompletedWalksAdapter.CompletedWalksHolder>
        {
private List<User> users;
private int rowLayout;
private Context context;

public static class CompletedWalksHolder extends RecyclerView.ViewHolder
        implements  View.OnClickListener, View.OnLongClickListener {
    LinearLayout usersLayout;
    TextView trailTitle;
    ImageButton delete;
    private ItemClickListener mListener;

    public CompletedWalksHolder(final View v)
    {
        super(v);
        usersLayout = (LinearLayout) v.findViewById(R.id.user_chosen);
        trailTitle = (TextView) v.findViewById(R.id.title);
        delete = (ImageButton) v.findViewById(R.id.deleteWalk);
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


            public CompletedWalksAdapter (List<User> users, int rowLayout, Context context)
    {
        this.users = users;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public CompletedWalksAdapter.CompletedWalksHolder onCreateViewHolder (ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CompletedWalksAdapter.CompletedWalksHolder(view);
    }

    @Override
    public void onBindViewHolder(CompletedWalksAdapter.CompletedWalksHolder holder, int position)
    {
        //get array from User object
        String[] favWalks = users.get(position).getCompletedWalks();
        Log.d("TAG", favWalks.toString());

        for (int i = 0; i < favWalks.length; i++)
        {
            holder.trailTitle.setText(favWalks[i]);
            String walk = favWalks[i];
        }
        for (String s : favWalks)
        {
            Log.d("CompletedWalksAdapter","TrailName:" + s);
            //holder.trailTitle.setText(s);
        }

        //get item id on click, and pass to new activity
        holder.setClickListener(new ItemClickListener() {
            @Override public void onClickItem(int pos) {
                Toast.makeText(context, "not yet ready", Toast.LENGTH_SHORT).show();

            }

            @Override public void onLongClickItem(int pos) {
                Toast.makeText(context, "not yet ready", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
