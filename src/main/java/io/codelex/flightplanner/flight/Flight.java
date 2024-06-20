package io.codelex.flightplanner.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.codelex.flightplanner.airport.Airport;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_airport_id")
    private Airport from;

    @ManyToOne
    @JoinColumn(name = "to_airport_id")
    private Airport to;

    private String carrier;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime departureTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime arrivalTime;

    public Flight() {
    }

    public Flight(Long id, Airport from, Airport to, String carrier, LocalDateTime departureTime, LocalDateTime arrivalTime) {
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

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flights = (Flight) o;
        return Objects.equals(from, flights.from) &&
                Objects.equals(to, flights.to) &&
                Objects.equals(carrier, flights.carrier) &&
                Objects.equals(departureTime, flights.departureTime) &&
                Objects.equals(arrivalTime, flights.arrivalTime);
    }
}