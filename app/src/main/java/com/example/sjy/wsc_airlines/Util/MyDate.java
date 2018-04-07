package com.example.sjy.wsc_airlines.Util;

public class MyDateTime {
    int year;
    int month;
    int day;
    public MyDateTime(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }
    public String getSlashDate(){
        return month+"/"+day+"/"+year;
    }
    public String getUrlDate(){
        return year+"-"+month+"-"+day;
    }
    public void setDateTime(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }
}
