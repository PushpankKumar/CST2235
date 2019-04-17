package com.example.kites.flighttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FlightAdapter extends ArrayAdapter {
    Context c;
    ArrayList<AllData> data;
    LayoutInflater inflater;
    int row_file;
    public FlightAdapter( Context context, int resource,ArrayList<AllData> data) {
        super(context, resource,data);
        c=context;
        row_file=resource;
        this.data=data;
        inflater=LayoutInflater.from(c);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         View v=inflater.inflate(row_file,null,true);
        TextView ia=v.findViewById(R.id.ia);
        TextView ic=v.findViewById(R.id.ic);
        TextView num=v.findViewById(R.id.n);
        AllData ad=data.get(position);
        Flight dd=ad.fl;
        String ia1=dd.a;
        String ic1=dd.b;
        String num1=dd.c;
        ia.setText(ia1);
        ic.setText(ic1);
        num.setText(num1);
         return  v;

    }
}
