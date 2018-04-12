package com.example.sjy.wsc_airlines.gson;

import com.google.gson.annotations.SerializedName;

public class FlightInfo {
    @SerializedName("flightNumber")
    public String flightNumber;
    @SerializedName("price")
    public String price;
    @SerializedName("time")
    public String leaveTime;
    @SerializedName("aircraft")
    public String aircraft;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }
}
