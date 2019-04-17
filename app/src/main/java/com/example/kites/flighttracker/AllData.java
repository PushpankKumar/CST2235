package com.example.kites.flighttracker;

class AllData {
    Direction geo;
    SpeedData sp;
    ArriveData arr;
    ArriveData dep;
    Flight fl;
    String status;
    public AllData(Direction geo,SpeedData d,ArriveData dep, ArriveData arr,Flight ff,String st)
    {
        this.geo=geo;
        this.sp=d;
        this.arr=arr;
        this.dep=dep;
        fl=ff;
        status=st;

    }
}
