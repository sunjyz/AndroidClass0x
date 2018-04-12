package com.example.sjy.wsc_airlines.gson;

import com.google.gson.annotations.SerializedName;

public class Ticket {
    @SerializedName("type")
    public String ticketType;

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
