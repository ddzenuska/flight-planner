package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.airport.AirportDbRepository;
import io.codelex.flightplanner.exceptions.AddFlightException;
import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.flight.FlightDbRepository;
import jakarta.transaction.Transactional;

import java.util.List;

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

        System.out.println("Adding flight: " + request);

        Airport fromAirport = findOrCreateAirport(request.getFrom());
        Airport toAirport = findOrCreateAirport(request.getTo());

        Flight newFlight = new Flight(null, fromAirport, toAirport, request.getCarrier(),
                request.getDepartureTime(), request.getArrivalTime());

        if (flightDbRepository.existsByFromAndToAndDepartureTimeAndArrivalTimeAndIdNot(fromAirport, toAirport, request.getDepartureTime(), request.getArrivalTime(), request.getId())) {
            System.out.println("Duplicate found!");
            throw new DuplicateFlightException("Flight already exists");
        }

        System.out.println("Saving new flight: " + newFlight);
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

    private Airport findOrCreateAirport(Airport request) {
        System.out.println("Searching for airport: " + request);

        List<Airport> foundAirports = airportDbRepository.findByAirportContainingIgnoreCaseOrCityContainingIgnoreCaseOrCountryContainingIgnoreCase(
                request.getAirport(), request.getCity(), request.getCountry());

        System.out.println("Found airports: " + foundAirports);

        Airport airport = foundAirports.stream()
                .filter(a -> a.equals(request))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Creating new airport: " + request);
                    return airportDbRepository.save(request);
                });

        System.out.println("Returning airport: " + airport);
        return airport;
    }
}