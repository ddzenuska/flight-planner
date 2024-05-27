package io.codelex.flightplanner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FlightNotFoundException extends ResponseStatusException {
    public FlightNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND);
    }
}