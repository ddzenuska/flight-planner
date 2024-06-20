package io.codelex.flightplanner.service;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.SearchFlightRequest;

import java.util.List;

public interface ServiceInterface {
    Flight addFlight(Flight request);

    Flight fetchFlight(long id);

    void deleteFlight(long id);

    Flight findFlightById(Long id);

    List<Airport> searchAirport(String search);

    List<Flight> searchFlights(SearchFlightRequest request);

    void clearFlights();
}
