package io.codelex.flightplanner.testingApi;

import io.codelex.flightplanner.flights.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestingApiController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/clear")
    public ResponseEntity<Void> clearFlights() {
        flightService.clearFlights();
        return ResponseEntity.ok().build();
    }
}