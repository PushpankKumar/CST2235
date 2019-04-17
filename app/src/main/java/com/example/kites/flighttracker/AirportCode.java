package com.example.kites.flighttracker;

import android.content.Context;
import android.content.SharedPreferences;

public class AirportCode {
    Context m;
     SharedPreferences shf;
    SharedPreferences.Editor edit;
    public  AirportCode(Context c)
    {
        m=c;
        shf= m.getSharedPreferences("Ac", Context.MODE_PRIVATE);
        edit=shf.edit();
    }
    public void save(String cc)
    {
        edit.putString("ac",cc);
        edit.commit();
    }

    public String  get()
    {
       String s=shf.getString("ac","none");
       return  s;
    }
}
