package com.example.kites.flighttracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FlightDetail extends AppCompatActivity {

    TextView lati,longi,alti,dir,status,hori,isg,vrti,fn;
    String flight_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_detail);
        lati=findViewById(R.id.lati);
        longi=findViewById(R.id.longi);
        alti=findViewById(R.id.alti);
        dir=findViewById(R.id.dir);
        status=findViewById(R.id.status);
        hori=findViewById(R.id.hori);
        isg=findViewById(R.id.isg);
        vrti=findViewById(R.id.verti);
        fn=findViewById(R.id.fn);
        setData();
    }

    private void setData() {
        Bundle b=getIntent().getExtras();
        lati.setText(b.getString("la"));
        longi.setText(b.getString("lo"));
        alti.setText(b.getString("al"));
        dir.setText(b.getString("dir"));

        status.setText(b.getString("st"));
        hori.setText(b.getString("h"));
        isg.setText(b.getString("i"));
        vrti.setText(b.getString("v"));
        flight_num=b.getString("fn");
        fn.setText(flight_num);

    }

    public void save(View view)
    {
        SqliteDbSaveFlight dd=new SqliteDbSaveFlight(this);
        dd.save(flight_num);

    }
}
