package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.airport.AirportDbRepository;
import io.codelex.flightplanner.exceptions.AddFlightException;
import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.FlightDbRepository;

public class AdminDbService implements AdminService {
    private final FlightDbRepository flightDbRepository;
    private final AirportDbRepository airportDbRepository;

    public AdminDbService(FlightDbRepository flightDbRepository, AirportDbRepository airportDbRepository) {
        this.flightDbRepository = flightDbRepository;
        this.airportDbRepository = airportDbRepository;
    }

    @Override
    public Flight addFlight(Flight request) {
        if (!isFlightValid(request)) {
            throw new AddFlightException("Invalid flight data");
        }

        Flight newFlight = new Flight(null, request.getFrom(), request.getTo(), request.getCarrier(),
                request.getDepartureTime(), request.getArrivalTime());

        if (flightDbRepository.existsByFromAndToAndDepartureTime(newFlight.getFrom(), newFlight.getTo(), newFlight.getDepartureTime())) {
            throw new DuplicateFlightException("Flight already exists");
        }

        airportDbRepository.save(request.getFrom());
        airportDbRepository.save(request.getTo());

        return flightDbRepository.save(newFlight);
    }

    @Override
    public Flight fetchFlight(long id) {
        return flightDbRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("Flight not found"));
    }

    @Override
    public void deleteFlight(long id) {
        flightDbRepository.deleteById(id);
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
}