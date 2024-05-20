package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airports.AirportRepository;
import io.codelex.flightplanner.airports.Airports;
import io.codelex.flightplanner.flights.FlightRepository;
import io.codelex.flightplanner.flights.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
}