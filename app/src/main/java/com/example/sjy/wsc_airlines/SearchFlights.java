package com.example.sjy.wsc_airlines;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sjy.wsc_airlines.Util.HttpUtil;
import com.example.sjy.wsc_airlines.Util.MyDate;
import com.example.sjy.wsc_airlines.Util.TransTime;
import com.example.sjy.wsc_airlines.gson.Airport;
import com.example.sjy.wsc_airlines.gson.FlightInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchFlights extends AppCompatActivity {
    LinearLayout flightsLayout;
    LayoutInflater inflater;
    FlightInfo[] flightInfoArray;
    Airport[] airports;
    Spinner fromSpinner;
    Spinner toSpinner;
    ArrayList<String> stringArrayList;
    HashMap<String,String> hashMap;
    TextView dateTimeText;
    MyDate myDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);
        stringArrayList=new ArrayList<>();
        hashMap=new HashMap<>();
        fromSpinner=findViewById(R.id.from_spinner);
        toSpinner=findViewById(R.id.to_spinner);
        dateTimeText=findViewById(R.id.date_text);
        Calendar c=Calendar.getInstance();
        myDate=new MyDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH)+1,c.get(Calendar.DAY_OF_MONTH)); //获取当前时间
        dateTimeText.setText(myDate.getSlashDate()); //显示当前时间
        requestAirportInfo();


    }

    /**
     * 搜索航班
     */
    public void searchFlights(View v){
        int fromPosition=fromSpinner.getSelectedItemPosition();
        int toPosition=toSpinner.getSelectedItemPosition();
        if(fromPosition==-1||toPosition==-1)
            Toast.makeText(this,"未取得出入机场信息，请检查网络是否通畅。",Toast.LENGTH_SHORT).show();
        else
            requestFlightInfo(hashMap.get(stringArrayList.get(fromPosition)),hashMap.get(stringArrayList.get(toPosition)));
    }

    /**
     * 返回主页
     */
    public void backClick(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 选择日期
     */
    public void showDatePickerDialog(View v){
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                myDate.setDate(year,month,dayOfMonth);
                dateTimeText.setText(myDate.getSlashDate());
            }
        };
        Calendar c=Calendar.getInstance();
        DatePickerDialog dialog=new DatePickerDialog
                (this, 0,listener,myDate.getYear(),myDate.getMonth(),myDate.getDay());
        dialog.show();
    }





    /**
     * 显示机场信息
     */
    public void showAirportInfo(){

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,R.layout.port,stringArrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        fromSpinner.setAdapter(arrayAdapter);
        toSpinner.setAdapter(arrayAdapter);
    }


    /**
     * 获取机场信息
     */
    public void requestAirportInfo(){
        String airportInfoUrl="http://10.0.2.2:5000/api/port/list";
        HttpUtil.sendOkHttpRequest(airportInfoUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SearchFlights.this,"获取机场信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                if(!TextUtils.isEmpty(responseText)){
                    try {
                        Gson gson=new Gson();
                        airports =gson.fromJson(responseText,Airport[].class);
                        for(int i=0;i<airports.length;i++){
                            stringArrayList.add(airports[i].getAirportName());
                            hashMap.put(airports[i].getAirportName(),airports[i].getAirportId());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showAirportInfo();
                    }
                });
            }
        });
    }


    /**
     * 显示Layout航班信息
     */
    public void showFlightInfo(){
        flightsLayout=(LinearLayout)findViewById(R.id.flights_list_layout);
        inflater = getLayoutInflater();
        flightsLayout.removeAllViews();
        for(int i = 0; i< flightInfoArray.length; i++) {
            View myLayout = inflater.inflate(R.layout.flights_list_item, flightsLayout, false);
            TextView flightNumberText =  myLayout.findViewById(R.id.flight_number_text);
            TextView timeText =  myLayout.findViewById(R.id.time_text);
            TextView priceText =  myLayout.findViewById(R.id.price_text);
            TextView aircraftText =  myLayout.findViewById(R.id.aircraft_text);
            TextView flightDateText = myLayout.findViewById(R.id.flight_date_text);
            flightNumberText.setText("flight number:"+ flightInfoArray[i].getFlightNumber());
            priceText.setText("Price:"+ flightInfoArray[i].getPrice());
            timeText.setText("Time:"+ TransTime.transTime(flightInfoArray[i].getLeaveTime()));
            aircraftText.setText("Aircraft:"+ flightInfoArray[i].getAircraft());
            flightDateText.setText(myDate.getSlashDate());
            flightsLayout.addView(myLayout);
        }
    }


    /**
     * 获取航班信息
     */
    public void requestFlightInfo(final String from, final String to){
        String flightInfoUrl="http://10.0.2.2:5000/api/schedule/list?from="+from+"&to="+to+"&date="+myDate.getUrlDate();
        HttpUtil.sendOkHttpRequest(flightInfoUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SearchFlights.this,"获取航班信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                if(!TextUtils.isEmpty(responseText)){
                    try {
                        Gson gson=new Gson();
                        flightInfoArray =gson.fromJson(responseText,FlightInfo[].class);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showFlightInfo();
                    }
                });
            }
        });
    }



}
