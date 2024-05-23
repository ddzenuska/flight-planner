package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airports.Airports;
import io.codelex.flightplanner.flights.Flight;
import io.codelex.flightplanner.pages.PageResult;
import io.codelex.flightplanner.flights.SearchFlightsRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/airports")
    public ResponseEntity<List<Airports>> searchAirport(@RequestParam(required = false) String search) {
        if (search == null || search.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Airports> airports = customerService.searchAirport(search);
        return ResponseEntity.ok(airports);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable long id) {
        Flight flight = customerService.findFlightById(id);
        if (flight == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flight);
    }

    @PostMapping("/flights/search")
    public ResponseEntity<PageResult<Flight>> searchFlights(@RequestBody SearchFlightsRequest request) {
        try {
            List<Flight> flights = customerService.searchFlights(request);
            PageResult<Flight> result = new PageResult<>(0, flights.size(), flights);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (IllegalArgumentException | HttpMessageNotReadableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
