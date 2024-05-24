package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.airports.AirportRepository;
import io.codelex.flightplanner.airports.Airports;
import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flights.*;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public AdminService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public Flight addFlight(Flight request) {
        if (!isValid(request)) {
            throw new IllegalArgumentException("Invalid flight data");
        }

        Flight newFlight = new Flight(null, request.getFrom(), request.getTo(), request.getCarrier(),
                request.getDepartureTime(), request.getArrivalTime());

        if (flightRepository.flightExists(newFlight)) {
            throw new DuplicateFlightException("Flight already exists");
        }

        airportRepository.addAirport(request.getFrom());
        airportRepository.addAirport(request.getTo());

        return flightRepository.addFlight(newFlight);
    }

    public Flight fetchFlight(long id) {
        Flight flight = flightRepository.findFlightById(id);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }
        return flight;
    }

    public void deleteFlight(long id) {
        flightRepository.deleteFlight(id);
    }

    private boolean isValid(Flight request) {
        if (request.getFrom() == null || request.getTo() == null || request.getCarrier() == null ||
                request.getDepartureTime() == null || request.getArrivalTime() == null) {
            return false;
        }
        if (isAirportInvalid(request.getFrom()) || isAirportInvalid(request.getTo())) {
            return false;
        }
        if (request.getFrom().equals(request.getTo())) {
            return false;
        }
        if (request.getCarrier().isEmpty()) {
            return false;
        }
        if (request.getDepartureTime().isAfter(request.getArrivalTime()) || request.getDepartureTime().isEqual(request.getArrivalTime())) {
            return false;
        }
        return true;
    }

    private boolean isAirportInvalid(Airports airport) {
        return airport.getCountry() == null || airport.getCity() == null || airport.getAirport() == null ||
                airport.getCountry().trim().isEmpty() || airport.getCity().trim().isEmpty() || airport.getAirport().trim().isEmpty();
    }
}