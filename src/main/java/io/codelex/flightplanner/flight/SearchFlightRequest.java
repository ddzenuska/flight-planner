package io.codelex.flightplanner.flight;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class SearchFlightRequest {
    private String from;
    private String to;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate departureDate;

    public SearchFlightRequest() {
    }

    public SearchFlightRequest(String from, String to, LocalDate departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
}