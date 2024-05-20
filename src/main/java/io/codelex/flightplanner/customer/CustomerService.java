package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airports.AirportRepository;
import io.codelex.flightplanner.airports.Airports;
import io.codelex.flightplanner.flights.FlightRepository;
import io.codelex.flightplanner.flights.Flight;
import io.codelex.flightplanner.flights.SearchFlightsRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<Airports> searchAirport(String phrase) {
        return airportRepository.searchAirports(phrase);
    }

    public List<Flight> searchFlights(SearchFlightsRequest request) {
        if (!isValid(request)) {
            throw new IllegalArgumentException("Invalid search request data");
        }

        return flightRepository.searchFlights(request);
    }

    private boolean isValid(SearchFlightsRequest request) {
        if (request.getFrom() == null || request.getTo() == null || request.getDepartureDate() == null) {
            return false;
        }
        if (request.getFrom().equals(request.getTo())) {
            return false;
        }
        return true;
    }
}