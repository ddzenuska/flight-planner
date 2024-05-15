package io.codelex.flightplanner.flights;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.codelex.flightplanner.airports.Airports;

import java.time.LocalDateTime;

public class AddFlightRequest {
    private Airports from;
    private Airports to;
    private String carrier;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime arrivalTime;

    public AddFlightRequest() {
    }

    public AddFlightRequest(Airports from, Airports to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Airports getFrom() {
        return from;
    }

    public void setFrom(Airports from) {
        this.from = from;
    }

    public Airports getTo() {
        return to;
    }

    public void setTo(Airports to) {
        this.to = to;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}


