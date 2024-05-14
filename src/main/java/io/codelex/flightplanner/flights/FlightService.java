package io.codelex.flightplanner.flights;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class FlightService {

    protected final List<Flight> flights = new ArrayList<>();
    protected final AtomicLong flightIdGenerator = new AtomicLong();

    public Flight addFlight(Flight flight) {
        flight.setId(flightIdGenerator.incrementAndGet());
        flights.add(flight);
        return flight;
    }

    public Flight findFlightById(Long id) {
        return flights.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void clearFlights() {
        flights.clear();
    }
}