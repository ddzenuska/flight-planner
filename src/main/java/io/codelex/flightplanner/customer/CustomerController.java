package io.codelex.flightplanner.customer;

import io.codelex.flightplanner.airport.Airport;
import io.codelex.flightplanner.exceptions.AddFlightException;
import io.codelex.flightplanner.exceptions.FlightNotFoundException;
import io.codelex.flightplanner.flight.Flight;
import io.codelex.flightplanner.pages.PageResult;
import io.codelex.flightplanner.flight.SearchFlightRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/airports")
    @ResponseStatus(HttpStatus.OK)
    public List<Airport> searchAirport(@RequestParam(required = false) String search) {
        if (search == null || search.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Search parameter is required");
        }
        return customerService.searchAirport(search);
    }

    @GetMapping("/flights/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flight findFlightById(@PathVariable long id) {
        Flight flight = customerService.findFlightById(id);
        if (flight == null) {
            throw new FlightNotFoundException("Cannot find flight");
        }
        return flight;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@RequestBody SearchFlightRequest request) {
        if (request.getFrom().equals(request.getTo())) {
            throw new AddFlightException("");
        } else {
            return new PageResult<>(0, customerService.searchFlights(request).size(), customerService.searchFlights(request));
        }
    }
}