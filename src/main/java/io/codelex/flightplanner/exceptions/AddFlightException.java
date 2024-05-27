package io.codelex.flightplanner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AddFlightException extends ResponseStatusException {
    public AddFlightException(String message) {
            super(HttpStatus.BAD_REQUEST);
    }
}