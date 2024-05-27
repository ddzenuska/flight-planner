package io.codelex.flightplanner.testingApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestingApiController {

    private final TestApiFlightsService flightService;

    public TestingApiController(TestApiFlightsService flightService) {
        this.flightService = flightService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/clear")
    public void clearFlights() {
        flightService.clearFlights();
    }
}