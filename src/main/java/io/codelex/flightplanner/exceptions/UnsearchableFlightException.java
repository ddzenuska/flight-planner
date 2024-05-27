package io.codelex.flightplanner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnsearchableFlightException extends ResponseStatusException {
    public UnsearchableFlightException(String status) {
        super(HttpStatus.BAD_REQUEST);
    }
}