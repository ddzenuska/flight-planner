package io.codelex.flightplanner.testingApi;

import io.codelex.flightplanner.flights.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class TestApiFlightsService {

    private final FlightRepository flightRepository;

    public TestApiFlightsService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clearFlights() {
        flightRepository.clearFlights();
    }
}