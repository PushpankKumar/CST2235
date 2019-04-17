package com.example.kites.flighttracker;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Arrive extends Fragment implements AdapterView.OnItemClickListener {
ListView list;
    String cc;
    AirportCode aac;
    public Arrive() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_arrive, container, false);
        list=v.findViewById(R.id.l);
        list.setOnItemClickListener(this);
        aac=new AirportCode(getActivity());
        cc=aac.get();

        fetch();

        return v;
    }

    private void fetch()
    {
        class myThread extends AsyncTask<String,Void,String>
        {
            ProgressDialog dialog=null;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog=ProgressDialog.show(getActivity(),null,"Please Wait...",false,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                getJson(s);
            //    Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader reader=null;
                StringBuilder str=new StringBuilder();
                try {

                    String ib = URLEncoder.encode(cc, "UTF-8");
                    URL url=new URL(MyUrl.arr_url+ib);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while (true)
                    {
                        String s1=reader.readLine();
                        if(s1.isEmpty())
                        {
                            break;
                        }
                        str.append(s1);
                        Log.d("s1",s1+"\n");
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                return  str.toString();
            }
        }
        myThread thread=new myThread();
        thread.execute();
    }
    ArrayList<AllData> ll=new ArrayList<>();

    private void getJson(String s)
    {
        ll.clear();
         try
        {
            JSONArray array=new JSONArray(s);
            int len=array.length();
            if(len>0) {
                for (int i = 0; i < len; i++) {
                    JSONObject c = array.getJSONObject(i);
                    JSONObject a1 = c.getJSONObject("geography");
                    String lati = a1.getString("latitude");
                    String longi = a1.getString("longitude");
                    String alti = a1.getString("altitude");
                    String dir = a1.getString("direction");
                    Direction d = new Direction(lati, longi, alti, dir);
                    Log.d("lati", lati);

                    JSONObject c2 = c.getJSONObject("speed");
                    String hori = c2.getString("horizontal");
                    String isg = c2.getString("isGround");
                    String verti = c2.getString("vertical");
                    SpeedData sp = new SpeedData(hori, isg, verti);

                    JSONObject c3 = c.getJSONObject("departure");
                    String ia = c3.getString("iataCode");
                    String ic = c3.getString("icaoCode");
                    ArriveData ar = new ArriveData(ia, ic);

                    JSONObject c4 = c.getJSONObject("arrival");
                    String ia1 = c4.getString("iataCode");
                    String ic1 = c4.getString("icaoCode");
                    ArriveData dep = new ArriveData(ia1, ic1);


                    JSONObject c1 = c.getJSONObject("flight");
                    String iata = c1.getString("iataNumber");
                    String icao = c1.getString("icaoNumber");
                    String num = c1.getString("number");
                    Flight fl = new Flight(iata, icao, num);

                    String st = c.getString("status");
                    Log.d("dd", st);
                    AllData obj = new AllData(d, sp, ar, dep, fl, st);
                    ll.add(obj);
                }
                FlightAdapter adpt = new FlightAdapter(getActivity(), R.layout.flight_row, ll);
                list.setAdapter(adpt);
            }
            else
            {
                Toast.makeText(getActivity(), "Data not Available", Toast.LENGTH_SHORT).show();
            }

        }
        catch(Exception e)
        {
            Log.d("exception", String.valueOf(e));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     AllData dd=ll.get(position);

        Direction d=dd.geo;
        String la=d.la;
        String lo=d.lo;
        String al=d.al;
        String dir=d.dr;

     SpeedData sp=dd.sp;
     String h=sp.ho;
     String i=sp.is;
     String v=sp.ve;


     String status=dd.status;
     Flight ff=dd.fl;
     String fn=ff.c;
     Intent i1=new Intent(getActivity(),FlightDetail.class);
     i1.putExtra("h",h);
     i1.putExtra("i",i);
     i1.putExtra("v",v);
     i1.putExtra("la",la);
     i1.putExtra("lo",lo);
     i1.putExtra("al",al);
     i1.putExtra("dir",dir);
     i1.putExtra("st",status);
     i1.putExtra("fn",fn);
        startActivity(i1);

    }
}

