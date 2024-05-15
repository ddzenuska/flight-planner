package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.airports.Airports;
import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flights.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminFlightService {

    private final FlightRepository flightRepository;
    private static final Logger logger = LoggerFactory.getLogger(AdminFlightService.class);

    public AdminFlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flights addFlight(AddFlightRequest request) {
        logger.info("Attempting to add flight: {}", request);
        if (!isValid(request)) {
            logger.error("Invalid flight data: {}", request);
            throw new IllegalArgumentException("Invalid flight data");
        }

        Flights newFlight = new Flights(null, request.getFrom(), request.getTo(), request.getCarrier(),
                request.getDepartureTime(), request.getArrivalTime());

        if (flightRepository.flightExists(newFlight)) {
            logger.error("Duplicate flight detected: {}", newFlight);
            throw new DuplicateFlightException("Flight already exists");
        }

        Flights addedFlight = flightRepository.addFlight(newFlight);
        logger.info("Flight added successfully: {}", addedFlight);
        return addedFlight;
    }

    public Flights fetchFlight(long id) {
        Flights flight = flightRepository.findFlightById(id);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }
        return flight;
    }

    public void deleteFlight(long id) {
        Flights flight = flightRepository.findFlightById(id);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }
        flightRepository.flightsList.remove(flight);
    }

    private boolean isValid(AddFlightRequest request) {
        if (request.getFrom() == null || request.getTo() == null || request.getCarrier() == null ||
                request.getDepartureTime() == null || request.getArrivalTime() == null) {
            logger.error("Validation failed: Missing required fields. Request: {}", request);
            return false;
        }

        if (isAirportInvalid(request.getFrom()) || isAirportInvalid(request.getTo())) {
            logger.error("Validation failed: Airport information is incomplete. Request: {}", request);
            return false;
        }

        if (request.getFrom().equals(request.getTo())) {
            logger.error("Validation failed: From and To airports are the same. Request: {}", request);
            return false;
        }

        if (request.getCarrier().isEmpty()) {
            logger.error("Validation failed: Carrier is empty. Request: {}", request);
            return false;
        }

        if (request.getDepartureTime().isAfter(request.getArrivalTime()) || request.getDepartureTime().isEqual(request.getArrivalTime())) {
            logger.error("Validation failed: Departure time is after or the same as arrival time. Request: {}", request);
            return false;
        }
        return true;
    }

    private boolean isAirportInvalid(Airports airport) {
        return airport.getCountry() == null || airport.getCity() == null || airport.getAirport() == null ||
                airport.getCountry().trim().isEmpty() || airport.getCity().trim().isEmpty() || airport.getAirport().trim().isEmpty();
    }
}