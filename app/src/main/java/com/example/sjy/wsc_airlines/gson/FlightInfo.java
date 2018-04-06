package com.example.sjy.wsc_airlines.gson;

import com.google.gson.annotations.SerializedName;

public class FlightSchedule {
    @SerializedName("flightNumber")
    public String flightNumber;
    @SerializedName("price")
    public String price;
    @SerializedName("time")
    public String leaveTime;
    @SerializedName("aircraft")
    public String aircraft;
}
