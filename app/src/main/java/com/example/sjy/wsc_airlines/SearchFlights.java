package com.example.sjy.wsc_airlines;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchFlights extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        LinearLayout flightsLayout=(LinearLayout)findViewById(R.id.flights_list_layout);
        LayoutInflater inflater = getLayoutInflater();
        for(int i=0;i<10;i++) {
            View myLayout = inflater.inflate(R.layout.flights_list_item, flightsLayout, false);
            TextView textView1 = (TextView) myLayout.findViewById(R.id.flight_number_text);
            TextView textView2 = (TextView) myLayout.findViewById(R.id.time_text);
            TextView textView3 = (TextView) myLayout.findViewById(R.id.price_text);
            TextView textView4 = (TextView) myLayout.findViewById(R.id.aircraft_text);
            TextView textView5 = (TextView) myLayout.findViewById(R.id.flight_date_text);
            textView1.setText("12345"+i);
            textView2.setText("11:3"+i);
            textView3.setText("xxx"+i);
            textView4.setText("xxxxxx"+i);
            textView5.setText("12/22/201"+i);
            flightsLayout.addView(myLayout);
        }
    }
}
