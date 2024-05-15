package io.codelex.flightplanner.flights;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class FlightRepository {
    public final List<Flights> flightsList = new ArrayList<>();
    protected final AtomicLong flightIdGenerator = new AtomicLong();

    public Flights addFlight(Flights flight) {
        flight.setId(flightIdGenerator.incrementAndGet());
        flightsList.add(flight);
        return flight;
    }

    public Flights findFlightById(Long id) {
        return flightsList.stream()
                .filter(flight -> flight.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void clearFlights() {
        flightsList.clear();
    }

    public boolean flightExists(Flights flight) {
        return flightsList.stream()
                .anyMatch(f -> f.equals(flight));
    }
}