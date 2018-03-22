package com.example.paul.myapplication.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;

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
        //TextView textViewGrade = (TextView) row.findViewById(R.id.grade);
        TextView textViewDifficultly = (TextView) row.findViewById(R.id.diffuctlty);
        TextView textViewQuality= (TextView) row.findViewById(R.id.quality);
        TextView textViewStart = (TextView) row.findViewById(R.id.start);
        TextView textViewFinish = (TextView) row.findViewById(R.id.finish);

        Trail item = values.get(position);
        String trail = item.getTrailName();
        textViewName.setText(trail);

        String county = item.getCounty();
        textViewCounty.setText(county);

        String format = item.getFormat();
        textViewFormat.setText(format);

        String time = item.getTime();
        textViewTime.setText(time);

        String length = item.getDistance();
        textViewLength.setText(length);

        String diff = item.getGrade();
        textViewDifficultly.setText(diff);

        String quality = item.getQuality();
        textViewQuality.setText(quality);

        String start = item.getStartTown();
        textViewStart.setText(start);

        String finish = item.getFinishTown();
        textViewFinish.setText(finish);

        return row;



    }

}
