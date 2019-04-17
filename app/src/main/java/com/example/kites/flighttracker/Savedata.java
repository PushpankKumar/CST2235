package com.example.kites.flighttracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Savedata extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;
    public Savedata() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View v=inflater.inflate(R.layout.fragment_savedata, container, false);
        list=v.findViewById(R.id.list);
        list.setOnItemClickListener(this);
        fetch();
        return  v;
    }
    ArrayList<String>  ss;
    private void fetch() {
        SqliteDbSaveFlight dd=new SqliteDbSaveFlight(getActivity());
         ss=dd.fetchRecords();
         if(ss.size()>0) {
             FlightNumAdapter adpt = new FlightNumAdapter(getActivity(), R.layout.flight_num_row, ss);
             list.setAdapter(adpt);
         }
         else
         {
             Toast.makeText(getActivity(), "Data not Available", Toast.LENGTH_SHORT).show();
         }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String fnum=ss.get(position);
        Intent i=new Intent(getActivity(),Flight_Num_Tracker.class);
        i.putExtra("fn",fnum);
        startActivity(i);
    }
}
