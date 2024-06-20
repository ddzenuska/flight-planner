package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.service.ServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestingApiController {

    private final ServiceInterface serviceClass;

    public TestingApiController(ServiceInterface serviceClass) {
        this.serviceClass = serviceClass;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/clear")
    public void clearFlights() {
        serviceClass.clearFlights();
    }
}