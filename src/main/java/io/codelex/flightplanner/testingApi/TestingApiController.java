package io.codelex.flightplanner.testingApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestingApiController {

    private final TestApiFlightsService flightService;

    public TestingApiController(TestApiFlightsService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearFlights() {
        flightService.clearFlights();
        return ResponseEntity.ok().build();
    }
}