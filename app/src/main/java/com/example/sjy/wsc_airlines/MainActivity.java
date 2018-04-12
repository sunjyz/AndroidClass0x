package com.example.sjy.wsc_airlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sjy.wsc_airlines.gson.Airport;

public class MainActivity extends AppCompatActivity {
    Button searchFlights;
    Button reserveSeats;
    Button amenities;
    Button aboutOurAirline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchFlights=findViewById(R.id.Search_Flights_Layout_Button);
        searchFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SearchFlights.class);
                startActivity(intent);
                finish();
            }
        });
        reserveSeats=findViewById(R.id.Reserver_Seats_Button);
        reserveSeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ReserveSeats.class);
                startActivity(intent);
                finish();
            }
        });
        amenities=findViewById(R.id.Amenities_Button);
        amenities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Amenities.class);
                startActivity(intent);
                finish();
            }
        });
        aboutOurAirline=findViewById(R.id.About_Our_Airline_Button);
        aboutOurAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AboutOurAirline.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
