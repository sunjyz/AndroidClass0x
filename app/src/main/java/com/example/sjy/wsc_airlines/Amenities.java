package com.example.sjy.wsc_airlines;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Amenities extends AppCompatActivity {
    String[] serviceName=new String[]{"Extra Blanket",
        "Next Seat Free",
        "Two Neighboring Seats Free",
        "Tablet Rental",
        "Laptop Rental",
        "Lounge Access",
        "Soft Drinks",
        "Premium Headphones Rental",
        "Extra Bag",
        "Fast Checkin Lane",
        "Wi-Fi 50 mb",
        "Wi-Fi 250 mb",
    };
    String[] servicePrice=new String[]{"10",
            "30",
            "50",
            "12",
            "15",
            "25",
            "0",
            "5",
            "15",
            "10",
            "0",
            "25"
    };
    LinearLayout serviceLayout;
    LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenities);

        serviceLayout=findViewById(R.id.service_list_layout);
        inflater = getLayoutInflater();
        serviceLayout.removeAllViews();
        for(int i = 0; i<serviceName.length; i++) {
            View myLayout = inflater.inflate(R.layout.service_list_item, serviceLayout, false);
            TextView serviceNameText =  myLayout.findViewById(R.id.Service_Name_Text);
            TextView servicePriceText =  myLayout.findViewById(R.id.Service_Price_Text);
            serviceNameText.setText("Service:"+ serviceName[i]);
            servicePriceText.setText("Price:"+ servicePrice[i]);
            serviceLayout.addView(myLayout);
        }

    }
    public void backClick(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    class Service {
        String serviceName;
        String servicePrice;

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(String servicePrice) {
            this.servicePrice = servicePrice;
        }
    }
}
