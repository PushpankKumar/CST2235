package com.example.kites.flighttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText ac;
    AirportCode aac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ac=findViewById(R.id.acode);
        aac=new AirportCode(this);

    }
    public void next(View view) {
        String s=ac.getText().toString();
        int size=s.length();
        if(size!=3)
        {
            ac.setError("Please enter 3 digit Airport code");
        }
        else
        {
            aac.save(s.toUpperCase());
            Intent i = new Intent(this,MainPage.class);
            startActivity(i);
        }
    }
}
