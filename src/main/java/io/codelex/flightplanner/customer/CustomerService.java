package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.SearchFlightRequest;

import java.util.List;

public interface CustomerService {
    Flight findFlightById(Long id);

    List<Airport> searchAirport(String search);

    List<Flight> searchFlights(SearchFlightRequest request);
}