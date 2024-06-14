package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.airport.AirportInMemRepository;
import io.codelex.flightplanner.exceptions.UnsearchableFlightException;
import io.codelex.flightplanner.flight.FlightInMemRepository;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.SearchFlightRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public class CustomerInMemService implements CustomerService {
    private final FlightInMemRepository flightRepository;
    private final AirportInMemRepository airportRepository;

    public CustomerInMemService(FlightInMemRepository flightRepository, AirportInMemRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.findFlightById(id);
    }

    @Override
    public List<Airport> searchAirport(String search) {
        return airportRepository.searchAirports(search);
    }

    @Override
    public List<Flight> searchFlights(SearchFlightRequest request) {
         if (!isValid(request)) {
            throw new UnsearchableFlightException("Invalid search request data");
        } else {
             return flightRepository.searchFlights(request);
         }
    }

    private boolean isValid(SearchFlightRequest request) {
        boolean isValid = true;
        if (request.getFrom() == null || request.getTo() == null || request.getDepartureDate() == null) {
            isValid = false;
        }
        return isValid;
    }
}