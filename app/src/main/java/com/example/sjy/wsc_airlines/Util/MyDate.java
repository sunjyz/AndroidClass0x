package com.example.sjy.wsc_airlines.Util;

public class MyDate {
    int year;
    int month;
    int day;
    public MyDate(int year,int month,int day){
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

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

    public int getDay(){
        return day;
    }

    public void setDate(int year,int month,int day){
        this.year=year;
        this.month=month;
        this.day=day;
    }
}
