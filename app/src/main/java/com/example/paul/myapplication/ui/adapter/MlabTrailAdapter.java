package com.example.paul.myapplication.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.paul.myapplication.R;
import com.example.paul.myapplication.api.model.Trail;

/**
 * Created by norman on 12/26/16.
 */

public class MlabTrailAdapter extends ArrayAdapter<Trail> {

    private Context context;
    private List<Trail> values;

    public MlabTrailAdapter(Context context, List<Trail> values) {
        super(context, R.layout.list_item_pagination, values);

        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_pagination, parent, false);
        }

        TextView textViewTrail = (TextView) row.findViewById(R.id.trailName);
        TextView textViewCounty = (TextView) row.findViewById(R.id.county);
        TextView textViewFormat = (TextView) row.findViewById(R.id.format);
        //TextView textViewDistance = (TextView) row.findViewById(R.id.distance);



        Trail item = values.get(position);
        String trail = item.getTrailName();
        textViewTrail.setText(trail);

        String county = item.getCounty();
        textViewCounty.setText(county);

        String format = item.getFormat();
        textViewFormat.setText(format);




        return row;
    }


}
