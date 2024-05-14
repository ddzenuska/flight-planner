package io.codelex.flightplanner.flights;

import io.codelex.flightplanner.airports.AirportRepository;

import java.time.LocalDateTime;

public class Flight {
    private Long id;
    private AirportRepository from;
    private AirportRepository to;
    private String carrier;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(Long id, AirportRepository from, AirportRepository to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.carrier = carrier;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirportRepository getFrom() {
        return from;
    }

    public void setFrom(AirportRepository from) {
        this.from = from;
    }

    public AirportRepository getTo() {
        return to;
    }

    public void setTo(AirportRepository to) {
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