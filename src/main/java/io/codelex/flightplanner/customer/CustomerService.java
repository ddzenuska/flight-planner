package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airport.AirportRepository;
import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.exceptions.AddFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.exceptions.UnsearchableFlightException;
import io.codelex.flightplanner.flight.FlightRepository;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.SearchFlightRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomerService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public CustomerService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public Flight findFlightById(Long id) {
        return flightRepository.findFlightById(id);
    }


    public List<Airport> searchAirport(String search) {
        return airportRepository.searchAirports(search);
    }

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