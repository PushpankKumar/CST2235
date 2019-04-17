package com.example.kites.flighttracker;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class SqliteDbSaveFlight {
    SQLiteDatabase sqliteDB;
    Context c;


    SqliteDbSaveFlight(Context c)
    {
    this.c=c;
    createDB();
    createTable();
    }
    private void createDB() {
        sqliteDB=c.openOrCreateDatabase("record",Context.MODE_PRIVATE,null);
        Toast.makeText(c,"Database created",Toast.LENGTH_SHORT).show();
    }

    private void createTable() {
        sqliteDB.execSQL("create table if not exists FlightNum(id integer " +
                "primary key autoincrement " +
                "not null," +
                "flight_num text)");
        Toast.makeText(c,"table created",Toast.LENGTH_SHORT).show();
    }
    public void save(String n)
    {
        boolean bb=check(n);
        if(!bb) {
            sqliteDB.execSQL("insert into FlightNum(flight_num) values " +
                    "('" + n + "')");
            Toast.makeText(c, "inserted successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(c, "Already Saved", Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<String> fetchRecords()
    {
        ArrayList<String> list=new ArrayList<String>();
        Cursor crsr=sqliteDB.rawQuery("select * from FlightNum",null);
        crsr.moveToFirst();
        while (!crsr.isAfterLast())
        {
            int id=crsr.getInt(0);
            String name=crsr.getString(1);
            list.add(name);
            crsr.moveToNext();
        }
        return list;
    }
    public boolean check (String n)
    {
        boolean b=false;
        Cursor crsr=sqliteDB.rawQuery("select * from FlightNum where flight_num= '"+n+"'",null);
        crsr.moveToFirst();
        if (!crsr.isAfterLast())
        {
         b=true;
        }
        return b;
    }
}
