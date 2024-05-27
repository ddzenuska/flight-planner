package io.codelex.flightplanner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DuplicateFlightException extends ResponseStatusException {
    public DuplicateFlightException(String message) {
        super(HttpStatus.CONFLICT);
    }
}