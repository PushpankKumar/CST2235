package com.example.kites.flighttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FlightNumAdapter extends ArrayAdapter {
    Context c;
    ArrayList<String> data;
    LayoutInflater inflater;
    int row_file;
    public FlightNumAdapter(Context context, int resource, ArrayList<String> data) {
        super(context, resource,data);
        c=context;
        row_file=resource;
        this.data=data;
        inflater=LayoutInflater.from(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View v=inflater.inflate(row_file,null,true);
        TextView ia=v.findViewById(R.id.fn);

        String dd=data.get(position);
        ia.setText(dd);
         return  v;

    }
}
