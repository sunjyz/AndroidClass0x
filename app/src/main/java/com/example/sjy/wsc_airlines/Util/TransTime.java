package com.example.sjy.wsc_airlines.Util;

import android.util.Log;

public class TransTime {
    public static String transTime(String time){
        String mytime=time;
        if(mytime.length()==3){
            String min=mytime.substring(2,3);
            String hour=mytime.substring(0,1);
            mytime="0"+hour+":0"+min;
        }else if(mytime.length()==4){
            if(mytime.substring(2,3).equals(":")){
                String min=mytime.substring(3,4);
                String hour=mytime.substring(0,2);
                mytime=hour+":0"+min;
            }else {
                String min=mytime.substring(2,4);
                String hour=mytime.substring(0,1);
                mytime="0"+hour+":"+min;
            }
        }else{
            ;
        }

        return mytime;
    }
}
