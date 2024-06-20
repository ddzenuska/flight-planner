package io.codelex.flightplanner.controller;

import io.codelex.flightplanner.flight.*;
import io.codelex.flightplanner.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-api/flights")
public class AdminController {

    @Autowired
    private final ServiceInterface serviceClass;

    public AdminController(ServiceInterface serviceClass) {
        this.serviceClass = serviceClass;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public Flight addFlight(@RequestBody Flight request) {
        return serviceClass.addFlight(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Flight fetchFlight(@PathVariable long id) {
        return serviceClass.fetchFlight(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable long id) {
        serviceClass.deleteFlight(id);
    }
}