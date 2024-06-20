package io.codelex.flightplanner.service;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.airport.AirportInMemRepository;
import io.codelex.flightplanner.exceptions.AddFlightException;
import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.exceptions.UnsearchableFlightException;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.FlightInMemRepository;
import io.codelex.flightplanner.flight.SearchFlightRequest;

import java.util.List;

public class InMemService implements ServiceInterface {
    private final FlightInMemRepository flightInMemRepository;
    private final AirportInMemRepository airportInMemRepository;

    public InMemService(FlightInMemRepository flightInMemRepository, AirportInMemRepository airportInMemRepository) {
        this.flightInMemRepository = flightInMemRepository;
        this.airportInMemRepository = airportInMemRepository;
    }

    @Override
    public Flight addFlight(Flight request) {
        if (!isFlightValid(request)) {
            throw new AddFlightException("Invalid flight data");
        }

        Flight newFlight = new Flight(null, request.getFrom(), request.getTo(), request.getCarrier(),
                request.getDepartureTime(), request.getArrivalTime());

        if (flightInMemRepository.flightExists(newFlight)) {
            throw new DuplicateFlightException("Flight already exists");
        }

        airportInMemRepository.addAirport(request.getFrom());
        airportInMemRepository.addAirport(request.getTo());

        return flightInMemRepository.addFlight(newFlight);
    }

    @Override
    public Flight fetchFlight(long id) {
        Flight flight = flightInMemRepository.findFlightById(id);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }
        return flight;
    }

    @Override
    public void deleteFlight(long id) {
        flightInMemRepository.deleteFlight(id);
    }

    private boolean isFlightValid(Flight request) {
        boolean isValid = true;
        if (request.getFrom() == null || request.getTo() == null || request.getCarrier() == null) isValid = false;
        else if (request.getDepartureTime() == null || request.getArrivalTime() == null ||
                request.getDepartureTime().equals(request.getArrivalTime()) ||
                request.getDepartureTime().isAfter(request.getArrivalTime())) {
            isValid = false;
        } else if (isAirportInvalid(request.getFrom()) || isAirportInvalid(request.getTo())) isValid = false;
        else if (request.getFrom().equals(request.getTo())) isValid = false;
        else if (request.getCarrier().isEmpty()) isValid = false;
        return isValid;
    }

    private boolean isAirportInvalid(Airport airport) {
        return airport.getCountry() == null || airport.getCity() == null || airport.getAirport() == null ||
                airport.getCountry().trim().isEmpty() || airport.getCity().trim().isEmpty() || airport.getAirport().trim().isEmpty();
    }

    @Override
    public Flight findFlightById(Long id) {
        return flightInMemRepository.findFlightById(id);
    }

    @Override
    public List<Airport> searchAirport(String search) {
        return airportInMemRepository.searchAirports(search);
    }

    @Override
    public List<Flight> searchFlights(SearchFlightRequest request) {
        if (!isValid(request)) {
            throw new UnsearchableFlightException("Invalid search request data");
        } else {
            return flightInMemRepository.searchFlights(request);
        }
    }

    private boolean isValid(SearchFlightRequest request) {
        boolean isValid = true;
        if (request.getFrom() == null || request.getTo() == null || request.getDepartureDate() == null) {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void clearFlights() {
        flightInMemRepository.clearFlights();
    }
}