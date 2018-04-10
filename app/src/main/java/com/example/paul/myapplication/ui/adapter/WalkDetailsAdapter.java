package com.example.paul.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.mLab.Trail;

import java.util.List;

/**
 * Created by butle on 3/15/2018.
 */

public class WalkDetailsAdapter extends ArrayAdapter<Trail> {

    private Context context;
    private List<Trail> values;

    public WalkDetailsAdapter ( Context context, List<Trail> values){
        super(context, R.layout.list_item_details, values);

        this.context = context;
        this.values = values;

    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if (row == null)
        {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_details, parent, false);

        }


        TextView textViewName = (TextView) row.findViewById(R.id.name);
        TextView textViewCounty = (TextView) row.findViewById(R.id.county);
        TextView textViewFormat = (TextView) row.findViewById(R.id.format);
        TextView textViewTime = (TextView) row.findViewById(R.id.time);
        TextView textViewLength = (TextView) row.findViewById(R.id.length);
        TextView textViewClimb = (TextView) row.findViewById(R.id.climb);
        TextView textViewDifficultly = (TextView) row.findViewById(R.id.diffuctlty);
        TextView textViewQuality= (TextView) row.findViewById(R.id.quality);
        TextView textViewStart = (TextView) row.findViewById(R.id.start);
        TextView textViewFinish = (TextView) row.findViewById(R.id.finish);
        TextView textViewDogs = (TextView) row.findViewById(R.id.dogs);

        Trail item = values.get(position);

        String trail = item.getTrailName();
        textViewName.setText("Trail Name: " +trail);

        String county = item.getCounty();
        textViewCounty.setText("County: " +county);

        String format = item.getFormat();
        textViewFormat.setText("Format: " + format);

        String climb = item.getClimb();
        textViewClimb.setText("Elevation(m) :" + climb);

        String time = item.getTime();
        textViewTime.setText("Estimated Time to Complete(mins): " +time);

        String length = item.getDistance();
        textViewLength.setText("Length(km): " +length);

        String diff = item.getGrade();
        textViewDifficultly.setText("Difficulty: " +diff);

        String quality = item.getQuality();
        textViewQuality.setText("Rating: " +quality);

        String dogs = item.getDogsAllowed();
        textViewDogs.setText("Dogs Allowed: " + dogs);

        String start = item.getStartTown();
        textViewStart.setText("Start Town: " +start);

        String finish = item.getFinishTown();
        textViewFinish.setText("Finish Town: " +finish);


        return row;



    }

}
