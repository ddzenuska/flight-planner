package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.flights.FlightRepository;
import io.codelex.flightplanner.flights.Flights;

public class CustomerFlightsService {

    private final FlightRepository flightRepository;

    public CustomerFlightsService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flights findFlightById(Long id) {
        return flightRepository.findFlightById(id);
    }
}
