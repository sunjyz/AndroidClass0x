package com.example.sjy.wsc_airlines.gson;

import com.google.gson.annotations.SerializedName;

public class Airport {
    @SerializedName("id")
    public String AirportId;
    @SerializedName("name")
    public String AirportName;

    public String getAirportId() {
        return AirportId;
    }

    public void setAirportId(String airportId) {
        AirportId = airportId;
    }

    public String getAirportName() {
        return AirportName;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }
}
