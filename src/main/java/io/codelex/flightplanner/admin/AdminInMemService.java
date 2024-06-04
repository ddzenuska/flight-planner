package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.airport.AirportInMemRepository;
import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.exceptions.AddFlightException;
import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flight.*;
import org.springframework.stereotype.Service;

public class AdminInMemService implements AdminService {
    private final FlightInMemRepository flightRepository;
    private final AirportInMemRepository airportRepository;

    public AdminInMemService(FlightInMemRepository flightRepository, AirportInMemRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public Flight addFlight(Flight request) {
        if (!isFlightValid(request)) {
            throw new AddFlightException("Invalid flight data");
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

    private boolean isFlightValid(Flight request) {
        boolean isValid = true;
        if (request.getFrom() == null || request.getTo() == null || request.getCarrier() == null) isValid = false;
        else if (request.getDepartureTime() == null || request.getArrivalTime() == null ||
                request.getDepartureTime().equals(request.getArrivalTime()) ||
                request.getDepartureTime().isAfter(request.getArrivalTime())) {
            isValid = false;
        }
        else if (isAirportInvalid(request.getFrom()) || isAirportInvalid(request.getTo())) isValid = false;
        else if (request.getFrom().equals(request.getTo())) isValid = false;
        else if (request.getCarrier().isEmpty()) isValid = false;
        return isValid;
    }

    private boolean isAirportInvalid(Airport airport) {
        return airport.getCountry() == null || airport.getCity() == null || airport.getAirport() == null ||
                airport.getCountry().trim().isEmpty() || airport.getCity().trim().isEmpty() || airport.getAirport().trim().isEmpty();
    }
}