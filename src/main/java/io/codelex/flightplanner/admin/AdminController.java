package io.codelex.flightplanner.admin;

import io.codelex.flightplanner.exceptions.DuplicateFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flights.*;
import io.codelex.flightplanner.flights.AddFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminController {

    private final AdminFlightService adminFlightService;

    public AdminController(AdminFlightService adminFlightService) {
        this.adminFlightService = adminFlightService;
    }

    @PutMapping
    public ResponseEntity<?> addFlight(@RequestBody AddFlightRequest request) {
        try {
            Flights flight = adminFlightService.addFlight(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(flight);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid flight data");
        } catch (DuplicateFlightException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Flight already exists");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flights> fetchFlight(@PathVariable long id) {
        try {
            Flights flight = adminFlightService.fetchFlight(id);
            return ResponseEntity.ok(flight);
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable long id) {
        try {
            adminFlightService.deleteFlight(id);
            return ResponseEntity.ok().build();
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}