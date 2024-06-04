package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.airport.AirportDbRepository;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.exceptions.UnsearchableFlightException;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.FlightDbRepository;
import io.codelex.flightplanner.flight.SearchFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerDbService implements CustomerService {
    private final FlightDbRepository flightRepository;
    private final AirportDbRepository airportRepository;

    public CustomerDbService(FlightDbRepository flightRepository, AirportDbRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("Cannot find flight"));
    }

    @Override
    public List<Airport> searchAirport(String search) {
        return airportRepository.searchAirports(search);
    }

    @Override
    public List<Flight> searchFlights(SearchFlightRequest request) {
        if (!isValid(request)) {
            throw new ResponseStatusException(HttpStatus.OK, "Invalid search request data");
        }

        LocalDateTime startOfDay = request.getDepartureDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);

        return flightRepository.findByFromAirportAndToAirportAndDepartureTimeBetween(
                request.getFrom(), request.getTo(), startOfDay, endOfDay);
    }

    private boolean isValid(SearchFlightRequest request) {
        boolean isValid = true;
        if (request.getFrom() == null || request.getTo() == null || request.getDepartureDate() == null) {
            isValid = false;
        }
        return isValid;
    }
}