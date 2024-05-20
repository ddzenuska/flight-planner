package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airports.Airports;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flights.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airports>> searchAirport(@RequestParam(required = false) String search) {
        if (search == null || search.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Airports> airports = customerService.searchAirport(search);
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/flights/{id}")
    public Flight findFlightById(@PathVariable long id) {
        Flight flight = customerService.findFlightById(id);
        if (flight == null) {
            throw new FlightNotFoundException("Flight not found");
        }
        return flight;
    }
}