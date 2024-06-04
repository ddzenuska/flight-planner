package io.codelex.flightplanner.testingApi;

import io.codelex.flightplanner.flight.FlightInMemRepository;
import org.springframework.stereotype.Service;

@Service
public class TestApiFlightsService {

    private final FlightInMemRepository flightRepository;

    public TestApiFlightsService(FlightInMemRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void clearFlights() {
        flightRepository.clearFlights();
    }
}